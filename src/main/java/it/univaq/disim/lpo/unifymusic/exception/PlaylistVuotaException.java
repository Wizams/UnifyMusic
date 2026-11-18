package it.univaq.disim.lpo.unifymusic.exception;

public class PlaylistVuotaException extends Exception {

    public PlaylistVuotaException() {
        super("La playlist è vuota.");
    }

    public PlaylistVuotaException(String message) {
        super(message);
    }

    public PlaylistVuotaException(String message, Throwable cause) {
        super(message, cause);
    }
}