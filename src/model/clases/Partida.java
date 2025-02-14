package model.clases;

import model.enums.EnumColor;
import model.enums.EstadoPartida;
import model.extras.GeneradorPartidaID;
import model.interfaces.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Partida implements IPartida{
    private int id;
    private int cantidadJugadores;
    private List<IJugador> jugadores_en_la_partida;
    private ICarta cartaAlta;
    private ICarta cartaBaja;
    private IMazo mazo;
    private ITurno turnos;
    private boolean repartidas;
    private EstadoPartida estado;

    public Partida(int cantidadJugadores){
        id = new GeneradorPartidaID().conseguirSiguienteID();
        this.cantidadJugadores = cantidadJugadores;
        jugadores_en_la_partida = new ArrayList<>();
        cartaAlta = new Carta(10, EnumColor.PURPURA);
        cartaBaja = new Carta(1, EnumColor.PURPURA);
        turnos = new Turno(new LinkedList<>());
        estado = EstadoPartida.EN_ESPERA;
        repartidas = false;
        mazo = new Mazo();
    }

    // GETTERS
    public ICarta getCartaAlta(){
        return cartaAlta;
    }

    public ICarta getCartaBaja(){
        return cartaBaja;
    }

    public IMazo getMazo(){
        return mazo;
    }

    public boolean getRepartidas(){
        return this.repartidas;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getCantidadJugadoresEnLaPartida(){
        return jugadores_en_la_partida.size();
    }

    @Override
    public List<IJugador> getJugadoresEnLaPartida(){
        return jugadores_en_la_partida;
    }

    @Override
    public int getCantidadJugadoresTotales(){
        return  cantidadJugadores;
    }

    @Override
    public void agregarJugador(IJugador jugador){
        jugadores_en_la_partida.add(jugador);
    }

    public IJugador getJugador(int posicion){
        return jugadores_en_la_partida.get(posicion);
    }


    // SETTERS
    public void setCartaAlta(ICarta cartaAlta){
        this.cartaAlta = cartaAlta;
    }

    public void setCartaBaja(ICarta cartaBaja){
        this.cartaBaja = cartaBaja;
    }

    public void setRepartidas(boolean repartidas){
        this.repartidas = repartidas;
    }

}
