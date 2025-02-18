package view.interfaces;

import controller.Controller;
import model.enums.Estados;
import model.clases.Partida;

import java.rmi.RemoteException;

public interface IVista {
    void login() throws RemoteException;
    void menu();
    void opciones();
    void opcionesDeJuego();
    void reglas();
    void buscarPartidas() throws RemoteException;
    void crearPartida(int cantidadJugadores) throws RemoteException;
    void esperandoJugadores() throws RemoteException;
    void empezarPartida() throws RemoteException;
    void mostrarPartida() throws RemoteException;
    Estados getEstado();
    Controller getControlador();
    void setEstado(Estados estado);
}
