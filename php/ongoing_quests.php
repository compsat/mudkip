<?php
	require("db_connection.php");
	
	$id = $_REQUEST["player_ID"];
	
	//yung tapos na
	echo json_encode($db->query("SELECT quest.quest_ID AS x, quest.quest_name, (SELECT COUNT(*) FROM quest_stop WHERE quest_ID = x) AS y, quest.points FROM quest, ongoing_quest WHERE quest.quest_ID = ongoing_quest.quest_ID AND ongoing_quest.player_ID = '$id'")->fetchAll(PDO::FETCH_ASSOC));
?>