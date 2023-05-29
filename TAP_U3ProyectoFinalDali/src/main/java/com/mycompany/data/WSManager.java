/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.mycompany.domain.Director;
import com.mycompany.domain.Evento;
import com.mycompany.domain.Participante;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author charl
 */
public class WSManager {

    private String url = "http://localhost/WS_U3_AppDiplomas/";

    public WSManager() {
    }

    // Metodos de operaciones de Director 
    public Director getActiveDirector() throws IOException, ParseException {

        String endpoint = this.url + "getActiveDirector.php";

        System.out.println("URL: " + endpoint);

        String result = Request.Get(endpoint).execute().returnContent().asString();

        if (result.contains("Error") || result.contains("null")) {
            JOptionPane.showMessageDialog(null, "Error al consultar el director actual");
            return null;
        }

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(result);

        byte[] firma = Base64.getDecoder().decode(json.get("firma").toString());

        Director director = new Director(
                Integer.parseInt(json.get("idDirector").toString()),
                json.get("grado").toString(),
                firma,
                json.get("nombre").toString(),
                Integer.parseInt(json.get("Usuario_idUsuario").toString()));

        return director;
    }

    public boolean createDirector(String name, String grado, byte[] firma, String username, String password) throws IOException {
        System.out.println("DENTRO DEL METODO CREATE DIRECTOR");

        String endPoint = this.url + "createDirector.php";

        String base64Sig = Base64.getEncoder().encodeToString(firma);

        Form form = Form.form();
        form.add("username", username);
        form.add("password", password);
        form.add("nombre", name);
        form.add("grado", grado);
        form.add("firma", base64Sig);

        String resultado = Request.Post(endPoint).bodyForm(form.build()).execute().returnContent().asString();

        if (!resultado.contains("Error")) {
            System.out.println(resultado);
            return true;
        } else {
            System.out.println(resultado);
        }
        return false;
    }

    // Metodos de operaciones de eventos
    public boolean createEvento(Evento evento) throws IOException {
        System.out.println("");

        String endpoint = this.url + "createEvent.php";

        Form form = Form.form();

        form.add("Director_idDirector", String.valueOf(evento.getDirector_idDirector()));
        form.add("Usuario_idCreador", String.valueOf(evento.getUsuario_idCreador()));
        form.add("nombre", evento.getNombre());
        form.add("fecha", evento.getFecha().toString());
        form.add("hora", evento.getHora().toString());
        form.add("tipo_formato", evento.getTipo_formato());

        String resultado = Request.Post(endpoint)
                .bodyForm(form.build())
                .execute()
                .returnContent()
                .asString();

        System.out.println(resultado);

        int res = 0;
        if (!resultado.contains("Error")) {
            System.out.println("RESULTADO: " + resultado);
            res = Integer.parseInt(resultado);
        } else {
            System.out.println(resultado);
        }

        System.out.println("RES: " + res);

        if (res > 0) {
            return true;
        }
        return false;
    }

    // No comprobado, porque aqui se manda llamar al de create item evento para cargar participantes
    public boolean updateEvento(Evento evento) throws IOException {
        String endpoint = this.url + "updateEvent.php";

        Form form = Form.form();

        form.add("idEvento", String.valueOf(evento.getIdEvento()));
        form.add("Director_idDirector", String.valueOf(evento.getDirector_idDirector()));
        form.add("Usuario_idCreador", String.valueOf(evento.getUsuario_idCreador()));
        form.add("nombre", evento.getNombre());
        form.add("fecha", evento.getFecha().toString());
        form.add("hora", evento.getHora().toString());
        form.add("tipo_formato", evento.getTipo_formato());

        //Mandamos los participantes com json
        Gson gson = new Gson();
        form.add("participantes", gson.toJson(evento.getParticipantes()));

        String resultado = Request.Post(endpoint)
                .bodyForm(form.build())
                .execute()
                .returnContent()
                .asString();

        int res = 0;
        if (!resultado.contains("Error")) {
            System.out.println("RESULTADO: " + resultado);
            res = Integer.parseInt(resultado);
        } else {
            System.out.println(resultado);
        }

        return res > 0;
    }

    // Falta primero eliminar los items_evento para poder eliminar el evento
    public boolean deleteEvento(int idEvento) throws IOException {
        String endpoint = this.url + "deleteEvent.php";

        Form form = Form.form();
        form.add("idEvento", String.valueOf(idEvento));

        String resultado = Request.Post(endpoint)
                .bodyForm(form.build())
                .execute()
                .returnContent()
                .asString();

        int res = 0;
        if (!resultado.contains("Error")) {
            System.out.println("RESULTADO: " + resultado);
            res = Integer.parseInt(resultado);
        } else {
            System.out.println(resultado);
        }

        return res > 0;
    }

