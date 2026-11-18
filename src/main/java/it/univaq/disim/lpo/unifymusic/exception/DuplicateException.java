package it.univaq.disim.lpo.unifymusic.exception;

public class DuplicateException extends Exception {

    public DuplicateException() {
        super("Elemento già presente.");
    }

    public DuplicateException(String message) {
        super(message);
    }

    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}