<?php
	require("db_connection.php");
	
	$id = $_REQUEST["player_ID"];
	
	//yung tapos na
	echo json_encode($db->query("SELECT quest_ID AS x, quest_name, (SELECT COUNT(*) FROM quest_stop WHERE quest_ID = x) AS y, points FROM quest WHERE quest_ID NOT IN (SELECT quest_ID FROM finished_quest WHERE player_ID = '$id') AND quest_ID NOT IN (SELECT quest_ID FROM ongoing_quest WHERE player_ID = '$id')")->fetchAll(PDO::FETCH_ASSOC));
?>