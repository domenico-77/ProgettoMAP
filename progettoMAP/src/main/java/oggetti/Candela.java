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
public class Candela extends Oggetto {
    private final static boolean PRENDIBILE = true;
    private final static int DURABILITA = 3;

    public Candela(String nome, Set<String> alias, List<Comando> listaMosse) {
        super(nome, alias, listaMosse, PRENDIBILE, DURABILITA);
    }

    @Override
    public void usa(Giocatore giocatore, Stanza stanza) {
        if(!stanza.isIlluminata()){
            stanza.setIlluminata(true);
            this.usabilita--;
        }
        else{
            System.out.println("Rin: 'La stanza è già illuminata non serve accendere una candela'");
        }
    }

    @Override
    public void descrizioneOggetto() {
        System.out.println("Rin: 'E' una candela, potrebbe servirci per illuminare luoghi buii'");
    }
    
}
