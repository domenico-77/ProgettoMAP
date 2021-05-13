/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progettomap.parser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mtubi
 */
public class Parser {
    public ParserOutput parse(String comandoUtente){
        ParserOutput comando=null;
        comandoUtente=comandoUtente.toLowerCase();
        comandoUtente=comandoUtente.replaceAll("[.!£$%&/]+", "");//Pulizia del comando di caratteri di punteggiatura
        String[] paroleComando=comandoUtente.split("\\s+|'");//Divido il comando in base agli spazi in modo da esaminare parola per parola
        if(paroleComando.length>0){//controllo che dopo le varie pulizie la stringa non sia vuota
            comando=esaminaParole(paroleComando);
        }
        return comando;
    }
    
    public ParserOutput esaminaParole(String[] paroleComando){
        ParserOutput comando=null;
        List<String> articoli= new ArrayList();
        for(int i=0; i<paroleComando.length;i++){
            if(articoli.indexOf(paroleComando[i])==-1){
                
            }
            i++;
        }
        return comando;
    }
            
}
