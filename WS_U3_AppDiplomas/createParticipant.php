<?php

require "DBManager.php";

$db = new DBManager();

$username;
$password;
$tipo_usuario;

$nombre;
$numero_control;
$carrera;

$foto = null;

if (isset($_POST["username"]) && isset($_POST["password"]) && isset($_POST["tipo_usuario"]) &&
    isset($_POST["nombre"]) && isset($_POST["numero_control"]) && isset($_POST["carrera"])) {

    $username = $_POST["username"];
    $password = $_POST["password"];
    $tipo_usuario = $_POST["tipo_usuario"];

    $nombre = $_POST["nombre"];
    $numero_control = $_POST["numero_control"];
    $carrera = $_POST["carrera"];
    
} else die("Se requieren todos los datos para crear un participante");

if (isset($_POST["foto"])) {
    $foto = base64_decode($_POST["foto"]);
}

// Prints true or false 
echo $db->createParticipant($username, $password, $tipo_usuario, $nombre, $foto, $numero_control, $carrera);

?>
