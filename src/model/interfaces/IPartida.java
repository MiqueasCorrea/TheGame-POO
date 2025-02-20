package model.interfaces;

import model.enums.EstadoPartida;
import model.enums.Estados;

import java.util.List;

public interface IPartida {
    // GETTERS
    List<IJugador> getJugadoresEnLaPartida();

    ICarta getCartaAlta();

    ICarta getCartaBaja();

    IMazo getMazo();

    int getCantidadJugadoresEnLaPartida();

    int getCantidadJugadoresTotales();

    int getPosicionJugador(String jugador);

    int getId();

    IJugador getJugador(int posicion);

    IJugador getTurno();

    EstadoPartida getEstado();

    // SETTERS
    void setEstado(EstadoPartida estado);

    // METODOS
    void agregarJugador(String jugador);

    void establecerTurnos();

    void repartirCartas();

    boolean jugarTurno(int zonasMano, int zonasCentro);

    void siguienteTurno();

    boolean gameOver();

    boolean gameWin();
}
