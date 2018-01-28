<?php

require("db_connection.php");
require("functions.php");

$cityID = intval($_SESSION['city']);
$description = $_REQUEST['description'];

$edit = "UPDATE place SET description = '$description' WHERE  place_ID=$cityID";

try{
	$result = $db->prepare($edit)->execute();
}catch(Exception $e){}

header("Location: description.php");
exit();

?>