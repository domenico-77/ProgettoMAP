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
    Materiale tipo;
    Stanza stanza;
    boolean chiusa;

    public Porta(Materiale tipo, Stanza stanza, boolean chiusa) {
        this.tipo = tipo;
        this.stanza = stanza;
        this.chiusa=chiusa;
    }

    public Materiale getTipo() {
        return tipo;
    }

    public Stanza getStanza() {
        return stanza;
    }

    public void setTipo(Materiale tipo) {
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
}
