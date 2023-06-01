/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.data;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mycompany.domain.Director;
import com.mycompany.domain.Evento;
import com.mycompany.domain.Participante;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPicture;

import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.converter.IConverter;
import fr.opensagres.xdocreport.converter.ConverterRegistry;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.core.document.DocumentKind;

/**
 *
 * @author charl
 */
public class TemplateManager {

    private String location = "src/main/java/com/mycompany/templates/";

    private String saveLocation = "C:\\xampp\\htdocs\\WS_U3_AppDiplomas\\";

    private String ip = "192.168.50.251";

    public TemplateManager() {
    }

    public void crearDocConferencia(Participante participante, Evento evento, Director director) {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.location + "Template-Conferencia.docx");
            XWPFDocument document = new XWPFDocument(fileInputStream);

            System.out.println("NOMBRE DEL EVENTO:" + evento.getNombre());
            // Reemplazar "[nombre conferencia]"
            replaceText(document, "nombreConferencia", evento.getNombre());

            // Reemplazar "[participante]"
            replaceText(document, "[participante]", participante.getNombre());

            // Reemplazar "Firma" del director en el documento
            replaceImageInParagraph(document, director.getFirma(), "image2.png");

            //replaceText(document, "CampoFirma", (!evento.isValidado() ? "Sin validar" : "FIRMA DEL DIRE"));
            System.out.println("FECHA: " + evento.getFecha().toString());
            // Reemplazamos la fecha con la hora
            replaceText(document, "CampoFecha", evento.getFecha().toString());
            
            // Ponemos el qr
            // Cargamos el codigo qr
            String url = "https://" + this.ip + "/WS_U3_AppDiplomas/" + "conferencia-" + participante.getNombre() + ".docx";
            byte[] bytesQR = this.generateQRCode(url, 64, 64);
            replaceImageInParagraph(document, bytesQR, "image1.png");
            
            // Reemplazamos la imagen de la firma del director
            replaceImageInParagraph(document, director.getFirma(), "image3.png");
            
            

            FileOutputStream fileOutputStream = new FileOutputStream(this.saveLocation + "conferencia" + participante.getNombre() + ".docx");
            document.write(fileOutputStream);
            fileOutputStream.close();
            document.close();

