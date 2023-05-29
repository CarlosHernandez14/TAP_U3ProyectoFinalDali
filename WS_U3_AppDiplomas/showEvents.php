<?php

require "DBManager.php";

$db = new DBManager();

echo json_encode($db->showEvents());

?>