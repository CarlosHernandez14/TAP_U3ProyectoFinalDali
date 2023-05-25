/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domain;

/**
 *
 * @author charl
 */
public class Usuario {
    private int idUsuario;
    private String username;
    private String password;
    private String tipo_usuario;

    public Usuario() {
    }

    public Usuario(String username, String password, String tipo_usuario) {
        this.username = username;
        this.password = password;
        this.tipo_usuario = tipo_usuario;
    }

    public Usuario(int idUsuario, String username, String password, String tipo_usuario) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.tipo_usuario = tipo_usuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", username=" + username + ", password=" + password + ", tipo_usuario=" + tipo_usuario + '}';
    }
    
    
}