            System.out.println("Documento de Word editado correctamente.");
            JOptionPane.showMessageDialog(null, "Documento del participante " + participante.getNombre() + " impreso con exito");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearDocDiploma(Participante participante, Evento evento, Director director) {
        try {
            // Ruta del archivo de plantilla .dotx
            String templatePath = this.location + "Template-Diploma.docx";

            // Ruta del archivo de salida
            String outputPath = this.saveLocation + "diploma-" + participante.getNombre() + ".docx";
            String outputPathPDF = this.saveLocation + "diploma-" + participante.getNombre() + ".pdf";

            // Cargar la plantilla .dotx
            FileInputStream templateFile = new FileInputStream(templatePath);
            XWPFDocument document = new XWPFDocument(templateFile);

            // Reemplazar los textos deseados en todos los párrafos del documento
            System.out.println("PARTICIPANTE:" + participante.getNombre());
            replaceTextInDocument(document, "PARTICIPANTE", participante.getNombre());
            replaceTextInDocument(document, "EVENTO", evento.getNombre());
            replaceTextInDocument(document, "FECHA", evento.getFecha().toString());
            //replaceTextInDocument(document, "FIRMA", (!evento.isValidado() ? "Sin validar" : "FIRMA DEL DIRE"));

            // Cargamos el codigo qr
            String url = "https://" + this.ip + "/WS_U3_AppDiplomas/" + "diploma-" + participante.getNombre() + ".docx";
            byte[] bytesQR = this.generateQRCode(url, 64, 64);
            replaceImageInParagraph(document, bytesQR, "image1.png");

            // Reemplazamos la firma del director
            replaceImageInParagraph(document, director.getFirma(), "image2.png");
            // Reemplazamos la imagen del participante en el documento
            replaceImageInParagraph(document, participante.getFoto(), "image3.png");

            // Guardar el documento modificado
            FileOutputStream outputFile = new FileOutputStream(outputPath);
            document.write(outputFile);
            outputFile.close(); // Cerrar el flujo de salida

            // Cerrar el flujo del archivo de plantilla
            templateFile.close();
            
            // Convertimos a pdf el documento
            //convertToPDF(outputPath, outputPathPDF);

            System.out.println("Plantilla editada con éxito.");
            JOptionPane.showMessageDialog(null, "Documento del participante " + participante.getNombre() + " impreso con exito");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearDocCurso(Participante participante, Evento evento, Director director) {
        try {
            // Ruta del archivo de plantilla .dotx
            String templatePath = this.location + "Template-Curso.docx";

            // Ruta del archivo de salida
            String outputPath = this.saveLocation + "curso-" + participante.getNombre() + ".docx";

            // Cargar la plantilla .dotx
            FileInputStream templateFile = new FileInputStream(templatePath);
            XWPFDocument document = new XWPFDocument(templateFile);

            // Reemplazar los textos deseados en todos los párrafos del documento
            //System.out.println("PARTICIPANTE:" + participante.getNombre());
            replaceTextInDocument(document, "$NOMBRE", participante.getNombre());
            replaceTextInDocument(document, "$NOMBREDIRECTOR", director.getNombre());
            replaceTextInDocument(document, "$FECHA", evento.getFecha().toString());
            replaceTextInDocument(document, "$HORA", evento.getHora().toString());
            replaceTextInDocument(document, "$GRADO", director.getGrado());
            replaceTextInDocument(document, "$CARRERA", participante.getCarrera());
            replaceTextInDocument(document, "$NUMCONTROL", participante.getNumero_control());
            
            // PONEMOS EL QR
            String url = "https://" + this.ip + "/WS_U3_AppDiplomas/" + "curso-" + participante.getNombre() + ".docx";
            byte[] qrBytes = this.generateQRCode(url, 64, 64);
            replaceImageInParagraph(document, qrBytes, "image1.png");
            
            // Reemplazamos la imagen con la del participante en el documento
            replaceImageInParagraph(document, participante.getFoto(), "image2.png");
            
            // Reemplamos la imagen con la firma del director
            replaceImageInParagraph(document, director.getFirma(), "image3.png");
            
            // Ponemos el qr
            

            // Reemplazam
            // Guardar el documento modificado
            FileOutputStream outputFile = new FileOutputStream(outputPath);
            document.write(outputFile);
            outputFile.close(); // Cerrar el flujo de salida

            // Cerrar el flujo del archivo de plantilla
            templateFile.close();

            System.out.println("Plantilla editada con éxito.");
            JOptionPane.showMessageDialog(null, "Documento del participante " + participante.getNombre() + " impreso con exito");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void replaceImageInParagraph(XWPFDocument document, byte[] imageBytes, String imageName) {
        try {
            // Reemplazamos las imagenes del header por si hubiera
            //for (XWPFHeader header : document.getHeaderList()) {
            //    for (XWPFPictureData pictureData : header.getAllPictures()) {
            //        String fileName = pictureData.getFileName();
            //        System.out.println(fileName);

            // El image name tiene que ir con todo y extens
            //        if (fileName.equals("image1.png")) {
            //            replacePictureData(pictureData, imageBytes);
            //            System.out.println(fileName + " replaced by participant image");
            //        }
            //        //if (pictureData.getPictureType() == Document.PICTURE_TYPE_PNG) {
            //    replacePictureData(pictureData, imageBytes);
            //    System.out.println(fileName + " replaced by participant image");
            //}
            //    }
            //}
            for (XWPFPictureData pictureData : document.getAllPictures()) {
                String fileName = pictureData.getFileName();
                System.out.println(fileName);

                if (fileName.equals(imageName)) {
                    replacePictureData(pictureData, imageBytes);
                    System.out.println(fileName + " replaced by participant image");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void replacePictureData(XWPFPictureData source, byte[] data) {
        try (ByteArrayInputStream in = new ByteArrayInputStream(data); OutputStream out = source.getPackagePart().getOutputStream();) {
            byte[] buffer = new byte[2048];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void replaceTextInDocument(XWPFDocument document, String oldText, String newText) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            replaceTextInParagraph(paragraph, oldText, newText);
        }
        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        replaceTextInParagraph(paragraph, oldText, newText);
                    }
                }
            }
        }
    }

    private static void replaceTextInParagraph(XWPFParagraph paragraph, String oldText, String newText) {
        for (XWPFRun run : paragraph.getRuns()) {
            String text = run.getText(0);
            if (text != null && text.contains(oldText)) {
                text = text.replace(oldText, newText);
                run.setText(text, 0);
            }
        }
    }

    private void replaceText(XWPFDocument document, String searchText, String replacementText) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            List<XWPFRun> runs = paragraph.getRuns();
            for (XWPFRun run : runs) {
                String text = run.getText(0);
                if (text != null && text.contains(searchText)) {
                    text = text.replace(searchText, replacementText);
                    run.setText(text, 0);
                }
            }
        }
    }

    private byte[] generateQRCode(String text, int width, int height) {
        String qr;
        byte[] qrBytes = null;
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int grayValue = (bitMatrix.get(x, y) ? 0 : 1) & 0xff;
                    bufferedImage.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
                }
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            qrBytes = baos.toByteArray();
        } catch (IOException e) {
            System.err.println("Error al generar el código QR: " + e.getMessage());
        } catch (WriterException ex) {
            Logger.getLogger(TemplateManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        qr = Base64.getEncoder().encodeToString(qrBytes);
        return qrBytes;
    }

    private void convertToPDF(String docPathAfterReplacing, String pdfPath) {
        System.out.println();
        try (InputStream in = new FileInputStream(new File(docPathAfterReplacing)); OutputStream out = new FileOutputStream(new File(pdfPath));) {
            Options options = Options.getFrom(DocumentKind.DOCX).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);
            converter.convert(in, out, options);
            
            System.out.println("Archivo convertido a pdf");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
