<?php

require "DBManager.php";

$db = new DBManager();

$idParticipante = null;
$nombre = null;
$foto = null;
$numeroControl = null;
$carrera = null;

if (isset($_POST["idParticipante"]) && isset($_POST["nombre"]) && isset($_POST["foto"]) && isset($_POST["numeroControl"]) && isset($_POST["carrera"])) {
    $idParticipante = $_POST["idParticipante"];
    $nombre = $_POST["nombre"];
    $foto64 = $_POST["foto"];
    $foto = base64_decode($foto64);
    $numeroControl = $_POST["numeroControl"];
    $carrera = $_POST["carrera"];
} else {
    die("Error. Se requieren de todos los datos en el post");
}

echo $db->updateParticipant($idParticipante, $nombre, $foto, $numeroControl, $carrera)

?>
