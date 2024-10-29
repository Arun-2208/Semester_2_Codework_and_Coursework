<?php
session_start();

/*
    Name: Arun Ragavendhar
    Student ID: 104837257
    Purpose: This script removes all quantities of an item from the cart, updates the session, and updates the goods.xml file.
*/

// Path to goods.xml
$xmlFile = '../../data/goods.xml';

if (isset($_POST['itemNumber']) && file_exists($xmlFile)) {
    $itemNumber = $_POST['itemNumber'];

    if (isset($_SESSION['cart'][$itemNumber])) {
        // Loading the XML file
        $xml = simplexml_load_file($xmlFile);
        
        // Getting the quantity of the item in the cart before removing
        $itemQuantityInCart = $_SESSION['cart'][$itemNumber]['quantity'];
        
        // Finding the item in the XML and update quantities
        foreach ($xml->item as $item) {
            if ((int)$item->itemNumber == $itemNumber) {
                // Update the XML file: subtract all quantities from quantityOnHold and add back to quantityAvailable
                $item->quantityOnHold = (int)$item->quantityOnHold - $itemQuantityInCart;
                $item->quantityAvailable = (int)$item->quantityAvailable + $itemQuantityInCart;
                
                // Saving the updated XML file
		        $xml->formatOutput = true;
                $xml->asXML($xmlFile);
                break;
            }
        }

        // Removing the item completely from the session cart
        unset($_SESSION['cart'][$itemNumber]);

        echo "<p class ='success-message'>All items removed from cart and quantities updated in goods.xml.</p>";
    } 
    else {
        echo "<p class ='error-message'>Item not found in cart.</p>";
    }
} 
else {
    echo "<p class ='error-message'>Invalid request or goods.xml file not found.</p>";
}
?>
