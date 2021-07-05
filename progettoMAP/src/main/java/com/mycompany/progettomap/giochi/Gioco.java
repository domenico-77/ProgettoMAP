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
import com.mycompany.progettomap.parser.Parser;
import logicaGioco.DescrizioneGioco;
import com.mycompany.progettomap.parser.ParserOutput;
import tipi.Comando;
import tipi.stanze.Stanza;
import tipi.TipoComando;
import java.io.PrintStream;
import java.util.ArrayList;
import npc.PngIndovinello;
import npc.PngScambio;
import java.util.Scanner;
import npc.Npc;
import oggetti.Affilatore;
import oggetti.Candela;
import oggetti.ChiaveOggettoContenitore;
import oggetti.ChiavePorta;
import oggetti.Cibo;
import oggetti.Oggetto;
import oggetti.OggettoContenitore;
import oggetti.OggettoMaligno;
import oggetti.Spada;
import oggetti.TipoOggetto;
import Threads.ThreadTempo;
import tipi.stanze.Porta;
import tipi.stanze.TipoPorta;
import tipi.Utilita;

/**
 *
 * @author Acer
 */
public class Gioco extends DescrizioneGioco {

    private static final Oggetto candela = new Candela("candela", Utilita.generaSetAlias("candelabro", "cera", "lume", "fiaccola", "torcia", "candela"));
    private static final Oggetto chiaveOggettoContenitore = new ChiaveOggettoContenitore("grimaldello", Utilita.generaSetAlias("grimaldello"));
    private static final Oggetto chiavePortaDorata = new ChiavePorta("chiave d'oro", Utilita.generaSetAlias("chiave", "chiave oro"), TipoPorta.oro);
    private static final Oggetto chiavePortaArgentata = new ChiavePorta("chiave argento", Utilita.generaSetAlias("chiave", "chiave argento"), TipoPorta.argento);
    private static final Oggetto cibo = new Cibo("pane", Utilita.generaSetAlias("panino", "cibo", "pane"), 30);
    private static final Oggetto oggettoContenitore = new OggettoContenitore("scrigno", Utilita.generaSetAlias("contenitore", "scrigno", "baule"), Utilita.creaListaOggetti());
    private static final Oggetto oggettoMaligno = new OggettoMaligno("veleno", Utilita.generaSetAlias(), 30);
    private int secondi = 0;
    private int minuti = 0;
    private int ore = 0;

    static final int MAX_SEC = 60;
    static final int MAX_MIN = 60;

