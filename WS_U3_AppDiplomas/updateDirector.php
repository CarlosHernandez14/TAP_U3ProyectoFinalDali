<?php

require "DBManager.php";

$db = new DBManager();

$idDirector = null;
$nombre = null;
$grado = null;
$firmaBytes = null;

if (isset( $_POST["idDirector"] ) && isset( $_POST["nombre"] ) && isset( $_POST["grado"] ) && isset( $_POST["firma"] )) {
    $directorId = $_POST["idDirector"];
    $name = $_POST["nombre"];
    $degree = $_POST["grado"];
    $base64String = $_POST["firma"];
    $firmaBytes = base64_decode($base64String);
} else {
    die("Error: No se encontraron datos en la solicitud POST.");
}

echo $db->updateDirector($idDirector, $nombre, $grado, $firmaBytes);

?>