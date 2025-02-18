package model.clases;

import model.enums.Eventos;
import model.interfaces.IManejadorEventos;

import java.io.Serializable;

public class ManejadorEventos implements IManejadorEventos, Serializable {
    private final int id;
    private final Eventos evento;

    public ManejadorEventos(int id, Eventos evento) {
        this.id = id;
        this.evento = evento;
    }

    @Override
    public boolean esIgual(int id){
        return this.id == id;
    }

    @Override
    public Eventos getEvento(){
        return evento;
    }

    @Override
    public int getId(){
        return id;
    }

}
