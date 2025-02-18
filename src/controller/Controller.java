package controller;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import model.clases.ManejadorEventos;
import model.clases.Modelo;
import model.clases.Jugador;
import model.clases.Partida;
import model.enums.Estados;
import model.enums.Eventos;
import model.interfaces.ICarta;
import model.interfaces.IJugador;
import model.interfaces.IModelo;
import model.interfaces.IPartida;
import view.interfaces.IVista;

import java.rmi.RemoteException;
import java.util.List;

public class Controller implements IControladorRemoto {
    private IModelo modelo;
    private IJugador jugador;
    private IVista vista;
    private int id_partida_actual;

    public Controller(){}

    public IModelo getModelo(){
        return this.modelo;
    }

    public void setVista(IVista vista) {
        this.vista = vista;
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
        modelo.agregarJugadorAPartida(id_partida, jugador);
    }

    public void agregarJugadorAPartida() throws RemoteException{
        vista.setEstado(Estados.EN_ESPERANDO_JUGADORES);
        modelo.agregarJugadorAPartida(id_partida_actual, jugador);
    }

    public void setId_partida_actual(int id) {
        this.id_partida_actual = id;
    }

    public IPartida getPartidaActual() throws RemoteException{
        return modelo.getPartida(id_partida_actual);
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

    // GESTION USUARIOS-OBSERVADORES
    public void conectarJugador(String nombre) throws RemoteException{
        this.jugador = this.modelo.conectarUsuario(nombre);
    }

    public IJugador getJugador(){
        return jugador;
    }

    public String getNombreJugador(){
        return jugador.getNombre();
    }

    public int getIdJugador(){
        return jugador.getId();
    }

    @Override
    public void actualizar(IObservableRemoto iObservableRemoto, Object o) throws RemoteException {
        if (o instanceof ManejadorEventos evento) {

            switch (evento.getEvento()) {
                case CAMBIO_ESPERANDO_JUGADORES -> {
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
                case ACTUALIZACION_CARTA -> {
                    if (id_partida_actual != evento.getId()){return;}
                    if (vista.getEstado() == Estados.EN_JUEGO) {
                        vista.mostrarPartida();
                    }
                }
                default -> {}
            }
        }
    }
}
