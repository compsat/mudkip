<?php
	require("db_connection.php");
	
	$id = $_REQUEST["stop_ID"];
	
	//yung tapos na
	echo json_encode($db->query("SELECT place_name, description, latitude, longitude, longitude, points FROM place WHERE place_ID = '$id'")->fetchAll(PDO::FETCH_ASSOC));
?>