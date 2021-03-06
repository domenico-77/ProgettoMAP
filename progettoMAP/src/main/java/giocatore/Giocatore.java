/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giocatore;

import parser.ParserOutput;
import java.io.Serializable;
import java.util.List;
import comandi.Comando;

/**
 *
 * @author mtubi
 */
public class Giocatore implements Serializable{
    private final static int VITA_INIZIO = 100;
    private int vitaMax = VITA_INIZIO;
    private int vitaCorrente = VITA_INIZIO;
    private Inventario inventario = new Inventario();
    private List<Comando> listaMosse;
    private int punteggio = 0;

    public Giocatore(List<Comando> listaMosse) {
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

    public List<Comando> getListaMosse() {
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

    public void setListaMosse(List<Comando> listaMosse) {
        this.listaMosse = listaMosse;
    }

    public int getPunteggio() {
        return punteggio;
    }
    
    public void incrementaPunteggio(int xp){
        this.punteggio += xp;
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
    
    public void aggiornaMosse(List<Comando> nuoveMosse){
        listaMosse.addAll(nuoveMosse);
    }
    
    public void rimuoviMosse(List<ParserOutput> mosseDaEliminare){
        listaMosse.removeAll(listaMosse);
    }
}
