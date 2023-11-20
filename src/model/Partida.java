package model;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private int cantidadJugadoresTotales;
    private List<Jugador> jugadores_en_la_partida;
    private Carta cartaAlta;
    private Carta cartaBaja;
    private Mazo mazo;

    public Partida(int cantidadJugadoresTotales){
        jugadores_en_la_partida = new ArrayList<>();
        this.cantidadJugadoresTotales = cantidadJugadoresTotales;
        cartaAlta = new Carta(10, EnumColor.PURPURA);
        cartaBaja = new Carta(1, EnumColor.PURPURA);
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
        this.cartaBaja = Partida.this.cartaBaja;
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
}
