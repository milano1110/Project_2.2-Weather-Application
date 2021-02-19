<?php
include "converter.php";
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
    <a href="XML/output.xml" download>Download</a>
    <a style = left: 0 href="logout.php">Logout</a>
</div>
<body>
<h2>Top 10 coldest stations in C</h2>
<?php
//Call function converter.php
convertCsvToXmlFile("C:/xampp/htdocs/project/Project_Weer/CSV/data.csv","C:/xampp/htdocs/project/Project_Weer/XML/output.xml");
//Load XML file
$xml=simplexml_load_file("C:/xampp/htdocs/Project/Project_Weer/XML/output.xml") or die("Error: Cannot create object");

//Ophalen temperatuur
$temp = $xml->xpath("/WEATHERDATA/MEASUREMENT/TEMP");

//Ophalen station
$stn = $xml->xpath("/WEATHERDATA/MEASUREMENT/STN");

//Ophalen Landen
$coun = $xml->xpath("/WEATHERDATA/MEASUREMENT/COUNTRY");

$data = array();

//Voeg alle data aan multidimensionaal array toe
for ($i = 0; $i < count($stn); $i++) {
    $stn_n = $stn[$i][0];
    $coun_n = $coun[$i][0];
    $temp_n = $temp[$i][0];

    array_push($data, [$stn_n, $coun_n, $temp_n]);

}
//Maak array uniek
$tempArr = array_unique(array_column($data, 0));
//Computes the intersection of arrays using keys for comparison
$data = array_intersect_key($data, $tempArr);
//Temp sorteren
function compareTemp($a, $b)
{
    return (floatval($a[2]) - floatval($b[2])) <=> 0;
}
usort($data, 'compareTemp');


?>
<link rel="stylesheet" href="css/style.css">
<table style="width: 95%">
    <tr>
        <th>Station</th>
        <th>Land</th>
        <th>Temperature</th>
    </tr>
    <?php
    //Plaats de data in een tabel met 10 lijnen
    for ($i = 0; $i < 10; $i++) {
        if (!empty($data[$i])) {
            ?>

            <tr>
                <th><?php echo $data[$i][0] ?></th>
                <th><?php echo $data[$i][1] ?></th>
                <th><?php echo $data[$i][2] ?></th>

            </tr>

            <?php
        }
    }
    ?>
</table>
</body>
</html>