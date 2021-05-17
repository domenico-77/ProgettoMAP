/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.mycompany.progettomap.parser.Parser;
import tipi.Utilita;

/**
 *
 * @author mtubi
 */
public class MainProvvisorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Parser prova=new Parser(Utilita.caricaFileSet("./risorse/articoli.txt"));
       prova.parse("Sono la mamma e ho comprato la torta e i bicchieri per il tuo compleanno che festeggerai stasera");
    }
    
}
