/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    
    public static int cercaAzioni(String token, List<Comando> comandi){
        int comando = -1;
        for (Comando appoggio : comandi){
            if(appoggio.getAlias().contains(token)){
                comando = comandi.indexOf(appoggio);
            }
        }
        return comando;
    }
    
    public static int cercaOggetto(String token, List<Oggetto> oggetti){
        int oggetto = -1;
        for(Oggetto appoggio : oggetti){
            if(appoggio.getAlias().contains(token)){
                oggetto = oggetti.indexOf(appoggio);
            }
        }
        return oggetto;
    }
}
