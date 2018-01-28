<?php
session_start();
unset($_SESSION['city']);
session_destroy();

header("Location: index.php");
exit;
?>
