package model.clases;

import model.interfaces.IRanking;
import model.interfaces.ISesion;
import serializacion.Serializador;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Ranking implements IRanking, Serializable {
    private static IRanking instancia = null;
    private static final long serialVersionUID = 1L;
    private Serializador serializador = new Serializador("src/data/ranking.dat");
    private Map<String, Integer> ranking;

    public static IRanking getInstancia(){
        if (instancia == null){
            instancia = new Ranking();
        }
        return instancia;
    }

    private Ranking() {
        Object rankObj = serializador.readFirstObject();
//        ranking = new HashMap<>();
//        serializador.writeOneObject(ranking);
        ranking = (Map<String, Integer>) rankObj;
    }

    @Override
    public void actualizar(String nombreUsuario){
        if (ranking.containsKey(nombreUsuario)){
            int incremento = ranking.get(nombreUsuario) + 1;
            ranking.put(nombreUsuario, incremento);
        } else {
            ranking.put(nombreUsuario, 1); // Si no existe en el ranking, lo agrego con valor 1
        }
        serializador.writeOneObject(ranking);
    }

    @Override
    public Map<String, Integer> getRanking() {
        System.out.println("LLEGA AL MAP GET RANKING");
        return ranking.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue())) // ordena de mayor a menor
                .collect(java.util.stream.Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // manejo de duplicados (validacion extra)
                        java.util.LinkedHashMap::new
                ));
    }

}
