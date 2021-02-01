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
    <a href="WeerGrafiek.php">Home</a>
    <a href="Asia_weatherstations.php">All Asia weatherstations</a>
    <a class="active" href="Top_10_Weather.php">Top 10 Weatherstations</a>
    <a href="1548769538143-695.xml" download>Download</a>
    <a style = left: 0 href="logout.php">Logout</a>
</div>
<body>
<h2>Top 10 coldest stations in C</h2>
<?php
$xml=simplexml_load_file("1548769538143-695.xml") or die("Error: Cannot create object");

//Ophalen temperatuur
$temp = $xml->xpath("/WEATHERDATA/MEASUREMENT/TEMP");

//Ophalen station
$stn = $xml->xpath("/WEATHERDATA/MEASUREMENT/STN");

//Array voor temperaturen
$temparray = [];

//Plaats temperaturen in een Array
for ($i = 0; $i < count($temp); $i++) {
    array_push($temparray, $temp[$i][0]);
}

//Array voor stations
$stnarray = [];

//Plaats stations in een array
for ($i = 0; $i < count($stn); $i++) {
    array_push($stnarray, $stn[$i][0]);
}

//Voeg stations en temperaturen bij elkaar met station als key
$zooi = array_combine($stnarray, $temparray);

//Sorteer van laag naar hoog
asort($zooi, SCANDIR_SORT_DESCENDING);

//Verijder alles in de array zodat je de eerste 10 overhoud
$zooi2 = array_slice($zooi, 0, 10, true);
?>
<table style="width:80%">
    <tr>
        <th>Station</th>
        <th>Temperature</th>
    </tr>
<?php
//Plaats de data in een tabel
foreach($zooi2 as $key => $val) {
    ?>

        <tr>
            <th><?php echo $key?></th>
            <th><?php echo $val?></th>
        </tr>

    <?php
}
?>
</table>
</body>
</html>