/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salvataggio;

import com.mycompany.progettomap.giochi.Gioco;
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
    
    public static List<DescrizioneGioco> creaLista(Gioco gioco){
        List<DescrizioneGioco> l= new ArrayList<>();
        l.add(gioco);
        return l;
    }
    
    public static void scriviLista(DescrizioneGioco partita) throws ClassNotFoundException{
        try {
            List<DescrizioneGioco> l = Deserializzazione.letturaFile();
            FileOutputStream fileOut = new FileOutputStream("./FileSalvataggio.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            if(l.contains(partita)){
                l.set(l.indexOf(partita), partita);
            }
            else{
                l.add(partita);
            }
            out.writeObject(l);
            out.close();
            fileOut.close();
            System.out.println("Dati serializzati salvati in FileSalvataggio.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
