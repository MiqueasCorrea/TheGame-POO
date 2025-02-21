package model.clases;

import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import model.enums.EstadoJugador;
import model.enums.EstadoPartida;
import model.enums.Eventos;
import model.excepciones.JugadorExistente;
import model.excepciones.JugadorNoExistente;
import model.interfaces.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Modelo extends ObservableRemoto implements IModelo, Serializable {
    private static IModelo instancia = null;
    private ISesion usuarios;
    private Map<Integer, IPartida> partidas;
    private IPartidaGuardada partidas_guardadas;
    private IRanking ranking;

    public static IModelo getInstancia() throws RemoteException{
        if (instancia == null) {
            instancia = new Modelo();
        }
        return instancia;
    }

    private Modelo(){
        usuarios = Sesion.getInstancia();
        partidas = new HashMap<>();
        ranking = Ranking.getInstancia();
        partidas_guardadas = PartidaGuardada.getInstancia();
        cargarPartidasPersistidas();
    }

    // GESTION PARTIDAS
    @Override
    public IPartida crearPartida(int cantidadJugadores) throws RemoteException {
        IPartida nueva_partida = new Partida(cantidadJugadores);
        nueva_partida.setEstado(EstadoPartida.EN_ESPERA);
        this.partidas.put(nueva_partida.getId(), nueva_partida);
        notificarObservadores(new ManejadorEventos(nueva_partida.getId(), Eventos.CAMBIO_BUSCAR_PARTIDA));
        return nueva_partida;
    }

    @Override
    public List<IPartida> getPartidas() throws RemoteException{
        return new ArrayList<>(partidas.values());
    }

    @Override
    public IPartida getPartida(int id) throws RemoteException{
        return partidas.get(id);
    }

    @Override
    public IMazo getMazo(int id_partida) throws RemoteException{
        return getPartida(id_partida).getMazo();
    }

    @Override
    public void agregarJugadorAPartida(int id_partida, String jugador) throws RemoteException{
        IPartida partida = partidas.get(id_partida);
        partida.agregarJugador(jugador);
        notificarObservadores(new ManejadorEventos(partida.getId(), Eventos.CAMBIO_ESPERANDO_JUGADORES));
        notificarObservadores(new ManejadorEventos(partida.getId(), Eventos.CAMBIO_BUSCAR_PARTIDA));
    }

    @Override
    public void empezarPartida(int id_partida_actual) throws RemoteException{
        IPartida partida = getPartida(id_partida_actual);
        if (partida.getEstado() != EstadoPartida.EN_JUEGO){
            partida.setEstado(EstadoPartida.EN_JUEGO);
            partida.establecerTurnos();
            partida.repartirCartas();
        }
    }

    @Override
    public IJugador getTurno(int id_partida) throws RemoteException {
        return getPartida(id_partida).getTurno();
    }

    @Override
    public void jugarTurno(int id_partida, int zonasMano, int zonasCentro) throws RemoteException {
        if (getPartida(id_partida).jugarTurno(zonasMano, zonasCentro)){
            notificarObservadores(new ManejadorEventos(id_partida, Eventos.ACTUALIZACION_CARTA));
        }
    }

    @Override
    public void siguienteTurno(int id_partida) throws RemoteException {
        IPartida partida = getPartida(id_partida);
        if (partida.getTurno().getCantidadCartasTiradas() > 0){
            if (!partida.getTurno().isPrimeraCarta_en_mano()){
                ICarta carta1 = partida.getMazo().agarrarCartaTope();
                partida.getTurno().setPrimeraCartaDelJugador(carta1);
                partida.getTurno().setPrimeraCarta_en_mano(true);
            }
            if (!partida.getTurno().isSegundaCarta_en_mano()){
                ICarta carta2 = partida.getMazo().agarrarCartaTope();
                partida.getTurno().setSegundaCartaDelJugador(carta2);
                partida.getTurno().setSegundaCarta_en_mano(true);
            }
            partida.getTurno().setCantidadCartasTiradas(0);
            getPartida(id_partida).siguienteTurno();
            notificarObservadores(new ManejadorEventos(id_partida, Eventos.CAMBIO_TURNO));
        }
    }

    @Override
    public boolean gameOver(int id_partida) throws RemoteException{
        IPartida partida = getPartida(id_partida);
        if (partida.gameOver()){
            notificarObservadores(new ManejadorEventos(id_partida, Eventos.GAME_OVER));
            partidas.remove(id_partida);
            borrarPartidaPersistida(id_partida);
            return true;
        }
        return false;
    }

    @Override
    public boolean gameWin(int id_partida) throws RemoteException{
        IPartida partida = getPartida(id_partida);
        if (partida.gameWin()){
            notificarObservadores(new ManejadorEventos(id_partida, Eventos.GAME_WIN));
            partidas.remove(id_partida);
            borrarPartidaPersistida(id_partida);
            return true;
        }
        return false;
    }

    private void borrarPartidaPersistida(int id_partida) throws RemoteException{
        partidas_guardadas.borrarPartidaGuardada(id_partida);
    }

    private void cargarPartidasPersistidas(){
        Map<Integer, IPartida> partidas_g = partidas_guardadas.getPartidasGuardadas();
        for (Map.Entry<Integer, IPartida> entry : partidas_g.entrySet()){
            partidas.put(entry.getKey(), entry.getValue());
        }
    }

    // GESTION USUARIOS-OBSERVADORES
    @Override
    public void registrarUsuario(String nombre, String password) throws JugadorExistente, RemoteException {
        usuarios.registrarse(nombre, password);
    }

    @Override
    public void iniciarSesion(String nombre, String password) throws JugadorNoExistente, RemoteException {
        usuarios.iniciarSesion(nombre, password);
    }

    @Override
    public void actualizarRanking(String nombre) throws RemoteException{
        ranking.actualizar(nombre);
    }

    @Override
    public Map<String, Integer> getRanking() throws RemoteException{
        return ranking.getRanking();
    }

    @Override
    public void desconectarJugador(String nombre_jugador, int id_partida) throws RemoteException {
        IPartida partida = getPartida(id_partida);
        partida.setEstadoJugador(nombre_jugador, EstadoJugador.DESCONECTADO);
        partida.decrementarCantidadJugadoresActuales();
        partidas_guardadas.actualizar(partida);
        if (partida.getEstado() == EstadoPartida.EN_ESPERA){
            notificarObservadores(new ManejadorEventos(id_partida, Eventos.DESCONEXION_E));
        } else {
            notificarObservadores(new ManejadorEventos(id_partida, Eventos.DESCONEXION_J));
        }
    }

    @Override
    public void cerrar(IObservadorRemoto observador) throws RemoteException{
        this.removerObservador(observador);
    }

    @Override
    public void reconectarJugador(String nombre_jugador, int id_partida) throws RemoteException {
        IPartida partida = partidas_guardadas.getPartidasGuardadas(nombre_jugador).get(id_partida);

        partida.setEstadoJugador(nombre_jugador, EstadoJugador.CONECTADO);
        partida.incrementarCantidadJugadoresActuales();
        partidas_guardadas.actualizar(partida);
        if (partida.getEstado() == EstadoPartida.EN_ESPERA){
            notificarObservadores(new ManejadorEventos(id_partida, Eventos.RECONEXION_E));
            System.out.println("entra a reconexion e");
        } else {
            System.out.println("entra a reconexion j");
            notificarObservadores(new ManejadorEventos(id_partida, Eventos.RECONEXION_J));
        }
    }

    @Override
    public Map<Integer, IPartida> getPartidasGuardadas(String nombre_jugador) throws RemoteException{
        return partidas_guardadas.getPartidasGuardadas(nombre_jugador);
    }
}
