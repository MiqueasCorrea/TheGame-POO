package view;

import model.enums.Estados;
import model.Partida;

public interface IVista {
//    public void mostrarVista(Carta cartaAlta, Carta cartaBaja);
    void empezarPartida(Partida partida);
    void mostrarInicioSesion();
    void mostrarJuego(Partida partida);
    void mostrarEsperandoJugadores(Partida nueva_partida);
    void mostrarBuscarPartidas();
    void gameOver(Partida partida);
    void gameWin(Partida partida);
    Estados getEstado();
}
