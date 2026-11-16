package it.univaq.disim.lpo.unifymusic.model;

public interface Ricercabile {
    String getId();
    String getTestoRicerca();

    default boolean corrisponde(String query) {
        if (query == null || query.isBlank()) {
            return true;
        }
        String testo = getTestoRicerca();
        return testo != null && testo.toLowerCase().contains(query.toLowerCase().trim());
    }
}