/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi.stanze;

import java.io.Serializable;
import tipi.stanze.Porta;
import oggetti.Oggetto;
import java.util.List;
import npc.Mob;
import npc.Npc;

/**
 *
 * @author mtubi
 */
public class Stanza implements Serializable {

    private static int numStanze = 0;
    private int id;
    private boolean illuminata;
    private final String nomeStanza;
    private Porta portaNord;
    private Porta portaSud;
    private Porta portaEst;
    private Porta portaOvest;
    private List<Oggetto> oggettiStanza;
    private Npc npc;

    public Stanza(String nomeStanza, boolean illuminata, Porta portaNord, Porta portaSud, Porta portaEst, Porta portaOvest, List<Oggetto> oggetiStanza, Npc npc) {
        this.nomeStanza = nomeStanza;
        this.illuminata = illuminata;
        this.id = numStanze;
        this.portaNord = portaNord;
        this.portaSud = portaSud;
        this.portaEst = portaEst;
        this.portaOvest = portaOvest;
        this.oggettiStanza = oggetiStanza;
        this.npc = npc;
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

    public void setPortaOvest(Porta portaOvest) {
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

    public Npc getNpc() {
        return npc;
    }

    public void setNpc(Npc npc) {
        this.npc = npc;
    }

    public String getNomeStanza() {
        return nomeStanza;
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

    public void rimuoviOggetto(Oggetto o) {
        if (this.oggettiStanza.contains(o)) {
            this.oggettiStanza.remove(o);
        }
    }

    /*
    public void prendiOggetto(Oggetto o, Giocatore giocatore){
        if(this.oggettiStanza.contains(o)){
            giocatore.getInventario().aggiungiOgetto(this.oggettiStanza.get(this.oggettiStanza.indexOf(o)));
            this.rimuoviOggetto(o);
        }
    }
     */
    public void DescriviStanza() {
        if (!this.illuminata) {
            if (this.npc != null) {
                System.out.println("Rin:'Non riesco a vedere nulla, sarebbe meglio illuminare la stanza prima di fare qualcosa o indietreggiare'");
            } else {
                System.out.println("Rin:'Non riesco a vedere nulla e ci sono dei rumori sospetti potrebbe esserci qualcuno, sarebbe meglio illuminare la stanza prima di fare qualcosa o indietreggiare'");
            }

        } else {
            System.out.println("Rin:' Sei entrato nella stanza : " + this.nomeStanza + " . ");
            if (this.portaNord != null) {
                System.out.println("a nord " + this.portaNord.descriviPorta() + ";  ");
            }
            if (this.portaSud != null) {
                System.out.println("a sud " + this.portaSud.descriviPorta() + "; ");
            }
            if (this.portaEst != null) {
                System.out.println("a est " + this.portaEst.descriviPorta() + "; ");
            }
            if (this.portaOvest != null) {
                System.out.println("a ovest " + this.portaOvest.descriviPorta() + ".");
            }
            if (this.oggettiStanza.isEmpty()) {
                System.out.print("Non c'è niente di interessante in questa stanza.");
            } else {
                if (this.oggettiStanza.size() >= 2) {
                    System.out.print("Sono presenti diversi oggetti sparsi nella stanza: ");
                    int i = 1;
                    int size = this.oggettiStanza.size();
                    for (Oggetto o : this.oggettiStanza) {
                        if (i < size - 2) {
                            System.out.print(o.getNome() + ", ");
                            i++;
                        } else {
                            if (i == size - 2) {
                                System.out.print(o.getNome() + " e ");
                            } else {
                                System.out.print(o.getNome());
                            }
                        }

                    }
                } else {
                    System.out.print("E' prensente solo " + this.oggettiStanza.get(0).getNome());
                }
            }

            if (this.npc != null) {
                if (this.npc.isVivo()) {
                    if (this.npc.isNeutrale()) {
                        System.out.print(" Sembra esserci un prigioniero, potremmo provare a parlarci");
                    } else {
                        Mob mob = (Mob) this.getNpc();
                        if (mob.isCorrotto()) {
                            System.out.print("C'e' la guardia che abbiamo corrotto precedentemente, andiamocene potrebbe cambiare idea");
                        } else {
                            System.out.print("Oh no! C'e' una guardia, se non possiamo affrontarlo ci conviene fare l'indispensabile in questa stanza e andarcene!");
                        }
                    }
                } else {
                    System.out.print("C'e' un cadavere in questa stanza, potrebbe avere qualcosa di utile");
                }
            }

            System.out.println("'");
        }
    }

}
