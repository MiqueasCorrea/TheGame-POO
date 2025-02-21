package model.clases;

import model.enums.EnumColor;
import model.enums.EstadoJugador;
import model.enums.EstadoPartida;
import model.extras.GeneradorPartidaID;
import model.interfaces.*;

import java.io.Serializable;
import java.util.*;
import java.util.function.BooleanSupplier;

public class Partida implements IPartida, Serializable {
    private int id;
    private int cantidadJugadores;
    private int cantidadJugadores_total_conectados;
    private int cantidadJugadores_actuales;
    private List<IJugador> jugadores_en_la_partida;
    private ICarta cartaAlta;
    private ICarta cartaBaja;
    private IMazo mazo;
    private ITurno turnos;
    private EstadoPartida estado;

    public Partida(int cantidadJugadores){
        id = new GeneradorPartidaID().conseguirSiguienteID();
        this.cantidadJugadores = cantidadJugadores;
        jugadores_en_la_partida = new ArrayList<>();
        cantidadJugadores_actuales = 0;
        cantidadJugadores_total_conectados = 0;
        cartaAlta = new Carta(10, EnumColor.PURPURA);
        cartaBaja = new Carta(1, EnumColor.PURPURA);
        turnos = new Turno(new LinkedList<>());
        estado = EstadoPartida.EN_ESPERA;
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getCantidadJugadoresEnLaPartida(){
        return cantidadJugadores_actuales;
    }

    @Override
    public int getCantidadJugadores_total_conectados() {
        return cantidadJugadores_total_conectados;
    }

    @Override
    public List<IJugador> getJugadoresEnLaPartida(){
        return jugadores_en_la_partida;
    }

    @Override
    public int getCantidadJugadoresTotales(){
        return  cantidadJugadores;
    }

    public IJugador getJugador(int posicion){
        return jugadores_en_la_partida.get(posicion);
    }

    @Override
    public IJugador getTurno(){
        return turnos.getTurno();
    }
    
    @Override
    public EstadoPartida getEstado(){
        return estado;
    }

    @Override
    public int getPosicionJugador(String jugador){
        int i = 0;
        for (IJugador jug : jugadores_en_la_partida){
            if (jug.getNombre().equals(jugador)){
                return i;
            }
            i++;
        }
        return -1;
    }


    // SETTERS
    public void setCartaAlta(ICarta cartaAlta){
        this.cartaAlta = cartaAlta;
    }

    public void setCartaBaja(ICarta cartaBaja){
        this.cartaBaja = cartaBaja;
    }

    @Override
    public void incrementarCantidadJugadoresTotalConectados() {
        this.cantidadJugadores_total_conectados ++;
    }

    @Override
    public void setEstado(EstadoPartida estado){
        this.estado = estado;
    }

    @Override
    public void setEstadoJugador(String nombre, EstadoJugador estadoJugador){
        for (IJugador jugador : jugadores_en_la_partida){
            if (jugador.getNombre().equals(nombre)){
                jugador.setEstadoJugador(estadoJugador);
            }
        }
    }

    @Override
    public void incrementarCantidadJugadoresActuales(){
        this.cantidadJugadores_actuales++;
    }

    @Override
    public void decrementarCantidadJugadoresActuales(){
        this.cantidadJugadores_actuales--;
    }

    // METODOS
    @Override
    public void agregarJugador(String jugador){
        IJugador nuevo_jugador = new Jugador(jugador);
        nuevo_jugador.setEstadoJugador(EstadoJugador.CONECTADO);
        jugadores_en_la_partida.add(nuevo_jugador);
        incrementarCantidadJugadoresActuales();
        incrementarCantidadJugadoresTotalConectados();
    }

    @Override
    public void establecerTurnos(){
        turnos.establecerTurnos(jugadores_en_la_partida);
    }

    // METODOS
    @Override
    public void repartirCartas() {
        for (IJugador jugador : jugadores_en_la_partida){
            ICarta carta1 = mazo.agarrarCartaTope();
            ICarta carta2 = mazo.agarrarCartaTope();

            jugador.setPrimeraCartaDelJugador(carta1);
            jugador.setSegundaCartaDelJugador(carta2);

            jugador.setPrimeraCarta_en_mano(true);
            jugador.setSegundaCarta_en_mano(true);
        }
    }

    @Override
    public boolean jugarTurno(int zonasMano, int zonasCentro) {
        Map<Integer, BooleanSupplier> accionesMano = Map.of(
                0, () -> jugarPrimerCarta(zonasCentro),
                1, () -> jugarSegundaCarta(zonasCentro)
        );

        BooleanSupplier accion = accionesMano.get(zonasMano);
        if (accion != null) {
            return accion.getAsBoolean();
        } else {
            throw new IllegalArgumentException("Zona de carta no válida.");
        }
    }

    public boolean jugarPrimerCarta(int zonasCentro) {
        if (zonasCentro == 0) {
            if (verificarMovimiento(getTurno().getPrimeraCartaDelJugador(), cartaAlta)){
                setCartaAlta(getTurno().getPrimeraCartaDelJugador());
                getTurno().setPrimeraCarta_en_mano(false);
                getTurno().incrementarCartasTiradas();
                return true;
            }
            return false;
        } else if (zonasCentro == 1) {
            if (verificarMovimiento(getTurno().getPrimeraCartaDelJugador(), cartaBaja)){
                setCartaBaja(getTurno().getPrimeraCartaDelJugador());
                getTurno().setPrimeraCarta_en_mano(false);
                getTurno().incrementarCartasTiradas();
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("Zona donde tirar la carta inválida");
    }

    public boolean jugarSegundaCarta(int zonasCentro) {
        if (zonasCentro == 0) {
            if (verificarMovimiento(getTurno().getSegundaCartaDelJugador(), cartaAlta)){
                setCartaAlta(getTurno().getSegundaCartaDelJugador());
                getTurno().setSegundaCarta_en_mano(false);
                getTurno().incrementarCartasTiradas();
                return true;
            }
            return false;
        } else if (zonasCentro == 1) {
            if (verificarMovimiento(getTurno().getSegundaCartaDelJugador(), cartaBaja)){
                setCartaBaja(getTurno().getSegundaCartaDelJugador());
                getTurno().setSegundaCarta_en_mano(false);
                getTurno().incrementarCartasTiradas();
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("Zona donde tirar la carta inválida");
    }

    public boolean verificarMovimiento(ICarta carta_a_tirar, ICarta zonaCarta){
        if ((carta_a_tirar.getNumero() <= cartaAlta.getNumero())
            &&
            (carta_a_tirar.getNumero() >= cartaBaja.getNumero())
            ||
            (carta_a_tirar.getColor() == zonaCarta.getColor())
            ){
            return true;
        }
        return false;
    }

    @Override
    public void siguienteTurno(){
        turnos.avanzarTurno();
    }

    @Override
    public boolean gameOver(){
        IJugador jugador = getTurno();
        return !verificarMovimiento(jugador.getPrimeraCartaDelJugador(), cartaAlta)
                &&
                !verificarMovimiento(jugador.getPrimeraCartaDelJugador(), cartaBaja)
                &&
                !verificarMovimiento(jugador.getSegundaCartaDelJugador(), cartaAlta)
                &&
                !verificarMovimiento(jugador.getSegundaCartaDelJugador(), cartaBaja);
    }

    @Override
    public boolean gameWin(){
        return mazo.getCantidadCartas() <= 0;
    }
}
