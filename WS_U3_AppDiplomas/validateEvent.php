<?php

require "DBManager.php";

$db = new DBManager();

$eventId = null;

if (isset( $_POST["idEvento"] )) {
    $eventId = $_POST["idEvento"];
} else {
    die("Error: No se encontraron datos en la solicitud POST.");
}

echo $db->validateEvent($eventId);

?>