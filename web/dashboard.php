<!DOCTYPE html>
<html lang="en">
<head>

  <!-- Basic Page Needs
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta charset="utf-8">
  <title>Dashboard</title>
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Mobile Specific Metas
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- FONT
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link href="//fonts.googleapis.com/css?family=Raleway:400,300,600" rel="stylesheet" type="text/css">

  <!-- CSS
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link rel="stylesheet" href="css/normalize.css">
  <link rel="stylesheet" href="css/skeleton.css">
  <link rel="stylesheet" href="css/general.css">

  <!-- Favicon
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link rel="icon" type="image/png" href="images/favicon.png">


  <!-- PHP Functions
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <?php
    require("functions.php");
  ?>
</head>
<body>

  <!-- Top Navigation
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->

  <?php require('topnav.php'); ?>

  <!-- Primary Page Layout
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <div class="container content-margin">
    <div class="row">
      <div class="twelve columns header">
        <h1>Dashboard</h1>
      </div>
    </div>
    <div class="row content">
        <div class="twelve columns padding">
          <div>
            <h3>Profile</h3>
            <?php
              require("db_connection.php");
              $cityID = intval($_SESSION['city']);
              $data = $db->query("SELECT place_name, description FROM place WHERE place_ID = $cityID")->fetchAll(PDO::FETCH_ASSOC)[0];
              echo "<h5>" . $data["place_name"] . "</h5>";
              echo "<p>" . $data["description"] . "</p>";
            ?>
            <form action="description.php">
              <button type="submit">Edit</button>
            </form>
          </div>

          <div>
            <h3><a href="quests.php">Quests</a></h3>
            <button>Add</button>
          </div>

          <div>
            <h3><a href="stops.php">Stops</a></h3>
            <form action="newStop.php" method="post">
              <button type="submit">Add</button>
            </form>
          </div>
        </div>
    </div>
  </div>
<!-- End Document
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
</body>
</html>
