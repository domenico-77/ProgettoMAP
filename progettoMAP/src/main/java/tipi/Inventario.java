/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

import java.io.Serializable;
import tipi.stanze.Stanza;
import oggetti.Oggetto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

/**
 *
 * @author mtubi
 */
public class Inventario implements Serializable {
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
    
   
    
    public void visualizzaInventario(JTextArea out){
        if (this.inventario.isEmpty()) {
            out.setText("Rin: 'Abbiamo le tasche vuote, potremmo trovare degli oggetti utili per la prigione");
        } else {
            out.setText("Rin: 'Questi sono gli oggetti a disposizione:\n");
            int i = 1;
            for (Oggetto o : inventario) {
                out.append(i + ". "+ o.getNome()+ "\n");
                i++;
            }
        }
    }
    
    public boolean contieneOggetto(Oggetto o){
        return (inventario.contains(o));
    }
    
    
    
    public void usaOggettoSwing(Oggetto o, Giocatore giocatore, Stanza stanza, JTextArea out){
        if(contieneOggetto(o)){
            Oggetto oggetto = this.inventario.get(this.inventario.indexOf(o));
            if(oggetto.getUsabilita() > 0){
                oggetto.usaSwing(giocatore, stanza, out);
                if(oggetto.getUsabilita() == 0){
                    this.inventario.remove(oggetto);
                }
            }
        } else {
            out.append("Rin :' Al momento non disponiamo dell'oggetto : "+o.getNome() + "'");
        }
    }
    
}
