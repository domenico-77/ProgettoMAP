/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempo;

import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author domen
 */
public class tempo {

    static final int MAX_SEC = 59;
    static final int MAX_MIN = 59;
    static int secondi = 0;
    static int minuti = 0;
    static int ore = 0;
    static Timer timer;

    public static void Time() {

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                if(secondi == MAX_SEC){
                    if(minuti == MAX_MIN){
                        secondi = 0;
                        minuti = 0;
                        ore ++;
                    }
                    else{
                        secondi = 0;
                        minuti ++;
                    }
                }
                else{
                    secondi ++;
                }
            }
        };

        //creazione thread che stampa i valori
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Scanner scanner = new Scanner(new InputStreamReader(System.in));
                    String x= scanner.nextLine();
                    if("tempo".equals(x)){
                        System.out.println("Tempo passato: " + ore+":"+minuti+":"+secondi);
                    }
                    //qua
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        timer = new Timer("MyTimer");
        timer.scheduleAtFixedRate(timerTask, 10, 1000);//start timer

        t.start();//start thread
    }
}
