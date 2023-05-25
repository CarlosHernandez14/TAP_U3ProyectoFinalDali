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
    && isset($_POST["grado"]) && isset($_FILES["firma"])) {
    $username = $_POST["username"];
    $password = $_POST["password"];
    $nombre = $_POST["nombre"];
    $grado = $_POST["grado"];
    $firma = file_get_contents($_FILES["firma"]["tmp_name"]); // Leer el contenido de la imagen subida
} else {
    die ("Error. Todos los datos son requeridos");
}

echo "Datos recibidos:";
var_dump($username, $password, $nombre, $grado, $_FILES["firma"]);

echo $db->addDirector($username, $password, $tipo_usuario, $grado, $firma, $nombre);


?>