package model.interfaces;

import model.enums.EstadoJugador;

public interface IJugador {
    // GETTERS
    public String getNombre();

    ICarta getSegundaCartaDelJugador();

    ICarta getPrimeraCartaDelJugador();

    int getCantidadCartasTiradas();

    boolean isPrimeraCarta_en_mano();

    boolean isSegundaCarta_en_mano();

    // SETTERS
    void setPrimeraCartaDelJugador(ICarta carta1);

    void setSegundaCartaDelJugador(ICarta carta2);

    void setPrimeraCarta_en_mano(boolean carta1_en_mano);

    void setSegundaCarta_en_mano(boolean carta2_en_mano);

    void setCantidadCartasTiradas(int cantidad);

    void incrementarCartasTiradas();

    EstadoJugador getEstadoJugador();

    void setEstadoJugador(EstadoJugador estadoJugador);
}
