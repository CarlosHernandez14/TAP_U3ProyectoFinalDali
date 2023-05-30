/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.tap_u3proyectofinaldali;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.data.TemplateManager;
import com.mycompany.data.WSManager;
import com.mycompany.domain.Director;
import com.mycompany.domain.Evento;
import com.mycompany.domain.Participante;
import com.mycompany.domain.Usuario;
import com.mycompany.tap_u3proyectofinaldali.documentos.CrearEventoForm;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import jnafilechooser.api.JnaFileChooser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author charl
 */
public class TAP_U3ProyectoFinalDali {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            new LoginForm().setVisible(true);
            //new CrearEventoForm(new Usuario()).setVisible(true);
            WSManager ws = new WSManager();
            
            
            //File firma = new File("src/main/java/com/mycompany/images/firma-Charly.png");
            
            
            //if (ws.createDirector("Charly", "Maestria", Files.readAllBytes(firma.toPath()), "charl", "ch262")) {
            //    JOptionPane.showMessageDialog(null, "Director creado correctamente");
            //} else {
            //    JOptionPane.showMessageDialog(null, "Error al crear el director");
            //}
            //String nombre = "Octavio";
            //String numeroCtrl = "21121553";
            //String carrera = "Sistemas";
            
            //Participante p = new Participante(nombre, null, numeroCtrl, carrera);
            
            //String[] userData = ws.genUserData(p);
            
            //int idParticipante = ws.createParticipant(p, userData[0], userData[1]);
            //if (idParticipante > 0) {
            //    JOptionPane.showMessageDialog(null, "Se agrego el participante \n idParticipante: " + idParticipante);
            //} else JOptionPane.showMessageDialog(null, "ERROR AL CREAR EL PARTICIPANTE");
            
            
            
        }catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TAP_U3ProyectoFinalDali.class.getName()).log(Level.SEVERE, null, ex);
        }
        //TemplateManager tm = new TemplateManager();
        //Participante p = new Participante();
        //p.setNombre("Carlos Hernandez");
        //Evento e = new Evento();
        //e.setNombre("Fisica cuantica");
        //Date fecha = Date.valueOf(LocalDate.now());
        //e.setFecha(fecha);
        //e.setValidado(false);
        //tm.crearDocDiploma(p, e, new Director());
        
        
        
        //TemplateManager tm = new TemplateManager();
        //Participante p = new Participante();
        //p.setNombre("Carlos Hernandez");
        //Evento e = new Evento();
        //e.setNombre("Fisica cuantica");
        //Date fecha = Date.valueOf(LocalDate.now());
        //e.setFecha(fecha);
        //e.setValidado(false);
        
        //tm.crearDocDiploma(p, e, new Director());
    }
    
}
