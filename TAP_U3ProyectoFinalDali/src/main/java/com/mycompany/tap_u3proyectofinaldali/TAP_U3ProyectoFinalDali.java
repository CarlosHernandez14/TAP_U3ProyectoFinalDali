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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import org.apache.poi.xwpf.usermodel.XWPFDocument;
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
            
            // Filtro participantes por numero de control
            
            //ArrayList<Participante> participantes = ws.showParticipants(null);
            //ArrayList<Evento> eventos = ws.showEvents();
            //Director director = ws.getActiveDirector();
            
            TemplateManager tm = new TemplateManager();
            
            String location = "src/main/java/com/mycompany/templates/";
            // Ruta del archivo de plantilla .dotx
            String templatePath = location + "Template-Curso.docx";
            String saveLocation = "C:\\xampp\\htdocs\\WS_U3_AppDiplomas\\";

            // Ruta del archivo de salida
            String outputPath = saveLocation + "curso.docx";

           
        }catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TAP_U3ProyectoFinalDali.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
