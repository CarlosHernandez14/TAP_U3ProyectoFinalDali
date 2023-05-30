<?php

require "DBManager.php";

$db = new DBManager();

$event_id = "";
$idDirector = "";
$usuario_creador_id = "";
$nombre = "";
$fecha = "";
$hora = "";
$tipo_formato = "";

if (isset($_POST["idEvento"]) && isset($_POST["Director_idDirector"]) && isset($_POST["Usuario_idCreador"]) && 
    isset($_POST["nombre"]) && isset($_POST["fecha"]) && isset($_POST["hora"]) && isset($_POST["tipo_formato"])) {
    $event_id = $_POST["idEvento"];
    $idDirector = $_POST["Director_idDirector"];
    $usuario_creador_id = $_POST["Usuario_idCreador"];
    $nombre = $_POST["nombre"];
    $fecha = $_POST["fecha"];
    $hora = $_POST["hora"];
    $tipo_formato = $_POST["tipo_formato"];
} else {
    die("Error. Se requieren todos los datos para actualizar el evento");
}


echo $db->updateEvent($event_id, $idDirector, $usuario_creador_id, $nombre, $fecha, $hora, $tipo_formato)

?>
