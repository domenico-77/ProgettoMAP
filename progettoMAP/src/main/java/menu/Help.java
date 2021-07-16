/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import javax.swing.JTextArea;

/**
 *
 * @author domen
 */
public class Help {
    

    /*public static void stampaHelpMenuInizio() {
        System.out.println("");
        System.out.println("+------------------ HELP ------------------+");
        System.out.println("Digitare 'gioca' per iniziare una nuova partita o caricarne una già avviata");
        System.out.println("Digitare 'esci' se si vuole chiudetere il gioco");
        System.out.println("+------------------ FINE HELP ------------------+");
    }*/
    
    public static void stampaHelpMenuGioco(){
        System.out.println("");
        System.out.println("+------------------ HELP ------------------+");
        System.out.println("Digitare 'inizia' per iniziare una nuova avventura");
        System.out.println("Digitare 'continua' per avviare una partita già iniziata");
        System.out.println("Digitare 'database' per visualizzare la classifica di gioco");
        System.out.println("Digitare 'esci' per terminare l' applicazione");
        System.out.println("+------------------ FINE HELP ------------------+");
    }
    
  
    
    public static void stampaHelpPartitaSwing(JTextArea out){
        out.append("+------------------ HELP ------------------+\n");
        out.append("In questa avventura potrai digitare diversi comandi per interagire con l'ambiente di gioco:\n");
        out.append("- 'osserva' per chiedere a Rin di descriverti la stanza\n");
        out.append("- 'osserva' + il nome di un oggetto, per chiedere a Rin di descrivere l'oggetto \n");
        out.append("- nord, 'sud', 'est' e 'ovest' per spostarti da una stanza all'altra\n");
        out.append("N.B.\n");
        out.append("Non sempre sarà possibile andare in qualsiasi direzione...attento alle porte chiuse...e sopratutto ai muri!!!\n");
        out.append("'prendi' + il nome dell'oggeto da raccogliere, per metterlo nel tuo inventario \n");
        out.append("Ci sono altri comandi tutti da scoprire, quando hai un oggetto pensa a cosa potresti farci\n");
        out.append("Per salvare basterà digitare il comando 'salva'\n");
        out.append("Ci sono anche dei bottoni utili per facilitare il gioco \n");
        out.append("- I bottoni 'nord', 'sud', 'ovest' e 'est' servono per eseguire i comandi di spostamento, senza scriverli ogni volta \n");
        out.append("- I bottoni 'muta' e 'smuta', servono per togliere il volume di gioco e mettere il volume di gioco \n");
        out.append("- Il bottone 'esci' serve per uscire dalla partita e tornare al menu principale \n");
        out.append("- Il bottone 'invia' serve per confermare un comando scritto nella casella di testo \n");
        out.append("+------------------ FINE HELP ------------------+\n");
    }
}
