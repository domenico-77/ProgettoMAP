/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npc;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextArea;
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
    private static final int PUNTEGGIO_MORTO = 30;
    private static final int PUNTEGGIO_ACCONTENTATO = 60;
    private static final String[] ALIAS_SCAMBIO = {"mercante","prigioniero","contrabbandiere"};

    private boolean accontentato;
    private final Oggetto oggettoRichiesto;

    public PngScambio(String nome, Oggetto oggetto, Oggetto oggettoRichiesto) {
        super(nome, oggetto, NEUTRALE);
        this.oggettoRichiesto = oggettoRichiesto;
        this.accontentato = PngScambio.ACCONTENTATO;
    }

   

    @Override
    public List<String> getAliasNome() {
         List<String> aliasNome = new ArrayList();
        for(String a : Npc.alias){
            aliasNome.add(a);
        }
        
         for(String a : PngScambio.ALIAS_SCAMBIO){
            aliasNome.add(a);
        }
        if(!this.sconosciuto){
            aliasNome.add(this.nome.toLowerCase());
        }
        else{
            aliasNome.add("prigioniero");
        }
        return aliasNome;
    }

    @Override
    public void interagisci(Giocatore giocatore, JTextArea out, JFrame frame) {
        if (!this.vivo) {
            String nomeNpc;
            if (this.sconosciuto) {
                nomeNpc = "Sconosciuto";
            } else {
                nomeNpc = this.nome;
            }
            if (Utilita.chiediConfermaSwing("Rin: '" + nomeNpc + " e' morto, vuoi controllare il corpo? potrebbe avere qualcosa di utile!''", "Rin: 'Va bene, controlliamo il suo corpo'", "Rin: 'Andiamocene prima che il suo corpo inizi a puzzare!'", out, frame)) {
                if(this.oggetto != null){
                    out.append("Hai raccolto: " + this.oggetto.getNome()+"\n");
                    giocatore.getInventario().aggiungiOggetto(this.oggetto);
                    giocatore.incrementaPunteggio(PngScambio.PUNTEGGIO_MORTO);
                    this.oggetto = null;
                }
                else{
                    out.append("Rin: 'Il cadavere non ha niente di interessante, possiamo andare'\n");
                }
            }
        } else if (this.accontentato) {
            if (this.oggetto != null) {
                out.append(this.nome + ": 'Tieni questo è quello che posso darti, per aiutarti nella tua fuga'\n");
                giocatore.getInventario().aggiungiOggetto(this.oggetto);
                out.append("Hai ottenuto: " + this.oggetto.getNome() + "\n");
                giocatore.incrementaPunteggio(PngScambio.PUNTEGGIO_ACCONTENTATO);
                this.oggetto = null;
            } else {
                out.append(this.nome + ": 'Ti ho gia' dato tutto cio' che era in mio possesso, cos'altro vuoi ahahahahah'\n");
            }
        } else {
            if (this.sconosciuto) {
                out.append("Sconosciuto: 'Ciao ho sentito parlare molto di te, se mi dai una mano ricambiero' il favore'\n");
                if (Utilita.chiediConfermaSwing("Rin: 'Vuoi sentire la proposta dello sconosciuto?'", "Rin: 'Vediamo cosa hai da offrire'", "Sconosciuto: 'Nel caso cambi idea io sono sempre qui'", out, frame)) {
                    this.sconosciuto = false;
                    out.append(this.nome + ": 'Piacere il mio nome e' " + this.nome + "'\n");
                    this.interagisci(giocatore, out, frame);
                }
            } else {
                out.append(this.nome + ": 'Se mi porti " + this.oggettoRichiesto.getNome() + " in cambio ti daro' un altra cosa'\n");
                if (giocatore.getInventario().contieneOggetto(this.oggettoRichiesto)) {
                    if (Utilita.chiediConfermaSwing("Rin: 'Abbiamo questo oggetto, vuoi darglielo?'", "Rin: 'Ecco l'oggetto promesso, ora mantieni la tua promessa'", this.nome + ": 'Nel caso cambi idea io sono sempre qui'", out, frame)) {
                        giocatore.getInventario().rimuoviOggetto(this.oggettoRichiesto);
                        this.accontentato = true;
                        out.append(this.nome + ": 'Era proprio quello che mi serviva!'\n");
                        this.interagisci(giocatore, out, frame);
                    }
                } else {
                    out.append(this.nome + ": 'Quando avrai " + this.oggettoRichiesto.getNome() + " torna da me, io saro' sempre qui' \n");
                }
            }
        }
    }
}
