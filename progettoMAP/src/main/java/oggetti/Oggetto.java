/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

import com.mycompany.progettomap.parser.ParserOutput;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import tipi.Comando;
import tipi.Giocatore;
import tipi.Stanza;

/**
 *
 * @author mtubi
 */
public abstract class Oggetto {
    protected String nome;
    protected Set<String> alias;
    protected List<Comando> listaMosse;
    protected String descrizione;
    protected boolean prendibile;
    protected int usabilita;

    public Oggetto(String nome, Set<String> alias, List<Comando> listaMosse, String descrizione, boolean prendibile, int usabilita) {
        this.nome = nome;
        this.alias = alias;
        this.listaMosse = listaMosse;
        this.descrizione = descrizione;
        this.prendibile = prendibile;
        this.usabilita = usabilita;
    }
    
    
    
    public void setNome(String name) {
        this.nome = name;
    }

    public void setListaMosse(List<Comando> listaMosse) {
        this.listaMosse = listaMosse;
    }
    
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    public void setDescrizione(String description) {
        this.descrizione = description;
    }

    public void setPrendibile(boolean prendibile) {
        this.prendibile = prendibile;
    }

    public String getNome() {
        return nome;
    }

    public List<Comando> getListaMosse() {
        return listaMosse;
    }
    
    public Set<String> getAlias() {
        return alias;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public boolean isPrendibile() {
        return prendibile;
    }

    public int getUsabilita() {
        return usabilita;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.nome);
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
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
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
}