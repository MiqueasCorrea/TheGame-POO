package model.clases;
import model.interfaces.IJugador;
import model.interfaces.ITurno;

import java.io.Serializable;
import java.util.List;
import java.util.Queue;

public class Turno implements ITurno, Serializable {
    private IJugador turnoActual;
    private Queue<IJugador> turnos;
    private int cantidadCartasTiradas;

    public Turno(Queue<IJugador> turnos){
        this.turnos = turnos;
        this.cantidadCartasTiradas = 0;
    }

    @Override
    public void establecerTurnos(List<IJugador> jugadores){
        for (IJugador jugador : jugadores){
            turnos.offer(jugador);
        }
        turnoActual = getTurno();
    }

    @Override
    public void avanzarTurno(){
        IJugador turno_jugador = turnos.poll();
        turnos.offer(turno_jugador);
        turnoActual = getTurno();
    }

    @Override
    public IJugador getTurno(){
        return turnos.peek();
    }

    @Override
    public int getCantidadCartasTiradas(){
        return this.cantidadCartasTiradas;
    }

    @Override
    public IJugador getTurnoActual(){
        return this.turnoActual;
    }

    @Override
    public void setCantidadCartasTiradas(int cantidadCartasTiradas){
        this.cantidadCartasTiradas = cantidadCartasTiradas;
    }

    @Override
    public void incrementarCartasTiradas(){
        this.cantidadCartasTiradas += 1;
    }
}
