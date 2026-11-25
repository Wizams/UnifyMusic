package it.univaq.disim.lpo.unifymusic.model;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Utente {

    private String id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;

    protected Utente(String id, String nome, String cognome, String email, LocalDate dataNascita) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("L'id utente non può essere nullo o vuoto.");
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome non può essere nullo o vuoto.");
        }
        if (cognome == null || cognome.isBlank()) {
            throw new IllegalArgumentException("Il cognome non può essere nullo o vuoto.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("L'email non può essere nulla o vuota.");
        }
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataNascita = dataNascita;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getNomeCompleto() {
        return nome + " " + cognome;
    }

    public abstract boolean puoGestireCatalogo();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utente)) return false;
        Utente other = (Utente) o;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getNomeCompleto() + " (" + email + ")";
    }
}