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
            <h5>Area Name</h6>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque convallis massa ac eros cursus, nec mollis massa efficitur. Vestibulum iaculis tellus eu massa mollis dictum. Proin sodales augue vel ex porta iaculis. Aenean quis lorem sem. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Morbi vitae rutrum dolor. Cras finibus semper lacinia. Curabitur facilisis nisi sit amet ligula porttitor porttitor. </p>
            <button>Edit</button>
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