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
        db.Visualizza();
       /* int i = db.Inserisci("anna", 0, true, true);
        System.out.println(i);*/
        db.Visualizza();

    }

}
