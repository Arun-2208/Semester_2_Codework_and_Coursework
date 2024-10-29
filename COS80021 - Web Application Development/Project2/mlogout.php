<!-- 
    Name: Arun Ragavendhar 
    Student ID: 104837257
    Purpose: This page displays a logout message after the customer or manager has logged out.
-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> Manager Logout</title>
    <link rel ='stylesheet' href ="style/style.css">
    <style>
        .button {
            width: 35%;
            align-items: center;
            justify-content: center;
    }
    </style>
</head>
<body>
    <h2>Thank you for using Arun's BuyOnline Shopping!</h2>
</body>
</html>

<?php
// php that clears and destroys the session variable and session for a manager login 
session_start();
if(isset($_SESSION['managerName']) && isset($_SESSION['managerId'])) {

    echo "<p class ='success-message'><strong>".$_SESSION['managerName']." , (Manager ID ) : ".$_SESSION['managerId']."</strong> <br> You have successfully logged out.</p>";
    echo "<button type ='button' class ='button'><a href='buyonline.html'>Return to Home Page</a></button>";
}
if(!isset($_SESSION['managerName']) && !isset($_SESSION['managerId'])){
    header("Location:mlogin.html");
}
session_unset();
session_destroy();

?>