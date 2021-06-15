/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

import com.mycompany.progettomap.parser.ParserOutput;
import java.util.List;

/**
 *
 * @author mtubi
 */
public class Giocatore {
    private int vita = 3;
    private Inventario inventario;
    private List<ParserOutput> listaMosse;

    public Giocatore(List<ParserOutput> listaMosse) {
        this.listaMosse = listaMosse;
    }

    public int getVita() {
        return vita;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public List<ParserOutput> getListaMosse() {
        return listaMosse;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public void setListaMosse(List<ParserOutput> listaMosse) {
        this.listaMosse = listaMosse;
    }
    
    public void decrementaVita(){
        if(vita<=3){
           vita--;
        }
    }
    
    public void incrementaVita(){
        if(vita<3){
            vita++;
        }
    }
    
    public boolean isDead(){
        return (vita==0);
    }
    
    public void aggiornaMosse(List<ParserOutput> nuoveMosse){
        listaMosse.addAll(nuoveMosse);
    }
    
    public void rimuoviMosse(List<ParserOutput> mosseDaEliminare){
        listaMosse.removeAll(listaMosse);
    }
}
