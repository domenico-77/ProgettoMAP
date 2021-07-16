/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

import java.io.Serializable;
import java.util.Set;
import javax.swing.JTextArea;
import tipi.Giocatore;
import tipi.stanze.Stanza;

/**
 *
 * @author mtubi
 */
public class Spada extends Oggetto implements Serializable {

    private final static int USABILITA = 3;
    private final static boolean PRENDIBILE = true;
    private final static int PUNTEGGIO = 10;
    private final static TipoOggetto TIPO_OGGETTO = TipoOggetto.spada;

    public Spada(String nome, Set<String> alias) {
        super(nome, alias, PRENDIBILE, USABILITA, TIPO_OGGETTO);
    }

    

    public static int getUSABILITA() {
        return USABILITA;
    }

    public static boolean isPRENDIBILE() {
        return PRENDIBILE;
    }

    public static TipoOggetto getTIPO_OGGETTO() {
        return TIPO_OGGETTO;
    }

    @Override
    public void usaSwing(Giocatore giocatore, Stanza stanza, JTextArea out) {
        String nome;
        if (usabilita > 0) {
            if (stanza.getNpc() != null) {
                if (stanza.getNpc().isVivo()) {
                    stanza.getNpc().setVivo(false);
                    this.usabilita --;
                    giocatore.incrementaPunteggio(Spada.PUNTEGGIO);
                    if(stanza.getNpc().isSconosciuto()){
                        nome = "Sconosciuto";
                    }
                    else{
                        nome = stanza.getNpc().getNome();
                    }
                    out.append(nome + ": 'Oh no maledetto, mi hai colpito alle spalle!'\n");
                    if (this.usabilita == 0) {
                        out.append("Rin: 'Non hai affilato bene la spada, ora si è rotta, speriamo di trovarne un altra'\n");
                    } 
                }
                else{
                    out.append("Rin: 'La persona e' stata gia' uccisa, non serve a niente infierire sul suo corpo'\n");
                }
            } else {
                out.append("Rin: 'Non c'e' nessuno in questa stanza'\n");
            }
        } else {
            out.setText("Rin: 'Non puoi usare questo oggetto'\n");
        }
    }

    @Override
    public void descrizioneOggetto(JTextArea out) {
         out.append("Rin: 'E' " + this.nome + " ci servira' nel caso in cui incontriamo dei soldati, puoi usarla per " + this.usabilita + " volte");
        if (this.usabilita < 3) {
            out.append(" \n sarebbe il caso di trovare un affilatore, per aumentare l'usabilita prima che si rompa'");
        } else {
            out.append("'\n");
        }
    }

}
