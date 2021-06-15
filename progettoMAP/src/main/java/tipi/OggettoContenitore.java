/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

import com.mycompany.progettomap.parser.ParserOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mtubi
 */
public class OggettoContenitore extends Oggetto {
    private List<Oggetto> listaOggetti=new ArrayList();
    
    public OggettoContenitore(String nome, Set<String> alias, String descrizione, boolean apribile, boolean prendibile, boolean aperto, List<ParserOutput> listaMosse) {
        super(nome, alias, descrizione, apribile, prendibile, aperto, listaMosse);
    }

    public OggettoContenitore(String nome, List<ParserOutput> listaMosse) {
        super(nome, listaMosse);
    }

    public OggettoContenitore(String nome, Set<String> alias, List<ParserOutput> listaMosse) {
        super(nome, alias, listaMosse);
    }

    public OggettoContenitore(String nome, String descrizione, List<ParserOutput> listaMosse) {
        super(nome, descrizione, listaMosse);
    }

    public OggettoContenitore(int id, String nome, String descrizione, List<ParserOutput> listaMosse) {
        super(id, nome, descrizione, listaMosse);
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
        if(contieneOggetto(o)){
        this.listaOggetti.remove(o);
        }
    }
    
    public boolean contenitoreVuoto(){
        boolean vuoto=false;
        if(!isAperto()){
            vuoto=this.listaOggetti.isEmpty();
        }
        return vuoto;
    }
    
    public boolean contieneOggetto(Oggetto o){
        return this.listaOggetti.contains(o);
    }

    @Override
    public void usa() {
        
    }
}
