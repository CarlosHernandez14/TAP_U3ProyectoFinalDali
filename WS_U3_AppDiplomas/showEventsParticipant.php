<?php

require "DBManager.php";

$db = new DBManager();

$participantId = null;

if( isset( $_POST["idParticipante"] )) {
    $participantId = $_POST["idParticipante"];
} else die("Error. Se requiere el id de participante");

echo json_encode($db->showEventsParticipant($participantId));

?>