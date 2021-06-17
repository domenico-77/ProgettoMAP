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
import tipi.Materiale;
import tipi.Porta;
import tipi.Stanza;

/**
 *
 * @author mtubi
 */
public class ChiavePorta extends Oggetto{
    private Materiale materiale;

    public ChiavePorta(String nome, Set<String> alias, List<Comando> listaMosse, String descrizione, boolean prendibile, Materiale materiale, int usabilita) {
        super(nome, alias, listaMosse, descrizione, prendibile, usabilita);
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
    
    
}
