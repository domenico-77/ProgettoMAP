/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salvataggio;

import com.mycompany.progettomap.giochi.Gioco;
import java.io.BufferedReader;
import java.io.FileInputStream;
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
            FileInputStream fileIn = new FileInputStream("./FileSalvataggio.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            BufferedReader br = new BufferedReader(new FileReader("./FileSalvataggio.ser"));
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
                gioco = new Gioco(nome);
            }

        } else {
            visualizzaPartite(l);
            Boolean risposta = false;
            DescrizioneGioco prova;
            do {
                nome = scanner.nextLine();
                if (nome.isEmpty()) {
                    System.out.println("Nome inserito non valido, reinserirne un altro");
                    risposta = true;
                } else {
                    /*prova = new Gioco(nome);
                        if(l.contains(prova)){
                           gioco = l.get(l.indexOf(prova));
                           risposta = false;*/
                    for (DescrizioneGioco g : l) {
                        if (g.getNomeGiocatore().equals(nome)) {
                            gioco = g;
                            
                        }
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

}
