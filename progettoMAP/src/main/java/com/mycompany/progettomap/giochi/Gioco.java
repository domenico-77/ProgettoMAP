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
import java.util.Scanner;
import oggetti.Candela;
import oggetti.ChiaveOggettoContenitore;
import oggetti.ChiavePorta;
import oggetti.Cibo;
import oggetti.Oggetto;
import oggetti.OggettoContenitore;
import oggetti.OggettoMaligno;
import tipi.Porta;
import tipi.TipoPorta;
import tipi.Utilita;

/**
 *
 * @author Acer
 */
public class Gioco extends DescrizioneGioco {

    Oggetto candela = new Candela("Candela", Utilita.generaSetAlias("Candelabro", "Cera", "Lume", "Fiaccola", "Torcia"), Utilita.generaListaComandi());
    Oggetto chiaveOggettoContenitore = new ChiaveOggettoContenitore("Grimaldello", Utilita.generaSetAlias("chiave scrigno"), Utilita.generaListaComandi());
    Oggetto chiavePortaDorata = new ChiavePorta("chiave", Utilita.generaSetAlias(), Utilita.generaListaComandi(), TipoPorta.oro);
    Oggetto chiavePortaArgentata = new ChiavePorta("chiave", Utilita.generaSetAlias(), Utilita.generaListaComandi(), TipoPorta.argento);
    Oggetto cibo = new Cibo("Pane", Utilita.generaSetAlias("Panino", "Cibo"), Utilita.generaListaComandi(), 30);
    Oggetto oggettoContenitore = new OggettoContenitore("Scrigno", Utilita.generaSetAlias("Armadio", "Mobile"), Utilita.generaListaComandi(), Utilita.creaListaOggetti());
    Oggetto oggettoMaligno = new OggettoMaligno("veleno", Utilita.generaSetAlias(), Utilita.generaListaComandi(), 30);

    public Gioco() {
    }

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

        Comando torna_indietro = new Comando("raccogliere", TipoComando.torna_indietro);
        torna_indietro.setAlias(new String[]{"indietreggia", "torna"});
        //stanze
        Stanza st1, st2, st3;
        Oggetto candela = new Candela("Candela", Utilita.generaSetAlias("Candelabro", "Cera", "Lume", "Fiaccola", "Torcia"), Utilita.generaListaComandi(raccogliere, usare, accendere, osservare));
        Oggetto chiaveOggettoContenitore = new ChiaveOggettoContenitore("Grimaldello", Utilita.generaSetAlias("chiave scrigno"), Utilita.generaListaComandi(raccogliere, usare));
        Oggetto chiavePortaDorata = new ChiavePorta("chiave", Utilita.generaSetAlias(), Utilita.generaListaComandi(), TipoPorta.oro);
        Oggetto chiavePortaArgentata = new ChiavePorta("chiave", Utilita.generaSetAlias(), Utilita.generaListaComandi(), TipoPorta.argento);
        Oggetto cibo = new Cibo("Pane", Utilita.generaSetAlias("Panino", "Cibo"), Utilita.generaListaComandi(usare, raccogliere), 30);
        Oggetto oggettoContenitore = new OggettoContenitore("Scrigno", Utilita.generaSetAlias("Armadio", "Mobile"), Utilita.generaListaComandi(aprire, chiudere, osservare, usare), Utilita.creaListaOggetti());
        Oggetto oggettoMaligno = new OggettoMaligno("veleno", Utilita.generaSetAlias(), Utilita.generaListaComandi(), 30);

        st1 = new Stanza("Cella di Madji", true, null, null, null, null, new ArrayList<>());

