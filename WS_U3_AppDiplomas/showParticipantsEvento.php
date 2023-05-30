<?php

require "DBManager.php";

$db = new DBManager();

$idEvento;

if (isset($_GET["idEvento"])) {
    $idEvento = $_GET["idEvento"];
} else die("Error. El idEvento es requerido en el url");

echo json_encode($db->showParticipantsEvento($idEvento));

?>