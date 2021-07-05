/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salvataggio;

import com.mycompany.progettomap.giochi.Gioco;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import logicaGioco.DescrizioneGioco;

/**
 *
 * @author domen
 */
public class Serializzazione {

    public static void scriviFile(DescrizioneGioco partita) throws ClassNotFoundException {
        try {
            List<DescrizioneGioco> l = Deserializzazione.letturaFile();
            FileOutputStream fileOut = new FileOutputStream("./risorse/FileSalvataggio.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            DescrizioneGioco gioco = null;

            /* if(l.contains(partita)){
                for(DescrizioneGioco g : l){
                    l.set(l.indexOf(l) ,partita);
                }
            }
            else{
                l.add(partita);              
            }*/
            int i = -1;
            for (DescrizioneGioco g : l) {
                if (g.getNomeGiocatore().equals(partita.getNomeGiocatore())) {
                    i = l.indexOf(g);
                }
            }
            if (i == -1) {
                l.add(partita);
            } else {
                l.set(i, partita);
            }
            out.writeObject(l);
            out.close();
            fileOut.close();
            System.out.println("Dati serializzati salvati in FileSalvataggio.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void scriviFileLista(List<DescrizioneGioco> l) throws FileNotFoundException {
        try{
        FileOutputStream fileOut = new FileOutputStream("./risorse/FileSalvataggio.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(l);
        out.close();
        fileOut.close();
        }catch (IOException i) {
            i.printStackTrace();
        }
    }
}
