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
import tipi.stanze.Stanza;
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
import tipi.stanze.Porta;
import tipi.stanze.TipoPorta;
import tipi.Utilita;

/**
 *
 * @author Acer
 */
public class Gioco extends DescrizioneGioco {

    private static final Oggetto candela = new Candela("candela", Utilita.generaSetAlias("candelabro", "cera", "lume", "fiaccola", "torcia", "candela"), Utilita.generaListaComandi());
    private static final Oggetto chiaveOggettoContenitore = new ChiaveOggettoContenitore("grimaldello", Utilita.generaSetAlias("grimaldello"), Utilita.generaListaComandi());
    private static final Oggetto chiavePortaDorata = new ChiavePorta("chiave d'oro", Utilita.generaSetAlias("chiave", "chiave oro"), Utilita.generaListaComandi(), TipoPorta.oro);
    private static final Oggetto chiavePortaArgentata = new ChiavePorta("chiave argento", Utilita.generaSetAlias("chiave", "chiave argento"), Utilita.generaListaComandi(), TipoPorta.argento);
    private static final Oggetto cibo = new Cibo("pane", Utilita.generaSetAlias("panino", "cibo", "pane"), Utilita.generaListaComandi(), 30);
    private static final Oggetto oggettoContenitore = new OggettoContenitore("scrigno", Utilita.generaSetAlias("contenitore", "scrigno", "baule"), Utilita.generaListaComandi(), Utilita.creaListaOggetti());
    private static final Oggetto oggettoMaligno = new OggettoMaligno("veleno", Utilita.generaSetAlias(), Utilita.generaListaComandi(), 30);

