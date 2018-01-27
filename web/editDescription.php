<?php

require("db_connection.php");

//$cityID = $_REQUEST['cityID'];
$cityID = 3;
$description = $_REQUEST['description'];

$edit = "UPDATE place SET description = '$description' WHERE '$cityID' = place_ID";

try{
	$result = $db->prepare($edit)->execute();
}catch(Exception $e){}

header("Location: description.php");
exit();

?>