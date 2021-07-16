/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import Threads.ThreadMusica;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author domen
 */
public class mainSwing {

    private final JPanel menuInizio;
    private final giocoGui giocoGui;
    private final JFrame frame = new JFrame();
    private final visualizzaDataBase visualizzazioneDataBase;
    private final creaPartita creaPartita;
    private final CancellaPartita cancellaPartita;
    private final ContinuaPartita continuaPartita;

    public mainSwing() {
        cancellaPartita = new CancellaPartita(this);
        this.continuaPartita = new ContinuaPartita(this);
        giocoGui = new giocoGui(this);
        menuInizio = new menuInizio(this);
        visualizzazioneDataBase = new visualizzaDataBase(this);
        creaPartita = new creaPartita(this);
        frame.setContentPane(this.menuInizio);
        frame.setPreferredSize(new Dimension(970, 700));
        frame.setBounds(100, 100, 600, 300);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }

    public giocoGui getGiocoGui() {
        return giocoGui;
    }

    public JPanel getMenuInizio() {
        return menuInizio;
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThreadMusica.Music();
                mainSwing ms = new mainSwing();
                ms.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ms.getFrame().pack();
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }

    public visualizzaDataBase getVisualizzazioneDataBase() {
        return visualizzazioneDataBase;
    }

    public creaPartita getCreaPartita() {
        return creaPartita;
    }

    public ContinuaPartita getContinuaPartita() {
        return continuaPartita;
    }

    public CancellaPartita getCancellaPartita() {
        return cancellaPartita;
    }

    
}
