/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npc;

import oggetti.Oggetto;
import tipi.Giocatore;

/**
 *
 * @author mtubi
 */
public class PngIndovinello extends Npc{
    private static final boolean NEUTRALE = true;
    private static final boolean SCONOSCIUTO = true;
    
    private String indovinello;
    private String rispostaA;
    private String rispostaB;
    private String rispostaC;
    private char rispostaEsatta;
    private boolean accontentato = false;
    private boolean sconosciuto = false;

    public PngIndovinello(String nome, boolean vivo, int vita, Oggetto oggetto, String indovinello, String rispostaA, String rispostaB, String rispostaC) {
        super(nome, vivo, vita, oggetto, NEUTRALE);
    }

    @Override
    public void interagisci(Giocatore giocatore) {
        
    }
    
    
}