        st2 = new Stanza("Corridoio", true, null, null, null, null, Utilita.creaListaOggetti(candela));
        st1.setPortaNord(new Porta(TipoPorta.normale, st2, false, false));
        st2.setPortaSud(new Porta(TipoPorta.normale, st1, false, false));

    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        boolean x;
        String s = "";
        Oggetto chiavePortaArgentata = new ChiavePorta("chiave d'argento", Utilita.generaSetAlias(), Utilita.generaListaComandi(), TipoPorta.argento);
        Oggetto chiavePortaOro = new ChiavePorta("chiave d'oro", Utilita.generaSetAlias(), Utilita.generaListaComandi(), TipoPorta.argento);

        if (p.getComando() == null) {
            out.println("Rin : Non ho capito cosa devo fare! Prova a esprimerti meglio.");

        } else {
            //NORD
            if (p.getComando().getTipo() == TipoComando.nord) {
                if (this.getStanzaCorrente().getPortaNord() != null) {
                    if ((this.getStanzaCorrente().getPortaNord().getTipo() == TipoPorta.normale) || (!this.getStanzaCorrente().getPortaNord().isChiusa())) {
                        this.getPercorsoStanze().push(this.getStanzaCorrente());
                        this.getStanzaCorrente().getPortaNord().setChiusa(false);
                        this.setStanzaCorrente(this.getStanzaCorrente().getPortaNord().getStanza());

                    } // controllo chiave argento 
                    else if ((this.getStanzaCorrente().getPortaNord().getTipo() == TipoPorta.argento) && this.getStanzaCorrente().getPortaNord().isChiusa()) {
                        out.print(" Rin : ");
                        s = this.getStanzaCorrente().getPortaNord().descriviPorta();
                        out.println(s);

                        if (this.getGiocatore().getInventario().contieneOggetto(chiavePortaArgentata)) {
                            x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                            if (x) {
                                this.getPercorsoStanze().push(this.getStanzaCorrente());
                                this.getStanzaCorrente().getPortaNord().setChiusa(false);
                                this.setStanzaCorrente(this.getStanzaCorrente().getPortaNord().getStanza());
                            }
                        } else if (!this.getGiocatore().getInventario().contieneOggetto(chiavePortaArgentata)) {
                            out.println("al momento non abbiamo la chiave argentata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                        }
                    } //controllo chiave oro
                    else if ((this.getStanzaCorrente().getPortaNord().getTipo() == TipoPorta.oro) && this.getStanzaCorrente().getPortaNord().isChiusa()) {
                        out.print(" Rin : ");
                        s = this.getStanzaCorrente().getPortaNord().descriviPorta();

                        if (this.getGiocatore().getInventario().contieneOggetto(chiavePortaOro)) {
                            x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                            if (x) {
                                this.getPercorsoStanze().push(this.getStanzaCorrente());
                                this.getStanzaCorrente().getPortaNord().setChiusa(false);
                                this.setStanzaCorrente(this.getStanzaCorrente().getPortaNord().getStanza());

                            }
                        } else if (!this.getGiocatore().getInventario().contieneOggetto(chiavePortaOro)) {
                            out.println("al momento non abbiamo la chiave dorata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                        }
                    } //controllo tunnel
                } else {
                    out.println("Non possiamo andare verso nord poichè non c'è alcuna porta e non sappiamo oltrepassare i muri");
                }
                //SUD
            } else if (p.getComando().getTipo() == TipoComando.sud) {
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

                        if (this.getGiocatore().getInventario().contieneOggetto(chiavePortaArgentata)) {
                            x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                            if (x) {
                                this.getPercorsoStanze().push(this.getStanzaCorrente());
                                this.getStanzaCorrente().getPortaSud().setChiusa(false);
                                this.setStanzaCorrente(this.getStanzaCorrente().getPortaSud().getStanza());
                            }
                        } else if (!this.getGiocatore().getInventario().contieneOggetto(chiavePortaArgentata)) {
                            out.println("al momento non abbiamo la chiave argentata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                        }
                    } //controllo chiave oro
                    else if ((this.getStanzaCorrente().getPortaSud().getTipo() == TipoPorta.oro) && this.getStanzaCorrente().getPortaSud().isChiusa()) {
                        out.print(" Rin : ");
                        s = this.getStanzaCorrente().getPortaSud().descriviPorta();

                        if (this.getGiocatore().getInventario().contieneOggetto(chiavePortaOro)) {
                            x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                            if (x) {
                                this.getPercorsoStanze().push(this.getStanzaCorrente());
                                this.getStanzaCorrente().getPortaSud().setChiusa(false);
                                this.setStanzaCorrente(this.getStanzaCorrente().getPortaSud().getStanza());

                            }
                        } else if (!this.getGiocatore().getInventario().contieneOggetto(chiavePortaOro)) {
                            out.println("al momento non abbiamo la chiave dorata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                        }
                    } //controllo tunnel
                } else {
                    out.println("Non possiamo andare verso sud poichè non c'è alcuna porta e non sappiamo oltrepassare i muri");
                }
            } //EST
            else if (p.getComando().getTipo() == TipoComando.est) {
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

                        if (this.getGiocatore().getInventario().contieneOggetto(chiavePortaArgentata)) {
                            x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                            if (x) {
                                this.getPercorsoStanze().push(this.getStanzaCorrente());
                                this.getStanzaCorrente().getPortaEst().setChiusa(false);
                                this.setStanzaCorrente(this.getStanzaCorrente().getPortaEst().getStanza());
                            }
                        } else if (!this.getGiocatore().getInventario().contieneOggetto(chiavePortaArgentata)) {
                            out.println("al momento non abbiamo la chiave argentata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                        }
                    } //controllo chiave oro
                    else if ((this.getStanzaCorrente().getPortaEst().getTipo() == TipoPorta.oro) && this.getStanzaCorrente().getPortaEst().isChiusa()) {
                        out.print(" Rin : ");
                        s = this.getStanzaCorrente().getPortaEst().descriviPorta();

                        if (this.getGiocatore().getInventario().contieneOggetto(chiavePortaOro)) {
                            x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                            if (x) {
                                this.getPercorsoStanze().push(this.getStanzaCorrente());
                                this.getStanzaCorrente().getPortaEst().setChiusa(false);
                                this.setStanzaCorrente(this.getStanzaCorrente().getPortaEst().getStanza());

                            }
                        } else if (!this.getGiocatore().getInventario().contieneOggetto(chiavePortaOro)) {
                            out.println("al momento non abbiamo la chiave dorata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                        }
                    } //controllo tunnel
                } else {
                    out.println("Non possiamo andare verso est poichè non c'è alcuna porta e non sappiamo oltrepassare i muri");
                }
            } //OVEST
            else if (p.getComando().getTipo() == TipoComando.ovest) {
                if (this.getStanzaCorrente().getPortaEst() != null) {
                    if ((this.getStanzaCorrente().getPortaEst().getTipo() == TipoPorta.normale) || (!this.getStanzaCorrente().getPortaEst().isChiusa())) {
                        this.getPercorsoStanze().push(this.getStanzaCorrente());
                        this.getStanzaCorrente().getPortaEst().setChiusa(false);
                        this.setStanzaCorrente(this.getStanzaCorrente().getPortaEst().getStanza());

                    } // controllo chiave argento 
                    else if ((this.getStanzaCorrente().getPortaOvest().getTipo() == TipoPorta.argento) && this.getStanzaCorrente().getPortaOvest().isChiusa()) {
                        out.print(" Rin : ");
                        s = this.getStanzaCorrente().getPortaOvest().descriviPorta();
                        out.println(s);

                        if (this.getGiocatore().getInventario().contieneOggetto(chiavePortaArgentata)) {
                            x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                            if (x) {
                                this.getPercorsoStanze().push(this.getStanzaCorrente());
                                this.getStanzaCorrente().getPortaOvest().setChiusa(false);
                                this.setStanzaCorrente(this.getStanzaCorrente().getPortaOvest().getStanza());
                            }
                        } else if (!this.getGiocatore().getInventario().contieneOggetto(chiavePortaArgentata)) {
                            out.println("al momento non abbiamo la chiave argentata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                        }
                    } //controllo chiave oro
                    else if ((this.getStanzaCorrente().getPortaOvest().getTipo() == TipoPorta.oro) && this.getStanzaCorrente().getPortaOvest().isChiusa()) {
                        out.print(" Rin : ");
                        s = this.getStanzaCorrente().getPortaEst().descriviPorta();

                        if (this.getGiocatore().getInventario().contieneOggetto(chiavePortaOro)) {
                            x = Utilita.chiediConferma("Fortunatamente ne abbiamo una, vuoi usarla ?", "Perfetto andiamo a scoprire cosa ci aspetta dietro questa porta.", "Va bene vorrà dire che torneremo indietro");
                            if (x) {
                                this.getPercorsoStanze().push(this.getStanzaCorrente());
                                this.getStanzaCorrente().getPortaOvest().setChiusa(false);
                                this.setStanzaCorrente(this.getStanzaCorrente().getPortaOvest().getStanza());

                            }
                        } else if (!this.getGiocatore().getInventario().contieneOggetto(chiavePortaOro)) {
                            out.println("al momento non abbiamo la chiave dorata, se vogliamo scoprire cosa si nasconde dietro questa porta dovremo andare in giro a cercarla ");
                        }
                    } //controllo tunnel
                } else {
                    out.println("Non possiamo andare verso ovest poichè non c'è alcuna porta e non sappiamo oltrepassare i muri");
                }

            } else if (p.getComando().getTipo() == TipoComando.inventario) {
                this.getGiocatore().getInventario().visualizzaInventario();

            } else if (p.getComando().getTipo() == TipoComando.raccogliere) {
                if (p.getOggetto() != null) {
                    if (p.getOggetto().isPrendibile()) {
                        this.getGiocatore().getInventario().aggiungiOggetto(p.getOggetto());
                        this.getStanzaCorrente().rimuoviOggetto(p.getOggetto());
                        System.out.println("Hai raccolto: " + p.getOggetto().getNome());
                    } else {
                        out.println("Non puoi raccogliere questo oggetto.");
                    }
                } else {
                    out.println("Non c'è niente da raccogliere qui.");
                }
            } else if (p.getComando().getTipo() == TipoComando.osservare) {
                if (p.getOggetto() == null) {
                    this.getStanzaCorrente().DescriviStanza();

                } else if (p.getOggetto() != null) {
                    p.getOggetto().descrizioneOggetto();
                }

            } else if (p.getComando().getTipo() == TipoComando.usare) {
                if (p.getOggetto() != null && p.getOggetto().getUsabilita() > 0) {

                    p.getOggetto().usa(this.getGiocatore(), this.getStanzaCorrente());
                } else if (p.getOggetto().getUsabilita() <= 0) {
                    System.out.println("Non puoi usare questo oggetto");
                } else if (p.getOggetto() == null) {
                    System.out.println("Non ho capito quale oggetto vuoi usare");

                }

            } else if (p.getComando().getTipo() == TipoComando.accendere) {
                if (p.getOggetto() == candela) {
                    if (this.getGiocatore().getInventario().contieneOggetto(candela)) {
                        candela.usa(this.getGiocatore(), this.getStanzaCorrente());
                    }

                } else if (p.getOggetto() != candela) {
                    System.out.println("Non posso usare quest'oggetto per illuminare la stanza");
                } else if (p.getOggetto() == null) {
                    System.out.println("Non ho capito cosa accendere");
                }

            } else if (p.getComando().getTipo() == TipoComando.aprire) {
                if(p.getOggetto() == )

            } else if (p.getComando().getTipo() == TipoComando.fine) {
                System.out.println("Complimenti");
                System.exit(0);

            } else if (p.getComando().getTipo() == TipoComando.torna_indietro) {
                this.setStanzaCorrente(this.getPercorsoStanze().pop());

            }

        }
    }
}
