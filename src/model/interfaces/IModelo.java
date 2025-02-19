package model.interfaces;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import controller.interfaces.IObserver;

import java.rmi.RemoteException;
import java.util.List;

public interface IModelo extends IObservableRemoto {
    // GESTION PARTIDAS
    IPartida crearPartida(int cantidadJugadores) throws RemoteException;

    List<IPartida> getPartidas() throws RemoteException;

    IPartida getPartida(int id) throws RemoteException;

    void agregarJugadorAPartida(int id_partida, IJugador jugador) throws RemoteException;

    void empezarPartida(int id_partida_actual) throws RemoteException;

    IJugador getTurno(int id_partida_actual) throws RemoteException;

    void jugarTurno(int id_partida_actual, int zonasMano, int zonasCentro) throws RemoteException;

    IMazo getMazo(int id_partida) throws RemoteException;
    // GESTION USUARIOS-OBSERVADORES
    IJugador conectarUsuario(String nombre) throws RemoteException;

    List<IJugador> getUsuarios() throws RemoteException;
    void siguienteTurno(int id_partida) throws RemoteException;
    void gameOver(int id_partida) throws RemoteException;
}
