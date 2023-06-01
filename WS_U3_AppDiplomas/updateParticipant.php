<?php

require "DBManager.php";

$db = new DBManager();

$idParticipante = null;
$nombre = null;
$foto = null;
$numeroControl = null;
$carrera = null;

if (isset($_POST["idParticipante"]) && isset($_POST["nombre"]) && isset($_POST["foto"]) && isset($_POST["numero_control"]) && isset($_POST["carrera"])) {
    $idParticipante = $_POST["idParticipante"];
    $nombre = $_POST["nombre"];
    $foto64 = $_POST["foto"];
    $foto = base64_decode($foto64);
    $numeroControl = $_POST["numero_control"];
    $carrera = $_POST["carrera"];
} else {
    die("Error. Se requieren de todos los datos en el post");
}

//echo "id= $idParticipante, nombre = $nombre, numero_control = $numeroControl, carrera = $carrera";

echo $db->updateParticipant($idParticipante, $nombre, $foto, $numeroControl, $carrera);

?>
