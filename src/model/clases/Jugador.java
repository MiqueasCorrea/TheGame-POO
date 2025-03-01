package model.clases;

import model.enums.EstadoJugador;
import model.interfaces.ICarta;
import model.interfaces.IJugador;

import java.io.Serializable;

public class Jugador implements IJugador, Serializable{
    private static final long serialVersionUID = 1L;
    private String nombre;
    private ICarta carta1;
    private ICarta carta2;
    private boolean carta1_en_mano;
    private boolean carta2_en_mano;
    private int cantidadCartasTiradas;
    private EstadoJugador estadoJugador;

    public Jugador(String nombre) {
        this.nombre = nombre;
        cantidadCartasTiradas = 0;
        carta1_en_mano = false;
        carta2_en_mano = false;
    }

    // GETTERS
    public String getNombre(){
        return nombre;
    }

    public ICarta getPrimeraCartaDelJugador(){
        return carta1;
    }

    public ICarta getSegundaCartaDelJugador(){
        return carta2;
    }

    @Override
    public boolean isPrimeraCarta_en_mano() {
        return carta1_en_mano;
    }

    @Override
    public boolean isSegundaCarta_en_mano() {
        return carta2_en_mano;
    }

    public int getCantidadCartasTiradas() {
        return cantidadCartasTiradas;
    }

    // SETTERS
    @Override
    public void setPrimeraCartaDelJugador(ICarta carta1){
        this.carta1 = carta1;
    }

    @Override
    public void setSegundaCartaDelJugador(ICarta carta2){
        this.carta2 = carta2;
    }

    @Override
    public void setPrimeraCarta_en_mano(boolean carta1_en_mano) {
        this.carta1_en_mano = carta1_en_mano;
    }

    @Override
    public void setSegundaCarta_en_mano(boolean carta2_en_mano) {
        this.carta2_en_mano = carta2_en_mano;
    }

    @Override
    public void incrementarCartasTiradas() {
        cantidadCartasTiradas++;
    }

    @Override
    public void setCantidadCartasTiradas(int cantidad) {
        this.cantidadCartasTiradas = cantidad;
    }
    
    @Override
    public EstadoJugador getEstadoJugador() {
        return estadoJugador;
    }

    @Override
    public void setEstadoJugador(EstadoJugador estadoJugador) {
        this.estadoJugador = estadoJugador;
    }
}
