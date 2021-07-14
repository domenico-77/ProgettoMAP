/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import Threads.ThreadTempo;
import com.sun.glass.events.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import logicaGioco.DescrizioneGioco;

/**
 *
 * @author mtubi
 */
public class ContinuaPartita extends javax.swing.JPanel {

    /**
     * Creates new form ContinuaPartita
     */
    public ContinuaPartita(mainSwing ms) {
        this.ms = ms;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        visualizzaPartiteSalvate = new javax.swing.JTextArea();
        nomePartita = new javax.swing.JTextField();
        invio = new javax.swing.JButton();
        esci = new javax.swing.JButton();

        visualizzaPartiteSalvate.setColumns(20);
        visualizzaPartiteSalvate.setRows(5);
        jScrollPane1.setViewportView(visualizzaPartiteSalvate);

        nomePartita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nomePartitaKeyPressed(evt);
            }
        });

        invio.setText("Invio");
        invio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invioActionPerformed(evt);
            }
        });

        esci.setText("Esci");
        esci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esciActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(nomePartita)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(invio)))
                .addContainerGap(128, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(esci)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(nomePartita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(invio)
                .addGap(18, 18, 18)
                .addComponent(esci)
                .addContainerGap(45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private mainSwing ms;
    private List<DescrizioneGioco> partiteSalvate;
    private void invioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invioActionPerformed
        String np = this.nomePartita.getText();
        int pos = -1;
        if (np.isEmpty()) {
            JOptionPane.showMessageDialog(this.ms.getFrame(), "Nome non valido", "Errore", JOptionPane.ERROR_MESSAGE);
            this.nomePartita.setText("");
        } else {
            for (DescrizioneGioco dg : partiteSalvate) {
                if (dg.getNomeGiocatore().equals(np)) {
                    pos = partiteSalvate.indexOf(dg);
                }
            }
            if (pos == -1) {
                JOptionPane.showMessageDialog(this.ms.getFrame(), "Nome non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                this.nomePartita.setText("");
            } else {
                DescrizioneGioco partita = partiteSalvate.get(pos);
                partita.setSospesa(false);
                this.ms.getGiocoGui().setPartita(partita);
                this.ms.getGiocoGui().getVisualizzazioneTesto().setText("Rin: 'Ben tornato, ora ci troviamo nel " + partita.getStanzaCorrente().getNomeStanza() + "'");
                ThreadTempo.Time();
                this.nomePartita.setText("");
                this.ms.getFrame().setContentPane(this.ms.getGiocoGui());
                this.ms.getFrame().validate();
            }
        }
    }//GEN-LAST:event_invioActionPerformed

    private void nomePartitaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomePartitaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String np = this.nomePartita.getText();
            int pos = -1;
            if (np.isEmpty()) {
                JOptionPane.showMessageDialog(this.ms.getFrame(), "Nome non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                this.nomePartita.setText("");
            } else {
                for (DescrizioneGioco dg : partiteSalvate) {
                    if (dg.getNomeGiocatore().equals(np)) {
                        pos = partiteSalvate.indexOf(dg);
                    }
                }
                if (pos == -1) {
                    JOptionPane.showMessageDialog(this.ms.getFrame(), "Nome non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    this.nomePartita.setText("");
                } else {
                    DescrizioneGioco partita = partiteSalvate.get(pos);
                    this.ms.getGiocoGui().setPartita(partita);
                    this.ms.getGiocoGui().getVisualizzazioneTesto().setText("Rin: 'Ben tornato, ora ci troviamo nel " + partita.getStanzaCorrente().getNomeStanza() + "'");
                    ThreadTempo.Time();
                    this.nomePartita.setText("");
                    this.ms.getFrame().setContentPane(this.ms.getGiocoGui());
                    this.ms.getFrame().validate();
                }
            }
        }
    }//GEN-LAST:event_nomePartitaKeyPressed

    public void setPartiteSalvate(List<DescrizioneGioco> partiteSalvate) {
        this.partiteSalvate = partiteSalvate;
    }

    public List<DescrizioneGioco> getPartiteSalvate() {
        return partiteSalvate;
    }

 

    public JTextArea getVisualizzaPartiteSalvate() {
        return visualizzaPartiteSalvate;
    }

    
    private void esciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esciActionPerformed
        this.ms.getFrame().setContentPane(this.ms.getMenuInizio());
        this.ms.getFrame().validate();
    }//GEN-LAST:event_esciActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton esci;
    private javax.swing.JButton invio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nomePartita;
    private javax.swing.JTextArea visualizzaPartiteSalvate;
    // End of variables declaration//GEN-END:variables
}
