<?php
	require("db_connection.php");
	
	echo json_encode($db->query("SELECT place_ID, place_name, description, latitude, longitude FROM place WHERE place_type = 'C'")->fetchAll(PDO::FETCH_ASSOC));
?>