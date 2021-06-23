/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progettomap.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import tipi.Comando;
import oggetti.Oggetto;
import tipi.Utilita;
import tipi.stanze.Porta;
import tipi.stanze.Stanza;

/**
 *
 * @author mtubi
 */
public class Parser {

    private Set<String> paroleProibite;

    public Parser(Set<String> paroleProibite) {
        this.paroleProibite = paroleProibite;
    }

    public ParserOutput parse(String comandoUtente, List<Comando> azioni, List<Oggetto> oggetti, List<Oggetto> inventario, Stanza stanza) {
        ParserOutput comando= null;
        comandoUtente = comandoUtente.toLowerCase();
        comandoUtente = comandoUtente.replaceAll("[.!£$%&/]+", "");//Pulizia del comando di caratteri di punteggiatura
        comandoUtente = comandoUtente.replaceAll("'", " ");//Elimino l'apostrofo e lo sotituisco con uno spazio per individuare l'articolo
        List<String> paroleComando = pulisciStringa(comandoUtente);//Divido il comando in base agli spazi in modo da esaminare parola per parola
        if (!paroleComando.isEmpty()) {//controllo che dopo le varie pulizie la stringa non sia vuota
            int intAzione = Utilita.cercaAzioni(paroleComando.get(0), azioni);
            if(intAzione > -1){//Se l'utente non ha scritto un azione l'intero comando non è valido
                int intOggetto = Utilita.cercaOggetto(paroleComando.get(1), oggetti);
                int intOggettoInv = -1;//Inizializzo una variabile per l'ggetto dell'inventario
                boolean portaB = false;
                if(intOggetto == -1){//Se non è stato trovato l'oggetto nella seconda posizione, provo a cercarlo nella terza posizione e cerco un oggetto dell'inventario nella seconda
                    intOggetto = Utilita.cercaOggetto(paroleComando.get(2), oggetti);
                    intOggettoInv = Utilita.cercaOggetto(paroleComando.get(1), inventario);
                    if(intOggettoInv == -1 && intOggetto == -1){//Se non ho trovato l'oggetto nella terza posizione e non ho trovato un oggetto dell'inventario nella seconda, cerco l'oggetto dell'inventario nella terza
                        intOggettoInv = Utilita.cercaOggetto(paroleComando.get(2), inventario);
                    }
                }
                else{//Se abbiamo trovato un oggetto, provo a vedere se c'è anche un oggetto dell'inventario nella terza
                    intOggettoInv = Utilita.cercaOggetto(paroleComando.get(2), inventario);
                }
                if(intOggetto == -1 && intOggettoInv == -1){
                    portaB = Utilita.cercaParola(paroleComando.get(1), Porta.getAlias());
                }
                if(intAzione > -1 && intOggetto > -1 && intOggettoInv >-1){
                    comando = (new ParserOutput(azioni.get(intAzione), oggetti.get(intOggetto), inventario.get(intOggettoInv)));
                }
                else if(intAzione > -1 && intOggetto > -1 && intOggettoInv == -1){
                    comando = (new ParserOutput(azioni.get(intAzione), oggetti.get(intOggetto)));
                }
                else if(intAzione > -1 && intOggetto == -1 && intOggettoInv > -1){
                    comando = (new ParserOutput(azioni.get(intAzione), null, inventario.get(intOggettoInv)));
                }
                else if(intAzione > -1 && intOggetto == -1 && intOggettoInv == -1){
                    comando = (new ParserOutput(azioni.get(intAzione)));
                }
            }
        }
        return comando;
    }

    public void stampaParole(List<String> paroleComando) {
        for (String parola : paroleComando) {
            System.out.println(parola);
        }
    }

    public List<String> pulisciStringa(String comando) {
        String[] appoggio = comando.split("\\s+");
        List<String> paroleComando = new ArrayList();
        for (int i = 0; i < appoggio.length; i++) {
            if (!paroleProibite.contains(appoggio[i])) {
                paroleComando.add(appoggio[i]);
            }
        }
        return paroleComando;
    }

}
