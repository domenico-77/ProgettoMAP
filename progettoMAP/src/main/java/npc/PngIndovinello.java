/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import oggetti.Oggetto;
import tipi.Giocatore;
import tipi.Utilita;

/**
 *
 * @author mtubi
 */
public class PngIndovinello extends Npc implements Serializable {

    private static final boolean NEUTRALE = true;
    private static final String RISPOSTA_A = "a";
    private static final String RISPOSTA_B = "b";
    private static final String RISPOSTA_C = "c";
    private static final int PUNTEGGIO_MORTO = 50;
    private static final int PUNTEGGIO_ACCONTENTATO = 100;
    private static final String[] ALIAS_INDOVINELLO = {"prigioniero", "cantastorie", "giullare", "cantabanco"};

    private final String indovinello;
    private final String rispostaA;
    private final String rispostaB;
    private final String rispostaC;
    private final String rispostaEsatta;
    private boolean accontentato = false;

    public PngIndovinello(String nome, Oggetto oggetto, String indovinello, String rispostaA, String rispostaB, String rispostaC, String rispostaEsatta) {
        super(nome, oggetto, NEUTRALE);
        this.indovinello = indovinello;
        this.rispostaA = rispostaA;
        this.rispostaB = rispostaB;
        this.rispostaC = rispostaC;
        this.rispostaEsatta = rispostaEsatta;
    }

    

    @Override
    public List<String> getAliasNome() {
        List<String> aliasNome = new ArrayList();
        for (String a : Npc.alias) {
            aliasNome.add(a);
        }
        for (String a : PngIndovinello.ALIAS_INDOVINELLO) {
            aliasNome.add(a);
        }
        if (!this.sconosciuto) {
            aliasNome.add(this.nome.toLowerCase());
        } else {
            aliasNome.add("prigioniero");
        }
        return aliasNome;
    }

