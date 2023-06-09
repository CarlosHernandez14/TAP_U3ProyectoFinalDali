/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.tap_u3proyectofinaldali.documentos;

import com.mycompany.data.WSManager;
import com.mycompany.domain.Evento;
import com.mycompany.domain.Participante;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import jnafilechooser.api.JnaFileChooser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author charl
 */
public class CargarParticipantesForm extends javax.swing.JFrame {
    
    private Evento evento;
    
    private WSManager ws;
    private ArrayList<Participante> participantes;
    /**
     * Creates new form CargarParticipantesForm
     */
    public CargarParticipantesForm() {
        initComponents();
    }
    
    public CargarParticipantesForm(Evento evento) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.ws = new WSManager();
        this.evento = evento;
        
        this.containerParticipants.setLayout(new BoxLayout(this.containerParticipants, BoxLayout.Y_AXIS));
        
        initDatos();
    }
    
    private void initDatos(){
        this.containerParticipants.removeAll();
        
        
        try {
            this.participantes = this.ws.showParticipantsEvento(this.evento.getIdEvento());
            
            if (!this.participantes.isEmpty()) {
                this.buttonCargarCsv.setVisible(false);
            }
            
            for (Participante participante : participantes) {
                PanelParticipante panelP = new PanelParticipante(participante);
                panelP.setMaximumSize(new Dimension(450, 113));
                this.containerParticipants.add(panelP);
            }
            
            this.containerParticipants.revalidate();
            this.containerParticipants.repaint();
        } catch (IOException ex) {
            Logger.getLogger(CargarParticipantesForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CargarParticipantesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        containerParticipants = new javax.swing.JPanel();
        buttonCargarCsv = new javax.swing.JButton();
        buttonAgregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new org.edisoncor.gui.util.DropShadowBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Participantes");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        containerParticipants.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout containerParticipantsLayout = new javax.swing.GroupLayout(containerParticipants);
        containerParticipants.setLayout(containerParticipantsLayout);
        containerParticipantsLayout.setHorizontalGroup(
            containerParticipantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );
        containerParticipantsLayout.setVerticalGroup(
            containerParticipantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(containerParticipants);

        buttonCargarCsv.setBackground(new java.awt.Color(255, 51, 255));
        buttonCargarCsv.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonCargarCsv.setForeground(new java.awt.Color(255, 255, 255));
        buttonCargarCsv.setText("Cargar CSV");
        buttonCargarCsv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCargarCsvMouseClicked(evt);
            }
        });

        buttonAgregar.setBackground(new java.awt.Color(255, 51, 255));
        buttonAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonAgregar.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregar.setText("Agregar");
        buttonAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonAgregarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(buttonCargarCsv, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCargarCsv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCargarCsvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCargarCsvMouseClicked
        JnaFileChooser fileChooser = new JnaFileChooser();
        
        boolean option = fileChooser.showOpenDialog(this);
        if (option) {
            try {
                String csvPath = fileChooser.getSelectedFile().toPath().toString();
                ArrayList<Participante> participantesCSV = this.ws.leerCSV(csvPath);
                if (this.ws.updateEvent(this.evento, participantesCSV)) {
                    this.initDatos();
                    JOptionPane.showMessageDialog(null, "Participantes cargados correctamente");
                } else JOptionPane.showMessageDialog(null, "No se cargaron los participantes");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar los participantes del CSV");
                Logger.getLogger(CargarParticipantesForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_buttonCargarCsvMouseClicked

    private void buttonAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAgregarMouseClicked
        new VentanaAgregarParticipante(this, this.evento).setVisible(true);
    }//GEN-LAST:event_buttonAgregarMouseClicked

    public void actualizarVentana(){
        this.initDatos();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CargarParticipantesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CargarParticipantesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CargarParticipantesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CargarParticipantesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CargarParticipantesForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAgregar;
    private javax.swing.JButton buttonCargarCsv;
    private javax.swing.JPanel containerParticipants;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
