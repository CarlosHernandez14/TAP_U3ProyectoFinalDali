<?php

require "DBManager.php";

$db = new DBManager();

$username;
$password;
$tipo_usuario = "documentos";

if (isset($_POST["username"]) && isset($_POST["password"])) {
    $username = $_POST["username"];
    $password = $_POST["password"];
}

// Prints true of alse 
echo $db->addUser($username, $password, $tipo_usuario);

?>