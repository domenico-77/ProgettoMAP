/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

import java.util.Set;
import javax.swing.JTextArea;
import tipi.Giocatore;
import tipi.stanze.TipoPorta;
import tipi.stanze.Porta;
import tipi.stanze.Stanza;

/**
 *
 * @author mtubi
 */
public class ChiavePorta extends Oggetto {

    private final static boolean PRENDIBILE = true;
    private final static int DURABILITA = 1;
    private final static int PUNTEGGIO = 35;
    private final TipoPorta materiale;
    private final static TipoOggetto TIPO_OGGETTO1 = TipoOggetto.chiave;
    private final static TipoOggetto TIPO_OGGETTO2 = TipoOggetto.totem;

    public ChiavePorta(String nome, Set<String> alias, TipoPorta materiale, TipoOggetto tipo) {
        super(nome, alias, PRENDIBILE, DURABILITA, tipo);
        this.materiale = materiale;
    }

    

  

    public static boolean isPRENDIBILE() {
        return PRENDIBILE;
    }

    public static int getDURABILITA() {
        return DURABILITA;
    }

    public static TipoOggetto getTIPO_OGGETTO1() {
        return TIPO_OGGETTO1;
    }

    public static TipoOggetto getTIPO_OGGETTO2() {
        return TIPO_OGGETTO2;
    }

    @Override
    public void usaSwing(Giocatore giocatore, Stanza stanza, JTextArea out) {
        Porta porta;
        if (stanza.getPortaNord() != null) {
            porta = stanza.getPortaNord();
            if (porta.getTipo() == this.materiale) {
                giocatore.incrementaPunteggio(ChiavePorta.PUNTEGGIO);
                porta.setChiusa(false);
                out.append("Rin: 'hai aperto la porta " + porta.getTipo() + "'\n");
                porta.getStanza().getPortaSud().setChiusa(false);
            }
        }
        if (stanza.getPortaSud() != null) {
            porta = stanza.getPortaSud();
            if (porta.getTipo() == this.materiale) {
                porta.setChiusa(false);
                giocatore.incrementaPunteggio(ChiavePorta.PUNTEGGIO);
                out.append("Rin: 'hai aperto la porta " + porta.getTipo() + "'\n");

                porta.getStanza().getPortaNord().setChiusa(false);
            }
        }

        if (stanza.getPortaOvest() != null) {
            porta = stanza.getPortaOvest();
            if (porta.getTipo() == this.materiale) {
                porta.setChiusa(false);
                giocatore.incrementaPunteggio(ChiavePorta.PUNTEGGIO);
                out.append("Rin: 'hai aperto la porta " + porta.getTipo() + "'\n");

                porta.getStanza().getPortaEst().setChiusa(false);
            }
        }
        if (stanza.getPortaEst() != null) {
            porta = stanza.getPortaEst();
            if (porta.getTipo() == this.materiale) {
                porta.setChiusa(false);
                giocatore.incrementaPunteggio(ChiavePorta.PUNTEGGIO);
                out.append("Rin : 'hai aperto la porta " + porta.getTipo() + "'\n");

                porta.getStanza().getPortaOvest().setChiusa(false);
            }
        }
    }

    @Override
    public void descrizioneOggetto(JTextArea out) {
        out.append("Rin: ' E'  ");
        switch (this.materiale) {
            case oro:
                out.append("un totem");
                break;
            case argento:
                out.append("una chiave");
                break;
        }

        out.append(", potrebbe servirici per aprire delle porte'\n");
    }

}
