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
public class ParserOutput {
    public ParserOutput parse(String comandoUtente){
        ParserOutput comando=null;
        puliziaStringa(comandoUtente);
        
        
        return comando;
    }
    
    public void puliziaStringa(String comandoUtente){
        List<String> articoli = new ArrayList();
        comandoUtente=comandoUtente.replaceAll("[.!£$%&/]+", "");//Pulizia del comando di caratteri di punteggiatura
        for(String articolo : articoli){
             comandoUtente=comandoUtente.replace(articolo, "");//cancellazione di tutti i tipi di articoli all'interno del comando
        }
    }
}

