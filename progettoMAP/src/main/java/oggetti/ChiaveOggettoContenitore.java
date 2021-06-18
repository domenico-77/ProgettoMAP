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
    private final static boolean PRENDIBILE = true;
    private final static int DURABILITA = 1;
    
    public ChiaveOggettoContenitore(String nome, Set<String> alias, List<Comando> listaMosse) {
        super(nome, alias, listaMosse, PRENDIBILE, DURABILITA);
    }

    

    @Override
    public void usa(Giocatore giocatore, Stanza stanza) {
       this.usabilita --;
    }

    @Override
    public void descrizioneOggetto() {
        System.out.println("Rin: 'E' una chiave, potrebbe servirci per aprire uno scrigno'");
    }
    
}
