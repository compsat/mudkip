<?php
	require("db_connection.php");
	
	$id = $_REQUEST["city_ID"];
	
	echo json_encode($db->query("SELECT DISTINCT stop.stop_ID, place.place_name, place.description, place.latitude, place.longitude FROM stop, place WHERE stop.stop_ID = place.place_ID AND stop.city_ID = '$id'")->fetchAll(PDO::FETCH_ASSOC));
?>