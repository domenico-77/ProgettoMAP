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
    private final static TipoOggetto TIPO_OGGETTO = TipoOggetto.chiaveOggettoContenitore;
    public ChiaveOggettoContenitore(String nome, Set<String> alias, List<Comando> listaMosse) {
        super(nome, alias, listaMosse, PRENDIBILE, DURABILITA, TIPO_OGGETTO);
    }

    

    @Override
    public void usa(Giocatore giocatore, Stanza stanza) {
        if(this.usabilita > 0){
            List<Oggetto> l = stanza.getOggetiStanza();
            Oggetto contenitore = new OggettoContenitore("Contenitore", null, null, null);
            if(l.contains(contenitore)){
                int i = l.indexOf(contenitore);
                OggettoContenitore contenitoreO = (OggettoContenitore) l.get(i);
                if(!contenitoreO.isAperto()){
                    contenitoreO.setAperto(true);
                    this.usabilita--;
                    contenitore = contenitoreO;
                    l.set(i, contenitore);
                    stanza.setOggetiStanza(l);
                    System.out.println("Rin: 'Hai aperto "+contenitore.getNome()+"'");
                }
                else{
                    System.out.println("Rin: 'E' già aperto'");
                }
            }
        }
        else{
            System.out.println("Non puoi usare questo oggetto");
        }
    }

    @Override
    public void descrizioneOggetto() {
        System.out.println("Rin: 'E' una chiave, potrebbe servirci per aprire uno scrigno, puo' essre utilizzata  per aprire un solo scrigno");
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
