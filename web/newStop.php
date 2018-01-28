<?php

require("db_connection.php");
require('functions.php');

$cityID = intval($_SESSION['city']);
$name = $_REQUEST['stop_name'];
$points = $_REQUEST['stop_points'];
$latitude = $_REQUEST['latitude'];
$longitude = $_REQUEST['longitude'];
$description = $_REQUEST['description'];

$placeInsert = "INSERT INTO place (place_name, description, latitude, longitude) VALUES ('$name', '$description', '$latitude', '$longitude')";

try{
	$result = $db->prepare($placeInsert)->execute();
}catch(Exception $e){}

$placeID = $db->query("SELECT LAST_INSERT_ID()")->fetchAll(PDO::FETCH_ASSOC)[0]["LAST_INSERT_ID()"];

$stopInsert = "INSERT INTO stop (stop_ID, city_ID, points) VALUES ('$placeID', $cityID, '$points')";

try{
	$result = $db->prepare($stopInsert)->execute();
}catch(Exception $e){}

header("Location: stops.php");
exit();

?>