package view;

import model.Carta;
import model.Estados;
import model.Partida;

public interface IVista {
//    public void mostrarVista(Carta cartaAlta, Carta cartaBaja);
    void empezarPartida(Partida partida);
    void mostrarInicioSesion();
    void mostrarJuego(Partida partida);
    void mostrarEsperandoJugadores(Partida nueva_partida);
    void mostrarBuscarPartidas();
    Estados getEstado();
}
