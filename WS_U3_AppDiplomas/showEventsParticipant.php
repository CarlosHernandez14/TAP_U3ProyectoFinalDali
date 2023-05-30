<?php

require "DBManager.php";

$db = new DBManager();

$participantId = null;

if( isset( $_GET["idParticipante"] )) {
    $participantId = $_GET["idParticipante"];
} else die("Error. Se requiere el id de participante");

echo json_encode($db->showEventsParticipant($participantId));

?>