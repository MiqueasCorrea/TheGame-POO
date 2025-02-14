package view.interfaces;

import controller.Controller;
import model.enums.Estados;
import model.clases.Partida;

public interface IVista {
    void login();
    void menu();
    void opciones();
    void opcionesDeJuego();
    void reglas();
    void buscarPartidas();
    void crearPartida(int cantidadJugadores);
    void esperandoJugadores();
    void empezarPartida();
    Estados getEstado();
    Controller getControlador();
}
