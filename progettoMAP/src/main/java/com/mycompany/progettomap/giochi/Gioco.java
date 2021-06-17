/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progettomap.giochi;

/**
 *
 * @author Acer
 */

import logicaGioco.DescrizioneGioco;
import com.mycompany.progettomap.parser.ParserOutput;
import tipi.Comando;
import tipi.Stanza;
import tipi.TipoComando;
import java.io.PrintStream;

/**
 *
 * @author Acer
 */
public class Gioco extends DescrizioneGioco {

    @Override
    public void inizializza() throws Exception {
        //comandi
        Comando nord = new Comando("nord", TipoComando.nord);
        nord.setAlias(new String[]{"n", "su", "sopra", "sù"});
        //aggiungere comando alla lista di comandi di descrizioneGioco?
        Comando sud = new Comando("sud", TipoComando.sud);
        sud.setAlias(new String[]{"s", "giu", "sotto", "giù"});

        Comando est = new Comando("est", TipoComando.est);
        est.setAlias(new String[]{"e", "destrra"});

        Comando ovest = new Comando("ovest", TipoComando.ovest);
        ovest.setAlias(new String[]{"o", "sinistra"});

        Comando fine = new Comando("fine", TipoComando.fine);
        fine.setAlias(new String[]{"end", "termina", "esci", "exit", "chiudi", "muori", "abbandona"});

        Comando inventario = new Comando("inventario", TipoComando.inventario);
        inventario.setAlias(new String[]{"zaino", "borsa", "i", "inv", "oggetti"});

        Comando aprire = new Comando("aprire", TipoComando.aprire);
        aprire.setAlias(new String[]{"apri", "open"});

        Comando chiudere = new Comando("chiudere", TipoComando.chiudere);
        chiudere.setAlias(new String[]{"chiudi", "close"});

        Comando spingere = new Comando("spingere", TipoComando.spingere);
        spingere.setAlias(new String[]{"spingi", "premi", "push", "attiva"});

        Comando tirare = new Comando("tirare", TipoComando.tirare);
        tirare.setAlias(new String[]{"tirare", "pull"});

        Comando camminare_verso = new Comando("camminare_verso", TipoComando.camminare_verso);
        camminare_verso.setAlias(new String[]{"vai_a", "vai_verso", "avvicinati_a", "vai"});
        
        Comando raccogliere = new Comando("raccogliere", TipoComando.raccogliere);
        raccogliere.setAlias(new String[]{"raccogli", "afferra", "prendi", "afferrare","prendere"});
        
        Comando parlare_a = new Comando("parlare_a", TipoComando.parlare_a);
        parlare_a.setAlias(new String[]{"parla", "parlagli", "parlale", "chiedi","domanda","interagisci"});
        
        Comando dare = new Comando("dare", TipoComando.dare);
        dare.setAlias(new String[]{"dai", "dagli", "dalle", "dona"});
        
        Comando usare = new Comando("usare", TipoComando.usare);
        usare.setAlias(new String[]{"usa", "utilizza", "utilizzare"});
        
        Comando osservare = new Comando("osservare", TipoComando.osservare);
        osservare.setAlias(new String[]{"osserva", "guarda", "guardare"});
        
        Comando accendere = new Comando("accendere", TipoComando.accendere);
        accendere.setAlias(new String[]{"accendi"});
        
        Comando spegnere = new Comando("spegnere", TipoComando.spegnere);
        spegnere.setAlias(new String[]{"spegni"});
        
        Comando torna_indietro = new Comando("raccogliere", TipoComando.torna_indietro);
        torna_indietro.setAlias(new String[]{"indietreggia", "torna"});
        //stanze
        Stanza hall = new Stanza ();

    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
    }
}
