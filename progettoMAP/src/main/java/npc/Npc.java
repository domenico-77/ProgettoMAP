/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npc;

import oggetti.Oggetto;
import tipi.Giocatore;

/**
 *
 * @author mtubi
 */
public abstract class Npc {
    protected String nome;
    protected boolean vivo;
    protected boolean neutrale;
    protected int vita;
    protected Oggetto oggetto;
    protected boolean sconosciuto;

    public Npc(String nome, boolean vivo, int vita, Oggetto oggetto, boolean neutrale) {
        this.nome = nome;
        this.vivo = vivo;
        this.vita = vita;
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

    public int getVita() {
        return vita;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public Oggetto getOggetto() {
        return oggetto;
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
    
    
    
    public abstract void interagisci(Giocatore giocatore);
}
