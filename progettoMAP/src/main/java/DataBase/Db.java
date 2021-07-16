/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JTextArea;

/**
 *
 * @author Acer
 */
public class Db {

    private static Db db = null;
    private Connection connessione;

    private Db() {
        try {
            this.connessione = DriverManager.getConnection("jdbc:h2:./risorse/db/Dati");
            String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Dati (id INT AUTO_INCREMENT, nomeGiocatore VARCHAR(30), data DATE, punteggio INT, giocoTerminato BOOLEAN, vivo BOOLEAN, PRIMARY KEY(id, nomeGiocatore))";
            Statement stm = this.connessione.createStatement();
            stm.executeUpdate(CREATE_TABLE);
            stm.close();

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());

        }

    }

    public static Db getDb() {
        if (Db.db == null) {
            Db.db = new Db();
        }
        return Db.db;
    }

    public int inserisci(String nome, int punteggio, boolean giocoTerminato, boolean vivo) {
        int id = 0;

        try {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            PreparedStatement pstm = this.connessione.prepareStatement("INSERT INTO Dati VALUES (NULL,?,?,?,?,?)");
            pstm.setString(1, nome);
            pstm.setDate(2, sqlDate);
            pstm.setInt(3, punteggio);
            pstm.setBoolean(4, giocoTerminato);
            pstm.setBoolean(5, vivo);
            pstm.executeUpdate();
            pstm.close();
            Statement stm = this.connessione.createStatement();
            ResultSet rs = stm.executeQuery("SELECT MAX(id) FROM Dati");
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());

        }
        return id;

    }

    public void aggiorna(int id, String nome, int punteggio, boolean giocoTerminato, boolean vivo) {
        try {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            PreparedStatement pstm = this.connessione.prepareStatement("UPDATE Dati SET punteggio=?, giocoTerminato=?, vivo=?, data=? WHERE id=? AND nomeGiocatore=?");
            pstm.setInt(1, punteggio);
            pstm.setBoolean(2, giocoTerminato);
            pstm.setBoolean(3, vivo);
            pstm.setDate(4, sqlDate);
            pstm.setInt(5, id);
            pstm.setString(6, nome);
            pstm.executeUpdate();
            pstm.close();

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());

        }
    }

    

    public void chiudiConnessione() {
        try {
            this.connessione.close();
            Db.db = null;

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());

        }

    }

    public void Cancella(int id, String Nome) {
        try {
            PreparedStatement pstm = this.connessione.prepareStatement("DELETE FROM Dati WHERE id=? AND nomeGiocatore=?");
            pstm.setInt(1, id);
            pstm.setString(2, Nome);
            pstm.executeUpdate();
            pstm.close();

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }

    public void visualizzaSwing(JTextArea spazioTesto) {
        String stringa = "";
        
        try {
            Statement stm = this.connessione.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Dati ORDER BY punteggio DESC");
            if (rs.next() == false) {
                stringa = "non ci sono partite";
            } else {
                do {
                    stringa = stringa + ("IDPARTITA: " + rs.getInt(1) + " NOMEGIOCATORE: " + rs.getString(2) + " DATASALVATAGGIO: " + rs.getDate(3) + " PUNTEGGIO: " + rs.getInt(4) + " GIOCOTERMINATO: " + rs.getBoolean(5) + "  VIVO: " + rs.getBoolean(6) + "\n \n");
                } while (rs.next());
            }
            rs.close();
            stm.close();
            spazioTesto.setText(stringa);

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());

        }

    }
}
