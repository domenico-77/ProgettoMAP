/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gioco;

/**
 *
 * @author Acer
 */
import parser.ParserOutput;
import comandi.Comando;
import stanze.Stanza;
import comandi.TipoComando;
import java.util.ArrayList;
import npc.PngIndovinello;
import npc.PngScambio;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import npc.Mob;
import giocatore.Giocatore;
import stanze.Porta;
import stanze.TipoPorta;
import utilita.Utilita;

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
        st1 = new Stanza("tesoreria", true, null, null, null, null, Utilita.creaListaOggetti(), new PngIndovinello("Alphonse", this.creaChiave(), "Spesso racconto una storia, ma non chiedo alcun soldo. \n Ti intrattengo tutta la notte, ma ahime', non ti ricorderai di me. \n Cosa sono?", "un sogno", "un libro", "una musica", "a"));
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
        st2 = new Stanza("cella 3", true, null, null, null, null, Utilita.creaListaOggetti(this.creaCibo()), new PngIndovinello("Roy Mustung", this.creaGrimaldello(), "Raramente mi toccano, ma spesso mi frenano. \n Se hai arguzia, mi userai bene, \n cosa sono?", "intelligenza", "la lingua", "una spada", "b"));
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
        st1 = new Stanza("tesoreria", false, null, null, null, null, Utilita.creaListaOggetti(), new PngIndovinello("Kise", this.creaTotem(), "Ho mari senza acqua, ho coste senza sabbia, \n villaggi senza persone e montagne senza terra,\n cosa sono?", "un deserto", "la luna", "una mappa", "c"));
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

        this.finita = false;
        this.sospesa = false;

        this.giocatore.aggiornaMosse(Utilita.generaListaComandi(nord, sud, ovest, est, inventario, osservare, raccogliere, torna_indietro, usare, aprire, accendere, mangiare, camminare_verso, tempo, interagire, salva, fine, salute, uccidere, affilare, help));
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
    public void controllaFineSwing(JTextArea out) {
        if (this.stanzaCorrente.getNomeStanza().equals("giardino della prigione")) {
            this.finita = true;
            out.append("Rin: 'Manji siamo fuori, siamo fuori! Dai andiamocene prima che le vedette ci scoprano'");
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

    @Override
    public void nextMove(ParserOutput p, JTextArea out, JFrame frame) {
        if (p != null) {
            if ((p.getComando().getTipo() == TipoComando.nord && p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false)) {
                this.spostamento(this.stanzaCorrente.getPortaNord(), out, frame);
                this.controllaFineSwing(out);
            } else if ((p.getComando().getTipo() == TipoComando.sud && p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false)) {
                this.spostamento(this.stanzaCorrente.getPortaSud(), out, frame);
            } else if ((p.getComando().getTipo() == TipoComando.est && p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false)) {
                this.spostamento(this.stanzaCorrente.getPortaEst(), out, frame);
            } else if ((p.getComando().getTipo() == TipoComando.ovest && p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false)) {
                this.spostamento(this.stanzaCorrente.getPortaOvest(), out, frame);
            } else if ((p.getComando().getTipo() == TipoComando.torna_indietro && p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false)) {
                if (!this.PercorsoStanze.empty()) {
                    this.setStanzaCorrente(this.PercorsoStanze.pop());
                    out.append("Sei entrato nella stanza : " + this.stanzaCorrente.getNomeStanza() + "\n");
                } else {
                    out.append("Rin : 'Non ho capito dove vuoi tornare. '" + "\n");
                }
            } else if ((p.getComando().getTipo() == TipoComando.aprire)) {
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() != null && p.isNpc() == false) {

                    if (this.stanzaCorrente.getPortaNord() != null && p.getPorta().getStanza().equals(this.stanzaCorrente.getPortaNord().getStanza())) {

                        apriPorta(this.stanzaCorrente.getPortaNord(), out);

                    } else if (this.stanzaCorrente.getPortaSud() != null && p.getPorta().getStanza().equals(this.stanzaCorrente.getPortaSud().getStanza())) {

                        apriPorta(this.stanzaCorrente.getPortaSud(), out);

                    } else if (this.stanzaCorrente.getPortaEst() != null && p.getPorta().getStanza().equals(this.stanzaCorrente.getPortaEst().getStanza())) {

                        apriPorta(this.stanzaCorrente.getPortaEst(), out);

                    } else if (this.stanzaCorrente.getPortaOvest() != null && p.getPorta().getStanza().equals(this.stanzaCorrente.getPortaOvest().getStanza())) {

                        apriPorta(this.stanzaCorrente.getPortaOvest(), out);

                    } else {
                        out.append("Rin: 'Non ho capito cosa fare, ripeti" + "\n");
                    }
                } else if (p.getOggetto() != null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggetto().getTipo() == TipoOggetto.oggettoContenitore) {
                        this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(p.getOggetto())).usaSwing(giocatore, stanzaCorrente, out);
                    }

                } else if (p.getOggetto() != null && p.getOggettoInv().getTipo() != null && p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggetto().equals(Gioco.OGGETTO_CONTENITORE) && p.getOggettoInv().equals(Gioco.CHIAVE_OGGETTO_CONTENITORE)) {
                        this.giocatore.getInventario().usaOggettoSwing(Gioco.CHIAVE_OGGETTO_CONTENITORE, giocatore, stanzaCorrente, out);
                        this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(Gioco.OGGETTO_CONTENITORE)).usaSwing(giocatore, stanzaCorrente, out);
                    }
                } else {
                    out.append("Rin : 'Non ho capito cosa vuoi aprire'" + "\n");
                }
            } else if (p.getComando().getTipo() == TipoComando.inventario) {
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    this.giocatore.getInventario().visualizzaInventario(out);
                } else {
                    out.append("Rin: 'Non ho capito cosa fare'" + "\n");
                }
            } else if (p.getComando().getTipo() == TipoComando.osservare) {

                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    this.stanzaCorrente.DescriviStanza(out);
                } else if (p.getOggetto() != null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    if (this.stanzaCorrente.isIlluminata()) {
                        p.getOggetto().descrizioneOggetto(out);
                    } else {
                        out.append("Rin: 'Non riesco a vedere nulla, sarebbe il caso di illuminare la stanza prima di fare qualcosa'" + "\n");
                    }
                } else if (p.getOggetto() == null && p.getOggettoInv() != null && p.getPorta() == null && p.isNpc() == false) {
                    p.getOggettoInv().descrizioneOggetto(out);
                } else {
                    out.append("Rin: 'Non ho capito cosa fare'" + "\n");
                }
            } else if (p.getComando().getTipo() == TipoComando.raccogliere) {
                if (p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggetto() != null) {
                        if (this.stanzaCorrente.isIlluminata()) {
                            if (p.getOggetto().isPrendibile()) {
                                this.giocatore.getInventario().aggiungiOggetto(p.getOggetto());
                                out.append("Rin: 'Hai raccolto: " + p.getOggetto().getNome() + "'" + "\n");
                                this.stanzaCorrente.rimuoviOggetto(p.getOggetto());

                            } else {
                                out.append("Rin: 'Non puoi raccogliere questo oggetto'" + "\n");
                            }
                        } else {
                            out.append("Rin: 'Non riesco a vedere nulla, sarebbe il caso di illuminare la stanza prima di fare qualcosa'" + "\n");
                        }
                    } else {
                        out.append("Rin: 'Non ho capito cosa raccogliere'" + "\n");
                    }
                } else {
                    out.append("Rin: 'Non ho capito cosa raccogliere'" + "\n");
                }

            } else if (p.getComando().getTipo() == TipoComando.usare) {
                if (p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggetto() != null && p.getOggettoInv() == null) {
                        if (this.stanzaCorrente.isIlluminata()) {
                            this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(p.getOggetto())).usaSwing(giocatore, stanzaCorrente, out);
                            if (this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(p.getOggetto())).getUsabilita() <= 0) {
                                this.stanzaCorrente.getOggetiStanza().remove(p.getOggetto());
                                out.append("Rin: 'L'oggetto ha finito la sua usabilita'" + "\n");
                            }
                        } else {
                            out.append("Rin: 'Non riesco a vedere nulla, sarebbe il caso di illuminare la stanza prima di fare qualcosa'" + "\n");
                        }
                    } else if (p.getOggetto() == null && p.getOggettoInv() != null) {
                        this.giocatore.getInventario().usaOggettoSwing(p.getOggettoInv(), giocatore, stanzaCorrente, out);
                    } else {
                        out.append("Rin: 'Non ho capito cosa fare'" + "\n");
                    }
                } else {
                    out.append("Rin: 'Non ho capito cosa fare'" + "\n");
                }
            } else if (p.getComando().getTipo() == TipoComando.tempo) {
                this.calcolaTempo();
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    calcolaTempo();
                    if (secondi < 10 && minuti < 10 && ore < 10) {
                        out.append("Tempo passato: 0" + ore + ":0" + minuti + ":0" + secondi + "\n");
                    } else if (secondi >= 10 && minuti < 10 && ore < 10) {
                        out.append("Tempo passato: 0" + ore + ":0" + minuti + ":" + secondi + "\n");
                    } else if (secondi < 10 && minuti >= 10 && ore < 10) {
                        out.append("Tempo passato: 0" + ore + ":" + minuti + ":0" + secondi + "\n");
                    } else if (secondi < 10 && minuti < 10 && ore >= 10) {
                        out.append("Tempo passato: 0" + ore + ":0" + minuti + ":" + secondi + "\n");
                    } else if (secondi >= 10 && minuti >= 10 && ore < 10) {
                        out.append("Tempo passato: " + ore + ":" + minuti + ":0" + secondi + "\n");
                    } else if (secondi < 10 && minuti >= 10 && ore >= 10) {
                        out.append("Tempo passato: 0" + ore + ":" + minuti + ":" + secondi + "\n");
                    } else if (secondi >= 10 && minuti < 10 && ore >= 10) {
                        out.append("Tempo passato: " + ore + ":0" + minuti + ":" + secondi + "\n");
                    } else {
                        out.append("Tempo passato: " + ore + ":" + minuti + ":" + secondi + "\n");
                    }
                }

            } else if (p.getComando().getTipo() == TipoComando.accendere) {
                if (p.getOggetto() == null && p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggettoInv() != null) {
                        if (p.getOggettoInv().equals(Gioco.CANDELA)) {
                            if (this.giocatore.getInventario().contieneOggetto(Gioco.CANDELA)) {
                                this.giocatore.getInventario().usaOggettoSwing(Gioco.CANDELA, giocatore, stanzaCorrente, out);
                            } else {
                                out.append("Rin: 'Non hai una candela da accendere'" + "\n");
                            }
                        } else {

                            out.append("Rin: 'Non puoi accendere niente, apparte una candela'" + "\n");
                        }

                    } else {
                        out.append("Rin: 'non ho capito cosa fare'" + "\n");
                    }
                } else {
                    out.append("Rin: 'Non ho capito cosa mangiare'\n");
                }
            } else if (p.getComando().getTipo() == TipoComando.mangiare) {
                if (p.getOggetto() == null && p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggettoInv() != null) {
                        if (p.getOggettoInv().equals(Gioco.CIBO)) {
                            if (this.giocatore.getInventario().contieneOggetto(Gioco.CIBO)) {
                                this.giocatore.getInventario().usaOggettoSwing(Gioco.CIBO, giocatore, stanzaCorrente, out);
                            } else {
                                out.append("Rin: 'Non abbiamo del cibo, moriremo di fame !?'" + "\n");
                            }
                        } else {
                            out.append("Rin: 'Non puoi mangiarlo'");
                        }
                    } else {

                        out.append("Rin: 'Non ho capito cosa mangiare'" + "\n");
                    }
                } else if (p.getOggettoInv() == null && p.getPorta() == null && p.isNpc()) {
                    if (p.getOggetto() != null) {
                        if (p.getOggetto().equals(Gioco.CIBO)) {
                            if (this.stanzaCorrente.getOggetiStanza().contains(Gioco.CIBO)) {
                                this.stanzaCorrente.getOggetiStanza().get(this.stanzaCorrente.getOggetiStanza().indexOf(Gioco.CIBO));
                            } else {
                                out.append("Rin: 'Non abbiamo del cibo, moriremo di fame !?'" + "\n");
                            }
                        } else {
                            out.append("Rin: 'Non puoi mangiarlo'" + "\n");
                        }
                    }
                } else {
                    out.append("Rin: 'Non ho capito cosa mangiare'" + "\n");
                }
            } else if (p.getComando().getTipo() == TipoComando.interagire) {
                if (this.stanzaCorrente.isIlluminata()) {
                    if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null) {
                        if (p.isNpc() == true && this.stanzaCorrente.getNpc() != null) {
                            if (this.stanzaCorrente.getNpc().isNeutrale()) {
                                this.stanzaCorrente.getNpc().interagisci(giocatore, out, frame);
                            } else {
                                Mob mob = (Mob) this.stanzaCorrente.getNpc();
                                if (!mob.isVivo() || mob.isCorrotto()) {
                                    this.stanzaCorrente.getNpc().interagisci(giocatore, out, frame);
                                } else {
                                    out.append("Rin: 'Sarebbe meglio andare, se non possiamo combatterlo'" + "\n");
                                }

                            }
                        } else {
                            out.append("Rin: 'non ho capito con chi vuoi parlare'" + "\n");
                        }
                    } else {
                        out.append("Rin: 'non ho capito con chi vuoi parlare'" + "\n");
                    }
                }
                else{
                    out.append("Rin: 'Non riesco a vedere niente, non sappiamo nemmeno se c'e' qualcuno'");
                }
            } else if (p.getComando().getTipo() == TipoComando.salva) {
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    try {
                        this.calcolaTempo();
                        salvataggio.Serializzazione.scriviFile(this);
                        out.append("Rin: 'Partita salvata'\n");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Gioco.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    out.append("Rin: 'Non ho capito cosa devo fare'" + "\n");
                }
            } else if (p.getComando().getTipo() == TipoComando.fine) {
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    if (Utilita.chiediConfermaSwing("Sei sicuro di voler uscire", "Uscita in corso...", "Ripresa in corso", out, frame)) {
                        this.sospesa = true;
                        ThreadTempo.setAttivo(false);
                        out.append("Manji: 'Rin io mi riposo un po', quando mi sveglio riprendiamo'" + "\n");
                        out.append("");
                        out.append("");
                        if (Utilita.chiediConfermaSwing("Vuoi salvare prima di uscire?", "Salvataggio in corso...", "Partita non salvata", out, frame)) {
                            try {
                                this.calcolaTempo();
                                salvataggio.Serializzazione.scriviFile(this);
                                out.append("Uscita in corso...");
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Gioco.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } else {
                    out.append("Rin: 'Non ho capito cosa devo fare'" + "\n");
                }
            } else if (p.getComando().getTipo() == TipoComando.affilare) {
                if (p.getOggetto() == null && p.getOggettoInv() != null && p.getPorta() == null && p.isNpc() == false) {
                    if (p.getOggettoInv().getTipo() == TipoOggetto.spada) {
                        if (this.giocatore.getInventario().contieneOggetto(Gioco.AFFILATORE)) {
                            this.giocatore.getInventario().usaOggettoSwing(Gioco.AFFILATORE, this.giocatore, this.stanzaCorrente, out);
                        } else {
                            out.append("Rin: 'Non abbiamo un affilatore per la spada, dobbiamo prima trovarlo'" + "\n");
                        }
                    } else {
                        out.append("Rin: 'Non puoi affilare " + p.getOggettoInv().getNome());
                    }
                } else {
                    out.append("Rin: 'Non ho capito cosa vuoi affilare'" + "\n");
                }

            } else if (p.getComando().getTipo() == TipoComando.salute) {
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    out.append("Rin: 'La tua salute e' : " + this.giocatore.getVitaCorrente() + " punti salute");
                    if (this.giocatore.getVitaCorrente() < Giocatore.getVITA_INIZIO()) {
                        out.append(", ti conviene trovare qualcosa da mangiare per rigenerare un po' di salute'" + "\n");
                    } else {
                        out.append("'" + "\n");
                    }
                }
            } else if (p.getComando().getTipo() == TipoComando.uccidere) {
                if (this.stanzaCorrente.isIlluminata()) {
                    if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null) {
                        if (p.isNpc()) {
                            if (this.getGiocatore().getInventario().contieneOggetto(Gioco.SPADA)) {
                                this.giocatore.getInventario().usaOggettoSwing(Gioco.SPADA, giocatore, stanzaCorrente, out);
                            } else {
                                out.append("Rin: 'non abbiamo una spada per poterlo uccidere'" + "\n");
                            }
                        } else {
                            out.append("Rin: 'Non ho capito chi vuoi uccidere'" + "\n");
                        }
                    } else {
                        out.append("Rin: 'Non puoi uccidere delle cose'" + "\n");
                    }
                } else {
                    out.append("Rin: 'Non vedo niente, non sappiamo nemmeno se c'e' qualcuno nella stanza'");
                }
            } else if (p.getComando().getTipo() == TipoComando.help) {
                if (p.getOggetto() == null && p.getOggettoInv() == null && p.getPorta() == null && p.isNpc() == false) {
                    Help.stampaHelpPartitaSwing(out);
                }
            }
        } else {
            out.append("Rin: 'Non ho capito cosa devo fare, prova a esprimerti meglio'" + "\n");
        }
    }

    public void apriPorta(Porta porta, JTextArea out) {
        if (porta.isChiusa()) {
            if (porta.getTipo() == TipoPorta.normale) {
                out.append("Rin: hai aperto la porta'" + "\n");
                porta.setChiusa(false);
            } else if (porta.getTipo() == TipoPorta.argento) {
                if (this.getGiocatore().getInventario().contieneOggetto(Gioco.CHIAVE)) {
                    this.giocatore.getInventario().usaOggettoSwing(Gioco.CHIAVE, giocatore, stanzaCorrente, out);

                } else {
                    out.append("Rin: 'Non possiamo aprire questa porta, ci servirebbe un determinato oggetto per aprirlo'" + "\n");
                }
            } else if (porta.getTipo() == TipoPorta.oro) {
                if (this.getGiocatore().getInventario().contieneOggetto(Gioco.TOTEM)) {
                    this.giocatore.getInventario().usaOggettoSwing(Gioco.TOTEM, giocatore, stanzaCorrente, out);

                } else {
                    out.append("Rin: 'Non possiamo aprire questa porta, ci servirebbe un determinato oggetto per aprirlo'" + "\n");
                }
            }
        } else {
            out.append("Rin: 'La porta e' gia' aperta'" + "\n");
        }
    }

    @Override
    public void spostamento(Porta porta, JTextArea out, JFrame frame) {
        if (this.stanzaCorrente.isIlluminata()) {
            if (porta != null) {
                if (porta.getTipo() == TipoPorta.normale || !porta.isChiusa()) {
                    this.cambiaStanza(porta, out);
                } else if (porta.getTipo() == TipoPorta.argento) {
                    out.append(" Rin: '");
                    out.append(porta.descriviPorta() + "." + "\n");

                    if (this.getGiocatore().getInventario().contieneOggetto(Gioco.CHIAVE)) {

                        if (Utilita.chiediConfermaSwing("Fortunatamente ne abbiamo una, vuoi usarla ?'", "Rin: 'Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta'", "Rin: 'Va bene vorrà dire che l' apriremo in un altro momento'", out, frame)) {
                            this.giocatore.getInventario().usaOggettoSwing(Gioco.CHIAVE, giocatore, stanzaCorrente, out);
                            this.cambiaStanza(porta, out);

                        }
                    } else {
                        out.append("al momento non abbiamo la chiave,\n se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla'");
                    }
                } else if (porta.getTipo() == TipoPorta.oro) {
                    out.append(" Rin: '");
                    out.append(porta.descriviPorta() + "." + "\n");

                    if (this.getGiocatore().getInventario().contieneOggetto(Gioco.TOTEM)) {
                        if (Utilita.chiediConfermaSwing("Fortunatamente ne abbiamo una, vuoi usarla ?'", "Rin: 'Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta'", "Rin : 'Va bene vorrà dire che l' apriremo in un altro momento'", out, frame)) {
                            this.giocatore.getInventario().usaOggettoSwing(Gioco.TOTEM, giocatore, stanzaCorrente, out);

                            this.cambiaStanza(porta, out);

                        }

                    } else {
                        out.append("al momento non abbiamo il totem, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarlo'" + "\n");
                    }
                }
            } else {
                out.append("Rin: 'Non possiamo andare da quella direzione, perche non c'e' alcuna porta, non possiamo oltrepassare i muri'" + "\n");
            }
        } else {
            out.append("Rin: 'La stanza e' buia, prima di fare qualsiasi cosa sarebbe meglio illuminare la stanza'" + "\n");
        }
    }

    public void cambiaStanza(Porta porta, JTextArea out) {
        this.PercorsoStanze.push(stanzaCorrente);
        this.setStanzaCorrente(porta.getStanza());
        out.append("Rin: 'Siamo nella stanza: " + this.stanzaCorrente.getNomeStanza() + "'" + "\n");
    }
}
