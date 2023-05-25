<?php

require "DBManager.php";

$db = new DBManager();

$username = null;
$password = null;

if (isset($_GET["username"]) && isset($_GET["password"])) {
    $username = $_GET["username"];
    $password = $_GET["password"];
}

echo $db->userExist($username, $password);

?>