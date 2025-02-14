package model.interfaces;

public interface IJugador {
    ICarta getSegundaCartaDelJugador();
    int getId();
    public String getNombre();
    ICarta getPrimeraCartaDelJugador();
    void setPrimeraCartaDelJugador(ICarta carta1);
    void setSegundaCartaDelJugador(ICarta carta2);
}
