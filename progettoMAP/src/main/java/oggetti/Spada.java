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
public class Spada extends Oggetto {
    
    private final static int USABILITA = 3;
    private final static boolean PRENDIBILE = true;

    public Spada(String nome, Set<String> alias, List<Comando> listaMosse) {
        super(nome, alias, listaMosse, PRENDIBILE, USABILITA);
    }

    @Override
    public void usa(Giocatore giocatore, Stanza stanza) {
        if(usabilita > 0){
            //usa
            this.usabilita --;
            if(this.usabilita == 0){
                System.out.println("Rin: 'Non hai affilato bene la spada, ora si è rotta, speriamo di trovarne un altra");
            }
        }
        else{
            System.out.println("Non puoi usare questo oggetto");
        }
    }

    @Override
    public void descrizioneOggetto() {
        System.out.print("Rin: 'E' "+ this.nome+"ci servira' nel caso in cui incontriamo dei soldati, puoi usarla per "+ this.usabilita+"volte, ");
        if(this.usabilita < 3){
            System.out.println("sarebbe il caso di troavre un affilatore, per aumentare l'uabilita prima che si rompa'");
        }
        else{
            System.out.println("'");
        }
    }
    
}
