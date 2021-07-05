/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import tipi.Comando;
import tipi.Giocatore;
import tipi.stanze.Stanza;

/**
 *
 * @author mtubi
 */
public abstract class Oggetto {
    protected String nome;
    protected Set<String> alias;
    protected boolean prendibile;
    protected int usabilita;
    protected TipoOggetto tipo;

    public Oggetto(String nome, Set<String> alias, boolean prendibile, int usabilita, TipoOggetto tipo) {
        this.nome = nome;
        this.alias = alias;
        this.prendibile = prendibile;
        this.usabilita = usabilita;
        this.tipo = tipo;
    }
    
    
    
    public void setNome(String name) {
        this.nome = name;
    }

    
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    public void setPrendibile(boolean prendibile) {
        this.prendibile = prendibile;
    }

    public void setUsabilita(int usabilita) {
        this.usabilita = usabilita;
    }

    
    public String getNome() {
        return nome;
    }

    
    public Set<String> getAlias() {
        return alias;
    }

    public boolean isPrendibile() {
        return prendibile;
    }

    public int getUsabilita() {
        return usabilita;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.tipo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Oggetto other = (Oggetto) obj;
        if (this.tipo != other.tipo) {
            return false;
        }
        return true;
    }

    public TipoOggetto getTipo() {
        return tipo;
    }
    
    

   

   
    
    public abstract void usa(Giocatore giocatore, Stanza stanza);
    
    public void prendi(Giocatore giocatore){
        if(this.prendibile){
            giocatore.getInventario().aggiungiOggetto(this);
        }
        else{
            System.out.println("Non puoi prendere questo oggetto");
        }
    }
    
    public abstract void descrizioneOggetto();
}
