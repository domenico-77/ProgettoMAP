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
import java.util.List;
import tipi.stanze.Stanza;
import tipi.Comando;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Stack;
import tipi.Giocatore;

/**
 *
 * @author Acer
 */
public abstract class DescrizioneGioco {

    protected final List<Stanza> stanze = new ArrayList<>();
    protected Giocatore giocatore = new Giocatore(new ArrayList());
    protected Stanza stanzaCorrente;
    protected Stack<Stanza> PercorsoStanze = new Stack<>();
    
   
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
    
    public abstract void gioca();
    
}
