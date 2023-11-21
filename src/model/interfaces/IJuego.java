package model.interfaces;

import model.Jugador;
import model.Partida;

import java.util.List;

public interface IJuego {
    Jugador conectarUsuario(String nombre, IObserver observador);
    List<Jugador> getUsuarios();
    List<Partida> getPartidas();
    void notificarObservers(Partida partida, Object arg);
    void agregarPartida(Partida partida_nueva);

}
