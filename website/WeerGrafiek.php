<?php

//Start de sessie
session_start();

//Check of de gebruiker is ingelogd
if(!isset($_SESSION['UserData']['Username'])){
    header("location:login.php");
    exit;
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Emirates Weather Information</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="icon" href="Afbeeldingen/icon.png">
</head>
<div class="topnav">
    <img class= "logo" src="Afbeeldingen/Emirates_logo.jpg" alt="Emirates Logo"><br>
    <a class="active" href="WeerGrafiek.php">Home</a>
    <a href="Asia_weatherstations.php">All Asia weatherstations</a>
    <a href="Top_10_Weather.php">Top 10 Weatherstations</a>
    <a style = left: 0 href="logout.php">Logout</a>
</div>
<body>
<div class="welcome">
    <h1>Welcome to the weather application of the Emirates Airlines</h1>
    <p>Hello and welcome to the weather application.</p>
    <p>Below is a description of the different pages of this application:</p>
    <p>
        - All Asia weatherstations: This page displays all the weather data of the stations in Asia with a minimum temperatures below 0 degrees Celsius, per day<br>
        - Top 10 Weatherstations: A top 10 of the stations in Asia with the lowest temperature per day
    </p>

</div>
</body>
</html>