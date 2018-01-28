<?php
	require("db_connection.php");
	
	$id = $_REQUEST["city_ID"];
	
	echo json_encode($db->query("SELECT DISTINCT quest_ID AS x, quest_name, (SELECT COUNT(*) FROM quest_stop WHERE quest_ID = x) AS y, points FROM quest WHERE city_ID = '$id'")->fetchAll(PDO::FETCH_ASSOC));
?>