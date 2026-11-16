package it.univaq.disim.lpo.unifymusic.model;

public enum GenereMusicale {
    ROCK("Rock"),
    POP("Pop"),
    RAP("Rap"),
    TRAP("Trap"),
    JAZZ("Jazz"),
    CLASSICA("Classica"),
    HIP_HOP("Hip Hop"),
    ELETTRONICA("Elettronica"),
    METAL("Metal"),
    BLUES("Blues"),
    COUNTRY("Country"),
    ALTRO("Altro");

    private final String etichetta;

    GenereMusicale(String etichetta) {
        this.etichetta = etichetta;
    }

    public String getEtichetta() {
        return etichetta;
    }
}
