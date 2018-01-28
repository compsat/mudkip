<!DOCTYPE html>
<html lang="en">
<head>

  <!-- Basic Page Needs
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta charset="utf-8">
  <title>Description</title>
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

  <!-- JS
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
  <script type="text/javascript" src="js/general.js"></script>

  <!-- Favicon
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <link rel="icon" type="image/png" href="images/favicon.png">

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
        <h1>Description</h1>
      </div>
    </div>
    <div class="row content padding with-table">
      <h3>Profile</h3>
      <?php
        require("db_connection.php");
        require("functions.php");
        $cityID = intval($_SESSION['city']);
        $data = $db->query("SELECT place_name, description FROM place WHERE place_ID = $cityID")->fetchAll(PDO::FETCH_ASSOC)[0];
        echo "<h5>" . $data["place_name"] . "</h5>";
        echo "<p id='d-content'>" . $data["description"] . "</p>";
      ?>
          <a id="editToggle" class="button button-primary">Edit</a>
    </div>
  </div>

  <div id="modal" class="modal">
    <div class="container">
      <div class="row modal-content">
        <div class="eight columns content padding no_float" style="margin:auto">
          <h2 id="close" class="close-btn u-pull-right">&times;</h2>
          <h3 id="modalTitle">Title</h3>
          <form action="editDescription.php" method="post">
            <textarea id="d-input" name="description" class="twelve columns"></textarea>
            <button class="button-primary" id="submit"></button>
          </form>
        </div>
      </div>
    </div>
  </div>
<!-- End Document
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
</body>
</html>
