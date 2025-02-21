package model.interfaces;

import java.util.Map;

public interface IPartidaGuardada {
    Map<Integer, IPartida> getPartidasGuardadas(String nombre_jugador);
    Map<Integer, IPartida> getPartidasGuardadas();

    void actualizar(IPartida partida);

    void borrarPartidaGuardada(int id_partida);
}
