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
import tipi.stanze.Stanza;

/**
 *
 * @author mtubi
 */
public class OggettoMaligno extends Oggetto{
    private final static boolean PRENDIBILE = false;
    private final static int DURABILITA = 1;
    private final int danno;
    private final static TipoOggetto TIPO_OGGETTO = TipoOggetto.oggettoMaligno;
    
    public OggettoMaligno(String nome, Set<String> alias, int danno) {
        super(nome, alias, PRENDIBILE, DURABILITA, TIPO_OGGETTO);
        this.danno = danno;
    }

  
    

    @Override
    public void usa(Giocatore giocatore, Stanza stanza) {
        if(this.usabilita > 0){
            giocatore.decrementaVita(this.danno);
            System.out.println("Rin: 'Oh no un veleno ti ha inferto dei danni'");
        }
        else{
            System.out.println("Non puoi usare questo oggetto");
        }
    }

    @Override
    public void descrizioneOggetto() {
        System.out.println("Rin: 'Potrebbe essere pericoloso, meglio stare alla larga'");
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
