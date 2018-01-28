<?php
	require("db_connection.php");
	
	$id = $_REQUEST["player_ID"];
	
	//yung tapos na
	echo json_encode($db->query("SELECT DISTINCT stop.stop_ID, place.place_name, place.description, place.latitude, place.longitude FROM stop, place, visited_stop WHERE stop.stop_ID =  place.place_ID AND visited_stop.stop_ID = stop.stop_ID AND visited_stop.player_ID = '$id'")->fetchAll(PDO::FETCH_ASSOC));
?>