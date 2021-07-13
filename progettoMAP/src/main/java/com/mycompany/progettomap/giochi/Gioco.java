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
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import menu.Help;
import npc.Mob;
import salvataggio.Deserializzazione;
import tipi.Giocatore;
import tipi.stanze.Porta;
import tipi.stanze.TipoPorta;
import tipi.Utilita;

/**
 *
 * @author Acer
 */
public class Gioco extends DescrizioneGioco {

    private static final Oggetto CANDELA = new Candela("la candela", Utilita.generaSetAlias("candelabro", "cera", "lume", "fiaccola", "torcia", "candela"));
    private static final Oggetto CHIAVE_OGGETTO_CONTENITORE = new ChiaveOggettoContenitore("grimaldello", Utilita.generaSetAlias("grimaldello"));
    private static final Oggetto TOTEM = new ChiavePorta("il totem", Utilita.generaSetAlias("totem", "statuetta", "artefatto"), TipoPorta.oro, ChiavePorta.getTIPO_OGGETTO2());
    private static final Oggetto CHIAVE = new ChiavePorta("la chiave", Utilita.generaSetAlias("chiave"), TipoPorta.argento, ChiavePorta.getTIPO_OGGETTO1());
    private static final Oggetto CIBO = new Cibo("il pane", Utilita.generaSetAlias("panino", "cibo", "pane"), 30);
    private static final Oggetto OGGETTO_CONTENITORE = new OggettoContenitore("lo scrigno", Utilita.generaSetAlias("contenitore", "scrigno", "baule"), Utilita.creaListaOggetti());
    private static final Oggetto OGGETTO_MALIGNO = new OggettoMaligno("del veleno", Utilita.generaSetAlias(), 30);
    private static final Oggetto SPADA = new Spada("la spada", Utilita.generaSetAlias("spada", "lama", "arma bianca", "daga", "katana", "ferro"));
    private static final Oggetto AFFILATORE = new Affilatore("l'affilatore", Utilita.generaSetAlias("affilatoio", "cote", "mola", "affilatrice", "affilatore"));

    private int secondi = 0;
    private int minuti = 0;
    private int ore = 0;

    static final int MAX_SEC = 60;
    static final int MAX_MIN = 60;

    public Gioco(String nomeGiocatore, int id) {
        super(nomeGiocatore, id);

    }

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

        Comando camminare_verso = new Comando("camminare_verso", TipoComando.camminare_verso, Utilita.generaSetAlias("vai", "andare", "andiamo", "vado"));

        Comando raccogliere = new Comando("raccogliere", TipoComando.raccogliere, Utilita.generaSetAlias("raccogli", "afferra", "prendi", "afferrare", "prendere", "raccogliere"));

        Comando usare = new Comando("usare", TipoComando.usare, Utilita.generaSetAlias("usa", "utilizza", "utilizzare", "usare"));

        Comando mangiare = new Comando("mangiare", TipoComando.mangiare, Utilita.generaSetAlias("mangiare", "mangia", "mordi", "assaggia", "consuma"));

        Comando osservare = new Comando("osservare", TipoComando.osservare, Utilita.generaSetAlias("osserva", "guarda", "guardare", "osservare"));

        Comando accendere = new Comando("accendere", TipoComando.accendere, Utilita.generaSetAlias("accendi", "accendere"));

        Comando torna_indietro = new Comando("torna_indietro", TipoComando.torna_indietro, Utilita.generaSetAlias("indietreggia", "torna", "indietro"));

        Comando tempo = new Comando("tempo", TipoComando.tempo, Utilita.generaSetAlias("tempo", "time", "t"));

        Comando help = new Comando("help", TipoComando.help, Utilita.generaSetAlias("help", "h", "aiuto"));

        Comando salva = new Comando("salva", TipoComando.salva, Utilita.generaSetAlias("salva", "salvataggio", "save", "s"));

        Comando interagire = new Comando("interagisre", TipoComando.interagire, Utilita.generaSetAlias("parla", "comunica", "interagisci", "parlare", "comunicare", "interagine"));

        Comando affilare = new Comando("affilare", TipoComando.affilare, Utilita.generaSetAlias("affila", "affilare", "affinare", "aguzzare"));

        Comando salute = new Comando("salute", TipoComando.salute, Utilita.generaSetAlias("salute", "vita", "energia", "vitalita"));

