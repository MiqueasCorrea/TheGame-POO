package model;

public class Jugador {
    private String nombre;
    private Carta carta1;
    private Carta carta2;
    private int cantidadCartasTiradas;
    public Jugador(String nombre) {
        this.nombre = nombre;
        cantidadCartasTiradas = 0;
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

    public void setCantidadCartasTiradas(int cantidadCartasTiradas){
        this.cantidadCartasTiradas = cantidadCartasTiradas;
    }

    public void incrementarCartasTiradas(){
        this.cantidadCartasTiradas += 1;
    }
    public int getCantidadCartasTiradas(){
        return this.cantidadCartasTiradas;
    }
}
