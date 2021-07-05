/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.mycompany.progettomap.giochi.Gioco;
import com.mycompany.progettomap.parser.Parser;

import com.mycompany.progettomap.parser.ParserOutput;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;

import logicaGioco.DescrizioneGioco;
import npc.Npc;
import tipi.Comando;
import tipi.TipoComando;
import tipi.Utilita;

/**
 *
 * @author mtubi
 */
public class MainProvvisorio {

    /**
     * @param args the command line arguments
     */
    private final DescrizioneGioco gioco;

    private Parser parser = new Parser(Utilita.caricaFileSet("./risorse/articoli.txt"));

    public MainProvvisorio(DescrizioneGioco gioco) {
        this.gioco = gioco;
        this.gioco.inizializza();
    }

    public void esegui() {

        System.out.println("================================");
        System.out.println("* Adventure v. 0.2 - 2020-2021 *");
        System.out.println("================================");
        Scanner scanner = new Scanner(System.in);
        /*
        for(Comando c : gioco.getGiocatore().getListaMosse()){
            
        }
         */
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parse(command, gioco.getGiocatore().getListaMosse(), gioco.getStanzaCorrente().getOggetiStanza(), gioco.getGiocatore().getInventario().getInventario(), gioco.getStanzaCorrente(), Npc.getAlias());

            gioco.nextMove(p, System.out);
        }
    }

    public static void main(String[] args) {
        MainProvvisorio main = new MainProvvisorio(new Gioco());
        main.esegui();
    }

}
