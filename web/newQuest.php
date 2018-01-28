<?php

require("db_connection.php");
require("functions.php");

$cityID = intval($_SESSION['city']);
$name = $_REQUEST['quest_name'];
$stops = $_POST['stops'];

$sum = 0;

foreach($stops as $stop){
	$val = intval($stop);
	$point = $db->query("SELECT points FROM stop WHERE stop_ID=$val")->fetchAll(PDO::FETCH_ASSOC)[0]['points'];
	$sum += intval($point);
}

$questInsert = "INSERT INTO quest (city_ID, quest_name, points) VALUES ($cityID,'$name',$sum)";
try{
	$result = $db->prepare($questInsert)->execute();
}catch(Exception $e){}

$questID = $db->query("SELECT LAST_INSERT_ID()")->fetchAll(PDO::FETCH_ASSOC)[0]["LAST_INSERT_ID()"];

foreach($stops as $stop){
	$val = intval($stop);
	$questStop = "INSERT INTO quest_stop (quest_ID, stop_ID) VALUES ($questID, $val)";
	try{
		$result = $db->prepare($questStop)->execute();
	}catch(Exception $e){}
}
header("Location: quests.php");
exit();

?>