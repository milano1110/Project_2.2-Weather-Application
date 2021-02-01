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
    <a class="active" href="Asia_weatherstations.php">All Asia weatherstations</a>
    <a href="Top_10_Weather.php">Top 10 Weatherstations</a>
    <a href="1548769538143-695.xml" download>Download</a>
    <a style = left: 0 href="logout.php">Logout</a>
</div>
<body>
<h2>All stations in Asia below 0 C</h2>

<?php

error_reporting(E_ERROR | E_WARNING | E_PARSE);

//Open XML
$xml=simplexml_load_file("1548769538143-695.xml") or die("Error: Cannot create object");

//Maak arrays voor alle typen data aan
$temp = $xml->xpath("/WEATHERDATA/MEASUREMENT/TEMP");

$stn = $xml->xpath("/WEATHERDATA/MEASUREMENT/STN");

$date = $xml->xpath("/WEATHERDATA/MEASUREMENT/DATE");

$time = $xml->xpath("/WEATHERDATA/MEASUREMENT/TIME");

$dewp = $xml->xpath("/WEATHERDATA/MEASUREMENT/DEWP");

$stp = $xml->xpath("/WEATHERDATA/MEASUREMENT/STP");

$slp = $xml->xpath("/WEATHERDATA/MEASUREMENT/SLP");

$visib = $xml->xpath("/WEATHERDATA/MEASUREMENT/VISIB");

$wdsp = $xml->xpath("/WEATHERDATA/MEASUREMENT/WDSP");

$prcp = $xml->xpath("/WEATHERDATA/MEASUREMENT/PRCP");

$sndp = $xml->xpath("/WEATHERDATA/MEASUREMENT/SNDP");

$frshtt = $xml->xpath("/WEATHERDATA/MEASUREMENT/FRSHTT");

$cldc = $xml->xpath("/WEATHERDATA/MEASUREMENT/CLDC");

$wnddir = $xml->xpath("/WEATHERDATA/MEASUREMENT/WNDDIR");

//Plaats data van XML in de bijbehorende array
$temparray = [];

for ($i = 0; $i < count($temp); $i++) {
    array_push($temparray, $temp[$i][0]);
}

$stnarray = [];

for ($i = 0; $i < count($stn); $i++) {
    array_push($stnarray, $stn[$i][0]);
}

$datearray = [];

for ($i = 0; $i < count($date); $i++) {
    array_push($datearray, $date[$i][0]);
}

$timearray = [];

for ($i = 0; $i < count($time); $i++) {
    array_push($timearray, $time[$i][0]);
}

$dewparray = [];

for ($i = 0; $i < count($dewp); $i++) {
    array_push($dewparray, $dewp[$i][0]);
}

$stparray = [];

for ($i = 0; $i < count($stp); $i++) {
    array_push($stparray, $stp[$i][0]);
}

$slparray = [];

for ($i = 0; $i < count($slp); $i++) {
    array_push($slparray, $slp[$i][0]);
}

$visibarray = [];

for ($i = 0; $i < count($visib); $i++) {
    array_push($visibarray, $visib[$i][0]);
}

$wdsparray = [];

for ($i = 0; $i < count($wdsp); $i++) {
    array_push($wdsparray, $wdsp[$i][0]);
}

$prcparray = [];

for ($i = 0; $i < count($prcp); $i++) {
    array_push($prcparray, $prcp[$i][0]);
}

$sndparray = [];

for ($i = 0; $i < count($sndp); $i++) {
    array_push($sndparray, $sndp[$i][0]);
}

$frshttarray = [];

for ($i = 0; $i < count($frshtt); $i++) {
    array_push($frshttarray, $frshtt[$i][0]);
}

$cldcarray = [];

for ($i = 0; $i < count($cldc); $i++) {
    array_push($cldcarray, $cldc[$i][0]);
}

$wnddirarray = [];

for ($i = 0; $i < count($wnddir); $i++) {
    array_push($wnddirarray, $wnddir[$i][0]);
}

//Array waar alles in wordt opgeslagen
$data = [];

//Plaats alle data in de juiste volgorde in de array
for ($i = 0; $i < count($stnarray); $i++) {
    $data[$i] = [];
    array_push($data[$i], $stnarray[$i], $datearray[$i], $timearray[$i], $temparray[$i], $dewparray[$i], $stparray[$i],
        $slparray[$i], $visibarray[$i], $wdsparray[$i], $prcparray[$i], $sndparray[$i], $frshttarray[$i], $cldcarray[$i],
        $wnddirarray[$i]);
}

//Verwijder stations waar de temperatuur 0.0 of hoger is
for ($i = 0; $i < count($data); $i++) {
    $x = 0;
    if ($data[$i][$x+3] >= 0.0) {
        unset($data[$i]);
    }
}
?>
<link rel="stylesheet" href="css/style.css">
<table style="width: 95%">
    <tr>
        <th>Station</th>
        <th>Date</th>
        <th>Time</th>
        <th>Temperature</th>
        <th>Dewpoint</th>
        <th>STP</th>
        <th>SLP</th>
        <th>Visibility</th>
        <th>WDSP</th>
        <th>PRCP</th>
        <th>SNDP</th>
        <th>FRSHTT</th>
        <th>CLDC</th>
        <th>WNDDIR</th>
    </tr>
<?php
//Plaats de data in een tabel
for ($i = 0; $i < count($data); $i++) {
    if (!empty($data[$i])) {
        $x = 0;
        ?>

            <tr>
                <th><?php echo $data[$i][$x] ?></th>
                <th><?php echo $data[$i][$x + 1] ?></th>
                <th><?php echo $data[$i][$x + 2] ?></th>
                <th><?php echo $data[$i][$x + 3] ?></th>
                <th><?php echo $data[$i][$x + 4] ?></th>
                <th><?php echo $data[$i][$x + 5] ?></th>
                <th><?php echo $data[$i][$x + 6] ?></th>
                <th><?php echo $data[$i][$x + 7] ?></th>
                <th><?php echo $data[$i][$x + 8] ?></th>
                <th><?php echo $data[$i][$x + 9] ?></th>
                <th><?php echo $data[$i][$x + 10] ?></th>
                <th><?php echo $data[$i][$x + 11] ?></th>
                <th><?php echo $data[$i][$x + 12] ?></th>
                <th><?php echo $data[$i][$x + 13] ?></th>
            </tr>

        <?php
    }
}
?>
</table>
</body>
</html>