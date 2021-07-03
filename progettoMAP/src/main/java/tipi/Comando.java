/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author mtubi
 */
public class Comando implements Serializable{
    private String nome;
    private TipoComando tipo;
    private Set<String> alias;

    public Comando(String nome, TipoComando tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Comando(String nome, TipoComando tipo, Set<String> alias) {
        this.nome = nome;
        this.tipo = tipo;
        this.alias = alias;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public String getNome() {
        return nome;
    }

    public TipoComando getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(TipoComando tipo) {
        this.tipo = tipo;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }
    
    public void setAlias(String[] alias){
        this.alias.retainAll(Arrays.asList(alias));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.nome);
        hash = 17 * hash + Objects.hashCode(this.tipo);
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
        final Comando other = (Comando) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        return true;
    }
    
    
}
