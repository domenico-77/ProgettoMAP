/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import DataBase.Db;
import Threads.ThreadTempo;
import com.sun.glass.events.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import logicaGioco.DescrizioneGioco;
import salvataggio.Deserializzazione;
import salvataggio.Serializzazione;
import tipi.Utilita;

/**
 *
 * @author mtubi
 */
public class CancellaPartita extends javax.swing.JPanel {

    /**
     * Creates new form CancellaPartita
     */
    public CancellaPartita(MainSwing ms) {
        initComponents();
        this.ms = ms;
        this.visualizzaPartite.setEditable(false);
    }

    private MainSwing ms;
    private List<DescrizioneGioco> partiteSalvate = new ArrayList();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        visualizzaPartite = new javax.swing.JTextArea();
        cancella = new javax.swing.JButton();
        indietro = new javax.swing.JButton();
        nomePartita = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        visualizzaPartite.setColumns(20);
        visualizzaPartite.setRows(5);
        jScrollPane1.setViewportView(visualizzaPartite);

        cancella.setBackground(new java.awt.Color(0, 204, 0));
        cancella.setText("CANCELLA");
        cancella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellaActionPerformed(evt);
            }
        });

        indietro.setBackground(new java.awt.Color(255, 51, 51));
        indietro.setText("INDIETRO");
        indietro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indietroActionPerformed(evt);
            }
        });

        nomePartita.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nomePartita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nomePartitaKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INSERIRE LA PARTITA DA CANCELLARE :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(nomePartita, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(cancella, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(155, 155, 155))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(indietro, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cancella, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nomePartita, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                .addGap(76, 76, 76)
                .addComponent(indietro, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancellaActionPerformed
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
                boolean rispostaCorretta = false;
                DescrizioneGioco partita = partiteSalvate.get(pos);
                String risposta = "";
                do {
                    rispostaCorretta = false;
                    risposta = JOptionPane.showInputDialog(this.ms.getFrame(), "Sei sicuro di voler cancellare la partita digitare 'si' o 'no'.", null);
                    risposta = risposta.toLowerCase();
                    switch (risposta) {
                        case "si":
                        case "sÃ¬":
                            rispostaCorretta = true;
                            break;
                        case "no":
                            rispostaCorretta = true;
                            break;
                        default:
                            rispostaCorretta = false;

                    }
                } while (rispostaCorretta);
                if (risposta.equals("si") || risposta.equals("sÃ¬")) {
                    Db db = Db.getDb();
                    db.Cancella(pos, np);
                    db.chiudiConnessione();
                    this.partiteSalvate.remove(pos);
                    try {
                        Serializzazione.scriviFileLista(this.partiteSalvate);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(CancellaPartita.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_cancellaActionPerformed

    private void nomePartitaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomePartitaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
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
                boolean rispostaCorretta = false;
                DescrizioneGioco partita = partiteSalvate.get(pos);
                String risposta = "";
                do {
                    rispostaCorretta = false;
                    risposta = JOptionPane.showInputDialog(this.ms.getFrame(), "Sei sicuro di voler cancellare la partita digitare 'si' o 'no'.", null);
                    risposta = risposta.toLowerCase();
                    switch (risposta) {
                        case "si":
                        case "sÃ¬":
                            rispostaCorretta = true;
                            break;
                        case "no":
                            rispostaCorretta = true;
                            break;
                        default:
                            rispostaCorretta = false;

                    }
                } while (!rispostaCorretta);
                if (risposta.equals("si") || risposta.equals("sÃ¬")) {
                    Db db = Db.getDb();
                    db.Cancella(partita.getId(),partita.getNomeGiocatore());    
                    db.chiudiConnessione();
                    this.partiteSalvate.remove(pos);
                    this.nomePartita.setText("");
                    try {
                        Serializzazione.scriviFileLista(this.partiteSalvate);
                        Deserializzazione.visualizzaPartiteSwing(visualizzaPartite);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(CancellaPartita.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        }
    }//GEN-LAST:event_nomePartitaKeyPressed

    private void indietroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indietroActionPerformed
        this.ms.getFrame().setContentPane(this.ms.getMenuInizio());
        this.ms.getFrame().validate();
    }//GEN-LAST:event_indietroActionPerformed

    public void setPartiteSalvate(List<DescrizioneGioco> partiteSalvate) {
        this.partiteSalvate = partiteSalvate;
    }

    public JTextArea getVisualizzaPartite() {
        return visualizzaPartite;
    }

    public JTextField getNomePartita() {
        return nomePartita;
    }

    
    public List<DescrizioneGioco> getPartiteSalvate() {
        return partiteSalvate;
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancella;
    private javax.swing.JButton indietro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nomePartita;
    private javax.swing.JTextArea visualizzaPartite;
    // End of variables declaration//GEN-END:variables
}
