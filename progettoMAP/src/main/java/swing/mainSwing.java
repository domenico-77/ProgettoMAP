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
public class MainSwing {

    private JPanel menuInizio;
    private GiocoGui giocoGui;
    private JFrame frame = new JFrame();
    private VisualizzaDataBase visualizzazioneDataBase;
    private CreaPartita creaPartita;
    private CancellaPartita cancellaPartita;
    private ContinuaPartita continuaPartita;

    public MainSwing() {
        cancellaPartita = new CancellaPartita(this);
        this.continuaPartita = new ContinuaPartita(this);
        giocoGui = new GiocoGui(this);
        menuInizio = new MenuInizio(this);
        visualizzazioneDataBase = new VisualizzaDataBase(this);
        creaPartita = new CreaPartita(this);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this.menuInizio);
        frame.setPreferredSize(new Dimension(970, 700));
        frame.setBounds(100, 100, 600, 300);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }

    public GiocoGui getGiocoGui() {
        return giocoGui;
    }

    public JPanel getMenuInizio() {
        return menuInizio;
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThreadMusica.Music();
                MainSwing ms = new MainSwing();
                ms.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ms.getFrame().pack();
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }

    public VisualizzaDataBase getVisualizzazioneDataBase() {
        return visualizzazioneDataBase;
    }

    public CreaPartita getCreaPartita() {
        return creaPartita;
    }

    public ContinuaPartita getContinuaPartita() {
        return continuaPartita;
    }

    public CancellaPartita getCancellaPartita() {
        return cancellaPartita;
    }

    
}
