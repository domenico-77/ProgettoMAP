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
import tipi.Stanza;
import tipi.Comando;
import tipi.Inventario;
import java.io.PrintStream;

import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public abstract class DescrizioneGioco {

    private final List<Stanza> stanze = new ArrayList<>();
    private final  List<Comando> comandi = new ArrayList<>();
    private final Inventario inventario = new Inventario();
    private Stanza stanzaCorrente;

    public Stanza getStanzaCorrente() {
        return stanzaCorrente;
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.stanzaCorrente = stanzaCorrente;
    }

    public List<Stanza> getStanze() {
        return stanze;
    }

    public List<Comando> getComandi() {
        return comandi;
    }

    public Inventario getInventory() {
        return inventario;
    }

    public abstract void inizializza() throws Exception;

    public abstract void nextMove(ParserOutput p, PrintStream out);
}
