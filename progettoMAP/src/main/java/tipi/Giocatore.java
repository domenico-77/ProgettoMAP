/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

import oggetti.OggettoContenitore;
import oggetti.Oggetto;
import com.mycompany.progettomap.parser.ParserOutput;
import java.util.List;

/**
 *
 * @author mtubi
 */
public class Giocatore {
    private final static int VITA_INIZIO = 100;
    private int vitaMax = VITA_INIZIO;
    private int vitaCorrente = VITA_INIZIO;
    private Inventario inventario;
    private static List<ParserOutput> listaMosse;

    public Giocatore(List<ParserOutput> listaMosse) {
        this.listaMosse = listaMosse;
    }

    public int getVitaCorrente() {
        return vitaCorrente;
    }

    public static int getVITA_INIZIO() {
        return VITA_INIZIO;
    }
    
     public int getVitaMax() {
        return vitaMax;
    }
    
    public Inventario getInventario() {
        return inventario;
    }

    public List<ParserOutput> getListaMosse() {
        return listaMosse;
    }

    public void setVitaCorrente(int vitaCorrente) {
        this.vitaCorrente = vitaCorrente;
    }

    public void setVitaMax(int vitaMax) {
        this.vitaMax = vitaMax;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public void setListaMosse(List<ParserOutput> listaMosse) {
        this.listaMosse = listaMosse;
    }
    
    public void decrementaVita(int danno){
        if(danno >= this.vitaCorrente){
            this.vitaCorrente = 0;
        }
        else{
            this.vitaCorrente = this.vitaCorrente - danno;
        }
    }
    
    public void incrementaVita(int cura){
        if((this.vitaCorrente+cura) >= this.vitaMax){
            this.vitaCorrente = this.vitaMax;
        }
        else{
            this.vitaCorrente = this.vitaCorrente + cura;
        }
    }
    
    public boolean isDead(){
        return (vitaCorrente==0);
    }
    
    public void aggiornaMosse(List<ParserOutput> nuoveMosse){
        listaMosse.addAll(nuoveMosse);
    }
    
    public void rimuoviMosse(List<ParserOutput> mosseDaEliminare){
        listaMosse.removeAll(listaMosse);
    }
}
