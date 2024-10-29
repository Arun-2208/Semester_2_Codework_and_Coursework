<?php
/*
    Name: Arun Ragavendhar 
    Student ID: 104837257
    Purpose: This PHP script processes , validates customer registration and stores data in customer.xml.
*/

if(isset($_POST['email'], $_POST['firstName'], $_POST['lastName'], $_POST['password'], $_POST['retypePassword'], $_POST['phone']) &&
   !empty($_POST['email']) && !empty($_POST['firstName']) && !empty($_POST['lastName']) && !empty($_POST['password']) && !empty($_POST['retypePassword']) && !empty($_POST['phone'])) {
    // Defining the path to the XML file
    $xmlFile = '../../data/customer.xml';

    // Creating a new DOMDocument
    $xml = new DOMDocument('1.0', 'UTF-8');
    $xml->preserveWhiteSpace = false;
    $xml->formatOutput = true;

    // Checking if the XML file exists
    if (!file_exists($xmlFile)) {
        // If the file doesn't exist, creating the root structure
        $root = $xml->createElement('customers');
        $xml->appendChild($root);
        // Saving the file with the root element
        $xml->save($xmlFile);
    } else {
        // Loading the existing XML file
        $xml->load($xmlFile);
    }

    // Retrieving the root element 
    $root = $xml->getElementsByTagName('customers')->item(0);
    $xml->formatOutput = true;

    // Retrieving form data
    $email = $_POST['email'];
    $firstName = $_POST['firstName'];
    $lastName = $_POST['lastName'];
    $password = $_POST['password'];
    $repassword = $_POST['retypePassword'];
    $phone = $_POST['phone']; 

    // Checking if email is unique
    $unique = true;
    foreach ($xml->getElementsByTagName('customer') as $customer) {
        if ($customer->getElementsByTagName('email')->item(0)->nodeValue == $email) {
            $unique = false;
            break;
        }
    }

    // Generating a unique ID for the customer
    $customerID = uniqid();

    // Email format validation
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo "<p class = 'error-message'>Invalid email format. Please enter a valid email.</p><br>";
       
    // First Name and Last Name validation
    } elseif (!preg_match("/^[a-zA-Z ]+$/", $firstName) || !preg_match("/^[a-zA-Z ]+$/", $lastName)) {
        echo "<p class = 'error-message'>Invalid first name or last name. Only letters and spaces are allowed.</p><br>";
       
    // Password strength validation (at least 6 characters, 1 letter, 1 number)
    } elseif (!preg_match("/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/", $password)) {
        echo "<p class = 'error-message'>Password must be at least 6 characters long and include at least one letter and one number.</p><br>";
        
    // Password match validation
    } elseif ($password !== $repassword) {
        echo "<p class = 'error-message'>Entered passwords do not match. Please try again.</p><br>";
        
    // Unique email validation
    } elseif (!$unique) {
        echo "<p class = 'error-message'>Email already exists. Please use another email.</p><br>";
        
    // Phone number format validation
    } elseif (!preg_match('/^0\d{8}$/', $phone) && !preg_match('/^0\d \d{8}$/', $phone)) {
        echo "<p class = 'error-message'>Invalid phone number format. Please follow ( 0d dddddddd ) or ( 0dddddddd ) format.</p><br>";
        
    } else {
        // Creating a new customer element
        $newCustomer = $xml->createElement('customer');
        
        // Adding customer ID
        $idElement = $xml->createElement('id', $customerID);
        $newCustomer->appendChild($idElement);
        
        // Adding email
        $emailElement = $xml->createElement('email', $email);
        $newCustomer->appendChild($emailElement);
        
        // Adding first name
        $firstNameElement = $xml->createElement('firstName', $firstName);
        $newCustomer->appendChild($firstNameElement);
        
        // Adding last name
        $lastNameElement = $xml->createElement('lastName', $lastName);
        $newCustomer->appendChild($lastNameElement);
        
        // Adding password
        $passwordElement = $xml->createElement('password', $password);
        $newCustomer->appendChild($passwordElement);
        
        // Adding phone
        $phoneElement = $xml->createElement('phone', $phone);
        $newCustomer->appendChild($phoneElement);
        
        // Appending the new customer to the root element
        $root->appendChild($newCustomer);
        
        // Saving the updated XML back to the file
        $xml->save($xmlFile);
        
        // Confirmation message
        echo "<p class = 'success-message'>Registration successful!</p><br>";
        echo "<p class = 'success-message'>Your Unique Customer ID : <strong>".$customerID."</strong></p><br>";
    }
}
else{
    echo "<p class = 'error-message'>Please enter all the fields to register !</p>";
}
?>
