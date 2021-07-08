/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salvataggio;

import DataBase.Db;
import com.mycompany.progettomap.giochi.Gioco;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import logicaGioco.DescrizioneGioco;
import tipi.Giocatore;
import tipi.Utilita;

/**
 *
 * @author domen
 */
public class Deserializzazione {

    public static List<DescrizioneGioco> letturaFile() {
        List<DescrizioneGioco> l = new ArrayList();
        try {
            FileInputStream fileIn = new FileInputStream("./risorse/FileSalvataggio.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            BufferedReader br = new BufferedReader(new FileReader("./risorse/FileSalvataggio.ser"));
            if (br.readLine() != null) {
                l = (List<DescrizioneGioco>) in.readObject();
            }
            in.close();
            fileIn.close();

        } catch (ClassNotFoundException i) {
            i.printStackTrace();
            return null;

        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());

        }

        return l;
    }

    public static DescrizioneGioco caricamento() {
        Db db = Db.getDb();
        List<DescrizioneGioco> l = Deserializzazione.letturaFile();
        DescrizioneGioco gioco = null;
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String nome;
        if (l.isEmpty()) {
            if (Utilita.chiediConferma("Non ci sono partite già avviate, vuoi iniziarne una nuova?", "Inserisci il nome", "Ritorna al menù di gioco")) {

                do {
                    nome = scanner.nextLine();
                    if (nome.isEmpty()) {
                        System.out.println("Nome inserito non valido, reinserirne un altro");
                    }
                } while (nome.isEmpty());
                gioco = new Gioco(nome, db.Inserisci(nome, 0, false, true));
                gioco.inizializza();
                db.chiudiConnessione();
            }

        } else {
            Deserializzazione.visualizzaPartite(l);
            Boolean risposta = false;
            do {
                nome = scanner.nextLine();
                if (nome.isEmpty()) {
                    System.out.println("Nome inserito non valido, reinserirne un altro");
                    risposta = true;
                } else {
                    int i = -1;
                    for (DescrizioneGioco g : l) {
                        if (g.getNomeGiocatore().equals(nome)) {
                            i = l.indexOf(g);
                        }
                    }
                    if (i != -1) {
                        gioco = l.get(i);
                        risposta = false;
                    }
                    if (gioco == null) {
                        if (Utilita.chiediConferma("Partita non trovata, vuoi riprovare?", "inserire nome partita da continuare", "Ritorna al menù di gioco")) {
                            risposta = true;

                        } else {
                            risposta = false;
                        }
                    }

                }
            } while (risposta);
        }

        return gioco;

    }

    public static void visualizzaPartite(List<DescrizioneGioco> l) {
        l.forEach(d -> {
            System.out.println(d.getNomeGiocatore());
        });
    }

    public static void cancellaPartita() throws FileNotFoundException {
        List<DescrizioneGioco> l = Deserializzazione.letturaFile();
        if (l.isEmpty()) {
            System.out.println("Non ci sono partite salvate");
        } else {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            String nome;
            Deserializzazione.visualizzaPartite(l);
            boolean risposta = false;
            do {
                if (scanner.hasNextLine()) {
                    nome = scanner.nextLine();
                    if (nome.isEmpty()) {
                        if (Utilita.chiediConferma("Nome inserito non valido, vuoi riprovare?", "inserisci il nome della partita che vuoi cancellare fra le partite elencate", "ritorno al menu di gioco")) {
                            risposta = true;
                        }
                    } else {
                        int i = -1;
                        for (DescrizioneGioco g : l) {
                            if (g.getNomeGiocatore().equals(nome)) {
                                i = l.indexOf(g);
                            }
                        }
                        if (i != -1) {
                            if (Utilita.chiediConferma("Sei sicuro di voler cancellare la pertita " + l.get(i).getNomeGiocatore(), "Cancellazione effettuata", "Annullata la cancellazione della partita " + l.get(i).getNomeGiocatore())) {
                                l.remove(i);
                                Serializzazione.scriviFileLista(l);
                            }
                            risposta = false;
                        }
                        else{
                            if(Utilita.chiediConferma("Partita non trovate, vuoi  riprovare?", "inserisci il nome della partita che vuoi cancellare fra le partite elencate", "ritorno al menu di gioco")){
                                risposta = true;
                            }
                            else {
                                risposta = false;
                            }
                        }
                    }
                }
            } while (risposta);

        }
    }
}
