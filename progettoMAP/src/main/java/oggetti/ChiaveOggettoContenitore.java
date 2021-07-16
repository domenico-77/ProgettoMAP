/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

import java.util.List;
import java.util.Set;
import javax.swing.JTextArea;
import tipi.Giocatore;
import tipi.stanze.Stanza;

/**
 *
 * @author mtubi
 */
public class ChiaveOggettoContenitore extends Oggetto {

    private final static boolean PRENDIBILE = true;
    private final static int DURABILITA = 1;
    private final static TipoOggetto TIPO_OGGETTO = TipoOggetto.chiaveOggettoContenitore;
    private final static int PUNTEGGIO = 50;

    public ChiaveOggettoContenitore(String nome, Set<String> alias) {
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
        if (this.usabilita > 0) {
            List<Oggetto> l = stanza.getOggetiStanza();
            Oggetto contenitore = new OggettoContenitore("Contenitore", null, null);
            if (l.contains(contenitore)) {
                int i = l.indexOf(contenitore);
                OggettoContenitore contenitoreO = (OggettoContenitore) l.get(i);
                if (!contenitoreO.isAperto()) {
                    contenitoreO.setAperto(true);
                    giocatore.incrementaPunteggio(ChiaveOggettoContenitore.PUNTEGGIO);
                    this.usabilita--;
                    contenitore = contenitoreO;
                    l.set(i, contenitore);
                    stanza.setOggetiStanza(l);
                    out.append("Rin: 'Hai aperto " + contenitore.getNome() + "'\n");
                } else {
                    out.append("Rin: 'E' già aperto'\n");
                }
            } else {
                out.append("Rin: 'Non puoi usare questo oggetto in questa stanza, non è presente uno scrigno da aprire'\n");
            }
        } else {
            out.append("Non puoi usare questo oggetto");
        }
    }

    @Override
    public void descrizioneOggetto(JTextArea out) {
        out.setText("Rin: 'E' una chiave, potrebbe servirci per aprire uno scrigno, puo' essre utilizzata  per aprire un solo scrigno'\n");

    }

}
