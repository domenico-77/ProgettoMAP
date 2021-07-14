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
import tipi.Giocatore;
import tipi.Utilita;

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
    public void interagisci(Giocatore giocatore) {
        if(!this.vivo){
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
        }
        else{
            if(this.corrotto){
                System.out.println(this.nome + ": 'Vai via prima che cambi idea'");
            }
            else{
                if(this.sconosciuto){
                    System.out.println(this.nome + ": 'Io sono la guardia " + this.nome + "'");
                    this.sconosciuto = false;
                    this.interagisci(giocatore);
                }
                else{
                    if(this.corrompibile){
                        System.out.println(this.nome + ": 'Se mi dai " + this.oggetto.getNome() + " potrei non averti visto passare di qui'");
                        if(giocatore.getInventario().contieneOggetto(this.oggetto)){
                            if(Utilita.chiediConferma("Rin: 'Abbiamo l'oggetto richiesto, vogliamo darglielo?'", "Rin: 'Perfetto un problema in meno!'", this.nome + ": 'Hai sprecato la tua occasione di scappare, ora ne subirai le conseguenze'")){
                                this.corrotto = true;
                                giocatore.getInventario().rimuoviOggetto(this.oggetto);
                                this.interagisci(giocatore);
                            }
                            else{
                                this.corrompibile = false;
                            }
                        }
                        else{
                            System.out.println("Rin: 'non abbiamo questo oggetto, ci conviene scappare!'");
                            this.corrompibile = false;
                        }
                    }
                    else{
                        if(this.distanza == 0){
                            System.out.println(this.nome + ": 'Prendi questo, MALEDETTO!!!'");
                            giocatore.decrementaVita(this.danno);
                            this.distanza = Mob.DISTANZA;
                            if(giocatore.getVitaCorrente() == 0){
                                System.out.println(this.nome + "'La prossima volta attento a quello che fai, non puoi metterti contro l'imperatore!'");
                            }
                            else{
                                System.out.println("Rin: 'Oh no! Ti ha colpito, non potremo resistere ancora per molto, dobbiamo sconfiggerlo il prima possibile altrimenti ci converrebbe scappare...finchè possiamo! ");
                            }
                        }
                        else{
                            this.distanza --;
                            System.out.println(this.nome + ": 'Sto arrivando, non posso tollerare che un prigioniero cammini per la prigione'");
                            if(this.distanza == 0){
                                System.out.println("Rin: 'Oh no e' troppo vicino, ci colpira' se non facciamo qualcosa'");
                            }
                            else{
                            System.out.println("Rin: 'Decidiamo in fretta cosa fare con lui, prima che ci faccia del male, ora e' distante " + this.distanza + " passi da noi'");
                            }
                        }
                    }
                }
            }
        }
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
