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
public class Cibo extends Oggetto{
    private int rigenerazione;
    private int usabilita;

    public Cibo(String nome, Set<String> alias, List<Comando> listaMosse, String descrizione, boolean prendibile, int rigenerazione, int usabilita) {
        super(nome, alias, listaMosse, descrizione, prendibile, usabilita);
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
        if(this.usabilita > 0){
            giocatore.incrementaVita(this.rigenerazione);
            this.usabilita --;
        }
        else{
            System.out.println("Non puoi usare questo oggetto");
        }
    }
    
    
}
