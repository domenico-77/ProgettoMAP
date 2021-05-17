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

/**
 *
 * @author mtubi
 */
public class Parser {
    private Set<String> paroleProibite;

    public Parser(Set<String> paroleProibite) {
        this.paroleProibite = paroleProibite;
    }

    public ParserOutput parse(String comandoUtente) {
        ParserOutput comando = null;
        comandoUtente = comandoUtente.toLowerCase();
        comandoUtente = comandoUtente.replaceAll("[.!£$%&/]+", "");//Pulizia del comando di caratteri di punteggiatura
        comandoUtente = comandoUtente.replaceAll("'", " ");//Elimino l'apostrofo e lo sotituisco con uno spazio per individuare l'articolo
        List<String> paroleComando = pulisciStringa(comandoUtente);//Divido il comando in base agli spazi in modo da esaminare parola per parola
        if (!paroleComando.isEmpty()) {//controllo che dopo le varie pulizie la stringa non sia vuota
            stampaParole(paroleComando);
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
