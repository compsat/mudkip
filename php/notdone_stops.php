<?php
	require("db_connection.php");
	
	$id = $_REQUEST["player_ID"];
	
	//yung tapos na
	echo json_encode($db->query("SELECT DISTINCT stop.stop_ID, place.place_name, place.description, place.latitude, place.longitude FROM stop, place WHERE stop.stop_ID NOT IN (SELECT stop_ID FROM visited_stop WHERE player_ID = '$id') AND stop_ID = place.place_ID")->fetchAll(PDO::FETCH_ASSOC));
?>
