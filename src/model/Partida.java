package model;

import model.enums.EnumColor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Partida {
    private int cantidadJugadoresTotales;
    private List<Jugador> jugadores_en_la_partida;
    private Carta cartaAlta;
    private Carta cartaBaja;
    private Mazo mazo;
    private Queue<Jugador> turnos;
    private Jugador turnoActual;
    private boolean repartidas;
    private boolean finalizado;
    private boolean enJuego;

    public Partida(int cantidadJugadoresTotales){
        jugadores_en_la_partida = new ArrayList<>();
        this.cantidadJugadoresTotales = cantidadJugadoresTotales;
        cartaAlta = new Carta(10, EnumColor.PURPURA);
        cartaBaja = new Carta(1, EnumColor.PURPURA);
        turnos = new LinkedList<>();
        repartidas = false;
        finalizado = false;
        mazo = new Mazo();
    }

    public Carta getCartaAlta(){
        return cartaAlta;
    }

    public Carta getCartaBaja(){
        return cartaBaja;
    }

    public void setCartaAlta(Carta cartaAlta){
        this.cartaAlta = cartaAlta;
    }

    public void setCartaBaja(Carta cartaBaja){
        this.cartaBaja = cartaBaja;
    }

    public void agregarJugador(Jugador jugador){
        jugadores_en_la_partida.add(jugador);
    }

    public int getCantidadJugadoresEnLaPartida(){
        return jugadores_en_la_partida.size();
    }

    public List<Jugador> getJugadores_en_la_partida(){
        return jugadores_en_la_partida;
    }

    public int getCantidadJugadoresTotales(){
        return  cantidadJugadoresTotales;
    }

    public Mazo getMazo(){
        return mazo;
    }

    public void establecerColaTurnos(){
        for (Jugador jugador : jugadores_en_la_partida){
            turnos.offer(jugador);
        }
        turnoActual = getTurno();
    }
    public void avanzarTurno(){
        Jugador turno_jugador = turnos.poll();
        turnos.offer(turno_jugador);
        turnoActual = getTurno();
    }

    public Jugador getTurno(){
        return turnos.peek();
    }

    public Jugador getTurnoActual(){
        return this.turnoActual;
    }

    public void setRepartidas(boolean repartidas){
        this.repartidas = repartidas;
    }
    public boolean getRepartidas(){
        return this.repartidas;
    }

    public boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public boolean getEnJuego() {
        return enJuego;
    }

    public void setEnJuego(boolean enJuego) {
        this.enJuego = enJuego;
    }
}
