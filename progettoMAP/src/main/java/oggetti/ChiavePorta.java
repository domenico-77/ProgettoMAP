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
import tipi.Inventario;
import tipi.stanze.TipoPorta;
import tipi.stanze.Porta;
import tipi.stanze.Stanza;

/**
 *
 * @author mtubi
 */
public class ChiavePorta extends Oggetto{
    private final static boolean PRENDIBILE = true;
    private final static int DURABILITA = -1;
    private final TipoPorta materiale;
    private final static TipoOggetto TIPO_OGGETTO = TipoOggetto.chiavePorta;

    public ChiavePorta(String nome, Set<String> alias, List<Comando> listaMosse, TipoPorta materiale) {
        super(nome, alias, listaMosse, PRENDIBILE, DURABILITA, TIPO_OGGETTO);
        this.materiale = materiale;
    }
    
    @Override
    public void usa(Giocatore giocatore, Stanza stanza) {
        Porta porta = stanza.getPortaNord();
        if(porta.getTipo() == this.materiale){
            porta.setChiusa(false);
            porta.getStanza().getPortaSud().setChiusa(false);
        }
        
        porta = stanza.getPortaSud();
        if (porta.getTipo() == this.materiale){
            porta.setChiusa(false);
            porta.getStanza().getPortaNord().setChiusa(false);
        }
        
        porta = stanza.getPortaOvest();
        if(porta.getTipo() == this.materiale){
            porta.setChiusa(false);
            porta.getStanza().getPortaEst().setChiusa(false);
        }
        
        porta = stanza.getPortaEst();
        if(porta.getTipo() == this.materiale){
            porta.setChiusa(false);
            porta.getStanza().getPortaOvest().setChiusa(false);
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

    public static TipoOggetto getTIPO_OGGETTO() {
        return TIPO_OGGETTO;
    }
    
    
    
}
