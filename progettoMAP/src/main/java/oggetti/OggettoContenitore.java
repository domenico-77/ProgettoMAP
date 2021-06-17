/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

import oggetti.Oggetto;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import tipi.Comando;
import tipi.Giocatore;
import tipi.Inventario;
import tipi.Materiale;
import tipi.Stanza;

/**
 *
 * @author mtubi
 */
public class OggettoContenitore extends Oggetto {
    private boolean aperto = false;
    private Materiale materiale;
    private List<Oggetto> listaOggetti=new ArrayList();

    public OggettoContenitore(String nome, Set<String> alias, List<Comando> listaMosse, String descrizione, boolean prendibile,int usabilita, List<Oggetto> listaOggetti, Materiale materiale) {
        super(nome, alias, listaMosse, descrizione, prendibile, usabilita);
        this.listaOggetti = listaOggetti;
        this.materiale = materiale;
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
        if(this.aperto){
            vuoto=this.listaOggetti.isEmpty();
        }
        return vuoto;
    }
    
    public boolean contieneOggetto(Oggetto o){
        return this.listaOggetti.contains(o);
    }

    @Override
    public void usa(Giocatore giocatore, Stanza stanza) {
        if (this.aperto) {
            if (!this.contenitoreVuoto()) {
                Inventario inventario = giocatore.getInventario();
                for (Oggetto o : listaOggetti) {
                    inventario.aggiungiOgetto(o);
                }
                giocatore.setInventario(inventario);
            }
            else{
                System.out.println("Non c'è nessun oggetto all'interno");
            }
        }
        else{
            System.out.println("Il contenitore è chiuso");
        }
    }
}
