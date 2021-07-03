/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import Threads.ThreadGioco;
import Threads.ThreadTempo;
import com.mycompany.progettomap.giochi.Gioco;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import logicaGioco.DescrizioneGioco;
import salvataggio.Deserializzazione;
import tipi.Utilita;

/**
 *
 * @author domen
 */
public class Menu {

    public static void MenuInizio() {
        boolean isExiting = false;
        String answer;
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        do {
            System.out.println("-------------------------------- Menu Principale "
                    + "--------------------------------");
            System.out.println("Digitare un comando valido... (digita 'help' per visualizzare i comandi)");
            if (scanner.hasNextLine()) {
                answer = scanner.nextLine();
                answer = answer.replaceAll(" +", "");
                switch (answer.toLowerCase()) {
                    case "help":
                        Help.stampaHelpMenuInizio();
                        break;
                    case "gioca":
                        menuDiGioco();
                        break;

                    case "esci":
                        isExiting = Utilita.chiediConferma("Si vuole davvero uscire?",
                                "Alla prossima partita!", "Non si è usciti dal gioco.");
                        break;

                    default:
                        System.out.println("Comando inserito non valido.");
                        System.out.println("Per sapere quali comandi sono validi digitare help.");
                        break;
                }
            }
        } while (!isExiting);

        scanner.close();
    }

    public static void menuDiGioco() {
        boolean isExiting = false;
        String answer;
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        DescrizioneGioco gioco;
        do {
            System.out.println("-------------------------------- Menu Di Gioco "
                    + "--------------------------------");
            System.out.println("Digitare un comando valido... (digita 'help' per visualizzare i comandi)");
            if (scanner.hasNextLine()) {
                answer = scanner.nextLine();
                answer = answer.replaceAll(" +", "");
                switch (answer.toLowerCase()) {
                    case "help":
                        Help.stampaHelpMenuGioco();
                        break;
                    case "inizia":

                        ThreadTempo.Time();
                        gioco = Menu.creaPartita();
                        gioco.gioca();
                        break;
                    case "continua":
                        gioco = Deserializzazione.caricamento();
                        if (gioco != null) {
                            ThreadTempo.Time();
                            gioco.continua();
                        }

                        break;

                    case "indietro":
                        MenuInizio();
                        break;

                    default:
                        System.out.println("Comando inserito non valido.");
                        System.out.println("Per sapere quali comandi sono validi digitare help.");
                        break;
                }
            }
        } while (!isExiting);

        scanner.close();
    }

    public static DescrizioneGioco creaPartita() {
        List<DescrizioneGioco> l = Deserializzazione.letturaFile();
        DescrizioneGioco partita;
        boolean isExiting = false;
        String answer = "";
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        do {
            System.out.println("inserire il nome");
            if (scanner.hasNextLine()) {
                answer = scanner.nextLine();
                answer = answer.replaceAll(" +", "");
                for(DescrizioneGioco g : l){
                    if(g.getNomeGiocatore().equals(answer)){
                        isExiting = true;
                        System.out.println("Esiste gia' una partita con questo nome, riprovare");
                    }
                }
            }
        } while (isExiting);
        partita = new Gioco(answer);
        return partita;
    }

}
