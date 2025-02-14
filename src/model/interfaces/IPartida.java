package model.interfaces;

import java.util.List;

public interface IPartida {
    List<IJugador> getJugadoresEnLaPartida();
    void agregarJugador(IJugador jugador);
    int getCantidadJugadoresEnLaPartida();
    int getCantidadJugadoresTotales();
    int getId();
    IJugador getJugador(int posicion);
}
