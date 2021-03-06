/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npc;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import oggetti.Oggetto;
import giocatore.Giocatore;
import utilita.Utilita;

/**
 *
 * @author mtubi
 */
public class Mob extends Npc{
    private final static int VITA_MAX = 1;
    private final static int DANNO = 50;
    private final static int DISTANZA = 3;
    private final static boolean NEUTRALE = false;
    private final static String[] ALIAS_MOB = {"soldato","nemico","guardia","avversario"};
    
    
    private boolean corrotto = false;
    private boolean corrompibile;
    private final int vita = VITA_MAX;
    private final int danno = DANNO;
    private int distanza = DISTANZA;
    

    public Mob(String nome, Oggetto oggetto, boolean corrompibile) {
        super(nome, oggetto, NEUTRALE);
        this.corrompibile = corrompibile;
    }

    

    @Override
    public List<String> getAliasNome() {
         List<String> aliasNome = new ArrayList();
        for(String a : Npc.alias){
            aliasNome.add(a);
        }
        
         for(String a : Mob.ALIAS_MOB){
            aliasNome.add(a);
        }
        if(!this.sconosciuto){
            aliasNome.add(this.nome.toLowerCase());
        }
        else{
            aliasNome.add("prigioniero");
        }
        return aliasNome;
    }

    public boolean isCorrotto() {
        return corrotto;
    }

    @Override
    public void interagisci(Giocatore giocatore, JTextArea out, JFrame frame) {
        if(!this.vivo){
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
                    this.oggetto = null;
                } else {
                    out.append("Rin: 'Il cadavere non ha niente di interessante, possiamo andare'\n");
                }
            }
        }
        else{
            if(this.corrotto){
                out.append(this.nome + ": 'Vai via prima che cambi idea'\n");
            }
            else{
                if(this.sconosciuto){
                    out.append(this.nome + ": 'Io sono la guardia " + this.nome + "'\n");
                    this.sconosciuto = false;
                    this.interagisci(giocatore, out, frame);
                }
                else{
                    if(this.corrompibile){
                        out.append(this.nome + ": 'Se mi dai " + this.oggetto.getNome() + " potrei non averti visto passare di qui'\n");
                        if(giocatore.getInventario().contieneOggetto(this.oggetto)){
                            if(Utilita.chiediConfermaSwing("Rin: 'Abbiamo l'oggetto richiesto, vogliamo darglielo?'", "Rin: 'Perfetto un problema in meno!'", this.nome + ": 'Hai sprecato la tua occasione di scappare, ora ne subirai le conseguenze'", out, frame)){
                                this.corrotto = true;
                                giocatore.getInventario().rimuoviOggetto(this.oggetto);
                                this.interagisci(giocatore, out, frame);
                            }
                            else{
                                this.corrompibile = false;
                            }
                        }
                        else{
                            out.append("Rin: 'non abbiamo questo oggetto, ci conviene scappare!'\n");
                            this.corrompibile = false;
                        }
                    }
                    else{
                        if(this.distanza == 0){
                            out.append(this.nome + ": 'Prendi questo, MALEDETTO!!!'\n");
                            giocatore.decrementaVita(this.danno);
                            this.distanza = Mob.DISTANZA;
                            if(giocatore.getVitaCorrente() == 0){
                                out.append(this.nome + "'La prossima volta attento a quello che fai, non puoi metterti contro l'imperatore!'\n");
                            }
                            else{
                                out.append("Rin: 'Oh no! Ti ha colpito, non potremo resistere ancora per molto,  \n dobbiamo sconfiggerlo il prima possibile altrimenti ci converrebbe scappare...finch?? possiamo!' \n");
                            }
                        }
                        else{
                            this.distanza --;
                            out.append(this.nome + ": 'Sto arrivando, non posso tollerare che un prigioniero cammini per la prigione'\n");
                            if(this.distanza == 0){
                                out.append("Rin: 'Oh no e' troppo vicino, ci colpira' se non facciamo qualcosa'\n");
                            }
                            else{
                            out.append("Rin: 'Decidiamo in fretta cosa fare con lui, prima che ci faccia del male, ora e' distante " + this.distanza + " passi da noi'\n");
                            }
                        }
                    }
                }
            }
        }
    }
    
    
}
