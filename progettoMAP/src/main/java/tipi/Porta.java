/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

/**
 *
 * @author mtubi
 */
public class Porta {
    TipoPorta tipo;
    Stanza stanza;
    boolean chiusa;
    boolean nascosta;

    public Porta(TipoPorta tipo, Stanza stanza, boolean chiusa, boolean nascosta) {
        this.tipo = tipo;
        this.stanza = stanza;
        this.chiusa=chiusa;
        this.nascosta=nascosta;
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

    public boolean isNascosta() {
        return nascosta;
    }

    public void setNascosta(boolean nascosta) {
        this.nascosta = nascosta;
    }
    
    public String decsiriviPorta(){
        String descrizione = "";
        if(this.nascosta){
            descrizione = (" c'è un armadio un po' strano");
        }
        else{
            if(this.chiusa){
                if(this.tipo == TipoPorta.argento){
                    descrizione = (" c'è una porta d'argento chiusa, servirà una chiave dello stesso tipo per aprirla");
                }
                else if(this.tipo == TipoPorta.oro){
                    descrizione = (" c'è una porta di oro chiusa, servirà una chiae dello stesso tipo per aprirla");
                }
                else if(this.tipo == TipoPorta.normale){
                    descrizione = (" c'è una porta normale, non ci vuole niente ad aprirla");
                }
                else if(this.tipo == TipoPorta.tunnel){
                    descrizione = (" c'è un tunnel, chissa dove porta");
                }
            }
            else{
                descrizione = (" c'è uns porta già aperta");
            }
        }
        return descrizione;
    }
}
