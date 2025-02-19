package model.interfaces;

import model.enums.EstadoPartida;
import model.enums.Estados;

import java.util.List;

public interface IPartida {
    List<IJugador> getJugadoresEnLaPartida();
    void agregarJugador(IJugador jugador);
    ICarta getCartaAlta();
    ICarta getCartaBaja();
    IMazo getMazo();
    int getCantidadJugadoresEnLaPartida();
    int getCantidadJugadoresTotales();
    int getPosicionJugador(IJugador jugador);
    int getId();
    EstadoPartida getEstado();
    void setEstado(EstadoPartida estado);
    void establecerTurnos();
    void repartirCartas();
    IJugador getJugador(int posicion);
    IJugador getTurno();
    boolean jugarTurno(int zonasMano, int zonasCentro);
    void siguienteTurno();
    boolean gameOver();
    boolean gameWin();
}
