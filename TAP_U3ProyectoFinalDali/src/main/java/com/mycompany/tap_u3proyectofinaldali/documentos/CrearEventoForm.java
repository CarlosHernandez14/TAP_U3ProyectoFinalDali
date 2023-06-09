/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.tap_u3proyectofinaldali.documentos;

import com.mycompany.data.WSManager;
import com.mycompany.domain.Director;
import com.mycompany.domain.Evento;
import com.mycompany.domain.Usuario;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.json.simple.parser.ParseException;

/**
 *
 * @author charl
 */
public class CrearEventoForm extends javax.swing.JFrame {

    private Usuario usuario;
    private WSManager ws;
    
    private VentanaPrincipalDocumentos ventanaPrincipalDocumentos;
    
    private String templatesLocation = "src/main/java/com/mycompany/templates";
    private String imagesLoacation = "src/main/java/com/mycompany/images/";
    
    /**
     * Creates new form CrearEventoForm
     */
    public CrearEventoForm() {
        initComponents();
    }
    
    public CrearEventoForm(Usuario usuario, VentanaPrincipalDocumentos ventanaPrincipalDocumentos) {
        initComponents();
        this.ventanaPrincipalDocumentos = ventanaPrincipalDocumentos;
        this.setLocationRelativeTo(null);
        this.usuario = usuario;
        this.ws = new WSManager();
        
        initDatos();
    }
    
    private void initDatos(){
        this.comboBoxFormatos.removeAll();
        ArrayList<String> formatos = listarArchivos(this.templatesLocation);
        
        DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
        
        for (String formato : formatos) {
            System.out.println("NOMBRE FORMATO: " + formato);
            comboModel.addElement(formato);
        }
        
        this.comboBoxFormatos.setModel(comboModel);
        
        this.comboBoxFormatos.revalidate();
        this.comboBoxFormatos.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new org.edisoncor.gui.panel.Panel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textFieldNombre = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboBoxFormatos = new javax.swing.JComboBox<>();
        buttonCrear = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        panelImagePreview = new org.edisoncor.gui.panel.PanelImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panel1.setColorPrimario(new java.awt.Color(255, 204, 255));
        panel1.setColorSecundario(new java.awt.Color(255, 51, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Crear evento");

        textFieldNombre.setBackground(new java.awt.Color(255, 255, 255));
        textFieldNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textFieldNombre.setText("fdfd");
        textFieldNombre.setBorder(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon-NombreEvento.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tipo de formato:");

        comboBoxFormatos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxFormatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxFormatosActionPerformed(evt);
            }
        });

        buttonCrear.setBackground(new java.awt.Color(255, 102, 255));
        buttonCrear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonCrear.setForeground(new java.awt.Color(255, 255, 255));
        buttonCrear.setText("Crear");
        buttonCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCrearMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nombre del evento:");

        javax.swing.GroupLayout panelImagePreviewLayout = new javax.swing.GroupLayout(panelImagePreview);
        panelImagePreview.setLayout(panelImagePreviewLayout);
        panelImagePreviewLayout.setHorizontalGroup(
            panelImagePreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelImagePreviewLayout.setVerticalGroup(
            panelImagePreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
                            .addComponent(comboBoxFormatos, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1)
                            .addComponent(panelImagePreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 151, Short.MAX_VALUE)
                .addComponent(buttonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBoxFormatos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelImagePreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCrearMouseClicked
        String tipoFormato = this.comboBoxFormatos.getSelectedItem().toString();
        String nombreEv = this.textFieldNombre.getText();
        
        java.util.Date date = Calendar.getInstance().getTime();
        
        Date fecha = new Date(date.getTime());
        Time hora = new Time(date.getTime());
        
        try {
            // Consultamos el actual director
            Director director = this.ws.getActiveDirector();
            
            if (director == null) {
                JOptionPane.showMessageDialog(null, "Error al consultar el director actual para crear un evento");
                return;
            }
            
            Evento evento =  new Evento(
                    director.getIdDirector(), 
                    false, 
                    nombreEv, 
                    fecha, 
                    hora, 
                    tipoFormato, 
                    this.usuario.getIdUsuario());
            
            if (this.ws.createEvento(evento)) {
                this.ventanaPrincipalDocumentos.acutalizarVentana();
                JOptionPane.showMessageDialog(null, "Evento creado con exito");
                this.dispose();
            } else JOptionPane.showMessageDialog(null, "Error al crear el evento");
            
        } catch (IOException ex) {
            Logger.getLogger(CrearEventoForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CrearEventoForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonCrearMouseClicked

    private void comboBoxFormatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxFormatosActionPerformed
        String selectedFormat = this.comboBoxFormatos.getSelectedItem().toString();
        
        this.panelImagePreview.setIcon(new ImageIcon(this.imagesLoacation + selectedFormat + ".jpg"));
        
        //Icon icon = this.panelImagePreview.getIcon();
        
        this.panelImagePreview.revalidate();
        this.panelImagePreview.repaint();
    }//GEN-LAST:event_comboBoxFormatosActionPerformed
    
    public static ArrayList<String> listarArchivos(String carpeta) {
        ArrayList<String> fileList = new ArrayList<>();
        
        File folder = new File(carpeta);
        
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file.getName().split("\\.")[0]);
                }
            }
        }
        
        return fileList;
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
            java.util.logging.Logger.getLogger(CrearEventoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearEventoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearEventoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearEventoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearEventoForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCrear;
    private javax.swing.JComboBox<String> comboBoxFormatos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.PanelImage panelImagePreview;
    private javax.swing.JTextField textFieldNombre;
    // End of variables declaration//GEN-END:variables
}
