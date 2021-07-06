/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Acer
 */
package logicaGioco;

import com.mycompany.progettomap.parser.ParserOutput;
import java.io.FileNotFoundException;
import java.util.List;
import tipi.stanze.Stanza;
import tipi.Comando;
import java.io.PrintStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;
import tipi.Giocatore;

/**
 *
 * @author Acer
 */
public abstract class DescrizioneGioco implements Serializable {

    protected final List<Stanza> stanze = new ArrayList<>();
    protected static int numPartite;
    protected int id;
    protected Giocatore giocatore = new Giocatore(new ArrayList());
    protected Stanza stanzaCorrente;
    protected Stack<Stanza> PercorsoStanze = new Stack<>();
    protected String nomeGiocatore;
    protected boolean finita = false;
    protected boolean sospesa = false;

    public DescrizioneGioco(String nomeGiocatore) {
        this.nomeGiocatore = nomeGiocatore;
    }

    public String getNomeGiocatore() {
        return nomeGiocatore;
    }

    public void setNomeGiocatore(String nomeGiocatore) {
        this.nomeGiocatore = nomeGiocatore;
    }

    public boolean isFinita() {
        return finita;
    }

    public void setFinita(boolean finita) {
        this.finita = finita;
    }

    public boolean isSospesa() {
        return sospesa;
    }

    public void setSospesa(boolean sospesa) {
        this.sospesa = sospesa;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.giocatore);
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
        final DescrizioneGioco other = (DescrizioneGioco) obj;
        if (!Objects.equals(this.giocatore, other.giocatore)) {
            return false;
        }
        return true;
    }
    
    

    public Stanza getStanzaCorrente() {
        return stanzaCorrente;
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.stanzaCorrente = stanzaCorrente;
    }

    public List<Stanza> getStanze() {
        return stanze;
    }

    public abstract void inizializza();

    public abstract void nextMove(ParserOutput p, PrintStream out);

    public abstract void stampaStanze();

    public Stack<Stanza> getPercorsoStanze() {
        return PercorsoStanze;
    }

    public void setPercorsoStanze(Stack<Stanza> PercorsoStanze) {
        this.PercorsoStanze = PercorsoStanze;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    public abstract void gioca()throws FileNotFoundException;

    public abstract void continua() throws FileNotFoundException;
    


}
