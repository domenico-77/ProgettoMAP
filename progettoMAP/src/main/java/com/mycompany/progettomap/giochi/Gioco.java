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
    public void inizializza() {

        //comandi
        Comando nord = new Comando("nord", TipoComando.nord, Utilita.generaSetAlias("n", "su", "sopra", "sù"));

        //aggiungere comando alla lista di comandi di descrizioneGioco?
        Comando sud = new Comando("sud", TipoComando.sud, Utilita.generaSetAlias("s", "giu", "sotto", "giù"));

        Comando est = new Comando("est", TipoComando.est, Utilita.generaSetAlias("e", "destrra"));

        Comando ovest = new Comando("ovest", TipoComando.ovest, Utilita.generaSetAlias("o", "sinistra"));

        Comando fine = new Comando("fine", TipoComando.fine, Utilita.generaSetAlias("end", "termina", "esci", "exit", "chiudi", "muori", "abbandona"));

        Comando inventario = new Comando("inventario", TipoComando.inventario, Utilita.generaSetAlias("zaino", "borsa", "i", "inv", "oggetti"));

        Comando aprire = new Comando("aprire", TipoComando.aprire, Utilita.generaSetAlias("apri", "open"));

        Comando chiudere = new Comando("chiudere", TipoComando.chiudere, Utilita.generaSetAlias("chiudi", "close"));

        Comando spingere = new Comando("spingere", TipoComando.spingere, Utilita.generaSetAlias("spingi", "premi", "push", "attiva"));

        Comando tirare = new Comando("tirare", TipoComando.tirare, Utilita.generaSetAlias("tirare", "pull"));

        Comando camminare_verso = new Comando("camminare_verso", TipoComando.camminare_verso, Utilita.generaSetAlias("vai a", "vai verso", "avvicinatia", "vai"));

        Comando raccogliere = new Comando("raccogliere", TipoComando.raccogliere, Utilita.generaSetAlias("raccogli", "afferra", "prendi", "afferrare", "prendere"));

        Comando parlare_a = new Comando("parlare_a", TipoComando.parlare_a, Utilita.generaSetAlias("parla", "parlagli", "parlale", "chiedi", "domanda", "interagisci"));

        Comando dare = new Comando("dare", TipoComando.dare, Utilita.generaSetAlias("dai", "dagli", "dalle", "dona"));

        Comando usare = new Comando("usare", TipoComando.usare, Utilita.generaSetAlias("usa", "utilizza", "utilizzare"));

        Comando osservare = new Comando("osservare", TipoComando.osservare, Utilita.generaSetAlias("osserva", "guarda", "guardare"));

        Comando accendere = new Comando("accendere", TipoComando.accendere, Utilita.generaSetAlias("accendi"));

        Comando spegnere = new Comando("spegnere", TipoComando.spegnere, Utilita.generaSetAlias("spegni"));

        Comando torna_indietro = new Comando("raccogliere", TipoComando.torna_indietro, Utilita.generaSetAlias("indietreggia", "torna"));
        torna_indietro.setAlias(new String[]{"indietreggia", "torna"});

        //stanze
        Stanza st1, st2, st3;
        Oggetto candela = new Candela("la candela", Utilita.generaSetAlias("candelabro", "cera", "lume", "fiaccola", "torcia", "candela"), Utilita.generaListaComandi(raccogliere, usare, accendere, osservare));
        Oggetto chiaveOggettoContenitore = new ChiaveOggettoContenitore("il grimaldello", Utilita.generaSetAlias("chiave scrigno", "grimardello"), Utilita.generaListaComandi(raccogliere, usare));
        Oggetto chiavePortaDorata = new ChiavePorta("la chiave dorata", Utilita.generaSetAlias(), Utilita.generaListaComandi(), TipoPorta.oro);
        Oggetto chiavePortaArgentata = new ChiavePorta("la chiave d'argento", Utilita.generaSetAlias(), Utilita.generaListaComandi(), TipoPorta.argento);
        Oggetto cibo = new Cibo("del pane", Utilita.generaSetAlias("panino", "cibo", "pane"), Utilita.generaListaComandi(usare, raccogliere), 30);
        Oggetto spada = new Spada("la spada", Utilita.generaSetAlias("spada", "lama", "arma bianca", "daga", "katana", "ferro"), Utilita.generaListaComandi(raccogliere, usare, osservare));
        Oggetto affilatore = new Affilatore("l'affilatore", Utilita.generaSetAlias("affilatoio", "cote", "mola", "affilatrice", "affilatore"), Utilita.generaListaComandi(raccogliere, usare, osservare));
        Oggetto oggettoContenitore;
        Oggetto oggettoMaligno = new OggettoMaligno("del veleno", Utilita.generaSetAlias("veleno"), Utilita.generaListaComandi(), 30);
        //stanza 1
        st1 = new Stanza("cella di Madji", true, null, null, null, null, new ArrayList<>());
        //stanza 2
        st2 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti(candela));
        st1.setPortaNord(new Porta(TipoPorta.normale, st2, false, false));
        st2.setPortaSud(new Porta(TipoPorta.normale, st1, false, false));
        this.stanze.add(st1);
        oggettoContenitore = new OggettoContenitore("uno scrigno", Utilita.generaSetAlias("tesoro", "cofanetto", "cassetta", "scatola", "astuccio", "bauletto", "portagioie", "forziere", "scrigno"), Utilita.generaListaComandi(aprire, chiudere, osservare, usare), Utilita.creaListaOggetti(cibo, oggettoMaligno));
        //stanza 3
        st3 = new Stanza("corridoio", false, null, null, null, null, Utilita.creaListaOggetti(oggettoContenitore));
        st2.setPortaNord(new Porta(TipoPorta.normale, st3, false, false));
        st3.setPortaSud(new Porta(TipoPorta.normale, st2, false, false));
        this.stanze.add(st2);
        //stanza 4
        st1 = new Stanza("bagno", true, null, null, null, null, Utilita.creaListaOggetti(chiaveOggettoContenitore));
        st3.setPortaOvest(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaEst(new Porta(TipoPorta.normale, st3, false, false));
        this.stanze.add(st1);
        //stanza 5
        st2 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti(affilatore, cibo));
        st3.setPortaNord(new Porta(TipoPorta.normale, st2, false, false));
        st2.setPortaSud(new Porta(TipoPorta.normale, st3, false, false));
        this.stanze.add(st3);
        //stanza 6
        st1 = new Stanza("tesoreria", true, null, null, null, null, Utilita.creaListaOggetti(chiavePortaArgentata));
        st2.setPortaEst(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaOvest(new Porta(TipoPorta.normale, st2, false, false));
        this.stanze.add(st1);
        //stanza 7
        st3 = new Stanza("armeria", true, null, null, null, null, Utilita.creaListaOggetti(spada));
        st2.setPortaNord(new Porta(TipoPorta.argento, st3, true, false));
        st3.setPortaSud(new Porta(TipoPorta.argento, st2, true, false));
        this.stanze.add(st2);
        //stanza 8
        st1 = new Stanza("stanza sicurezza", true, null, null, null, null, Utilita.creaListaOggetti());
        st3.setPortaOvest(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaEst(new Porta(TipoPorta.normale, st3, false, false));
        oggettoContenitore = new OggettoContenitore("lo scrigno", Utilita.generaSetAlias("tesoro", "cofanetto", "cassetta", "scatola", "astuccio", "bauletto", "portagioie", "forziere", "scrigno"), Utilita.generaListaComandi(aprire, chiudere, osservare, usare), Utilita.creaListaOggetti(oggettoMaligno, chiaveOggettoContenitore, candela));
        //stanza 9
        st2 = new Stanza("cella 1", true, null, null, null, null, Utilita.creaListaOggetti(oggettoContenitore));
        st1.setPortaOvest(new Porta(TipoPorta.argento, st2, true, false));
        st2.setPortaEst(new Porta(TipoPorta.argento, st1, true, false));
        this.stanze.add(st1);
        //stanza 10
        st1 = new Stanza("cella 2", false, null, null, null, null, Utilita.creaListaOggetti(chiaveOggettoContenitore, chiaveOggettoContenitore));
        st2.setPortaNord(new Porta(TipoPorta.oro, st1, true, false));
        st1.setPortaSud(new Porta(TipoPorta.oro, st2, true, false));
        this.stanze.add(st2);
        //stanza 11
        st2 = new Stanza("cella 3", true, null, null, null, null, Utilita.creaListaOggetti(cibo));
        st1.setPortaNord(new Porta(TipoPorta.oro, st2, true, false));
        st2.setPortaSud(new Porta(TipoPorta.oro, st1, true, false));
        this.stanze.add(st1);
        //stanza 12
        st1 = new Stanza("stanza sicurezza", false, null, null, null, null, Utilita.creaListaOggetti(affilatore, cibo));
        st1.setPortaOvest(new Porta(TipoPorta.normale, st2, false, false));
        st2.setPortaEst(new Porta(TipoPorta.normale, st1, false, false));
        this.stanze.add(st2);
        //stanza 13
        st2 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti());
        st1.setPortaEst(new Porta(TipoPorta.oro, st2, true, false));
        st2.setPortaOvest(new Porta(TipoPorta.oro, st1, true, false));
        this.stanze.add(st1);
        //stanza 14
        st1 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti(oggettoMaligno));
        st3.setPortaNord(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaSud(new Porta(TipoPorta.normale, st3, false, false));
        this.stanze.add(st3);
        st2.setPortaSud(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaNord(new Porta(TipoPorta.normale, st2, false, false));
        this.stanze.add(st1);
        //stanza 15
        st3 = new Stanza("stanza guardie", true, null, null, null, null, Utilita.creaListaOggetti());
        st2.setPortaNord(new Porta(TipoPorta.normale, st3, false, false));
        st3.setPortaSud(new Porta(TipoPorta.normale, st2, false, false));
        this.stanze.add(st2);
        //stanza 16
        st1 = new Stanza("tesoreria", false, null, null, null, null, Utilita.creaListaOggetti(chiavePortaDorata));
        st3.setPortaNord(new Porta(TipoPorta.argento, st1, true, true));
        st1.setPortaSud(new Porta(TipoPorta.argento, st3, true, false));
        this.stanze.add(st3);
        //stanza 17
        st2 = new Stanza("secondo piano", true, null, null, null, null, Utilita.creaListaOggetti());
        st1.setPortaEst(new Porta(TipoPorta.normale, st2, false, false));
        st2.setPortaOvest(new Porta(TipoPorta.normale, st1, false, false));
        this.stanze.add(st1);
        //stanza 18
        st3 = new Stanza("segreteria della prigione", true, null, null, null, null, Utilita.creaListaOggetti(spada, cibo));
        st2.setPortaEst(new Porta(TipoPorta.normale, st3, false, false));
        st3.setPortaOvest(new Porta(TipoPorta.normale, st2, false, false));
        this.stanze.add(st2);
        //stanza 19
        st1 = new Stanza("entrata della prigione", true, null, null, null, null, Utilita.creaListaOggetti());
        st3.setPortaNord(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaSud(new Porta(TipoPorta.normale, st3, false, false));
        this.stanze.add(st3);
        //stanza 20
        st2 = new Stanza("giardino della prigione", true, null, null, null, null, Utilita.creaListaOggetti());
        st1.setPortaNord(new Porta(TipoPorta.oro, st2, true, false));
        st2.setPortaSud(new Porta(TipoPorta.oro, st1, true, true));
        this.stanze.add(st2);
        this.stanze.add(st1);
    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
    }

    @Override
    public void stampaStanze() {
        for (Stanza s : this.stanze) {
            s.DescriviStanza();
        }
    }
}
