<!-- 
    Name: Arun Ragvendhar
    Student ID: 104837257
    Purpose: This page displays a logout message after the customer or manager has logged out.
-->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
    <link rel ='stylesheet' href ="style/style.css">
    <style>
        .button {
            width: 20%;
            align-items: center;
            justify-content: center;
        }
    </style>
</head>
<body>
    <h2>Thank you for using BuyOnline!</h2>
</body>
</html>

<?php
// php code to destroy the session and the session variables
session_start();
if(isset($_SESSION['firstName']) && isset($_SESSION['CustomerId'])) {

    echo "<p class ='success-message'><strong>".$_SESSION['firstName']." , (Customer ID :".$_SESSION['CustomerId'].")</strong> You have successfully logged out.</p>";
    echo "<button type= 'button' class ='button'><a href='buyonline.html'>Return to Home Page</a></button>";
}
if(!isset($_SESSION['firstName']) && !isset($_SESSION['CustomerId'])){
    header("Location:login.html");
}

session_unset();
session_destroy();

?>