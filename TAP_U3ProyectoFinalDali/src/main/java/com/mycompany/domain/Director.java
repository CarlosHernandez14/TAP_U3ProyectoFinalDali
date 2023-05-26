/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domain;

/**
 *
 * @author charl
 */
public class Director {
    private int idDirector;
    private String grado;
    private byte[] firma;
    private String nombre;
    private int Usuario_idUsuario;

    public Director() {
    }

    public Director(String grado, byte[] firma, String nombre, int Usuario_idUsuario) {
        this.grado = grado;
        this.firma = firma;
        this.nombre = nombre;
        this.Usuario_idUsuario = Usuario_idUsuario;
    }

    public Director(int idDirector, String grado, byte[] firma, String nombre, int Usuario_idUsuario) {
        this.idDirector = idDirector;
        this.grado = grado;
        this.firma = firma;
        this.nombre = nombre;
        this.Usuario_idUsuario = Usuario_idUsuario;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public byte[] getFirma() {
        return firma;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUsuario_idUsuario() {
        return Usuario_idUsuario;
    }

    public void setUsuario_idUsuario(int Usuario_idUsuario) {
        this.Usuario_idUsuario = Usuario_idUsuario;
    }

    @Override
    public String toString() {
        return "Director{" + "idDirector=" + idDirector + ", grado=" + grado + ", firma=" + firma + ", nombre=" + nombre + ", Usuario_idUsuario=" + Usuario_idUsuario + '}';
    }
    
}
