<?php
/*
Name: Arun Ragavendhar
Student ID: 104837257
Purpose: This script handles manager login and displays customer data using XSLT.
*/
session_start();
if(isset($_POST['managerId']) && isset($_POST['password']) && !empty($_POST['managerId']) && !empty($_POST['password'])){
    // Path to the manager credentials and XML files
    $filename = '../../data/manager.txt';
    $xmlFile = '../../data/goods.xml';
    $xslFile = 'manager_catalog.xslt';

    // Retrieving form data
    $managerId = $_POST['managerId'];
    $password = $_POST['password'];
    $valid = false;

    // Validating manager credentials
    if (file_exists($filename)) {
        $lines = file($filename);
        foreach ($lines as $line) {
            list($storedName,$storedId, $storedPassword) = explode(', ', trim($line));
            if ($managerId == $storedId && $password == $storedPassword) {
                $valid = true;
                break;
            }
        }
    }

    // If login is valid, displaying customer details using XSLT
    if ($valid) {
        $_SESSION['managerName'] = $storedName;
        $_SESSION['managerId'] = $managerId;
        echo "<p class ='success-message'>Login successful!  Welcome , <strong>" .$storedName. " Manager ID : ". $managerId."</strong></p><br>"; 
    }
    else{
        echo "<p class = 'error-message'>Login failed. Invalid manager ID or password.</p>";
    }
}
else{
    echo "<p class = 'error-message'>Please enter all fields to login</p>";
}
?>
