package it.univaq.disim.lpo.unifymusic.exception;

public class BranoNonTrovatoException extends ElementoNonTrovatoException {

    public BranoNonTrovatoException() {
        super("Brano non trovato.");
    }

    public BranoNonTrovatoException(String message) {
        super(message);
    }

    public BranoNonTrovatoException(String message, Throwable cause) {
        super(message, cause);
    }
}