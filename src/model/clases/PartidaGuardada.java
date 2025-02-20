package model.clases;

import model.interfaces.IJugador;
import model.interfaces.IPartida;
import model.interfaces.IPartidaGuardada;
import serializacion.Serializador;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PartidaGuardada implements IPartidaGuardada, Serializable {
    private static final long serialVersionUID = 1L;
    private Serializador serializador = new Serializador("src/data/partidas_guardadas.dat");
    private Map<Integer, IPartida> partidas_guardadas;

    public PartidaGuardada(){
        Object pg_obj = serializador.readFirstObject();
//        partidas_guardadas = new HashMap<>();
//        serializador.writeOneObject(partidas_guardadas);
        partidas_guardadas = (Map<Integer, IPartida>) pg_obj;
    }

    @Override
    public void actualizar(IPartida partida){
        partidas_guardadas.put(partida.getId(), partida);
        serializador.writeOneObject(partidas_guardadas);
    }

    @Override
    public Map<Integer, IPartida> getPartidasGuardadas(String nombre_jugador){
        Map<Integer, IPartida> partidas_guardadas_jugador = new HashMap<>();;
        for (Map.Entry<Integer, IPartida> entry: partidas_guardadas.entrySet()){
            for (IJugador jugador : entry.getValue().getJugadoresEnLaPartida()){
                if (jugador.getNombre().equals(nombre_jugador)){
                    partidas_guardadas_jugador.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return partidas_guardadas_jugador;
    }

    @Override
    public Map<Integer, IPartida> getPartidasGuardadas() {
        return partidas_guardadas;
    }

    @Override
    public void borrarPartidaGuardada(int id_partida){
        if (partidas_guardadas.containsKey(id_partida)){
            partidas_guardadas.remove(id_partida);
            serializador.writeOneObject(partidas_guardadas);
        }
    }
}
