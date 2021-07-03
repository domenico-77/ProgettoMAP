/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npc;

import java.io.Serializable;
import java.util.Scanner;
import oggetti.Oggetto;
import tipi.Giocatore;
import tipi.Utilita;

/**
 *
 * @author mtubi
 */
public class PngIndovinello extends Npc implements Serializable {

    private static final boolean NEUTRALE = true;
    private static final boolean SCONOSCIUTO = true;
    private static final String RISPOSTA_A = "a";
    private static final String RISPOSTA_B = "b";
    private static final String RISPOSTA_C = "c";

    private String indovinello;
    private String rispostaA;
    private String rispostaB;
    private String rispostaC;
    private String rispostaEsatta;
    private boolean accontentato = false;
    private boolean sconosciuto = false;

    public PngIndovinello(String nome, Oggetto oggetto, String indovinello, String rispostaA, String rispostaB, String rispostaC, String rispostaEsatta) {
        super(nome, oggetto, NEUTRALE);
        this.indovinello = indovinello;
        this.rispostaA = rispostaA;
        this.rispostaB = rispostaB;
        this.rispostaC = rispostaC;
        this.rispostaEsatta = rispostaEsatta;
    }

    @Override
    public void interagisci(Giocatore giocatore) {
        if (!this.vivo) {
            String nomeNpc;
            if (this.sconosciuto) {
                nomeNpc = "Sconosciuto";
            } else {
                nomeNpc = this.nome;
            }
            if (Utilita.chiediConferma("Rin: '" + nomeNpc + " e' morto, vuoi controllare il corpo? potrebbe avere qualcosa di utile!'", "Rin: 'Va bene, controlliamo il suo corpo'", "Rin: 'Andiamocene prima che il suo corpo inizi a puzzare!'")) {
                if (this.oggetto != null) {
                    System.out.println("Hai raccolto: " + this.oggetto.getNome());
                    giocatore.getInventario().aggiungiOggetto(this.oggetto);
                    this.oggetto = null;
                } else {
                    System.out.println("Rin: 'Il cadavere non ha niente di interessante, possiamo andare'");
                }
            }
        } else {
            if (accontentato) {
                if (this.oggetto != null) {
                    System.out.println(this.nome + ": 'Tieni questo è quello che posso darti, per aiutarti nella tua fuga'");
                    giocatore.getInventario().aggiungiOggetto(this.oggetto);
                    System.out.println("Hai ottenuto: " + this.nome);
                    this.oggetto = null;
                } else {
                    System.out.println(this.nome + ": 'Ti ho gia' dato tutto cio' che era in mio possesso, cos'altro vuoi ahahahahah'");
                }
            } else {
                if (this.sconosciuto) {
                    System.out.println("Sconosciuto: 'Ciao ho sentito parlare molto di te, se mi dai una mano ricambierò il favore'");
                    if (Utilita.chiediConferma("Rin: 'Vuoi sentire la proposta dello sconosciuto?'", "Rin: 'Vediamo cosa hai da offrire'", "Sconosciuto: 'Nel caso cambi idea io sono sempre qui'")) {
                        this.sconosciuto = false;
                        System.out.println(this.nome + ": 'Piacere il mio nome e' " + this.nome + "'");
                        this.interagisci(giocatore);
                    }
                } else {
                    Scanner scanner = new Scanner(System.in);
                    String risposta;
                    System.out.println(this.nome + ": 'Ora ti faro' un indovinello, se mi darai una risposta giusta, ti premiero'!'");
                    System.out.println(this.nome + ": " + this.indovinello);
                    System.out.println(this.nome + ": le risposte sono: ");
                    System.out.println("a." + this.rispostaA);
                    System.out.println("b." + this.rispostaB);
                    System.out.println("c." + this.rispostaC);
                    if (scanner.hasNextLine()) {
                        risposta = scanner.nextLine();
                        risposta = risposta.toLowerCase();
                        switch (risposta) {
                            case RISPOSTA_A:
                                if (risposta.equals(this.rispostaEsatta)) {
                                    this.accontentato = true;
                                    System.out.println(this.nome + ": 'Complimenti la risposta e' giusta!'");
                                    this.interagisci(giocatore);
                                } else {
                                    System.out.println(this.nome + ": 'La risposta e' errata!'");
                                    if (Utilita.chiediConferma(this.nome + ": 'Vuoi riprovare?'", this.nome + ": 'Sara' la volta buona, ahahahahah'", this.nome + ": 'Nel caso cambi idea io sono sempre qui'")) {
                                        this.interagisci(giocatore);
                                    }
                                }
                                break;

                            case RISPOSTA_B:
                                if (risposta.equals(this.rispostaEsatta)) {
                                    this.accontentato = true;
                                    System.out.println(this.nome + ": 'Complimenti la risposta e' giusta!'");
                                    this.interagisci(giocatore);
                                } else {
                                    System.out.println(this.nome + ": 'La risposta e' errata!'");
                                    if (Utilita.chiediConferma(this.nome + ": 'Vuoi riprovare?'", this.nome + ": 'Sara' la volta buona, ahahahahah'", this.nome + ": 'Nel caso cambi idea io sono sempre qui'")) {
                                        this.interagisci(giocatore);
                                    }
                                }
                                break;

                            case RISPOSTA_C:
                                if (risposta.equals(this.rispostaEsatta)) {
                                    this.accontentato = true;
                                    System.out.println(this.nome + ": 'Complimenti la risposta e' giusta!'");
                                    this.interagisci(giocatore);
                                } else {
                                    System.out.println(this.nome + ": 'La risposta e' errata!'");
                                    if (Utilita.chiediConferma(this.nome + ": 'Vuoi riprovare?'", this.nome + ": 'Sara' la volta buona, ahahahahah'", this.nome + ": 'Nel caso cambi idea io sono sempre qui'")) {
                                        this.interagisci(giocatore);
                                    }
                                }
                                break;
                            default:
                                System.out.println(this.nome + ": 'La risposta data non e' valida!");
                                if (Utilita.chiediConferma(this.nome + ": 'Vuoi riprovare?'", this.nome + ": 'Sara' la volta buona, ahahahahah'", this.nome + ": 'Nel caso cambi idea io sono sempre qui'")) {
                                        this.interagisci(giocatore);
                                    }
                            break;
                        }
                    }

                }
            }
        }
    }

}
