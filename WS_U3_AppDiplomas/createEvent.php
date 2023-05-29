<?php

require "DBManager.php";

$db = new DBManager();

$idDirector = "";
$usuario_creador_id = "";
$nombre = "";
$fecha = "";
$hora = "";
$tipo_formato = "";

if (isset($_POST["Director_idDirector"]) && isset($_POST["Usuario_idCreador"]) && 
    isset($_POST["nombre"]) && isset($_POST["fecha"]) && isset($_POST["hora"]) && isset($_POST["tipo_formato"])) {
    $idDirector = $_POST["Director_idDirector"];
    $usuario_creador_id = $_POST["Usuario_idCreador"];
    $nombre = $_POST["nombre"];
    $fecha = $_POST["fecha"];
    $hora = $_POST["hora"];
    $tipo_formato = $_POST["tipo_formato"];
} else {
    die("Se requieren todos los datos para crear el evento");
}

// Crea el evento y obtiene el ID del evento creado
echo $db->createEvent($idDirector, $usuario_creador_id, $nombre, $fecha, $hora, $tipo_formato);

?>
