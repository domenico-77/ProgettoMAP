/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

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
    private Porta Nord;
    private List<Oggetto> oggetiStanza;

    public Stanza(int id, String descrizione, Porta portaNord, Porta portaSud, Porta portaEst, Porta Nord, List<Oggetto> oggetiStanza) {
        this.id = numStanze;
        this.descrizione = descrizione;
        this.portaNord = portaNord;
        this.portaSud = portaSud;
        this.portaEst = portaEst;
        this.Nord = Nord;
        this.oggetiStanza = oggetiStanza;
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

    public Porta getNord() {
        return Nord;
    }

    public List<Oggetto> getOggetiStanza() {
        return oggetiStanza;
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

    public void setNord(Porta Nord) {
        this.Nord = Nord;
    }

    public void setOggetiStanza(List<Oggetto> oggetiStanza) {
        this.oggetiStanza = oggetiStanza;
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
    
    
}