    @Override
    public void inizializza() {

        //comandi
        Comando nord = new Comando("nord", TipoComando.nord, Utilita.generaSetAlias("su", "sopra", "sù", "nord"));

        //aggiungere comando alla lista di comandi di descrizioneGioco?
        Comando sud = new Comando("sud", TipoComando.sud, Utilita.generaSetAlias("giu", "sotto", "giù", "sud"));

        Comando est = new Comando("est", TipoComando.est, Utilita.generaSetAlias("destra", "est"));

        Comando ovest = new Comando("ovest", TipoComando.ovest, Utilita.generaSetAlias("sinistra", "ovest"));

        Comando fine = new Comando("fine", TipoComando.fine, Utilita.generaSetAlias("end", "termina", "esci", "exit", "chiudi", "muori", "abbandona", "fine"));

        Comando inventario = new Comando("inventario", TipoComando.inventario, Utilita.generaSetAlias("zaino", "borsa", "i", "inv", "oggetti", "inventario"));

        Comando aprire = new Comando("aprire", TipoComando.aprire, Utilita.generaSetAlias("apri", "open", "aprire"));

        Comando chiudere = new Comando("chiudere", TipoComando.chiudere, Utilita.generaSetAlias("chiudi", "close", "chiudere"));

        Comando spingere = new Comando("spingere", TipoComando.spingere, Utilita.generaSetAlias("spingi", "premi", "push", "attiva", "spingere"));

        Comando tirare = new Comando("tirare", TipoComando.tirare, Utilita.generaSetAlias("tirare", "pull", "tirare"));

        Comando camminare_verso = new Comando("camminare_verso", TipoComando.camminare_verso, Utilita.generaSetAlias("vai", "andare", "andiamo", "vado"));

        Comando raccogliere = new Comando("raccogliere", TipoComando.raccogliere, Utilita.generaSetAlias("raccogli", "afferra", "prendi", "afferrare", "prendere", "raccogliere"));

        Comando parlare_a = new Comando("parlare_a", TipoComando.parlare_a, Utilita.generaSetAlias("parla", "parlagli", "parlale", "chiedi", "domanda", "interagisci", "parlare_a"));

        Comando dare = new Comando("dare", TipoComando.dare, Utilita.generaSetAlias("dai", "dagli", "dalle", "dona", "dare"));

        Comando usare = new Comando("usare", TipoComando.usare, Utilita.generaSetAlias("usa", "utilizza", "utilizzare", "usare"));

        Comando mangiare = new Comando("mangiare", TipoComando.mangiare, Utilita.generaSetAlias("mangiare", "mangia", "mordi", "assaggia", "consuma"));

        Comando osservare = new Comando("osservare", TipoComando.osservare, Utilita.generaSetAlias("osserva", "guarda", "guardare", "osservare"));

        Comando accendere = new Comando("accendere", TipoComando.accendere, Utilita.generaSetAlias("accendi", "accendere"));

        Comando spegnere = new Comando("spegnere", TipoComando.spegnere, Utilita.generaSetAlias("spegni", "spegnere"));

        Comando torna_indietro = new Comando("torna_indietro", TipoComando.torna_indietro, Utilita.generaSetAlias("indietreggia", "torna", "indietro"));

        Comando tempo = new Comando("tempo", TipoComando.tempo, Utilita.generaSetAlias("tempo", "time", "t"));
        
        Comando interagire = new Comando("interagisre", TipoComando.interagire, Utilita.generaSetAlias("parla", "comunica", "interagisci","parlare","comunicare","interagine"));

        //stanze
        Stanza st1, st2, st3;
        Oggetto spada = new Spada("la spada", Utilita.generaSetAlias("spada", "lama", "arma bianca", "daga", "katana", "ferro"));
        Oggetto affilatore = new Affilatore("l'affilatore", Utilita.generaSetAlias("affilatoio", "cote", "mola", "affilatrice", "affilatore"));
        Oggetto oggettoContenitore;
        //stanza 1
        st1 = new Stanza("cella di Manji", true, null, null, null, null, new ArrayList<>(), null);
        //stanza 2
        st2 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti(this.candela), null);
        st1.setPortaNord(new Porta(TipoPorta.normale, st2, false, false));
        st2.setPortaSud(new Porta(TipoPorta.normale, st1, false, false));
        this.stanze.add(st1);
        oggettoContenitore = new OggettoContenitore("uno scrigno", Utilita.generaSetAlias("tesoro", "cofanetto", "cassetta", "scatola", "astuccio", "bauletto", "portagioie", "forziere", "scrigno"), Utilita.creaListaOggetti(oggettoMaligno, cibo));
        //stanza 3
        st3 = new Stanza("corridoio", false, null, null, null, null, Utilita.creaListaOggetti(oggettoContenitore), null);
        st2.setPortaNord(new Porta(TipoPorta.normale, st3, false, false));
        st3.setPortaSud(new Porta(TipoPorta.normale, st2, false, false));
        this.stanze.add(st2);
        //stanza 4
        st1 = new Stanza("bagno", true, null, null, null, null, Utilita.creaListaOggetti(Gioco.chiaveOggettoContenitore), new PngScambio("Sanji", Gioco.cibo, Gioco.candela));
        st3.setPortaOvest(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaEst(new Porta(TipoPorta.normale, st3, false, false));
        this.stanze.add(st1);
        //stanza 5
        st2 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti(affilatore, this.cibo), null);
        st3.setPortaNord(new Porta(TipoPorta.normale, st2, false, false));
        st2.setPortaSud(new Porta(TipoPorta.normale, st3, false, false));
        this.stanze.add(st3);
        //stanza 6
        st1 = new Stanza("tesoreria", true, null, null, null, null, Utilita.creaListaOggetti(), new PngIndovinello("Alphonse", Gioco.chiavePortaArgentata, "Spesso racconto una storia, ma non chiedo alcun soldo. Ti intrattengo tutta la notte, ma ahime', non ti ricorderai di me. Cosa sono?", "un sogno", "un libro", "una musica", "a"));
        st2.setPortaEst(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaOvest(new Porta(TipoPorta.normale, st2, false, false));
        //stanza 7
        st3 = new Stanza("armeria", true, null, null, null, null, Utilita.creaListaOggetti(spada), null);
        st2.setPortaNord(new Porta(TipoPorta.argento, st3, true, false));
        st3.setPortaSud(new Porta(TipoPorta.argento, st2, true, false));
        this.stanze.add(st2);
        this.stanze.add(st1);
        //stanza 8
        st1 = new Stanza("stanza sicurezza", true, null, null, null, null, Utilita.creaListaOggetti(), null);
        st3.setPortaOvest(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaEst(new Porta(TipoPorta.normale, st3, false, false));
        oggettoContenitore = new OggettoContenitore("lo scrigno", Utilita.generaSetAlias("tesoro", "cofanetto", "cassetta", "scatola", "astuccio", "bauletto", "portagioie", "forziere", "scrigno"), Utilita.creaListaOggetti(oggettoMaligno, chiaveOggettoContenitore, candela));
        //stanza 9
        st2 = new Stanza("cella 1", true, null, null, null, null, Utilita.creaListaOggetti(oggettoContenitore), new PngScambio("Killer bee", affilatore, Gioco.cibo));
        st1.setPortaOvest(new Porta(TipoPorta.argento, st2, true, false));
        st2.setPortaEst(new Porta(TipoPorta.argento, st1, true, false));
        this.stanze.add(st1);
        //stanza 10
        st1 = new Stanza("cella 2", false, null, null, null, null, Utilita.creaListaOggetti(this.chiaveOggettoContenitore, this.chiaveOggettoContenitore), null);
        st2.setPortaNord(new Porta(TipoPorta.oro, st1, true, false));
        st1.setPortaSud(new Porta(TipoPorta.oro, st2, true, false));
        this.stanze.add(st2);
        //stanza 11
        st2 = new Stanza("cella 3", true, null, null, null, null, Utilita.creaListaOggetti(this.cibo), new PngIndovinello("Roy Mustung", Gioco.chiaveOggettoContenitore, "Raramente mi toccano, ma spesso mi frenano. Se hai arguzia, mi userai bene, cosa sono?", "intelligenza", "la lingua", "una spada", "b"));
        st1.setPortaNord(new Porta(TipoPorta.oro, st2, true, false));
        st2.setPortaSud(new Porta(TipoPorta.oro, st1, true, false));
        this.stanze.add(st1);
        //stanza 12
        st1 = new Stanza("stanza sicurezza", false, null, null, null, null, Utilita.creaListaOggetti(affilatore, this.cibo), null);
        st1.setPortaOvest(new Porta(TipoPorta.normale, st2, false, false));
        st2.setPortaEst(new Porta(TipoPorta.normale, st1, false, false));
        this.stanze.add(st2);
        //stanza 13
        st2 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti(), null);
        st1.setPortaEst(new Porta(TipoPorta.oro, st2, true, false));
        st2.setPortaOvest(new Porta(TipoPorta.oro, st1, true, false));
        this.stanze.add(st1);
        //stanza 14
        st1 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti(oggettoMaligno), null);
        st3.setPortaNord(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaSud(new Porta(TipoPorta.normale, st3, false, false));
        this.stanze.add(st3);
        st2.setPortaSud(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaNord(new Porta(TipoPorta.normale, st2, false, false));
        this.stanze.add(st1);
        //stanza 15
        st3 = new Stanza("stanza guardie", true, null, null, null, null, Utilita.creaListaOggetti(), null);
        st2.setPortaNord(new Porta(TipoPorta.normale, st3, false, false));
        st3.setPortaSud(new Porta(TipoPorta.normale, st2, false, false));
        this.stanze.add(st2);
        //stanza 16
        st1 = new Stanza("tesoreria", false, null, null, null, null, Utilita.creaListaOggetti(this.chiavePortaDorata), new PngIndovinello("Kise", Gioco.chiavePortaDorata, "Ho mari senza acqua, ho coste senza sabbia, villaggi senza persone e montagne senza terra, cosa sono?", "un deserto", "la luna", "una mappa", "c"));
        st3.setPortaNord(new Porta(TipoPorta.argento, st1, true, true));
        st1.setPortaSud(new Porta(TipoPorta.argento, st3, true, false));
        this.stanze.add(st3);
        //stanza 17
        st2 = new Stanza("secondo piano", true, null, null, null, null, Utilita.creaListaOggetti(), null);
        st1.setPortaEst(new Porta(TipoPorta.normale, st2, false, false));
        st2.setPortaOvest(new Porta(TipoPorta.normale, st1, false, false));
        this.stanze.add(st1);
        //stanza 18
        st3 = new Stanza("segreteria della prigione", true, null, null, null, null, Utilita.creaListaOggetti(spada, this.cibo), null);
        st2.setPortaEst(new Porta(TipoPorta.normale, st3, false, false));
        st3.setPortaOvest(new Porta(TipoPorta.normale, st2, false, false));
        this.stanze.add(st2);
        //stanza 19
        st1 = new Stanza("entrata della prigione", true, null, null, null, null, Utilita.creaListaOggetti(), null);
        st3.setPortaNord(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaSud(new Porta(TipoPorta.normale, st3, false, false));
        this.stanze.add(st3);
        //stanza 20
        st2 = new Stanza("giardino della prigione", true, null, null, null, null, Utilita.creaListaOggetti(), null);
        st1.setPortaNord(new Porta(TipoPorta.oro, st2, true, false));
        st2.setPortaSud(new Porta(TipoPorta.oro, st1, true, true));
        this.stanze.add(st2);
        this.stanze.add(st1);

        this.stanzaCorrente = this.stanze.get(0);

        this.giocatore.aggiornaMosse(Utilita.generaListaComandi(nord, sud, ovest, est, inventario, osservare, raccogliere, torna_indietro, usare, aprire, accendere, mangiare, camminare_verso, tempo, interagire));

    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        if (p != null) {
            if ((p.getComando().getTipo() == TipoComando.nord && p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false)) {
                this.spostamento(this.stanzaCorrente.getPortaNord(), out);
            } else if ((p.getComando().getTipo() == TipoComando.sud && p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false)) {
                this.spostamento(this.stanzaCorrente.getPortaSud(), out);
            } else if ((p.getComando().getTipo() == TipoComando.est && p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false)) {
                this.spostamento(this.stanzaCorrente.getPortaEst(), out);
            } else if ((p.getComando().getTipo() == TipoComando.ovest && p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false)) {
                this.spostamento(this.stanzaCorrente.getPortaOvest(), out);
            } else if ((p.getComando().getTipo() == TipoComando.torna_indietro && p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false)) {
                if (!this.PercorsoStanze.empty()) {
                    this.setStanzaCorrente(this.PercorsoStanze.pop());
                    System.out.println("Sei entrato nella stanza : " + this.stanzaCorrente.getNomeStanza());
                } else {
                    System.out.println("Rin : 'Non ho capito dove vuoi tornare. '");
                }
            } else if ((p.getComando().getTipo() == TipoComando.aprire)) {
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() != null && p.isNpc() == false) {
                    if (p.getPorta().getStanza().equals(this.stanzaCorrente.getPortaNord().getStanza())) {

                        apriPorta(this.stanzaCorrente.getPortaNord(), out);
                    } else if (p.getPorta().getStanza().equals(this.stanzaCorrente.getPortaSud().getStanza())) {

                        apriPorta(this.stanzaCorrente.getPortaSud(), out);
                    } else if (p.getPorta().getStanza().equals(this.stanzaCorrente.getPortaEst().getStanza())) {

                        apriPorta(this.stanzaCorrente.getPortaEst(), out);
                    } else if (p.getPorta().getStanza().equals(this.stanzaCorrente.getPortaOvest().getStanza())) {

                        apriPorta(this.stanzaCorrente.getPortaOvest(), out);
                    } else {
                        out.println("Rin: 'Non ho capito cosa fare, ripeti");
                    }
                } else if (p.getOggetto().getTipo() != null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggetto().equals(Gioco.oggettoContenitore)) {
                        this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(p.getOggetto())).usa(giocatore, stanzaCorrente);
                    }

                } else if (p.getOggetto() != null && p.getOggettoInv().getTipo() != null && p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggetto().equals(Gioco.oggettoContenitore) && p.getOggettoInv().equals(Gioco.chiaveOggettoContenitore)) {
                        this.giocatore.getInventario().usaOggetto(Gioco.chiaveOggettoContenitore, giocatore, stanzaCorrente);
                        this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(Gioco.oggettoContenitore)).usa(giocatore, stanzaCorrente);
                    }
                } else {
                    System.out.println("Rin : 'Non ho capito cosa vuoi aprire'");
                }
            } else if (p.getComando().getTipo() == TipoComando.inventario) {
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    this.giocatore.getInventario().visualizzaInventario();
                } else {
                    out.println("Rin: 'Non ho capito cosa fare'");
                }
            } else if (p.getComando().getTipo() == TipoComando.osservare) {

                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    this.stanzaCorrente.DescriviStanza();
                } else if (p.getOggetto() != null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    if (this.stanzaCorrente.isIlluminata()) {
                        p.getOggetto().descrizioneOggetto();
                    } else {
                        out.println("Rin: 'Non riesco a vedere nulla, sarebbe il caso di illuminare la stanza prima di fare qualcosa'");
                    }
                } else if (p.getOggetto() == null && p.getOggettoInv() != null && p.getPorta() == null && p.isNpc() == false) {
                    p.getOggettoInv().descrizioneOggetto();
                } else {
                    out.println("Rin: 'Non ho capito cosa fare'");
                }
            } else if (p.getComando().getTipo() == TipoComando.raccogliere) {
                if (p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggetto() != null) {
                        if (this.stanzaCorrente.isIlluminata()) {
                            if (p.getOggetto().isPrendibile()) {
                                this.giocatore.getInventario().aggiungiOggetto(p.getOggetto());
                                out.println("Rin: 'Hai raccolto: " + p.getOggetto().getNome() + "'");
                                this.stanzaCorrente.rimuoviOggetto(p.getOggetto());

                            } else {
                                out.println("Rin: 'Non puoi raccogliere questo oggetto'");
                            }
                        } else {
                            out.println("Rin: 'Non riesco a vedere nulla, sarebbe il caso di illuminare la stanza prima di fare qualcosa'");
                        }
                    } else {
                        out.println("Rin: 'Non ho capito cosa raccogliere'");
                    }
                } else {
                    out.println("Rin: 'Non ho capito cosa raccogliere'");
                }

            } else if (p.getComando().getTipo() == TipoComando.usare) {
                if (p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggetto() != null && p.getOggettoInv() == null) {
                        if (this.stanzaCorrente.isIlluminata()) {
                            this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(p.getOggetto())).usa(giocatore, stanzaCorrente);
                            if (this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(p.getOggetto())).getUsabilita() <= 0) {
                                this.stanzaCorrente.getOggetiStanza().remove(p.getOggetto());
                                out.println("Rin: 'L'oggetto ha finito la sua usabilita'");
                            }
                        } else {
                            out.println("Rin: 'Non riesco a vedere nulla, sarebbe il caso di illuminare la stanza prima di fare qualcosa'");
                        }
                    } else if (p.getOggetto() == null && p.getOggettoInv() != null) {
                        this.giocatore.getInventario().usaOggetto(p.getOggettoInv(), giocatore, stanzaCorrente);
                    } else {
                        out.println("Rin: 'Non ho capito cosa fare'");
                    }
                } else {
                    out.println("Rin: 'Non ho capito cosa fare'");
                }
            } else if (p.getComando().getTipo() == TipoComando.tempo) {
                this.calcolaTempo();
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    calcolaTempo();
                    if (secondi < 10 && minuti < 10 && ore < 10) {
                        System.out.println("Tempo passato: 0" + ore + ":0" + minuti + ":0" + secondi);
                    } else if (secondi >= 10 && minuti < 10 && ore < 10) {
                        System.out.println("Tempo passato: 0" + ore + ":0" + minuti + ":" + secondi);
                    } else if (secondi < 10 && minuti >= 10 && ore < 10) {
                        System.out.println("Tempo passato: 0" + ore + ":" + minuti + ":0" + secondi);
                    } else if (secondi < 10 && minuti < 10 && ore >= 10) {
                        System.out.println("Tempo passato: 0" + ore + ":0" + minuti + ":" + secondi);
                    } else if (secondi >= 10 && minuti >= 10 && ore < 10) {
                        System.out.println("Tempo passato: " + ore + ":" + minuti + ":0" + secondi);
                    } else if (secondi < 10 && minuti >= 10 && ore >= 10) {
                        System.out.println("Tempo passato: 0" + ore + ":" + minuti + ":" + secondi);
                    } else if (secondi >= 10 && minuti < 10 && ore >= 10) {
                        System.out.println("Tempo passato: " + ore + ":0" + minuti + ":" + secondi);
                    } else {
                        System.out.println("Tempo passato: " + ore + ":" + minuti + ":" + secondi);
                    }
                }

            } else if (p.getComando().getTipo() == TipoComando.accendere) {
                if (p.getOggetto() == null && p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggettoInv() != null) {
                        if (p.getOggettoInv().equals(Gioco.candela)) {
                            if (this.giocatore.getInventario().contieneOggetto(Gioco.candela)) {
                                this.giocatore.getInventario().usaOggetto(Gioco.candela, giocatore, stanzaCorrente);
                            } else {
                                out.println("Rin: 'Non hai una candela da accendere'");
                            }
                        } else if (this.giocatore.getInventario().contieneOggetto(Gioco.candela)) {
                            if (Utilita.chiediConferma("Rin: 'Intendevi candela, vuoi accenderla?'", "Rin: 'Proviamo ad accenderla'", "Rin: 'Ricordati che non puoi accendere altri oggetti, che non siano candela'")) {
                                this.giocatore.getInventario().usaOggetto(Gioco.candela, giocatore, stanzaCorrente);
                            }
                        } else {
                            out.println("Rin: 'Non puoi accendere niete, apparte una candela che non abbiamo'");
                        }
                    } else {
                        if (this.giocatore.getInventario().contieneOggetto(Gioco.candela)) {
                            if (Utilita.chiediConferma("Rin: 'Intendevi candela, vuoi accenderla?'", "Rin: 'Proviamo ad accenderla'", "Rin: 'Ricordati che non puoi accendere altri oggetti, che non siano candela'")) {
                                this.giocatore.getInventario().usaOggetto(Gioco.candela, giocatore, stanzaCorrente);
                            }
                        } else {
                            out.println("Rin: 'Non puoi accendere niete, apparte una candela che non abbiamo'");
                        }
                    }

                } else {
                    out.println("Rin: 'non ho capito cosa fare'");
                }
            }
            else if(p.getComando().getTipo() == TipoComando.mangiare){
                if(p.getOggettoInv() != null && p.getOggetto() == null && p.getPorta() == null && p.isNpc() == false){
                    if(p.getOggettoInv().getTipo() == TipoOggetto.cibo){
                        this.giocatore.getInventario().usaOggetto(p.getOggettoInv(), giocatore, stanzaCorrente);
                    }
                    else{
                        if(this.giocatore.getInventario().contieneOggetto(Gioco.cibo)){
                            if(Utilita.chiediConferma("Rin: 'Intendevi pane, vuoi mangiarlo?", "Rin: 'Va bene, mangiamolo'", "Rin: 'Ricordati che non puoi mangiare altri oggetti, che non sono cibo")){
                                this.giocatore.getInventario().usaOggetto(Gioco.cibo, giocatore, stanzaCorrente);
                            }
                        }
                        else{
                            out.println("Rin: 'Non abbiamo niente da mangiare, moriremo di fame!!");
                        }
                    }
                }
                else if(p.getOggettoInv() == null && p.getOggetto() != null && p.getPorta() == null && p.isNpc() == false){
                    if(p.getOggetto().getTipo() == TipoOggetto.cibo){
                        this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(Gioco.cibo)).usa(giocatore, stanzaCorrente);
                    }
                    else{
                        if(this.stanzaCorrente.getOggetiStanza().contains(Gioco.cibo)){
                            if(Utilita.chiediConferma("Rin: 'Intendevi pane, vuoi mangiarlo?", "Rin: 'Va bene, mangiamolo'", "Rin: 'Almeno raccogliamolo, non si butta mai il cibo")){
                                this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(Gioco.cibo)).usa(giocatore, stanzaCorrente);
                            }
                        }
                        else{
                            out.println("Rin: 'Non abbiamo niente da mangiare, moriremo di fame!!");
                        }
                    }
                }
                else{
                    System.out.println("Rin: 'Non ho capito cosa mangiare'");
                }
            }
            else if(p.getComando().getTipo() == TipoComando.interagire){
                if(p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null){
                    if(p.isNpc() == true && this.stanzaCorrente.getNpc() != null){
                        this.stanzaCorrente.getNpc().interagisci(giocatore);
                    }
                }
                else{
                    out.println("Rin: 'non ho capito cosa devo fare'");
                }
            }
            
            else {
                out.println("Rin: 'non ho capito cosa fare'");
            }

        } else {
            System.out.println("Rin: 'Non ho capito cosa devo fare, prova a esprimerti meglio'");
        }
    }

    public void calcolaTempo() {
        int tempoS = ThreadTempo.getSecondi();
        int tempoM = ThreadTempo.getMinuti();
        int tempoO = ThreadTempo.getOre();
        if (this.secondi + tempoS >= MAX_SEC) {
            this.secondi = (this.secondi + tempoS) - MAX_SEC;
            this.minuti++;
        } else {
            this.secondi += tempoS;
        }
        if (this.minuti + tempoM >= MAX_MIN) {
            this.minuti = (this.secondi + tempoM) - MAX_MIN;
            ore++;
        } else {
            this.minuti += tempoM;
        }
        this.ore += tempoO;
        ThreadTempo.reset();
    }

    @Override
    public void stampaStanze() {
        for (Stanza s : this.stanze) {
            s.DescriviStanza();
        }
    }

    @Override
    public void gioca() {
        this.inizializza();
        Parser parser = new Parser(Utilita.caricaFileSet("./risorse/articoli.txt"));
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parse(command, this.giocatore.getListaMosse(), this.stanzaCorrente.getOggetiStanza(), this.giocatore.getInventario().getInventario(), stanzaCorrente, Npc.getAlias());
            this.nextMove(p, System.out);
        }

    }

    public void cambiaStanza(Porta porta, PrintStream out) {
        this.PercorsoStanze.push(stanzaCorrente);
        porta.setChiusa(false);
        this.setStanzaCorrente(porta.getStanza());
        out.println("Rin: 'Siamo nella stanza: " + this.stanzaCorrente.getNomeStanza() + "'");
    }

    public void spostamento(Porta porta, PrintStream out) {
        if (this.stanzaCorrente.isIlluminata()) {
            if (porta != null) {
                if (porta.getTipo() == TipoPorta.normale || !porta.isChiusa()) {
                    this.cambiaStanza(porta, out);
                } else if (porta.getTipo() == TipoPorta.argento) {
                    out.print(" Rin : '");
                    out.println(porta.descriviPorta() + ".");

                    if (this.getGiocatore().getInventario().contieneOggetto(Gioco.chiavePortaArgentata)) {
                        if (Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?'", "Rin : 'Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta'", "Rin : 'Va bene vorrà dire che l' apriremo in un altro momento'")) {
                            this.cambiaStanza(porta, out);

                        }
                    } else {
                        out.println("al momento non abbiamo la chiave argentata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                    }
                } else if (porta.getTipo() == TipoPorta.oro) {
                    out.print(" Rin : '");
                    out.println(porta.descriviPorta() + ".");

                    if (this.getGiocatore().getInventario().contieneOggetto(Gioco.chiavePortaDorata)) {
                        if (Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?'", "Rin : 'Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta'", "Rin : 'Va bene vorrà dire che l' apriremo in un altro momento'")) {
                            this.cambiaStanza(porta, out);

                        }
                    } else {
                        out.println("al momento non abbiamo la chiave argentata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                    }
                }
            } else {
                out.println("Rin: 'Non possiamo andare da quella direzione, perche non c'e' alcuna porta, non possiamo oltrepassare i muri'");
            }
        } else {
            out.println("La stanza e' buia, prima di fare qualsiasi cosa sarebbe meglio illuminare la stanza'");
        }
    }

    public void apriPorta(Porta porta, PrintStream out) {
        if (porta.isChiusa()) {
            if (porta.getTipo() == TipoPorta.normale) {
                out.println("Rin: hai aperto la porta");
                porta.setChiusa(false);
            } else if (porta.getTipo() == TipoPorta.argento) {
                if (this.getGiocatore().getInventario().contieneOggetto(Gioco.chiavePortaArgentata)) {
                    if (Utilita.chiediConferma("Fortunatamente abbiamo l'oggetto necessario per aprirla, vuoi usarlo?'", "Rin : 'Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta'", "Rin : 'Va bene vorrà dire che l' apriremo in un altro momento'")) {
                        this.giocatore.getInventario().usaOggetto(Gioco.chiavePortaArgentata, giocatore, stanzaCorrente);
                    }
                } else {
                    out.println("Rin: 'Non possiamo aprire questa porta, ci servirebbe un determinato oggetto per aprirlo'");
                }
            } else if (porta.getTipo() == TipoPorta.oro) {
                if (this.getGiocatore().getInventario().contieneOggetto(Gioco.chiavePortaDorata)) {
                    if (Utilita.chiediConferma("Fortunatamente abbiamo l'oggetto necessario per aprirla, vuoi usarlo?'", "Rin : 'Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta'", "Rin : 'Va bene vorrà dire che l' apriremo in un altro momento'")) {
                        this.giocatore.getInventario().usaOggetto(Gioco.chiavePortaDorata, giocatore, stanzaCorrente);
                    }
                } else {
                    out.println("Rin: 'Non possiamo aprire questa porta, ci servirebbe un determinato oggetto per aprirlo'");
                }
            }
        } else {
            out.println("Rin: 'La porta e' gia' aperta'");
        }
    }
}