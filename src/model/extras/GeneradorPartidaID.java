package model.extras;

import serializacion.Serializador;

import java.io.Serializable;

public class GeneradorPartidaID implements IGeneradorID, Serializable {
    private static final long serialVersionUID = 1L;
    private static Serializador serializador = new Serializador("src/data/ultimo_id_partida.dat");
    int ID = 0;

    public GeneradorPartidaID(){
        Object id = serializador.readFirstObject();
        if (id != null){
            System.out.println("ULTIMO ID PARTIDA: " + (int) id);
            ID = (int) id;
        }
        else{
            System.out.println("NO EXISTE NINGUN ID, ID PARTIDA:  " + ID + " AGREGADO");
            guardarID();
        }
    }

    @Override
    public int conseguirSiguienteID(){
        ID++;
        guardarID();
        return ID;
    }

    @Override
    public void guardarID(){
        serializador.writeOneObject(ID);
        System.out.println("ID DE PARTIDA: " + (int) serializador.readFirstObject());
    }
}
