<!DOCTYPE html>
<html lang="en">
<head>

  <!-- Basic Page Needs
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
  <meta charset="utf-8">
  <title>Stops</title>
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
        <h1>Stops</h1>
      </div>
    </div>
    <div class="row content padding">
      <a id="addToggle" class="button button-primary">Add</a>
      <table class="twelve columns">
        <thead>
          <th>Name</th>
          <th>Location</th>
          <th>Points</th>
          <th>Visit Count</th>
        </thead>
        <tr>
          <?php 
            require("db_connection.php");
            require("functions.php");

            $city_id = intval($_SESSION['city']);
                $stops = $db->query("SELECT stop_ID, points FROM stop WHERE city_ID=$city_id")->fetchAll(PDO::FETCH_ASSOC);
                if($stops){
                  foreach($stops as $stop){
                    $stop_id = intval($stop['stop_ID']);
                    $points = intval($stop['points']);
                    $stop_data = $db->query("SELECT place_name, latitude, longitude FROM place WHERE place_ID=$stop_id")->fetchAll(PDO::FETCH_ASSOC)[0];
                    $stop_loc = $stop_data['latitude'] . ", " . $stop_data['longitude'];
                    $stop_name = $stop_data['place_name'];
                    $vCount = $db->query("SELECT COUNT(*) FROM visited_stop WHERE stop_ID=$stop_id")->fetchAll(PDO::FETCH_ASSOC)[0]["COUNT(*)"];
                    echo "<tr>";
                    echo "<td>$stop_name</td>";
                    echo "<td>$stop_loc</td>";
                    echo "<td>$points</td>";
                    echo "<td>$vCount</td>";
                    echo "</tr>";
                  }
                }else{
                  echo "<td class='center' colspan='1000'>None</td>";
                }
          ?>
        </tr>
      </table>
    </div>
  </div>

  <div id="modal" class="modal">
    <div class="container">
      <div class="row modal-content">
        <div class="eight columns content padding no_float" style="margin:auto;">
          <h2 id="close" class="close-btn u-pull-right">&times;</h2>
          <h3 id="modalTitle">Title</h3>
          <form action="newStop.php" method="post">
            <label for="stop_name">Stop Name</label>
            <input type="text" name="stop_name" class="twelve columns">

            <label for="stop_points">Points</label>
            <input type="number" name="stop_points" class="twelve columns">

            <div class="twelve columns"> 
              <div class="six columns">
                <label for="latitude" class="alt-label">Latitude</label>
                <input type="number" name="latitude" class="twelve columns" step="0.000001">
              </div>           

              <div class="six columns">
                <label for="longitude" class="alt-label">Longitude</label>
                <input type="number" name="longitude" class="twelve columns" step="0.000001">
              </div>
            </div>

            <label for="description">Description</label>
            <textarea name="description" class="twelve columns"></textarea>

            <button type="submit" class="button-primary" id="submit"></button>
          </form>
        </div>
      </div>
    </div>
  </div>
<!-- End Document
  –––––––––––––––––––––––––––––––––––––––––––––––––– -->
</body>
</html>
