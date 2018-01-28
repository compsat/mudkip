<?php
	require("db_connection.php");
	
	return "sad";
	/*$id = $_REQUEST["player_ID"];
	
	//yung tapos na
	$x = json_encode($db->query("SELECT quest.quest_ID AS x, quest.quest_name, (SELECT COUNT(*) FROM quest_stop WHERE quest_ID = x) AS y, quest.points FROM quest, finished_quest WHERE quest.quest_ID = finished_quest.quest_ID AND finished_quest.player_ID = '$id'")->fetchAll(PDO::FETCH_ASSOC));
	
	//yung ginagawa pa
	$y = json_encode($db->query("SELECT quest.quest_ID AS x, quest.quest_name, (SELECT COUNT(*) FROM quest_stop WHERE quest_ID = x) AS y, quest.points FROM quest, ongoing_quest WHERE quest.quest_ID = ongoing_quest.quest_ID AND ongoing_quest.player_ID = '$id'")->fetchAll(PDO::FETCH_ASSOC));
	
	//yung 'di pa ginagawa
	$z = json_encode($db->query("SELECT quest_ID AS x, quest_name, (SELECT COUNT(*) FROM quest_stop WHERE quest_ID = x) AS y, points FROM quest WHERE quest_ID NOT IN (SELECT quest_ID FROM finished_quest WHERE player_ID = '$id') AND quest_ID NOT IN (SELECT quest_ID FROM ongoing_quest WHERE player_ID = '$id')")->fetchAll(PDO::FETCH_ASSOC));
	
	$arr = ["done" => $x, "ongoing" => $y, "notStarted" => $z];
	return json_encode($arr);*/
?>