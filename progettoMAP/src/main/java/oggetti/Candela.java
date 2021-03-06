/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

import java.util.Set;
import javax.swing.JTextArea;
import giocatore.Giocatore;
import stanze.Stanza;

/**
 *
 * @author mtubi
 */
public class Candela extends Oggetto {

    private final static boolean PRENDIBILE = true;
    private final static int DURABILITA = 3;
    private final static TipoOggetto TIPO_OGGETTO = TipoOggetto.candela;
    private final static int PUNTEGGIO = 20;

    public Candela(String nome, Set<String> alias) {
        super(nome, alias, PRENDIBILE, DURABILITA, TIPO_OGGETTO);
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
        if (!stanza.isIlluminata()) {
            //fare il controllo se la candela è nell inventario
            stanza.setIlluminata(true);
            giocatore.incrementaPunteggio(Candela.PUNTEGGIO);
            this.usabilita--;
            out.append("Rin: 'Hai illuminato la stanza'\n");
            if (this.usabilita == 0) {
                out.append("Rin: 'L'oggetto " + this.nome + "ha finito i suoi utilizzi, non puoi usare più questo oggetto'\n");
            }
        } else {
            out.append("Rin: 'La stanza è già illuminata non serve accendere una candela'\n");
        }
    }

    @Override
    public void descrizioneOggetto(JTextArea out) {
        out.setText("Rin: 'E' una candela, potrebbe servirci per illuminare luoghi buii, ha ancora " + this.usabilita + " di utilizzi");

    }

}
