/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

import java.util.Set;
import javax.swing.JTextArea;
import tipi.Giocatore;
import tipi.stanze.Stanza;

/**
 *
 * @author mtubi
 */
public class Cibo extends Oggetto {

    private final static boolean PRENDIBILE = true;
    private final static int DURABILITA = 1;
    private final static int PUNTEGGIO = 10;
    private int rigenerazione;
    private final static TipoOggetto TIPO_OGGETTO = TipoOggetto.cibo;

    public Cibo(String nome, Set<String> alias, int rigenerazione) {
        super(nome, alias, PRENDIBILE, DURABILITA, TIPO_OGGETTO);
        this.rigenerazione = rigenerazione;
    }

    public int getRigenerazione() {
        return rigenerazione;
    }

    public void setRigenerazione(int rigenerazione) {
        this.rigenerazione = rigenerazione;
    }

    

    public static boolean isPRENDIBILE() {
        return PRENDIBILE;
    }

    public static int getDURABILITA() {
        return DURABILITA;
    }

    public static TipoOggetto getTIPO_OGGETTO() {
        return TIPO_OGGETTO;
    }

    @Override
    public void usaSwing(Giocatore giocatore, Stanza stanza, JTextArea out) {
        if (this.usabilita > 0) {
            if (giocatore.getVitaCorrente() < Giocatore.getVITA_INIZIO()) {
                giocatore.incrementaVita(this.rigenerazione);
                giocatore.incrementaPunteggio(Cibo.PUNTEGGIO);
                out.setText("Rin: 'Hai recuperato della vita'");
                this.usabilita--;
                if (this.usabilita == 0) {
                    out.setText("Rin: 'L'oggetto " + this.nome + " ha finito i suoi utilizzi, non puoi usare più questo oggetto'");
                }
            } else if (giocatore.getVitaCorrente() == Giocatore.getVITA_INIZIO()) {
                out.setText("Rin: 'Sei al pieno delle tue forze, risparmia " + this.nome + " per comabattimenti futuri");
            }
        } else {
            out.setText("Rin : 'Non puoi usare questo oggetto poichè ha finito le usabilità'");
        }
    }

    @Override
    public void descrizioneOggetto(JTextArea out) {
        out.setText("Rin: 'E' " + this.nome + " potrebbe servirci per curare le nostre ferite, hai ancora " + this.usabilita + " utlizzi");

    }

}
