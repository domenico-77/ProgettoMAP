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

    @Override
    public void usa(Giocatore giocatore, Stanza stanza) {
        Porta porta;
        if (stanza.getPortaNord() != null) {
            porta = stanza.getPortaNord();
            if (porta.getTipo() == this.materiale) {
                giocatore.incrementaPunteggio(ChiavePorta.PUNTEGGIO);
                porta.setChiusa(false);
                System.out.println("Rin: 'hai aperto la porta " + porta.getTipo() + "'");
                porta.getStanza().getPortaSud().setChiusa(false);
            }
        }
        if (stanza.getPortaSud() != null) {
            porta = stanza.getPortaSud();
            if (porta.getTipo() == this.materiale) {
                porta.setChiusa(false);
                giocatore.incrementaPunteggio(ChiavePorta.PUNTEGGIO);
                System.out.println("Rin: 'hai aperto la porta " + porta.getTipo() + "'");

                porta.getStanza().getPortaNord().setChiusa(false);
            }
        }

        if (stanza.getPortaOvest() != null) {
            porta = stanza.getPortaOvest();
            if (porta.getTipo() == this.materiale) {
                porta.setChiusa(false);
                giocatore.incrementaPunteggio(ChiavePorta.PUNTEGGIO);
                System.out.println("Rin: 'hai aperto la porta " + porta.getTipo() + "'");

                porta.getStanza().getPortaEst().setChiusa(false);
            }
        }
        if (stanza.getPortaEst() != null) {
            porta = stanza.getPortaEst();
            if (porta.getTipo() == this.materiale) {
                porta.setChiusa(false);
                giocatore.incrementaPunteggio(ChiavePorta.PUNTEGGIO);
                System.out.println("Rin : 'hai aperto la porta " + porta.getTipo() + "'");

                porta.getStanza().getPortaOvest().setChiusa(false);
            }
        }
    }

    @Override
    public void descrizioneOggetto() {
        System.out.print("Rin: ' E' una chiave ");
        switch (this.materiale) {
            case oro:
                System.out.print("d'oro");
                break;
            case argento:
                System.out.print("d'argento");
                break;
        }

        System.out.println(", potrebbe servirici per aprire delle porte'");
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
                out.setText("Rin: 'hai aperto la porta " + porta.getTipo() + "'");
                porta.getStanza().getPortaSud().setChiusa(false);
            }
        }
        if (stanza.getPortaSud() != null) {
            porta = stanza.getPortaSud();
            if (porta.getTipo() == this.materiale) {
                porta.setChiusa(false);
                giocatore.incrementaPunteggio(ChiavePorta.PUNTEGGIO);
                out.setText("Rin: 'hai aperto la porta " + porta.getTipo() + "'");

                porta.getStanza().getPortaNord().setChiusa(false);
            }
        }

        if (stanza.getPortaOvest() != null) {
            porta = stanza.getPortaOvest();
            if (porta.getTipo() == this.materiale) {
                porta.setChiusa(false);
                giocatore.incrementaPunteggio(ChiavePorta.PUNTEGGIO);
                out.setText("Rin: 'hai aperto la porta " + porta.getTipo() + "'");

                porta.getStanza().getPortaEst().setChiusa(false);
            }
        }
        if (stanza.getPortaEst() != null) {
            porta = stanza.getPortaEst();
            if (porta.getTipo() == this.materiale) {
                porta.setChiusa(false);
                giocatore.incrementaPunteggio(ChiavePorta.PUNTEGGIO);
                out.setText("Rin : 'hai aperto la porta " + porta.getTipo() + "'");

                porta.getStanza().getPortaOvest().setChiusa(false);
            }
        }
    }

    @Override
    public void descrizioneOggetto(JTextArea out) {
        out.setText("Rin: ' E' una chiave ");
        switch (this.materiale) {
            case oro:
                out.append("d'oro");
                break;
            case argento:
                out.append("d'argento");
                break;
        }

        out.append(", potrebbe servirici per aprire delle porte'");
    }

}
