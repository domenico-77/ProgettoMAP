/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.mycompany.progettomap.giochi.Gioco;
import com.mycompany.progettomap.parser.Parser;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import logicaGioco.DescrizioneGioco;
import oggetti.Oggetto;
import oggetti.Spada;
import tipi.Comando;
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
    
    public MainProvvisorio(DescrizioneGioco gioco){
        this.gioco = gioco;
        this.gioco.inizializza();
    }
    
    public static void main(String[] args) {
       Parser prova=new Parser(Utilita.caricaFileSet("./risorse/articoli.txt"));
       MainProvvisorio mainprov = new MainProvvisorio(new Gioco());
           mainprov.gioco.stampaStanze();
       Oggetto o = new Spada("spada", Utilita.generaSetAlias(), Utilita.generaListaComandi());
       List<Oggetto> l = new ArrayList();
       l.add(o);
       Oggetto og = new Spada("spada", Utilita.generaSetAlias(), Utilita.generaListaComandi());
       if(l.contains(og)){
           int i = l.indexOf(og);
           l.get(i).descrizioneOggetto();
       }
       else{
                System.out.println("COGLIONE");
       }
    }
    
}
