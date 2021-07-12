/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import DataBase.Db;
import Threads.ThreadTempo;
import com.mycompany.progettomap.giochi.Gioco;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaGioco.DescrizioneGioco;
import salvataggio.Deserializzazione;
import salvataggio.Serializzazione;
import tipi.Utilita;

/**
 *
 * @author domen
 */
public class Menu {

    public static void menuDiGioco() throws FileNotFoundException {
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
                        isExiting = false;
                        break;
                    case "inizia":
                        gioco = Menu.creaPartita();
                        ThreadTempo.Time();
                        gioco.iniziaPartita();
                        break;
                    case "continua":
                        gioco = Deserializzazione.caricamento();
                        if (gioco != null) {
                            ThreadTempo.Time();
                            gioco.continua();
                        }
                        break;

                    case "database":
                        Db db = Db.getDb();
                        db.visualizza();
                        db.chiudiConnessione();
                        break;

                    case "cancella":
                        Deserializzazione.cancellaPartita();
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

    public static DescrizioneGioco creaPartita() {
        List<DescrizioneGioco> l = Deserializzazione.letturaFile();
        Db db = Db.getDb();
        DescrizioneGioco partita;
        boolean isExiting;
        String answer = "";
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        do {
            isExiting = false;
            System.out.println("inserire il nome");
            if (scanner.hasNextLine()) {
                answer = scanner.nextLine();
                answer = answer.replaceAll("\\s+", "");
                if (!answer.isEmpty()) {
                    for (DescrizioneGioco g : l) {
                        if (g.getNomeGiocatore().equals(answer)) {
                            isExiting = true;
                            System.out.println("Esiste gia' una partita con questo nome, riprovare");
                        }
                    }
                } else {
                    isExiting = true;
                    System.out.println("Nome inserito non valido");
                }
            }
        } while (isExiting);
        partita = new Gioco(answer, db.inserisci(answer, 0, false, true));
        db.chiudiConnessione();
        try {
            Serializzazione.scriviFile(partita);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Gioco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return partita;
    }

}
