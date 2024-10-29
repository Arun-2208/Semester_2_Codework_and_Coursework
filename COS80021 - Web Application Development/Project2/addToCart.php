<?php
session_start();

/*
    Name  : Arun Ragavendhar
    Student ID: 104837257
    Purpose: This script adds an item to the shopping cart and updates the goods.xml file.
*/

$xmlFile = '../../data/goods.xml';

if (isset($_POST['itemNumber']) && file_exists($xmlFile)) {
    $xml = simplexml_load_file($xmlFile);
    $itemNumber = $_POST['itemNumber'];
    $itemFound = false;

    // Searching for the item in the XML
    foreach ($xml->item as $item) {
        if ((int)$item->itemNumber == $itemNumber) {
            if ((int)$item->quantityAvailable > 0) {
                // Subtracting from available and add to on-hold
                $item->quantityAvailable = (int)$item->quantityAvailable - 1;
                $item->quantityOnHold = (int)$item->quantityOnHold + 1;

                // Adding item to the cart stored in session
                if (!isset($_SESSION['cart'][$itemNumber])) {
                    $_SESSION['cart'][$itemNumber] = [
                        'itemNo' => (int)$itemNumber,
                        'name' => (string)$item->name,
                        'quantity' => 1,
                        'price' => (float)$item->price
                    ];
                } else {
                    // Updating the quantity for the existing item in the cart
                    $_SESSION['cart'][$itemNumber]['quantity'] += 1;
                }

                // Saving the updated XML
		        $xml->formatOutput = true;
                $xml->asXML($xmlFile);
		
                $itemFound = true;
            } else {
                echo "<p class ='error-message'>Sorry, this item is not available for sale.</p>";
            }
            break;
        }
    }

    // Displaying the updated cart
    if ($itemFound) {
        foreach ($_SESSION['cart'] as $itemNumber => $cartItem) {
            echo "<p>" . $cartItem['name'] . " - Quantity: " . $cartItem['quantity'] . " - Price: $" . $cartItem['price'] . "</p>";
        }
    }
}
?>
