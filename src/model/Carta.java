package model;

import model.enums.EnumColor;

public class Carta {
    private int numero;
    private EnumColor color;
    private boolean enMano;

    public Carta(int numero){
        enMano = true;
        this.numero = numero;
        this.color = null;
    }
    public Carta(int numero, EnumColor color){
        enMano = true;
        this.numero = numero;
        this.color = color;
    }

    /**
     * Este metodo de instancia devuelve el atributo encapsulado "numero".
     * @return int con el numero del atributo numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Este metodo de instancia setea el valor del atributo encapsulado "numero".
     * @param numero Un entero con el numero de la carta a setear.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Este metodo de instancia devuelve el EnumColor del atributo encapsulado "color".
     * @return EnumColor con el tipo de color del enumerado.
     */
    public EnumColor getColor() {
        return color;
    }

    /**
     * Este metodo de instancia setea el color del atributo encapsulado "color"
     * @param color con el tipo de color a setear.
     */
    public void setColor(EnumColor color) {
        this.color = color;
    }

    public void setEnMano(boolean enMano){
        this.enMano = enMano;
    }

    public boolean getEnMano(){
        return enMano;
    }
}
