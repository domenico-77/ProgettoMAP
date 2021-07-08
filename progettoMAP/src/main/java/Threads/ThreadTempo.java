/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

/**
 *
 * @author domen
 */
public class ThreadTempo {

    private static int minuti = 0;
    private static int ore = 0;
    private static final int MAX_SEC = 59;
    private static final int MAX_MIN = 59;
    private static int secondi = 0;
    private static boolean attivo = true;

    public static void Time() {
        
        Thread t = new Thread(() -> {
            ThreadTempo.secondi = 0;
            ThreadTempo.minuti = 0;
            ThreadTempo.ore = 0;
            while (attivo) {
                try {
                    if (secondi == MAX_SEC) {
                        if (minuti == MAX_MIN) {
                            secondi = 0;
                            minuti = 0;
                            ore++;
                        } else {
                            secondi = 0;
                            minuti++;
                        }
                    } else {
                        secondi++;
                    }

                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            
        });
        
        
        t.setDaemon(true);
        t.start();//start thread

    }

    public static int getMinuti() {
        return minuti;
    }

    public static int getOre() {
        return ore;
    }

    public static int getSecondi() {
        return secondi;
    }
    
    public static void reset(){
        secondi = 0;
        minuti = 0;
        ore = 0;
    }

    public static void setAttivo(boolean attivo) {
        ThreadTempo.attivo = attivo;
    }
    
    
}
