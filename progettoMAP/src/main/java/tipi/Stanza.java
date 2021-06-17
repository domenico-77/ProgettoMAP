/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

import oggetti.Oggetto;
import java.util.List;

/**
 *
 * @author mtubi
 */
public class Stanza {
    private static int numStanze=0;
    private int id;
    private String descrizione;
    private Porta portaNord;
    private Porta portaSud;
    private Porta portaEst;
    private Porta portaOvest;
    private List<Oggetto> oggettiStanza;

    public Stanza(int id, String descrizione, Porta portaNord, Porta portaSud, Porta portaEst, Porta portaOvest, List<Oggetto> oggetiStanza) {
        this.id = numStanze;
        this.descrizione = descrizione;
        this.portaNord = portaNord;
        this.portaSud = portaSud;
        this.portaEst = portaEst;
        this.portaOvest = portaOvest;
        this.oggettiStanza = oggetiStanza;
        numStanze++;
    }

    public static int getNumStanze() {
        return numStanze;
    }

    public int getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Porta getPortaNord() {
        return portaNord;
    }

    public Porta getPortaSud() {
        return portaSud;
    }

    public Porta getPortaEst() {
        return portaEst;
    }

    public Porta getPortaOvest() {
        return portaOvest;
    }

    public List<Oggetto> getOggetiStanza() {
        return oggettiStanza;
    }

    public static void setNumStanze(int numStanze) {
        Stanza.numStanze = numStanze;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPortaNord(Porta portaNord) {
        this.portaNord = portaNord;
    }

    public void setPortaSud(Porta portaSud) {
        this.portaSud = portaSud;
    }

    public void setPortaEst(Porta portaEst) {
        this.portaEst = portaEst;
    }

    public void setPortaOvest(Porta portsaOvest) {
        this.portaOvest = portaOvest;
    }

    public void setOggetiStanza(List<Oggetto> oggetiStanza) {
        this.oggettiStanza = oggetiStanza;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
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
        final Stanza other = (Stanza) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    /*
    public void rimuoviOggetto(Oggetto o){
        if(this.oggettiStanza.contains(o)){
           this.oggettiStanza.remove(o);
        }
    }
    
    public void prendiOggetto(Oggetto o, Giocatore giocatore){
        if(this.oggettiStanza.contains(o)){
            giocatore.getInventario().aggiungiOgetto(this.oggettiStanza.get(this.oggettiStanza.indexOf(o)));
            this.rimuoviOggetto(o);
        }
    }
    */
}