package controller;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import model.clases.ManejadorEventos;
import model.clases.Modelo;
import model.clases.Jugador;
import model.clases.Partida;
import model.enums.Estados;
import model.enums.Eventos;
import model.excepciones.JugadorExistente;
import model.excepciones.JugadorNoExistente;
import model.interfaces.*;
import view.interfaces.IVista;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class Controller implements IControladorRemoto {
    private IModelo modelo;
    private IVista vista;
    private int id_partida_actual;
    private String nombre_jugador;

    public Controller(){}

    public void setVista(IVista vista) {
        this.vista = vista;
    }

    public int getId_partida_actual(){
        return id_partida_actual;
    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modelo){
        this.modelo = (IModelo) modelo;
    }

    // GESTION PARTIDAS
    public List<IPartida> getPartidas() throws RemoteException {
        return this.modelo.getPartidas();
    }

    public void crearPartida(int cantidadJugadores) throws RemoteException {
        IPartida partida= modelo.crearPartida(cantidadJugadores);
        setId_partida_actual(partida.getId());
    }

    public void agregarJugadorAPartida(int id_partida) throws RemoteException{
        vista.setEstado(Estados.EN_ESPERANDO_JUGADORES);
        setId_partida_actual(id_partida);
        modelo.agregarJugadorAPartida(id_partida, nombre_jugador);
    }

    public void agregarJugadorAPartida() throws RemoteException{
        vista.setEstado(Estados.EN_ESPERANDO_JUGADORES);
        modelo.agregarJugadorAPartida(id_partida_actual, nombre_jugador);
    }

    public void setId_partida_actual(int id) {
        this.id_partida_actual = id;
    }

    public IPartida getPartidaActual() throws RemoteException{
        IPartida partida = modelo.getPartida(id_partida_actual);
        return partida;
    }

    public void empezarPartida() throws RemoteException{
        vista.setEstado(Estados.EN_JUEGO);
        modelo.empezarPartida(id_partida_actual);
    }

    public IJugador getTurno() throws RemoteException{
        return modelo.getTurno(id_partida_actual);
    }

    public void jugarTurno(int zonasMano, int zonasCentro) throws RemoteException {
        modelo.jugarTurno(id_partida_actual, zonasMano, zonasCentro);
    }

    public IMazo getMazo() throws RemoteException{
        return modelo.getMazo(id_partida_actual);
    }

    public void siguienteTurno() throws RemoteException {
        modelo.siguienteTurno(id_partida_actual);
    }

    public boolean verificarGameOver() throws RemoteException {
        return modelo.gameOver(id_partida_actual);
    }

    public boolean verificarGameWin() throws RemoteException {
        return modelo.gameWin(id_partida_actual);
    }

    public void desconectarJugador() throws RemoteException {
        modelo.desconectarJugador(nombre_jugador, id_partida_actual);
        setId_partida_actual(-1);
    }

    public void cerrar(boolean in_game) throws RemoteException{
        if (in_game){
            desconectarJugador();
        }
        modelo.cerrar(this);
    }

    public void reconectarJugador(int id_partida) throws RemoteException {
        setId_partida_actual(id_partida);
        modelo.reconectarJugador(nombre_jugador, id_partida);
    }

    // GESTION USUARIOS-OBSERVADORES
    public void registrarUsuario(String nombre, String password) throws RemoteException, JugadorExistente {
        modelo.registrarUsuario(nombre, password);
        this.nombre_jugador = nombre;
    }

    public void iniciarSesion(String nombre, String password) throws RemoteException, JugadorNoExistente {
        modelo.iniciarSesion(nombre, password);
        this.nombre_jugador = nombre;
    }

    public String getNombreJugador(){
        return nombre_jugador;
    }

    public void actualizarRanking(String nombre) throws RemoteException {
        modelo.actualizarRanking(nombre);
    }

    public Map<String, Integer> getRanking() throws RemoteException {
        return modelo.getRanking();
    }

    public Map<Integer, IPartida> getPartidasGuardadas(String nombre_jugador) throws RemoteException{
        return modelo.getPartidasGuardadas(nombre_jugador);
    }

    @Override
    public void actualizar(IObservableRemoto iObservableRemoto, Object o) throws RemoteException {
        if (o instanceof ManejadorEventos evento) {

            switch (evento.getEvento()) {
                case CAMBIO_ESPERANDO_JUGADORES, DESCONEXION_E, RECONEXION_E -> {
                    if (id_partida_actual != evento.getId()){return;}
                    if (vista.getEstado() == Estados.EN_ESPERANDO_JUGADORES) {
                        vista.esperandoJugadores();
                    }
                }
                case CAMBIO_BUSCAR_PARTIDA -> {
                    if (vista.getEstado() == Estados.EN_BUSCAR_PARTIDA) {
                        vista.buscarPartidas();
                    }
                }
                case ACTUALIZACION_CARTA, CAMBIO_TURNO, DESCONEXION_J, RECONEXION_J -> {
                    if (id_partida_actual != evento.getId()){return;}
                    if (vista.getEstado() == Estados.EN_JUEGO) {
                        vista.mostrarPartida();
                    }
                }
                case GAME_OVER -> {
                    if (id_partida_actual != evento.getId()){return;}
                    if (vista.getEstado() == Estados.EN_JUEGO) {
                        vista.mostrarGameOver();
                    }
                }
                case GAME_WIN -> {
                    if (id_partida_actual != evento.getId()){return;}
                    if (vista.getEstado() == Estados.EN_JUEGO) {
                        vista.mostrarGameWin();
                    }
                }
                default -> {}
            }
        }
    }
}
