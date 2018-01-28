<?php
require("db_connection.php");
if(!session_id()) session_start();

$username = $_REQUEST['username'];
$password = $_REQUEST['password'];
$user = $db->query("SELECT account_ID FROM account WHERE account_email='$username';")->fetchAll(PDO::FETCH_ASSOC);
$userID = $user[0]['account_ID']; 
if($userID){
	$actualPW = $db->query("SELECT password FROM credentials WHERE account_ID=$userID")->fetchAll(PDO::FETCH_ASSOC);
	if($password == $actualPW[0]["password"]){
		$city = $db->query("SELECT city_ID FROM administrator WHERE administrator_ID=$userID")->fetchAll(PDO::FETCH_ASSOC);
		$_SESSION['city'] = $city[0]["city_ID"];
		header("Location: dashboard.php");
		exit();
	}
}

header("Location: index.php");
exit();
?>