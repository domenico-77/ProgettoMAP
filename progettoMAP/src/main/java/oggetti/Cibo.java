/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

import java.util.List;
import java.util.Set;
import tipi.Comando;
import tipi.Giocatore;
import tipi.stanze.Stanza;

/**
 *
 * @author mtubi
 */
public class Cibo extends Oggetto {

    private final static boolean PRENDIBILE = true;
    private final static int DURABILITA = 1;
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

    @Override
    public void usa(Giocatore giocatore, Stanza stanza) {
        if (this.usabilita > 0) {
            if (giocatore.getVitaCorrente() != giocatore.getVitaMax()) {
                giocatore.incrementaVita(this.rigenerazione);
                this.usabilita--;
                if (this.usabilita == 0) {
                    System.out.println("Rin: 'L'oggetto " + this.nome + " ha finito i suoi utilizzi, non puoi usare più questo oggetto'");
                }
            }
            else{
                System.out.println("Rin: 'Sei al pieno delle tue forze, risparmia"+this.nome+"per comabattimenti futuri");
            }
        } else {
            System.out.println("Non puoi usare questo oggetto");
        }
    }

    @Override
    public void descrizioneOggetto() {
        System.out.println("Rin: 'E' " + this.nome + " potrebbe servirci per curare le nostre ferite, hai ancora " + this.usabilita + " utlizzi");
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

}
