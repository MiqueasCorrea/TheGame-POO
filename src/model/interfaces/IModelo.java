package model.interfaces;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import model.excepciones.JugadorExistente;
import model.excepciones.JugadorNoExistente;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface IModelo extends IObservableRemoto {
    // GESTION PARTIDAS
    IPartida crearPartida(int cantidadJugadores) throws RemoteException;

    List<IPartida> getPartidas() throws RemoteException;

    IPartida getPartida(int id) throws RemoteException;

    void agregarJugadorAPartida(int id_partida, String jugador) throws RemoteException;

    void empezarPartida(int id_partida_actual) throws RemoteException;

    IJugador getTurno(int id_partida_actual) throws RemoteException;

    void jugarTurno(int id_partida_actual, int zonasMano, int zonasCentro) throws RemoteException;

    IMazo getMazo(int id_partida) throws RemoteException;

    // GESTION USUARIOS-OBSERVADORES
    void siguienteTurno(int id_partida) throws RemoteException;

    boolean gameOver(int id_partida) throws RemoteException;

    boolean gameWin(int id_partida) throws RemoteException;

    void actualizarRanking(String nombre) throws RemoteException;

    Map<String, Integer> getRanking() throws RemoteException;

    void registrarUsuario(String nombre, String password) throws JugadorExistente, RemoteException;

    void iniciarSesion(String nombre, String password) throws JugadorNoExistente, RemoteException;
    void desconectarJugador(String nombre_jugador, int id_partida) throws RemoteException;

    void cerrar(IObservadorRemoto observador) throws RemoteException;

    void reconectarJugador(String nombre_jugador, int id_partida) throws RemoteException;

    Map<Integer, IPartida> getPartidasGuardadas(String nombre_jugador) throws RemoteException;
}
