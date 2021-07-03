/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import tipi.Comando;
import tipi.Giocatore;
import tipi.stanze.Stanza;

/**
 *
 * @author mtubi
 */
public class Spada extends Oggetto implements Serializable {
    
    private final static int USABILITA = 3;
    private final static boolean PRENDIBILE = true;
    private final static TipoOggetto TIPO_OGGETTO = TipoOggetto.spada;
    
    public Spada(String nome, Set<String> alias) {
        super(nome, alias, PRENDIBILE, USABILITA,TIPO_OGGETTO);
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

    public static int getUSABILITA() {
        return USABILITA;
    }

    public static boolean isPRENDIBILE() {
        return PRENDIBILE;
    }

    public static TipoOggetto getTIPO_OGGETTO() {
        return TIPO_OGGETTO;
    }
    
    
    
}
