package model.clases;

import model.extras.GeneradorUsuarioID;
import model.interfaces.ICarta;
import model.interfaces.IJugador;

import java.io.Serializable;

public class Jugador implements IJugador, Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private ICarta carta1;
    private ICarta carta2;
    private int cantidadCartasTiradas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        id = new GeneradorUsuarioID().conseguirSiguienteID();
        cantidadCartasTiradas = 0;
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

    public int getId(){
        return id;
    }

    public int getCantidadCartasTiradas() {
        return cantidadCartasTiradas;
    }

    // SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrimeraCartaDelJugador(ICarta carta1){
        this.carta1 = carta1;
    }

    public void setSegundaCartaDelJugador(ICarta carta2){
        this.carta2 = carta2;
    }
}
