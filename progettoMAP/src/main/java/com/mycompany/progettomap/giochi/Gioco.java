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
import java.util.ArrayList;
import java.util.List;
import oggetti.Affilatore;
import oggetti.Candela;
import oggetti.ChiaveOggettoContenitore;
import oggetti.ChiavePorta;
import oggetti.Cibo;
import oggetti.Oggetto;
import oggetti.OggettoContenitore;
import oggetti.OggettoMaligno;
import oggetti.Spada;
import tipi.Porta;
import tipi.TipoPorta;
import tipi.Utilita;

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
        raccogliere.setAlias(new String[]{"raccogli", "afferra", "prendi", "afferrare", "prendere"});

        Comando parlare_a = new Comando("parlare_a", TipoComando.parlare_a);
        parlare_a.setAlias(new String[]{"parla", "parlagli", "parlale", "chiedi", "domanda", "interagisci"});

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
        Stanza st1, st2, st3;
        Oggetto candela = new Candela("Candela", Utilita.generaSetAlias("Candelabro", "Cera", "Lume", "Fiaccola", "Torcia","candela"), Utilita.generaListaComandi(raccogliere, usare, accendere, osservare));
        Oggetto chiaveOggettoContenitore = new ChiaveOggettoContenitore("Grimaldello",Utilita.generaSetAlias("chiave scrigno"),Utilita.generaListaComandi(raccogliere,usare));
        Oggetto chiavePortaDorata = new ChiavePorta("chiave dorata",Utilita.generaSetAlias(),Utilita.generaListaComandi(),TipoPorta.oro);
        Oggetto chiavePortaArgentata = new ChiavePorta("chiave d'argento",Utilita.generaSetAlias(),Utilita.generaListaComandi(),TipoPorta.argento);
        Oggetto cibo = new Cibo("Pane",Utilita.generaSetAlias("Panino","Cibo"),Utilita.generaListaComandi(usare,raccogliere),30);
        Oggetto spada = new Spada("Spada", Utilita.generaSetAlias("spada", "lama", "arma bianca", "daga", "katana", "ferro"), Utilita.generaListaComandi(raccogliere, usare, osservare));
        Oggetto affilatore = new Affilatore("Affilatore", Utilita.generaSetAlias("affilatoio", "cote", "mola", "affilatrice"), Utilita.generaListaComandi(raccogliere, usare, osservare));
        Oggetto oggettoContenitore;
        Oggetto oggettoMaligno = new OggettoMaligno("veleno",Utilita.generaSetAlias(),Utilita.generaListaComandi(),30);

        st1 = new Stanza("Cella di Madji", true, null, null, null, null, new ArrayList<>());
        
        st2 = new Stanza("Corridoio",true,null,null,null,null,Utilita.creaListaOggetti(candela));
        st1.setPortaNord(new Porta(TipoPorta.normale,st2,false,false));
        st2.setPortaSud(new Porta(TipoPorta.normale,st1,false,false));
        this.stanze.add(st1);
        oggettoContenitore = new OggettoContenitore("Scrigno",Utilita.generaSetAlias("tesoro","cofanetto","cassetta","scatola","astuccio","bauletto","portagioie","forziere","scrigno"),Utilita.generaListaComandi(aprire,chiudere,osservare,usare),Utilita.creaListaOggetti(cibo, oggettoMaligno));
        st3 = new Stanza("corridoio", false, null, null, null, null, Utilita.creaListaOggetti(oggettoContenitore));
        st2.setPortaNord(new Porta(TipoPorta.normale, st3, false, false));
        st3.setPortaSud(new Porta(TipoPorta.normale, st2, false, false));
        this.stanze.add(st2);
        st1 = new Stanza("Bagno", true, null, null, null, null, Utilita.creaListaOggetti(chiaveOggettoContenitore));
        st3.setPortaOvest(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaEst(new Porta(TipoPorta.normale, st3, false, false));
        this.stanze.add(st1);
        st2 = new Stanza("Corridoio", true, null, null, null, null, Utilita.creaListaOggetti(affilatore, cibo));
        st3.setPortaNord(new Porta(TipoPorta.normale, st2, false, false));
        st2.setPortaSud(new Porta(TipoPorta.normale, st3, false, false));
        this.stanze.add(st3);
        st1 = new Stanza ("Tesoreria", true, null, null, null, null, Utilita.creaListaOggetti(chiavePortaArgentata));
        st2.setPortaEst(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaOvest(new Porta(TipoPorta.normale, st2, false, false));
        this.stanze.add(st1);
        st3 = new Stanza("Armeria", true, null, null, null, null, Utilita.creaListaOggetti(spada));
        st2.setPortaNord(new Porta(TipoPorta.argento, st3, true, false));
        st3.setPortaSud(new Porta(TipoPorta.argento, st2, true, false));
        this.stanze.add(st2);
        st1 = new Stanza("stanza sicurezza", true, null, null, null, null, Utilita.creaListaOggetti());
        st3.setPortaOvest(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaEst(new Porta(TipoPorta.normale, st3, false, false));
        oggettoContenitore = new OggettoContenitore("Scrigno",Utilita.generaSetAlias("tesoro","cofanetto","cassetta","scatola","astuccio","bauletto","portagioie","forziere","scrigno"),Utilita.generaListaComandi(aprire,chiudere,osservare,usare),Utilita.creaListaOggetti(oggettoMaligno, chiaveOggettoContenitore, candela));
        st2 = new Stanza("Cella 1", true, null, null, null, null, Utilita.creaListaOggetti(oggettoContenitore));
        st1.setPortaOvest(new Porta(TipoPorta.argento, st2, true, false));
        st2.setPortaEst(new Porta(TipoPorta.argento, st1, true, false));
        this.stanze.add(st1);
        st1 = new Stanza("cella 2", false, null, null, null, null, Utilita.creaListaOggetti(chiaveOggettoContenitore, chiaveOggettoContenitore));
        st2.setPortaNord(new Porta(TipoPorta.oro, st1, true, false));
        st1.setPortaSud(new Porta(TipoPorta.oro, st2, true, false));
        this.stanze.add(st2);
        st2 = new Stanza("cella 3", true, null, null, null, null, Utilita.creaListaOggetti(cibo));
        st1.setPortaNord(new Porta(TipoPorta.oro, st2, true, false));
        st2.setPortaSud(new Porta(TipoPorta.oro, st1, true, false));
        this.stanze.add(st1);
        st1 = new Stanza("stanza sicurezza", false, null, null, null, null, Utilita.creaListaOggetti(affilatore, cibo));
        st1.setPortaOvest(new Porta(TipoPorta.normale, st2, false, false));
        st2.setPortaEst(new Porta(TipoPorta.normale, st1, false, false));
        this.stanze.add(st2);
        st2 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti());
        st1.setPortaEst(new Porta(TipoPorta.oro, st2, true, false));
        st2.setPortaOvest(new Porta(TipoPorta.oro, st1, true, false));
        this.stanze.add(st1);
        st1 = new Stanza("Corridoio", true, null, null, null, null, Utilita.creaListaOggetti(oggettoMaligno));
        st3.setPortaNord(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaSud(new Porta(TipoPorta.normale, st3, false, false));
        this.stanze.add(st3);
        st2.setPortaSud(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaNord(new Porta(TipoPorta.normale, st2, false, false));
        this.stanze.add(st1);
        st3 = new Stanza("stanza guardie", true, null, null, null, null, Utilita.creaListaOggetti());
        st2.setPortaNord(new Porta(TipoPorta.normale, st3, false, false));
        st3.setPortaSud(new Porta(TipoPorta.normale, st2, false, false));
        this.stanze.add(st2);
        st1 = new Stanza("tesoreria", false, null, null, null, null, Utilita.creaListaOggetti(chiavePortaDorata));
        st3.setPortaNord(new Porta(TipoPorta.argento, st1, true, true));
        st1.setPortaSud(new Porta(TipoPorta.argento, st3, true, false));
        this.stanze.add(st3);
        st2 = new Stanza("secondo piano", true, null, null, null, null, Utilita.creaListaOggetti());
        st1.setPortaEst(new Porta(TipoPorta.normale, st2, false, false));
        st2.setPortaOvest(new Porta(TipoPorta.normale, st1, false, false));
        this.stanze.add(st1);
        st3 = new Stanza("segreteria della prigione", true, null, null, null, null, Utilita.creaListaOggetti(spada, cibo));
        st2.setPortaEst(new Porta(TipoPorta.normale, st3, false, false));
        st3.setPortaOvest(new Porta(TipoPorta.normale, st2, false, false));
        this.stanze.add(st2);
        st1 = new Stanza("entrata della prigione", true, null, null, null, null, Utilita.creaListaOggetti());
        st3.setPortaNord(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaSud(new Porta(TipoPorta.normale, st3, false, false));
        this.stanze.add(st3);
        st2 = new Stanza("giardino della prigione", true, null, null, null, null, Utilita.creaListaOggetti());
        st1.setPortaNord(new Porta(TipoPorta.oro, st2, true, false));
        st2.setPortaSud(new Porta(TipoPorta.oro, st1, true, true));
        this.stanze.add(st2);
        this.stanze.add(st1);
    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
    }
}
