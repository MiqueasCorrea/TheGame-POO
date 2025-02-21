package model.interfaces;

import model.enums.EstadoJugador;
import model.enums.EstadoPartida;
import model.enums.Estados;

import java.util.List;

public interface IPartida {
    int getCantidadJugadores_total_conectados();

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

    void incrementarCantidadJugadoresTotalConectados();

    // SETTERS
    void setEstado(EstadoPartida estado);

    void setEstadoJugador(String nombre, EstadoJugador estadoJugador);

    void incrementarCantidadJugadoresActuales();

    void decrementarCantidadJugadoresActuales();

    // METODOS
    void agregarJugador(String jugador);

    void establecerTurnos();

    void repartirCartas();

    boolean jugarTurno(int zonasMano, int zonasCentro);

    void siguienteTurno();

    boolean gameOver();

    boolean gameWin();
}
