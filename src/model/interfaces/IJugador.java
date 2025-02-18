package model.interfaces;

public interface IJugador {
    int getId();
    public String getNombre();
    ICarta getSegundaCartaDelJugador();
    ICarta getPrimeraCartaDelJugador();
    void setPrimeraCartaDelJugador(ICarta carta1);
    void setSegundaCartaDelJugador(ICarta carta2);
    boolean isPrimeraCarta_en_mano();
    boolean isSegundaCarta_en_mano();
    void setPrimeraCarta_en_mano(boolean carta1_en_mano);
    void setSegundaCarta_en_mano(boolean carta2_en_mano);
}
