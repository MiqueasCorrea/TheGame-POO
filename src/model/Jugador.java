package model;

public class Jugador {
    private String nombre;
    Carta carta1;
    Carta carta2;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Carta getPrimeraCartaDelJugador(){
        return carta1;
    }

    public void setPrimeraCartaDelJugador(Carta carta1){
        this.carta1 = carta1;
    }

    public Carta getSegundaCartaDelJugador(){
        return carta2;
    }

    public void setSegundaCartaDelJugador(Carta carta2){
        this.carta2 = carta2;
    }
}
