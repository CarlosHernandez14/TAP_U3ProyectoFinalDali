<?php

class Director {
    private $idDirector;
    private $grado;
    private $firma;
    private $nombre;
    private $Usuario_idUsuario;

    public function __construct() {
    }

    public function __construct2($grado, $firma, $nombre, $Usuario_idUsuario) {
        $this->grado = $grado;
        $this->firma = $firma;
        $this->nombre = $nombre;
        $this->Usuario_idUsuario = $Usuario_idUsuario;
    }

    public function __construct3($idDirector, $grado, $firma, $nombre, $Usuario_idUsuario) {
        $this->idDirector = $idDirector;
        $this->grado = $grado;
        $this->firma = $firma;
        $this->nombre = $nombre;
        $this->Usuario_idUsuario = $Usuario_idUsuario;
    }

    public function getIdDirector() {
        return $this->idDirector;
    }

    public function setIdDirector($idDirector) {
        $this->idDirector = $idDirector;
    }

    public function getGrado() {
        return $this->grado;
    }

    public function setGrado($grado) {
        $this->grado = $grado;
    }

    public function getFirma() {
        return $this->firma;
    }

    public function setFirma($firma) {
        $this->firma = $firma;
    }

    public function getNombre() {
        return $this->nombre;
    }

    public function setNombre($nombre) {
        $this->nombre = $nombre;
    }

    public function getUsuario_idUsuario() {
        return $this->Usuario_idUsuario;
    }

    public function setUsuario_idUsuario($Usuario_idUsuario) {
        $this->Usuario_idUsuario = $Usuario_idUsuario;
    }

    public function __toString() {
        return "Director{" . "idDirector=" . $this->idDirector . ", grado=" . $this->grado . ", firma=" . $this->firma . ", nombre=" . $this->nombre . ", Usuario_idUsuario=" . $this->Usuario_idUsuario . '}';
    }
}


?>