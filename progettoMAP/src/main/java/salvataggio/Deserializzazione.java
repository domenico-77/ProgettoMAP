/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salvataggio;

import DataBase.Db;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import logicaGioco.DescrizioneGioco;

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
            List<DescrizioneGioco> l2 = new ArrayList();

        }

        return l;
    }

   

   
    
    public static void visualizzaPartiteSwing( JTextArea out){
        List<DescrizioneGioco> l = Deserializzazione.letturaFile();
        if(!l.isEmpty()){
        out.setText("");
         l.forEach(d -> {
            out.append(d.getNomeGiocatore() + "\n");
        });
        }
        else{
            out.append("Non ci sono partite salvate");
        }
    }

    

    public static void cancellaPartitaFinita(DescrizioneGioco partitaFinita) throws FileNotFoundException {
        List<DescrizioneGioco> l = Deserializzazione.letturaFile();
        if (!l.isEmpty()) {
            int i = -1;
            for (DescrizioneGioco g : l) {
                if (g.getNomeGiocatore().equals(partitaFinita.getNomeGiocatore())) {
                    i = l.indexOf(g);
                }
            }
            if (i != -1) {
               Db db = Db.getDb();
                l.remove(i);
                Serializzazione.scriviFileLista(l);
                db.aggiorna(partitaFinita.getId(), partitaFinita.getNomeGiocatore(), partitaFinita.getGiocatore().getPunteggio(), partitaFinita.isFinita(), (partitaFinita.getGiocatore().getVitaCorrente() > 0));
            } 
        }
    }
}
