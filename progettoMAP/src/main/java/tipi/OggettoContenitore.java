/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mtubi
 */
public class OggettoContenitore extends Oggetto {
    private List<Oggetto> listaOggetti=new ArrayList();
    
    public OggettoContenitore(String name, Set<String> alias, String description, boolean apribile, boolean prendibile, boolean aperto) {
        super(name, alias, description, apribile, prendibile, aperto);
    }

    public OggettoContenitore(String name) {
        super(name);
    }

    public OggettoContenitore(String name, Set<String> alias) {
        super(name, alias);
    }

    public OggettoContenitore(String name, String description) {
        super(name, description);
    }

    public OggettoContenitore(int id, String name, String description) {
        super(id, name, description);
    }

    public List<Oggetto> getListaOggetti() {
        return listaOggetti;
    }

    public void setListaOggetti(List<Oggetto> listaOggetti) {
        this.listaOggetti = listaOggetti;
    }
    
    public void aggiungiOggetto(Oggetto o){
        this.listaOggetti.add(o);
    }
    
    public void rimuoviOggetto(Oggetto o){
        this.listaOggetti.remove(o);
    }
    
    public boolean contenitoreVuoto(){
        boolean vuoto=false;
        if(!isAperto()){
            vuoto=this.listaOggetti.isEmpty();
        }
        return vuoto;
    }
}
