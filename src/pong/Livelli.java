// Pong — Programmazione Orientata agli Oggetti e Laboratori, Udine, a.a. 2026-27
// Consegna 3: i livelli. Scadenza: domenica 8 novembre 2026.
package pong;

/**
 * Il gioco a livelli contro il giocatore automatico: a che livello si è e quante vite restano.
 * Si vince un livello vincendo una partita; perdendo una partita si perde una vita.
 * La Partita gli dice com'è finita ogni partita; il giocatore automatico e la velocità della palla
 * dipendono dal livello.
 *
 * IL PEZZO MANCANTE: i corpi dei metodi qui sotto (e i campi che vi servono).
 * Ogni metodo ha il suo contratto, nel commento sopra. I test ufficiali (ConsegnaTreTest) lo controllano.
 */
public class Livelli {

    private int livello;
    private int numeroLivelli;
    private int vite;
    private int viteIniziali;
    private Lato chi;
    private boolean completato;

    /**
     * Un gioco nuovo.
     * Pretende: niente.
     * Promette: livello 1, con tutte le vite; il gioco non è completato né perso.
     * Lancia IllegalArgumentException se numeroLivelli o vite è minore di 1, o se umano è null.
     */
    public Livelli(int numeroLivelli, int vite, Lato umano) {
        if (numeroLivelli < 1 || vite < 1 || umano == null) {
            throw new IllegalArgumentException("Argomenti passati non validi, numeroLivelli ≥ 1, vite ≥ 1, umano ≠ null");
        }
        this.livello = 1;
        this.numeroLivelli = numeroLivelli;
        this.vite = vite;
        this.viteIniziali = vite;
        this.chi = umano;
    }

    /** Il livello a cui si gioca, fra 1 e getNumeroLivelli(). */
    public int getLivello() {
        return livello; 
    }

    /** Quanti livelli ha il gioco. */
    public int getNumeroLivelli() {
        return numeroLivelli; 
    }

    /** Le vite che restano, fra 0 e quelle iniziali. */
    public int getVite() {
        return vite; 
    }

    /** Il lato su cui gioca la persona (l'altro è il giocatore automatico). */
    public Lato getUmano() {
        return chi; 
    }

    /** Vero se è stato vinto l'ultimo livello. */
    public boolean isCompletato() {
        return completato; 
    }

    /** Vero se le vite sono finite. */
    public boolean isPerso() {
        return vite == 0; 
    }

    /** Vero se il gioco è completato o perso. */
    public boolean isFinito() {
        return isCompletato() || isPerso();
    }

    /**
     * Registra com'è finita una partita.
     * Pretende: niente.
     * Promette: se il gioco è finito (completato o perso), non cambia niente. Altrimenti:
     *           se ha vinto umano, si passa al livello dopo; se era l'ultimo, il gioco è completato
     *           e il livello resta l'ultimo; le vite non cambiano.
     *           Se ha vinto l'altro lato, una vita in meno e il livello non cambia;
     *           con l'ultima vita il gioco è perso.
     * Lancia IllegalArgumentException se vincitore è null, e non cambia niente.
     */
    public void partitaFinita(Lato vincitore) {
        if (vincitore == null) {
            throw new IllegalArgumentException("Argomento non valido");
        } else if (isFinito()) {
            return;
        } else if (vincitore == chi) {
            if (getNumeroLivelli() == livello) {
                completato = true;
            } else {
                livello++;
            }
        } else {
            vite--;
        }
    }

    /**
     * Si ricomincia da capo.
     * Pretende: niente.
     * Promette: livello 1, con tutte le vite; il gioco non è completato né perso.
     */
    public void ricomincia() {
        vite = viteIniziali;
        livello = 1;
        completato = false;
    }
}
