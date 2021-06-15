/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

import java.util.Set;

/**
 *
 * @author mtubi
 */
public class Oggetto {
    private static int numOggetti;
    private final int id;
    private String nome;
    private Set<String> alias;
    private String descrizione;
    private boolean apribile=false;
    private boolean prendibile=false;
    private boolean aperto=false;

    public Oggetto(String name, Set<String> alias, String description, boolean apribile, boolean prendibile, boolean aperto) {
        this.id = numOggetti;
        this.nome = name;
        this.alias = alias;
        this.descrizione = description;
        this.apribile = apribile;
        this.prendibile = prendibile;
        this.aperto = aperto;
        numOggetti++;
    }

    public Oggetto(String name) {
        this.id=numOggetti;
        this.nome = name;
        numOggetti++;
    }

    public Oggetto(String name, Set<String> alias) {
        this.id=numOggetti;
        numOggetti++;
        this.nome = name;
        this.alias = alias;
    }

    public Oggetto(String name, String description) {
        this.id=numOggetti;
        numOggetti++;
        this.nome = name;
        this.descrizione = description;
    }

    public Oggetto(int id, String name, String description) {
        this.id = id;
        this.nome = name;
        this.descrizione = description;
    }

    public void setNome(String name) {
        this.nome = name;
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
}
