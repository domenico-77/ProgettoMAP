/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi.stanze;

import java.io.Serializable;


/**
 *
 * @author mtubi
 */
public class Porta implements Serializable{
    TipoPorta tipo;
    Stanza stanza;
    boolean chiusa;
    private static final String[] alias = {"porta", "uscio", "portone", "entrata", "passaggio", "accesso", "ingresso"};

    public Porta(TipoPorta tipo, Stanza stanza, boolean chiusa) {
        this.tipo = tipo;
        this.stanza = stanza;
        this.chiusa=chiusa;
    }

    public TipoPorta getTipo() {
        return tipo;
    }

    public Stanza getStanza() {
        return stanza;
    }

    public void setTipo(TipoPorta tipo) {
        this.tipo = tipo;
    }

    public void setStanza(Stanza stanza) {
        this.stanza = stanza;
    }

    public void setChiusa(boolean chiusa) {
        this.chiusa = chiusa;
    }

    public boolean isChiusa() {
        return chiusa;
    }
    
    public String descriviPorta(){
        String descrizione = "";
            if(this.chiusa){
                if(this.tipo == TipoPorta.argento){
                    descrizione = (" c'è una porta d'argento chiusa, servirà una chiave per aprirla");
                }
                else if(this.tipo == TipoPorta.oro){
                    descrizione = (" c'è una porta di oro chiusa, servirà un totem per aprirla");
                }
                else if(this.tipo == TipoPorta.normale){
                    descrizione = (" c'è una porta normale, non ci vuole niente ad aprirla");
                }
            }
            else{
                descrizione = (" c'è una porta già aperta");
            }
        
        return descrizione;
    }
    

    public static String[] getAlias() {
        return alias;
    }
    
    
}
