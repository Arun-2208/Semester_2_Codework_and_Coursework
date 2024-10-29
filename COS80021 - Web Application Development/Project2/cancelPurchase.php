<?php
session_start();

/*
    Name: Arun Ragavendhar
    Student ID: 104837257
    Purpose: This script cancels the purchase and restores item quantities in goods.xml.
*/

$xmlFile = '../../data/goods.xml';

if(isset($_SESSION['cart']) && !empty($_SESSION['cart'])){
    if (file_exists($xmlFile)) {
        $xml = simplexml_load_file($xmlFile);

        // Restoring the XML by decreasing quantity on hold and increasing available quantity
        foreach ($_SESSION['cart'] as $itemNumber => $cartItem) {
            foreach ($xml->item as $item) {
                if ((int)$item->itemNumber == $itemNumber) {
                    $item->quantityOnHold = (int)$item->quantityOnHold - $cartItem['quantity'];
                    $item->quantityAvailable = (int)$item->quantityAvailable + $cartItem['quantity'];
                    break;
                }
            }
        }

        // Saving the updated XML file
	    $xml->formatOutput = true;
        $xml->asXML($xmlFile);

        // Clearing the cart
        unset($_SESSION['cart']);

        echo "<p class ='error-message'>Your purchase has been cancelled.</p>";
    }
}

?>
