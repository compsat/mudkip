<div class="topnav">
	<h2><a href="dashboard.php">Lakbay</a></h2>
	<?php  
		if(!session_id()) session_start();
		if(isset($_SESSION['city'])){
			echo "<h5 class='log-out'><a href='logout.php'>Log out</a></h5>";
		}
	?>
</div>
