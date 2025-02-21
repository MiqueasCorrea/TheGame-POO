package model.interfaces;

import java.util.List;

public interface ITurno {
    IJugador getTurno();

    IJugador getTurnoActual();

    int getCantidadCartasTiradas();

    void setCantidadCartasTiradas(int cantidadCartasTiradas);

    void avanzarTurno();

    void establecerTurnos(List<IJugador> jugadores);

    void incrementarCartasTiradas();
}
