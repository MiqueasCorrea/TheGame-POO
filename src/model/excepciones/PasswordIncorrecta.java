package model.excepciones;

public class PasswordIncorrecta extends RuntimeException {
    public PasswordIncorrecta(String message) {
        super(message);
    }
}
