
<?php
/*
    Name: Arun Ragavendhar
    Student ID: 104837257
    Purpose: This PHP script processes customer login and validates credentials against customer.xml.
*/

session_start();

if (isset($_POST['email'], $_POST['password']) && !empty($_POST['email']) && !empty($_POST['password'])) {
    // Defining the path to the XML file
    $xmlFile = '../../data/customer.xml';

    // Loading the customer XML file
    if (!file_exists($xmlFile)) {
        echo "<p class ='error-message'>Customer data not found. Please register first.</p><br>";
        echo "<p><button type='button' class ='button'><a href='register.html'>Back to Registration</a></button></p>";
        exit();
    }

    $xml = new DOMDocument();
    $xml->load($xmlFile);

    // Retrieving form data
    $email = $_POST['email'];
    $password = $_POST['password'];

    // Email format validation
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo "<p class ='error-message'>Invalid email format. Please enter a valid email.</p><br>";
        exit();
    }

    // Checking if email and password match any customer in the XML
    $validLogin = false;
    foreach ($xml->getElementsByTagName('customer') as $customer) {
        $storedEmail = $customer->getElementsByTagName('email')->item(0)->nodeValue;
        $storedPassword = $customer->getElementsByTagName('password')->item(0)->nodeValue;

        if ($storedEmail === $email && $storedPassword === $password) {
            $validLogin = true;
            $_SESSION['firstName'] =$customer->getElementsByTagName('firstName')->item(0)->nodeValue;
            $_SESSION['CustomerId'] =$customer->getElementsByTagName('id')->item(0)->nodeValue;
            break;
        }
    }

    // Validating login credentials
    if ($validLogin) {
        echo "<p class ='success-message'>Welcome  <strong>".$_SESSION['firstName']."</strong></p><br>";
        echo "<p class ='success-message'>Login successful! Redirecting to the catalog...</p>";
    } else {
        echo "<p class ='error-message'>Invalid email or password. Please try again.</p><br>";
    }
}

else{
    echo "<p class ='error-message'>Please enter all fields to login</p>";
}
?>
