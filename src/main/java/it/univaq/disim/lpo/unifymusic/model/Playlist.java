package it.univaq.disim.lpo.unifymusic.model;

import it.univaq.disim.lpo.unifymusic.exception.BranoNonTrovatoException;
import it.univaq.disim.lpo.unifymusic.exception.DuplicateException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist implements Riproducibile {

    private static int contatore = 0;
    private String id;
    private String nome;
    private String descrizione;
    private LocalDate dataCreazione;
    private List<Brano> brani;

    // Costruttore vuoto
    public Playlist() {
        this("", "");
    }

    // Costruttore con parametri
    public Playlist(String nome, String descrizione) {

        contatore = contatore + 1;

        this.id = "playlist-" + System.currentTimeMillis()
                + "-" + contatore;

        this.nome = nome;
        this.descrizione = descrizione;
        this.dataCreazione = LocalDate.now();
        this.brani = new ArrayList<>();
    }

    // Cambia il nome della playlist
    public void rinomina(String nuovoNome) {
        this.nome = nuovoNome;
    }

    // Aggiunge un brano alla playlist
    public void aggiungiBrano(Brano brano)
            throws DuplicateException {

        if (brano == null) {
            throw new IllegalArgumentException(
                    "Il brano non puo essere null");
        }

        if (brani.contains(brano)) {
            throw new DuplicateException(
                    "Brano gia presente");
        }

        brani.add(brano);
    }

    // Rimuove un brano dalla playlist
    public void rimuoviBrano(Brano brano)
            throws BranoNonTrovatoException {

        if (!brani.contains(brano)) {
            throw new BranoNonTrovatoException(
                    "Brano non trovato");
        }

        brani.remove(brano);
    }

    // Sposta un brano da una posizione a un'altra
    public void spostaBrano(int daIndice, int aIndice) {

        if (daIndice < 0 || daIndice >= brani.size()) {
            throw new IndexOutOfBoundsException(
                    "Indice di partenza non valido");
        }

        if (aIndice < 0 || aIndice >= brani.size()) {
            throw new IndexOutOfBoundsException(
                    "Indice di destinazione non valido");
        }

        Brano brano = brani.remove(daIndice);

        brani.add(aIndice, brano);
    }

    // Restituisce una copia ordinata dei brani
    public List<Brano> ordina(CriterioOrdinamento criterio) {

        List<Brano> copia = new ArrayList<>(brani);

        Collections.sort(copia, criterio.getComparator());

        return Collections.unmodifiableList(copia);
    }

    // Restituisce i brani ordinati
    public List<Brano> getBraniOrdinati( CriterioOrdinamento criterio) {

        return ordina(criterio);
    }

    // Calcola la durata della playlist in secondi
    public int getDurata() {

        int totale = 0;

        for (Brano brano : brani) {
            totale = totale + brano.getDurataSecondi();
        }

        return totale;
    }

    // Restituisce la durata totale
    public int getDurataTotale() {
        return getDurata();
    }

    // Getter e setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    @Override
    public List<Brano> getBrani() {
        return Collections.unmodifiableList(brani);
    }

    // Sostituisce tutti i brani della playlist
    public void setBrani(List<Brano> nuoviBrani)
            throws DuplicateException {

        List<Brano> copia = new ArrayList<>();

        for (Brano brano : nuoviBrani) {

            if (brano == null) {
                throw new IllegalArgumentException(
                        "Il brano non puo essere null");
            }

            if (copia.contains(brano)) {
                throw new DuplicateException(
                        "Brano gia presente");
            }

            copia.add(brano);
        }

        brani.clear();
        brani.addAll(copia);
    }

    // Confronta due playlist tramite il loro id
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Playlist altra = (Playlist) obj;

        if (this.id == null) {
            return altra.id == null;
        }

        return this.id.equals(altra.id);
    }

    @Override
    public int hashCode() {

        if (id == null) {
            return 0;
        }

        return id.hashCode();
    }
}
