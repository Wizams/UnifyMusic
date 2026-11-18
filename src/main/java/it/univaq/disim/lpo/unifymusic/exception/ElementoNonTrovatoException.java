package it.univaq.disim.lpo.unifymusic.exception;

public class ElementoNonTrovatoException extends Exception {

    public ElementoNonTrovatoException() {
        super("Elemento non trovato.");
    }

    public ElementoNonTrovatoException(String message) {
        super(message);
    }

    public ElementoNonTrovatoException(String message, Throwable cause) {
        super(message, cause);
    }
}