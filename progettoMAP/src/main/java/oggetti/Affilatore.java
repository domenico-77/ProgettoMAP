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
import tipi.Stanza;

/**
 *
 * @author mtubi
 */
public class Affilatore extends Oggetto{
    private final static int USABILITA = 1;
    private final static int USABILITA_SPADA = 3;
    private final static boolean PRENDIBILE = true;
    
    public Affilatore(String nome, Set<String> alias, List<Comando> listaMosse) {
        super(nome, alias, listaMosse, PRENDIBILE, USABILITA);
    }

    @Override
    public void usa(Giocatore giocatore, Stanza stanza) {
        if(this.usabilita > 0){
            Inventario inventario = giocatore.getInventario();
            Oggetto oggetto = new Spada("Spada", null, null);
            if(inventario.contieneOggetto(oggetto)){
                List<Oggetto> l = inventario.getInventario();
                int i = l.indexOf(oggetto);
                if(l.get(i).getUsabilita() == USABILITA_SPADA){
                    System.out.println("Rin: 'L'affilatura di questa spada è al massimo non puoi affilarla");
                }
                else{
                    l.get(i).setUsabilita(l.get(i).usabilita+1);
                    this.usabilita--;
                    System.out.println("Rin: 'Ora che abbiamo affilato la spada, possiamo buttare l'affilatore'");
                }
            }
        }
        else{
            System.out.println("Non puoi usare questo oggetto");
        }
    }

    @Override
    public void descrizioneOggetto() {
        System.out.println("Rin: 'E' un affilatore per lame, puo' servire nel caso in cui la lama di una spada perde usabiita, puo' essre usata una sola volta'");
    }
    
}