    public ArrayList<Evento> showEvents() throws IOException {
        String endpoint = this.url + "showEvents.php";

        String resultado = Request.Get(endpoint)
                .execute()
                .returnContent()
                .asString();

        ArrayList<Evento> eventos = new ArrayList<>();
        // Configurar Gson con adaptadores de tipo de fecha y hora personalizados
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> {
            LocalDate localDate = LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE);
            return Date.valueOf(localDate);
        });
        gsonBuilder.registerTypeAdapter(Time.class, (JsonDeserializer<Time>) (json, typeOfT, context) -> {
            LocalTime localTime = LocalTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_TIME);
            return Time.valueOf(localTime);
        });

        Gson gson = gsonBuilder.create();
        if (!resultado.contains("Error")) {
            try {
                // Procesar la respuesta y crear una lista de objetos Evento
                // Ejemplo de cómo se puede hacer, asumiendo un formato JSON de respuesta:

                JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse(resultado);

                for (Object object : jsonArray) {
                    Evento evento = gson.fromJson(object.toString(), Evento.class);
                    eventos.add(evento);
                }

                return eventos;
            } catch (ParseException ex) {
                Logger.getLogger(WSManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("ERROR ARROJADO: " + resultado);
        }
        return null;
    }

    // METODOS PARA OPERACIONES DE ITEM EVENTO EN EL WEB SERVICE
    public boolean createItemEvento(int idParticipante, int idEvento) throws IOException {
        String endpoint = this.url + "createItemEvento.php";

        Form form = Form.form();
        form.add("Participante_idParticipante", String.valueOf(idParticipante));
        form.add("Evento_idEvento", String.valueOf(idEvento));

        String resultado = Request.Post(endpoint)
                .bodyForm(form.build())
                .execute()
                .returnContent()
                .asString();

        if (!resultado.contains("Error")) {
            return true;
        } else {
            System.out.println(resultado);
            throw new IOException("Error al dar de alta el ItemEvento");
        }
    }

    // METODOS PARA OPERACIONES DE PARTICIPANTES EN EL WEB SERVICE
    // Funcional
    public boolean createParticipant(Participante participante, String username, String password) throws IOException {
        String endpoint = this.url + "createParticipant.php";

        String foto64 = Base64.getEncoder().encodeToString(participante.getFoto());

        Form form = Form.form();
        form.add("username", username);
        form.add("password", password);
        form.add("tipo_usuario", "participante");
        form.add("nombre", participante.getNombre());
        form.add("foto", foto64);
        form.add("numero_control", participante.getNumero_control());
        form.add("carrera", participante.getCarrera());

        String resultado = Request.Post(endpoint)
                .bodyForm(form.build())
                .execute()
                .returnContent()
                .asString();

        if (!resultado.contains("Error")) {
            System.out.println("Participante creado correctamente.");
            return true;
        } else {
            System.out.println("Error al crear el participante: " + resultado);
            return false;
        }
    }

    public ArrayList<Participante> showParticipants(String nombre) throws IOException, ParseException {
        ArrayList<Participante> participantes = new ArrayList<>();
        String endpoint;

        if (nombre != null) {
            endpoint = this.url + "showParticipants.php?nombre=" + nombre;
        } else {
            endpoint = this.url + "showParticipants.php";
        }
        
        String result = Request.Get(endpoint).execute().returnContent().asString();
        
        if (!result.contains("Error")) {
            JSONParser parser = new JSONParser();
            Gson gson = new Gson();

            JSONArray jArray = (JSONArray) parser.parse(result);

            for (Object object : jArray) {
                JSONObject jsonOB= (JSONObject) object;
                
                byte[] fotoBytes = Base64.getDecoder().decode(jsonOB.get("foto").toString());
                
                Participante participante = new Participante(
                        Integer.parseInt(jsonOB.get("idParticipante").toString()), 
                        jsonOB.get("nombre").toString(), 
                        fotoBytes, 
                        jsonOB.get("numero_control").toString(), 
                        jsonOB.get("carrera").toString(),
                        Integer.parseInt(jsonOB.get("Usuario_idUsuario").toString())
                );
                participantes.add(participante);
            }
            return participantes;
        } else {
            System.out.println(result);
        }

        return null;
    }
    
    
    
    // METODO
}
