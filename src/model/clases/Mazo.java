package model.clases;

import model.enums.EnumColor;
import model.interfaces.ICarta;
import model.interfaces.IMazo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo implements IMazo, Serializable {
    private List<ICarta> cartas;
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
    }

    public List<ICarta> getCartas() {
        return cartas;
    }

    @Override
    public int getCantidadCartas(){
        return cantidadCartas;
    }

    @Override
    public ICarta agarrarCartaTope(){
        ICarta carta = cartas.get(cantidadCartas);
        cantidadCartas--;
        return carta;
    }
}
