/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import com.mycompany.progettomap.giochi.Gioco;
import java.io.InputStreamReader;
import java.util.Scanner;
import logicaGioco.DescrizioneGioco;

/**
 *
 * @author domen
 */
public class ThreadGioco {

    public static void Esegui() {
String nome = "ciola";
        Thread p = new Thread(() -> {
            while (true) {
                try {
                    DescrizioneGioco gioco = new Gioco(nome);
                    gioco.gioca();
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        p.start();
        
    }
}
