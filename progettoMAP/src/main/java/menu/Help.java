/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

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
    
    public static void stampaHelpPartita(){
        System.out.println("");
        System.out.println("+------------------ HELP ------------------+");
        System.out.println("In questa avventura potrai digitare diversi comandi per interagire con l'ambiente di gioco:");
        System.out.println("");
        System.out.println("- 'osserva' per chiedere a Rin di descriverti la stanza");
        System.out.println("- 'osserva' + il nome di un oggetto, per chiedere a Rin di descrivere l'oggetto ");
        System.out.println("");
        System.out.println("- nord, 'sud', 'est' e 'ovest' per spostarti da una stanza all'altra");
        System.out.println("N.B.");
        System.out.println("Non sempre sarà possibile andare in qualsiasi direzione...attento alle porte chiuse...e sopratutto ai muri!!!");
        System.out.println("");
        System.out.println("'prendi' + il nome dell'oggeto da raccogliere, per metterlo nel tuo inventario ");
        System.out.println("");
        System.out.println("Ci sono altri comandi tutti da scoprire, quando hai un oggetto pensa a cosa potresti farci");
        System.out.println("");
        System.out.println("Per salvare basterà digitare il comando 'salva'");
        System.out.println("+------------------ FINE HELP ------------------+");
    }
}
