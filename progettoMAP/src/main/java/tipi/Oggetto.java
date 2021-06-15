/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

import com.mycompany.progettomap.parser.ParserOutput;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mtubi
 */
public abstract class Oggetto {
    private static int numOggetti;
    private final int id;
    private String nome;
    private Set<String> alias;
    private List<ParserOutput> listaMosse;
    private String descrizione;
    private boolean apribile=false;
    private boolean prendibile=false;
    private boolean aperto=false;

    public Oggetto(String nome, Set<String> alias, String description, boolean apribile, boolean prendibile, boolean aperto, List<ParserOutput> listaMosse) {
        this.id = numOggetti;
        this.nome = nome;
        this.alias = alias;
        this.listaMosse = listaMosse;
        this.descrizione = description;
        this.apribile = apribile;
        this.prendibile = prendibile;
        this.aperto = aperto;
        numOggetti++;
    }

    public Oggetto(String nome, List<ParserOutput> listaMosse) {
        this.id=numOggetti;
        this.nome = nome;
        this.listaMosse = listaMosse;
        numOggetti++;
    }

    public Oggetto(String nome, Set<String> alias, List<ParserOutput> listaMosse) {
        this.id=numOggetti;
        numOggetti++;
        this.nome = nome;
        this.alias = alias;
        this.listaMosse = listaMosse;
    }

    public Oggetto(String nome, String description, List<ParserOutput> listaMosse) {
        this.id=numOggetti;
        numOggetti++;
        this.nome = nome;
        this.descrizione = description;
        this.listaMosse = listaMosse;
    }

    public Oggetto(int id, String nome, String description, List<ParserOutput> listaMosse) {
        this.id = id;
        this.nome = nome;
        this.descrizione = description;
        this.listaMosse = listaMosse;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public void setListaMosse(List<ParserOutput> listaMosse) {
        this.listaMosse = listaMosse;
    }
    
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    public void setDescrizione(String description) {
        this.descrizione = description;
    }

    public void setApribile(boolean apribile) {
        this.apribile = apribile;
    }

    public void setPrendibile(boolean prendibile) {
        this.prendibile = prendibile;
    }

    public void setAperto(boolean aperto) {
        this.aperto = aperto;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<ParserOutput> getListaMosse() {
        return listaMosse;
    }
    
    public Set<String> getAlias() {
        return alias;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public boolean isApribile() {
        return apribile;
    }

    public boolean isPrendibile() {
        return prendibile;
    }

    public boolean isAperto() {
        return aperto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }  
    
    public abstract void usa();
}