        Comando uccidere = new Comando("uccidere", TipoComando.uccidere, Utilita.generaSetAlias("uccidere", "uccidi", "assassina", "assassinare", "accoltella", "ammazza", "accoltellare", "ammazzare", "colpisci", "colpire"));

        //stanze
        Stanza st1, st2, st3;
        Oggetto oggettoContenitore;
        //stanza 1
        st1 = new Stanza("cella di Manji", true, null, null, null, null, new ArrayList<>(), null);
        //stanza 2
        st2 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti(this.creaCandela()), new Mob("Max", this.creaCibo(), false));
        st1.setPortaNord(new Porta(TipoPorta.normale, st2, false));
        st2.setPortaSud(new Porta(TipoPorta.normale, st1, false));
        this.stanze.add(st1);
        oggettoContenitore = new OggettoContenitore("uno scrigno", Utilita.generaSetAlias("tesoro", "cofanetto", "cassetta", "scatola", "astuccio", "bauletto", "portagioie", "forziere", "scrigno"), Utilita.creaListaOggetti(this.creaOggettoMaligno(), this.creaCibo()));
        //stanza 3
        st3 = new Stanza("corridoio", false, null, null, null, null, Utilita.creaListaOggetti(oggettoContenitore), null);
        st2.setPortaNord(new Porta(TipoPorta.normale, st3, false));
        st3.setPortaSud(new Porta(TipoPorta.normale, st2, false));
        this.stanze.add(st2);
        //stanza 4
        st1 = new Stanza("bagno", true, null, null, null, null, Utilita.creaListaOggetti(this.creaGrimaldello()), new PngScambio("Sanji", this.creaCibo(), Gioco.CANDELA));
        st3.setPortaOvest(new Porta(TipoPorta.normale, st1, false));
        st1.setPortaEst(new Porta(TipoPorta.normale, st3, false));
        this.stanze.add(st1);
        //stanza 5
        st2 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti(this.creaAffilatore(), this.creaCibo()), null);
        st3.setPortaNord(new Porta(TipoPorta.normale, st2, false));
        st2.setPortaSud(new Porta(TipoPorta.normale, st3, false));
        this.stanze.add(st3);
        //stanza 6
        st1 = new Stanza("tesoreria", true, null, null, null, null, Utilita.creaListaOggetti(), new PngIndovinello("Alphonse", this.creaChiave(), "Spesso racconto una storia, ma non chiedo alcun soldo. Ti intrattengo tutta la notte, ma ahime', non ti ricorderai di me. Cosa sono?", "un sogno", "un libro", "una musica", "a"));
        st2.setPortaEst(new Porta(TipoPorta.normale, st1, false));
        st1.setPortaOvest(new Porta(TipoPorta.normale, st2, false));
        //stanza 7
        st3 = new Stanza("armeria", true, null, null, null, null, Utilita.creaListaOggetti(this.creaSpada()), null);
        st2.setPortaNord(new Porta(TipoPorta.argento, st3, true));
        st3.setPortaSud(new Porta(TipoPorta.argento, st2, true));
        this.stanze.add(st2);
        this.stanze.add(st1);
        //stanza 8
        st1 = new Stanza("stanza sicurezza", true, null, null, null, null, Utilita.creaListaOggetti(), new Mob("Francesco", this.creaCibo(), true));
        st3.setPortaOvest(new Porta(TipoPorta.normale, st1, false));
        st1.setPortaEst(new Porta(TipoPorta.normale, st3, false));
        oggettoContenitore = new OggettoContenitore("lo scrigno", Utilita.generaSetAlias("tesoro", "cofanetto", "cassetta", "scatola", "astuccio", "bauletto", "portagioie", "forziere", "scrigno"), Utilita.creaListaOggetti(this.creaOggettoMaligno(), this.creaGrimaldello(), this.creaCandela()));
        //stanza 9
        st2 = new Stanza("cella 1", true, null, null, null, null, Utilita.creaListaOggetti(oggettoContenitore), new PngScambio("Killer bee", this.creaAffilatore(), Gioco.CIBO));
        st1.setPortaOvest(new Porta(TipoPorta.argento, st2, true));
        st2.setPortaEst(new Porta(TipoPorta.argento, st1, true));
        this.stanze.add(st1);
        //stanza 10
        st1 = new Stanza("cella 2", false, null, null, null, null, Utilita.creaListaOggetti(this.creaGrimaldello(), this.creaGrimaldello()), null);
        st2.setPortaNord(new Porta(TipoPorta.oro, st1, true));
        st1.setPortaSud(new Porta(TipoPorta.oro, st2, true));
        this.stanze.add(st2);
        //stanza 11
        st2 = new Stanza("cella 3", true, null, null, null, null, Utilita.creaListaOggetti(this.creaCibo()), new PngIndovinello("Roy Mustung", this.creaGrimaldello(), "Raramente mi toccano, ma spesso mi frenano. Se hai arguzia, mi userai bene, cosa sono?", "intelligenza", "la lingua", "una spada", "b"));
        st1.setPortaNord(new Porta(TipoPorta.oro, st2, true));
        st2.setPortaSud(new Porta(TipoPorta.oro, st1, true));
        this.stanze.add(st1);
        //stanza 12
        st1 = new Stanza("stanza sicurezza", false, null, null, null, null, Utilita.creaListaOggetti(this.creaAffilatore(), this.creaCibo()), null);
        st1.setPortaOvest(new Porta(TipoPorta.normale, st2, false));
        st2.setPortaEst(new Porta(TipoPorta.normale, st1, false));
        this.stanze.add(st2);
        //stanza 13
        st2 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti(), null);
        st1.setPortaEst(new Porta(TipoPorta.oro, st2, true));
        st2.setPortaOvest(new Porta(TipoPorta.oro, st1, true));
        this.stanze.add(st1);
        //stanza 14
        oggettoContenitore = new OggettoContenitore("lo scrigno", Utilita.generaSetAlias("tesoro", "cofanetto", "cassetta", "scatola", "astuccio", "bauletto", "portagioie", "forziere", "scrigno"), Utilita.creaListaOggetti(this.creaSpada(), this.creaCibo()));
        st1 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti(oggettoContenitore), null);
        st3.setPortaNord(new Porta(TipoPorta.normale, st1, false));
        st1.setPortaSud(new Porta(TipoPorta.normale, st3, false));
        this.stanze.add(st3);
        st2.setPortaSud(new Porta(TipoPorta.normale, st1, false));
        st1.setPortaNord(new Porta(TipoPorta.normale, st2, false));
        this.stanze.add(st1);
        //stanza 15
        st3 = new Stanza("stanza guardie", true, null, null, null, null, Utilita.creaListaOggetti(), new Mob("Domenico", this.creaGrimaldello(), false));
        st2.setPortaNord(new Porta(TipoPorta.normale, st3, false));
        st3.setPortaSud(new Porta(TipoPorta.normale, st2, false));
        this.stanze.add(st2);
        //stanza 16
        st1 = new Stanza("tesoreria", false, null, null, null, null, Utilita.creaListaOggetti(), new PngIndovinello("Kise", this.creaTotem(), "Ho mari senza acqua, ho coste senza sabbia, villaggi senza persone e montagne senza terra, cosa sono?", "un deserto", "la luna", "una mappa", "c"));
        st3.setPortaNord(new Porta(TipoPorta.argento, st1, true));
        st1.setPortaSud(new Porta(TipoPorta.argento, st3, true));
        this.stanze.add(st3);
        //stanza 17
        st2 = new Stanza("secondo piano", true, null, null, null, null, Utilita.creaListaOggetti(), null);
        st1.setPortaEst(new Porta(TipoPorta.normale, st2, false));
        st2.setPortaOvest(new Porta(TipoPorta.normale, st1, false));
        this.stanze.add(st1);
        //stanza 18
        st3 = new Stanza("segreteria della prigione", true, null, null, null, null, Utilita.creaListaOggetti(this.creaSpada(), this.creaCibo()), new Mob("Filippo", null, false));
        st2.setPortaEst(new Porta(TipoPorta.normale, st3, false));
        st3.setPortaOvest(new Porta(TipoPorta.normale, st2, false));
        this.stanze.add(st2);
        //stanza 19
        st1 = new Stanza("entrata della prigione", true, null, null, null, null, Utilita.creaListaOggetti(), new Mob("Pierpaolo", null, false));
        st3.setPortaNord(new Porta(TipoPorta.normale, st1, false));
        st1.setPortaSud(new Porta(TipoPorta.normale, st3, false));
        this.stanze.add(st3);
        //stanza 20
        st2 = new Stanza("giardino della prigione", true, null, null, null, null, Utilita.creaListaOggetti(), null);
        st1.setPortaNord(new Porta(TipoPorta.oro, st2, true));
        st2.setPortaSud(new Porta(TipoPorta.oro, st1, true));
        this.stanze.add(st2);
        this.stanze.add(st1);

        this.stanzaCorrente = this.stanze.get(0);

        this.giocatore.aggiornaMosse(Utilita.generaListaComandi(nord, sud, ovest, est, inventario, osservare, raccogliere, torna_indietro, usare, aprire, accendere, mangiare, camminare_verso, tempo, interagire, salva, fine, salute, uccidere, affilare, help));
    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        if (p != null) {
            if ((p.getComando().getTipo() == TipoComando.nord && p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false)) {
                this.spostamento(this.stanzaCorrente.getPortaNord(), out);
                this.controllaFine();
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
                } else if (p.getOggetto() != null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggetto().getTipo() == TipoOggetto.oggettoContenitore) {
                        this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(p.getOggetto())).usa(giocatore, stanzaCorrente);
                    }

                } else if (p.getOggetto() != null && p.getOggettoInv().getTipo() != null && p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggetto().equals(Gioco.OGGETTO_CONTENITORE) && p.getOggettoInv().equals(Gioco.CHIAVE_OGGETTO_CONTENITORE)) {
                        this.giocatore.getInventario().usaOggetto(Gioco.CHIAVE_OGGETTO_CONTENITORE, giocatore, stanzaCorrente);
                        this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(Gioco.OGGETTO_CONTENITORE)).usa(giocatore, stanzaCorrente);
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
                        if (p.getOggettoInv().equals(Gioco.CANDELA)) {
                            if (this.giocatore.getInventario().contieneOggetto(Gioco.CANDELA)) {
                                this.giocatore.getInventario().usaOggetto(Gioco.CANDELA, giocatore, stanzaCorrente);
                            } else {
                                out.println("Rin: 'Non hai una candela da accendere'");
                            }
                        } else if (this.giocatore.getInventario().contieneOggetto(Gioco.CANDELA)) {
                            if (Utilita.chiediConferma("Rin: 'Intendevi candela, vuoi accenderla?'", "Rin: 'Proviamo ad accenderla'", "Rin: 'Ricordati che non puoi accendere altri oggetti, che non siano candela'")) {
                                this.giocatore.getInventario().usaOggetto(Gioco.CANDELA, giocatore, stanzaCorrente);
                            }
                        } else {
                            out.println("Rin: 'Non puoi accendere niente, apparte una candela che non abbiamo'");
                        }
                    } else if (this.giocatore.getInventario().contieneOggetto(Gioco.CANDELA)) {
                        if (Utilita.chiediConferma("Rin: 'Intendevi candela, vuoi accenderla?'", "Rin: 'Proviamo ad accenderla'", "Rin: 'Ricordati che non puoi accendere altri oggetti, che non siano candela'")) {
                            this.giocatore.getInventario().usaOggetto(Gioco.CANDELA, giocatore, stanzaCorrente);
                        }
                    } else {
                        out.println("Rin: 'Non puoi accendere niente, apparte una candela che non abbiamo'");
                    }

                } else {
                    out.println("Rin: 'non ho capito cosa fare'");
                }
            } else if (p.getComando().getTipo() == TipoComando.mangiare) {
                if (p.getOggetto() == null && p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggettoInv() != null) {
                        if (p.getOggettoInv().equals(Gioco.CIBO)) {
                            if (this.giocatore.getInventario().contieneOggetto(Gioco.CIBO)) {
                                this.giocatore.getInventario().usaOggetto(Gioco.CIBO, giocatore, stanzaCorrente);
                            } else {
                                out.println("Rin: 'Non abbiamo del cibo, moriremo di fame !?'");
                            }
                        } else if (this.giocatore.getInventario().contieneOggetto(Gioco.CIBO)) {
                            if (Utilita.chiediConferma("Rin: 'Intendevi pane, vuoi mangiarlo?'", "Rin: 'Mangiare fa sempre bene'", "Rin: 'Ricordati che non puoi mangiare oggetti che non sono cibo'")) {
                                this.giocatore.getInventario().usaOggetto(Gioco.CIBO, giocatore, stanzaCorrente);
                            }
                        } else {
                            out.println("Rin: 'Non puoi mangiare niente'");
                        }
                    } else {
                        if (this.giocatore.getInventario().contieneOggetto(Gioco.CIBO)) {
                            if (Utilita.chiediConferma("Rin: 'Intendevi pane, vuoi mangiarlo?'", "Rin: 'Mangiare fa sempre bene'", "Rin: 'Ricordati che non puoi mangiare oggetti che non sono cibo'")) {
                                this.giocatore.getInventario().usaOggetto(Gioco.CIBO, giocatore, stanzaCorrente);
                            }
                        } else {
                            out.println("Rin: 'Non abbiamo del cibo, moriremo di fame !?'");
                        }
                    }
                } else if (p.getOggettoInv() == null && p.getPorta() == null && p.isNpc()) {
                    if (p.getOggetto() != null) {
                        if (p.getOggetto().equals(Gioco.CIBO)) {
                            if (this.stanzaCorrente.getOggetiStanza().contains(Gioco.CIBO)) {
                                this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(Gioco.CIBO));
                            } else {
                                out.println("Rin: 'Non abbiamo del cibo, moriremo di fame !?'");
                            }
                        } else if (this.stanzaCorrente.getOggetiStanza().contains(Gioco.CIBO)) {
                            if (Utilita.chiediConferma("Rin: 'Intendevi pane, vuoi mangiarlo?'", "Rin: 'Mangiare fa sempre bene'", "Rin: 'Ricordati che non puoi mangiare oggetti che non sono cibo'")) {
                                this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(Gioco.CIBO));
                            }
                        } else {
                            out.println("Rin: 'Non puoi mangiare niente'");
                        }
                    }
                } else {
                    System.out.println("Rin: 'Non ho capito cosa mangiare'");
                }
            } else if (p.getComando().getTipo() == TipoComando.interagire) {
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null) {
                    if (p.isNpc() == true && this.stanzaCorrente.getNpc() != null) {
                        if (this.stanzaCorrente.getNpc().isNeutrale()) {
                            this.stanzaCorrente.getNpc().interagisci(giocatore);
                        } else {
                            Mob mob = (Mob) this.stanzaCorrente.getNpc();
                            if (!mob.isVivo() || mob.isCorrotto()) {
                                this.stanzaCorrente.getNpc().interagisci(giocatore);
                            } else {
                                System.out.println("Rin: 'Sarebbe meglio andare, se non possiamo combatterlo'");
                            }

                        }
                    }
                } else {
                    out.println("Rin: 'non ho capito con chi vuoi parlare'");
                }
            } else if (p.getComando().getTipo() == TipoComando.salva) {
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    try {
                        this.calcolaTempo();
                        salvataggio.Serializzazione.scriviFile(this);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Gioco.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    out.println("Rin: 'Non ho capito cosa devo fare'");
                }
            } else if (p.getComando().getTipo() == TipoComando.fine) {
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {

                    if (Utilita.chiediConferma("Sei sicuro di voler uscure?", "Uscita in corso...", "Rirpesa gioco")) {
                        this.sospesa = true;
                        ThreadTempo.setAttivo(false);
                        out.println("Manji: 'Rin io mi riposo un po', quando mi sveglio riprendiamo'");
                        out.println("");
                        out.println("");
                        if (Utilita.chiediConferma("Vuoi salvare la partita prima di uscire?", "Salvataggio in corso...", "Uscita dalla partita senza salvataggio")) {
                            try {
                                this.calcolaTempo();
                                salvataggio.Serializzazione.scriviFile(this);
                                System.out.println("Uscita in corso...");
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Gioco.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } else {
                    out.println("Rin: 'Non ho capito cosa devo fare'");
                }
            } else if (p.getComando().getTipo() == TipoComando.affilare) {
                if (p.getOggetto() == null && p.getOggettoInv() != null && p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggettoInv().getTipo() == TipoOggetto.spada) {
                        if (this.giocatore.getInventario().contieneOggetto(Gioco.AFFILATORE)) {
                            this.giocatore.getInventario().usaOggetto(Gioco.AFFILATORE, this.giocatore, this.stanzaCorrente);
                        } else {
                            System.out.println("Rin: 'Non abbiamo un affilatore per la spda, dobbiamo prima trovarlo'");
                        }
                    } else {
                        System.out.println("Rin: 'Non puoi affilare " + p.getOggettoInv().getNome());
                        if (this.getGiocatore().getInventario().contieneOggetto(Gioco.SPADA) && this.getGiocatore().getInventario().contieneOggetto(Gioco.AFFILATORE)) {
                            if (Utilita.chiediConferma("Rin: 'Intendevi la spada, ne abbiamo una vuoi affilarla?'", "Rin: 'E' buona cosa mantenere le proprie armi in uno stato ottimale'", "Rin: 'Se cambi idea, ricorda che puoi sempre farlo, ma attenzione che se non curi con attenzione la tua spada puo' rompersi'")) {
                                this.giocatore.getInventario().usaOggetto(Gioco.AFFILATORE, this.giocatore, this.stanzaCorrente);
                            }
                        } else {
                            System.out.println("Rin: 'Pur volendo non possiamo affilare un bel niente'");
                        }
                    }
                } else {
                    System.out.println("Rin: 'Non ho capito cosa vuoi affilare");
                }

            } else if (p.getComando().getTipo() == TipoComando.salute) {
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    System.out.print("Rin: 'La tua salute e' : " + this.giocatore.getVitaCorrente() + " punti salute");
                    if (this.giocatore.getVitaCorrente() < Giocatore.getVITA_INIZIO()) {
                        System.out.println(", ti conviene trovare qualcosa da mangiare per rigenerare un po' di salute'");
                    } else {
                        System.out.println("'");
                    }
                }
            } else if (p.getComando().getTipo() == TipoComando.uccidere) {
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null) {
                    if (p.isNpc()) {
                        if (this.getGiocatore().getInventario().contieneOggetto(Gioco.SPADA)) {
                            this.giocatore.getInventario().usaOggetto(Gioco.SPADA, giocatore, stanzaCorrente);
                        } else {
                            System.out.println("Rin: 'non abbiamo una spada per poterlo uccidere'");
                        }
                    } else {
                        System.out.println("Rin: 'Non ho capito chi vuoi uccidere'");
                    }
                } else {
                    System.out.println("Rin: 'Non puoi uccidere delle cose'");
                }
            } if(p.getComando().getTipo() == TipoComando.help){
                if(p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false){
                    Help.stampaHelpPartita();
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
    public void iniziaPartita() throws FileNotFoundException {
        this.inizializza();
        System.out.println("C’era una volta un samurai di nome Manji, egli era un samurai fedelissimo al suo credo e nella via della spada." + "\n"
                + "Per molti anni ha lavorato per un padrone credendo che quello che faceva per lui fosse giusto," + "\n"
                + "un giorno leggendo dei documenti scoprì che molte delle persone che aveva ucciso erano dei semplici cittadini." + "\n"
                + "Il suo padrone era una persona molto avida che per arricchirsi aumentò di molto le tasse e chiunque rifiutasse di pagarle veniva assassinato da Manji." + "\n"
                + "Dopo la sconvolgente scoperta, Manji in creca di redenzione dalle sue azioni decide di ribellarsi al suo padrone " + "\n"
                + "cercando di liberare il territorio controllato da esso uccidendo quanti più samurai possibili in modo da arrivare ad ucciderlo.\n"
                + "Manji uccise più di 100 sottoposti, ma in una battaglia venne sconfitto e catturato." + "\n"
                + "Il padrone per vendetta decise di non ucciderlo ma di tenerlo in prigione nei sotteranei più profondi dove veniva continuamente torturato. " + "\n"
                + "Per evitare che Manji scappasse, il suo vecchio padrone gli cavò entrambi gli occhi così da impedirgli di usare le sue abilità da samurai.\n"
                + "La sua cella confinava con la cella di una ragazza di nome Rin, lei era una donna alle servitù del padrone." + "\n"
                + "I soldati spesso lasciavano la cella di Rin aperta " + "\n"
                + "in quanto credevano che essendo una donna non fosse capace di creare problemi in quanto non in grado di combattere. \n"
                + "Manji era un samurai molto astuto , infatti," + "\n"
                + "riuscì a capire che la cella era spesso lasciata aperta grazie ai discorsi delle guardie e ai mancati click delle mandate della serratura." + "\n"
                + "Grazie alla sua astuzia riuscì a fare un accordo con Rin, " + "\n"
                + "egli chiese di aiutarlo ad evadere facendogli da guida diventando il suo unico modo di vedere l’esterno," + "\n"
                + "in cambio promise di riuscire a portarli entrambi fuori da quella prigione.\n"
                + "Rin accetta l’ accordo e il giorno dopo al ritorno in cella riesce ad ammaliare una delle guardie rubandogli la chiave della cella di Manji." + "\n"
                + "La notte stessa durante il cambio delle guardie riesce ad aprire la sua cella e da qui inizia la grande fuga dalla prigione.");

        System.out.println("");
        System.out.println("Rin: 'Siamo pronti per iniziare la fuga'");
        System.out.println("Rin: 'Ora ci troviamo nella tua cella Manji, cosa vuoi fare?'");
        System.out.println("");
        this.gioca();
    }

    public void cambiaStanza(Porta porta, PrintStream out) {
        this.PercorsoStanze.push(stanzaCorrente);
        this.setStanzaCorrente(porta.getStanza());
        out.println("Rin: 'Siamo nella stanza: " + this.stanzaCorrente.getNomeStanza() + "'");
    }

    public void spostamento(Porta porta, PrintStream out) {
        if (this.stanzaCorrente.isIlluminata()) {
            if (porta != null) {
                if (porta.getTipo() == TipoPorta.normale || !porta.isChiusa()) {
                    this.cambiaStanza(porta, out);
                } else if (porta.getTipo() == TipoPorta.argento) {
                    out.print(" Rin: '");
                    out.println(porta.descriviPorta() + ".");

                    if (this.getGiocatore().getInventario().contieneOggetto(Gioco.CHIAVE)) {
                        if (Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?'", "Rin: 'Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta'", "Rin: 'Va bene vorrà dire che l' apriremo in un altro momento'")) {
                            this.giocatore.getInventario().usaOggetto(Gioco.CHIAVE, giocatore, stanzaCorrente);
                            this.cambiaStanza(porta, out);

                        }
                    } else {
                        out.println("al momento non abbiamo la chiave, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla'");
                    }
                } else if (porta.getTipo() == TipoPorta.oro) {
                    out.print(" Rin: '");
                    out.println(porta.descriviPorta() + ".");

                    if (this.getGiocatore().getInventario().contieneOggetto(Gioco.TOTEM)) {
                        if (Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?'", "Rin: 'Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta'", "Rin : 'Va bene vorrà dire che l' apriremo in un altro momento'")) {
                            this.giocatore.getInventario().usaOggetto(Gioco.TOTEM, giocatore, stanzaCorrente);

                            this.cambiaStanza(porta, out);

                        }
                    } else {
                        out.println("al momento non abbiamo il totem, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarlo'");
                    }
                }
            } else {
                out.println("Rin: 'Non possiamo andare da quella direzione, perche non c'e' alcuna porta, non possiamo oltrepassare i muri'");
            }
        } else {
            out.println("Rin: 'La stanza e' buia, prima di fare qualsiasi cosa sarebbe meglio illuminare la stanza'");
        }
    }

    public void apriPorta(Porta porta, PrintStream out) {
        if (porta.isChiusa()) {
            if (porta.getTipo() == TipoPorta.normale) {
                out.println("Rin: hai aperto la porta");
                porta.setChiusa(false);
            } else if (porta.getTipo() == TipoPorta.argento) {
                if (this.getGiocatore().getInventario().contieneOggetto(Gioco.CHIAVE)) {
                    if (Utilita.chiediConferma("Fortunatamente abbiamo l'oggetto necessario per aprirla, vuoi usarlo?'", "Rin: 'Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta'", "Rin: 'Va bene vorrà dire che l' apriremo in un altro momento'")) {
                        this.giocatore.getInventario().usaOggetto(Gioco.CHIAVE, giocatore, stanzaCorrente);
                    }
                } else {
                    out.println("Rin: 'Non possiamo aprire questa porta, ci servirebbe un determinato oggetto per aprirlo'");
                }
            } else if (porta.getTipo() == TipoPorta.oro) {
                if (this.getGiocatore().getInventario().contieneOggetto(Gioco.TOTEM)) {
                    if (Utilita.chiediConferma("Fortunatamente abbiamo l'oggetto necessario per aprirla, vuoi usarlo?'", "Rin: 'Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta'", "Rin: 'Va bene vorrà dire che l' apriremo in un altro momento'")) {
                        this.giocatore.getInventario().usaOggetto(Gioco.TOTEM, giocatore, stanzaCorrente);
                    }
                } else {
                    out.println("Rin: 'Non possiamo aprire questa porta, ci servirebbe un determinato oggetto per aprirlo'");
                }
            }
        } else {
            out.println("Rin: 'La porta e' gia' aperta'");
        }
    }

    @Override
    public void continua() throws FileNotFoundException {
        this.sospesa = false;
        System.out.println("Rin: 'Ben tornato, ora ci troviamo nel " + this.stanzaCorrente.getNomeStanza());
        this.gioca();
    }

    public void controllaFine() {
        if (this.stanzaCorrente.getNomeStanza().equals("giardino della prigione")) {
            this.finita = true;
            System.out.println("Rin: 'Manji siamo fuori, siamo fuori! Dai andiamocene prima che le vedette ci scoprano'");
        }
    }

    public void gioca() throws FileNotFoundException {
        Parser parser = new Parser(Utilita.caricaFileSet("./risorse/articoli.txt"));
        Scanner scanner = new Scanner(System.in);
        while (!this.finita && !this.sospesa && this.giocatore.getVitaCorrente() > 0) {
            if (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                ParserOutput p = parser.parse(command, this.giocatore.getListaMosse(), this.stanzaCorrente.getOggetiStanza(), this.giocatore.getInventario().getInventario(), stanzaCorrente);
                this.nextMove(p, System.out);
                if (this.stanzaCorrente.isIlluminata()) {
                    if (this.stanzaCorrente.getNpc() != null) {
                        if (!this.stanzaCorrente.getNpc().isNeutrale()) {
                            Mob mob = (Mob) this.stanzaCorrente.getNpc();
                            if (mob.isVivo() && !mob.isCorrotto()) {
                                mob.interagisci(giocatore);
                            }
                        }
                    }
                }
            }
        }
        if (this.finita) {
            System.out.println("Finalmente i nostri eroi sono riusciti ad uscire dalla prigione" + "\n"
                    + "una volta fuori, decisero di andare lontano dal villaggio e vivere in campagna insieme");

            System.out.println("Complimenti hai completato il gioco");
            Deserializzazione.cancellaPartitaFinita(this);
        } else if (this.giocatore.getVitaCorrente() <= 0) {
            System.out.println("I nostri eroi non sono riusciti a fuggire dalla prigione");
            System.out.println("Hai perso!");
        }
    }

    public Oggetto creaCandela() {
        return new Candela("la candela", Utilita.generaSetAlias("candelabro", "cera", "lume", "fiaccola", "torcia", "candela"));
    }

    public Oggetto creaGrimaldello() {
        return new ChiaveOggettoContenitore("grimaldello", Utilita.generaSetAlias("grimaldello"));
    }

    public Oggetto creaTotem() {
        return new ChiavePorta("il totem", Utilita.generaSetAlias("totem", "statuetta", "artefatto"), TipoPorta.oro, ChiavePorta.getTIPO_OGGETTO2());
    }

    public Oggetto creaChiave() {
        return new ChiavePorta("la chiave", Utilita.generaSetAlias("chiave"), TipoPorta.argento, ChiavePorta.getTIPO_OGGETTO1());
    }

    public Oggetto creaCibo() {
        return new Cibo("il pane", Utilita.generaSetAlias("panino", "cibo", "pane"), 30);

    }

    public Oggetto creaScrigno() {
        return new OggettoContenitore("lo scrigno", Utilita.generaSetAlias("contenitore", "scrigno", "baule"), Utilita.creaListaOggetti());
    }

    public Oggetto creaOggettoMaligno() {
        return new OggettoMaligno("del veleno", Utilita.generaSetAlias(), 30);
    }

    public Oggetto creaSpada() {
        return new Spada("la spada", Utilita.generaSetAlias("spada", "lama", "arma bianca", "daga", "katana", "ferro"));
    }

    public Oggetto creaAffilatore() {
        return new Affilatore("l'affilatore", Utilita.generaSetAlias("affilatoio", "cote", "mola", "affilatrice", "affilatore"));
    }

}
