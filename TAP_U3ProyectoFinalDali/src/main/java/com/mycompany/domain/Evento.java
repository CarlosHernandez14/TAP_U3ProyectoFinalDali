/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author charl
 */
public class Evento {
    private int idEvento;
    private int Director_idDirector;
    private boolean validado;
    private String nombre;
    private Date fecha;
    private Time hora;
    private String tipo_formato;
    private int Usuario_idUsuario;
    
    private ArrayList<Participante> participantes;

    public Evento() {
    }

    public Evento(int Director_idDirector, boolean validado, String nombre, Date fecha, Time hora, String tipo_formato, int Usuario_idUsuario, ArrayList<Participante> participantes) {
        this.Director_idDirector = Director_idDirector;
        this.validado = validado;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo_formato = tipo_formato;
        this.Usuario_idUsuario = Usuario_idUsuario;
        this.participantes = participantes;
    }
    
    // Constructor para consulta
    public Evento(int idEvento, int Director_idDirector, boolean validado, String nombre, Date fecha, Time hora, String tipo_formato, int Usuario_idUsuario, ArrayList<Participante> participantes) {
        this.idEvento = idEvento;
        this.Director_idDirector = Director_idDirector;
        this.validado = validado;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo_formato = tipo_formato;
        this.Usuario_idUsuario = Usuario_idUsuario;
        this.participantes = participantes;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getDirector_idDirector() {
        return Director_idDirector;
    }

    public void setDirector_idDirector(int Director_idDirector) {
        this.Director_idDirector = Director_idDirector;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getTipo_formato() {
        return tipo_formato;
    }

    public void setTipo_formato(String tipo_formato) {
        this.tipo_formato = tipo_formato;
    }

    public int getUsuario_idUsuario() {
        return Usuario_idUsuario;
    }

    public void setUsuario_idUsuario(int Usuario_idUsuario) {
        this.Usuario_idUsuario = Usuario_idUsuario;
    }

    @Override
    public String toString() {
        return "Evento{" + "idEvento=" + idEvento + ", Director_idDirector=" + Director_idDirector + ", validado=" + validado + ", nombre=" + nombre + ", fecha=" + fecha + ", hora=" + hora + ", tipo_formato=" + tipo_formato + ", Usuario_idUsuario=" + Usuario_idUsuario + '}';
    }
    
    
}
