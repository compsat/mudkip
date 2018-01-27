<!DOCTYPE html>
<html lang="en">
<head>

  <!-- Basic Page Needs
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta charset="utf-8">
  <title>Quests</title>
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

  <!-- php insert
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <?php 
    require("db_connection.php");
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
        <h1>Quests</h1>
      </div>
    </div>
    <div class="row content padding with-table">
      <a id="addToggle" class=" button button-primary">Add</a>
      <table class="twelve columns">
        <thead>
          <th>Name</th>
          <th>Stops</th>
          <th>No. Done</th>
          <th>No. of Ongoing</th>
        </thead>
        <tr>
          <td class="center" colspan="1000">None</td>
        </tr>
      </table>
    </div>
  </div>

  <div id="modal" class="modal">
    <div class="container">
      <div class="row modal-content">
        <div class="eight columns content padding no_float" style="margin:auto">
          <h2 id="close" class="close-btn u-pull-right">&times;</h2>
          <h3 id="modalTitle">Title</h3>
          <form>
            <label for="stop_name">Quest Name</label>
            <input type="text" name="stop_name" class="twelve columns">

            <table id="quest_stops" class="twelve columns">
              <thead>
                <th>Index</th>
                <th>Stop</th>
              </thead>

              <tr id="no_stop">
                <td class="center" colspan="1000">None Selected</td>
              </tr>
            </table>

            <label for="stops">Add Stop</label>
            <select id="stop" class="twelve columns">
              <?php
                $city_id = intval($_SESSION['city']);
                $stops = $db->query("SELECT stop_ID FROM stop WHERE city_ID=$city_id")->fetchAll(PDO::FETCH_ASSOC);
                $rand;
                foreach($stops as $stop){
                  $stop_id = intval($stop['stop_ID']);
                  $stop_name = $db->query("SELECT place_name FROM place WHERE place_ID=$stop_id")->fetchAll(PDO::FETCH_ASSOC)[0]['place_name'];
                  echo "<option value='$stop_id'>$stop_name</option>";
                }
              ?>
            </select>
            <a id="addStop" class="twelve columns button">add stop</a>

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
