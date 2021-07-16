/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import DataBase.Db;
import Threads.ThreadMusica;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import salvataggio.Deserializzazione;

/**
 *
 * @author domen
 */
public class menuInizio extends JPanel {

    private mainSwing ms;

    /**
     * Creates new form menuInizio
     */
    public menuInizio(mainSwing ms) {
        initComponents();
        this.ms = ms;
        this.setBounds(100, 100, 600, 300);
        this.setBackground(Color.black);
        Titolo.setForeground(Color.white);
        Titolo.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        inizia.setBackground(Color.black);
        inizia.setForeground(Color.white);
        inizia.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        inizia.setFocusPainted(false);
        inizia.setBorder(null);
        DataBase.setBackground(Color.black);
        DataBase.setForeground(Color.white);
        DataBase.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        DataBase.setFocusPainted(false);
        DataBase.setBorder(null);
        continuaPartita.setBackground(Color.black);
        continuaPartita.setForeground(Color.white);
        continuaPartita.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        continuaPartita.setFocusPainted(false);
        continuaPartita.setBorder(null);
        esci.setBackground(Color.black);
        esci.setForeground(Color.white);
        esci.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        esci.setFocusPainted(false);
        esci.setBorder(null);
        cancellaPartita.setBackground(Color.black);
        cancellaPartita.setForeground(Color.white);
        cancellaPartita.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        cancellaPartita.setFocusPainted(false);
        cancellaPartita.setBorder(null);
        
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inizia = new javax.swing.JButton();
        DataBase = new javax.swing.JButton();
        Titolo = new javax.swing.JLabel();
        continuaPartita = new javax.swing.JButton();
        esci = new javax.swing.JButton();
        cancellaPartita = new javax.swing.JButton();
        muta = new javax.swing.JButton();
        smuta = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 0));

        inizia.setText("NUOVA PARTITA");
        inizia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniziaActionPerformed(evt);
            }
        });

        DataBase.setBackground(new java.awt.Color(255, 255, 255));
        DataBase.setText("CLASSIFICA");
        DataBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataBaseActionPerformed(evt);
            }
        });

        Titolo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titolo.setText(" LA FUGA DI MANJI");

        continuaPartita.setText("CONTINUA");
        continuaPartita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuaPartitaActionPerformed(evt);
            }
        });

        esci.setBackground(new java.awt.Color(0, 0, 0));
        esci.setForeground(new java.awt.Color(255, 255, 255));
        esci.setText("ESCI");
        esci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esciActionPerformed(evt);
            }
        });

        cancellaPartita.setBackground(new java.awt.Color(0, 0, 0));
        cancellaPartita.setForeground(new java.awt.Color(255, 255, 255));
        cancellaPartita.setText("CANCELLA PARTITA");
        cancellaPartita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellaPartitaActionPerformed(evt);
            }
        });

        muta.setBackground(new java.awt.Color(255, 204, 102));
        muta.setText("MUTE");
        muta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mutaActionPerformed(evt);
            }
        });

        smuta.setBackground(new java.awt.Color(255, 255, 102));
        smuta.setText("UNMUTE");
        smuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smutaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titolo, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(380, 380, 380)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancellaPartita)
                    .addComponent(esci)
                    .addComponent(DataBase)
                    .addComponent(continuaPartita)
                    .addComponent(inizia))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(smuta)
                .addGap(26, 26, 26)
                .addComponent(muta, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(Titolo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131)
                .addComponent(inizia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(continuaPartita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancellaPartita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DataBase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(esci)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(muta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(smuta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void iniziaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniziaActionPerformed
        this.ms.getCreaPartita().getNomePartita().setText("");
        this.ms.getFrame().setContentPane(this.ms.getCreaPartita());
        this.ms.getFrame().validate();
    }//GEN-LAST:event_iniziaActionPerformed

    private void DataBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataBaseActionPerformed
        Db db = Db.getDb();
        db.visualizzaSwing(this.ms.getVisualizzazioneDataBase().getVisualizzaDb());
        this.ms.getFrame().setContentPane(this.ms.getVisualizzazioneDataBase());
        this.ms.getFrame().validate();
    }//GEN-LAST:event_DataBaseActionPerformed

    private void continuaPartitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuaPartitaActionPerformed
       this.ms.getContinuaPartita().getNomePartita().setText("");
        this.ms.getContinuaPartita().setPartiteSalvate(Deserializzazione.letturaFile());
        Deserializzazione.visualizzaPartiteSwing(this.ms.getContinuaPartita().getVisualizzaPartiteSalvate());
        this.ms.getFrame().setContentPane(this.ms.getContinuaPartita());
        this.ms.getFrame().validate();
    }//GEN-LAST:event_continuaPartitaActionPerformed

    private void esciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esciActionPerformed
        System.exit(0);
    }//GEN-LAST:event_esciActionPerformed

    private void cancellaPartitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancellaPartitaActionPerformed
        this.ms.getCancellaPartita().getNomePartita().setText("");
        this.ms.getCancellaPartita().setPartiteSalvate(Deserializzazione.letturaFile());
        Deserializzazione.visualizzaPartiteSwing(this.ms.getCancellaPartita().getVisualizzaPartite());
        this.ms.getFrame().setContentPane(this.ms.getCancellaPartita());
        this.ms.getFrame().validate();
    }//GEN-LAST:event_cancellaPartitaActionPerformed

    private void mutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mutaActionPerformed
        // TODO add your handling code here:
        ThreadMusica.setVolumeOff();
        
    }//GEN-LAST:event_mutaActionPerformed

    private void smutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smutaActionPerformed
        ThreadMusica.setVolumeOn();
    }//GEN-LAST:event_smutaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DataBase;
    private javax.swing.JLabel Titolo;
    private javax.swing.JButton cancellaPartita;
    private javax.swing.JButton continuaPartita;
    private javax.swing.JButton esci;
    private javax.swing.JButton inizia;
    private javax.swing.JButton muta;
    private javax.swing.JButton smuta;
    // End of variables declaration//GEN-END:variables
}
