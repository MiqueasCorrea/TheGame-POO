package model.clases;

import model.enums.EnumColor;
import model.enums.EstadoPartida;
import model.extras.GeneradorPartidaID;
import model.interfaces.*;

import java.io.Serializable;
import java.util.*;

public class Partida implements IPartida, Serializable {
    private int id;
    private int cantidadJugadores;
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

    public int getPosicionJugador(IJugador jugador){
        int i = 0;
        for (IJugador jug : jugadores_en_la_partida){
            if (jug.getId() == jugador.getId()){
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
    public void setEstado(EstadoPartida estado){
        this.estado = estado;
    }

    // METODOS
    @Override
    public void agregarJugador(IJugador jugador){
        jugadores_en_la_partida.add(jugador);
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
    public void jugarTurno(int zonasMano, int zonasCentro){
        Map<Integer, Runnable> accionesMano = Map.of(
                0, () -> jugarPrimerCarta(zonasCentro),
                1, () -> jugarSegundaCarta(zonasCentro)
        );

        Runnable accion = accionesMano.get(zonasMano);
        if (accion != null) {
            accion.run();
        } else {
            throw new IllegalArgumentException("Zona de carta no valida.");
        }

    }

    public void jugarPrimerCarta(int zonasCentro){
        if (zonasCentro == 0){
            setCartaAlta(getTurno().getPrimeraCartaDelJugador());
            getTurno().setPrimeraCarta_en_mano(false);
        } else if (zonasCentro == 1) {
            setCartaBaja(getTurno().getPrimeraCartaDelJugador());
            getTurno().setPrimeraCarta_en_mano(false);
        } else{
            throw new IllegalArgumentException("Zona donde tirar la carta invalida");
        }
    }

    public void jugarSegundaCarta(int zonasCentro){
        if (zonasCentro == 0){
            setCartaAlta(getTurno().getSegundaCartaDelJugador());
            getTurno().setSegundaCarta_en_mano(false);
        } else if (zonasCentro == 1) {
            setCartaBaja(getTurno().getSegundaCartaDelJugador());
            getTurno().setSegundaCarta_en_mano(false);
        } else{
            throw new IllegalArgumentException("Zona donde tirar la carta invalida");
        }
    }
}
