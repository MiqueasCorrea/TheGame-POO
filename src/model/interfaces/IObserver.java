package model.interfaces;

import model.Partida;

public interface IObserver {
    void update(Partida partida, Object arg);
}
