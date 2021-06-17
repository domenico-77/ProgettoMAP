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
import tipi.Stanza;

/**
 *
 * @author mtubi
 */
public class ChiaveOggettoContenitore extends Oggetto {

    public ChiaveOggettoContenitore(String nome, Set<String> alias, List<Comando> listaMosse, String descrizione, boolean prendibile) {
        super(nome, alias, listaMosse, descrizione, prendibile);
    }

    @Override
    public void usa(Giocatore giocatore, Stanza stanza) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
