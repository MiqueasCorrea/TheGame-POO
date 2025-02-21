package model.clases;

import model.excepciones.JugadorExistente;
import model.excepciones.JugadorNoExistente;
import model.excepciones.PasswordIncorrecta;
import model.interfaces.ISesion;
import serializacion.Serializador;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sesion implements ISesion, Serializable {
    private static ISesion instancia = null;
    private static final long serialVersionUID = 1L;
    private Serializador serializador = new Serializador("src/data/sesiones.dat");
    private Map<String, String> usuarios;

    public static ISesion getInstancia(){
        if (instancia == null){
            instancia = new Sesion();
        }
        return instancia;
    }

    private Sesion() {
        Object usuariosObj = serializador.readFirstObject();
//        usuarios = new HashMap<>();
//        serializador.writeOneObject(usuarios);
        usuarios = (Map<String, String>) usuariosObj;
    }

    @Override
    public void registrarse(String nombre, String password) throws JugadorExistente{
        if (existeJugador(nombre)){
            throw new JugadorExistente("Nombre de usuario en uso.");
        }
        usuarios.put(nombre, password);
        serializador.writeOneObject(usuarios);
    }

    @Override
    public void iniciarSesion(String nombre, String password) throws JugadorNoExistente, PasswordIncorrecta {
        if (!existeJugador(nombre)){
            throw new JugadorNoExistente("Usuario no registrado.");
        }

        if (!usuarios.get(nombre).equals(password)) {
            throw new PasswordIncorrecta("Password incorrecta.");
        }

    }

    public boolean existeJugador(String nombre){
        return usuarios.containsKey(nombre);
    }
}
