<?php

require "DBManager.php";

$db = new DBManager();

$idParticipante;
$idEvento;


if (isset($_POST["Participante_idParticipante"]) && isset($_POST["Evento_idEvento"])) {
    $idParticipante = $_POST["Participante_idParticipante"];
    $idEvento = $_POST["Evento_idEvento"];
}
else die ("Error. Todos los datos son requeridos");

echo $db->createItemEvento($idParticipante, $idEvento);

?>