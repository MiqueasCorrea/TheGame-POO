package model;

import model.enums.EnumColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private List<Carta> cartas;
    private int cantidadCartas;

    /**
     * Constructor de la clase Mazo, instancia en el atributo encapsulado "cartas" un ArrayList y luego por cada
     * color que haya en el EnumColor, le agrega a cartas una nueva Carta con el indice y el color actual del EnumColor.
     * Luego se mezclan con Collections.shuffle para darle orden aleatorio al mazo.
     */
    public Mazo(){
        cantidadCartas = 48;
        cartas = new ArrayList<>();
        for (EnumColor color : EnumColor.values()){
            if (color != EnumColor.PURPURA){
                for (int i = 1; i <= 10; i++){
                    cartas.add(new Carta(i, color));
                }
            }
        }
        Collections.shuffle(cartas);
//        for (Carta carta : cartas){
//            System.out.println(carta.getNumero() + " " + carta.getColor());
//        }
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public int getCantidadCartas(){
        return cantidadCartas;
    }

    public Carta agarrarCartaTope(){
        Carta carta = cartas.get(cantidadCartas);
        cantidadCartas--;
        return carta;
    }
}
