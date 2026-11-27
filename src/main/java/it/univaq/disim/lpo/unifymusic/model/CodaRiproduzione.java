package it.univaq.disim.lpo.unifymusic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodaRiproduzione {

    private List<Brano> brani;
    private List<Brano> ordineOriginale;
    private int indiceCorrente;
    private boolean shuffle;
    private ModalitaRepeat repeat;

    // Costruttore
    public CodaRiproduzione() {
        this.brani = new ArrayList<>();
        this.ordineOriginale = new ArrayList<>();
        this.indiceCorrente = -1;
        this.shuffle = false;
        this.repeat = ModalitaRepeat.OFF;
    }

    // Aggiunge un singolo brano
    public void aggiungi(Brano b) {

        if (b == null) {
            throw new IllegalArgumentException("Brano nullo");
        }

        this.brani.add(b);
        this.ordineOriginale.add(b);

        if (this.indiceCorrente == -1) {
            this.indiceCorrente++;
        }
    }

    // Aggiunge tutti i brani di un elemento riproducibile
    public void aggiungi(Riproducibile r) {

        if (r == null) {
            throw new IllegalArgumentException("Elemento nullo");
        }

        for (Brano b : r.getBrani()) {
            aggiungi(b);
        }
    }

    // Rimuove un brano dalla coda
    public void rimuovi(int indice) {

        if (indice < 0 || indice >= this.brani.size()) {
            throw new IndexOutOfBoundsException("Indice non valido");
        }

        Brano rimosso = this.brani.remove(indice);

        if (this.shuffle) {
            this.ordineOriginale.remove(rimosso);
        } else {
            this.ordineOriginale.remove(indice);
        }

        if (this.brani.isEmpty()) {
            this.indiceCorrente = -1;
        } else if (indice < this.indiceCorrente) {
            this.indiceCorrente--;
        } else if (this.indiceCorrente >= this.brani.size()) {
            this.indiceCorrente = this.brani.size() - 1;
        }
    }

    // Svuota la coda
    public void svuota() {
        this.brani.clear();
        this.ordineOriginale.clear();
        this.indiceCorrente = -1;
    }

    // Restituisce il brano corrente
    public Brano getCorrente() {

        if (this.indiceCorrente == -1) {
            return null;
        }

        return this.brani.get(this.indiceCorrente);
    }

    // Passa al brano successivo
    public Brano prossimo() {

        if (this.brani.isEmpty()) {
            return null;
        }

        if (this.repeat == ModalitaRepeat.BRANO) {
            return getCorrente();
        }

        if (this.indiceCorrente < this.brani.size() - 1) {
            this.indiceCorrente++;
            return getCorrente();
        }

        if (this.repeat == ModalitaRepeat.LISTA) {
            this.indiceCorrente = 0;
            return getCorrente();
        }

        return null;
    }

    // Passa al brano precedente
    public Brano precedente() {

        if (this.brani.isEmpty()) {
            return null;
        }

        if (this.repeat == ModalitaRepeat.BRANO) {
            return getCorrente();
        }

        if (this.indiceCorrente > 0) {
            this.indiceCorrente--;
            return getCorrente();
        }

        if (this.repeat == ModalitaRepeat.LISTA) {
            this.indiceCorrente = this.brani.size() - 1;
            return getCorrente();
        }

        return null;
    }

    // Attiva o disattiva lo shuffle
    public void setShuffle(boolean attivo) {

        if (this.shuffle == attivo) {
            return;
        }

        this.shuffle = attivo;

        if (this.brani.isEmpty()) {
            return;
        }

        Brano corrente = getCorrente();

        if (this.shuffle) {
            this.brani.remove(this.indiceCorrente);
            Collections.shuffle(this.brani);
            this.brani.add(0, corrente);
            this.indiceCorrente = 0;
        } else {
            this.brani.clear();
            this.brani.addAll(this.ordineOriginale);
            this.indiceCorrente = this.brani.indexOf(corrente);
        }
    }

    // Imposta la modalita di ripetizione
    public void setRepeat(ModalitaRepeat mode) {

        if (mode == null) {
            throw new IllegalArgumentException("Modalita non valida");
        }

        this.repeat = mode;
    }

    // Getter
    public List<Brano> getBrani() {
        return Collections.unmodifiableList(this.brani);
    }

    public List<Brano> getOrdineOriginale() {
        return Collections.unmodifiableList(this.ordineOriginale);
    }

    public int getIndiceCorrente() {
        return this.indiceCorrente;
    }

    public boolean isShuffle() {
        return this.shuffle;
    }

    public ModalitaRepeat getRepeat() {
        return this.repeat;
    }

    // Setter della lista dei brani
    public void setBrani(List<Brano> nuoviBrani) {

    if (nuoviBrani == null) {
        throw new IllegalArgumentException("Lista nulla");
    }

    for (Brano b : nuoviBrani) {
        if (b == null) {
            throw new IllegalArgumentException("Brano nullo");
        }
    }

    svuota();
    this.shuffle = false;

    for (Brano b : nuoviBrani) {
        aggiungi(b);
    }
    
    }

    // Setter dell'indice corrente
    public void setIndiceCorrente(int indice) {

        if (this.brani.isEmpty() && indice == -1) {
            this.indiceCorrente = -1;
            return;
        }

        if (indice < 0 || indice >= this.brani.size()) {
            throw new IndexOutOfBoundsException("Indice non valido");
        }

        this.indiceCorrente = indice;
    }
}
