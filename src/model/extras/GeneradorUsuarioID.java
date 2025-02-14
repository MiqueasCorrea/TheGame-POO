package model.extras;

import serializacion.Serializador;

import java.io.Serializable;

public class GeneradorUsuarioID implements IGeneradorID, Serializable {
    private static final long serialVersionUID = 1L;
    private static Serializador serializador = new Serializador("src/data/ultimo_id_usuario.dat");
    int ID = 0;

    public GeneradorUsuarioID(){
        Object id = serializador.readFirstObject();
        if (id != null){
            System.out.println("ULTIMO ID: " + (int) id);
            ID = (int) id;
        }
        else{
            System.out.println("NO EXISTE NINGUN ID, ID:  " + ID + " AGREGADO");
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
        System.out.println("ID DEL USUARIO: " + (int) serializador.readFirstObject());
    }
}
