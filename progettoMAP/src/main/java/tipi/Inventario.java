/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

import oggetti.Oggetto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mtubi
 */
public class Inventario {
    List<Oggetto> inventario;
    public Inventario(){
        this.inventario= new ArrayList();
    }

    public void setInventario(List<Oggetto> inventario) {
        this.inventario = inventario;
    }

    public List<Oggetto> getInventario() {
        return inventario;
    }
    
    
    public void aggiungiOggetto(Oggetto o){
       this.inventario.add(o);
    }
    
    public void rimuoviOggetto(Oggetto o){
       int pos = this.inventario.indexOf(o);
       if(pos!=-1){
           this.inventario.remove(pos);
       }
    }
    
    public void visualizzaInventario(){
        int i=1;
        for(Oggetto o : inventario){
            System.out.println("Oggetto num"+(i)+" "+o.getNome()+": "+o.getDescrizione());
            i++;
        }
    }
    
    public boolean contieneOggetto(Oggetto o){
        return (inventario.contains(o));
    }
    
    public void usaOggetto(Oggetto o, Giocatore giocatore, Stanza stanza){
        if(contieneOggetto(o)){
            Oggetto oggetto = this.inventario.get(this.inventario.indexOf(o));
            if(oggetto.getUsabilita() > 0){
                oggetto.usa(giocatore, stanza);
                if(oggetto.getUsabilita() == 0){
                    this.inventario.remove(oggetto);
                }
            }
        }
    }
}
