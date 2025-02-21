package model.interfaces;

import model.enums.Eventos;

public interface IManejadorEventos {
    boolean esIgual(int id);

    Eventos getEvento();

    int getId();
}
