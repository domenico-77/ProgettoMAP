/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

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
    
    
    public void aggiungiOgetto(Oggetto o){
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
    
    
}
