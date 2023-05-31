/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.tap_u3proyectofinaldali.documentos;

import com.mycompany.data.WSManager;
import com.mycompany.domain.Director;
import com.mycompany.domain.Evento;
import com.mycompany.domain.Participante;
import com.mycompany.domain.Usuario;
import com.mycompany.tap_u3proyectofinaldali.director.VentanaPrincipalDirector;
import com.mycompany.tap_u3proyectofinaldali.participante.VentanaPrincipalParticipante;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import org.json.simple.parser.ParseException;

/**
 *
 * @author charl
 */
public class PanelEvento extends javax.swing.JPanel {
    
    private Evento evento;
    private Director director;
    private Usuario usuario;
    
    private String iconValido = "src/main/resources/icon-isValidado.png";
    private String iconNoValidado = "src/main/resources/icon-NoValidado.png";

    private WSManager ws;

    private VentanaPrincipalDocumentos ventanaPrincipalDocumentos;
    private VentanaPrincipalDirector ventanaPrincipalDirector;
    private VentanaPrincipalParticipante ventanaPrincipalParticipante;

    private ArrayList<Participante> participants;

    /**
     * Creates new form PanelEvento
     */
    public PanelEvento() {
        initComponents();
    }

    public PanelEvento(Evento evento, Director director, VentanaPrincipalDocumentos ventanaPrincipalDocumentos, Usuario usuario) {
        initComponents();
        this.evento = evento;
        this.usuario = usuario;
        this.director = director;
        this.ws = new WSManager();
        this.ventanaPrincipalDocumentos = ventanaPrincipalDocumentos;

        try {
            this.participants = this.ws.showParticipantsEvento(evento.getIdEvento());

            if (this.participants == null) {
                JOptionPane.showMessageDialog(null, "Error al leer los participantes");
            }
        } catch (IOException ex) {
            Logger.getLogger(PanelEvento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PanelEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("EL USUARIO ES UN DOCUMENTOS");

        initDatos();
    }

    public PanelEvento(Evento evento, Director director, VentanaPrincipalDirector ventanaPrincipalDirector, Usuario usuario) {
        System.out.println("EL USUARIO ES DE TIPO DIRECTOR");
        initComponents();
        this.evento = evento;
        this.usuario = usuario;
        this.director = director;
        this.ws = new WSManager();
        this.ventanaPrincipalDirector = ventanaPrincipalDirector;

        try {
            this.participants = this.ws.showParticipants(null);

            if (this.participants == null) {
                JOptionPane.showMessageDialog(null, "Error al leer los participantes");
            }
        } catch (IOException ex) {
            Logger.getLogger(PanelEvento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PanelEvento.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        initDatos();
        
        // No le permitimos agregar participantes
        this.itemAddParticipants.setVisible(false);
        
        System.out.println("ES UN USUARIO DIRECTOR");
        
        // ELiminamos todos los botones de editar y agregamos uno de validar para el director
        this.containerButtonsEdit.setLayout(new BorderLayout());
        this.containerButtonsEdit.removeAll();
        
        JButton buttonValidar = new JButton("Validar");
        buttonValidar.setVisible(true);
        buttonValidar.setMinimumSize(new Dimension(120, 50));
        buttonValidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ws.validateEvent(evento.getIdEvento())) {
                        ventanaPrincipalDirector.actualizarVentana();
                        JOptionPane.showMessageDialog(null, "Se valido el evento");
                    } else JOptionPane.showMessageDialog(null, "No se valido el evento");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al intentar validar el evento");
                    Logger.getLogger(PanelEvento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        this.containerButtonsEdit.add(buttonValidar, BorderLayout.CENTER);
        
        
        this.containerButtonsEdit.revalidate();
        this.containerButtonsEdit.repaint();
        

    }
    
    public PanelEvento(Evento evento, Director director, VentanaPrincipalParticipante ventanaPrincipalParticipante, Usuario usuario) {
        initComponents();
        this.evento = evento;
        this.usuario = usuario;
        this.director = director;
        this.ws = new WSManager();
        this.ventanaPrincipalParticipante = ventanaPrincipalParticipante;

        try {
            this.participants = this.ws.showParticipantsEvento(evento.getIdEvento());

            if (this.participants == null) {
                JOptionPane.showMessageDialog(null, "Error al leer los participantes");
            }
        } catch (IOException ex) {
            Logger.getLogger(PanelEvento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PanelEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("EL USUARIO ES UN PARTICIPANTE");

        initDatos();
        
        this.buttonEditar.setVisible(false);
        this.buttonEliminar.setVisible(false);
        this.buttonStatus.setVisible(false);
        this.buttonParticipantes.setVisible(false);
    }

    private void initDatos() {
        this.labelFecha.setText(this.evento.getFecha().toString());
        this.labelHora.setText(this.evento.getHora().toString());
        this.labelNombreEvento.setText(this.evento.getNombre());
        
        System.out.println(this.evento.toString());

        if (this.evento.isValidado()) {
            this.itemStatus.setForeground(Color.GREEN);
            this.itemStatus.setText(String.format("""
                                    Evento validado por
                                    el Director %s
                                    """, this.director.getNombre()));
            this.labelStatus.setIcon(new ImageIcon(this.iconValido));
        } else {
            this.itemStatus.setForeground(Color.red);
            this.itemStatus.setText("Evento sin validar");
            this.labelStatus.setIcon(new ImageIcon(this.iconNoValidado));
        }

        // Creamos todos los items para el menu participantes
        for (Participante participant : participants) {
            JMenuItem item = new JMenuItem(participant.getNombre());
            this.menuParticipantes.add(item);
        }
        this.menuParticipantes.revalidate();
        this.menuParticipantes.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        itemStatus = new javax.swing.JMenuItem();
        menuParticipantes = new javax.swing.JPopupMenu();
        itemAddParticipants = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        hintParticipants = new javax.swing.JPopupMenu();
        itemHintParticipants = new javax.swing.JMenuItem();
        labelNombreEvento = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        labelHora = new javax.swing.JLabel();
        containerButtonsEdit = new javax.swing.JPanel();
        buttonEditar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        buttonImprimir = new javax.swing.JButton();
        buttonParticipantes = new javax.swing.JButton();
        buttonStatus = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labelStatus = new javax.swing.JLabel();

        itemStatus.setForeground(new java.awt.Color(255, 0, 0));
        itemStatus.setText("Sin validar");
        jPopupMenu1.add(itemStatus);

        itemAddParticipants.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        itemAddParticipants.setForeground(new java.awt.Color(255, 0, 255));
        itemAddParticipants.setText("Agregar participantes");
        itemAddParticipants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAddParticipantsActionPerformed(evt);
            }
        });
        menuParticipantes.add(itemAddParticipants);
        menuParticipantes.add(jSeparator1);

        itemHintParticipants.setText("Participantes");
        hintParticipants.add(itemHintParticipants);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new org.edisoncor.gui.util.DropShadowBorder());

        labelNombreEvento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelNombreEvento.setForeground(new java.awt.Color(0, 0, 0));
        labelNombreEvento.setText("Nombre del evento");

        labelFecha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelFecha.setForeground(new java.awt.Color(255, 51, 255));
        labelFecha.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelFecha.setText("Fecha");

        labelHora.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelHora.setForeground(new java.awt.Color(255, 51, 255));
        labelHora.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelHora.setText("Hora");

        containerButtonsEdit.setBackground(new java.awt.Color(255, 255, 255));

        buttonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon-Editar.png"))); // NOI18N
        buttonEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonEditarMouseClicked(evt);
            }
        });

        buttonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon-Borrar.png"))); // NOI18N
        buttonEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout containerButtonsEditLayout = new javax.swing.GroupLayout(containerButtonsEdit);
        containerButtonsEdit.setLayout(containerButtonsEditLayout);
        containerButtonsEditLayout.setHorizontalGroup(
            containerButtonsEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerButtonsEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        containerButtonsEditLayout.setVerticalGroup(
            containerButtonsEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerButtonsEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containerButtonsEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonEliminar)
                    .addComponent(buttonEditar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        buttonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon-Imprimir.png"))); // NOI18N

        buttonParticipantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon-Participantes.png"))); // NOI18N
        buttonParticipantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonParticipantesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonParticipantesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonParticipantesMouseExited(evt);
            }
        });

        buttonStatus.setText("Status");
        buttonStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonStatusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonImprimir)
                .addGap(17, 17, 17)
                .addComponent(buttonParticipantes, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonParticipantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonImprimir))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 204));
        jLabel1.setText("Validado:");

        labelStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon-isValidado.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNombreEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(containerButtonsEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(labelNombreEvento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFecha)
                    .addComponent(labelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(labelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(containerButtonsEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void buttonStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonStatusMouseClicked
        this.jPopupMenu1.show(this.buttonStatus, WIDTH, this.buttonStatus.getHeight());
    }//GEN-LAST:event_buttonStatusMouseClicked

    private void buttonParticipantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonParticipantesMouseClicked
        this.menuParticipantes.show(this.buttonParticipantes, WIDTH, this.buttonParticipantes.getHeight());
        this.hintParticipants.setVisible(false);
    }//GEN-LAST:event_buttonParticipantesMouseClicked

    private void itemAddParticipantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAddParticipantsActionPerformed
        new CargarParticipantesForm(this.evento).setVisible(true);
    }//GEN-LAST:event_itemAddParticipantsActionPerformed

    private void buttonParticipantesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonParticipantesMouseEntered
        this.hintParticipants.show(this.buttonParticipantes, WIDTH, -this.buttonParticipantes.getHeight());
    }//GEN-LAST:event_buttonParticipantesMouseEntered

    private void buttonParticipantesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonParticipantesMouseExited
        this.hintParticipants.setVisible(false);
    }//GEN-LAST:event_buttonParticipantesMouseExited

    private void buttonEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEditarMouseClicked
        new VentanaEditarEvento(evento, ventanaPrincipalDocumentos).setVisible(true);
    }//GEN-LAST:event_buttonEditarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEditar;
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonImprimir;
    private javax.swing.JButton buttonParticipantes;
    private javax.swing.JButton buttonStatus;
    private javax.swing.JPanel containerButtonsEdit;
    private javax.swing.JPopupMenu hintParticipants;
    private javax.swing.JMenuItem itemAddParticipants;
    private javax.swing.JMenuItem itemHintParticipants;
    private javax.swing.JMenuItem itemStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelHora;
    private javax.swing.JLabel labelNombreEvento;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JPopupMenu menuParticipantes;
    // End of variables declaration//GEN-END:variables
}
