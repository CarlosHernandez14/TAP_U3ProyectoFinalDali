/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domain;

/**
 *
 * @author charl
 */
public class Participante {
    private int idParticipante;
    private String nombre;
    private byte[] foto;
    private String numero_control;
    private String carrera;
    private int Usuario_idUsuario;

    public Participante() {
    }
    
    // Constructor para creare participantes
    public Participante(String nombre, byte[] foto, String numero_control, String carrera) {
        this.nombre = nombre;
        this.foto = foto;
        this.numero_control = numero_control;
        this.carrera = carrera;
    }

    public Participante(int idParticipante, String nombre, byte[] foto, String numero_control, String carrera, int Usuario_idUsuario) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
        this.foto = foto;
        this.numero_control = numero_control;
        this.carrera = carrera;
        this.Usuario_idUsuario = Usuario_idUsuario;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNumero_control() {
        return numero_control;
    }

    public void setNumero_control(String numero_control) {
        this.numero_control = numero_control;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getUsuario_idUsuario() {
        return Usuario_idUsuario;
    }

    public void setUsuario_idUsuario(int Usuario_idUsuario) {
        this.Usuario_idUsuario = Usuario_idUsuario;
    }

    @Override
    public String toString() {
        return "Participante{" + "idParticipante=" + idParticipante + ", nombre=" + nombre + ", foto=" + foto + ", numero_control=" + numero_control + ", carrera=" + carrera + ", Usuario_idUsuario=" + Usuario_idUsuario + '}';
    }
    
    
}
