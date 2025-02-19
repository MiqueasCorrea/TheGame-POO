package model.clases;

import model.interfaces.IRanking;
import serializacion.Serializador;

import java.io.Serializable;

public class Ranking implements IRanking, Serializable {
    private static final long serialVersionUID = 1L;
    private Serializador serializador = new Serializador("src/data/ranking.dat");


}
