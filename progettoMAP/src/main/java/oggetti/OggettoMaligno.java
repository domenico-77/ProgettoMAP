/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

import java.io.Serializable;
import java.util.Set;
import javax.swing.JTextArea;
import giocatore.Giocatore;
import stanze.Stanza;

/**
 *
 * @author mtubi
 */
public class OggettoMaligno extends Oggetto implements Serializable {

    private final static boolean PRENDIBILE = false;
    private final static int DURABILITA = 1;
    private final static int PUNTEGGIO = -100;
    private final int danno;
    private final static TipoOggetto TIPO_OGGETTO = TipoOggetto.oggettoMaligno;

    public OggettoMaligno(String nome, Set<String> alias, int danno) {
        super(nome, alias, PRENDIBILE, DURABILITA, TIPO_OGGETTO);
        this.danno = danno;
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
            giocatore.decrementaVita(this.danno);
            giocatore.incrementaPunteggio(OggettoMaligno.PUNTEGGIO);
            out.append("Rin: 'Oh no un veleno ti ha inferto dei danni'\n");
        } else {
            out.append("Non puoi usare questo oggetto\n");
        }
    }

    @Override
    public void descrizioneOggetto(JTextArea out) {
        out.setText("Rin: 'Potrebbe essere pericoloso, meglio stare alla larga'\n");

    }

}
