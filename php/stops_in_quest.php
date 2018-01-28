<?php
	require("db_connection.php");
	
	$id = $_REQUEST["quest_ID"];
	
	echo json_encode($db->query("SELECT DISTINCT stop.stop_ID, place.place_name, place.description, place.latitude, place.longitude FROM stop, place, quest_stop WHERE stop.stop_ID = place.place_ID AND quest_stop.quest_ID = '$id' AND quest_stop.stop_ID = stop.stop_ID")->fetchAll(PDO::FETCH_ASSOC));
?>
