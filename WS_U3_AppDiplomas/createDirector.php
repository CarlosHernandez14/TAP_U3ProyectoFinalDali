<?php

require "DBManager.php";

$db = new DBManager();

$username;
$password;
$tipo_usuario = "Director";
$nombre;
$grado;
$firma;

if (isset($_POST["username"]) && isset($_POST["password"]) && isset($_POST["nombre"]) 
    && isset($_POST["grado"]) && isset($_POST["firma"])) {
    $username = $_POST["username"];
    $password = $_POST["password"];
    $nombre = $_POST["nombre"];
    $grado = $_POST["grado"];
    $firma = base64_decode($_POST["firma"]); // Leer el contenido de la imagen subida
} else {
    die ("Error. Todos los datos son requeridos");
}


echo $db->createDirector($username, $password, $tipo_usuario, $grado, $firma, $nombre);


?>