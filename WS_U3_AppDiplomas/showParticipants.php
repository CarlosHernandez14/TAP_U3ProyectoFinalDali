<?php

require "DBManager.php";


$db = new DBManager();
$nombre = null;

if (isset($_GET["nombre"])){
    $nombre = $_GET["nombre"];
}

echo json_encode($db->showParticipants());

?>