package model.interfaces;

import java.util.Map;

public interface IRanking {
    void actualizar(String nombre);
    Map<String, Integer> getRanking();
}
