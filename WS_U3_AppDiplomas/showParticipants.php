<?php

require "DBManager.php";


$db = new DBManager();
$numero_control = null;

if (isset($_GET["numero_control"])){
    $numero_control = $_GET["numero_control"];
}

echo json_encode($db->showParticipants($numero_control));

?>