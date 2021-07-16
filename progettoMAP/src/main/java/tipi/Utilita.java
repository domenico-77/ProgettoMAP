/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

import oggetti.Oggetto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


/**
 *
 * @author mtubi
 */

public class Utilita {
    

    public static Set<String> caricaFileSet(String file) {
        Set<String> set = new HashSet<>();
        BufferedReader fileInput = null;
        try {
            fileInput = new BufferedReader(new FileReader(file));
            while (fileInput.ready()) {
                set.add(fileInput.readLine().trim().toLowerCase());
            }
            fileInput.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        return set;
    }

    public static int cercaAzioni(String token, List<Comando> comandi) {
        int comando = -1;
        for (Comando appoggio : comandi) {
            if (appoggio.getAlias().contains(token)) {
                comando = comandi.indexOf(appoggio);
            }
        }
        return comando;
    }

    public static int cercaOggetto(String token, List<Oggetto> oggetti) {
        int oggetto = -1;
        for (Oggetto appoggio : oggetti) {
            if (appoggio.getAlias().contains(token)) {
                oggetto = oggetti.indexOf(appoggio);
            }
        }
        return oggetto;
    }

    public static List<Oggetto> creaListaOggetti(Oggetto... o) {
        List<Oggetto> l = new ArrayList<>();
        for (int i = 0; i < o.length; i++) {
            l.add(o[i]);
        }
        return l;

    }

    public static List<Comando> generaListaComandi(Comando... c) {
        List<Comando> l = new ArrayList<>();
        l = Arrays.asList(c);
        return l;
    }

    public static Set<String> generaSetAlias(String... a) {
        Set<String> s = new HashSet<>();

        for (int i = 0; i < a.length; i++) {
            s.add(a[i]);
        }
        return s;

    }

    
    
  

    public static boolean cercaParola(String nome, String... alias) {
        boolean contiene = false;
        for (String a : alias) {
            if (nome.equals(a)) {
                contiene = true;
            }
        }
        return contiene;
    }

    public static boolean cercaParola(String nome, List<String> alias){
        boolean contiene = false;
        for (String a : alias){
            if(nome.equals(a)){
                contiene = true;
            }
        }
        return contiene;
    }
    
    public static boolean chiediConfermaSwing(final String richiesta, final String casoAffermativo, final String casoNegativo, JTextArea out, JFrame frame){
        boolean vuole = false;
        out.append(richiesta + "\n");
        boolean error;
        String answer;

        do {
            answer = JOptionPane.showInputDialog(frame, "digitare 'si' o 'no'.", null);
            error = false;
            if(answer != null){
                answer = answer.replaceAll(" +", "");
                switch (answer.toLowerCase()) {
                    case "si":
                    case "sÃ¬":
                        out.append(casoAffermativo + "\n");
                        vuole = true;
                        break;
                    case "no":
                        out.append(casoNegativo + "\n");
                        vuole = false;
                        break;
                    default:
                        out.append("Digitare una risposta valida... \n");
                        error = true;
                        break;
                }
            }
            else{
                out.setText("Digitare una risposta valida...\n");
            }
            
        } while (error);

        return vuole;
    }
        
}
