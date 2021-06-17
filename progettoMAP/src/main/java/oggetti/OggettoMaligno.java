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
public class OggettoMaligno extends Oggetto{
    
    private final int danno;
    
    public OggettoMaligno(String nome, Set<String> alias, List<Comando> listaMosse, String descrizione, boolean prendibile, int usabilita, int danno) {
        super(nome, alias, listaMosse, descrizione, prendibile, usabilita);
        this.danno = danno;
    }

    @Override
    public void usa(Giocatore giocatore, Stanza stanza) {
        if(this.usabilita > 0){
            giocatore.decrementaVita(this.danno);
        }
        else{
            System.out.println("Non puoi usare questo oggetto");
        }
    }
    
}
