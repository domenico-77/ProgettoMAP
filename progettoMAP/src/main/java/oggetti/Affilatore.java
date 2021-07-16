/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

import java.util.List;
import java.util.Set;
import javax.swing.JTextArea;
import giocatore.Giocatore;
import giocatore.Inventario;
import stanze.Stanza;

/**
 *
 * @author mtubi
 */
public class Affilatore extends Oggetto {

    private final static int USABILITA = 1;
    private final static int USABILITA_SPADA = 3;
    private final static boolean PRENDIBILE = true;
    private final static int PUNTEGGIO = 10;
    private final static TipoOggetto TIPO_OGGETTO = TipoOggetto.affilatore;

    public Affilatore(String nome, Set<String> alias) {
        super(nome, alias, PRENDIBILE, USABILITA, TIPO_OGGETTO);
    }

 

   

    public static int getUSABILITA() {
        return USABILITA;
    }

    public static int getUSABILITA_SPADA() {
        return USABILITA_SPADA;
    }

    public static boolean isPRENDIBILE() {
        return PRENDIBILE;
    }

    public static TipoOggetto getTIPO_OGGETTO() {
        return TIPO_OGGETTO;
    }

    @Override
    public void usaSwing(Giocatore giocatore, Stanza stanza, JTextArea out) {
        if (this.usabilita > 0) {
            Inventario inventario = giocatore.getInventario();
            Oggetto oggetto = new Spada("Spada", null);
            if (inventario.contieneOggetto(oggetto)) {
                List<Oggetto> l = inventario.getInventario();
                int i = l.indexOf(oggetto);
                if (l.get(i).getUsabilita() == USABILITA_SPADA) {
                    out.append("Rin: 'L'affilatura di questa spada è al massimo non puoi affilarla'\n");
                } else {
                    l.get(i).setUsabilita(l.get(i).usabilita + 1);
                    this.usabilita--;
                    giocatore.incrementaPunteggio(Affilatore.PUNTEGGIO);
                    out.append("Rin: 'Ora che abbiamo affilato la spada, possiamo buttare l'affilatore'\n");
                }
            } else {
                out.append("Rin: 'Non abbiamo una spada da affilare, dobbiamo prima trovarla' \n");
            }
        } else {
            out.append("Non puoi usare questo oggetto");
        }
    }

    @Override
    public void descrizioneOggetto(JTextArea out) {
        out.setText("Rin: 'E' un affilatore per lame, puo' servire nel caso in cui la lama di una spada perde usabiita, puo' essre usata una sola volta'");
    }

}
