package controller.interfaces;

import model.clases.Partida;
import model.interfaces.IPartida;

public interface IObserver {
    void update(int id_partida, Object arg);
}
