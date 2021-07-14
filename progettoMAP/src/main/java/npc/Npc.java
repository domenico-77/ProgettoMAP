/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npc;

import java.io.Serializable;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import oggetti.Oggetto;
import tipi.Giocatore;

/**
 *
 * @author mtubi
 */
public abstract class Npc implements Serializable{
    protected String nome;
    protected boolean vivo = true;
    protected boolean neutrale;
    protected Oggetto oggetto;
    protected boolean sconosciuto = true;
    protected final static String[] alias = {"personaggio", "uomo", "persona", "tizio", "umano"};

    public Npc(String nome, Oggetto oggetto, boolean neutrale) {
        this.nome = nome;
        this.oggetto = oggetto;
        this.neutrale = neutrale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public Oggetto getOggetto() {
        return oggetto;
    }

    public boolean isSconosciuto() {
        return sconosciuto;
    }

    public void setOggetto(Oggetto oggetto) {
        this.oggetto = oggetto;
    }

    public boolean isNeutrale() {
        return neutrale;
    }

    public void setNeutrale(boolean neutrale) {
        this.neutrale = neutrale;
    }

    public static String[] getAlias() {
        return alias;
    }
    
    public abstract List<String> getAliasNome();
    
    public abstract void interagisci(Giocatore giocatore);
    public abstract void interagisci(Giocatore giocatore, JTextArea out, JFrame frame);
}