    @Override
    public void interagisci(Giocatore giocatore, JTextArea out, JFrame frame) {
        if (!this.vivo) {
            String nomeNpc;
            if (this.sconosciuto) {
                nomeNpc = "Sconosciuto";
            } else {
                nomeNpc = this.nome;
            }
            if (Utilita.chiediConfermaSwing("Rin: '" + nomeNpc + " e' morto, vuoi controllare il corpo? potrebbe avere qualcosa di utile!'", "Rin: 'Va bene, controlliamo il suo corpo'", "Rin: 'Andiamocene prima che il suo corpo inizi a puzzare!'", out, frame)) {
                if (this.oggetto != null) {
                    out.append("Hai raccolto: " + this.oggetto.getNome() + "\n");
                    giocatore.getInventario().aggiungiOggetto(this.oggetto);
                    giocatore.incrementaPunteggio(PngIndovinello.PUNTEGGIO_MORTO);
                    this.oggetto = null;
                } else {
                    out.append("Rin: 'Il cadavere non ha niente di interessante, possiamo andare'\n");
                }
            }
        } else {
            if (accontentato) {
                if (this.oggetto != null) {
                    out.append(this.nome + ": 'Tieni questo è quello che posso darti, per aiutarti nella tua fuga'\n");
                    giocatore.getInventario().aggiungiOggetto(this.oggetto);
                    giocatore.incrementaPunteggio(PngIndovinello.PUNTEGGIO_ACCONTENTATO);
                    out.append("Hai ottenuto: " + this.oggetto.getNome() + "\n");
                    this.oggetto = null;
                } else {
                    out.append(this.nome + ": 'Ti ho gia' dato tutto cio' che era in mio possesso, cos'altro vuoi ahahahahah'\n");
                }
            } else {
                if (this.sconosciuto) {
                    out.append("Sconosciuto: 'Ciao ho sentito parlare molto di te, se mi dai una mano ricambierò il favore'\n");
                    if (Utilita.chiediConfermaSwing("Rin: 'Vuoi sentire la proposta dello sconosciuto?'", "Rin: 'Vediamo cosa hai da offrire'", "Sconosciuto: 'Nel caso cambi idea io sono sempre qui'", out, frame)) {
                        this.sconosciuto = false;
                        out.append(this.nome + ": 'Piacere il mio nome e' " + this.nome + "'\n");
                        this.interagisci(giocatore, out, frame);
                    }
                } else {
                    String risposta;
                    out.append(this.nome + ": 'Ora ti faro' un indovinello, se mi darai una risposta giusta, ti premiero'!'\n");
                    out.append(this.nome + ": " + this.indovinello);
                    out.append(this.nome + ": le risposte sono:\n ");
                    out.append("a." + this.rispostaA + "\n");
                    out.append("b." + this.rispostaB + "\n");
                    out.append("c." + this.rispostaC + "\n");
                    risposta = JOptionPane.showInputDialog(frame, "digitare 'a' o 'b' o 'c'.", null);
                    risposta = risposta.toLowerCase();
                    if (risposta != null) {
                        switch (risposta) {
                            case RISPOSTA_A:
                                if (risposta.equals(this.rispostaEsatta)) {
                                    this.accontentato = true;
                                    out.append(this.nome + ": 'Complimenti la risposta e' giusta!' \n");
                                    this.interagisci(giocatore, out, frame);
                                } else {
                                    out.append(this.nome + ": 'La risposta e' errata!'\n");
                                    if (Utilita.chiediConfermaSwing(this.nome + ": 'Vuoi riprovare?'", this.nome + ": 'Sara' la volta buona, ahahahahah'", this.nome + ": 'Nel caso cambi idea io sono sempre qui'", out, frame)) {
                                        this.interagisci(giocatore, out, frame);
                                    }
                                }
                                break;

                            case RISPOSTA_B:
                                if (risposta.equals(this.rispostaEsatta)) {
                                    this.accontentato = true;
                                    out.append(this.nome + ": 'Complimenti la risposta e' giusta!'\n");
                                    this.interagisci(giocatore, out, frame);
                                } else {
                                    out.append(this.nome + ": 'La risposta e' errata!'\n");
                                    if (Utilita.chiediConfermaSwing(this.nome + ": 'Vuoi riprovare?'", this.nome + ": 'Sara' la volta buona, ahahahahah'", this.nome + ": 'Nel caso cambi idea io sono sempre qui'", out, frame)) {
                                        this.interagisci(giocatore, out, frame);
                                    }
                                }
                                break;

                            case RISPOSTA_C:
                                if (risposta.equals(this.rispostaEsatta)) {
                                    this.accontentato = true;
                                    out.append(this.nome + ": 'Complimenti la risposta e' giusta!'\n");
                                    this.interagisci(giocatore, out, frame);
                                } else {
                                    out.append(this.nome + ": 'La risposta e' errata!'\n");
                                    if (Utilita.chiediConfermaSwing(this.nome + ": 'Vuoi riprovare?'", this.nome + ": 'Sara' la volta buona, ahahahahah'", this.nome + ": 'Nel caso cambi idea io sono sempre qui'", out, frame)) {
                                        this.interagisci(giocatore, out, frame);
                                    }
                                }
                                break;
                            default:
                                out.append(this.nome + ": 'La risposta data non e' valida!\n");
                                if (Utilita.chiediConfermaSwing(this.nome + ": 'Vuoi riprovare?'", this.nome + ": 'Sara' la volta buona, ahahahahah'", this.nome + ": 'Nel caso cambi idea io sono sempre qui'", out, frame)) {
                                    this.interagisci(giocatore, out, frame);
                                }
                                break;
                        }
                    } else {
                        out.append(this.nome + ": 'La risposta data non e' valida!\n");
                        if (Utilita.chiediConfermaSwing(this.nome + ": 'Vuoi riprovare?'", this.nome + ": 'Sara' la volta buona, ahahahahah'", this.nome + ": 'Nel caso cambi idea io sono sempre qui'", out, frame)) {
                            this.interagisci(giocatore, out, frame);
                        }
                    }
                }
            }
        }
    }

}
