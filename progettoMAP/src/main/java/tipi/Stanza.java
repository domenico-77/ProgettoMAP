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

    private static int numStanze = 0;
    private int id;
    private boolean illuminata;
    private String nomeStanza;
    private Porta portaNord;
    private Porta portaSud;
    private Porta portaEst;
    private Porta portaOvest;
    private List<Oggetto> oggettiStanza;

    public Stanza(String nomeStanza,boolean illuminata,Porta portaNord, Porta portaSud, Porta portaEst, Porta portaOvest, List<Oggetto> oggetiStanza) {
        this.nomeStanza = nomeStanza;
        this.illuminata = illuminata;
        this.id = numStanze;
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

    public boolean isIlluminata() {
        return illuminata;
    }

    public void setIlluminata(boolean illuminata) {
        this.illuminata = illuminata;
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

    
    public void rimuoviOggetto(Oggetto o){
        if(this.oggettiStanza.contains(o)){
           this.oggettiStanza.remove(o);
        }
    }
    
    public void prendiOggetto(Oggetto o, Giocatore giocatore){
        if(this.oggettiStanza.contains(o)){
            giocatore.getInventario().aggiungiOggetto(this.oggettiStanza.get(this.oggettiStanza.indexOf(o)));
            this.rimuoviOggetto(o);
        }
    }
     
    public void DescriviStanza() {
        if (!this.illuminata) {
            System.out.println("Rin:'Non riesco a vedere nulla, sarebbe meglio illuminare la stanza prima di fare qualcosa'");

        } else {
            System.out.print("Rin:' Sei entrato nella " + this.nomeStanza);
            if (this.portaNord != null) {
                System.out.print("A nord " + this.portaNord.descriviPorta() + ",  ");
            } else {
                System.out.print("A nord non c'è niente,  ");
            }
            if (this.portaSud != null) {
                System.out.print("a sud " + this.portaNord.descriviPorta() + ", ");
            } else {
                System.out.print("A sud non c'è niente,  ");

            }
            if (this.portaEst != null) {
                System.out.print("a est " + this.portaEst.descriviPorta() + "e ");
            } else {
                System.out.print("A est non c'è niente, e ");

            }
            if (this.portaOvest != null) {
                System.out.println("a ovest " + this.portaEst.descriviPorta() + ".");

            } else {
                System.out.println("A ovest non c'è niente.");

            }
           if(this.oggettiStanza.isEmpty()){
               System.out.println("Non c'è niente di interessante in questa stanza.");
           } else {
               if(this.oggettiStanza.size() >= 2){
                   System.out.print("Sono presenti diversi oggetti sparsi nella stanza:");
                   int i= 1;
                   for (Oggetto o: oggettiStanza){
                        System.out.println(i + o.getNome());
                        i++;
                    }
               }
               else{
                   System.out.print("E' prensente solo " + this.oggettiStanza.get(0).getNome());
               }
           }
           System.out.println("'");
        }
    }
}
