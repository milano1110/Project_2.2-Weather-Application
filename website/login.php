<?php

//Start de sessie
session_start();

//Check of het formulier is ingevuld
if(isset($_POST['Submit'])){

    //Open CSV
    $csv = array();
    $lines = file('logindb.csv', FILE_IGNORE_NEW_LINES);

    foreach ($lines as $key => $value)
    {
        $csv[$key] = str_getcsv($value);
    }

    //Variabelen username en password die ingevuld zijn
    $Username = isset($_POST['Username']) ? $_POST['Username'] : '';
    $Password = isset($_POST['Password']) ? $_POST['Password'] : '';

    $check = password_verify($Password, $csv[1][1]);


    //Check of de ingevulde gegevens juist zijn
    if ($csv[1][0] == $Username && $check == true) {
        $_SESSION['UserData']['Username']=$csv[1][0];
        header("location:WeerGrafiek.php");
    } else {
        $msg="<span style='color:red'>Invalid Login Details</span>";
    }
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Emirates Weather Information</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<img src="Afbeeldingen/Emirates_logo.jpg" alt="Emirates Logo"><br>
    <div class="login">
    <form id="login" method="post" action="">
        <label>
            <b>Username</b>
            <?php if(isset($msg)){
             //Echo string message
             echo $msg;
                } ?>
        </label>
        <input type="text" name="Username" placeholder="Username">
        <br><br>
        <label><b>Password
        </b>
        </label>
        <input type="password" name="Password" placeholder="Password">
        <br><br>
        <input type="submit" name="Submit" value="Log In Here">
        <br><br>
    </form>
</div>
</body>
</html>

