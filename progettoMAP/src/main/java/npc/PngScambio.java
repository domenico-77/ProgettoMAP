/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npc;

import oggetti.Oggetto;
import tipi.Giocatore;
import tipi.Utilita;

/**
 *
 * @author mtubi
 */
public class PngScambio extends Npc {

    private static final boolean NEUTRALE = true;
    private static final boolean ACCONTENTATO = false;

    private boolean accontentato;
    private final Oggetto oggettoRichiesto;

    public PngScambio(String nome, Oggetto oggetto, Oggetto oggettoRichiesto) {
        super(nome, oggetto, NEUTRALE);
        this.oggettoRichiesto = oggettoRichiesto;
        this.accontentato = PngScambio.ACCONTENTATO;
    }

    @Override
    public void interagisci(Giocatore giocatore) {
        if (!this.vivo) {
            String nomeNpc;
            if (this.sconosciuto) {
                nomeNpc = "Sconosciuto";
            } else {
                nomeNpc = this.nome;
            }
            if (Utilita.chiediConferma("Rin: '" + nomeNpc + " e' morto, vuoi controllare il corpo? potrebbe avere qualcosa di utile!''", "Rin: 'Va bene, controlliamo il suo corpo'", "Rin: 'Andiamocene prima che il suo corpo inizi a puzzare!'")) {
                if(this.oggetto != null){
                    System.out.println("Hai raccolto: " + this.oggetto.getNome());
                    giocatore.getInventario().aggiungiOggetto(this.oggetto);
                    this.oggetto = null;
                }
                else{
                    System.out.println("Rin: 'Il cadavere non ha niente di interessante, possiamo andare'");
                }
            }
        } else if (this.accontentato) {
            if (this.oggetto != null) {
                System.out.println(this.nome + ": 'Tieni questo è quello che posso darti, per aiutarti nella tua fuga'");
                giocatore.getInventario().aggiungiOggetto(this.oggetto);
                System.out.println("Hai ottenuto: " + this.oggetto.getNome());
                this.oggetto = null;
            } else {
                System.out.println(this.nome + ": 'Ti ho gia' dato tutto cio' che era in mio possesso, cos'altro vuoi ahahahahah'");
            }
        } else {
            if (this.sconosciuto) {
                System.out.println("Sconosciuto: 'Ciao ho sentito parlare molto di te, se mi dai una mano ricambiero' il favore'");
                if (Utilita.chiediConferma("Rin: 'Vuoi sentire la proposta dello sconosciuto?'", "Rin: 'Vediamo cosa hai da offrire'", "Sconosciuto: 'Nel caso cambi idea io sono sempre qui'")) {
                    this.sconosciuto = false;
                    System.out.println(this.nome + ": 'Piacere il mio nome e' " + this.nome + "'");
                    this.interagisci(giocatore);
                }
            } else {
                System.out.println(this.nome + ": 'Se mi porti " + this.oggettoRichiesto.getNome() + " in cambio ti daro' un altra cosa'");
                if (giocatore.getInventario().contieneOggetto(this.oggettoRichiesto)) {
                    if (Utilita.chiediConferma("Rin: 'Abbiamo questo oggetto, vuoi darglielo?'", "Rin: 'Ecco l'oggetto promesso, ora mantieni la tua promessa'", this.nome + ": 'Nel caso cambi idea io sono sempre qui'")) {
                        giocatore.getInventario().rimuoviOggetto(this.oggettoRichiesto);
                        this.accontentato = true;
                        System.out.println(this.nome + ": 'Era proprio quello che mi serviva!'");
                        this.interagisci(giocatore);
                    }
                } else {
                    System.out.println(this.nome + ": 'Quando avrai " + this.oggettoRichiesto + " torna da me, io saro' sempre qui");
                }
            }
        }
    }
}
