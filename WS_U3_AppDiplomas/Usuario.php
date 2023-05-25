<?php

class Usuario {
    private $idUsuario;
    private $username;
    private $password;
    private $tipo_usuario;

    public function _construct() {
    }

    public function _construct2($username, $password, $tipo_usuario) {
        $this->username = $username;
        $this->password = $password;
        $this->tipo_usuario = $tipo_usuario;
    }

    public function _construct3($idUsuario, $username, $password, $tipo_usuario) {
        $this->idUsuario = $idUsuario;
        $this->username = $username;
        $this->password = $password;
        $this->tipo_usuario = $tipo_usuario;
    }

    public function getIdUsuario() {
        return $this->idUsuario;
    }

    public function setIdUsuario($idUsuario) {
        $this->idUsuario = $idUsuario;
    }

    public function getUsername() {
        return $this->username;
    }

    public function setUsername($username) {
        $this->username = $username;
    }

    public function getPassword() {
        return $this->password;
    }

    public function setPassword($password) {
        $this->password = $password;
    }

    public function getTipo_usuario() {
        return $this->tipo_usuario;
    }

    public function setTipo_usuario($tipo_usuario) {
        $this->tipo_usuario = $tipo_usuario;
    }

    public function __toString() {
        return "Usuario{" . "idUsuario=" . $this->idUsuario . ", username=" . $this->username . ", password=" . $this->password . ", tipo_usuario=" . $this->tipo_usuario . '}';
    }
}


?>