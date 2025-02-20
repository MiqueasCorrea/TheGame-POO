package model.excepciones;

public class JugadorNoExistente extends RuntimeException {
    public JugadorNoExistente(String message) {
        super(message);
    }
}