    @Override
    public void inizializza() {

        //comandi
        Comando nord = new Comando("nord", TipoComando.nord, Utilita.generaSetAlias("n", "su", "sopra", "sù", "nord"));

        //aggiungere comando alla lista di comandi di descrizioneGioco?
        Comando sud = new Comando("sud", TipoComando.sud, Utilita.generaSetAlias("s", "giu", "sotto", "giù", "sud"));

        Comando est = new Comando("est", TipoComando.est, Utilita.generaSetAlias("e", "destrra", "est"));

        Comando ovest = new Comando("ovest", TipoComando.ovest, Utilita.generaSetAlias("o", "sinistra", "ovest"));

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

        //stanze
        Stanza st1, st2, st3;
        Oggetto spada = new Spada("la spada", Utilita.generaSetAlias("spada", "lama", "arma bianca", "daga", "katana", "ferro"), Utilita.generaListaComandi(raccogliere, usare, osservare));
        Oggetto affilatore = new Affilatore("l'affilatore", Utilita.generaSetAlias("affilatoio", "cote", "mola", "affilatrice", "affilatore"), Utilita.generaListaComandi(raccogliere, usare, osservare));
        Oggetto oggettoContenitore;
        //stanza 1
        st1 = new Stanza("cella di Manji", true, null, null, null, null, new ArrayList<>());
        //stanza 2
        st2 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti(this.candela));
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
        st1 = new Stanza("bagno", true, null, null, null, null, Utilita.creaListaOggetti(this.chiaveOggettoContenitore));
        st3.setPortaOvest(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaEst(new Porta(TipoPorta.normale, st3, false, false));
        this.stanze.add(st1);
        //stanza 5
        st2 = new Stanza("corridoio", true, null, null, null, null, Utilita.creaListaOggetti(affilatore, this.cibo));
        st3.setPortaNord(new Porta(TipoPorta.normale, st2, false, false));
        st2.setPortaSud(new Porta(TipoPorta.normale, st3, false, false));
        this.stanze.add(st3);
        //stanza 6
        st1 = new Stanza("tesoreria", true, null, null, null, null, Utilita.creaListaOggetti(this.chiavePortaArgentata));
        st2.setPortaEst(new Porta(TipoPorta.normale, st1, false, false));
        st1.setPortaOvest(new Porta(TipoPorta.normale, st2, false, false));
        //stanza 7
        st3 = new Stanza("armeria", true, null, null, null, null, Utilita.creaListaOggetti(spada));
        st2.setPortaNord(new Porta(TipoPorta.argento, st3, true, false));
        st3.setPortaSud(new Porta(TipoPorta.argento, st2, true, false));
        this.stanze.add(st2);
        this.stanze.add(st1);
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
        st1 = new Stanza("cella 2", false, null, null, null, null, Utilita.creaListaOggetti(this.chiaveOggettoContenitore, this.chiaveOggettoContenitore));
        st2.setPortaNord(new Porta(TipoPorta.oro, st1, true, false));
        st1.setPortaSud(new Porta(TipoPorta.oro, st2, true, false));
        this.stanze.add(st2);
        //stanza 11
        st2 = new Stanza("cella 3", true, null, null, null, null, Utilita.creaListaOggetti(this.cibo));
        st1.setPortaNord(new Porta(TipoPorta.oro, st2, true, false));
        st2.setPortaSud(new Porta(TipoPorta.oro, st1, true, false));
        this.stanze.add(st1);
        //stanza 12
        st1 = new Stanza("stanza sicurezza", false, null, null, null, null, Utilita.creaListaOggetti(affilatore, this.cibo));
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
        st1 = new Stanza("tesoreria", false, null, null, null, null, Utilita.creaListaOggetti(this.chiavePortaDorata));
        st3.setPortaNord(new Porta(TipoPorta.argento, st1, true, true));
        st1.setPortaSud(new Porta(TipoPorta.argento, st3, true, false));
        this.stanze.add(st3);
        //stanza 17
        st2 = new Stanza("secondo piano", true, null, null, null, null, Utilita.creaListaOggetti());
        st1.setPortaEst(new Porta(TipoPorta.normale, st2, false, false));
        st2.setPortaOvest(new Porta(TipoPorta.normale, st1, false, false));
        this.stanze.add(st1);
        //stanza 18
        st3 = new Stanza("segreteria della prigione", true, null, null, null, null, Utilita.creaListaOggetti(spada, this.cibo));
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

        this.stanzaCorrente = this.stanze.get(0);

        this.giocatore.aggiornaMosse(Utilita.generaListaComandi(nord, sud, ovest, est, inventario, osservare, raccogliere, torna_indietro, usare, aprire, accendere, mangiare, camminare_verso));

    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        boolean x;
        String s = "";

        if (p == null) {
            out.println("Rin : Non ho capito cosa devo fare! Prova a esprimerti meglio.");

        } else {
            //NORD
            if (p.getComando().getTipo() == TipoComando.nord) {
                if (this.stanzaCorrente.isIlluminata()) {
                    if (this.getStanzaCorrente().getPortaNord() != null) {
                        if ((this.getStanzaCorrente().getPortaNord().getTipo() == TipoPorta.normale) || (!this.getStanzaCorrente().getPortaNord().isChiusa())) {
                            this.getPercorsoStanze().push(this.getStanzaCorrente());
                            this.getStanzaCorrente().getPortaNord().setChiusa(false);
                            this.setStanzaCorrente(this.getStanzaCorrente().getPortaNord().getStanza());

                        } // controllo chiave argento 
                        else if ((this.getStanzaCorrente().getPortaNord().getTipo() == TipoPorta.argento) && this.getStanzaCorrente().getPortaNord().isChiusa()) {
                            out.print(" Rin : '");
                            s = this.getStanzaCorrente().getPortaNord().descriviPorta();
                            out.println(s + ".");

                            if (this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaArgentata)) {
                                x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?'", "Rin : 'Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta'", "Rin : 'Va bene vorrà dire che l' apriremo in un altro momento'");
                                if (x) {
                                    this.getPercorsoStanze().push(this.getStanzaCorrente());
                                    this.getStanzaCorrente().getPortaNord().setChiusa(false);
                                    this.setStanzaCorrente(this.getStanzaCorrente().getPortaNord().getStanza());
                                }
                            } else if (!this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaArgentata)) {
                                out.println("al momento non abbiamo la chiave argentata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                            }
                        } //controllo chiave oro
                        else if ((this.getStanzaCorrente().getPortaNord().getTipo() == TipoPorta.oro) && this.getStanzaCorrente().getPortaNord().isChiusa()) {
                            out.print(" Rin : ");
                            s = this.getStanzaCorrente().getPortaNord().descriviPorta();

                            if (this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaDorata)) {
                                x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                                if (x) {
                                    this.getPercorsoStanze().push(this.getStanzaCorrente());
                                    this.getStanzaCorrente().getPortaNord().setChiusa(false);
                                    this.setStanzaCorrente(this.getStanzaCorrente().getPortaNord().getStanza());
                                }
                            } else if (!this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaDorata)) {
                                out.println("al momento non abbiamo la chiave dorata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                            }
                        } //controllo tunnel
                    } else {
                        out.println("Rin : 'Non possiamo andare verso nord poichè non c'è alcuna porta e non sappiamo oltrepassare i muri'");
                    }
                } else {

                    System.out.println("Rin : 'La stanza è buia e potrebbe nascondere pericoli, ci conviene usare una candela per illuminarla o altrimenti tornare indietro...'");
                }
                //SUD
            } else if (p.getComando().getTipo() == TipoComando.sud) {
                if (this.stanzaCorrente.isIlluminata()) {
                    if (this.getStanzaCorrente().getPortaSud() != null) {
                        if ((this.getStanzaCorrente().getPortaSud().getTipo() == TipoPorta.normale) || (!this.getStanzaCorrente().getPortaSud().isChiusa())) {
                            this.getPercorsoStanze().push(this.getStanzaCorrente());
                            this.getStanzaCorrente().getPortaSud().setChiusa(false);
                            this.setStanzaCorrente(this.getStanzaCorrente().getPortaSud().getStanza());

                        } // controllo chiave argento 
                        else if ((this.getStanzaCorrente().getPortaSud().getTipo() == TipoPorta.argento) && this.getStanzaCorrente().getPortaSud().isChiusa()) {
                            out.print(" Rin : ");
                            s = this.getStanzaCorrente().getPortaSud().descriviPorta();
                            out.println(s);

                            if (this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaArgentata)) {
                                x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                                if (x) {
                                    this.getPercorsoStanze().push(this.getStanzaCorrente());
                                    this.getStanzaCorrente().getPortaSud().setChiusa(false);
                                    this.setStanzaCorrente(this.getStanzaCorrente().getPortaSud().getStanza());
                                }
                            } else if (!this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaArgentata)) {
                                out.println("al momento non abbiamo la chiave argentata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                            }
                        } //controllo chiave oro
                        else if ((this.getStanzaCorrente().getPortaSud().getTipo() == TipoPorta.oro) && this.getStanzaCorrente().getPortaSud().isChiusa()) {
                            out.print(" Rin : ");
                            s = this.getStanzaCorrente().getPortaSud().descriviPorta();

                            if (this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaDorata)) {
                                x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                                if (x) {
                                    this.getPercorsoStanze().push(this.getStanzaCorrente());
                                    this.getStanzaCorrente().getPortaSud().setChiusa(false);
                                    this.setStanzaCorrente(this.getStanzaCorrente().getPortaSud().getStanza());

                                }
                            } else if (!this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaDorata)) {
                                out.println("al momento non abbiamo la chiave dorata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                            }
                        } //controllo tunnel
                    } else {
                        out.println("Rin : 'Non possiamo andare verso sud poichè non c'è alcuna porta e non sappiamo oltrepassare i muri'");
                    }
                } else {
                    System.out.println("Rin : 'La stanza è buia e potrebbe nascondere pericoli, ci conviene usare una candela per illuminarla o altrimenti tornare indietro...'");
                }

            } //EST
            else if (p.getComando().getTipo() == TipoComando.est) {
                if (this.stanzaCorrente.isIlluminata()) {
                    if (this.getStanzaCorrente().getPortaEst() != null) {
                        if ((this.getStanzaCorrente().getPortaEst().getTipo() == TipoPorta.normale) || (!this.getStanzaCorrente().getPortaEst().isChiusa())) {
                            this.getPercorsoStanze().push(this.getStanzaCorrente());
                            this.getStanzaCorrente().getPortaEst().setChiusa(false);
                            this.setStanzaCorrente(this.getStanzaCorrente().getPortaEst().getStanza());

                        } // controllo chiave argento 
                        else if ((this.getStanzaCorrente().getPortaEst().getTipo() == TipoPorta.argento) && this.getStanzaCorrente().getPortaEst().isChiusa()) {
                            out.print(" Rin : ");
                            s = this.getStanzaCorrente().getPortaEst().descriviPorta();
                            out.println(s);

                            if (this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaArgentata)) {
                                x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                                if (x) {
                                    this.getPercorsoStanze().push(this.getStanzaCorrente());
                                    this.getStanzaCorrente().getPortaEst().setChiusa(false);
                                    this.setStanzaCorrente(this.getStanzaCorrente().getPortaEst().getStanza());
                                }
                            } else if (!this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaArgentata)) {
                                out.println("al momento non abbiamo la chiave argentata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                            }
                        } //controllo chiave oro
                        else if ((this.getStanzaCorrente().getPortaEst().getTipo() == TipoPorta.oro) && this.getStanzaCorrente().getPortaEst().isChiusa()) {
                            out.print(" Rin : ");
                            s = this.getStanzaCorrente().getPortaEst().descriviPorta();

                            if (this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaDorata)) {
                                x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                                if (x) {
                                    this.getPercorsoStanze().push(this.getStanzaCorrente());
                                    this.getStanzaCorrente().getPortaEst().setChiusa(false);
                                    this.setStanzaCorrente(this.getStanzaCorrente().getPortaEst().getStanza());

                                }
                            } else if (!this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaDorata)) {
                                out.println("al momento non abbiamo la chiave dorata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                            }
                        } //controllo tunnel
                    } else {
                        out.println("Rin : 'Non possiamo andare verso est poichè non c'è alcuna porta e non sappiamo oltrepassare i muri'");
                    }
                } else {
                    System.out.println("Rin : 'La stanza è buia e potrebbe nascondere pericoli, ci conviene usare una candela per illuminarla o altrimenti tornare indietro...'");

                }
            } //OVEST
            else if (p.getComando().getTipo() == TipoComando.ovest) {
                if (this.stanzaCorrente.isIlluminata()) {
                    if (this.getStanzaCorrente().getPortaOvest() != null) {
                        if ((this.getStanzaCorrente().getPortaOvest().getTipo() == TipoPorta.normale) || (!this.getStanzaCorrente().getPortaOvest().isChiusa())) {
                            this.getPercorsoStanze().push(this.getStanzaCorrente());
                            this.getStanzaCorrente().getPortaOvest().setChiusa(false);
                            this.setStanzaCorrente(this.getStanzaCorrente().getPortaOvest().getStanza());

                        } // controllo chiave argento 
                        else if ((this.getStanzaCorrente().getPortaOvest().getTipo() == TipoPorta.argento) && this.getStanzaCorrente().getPortaOvest().isChiusa()) {
                            out.print(" Rin : ");
                            s = this.getStanzaCorrente().getPortaOvest().descriviPorta();
                            out.println(s);

                            if (this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaArgentata)) {
                                x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                                if (x) {
                                    this.getPercorsoStanze().push(this.getStanzaCorrente());
                                    this.getStanzaCorrente().getPortaOvest().setChiusa(false);
                                    this.setStanzaCorrente(this.getStanzaCorrente().getPortaOvest().getStanza());
                                }
                            } else if (!this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaArgentata)) {
                                out.println("al momento non abbiamo la chiave argentata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                            }
                        } //controllo chiave oro
                        else if ((this.getStanzaCorrente().getPortaOvest().getTipo() == TipoPorta.oro) && this.getStanzaCorrente().getPortaOvest().isChiusa()) {
                            out.print(" Rin : ");
                            s = this.getStanzaCorrente().getPortaEst().descriviPorta();

                            if (this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaDorata)) {
                                x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                                if (x) {
                                    this.getPercorsoStanze().push(this.getStanzaCorrente());
                                    this.getStanzaCorrente().getPortaOvest().setChiusa(false);
                                    this.setStanzaCorrente(this.getStanzaCorrente().getPortaOvest().getStanza());

                                }
                            } else if (!this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaDorata)) {
                                out.println("al momento non abbiamo la chiave dorata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                            }
                        } //controllo tunnel
                    } else {
                        out.println("Rin : 'Non possiamo andare verso ovest poichè non c'è alcuna porta e non sappiamo oltrepassare i muri'");
                    }
                } else {
                    System.out.println("Rin : 'La stanza è buia e potrebbe nascondere pericoli, ci conviene usare una candela per illuminarla o altrimenti tornare indietro...'");

                }

            } else if (p.getComando().getTipo() == TipoComando.inventario) {
                this.getGiocatore().getInventario().visualizzaInventario();

            } else if (p.getComando().getTipo() == TipoComando.raccogliere) {
                if (!this.stanzaCorrente.getOggetiStanza().isEmpty()) {
                    if (this.stanzaCorrente.isIlluminata()) {
                        if (p.getOggetto() != null) {
                            if (p.getOggetto().isPrendibile()) {
                                this.getGiocatore().getInventario().aggiungiOggetto(p.getOggetto());
                                this.getStanzaCorrente().rimuoviOggetto(p.getOggetto());
                                System.out.println("Hai raccolto: " + p.getOggetto().getNome());
                            } else {
                                out.println("Rin : 'Non puoi raccogliere questo oggetto'");
                            }
                        } else {
                            out.println("Rin : 'Non ho capito cosa raccogliere'");
                        }
                    } else if (!this.stanzaCorrente.isIlluminata()) {
                        System.out.println("Rin : 'La stanza è buia e potrebbe nascondere pericoli, ci conviene usare una candela per illuminarla o altrimenti tornare indietro...'");

                    }
                } else {
                    System.out.println("Rin: 'Non c'è niente da raccogliere qui");
                }
            } else if (p.getComando().getTipo() == TipoComando.osservare) {
                if (p.getOggetto() == null && p.getOggettoInv() == null) {
                    this.getStanzaCorrente().DescriviStanza();

                } else if (p.getOggetto() != null && p.getOggettoInv() == null) {
                    if (!this.stanzaCorrente.isIlluminata()) {
                        System.out.println("Rin : 'Non riesco a descriverti nulla poiche' la stanza e' buia'");
                    } else if (this.stanzaCorrente.isIlluminata()) {
                        p.getOggetto().descrizioneOggetto();
                    }
                } else if (p.getOggetto() == null && p.getOggettoInv() != null) {
                    p.getOggettoInv().descrizioneOggetto();
                }

            } else if (p.getComando().getTipo() == TipoComando.usare) {
                if (this.stanzaCorrente.isIlluminata()) {
                    if (p.getOggetto() != null) {
                        p.getOggetto().usa(this.getGiocatore(), this.getStanzaCorrente());
                    } else if (p.getOggetto() == null) {
                        if ((p.getOggettoInv() != null) && (this.giocatore.getInventario().contieneOggetto(p.getOggettoInv()))) {
                            this.giocatore.getInventario().usaOggetto(p.getOggettoInv(), this.giocatore, this.stanzaCorrente);
                        }
                    } else if ((p.getOggetto() == null) && (p.getOggettoInv() == null)) {

                        System.out.println("Rin : 'Non ho capito quale oggetto vuoi usare'");
                    }
                } else if (!this.stanzaCorrente.isIlluminata()) {
                    System.out.println("");
                }
            } else if (p.getComando().getTipo() == TipoComando.accendere) {
                if (p.getOggettoInv() == candela && p.getOggetto() == null) {

                    candela.usa(this.getGiocatore(), this.getStanzaCorrente());

                } else if (p.getOggetto() == candela && p.getOggettoInv() == null) {
                    candela.usa(this.getGiocatore(), this.getStanzaCorrente());

                } else {
                    if (this.giocatore.getInventario().contieneOggetto(candela)) {
                        boolean y = Utilita.chiediConferma("Rin : 'Intendevi candela?'", "", "Rin : 'L'unica cosa che puoi accendere e' la candela'");
                        if (y) {
                            this.giocatore.getInventario().usaOggetto(candela, this.giocatore, this.stanzaCorrente);

                        }
                    } else {
                        System.out.println("Rin : 'Non disponi di una candela'");
                    }
                }

            } else if (p.getComando().getTipo() == TipoComando.aprire) {
                if (this.stanzaCorrente.isIlluminata()) {
                    if (p.getPorta() != null) {
                        if (p.getPorta().isChiusa()) {
                            TipoPorta tipoPorta = p.getPorta().getTipo();
                            switch (tipoPorta) {
                                case normale:
                                    p.getPorta().setChiusa(false);
                                    System.out.println("Rin: 'Hai aperto la porta'");
                                    break;

                                case oro:
                                    System.out.println("Rin: 'Per aprire una porta di quel tipo dovremmo usare una chiave d'oro");
                                    if (this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaDorata)) {
                                        x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                                        if (x) {
                                            this.getPercorsoStanze().push(this.getStanzaCorrente());
                                            this.getStanzaCorrente().getPortaNord().setChiusa(false);
                                            this.setStanzaCorrente(this.getStanzaCorrente().getPortaNord().getStanza());
                                        }
                                    } else if (!this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaDorata)) {
                                        out.println("al momento non abbiamo la chiave dorata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                                    }
                                    break;

                                case argento:
                                    System.out.println("Rin: 'Per aprire una porta di quel tipo dovremmo usare una chiave d'argento");
                                    if (this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaArgentata)) {
                                        x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                                        if (x) {
                                            this.getPercorsoStanze().push(this.getStanzaCorrente());
                                            this.getStanzaCorrente().getPortaEst().setChiusa(false);
                                            this.setStanzaCorrente(this.getStanzaCorrente().getPortaEst().getStanza());
                                        }
                                    } else if (!this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaArgentata)) {
                                        out.println("al momento non abbiamo la chiave argentata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                                    }
                            }
                        } else {
                            System.out.println("Rin: 'La porta è già stata aperta'");
                        }
                    } else if (p.getOggetto() == oggettoContenitore) {
                        oggettoContenitore.usa(this.giocatore, this.stanzaCorrente);
                    }
                } else if (!this.stanzaCorrente.isIlluminata()) {
                    System.out.println("Rin : 'La stanza è buia e potrebbe nascondere pericoli, ci conviene usare una candela per illuminarla o altrimenti tornare indietro...'");

                }

                /*else if (p.getComando().getTipo() == TipoComando.fine) {
                System.out.println("Complimenti");
                System.exit(0);*/
            } else if (p.getComando().getTipo() == TipoComando.torna_indietro && p.getOggettoInv() == null && p.getOggetto() == null && p.getPorta() == null) {
                this.setStanzaCorrente(this.getPercorsoStanze().pop());

            } else if (p.getComando().getTipo() == TipoComando.mangiare) {
                if (p.getOggettoInv() == cibo) {
                    cibo.usa(this.giocatore, this.stanzaCorrente);
                }
            } else if (p.getComando().getTipo() == TipoComando.camminare_verso) {
                if (this.stanzaCorrente.isIlluminata()) {
                    if (p.getPorta() != null) {
                        if (p.getPorta().isChiusa()) {
                            if (p.getPorta().getTipo() == TipoPorta.normale) {
                                this.getPercorsoStanze().push(this.getStanzaCorrente());
                                this.stanzaCorrente = p.getPorta().getStanza();

                            } else if (p.getPorta().getTipo() == TipoPorta.argento) {
                                out.print(" Rin : ");
                                String string = p.getPorta().descriviPorta();
                                out.println(s);

                                if (this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaArgentata)) {
                                    x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                                    if (x) {
                                        this.getPercorsoStanze().push(this.getStanzaCorrente());
                                        p.getPorta().setChiusa(false);
                                        this.setStanzaCorrente(p.getPorta().getStanza());
                                    }
                                } else if (!this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaArgentata)) {
                                    out.println("al momento non abbiamo la chiave argentata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                                }

                            } else if (p.getPorta().getTipo() == TipoPorta.oro) {
                                out.print(" Rin : ");
                                String string = p.getPorta().descriviPorta();
                                out.println(s);

                                if (this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaDorata)) {
                                    x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                                    if (x) {
                                        this.getPercorsoStanze().push(this.getStanzaCorrente());
                                        p.getPorta().setChiusa(false);
                                        this.setStanzaCorrente(p.getPorta().getStanza());
                                    }
                                } else if (!this.getGiocatore().getInventario().contieneOggetto(this.chiavePortaDorata)) {
                                    out.println("al momento non abbiamo la chiave dorata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                                }

                            }

                        } else {
                            this.getPercorsoStanze().push(this.getStanzaCorrente());
                            this.stanzaCorrente = p.getPorta().getStanza();
                        }
                    } else if (p.getPorta() == null) {
                        System.out.println("Rin : 'Non ho capito verso dove vuoi andare");
                    }
                } else if (!this.stanzaCorrente.isIlluminata()) {
                    System.out.println("Rin : 'La stanza è buia e potrebbe nascondere pericoli, ci conviene usare una candela per illuminarla o altrimenti tornare indietro...'");

                }
            }

        }
    }

    @Override
    public void stampaStanze() {
        for (Stanza s : this.stanze) {
            s.DescriviStanza();
        }
    }
}
