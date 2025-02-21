package model.interfaces;

import model.excepciones.JugadorExistente;
import model.excepciones.JugadorNoExistente;
import model.excepciones.PasswordIncorrecta;

public interface ISesion {
    void registrarse(String nombre, String password) throws JugadorExistente;

    void iniciarSesion(String nombre, String password) throws JugadorNoExistente, PasswordIncorrecta;
}
