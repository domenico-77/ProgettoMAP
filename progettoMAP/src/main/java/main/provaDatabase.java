/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import DataBase.Db;

/**
 *
 * @author Acer
 */
public class provaDatabase {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Db db = Db.getDb();
        /*db.Inserisci("anna", 0, false, true);
       
        
        db.Visualizza();
        /*db.aggiorna(1, "max", 50, true, true);
        db.aggiorna(2, "mario", 40, true, true);
        db.Cancella(2, "mario");
        db.Visualizza();
        db.Cancella(3, "anna");
        db.Inserisci("mino", 0, false, true);
        db.Visualizza();*/
        db.Inserisci("io", 10, true, true);
                db.Visualizza();

       
    }
    
}
