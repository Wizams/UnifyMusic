package it.univaq.disim.lpo.unifymusic.model;

import java.time.LocalDate;

public class Admin extends Utente {

    public Admin(String id, String nome, String cognome, String email, LocalDate dataNascita) {
        super(id, nome, cognome, email, dataNascita);
    }

    @Override
    public boolean puoGestireCatalogo() {
        return true;
    }
}