<?php
/*
Name: Arun Ragavendhar
Student ID: 104837257
Purpose: This script handles the addition of new items by store managers. It validates the input and stores the new item details in the goods.xml file.
*/

if (isset($_POST['itemName']) && isset($_POST['price']) && isset($_POST['quantity']) && isset($_POST['description'])) {

    // Input field validations
    $itemName = $_POST['itemName'];
    $price = $_POST['price'];
    $quantity = $_POST['quantity'];
    $description = $_POST['description'];

    // Validating item name (ensure it's not empty and has at least 2 characters)
    if (empty($itemName) || strlen($itemName) < 3 || !preg_match("/^[A-Za-z0-9 .]+$/",$itemName)) {
        echo "<p class = 'error-message'>Item name must be at least 3 characters long and should only contain numbers or alphabets or spaces.</p>";
        exit();
    }

    // Validating price (ensure it's a positive number)
    if (!is_numeric($price) || $price <= 0) {
        echo "<p class = 'error-message'>Price must be a positive number.</p>";
        exit();
    }

    // Validating quantity (ensure it's a non-negative integer)
    if (!is_numeric($quantity) || $quantity <= 0 || intval($quantity) != $quantity) {
        echo "<p class = 'error-message'>Quantity must be a non-negative integer and must be greater than 0.</p>";
        exit();
    }

    // Validating description (ensure it's not empty and has at least 5 characters)
    if (empty($description) || strlen($description) < 5) {
        echo "<p class = 'error-message'>Description must be at least 5 characters long.</p>";
        exit();
    }

    // Path to the goods.xml file
    $xmlFile = '../../data/goods.xml';
    $xslFile = 'manager_catalog.xslt';

    // Loading the XML document, or create a new one if it doesn't exist
    if (file_exists($xmlFile)) {
        $xml = new DOMDocument();
        $xml->load($xmlFile);
    } else {
        $xml = new DOMDocument('1.0', 'UTF-8');
        $root = $xml->createElement('goods');
        $xml->appendChild($root);
    }

    $xml->preserveWhiteSpace = false;
    $xml->formatOutput = true;

    // Generating a new item number
    $items = $xml->getElementsByTagName('item');
    $nextItemNumber = (int)(crc32(uniqid()));

    // Creating a new item element
    $newItem = $xml->createElement('item');

    // Creating and appending the itemNumber element
    $itemNumberElement = $xml->createElement('itemNumber', $nextItemNumber);
    $newItem->appendChild($itemNumberElement);

    // Creating and appending the name element
    $nameElement = $xml->createElement('name', $itemName);
    $newItem->appendChild($nameElement);

    // Creating and appending the price element
    $priceElement = $xml->createElement('price', $price);
    $newItem->appendChild($priceElement);

    // Creating and appending the quantityAvailable element
    $quantityAvailableElement = $xml->createElement('quantityAvailable', $quantity);
    $newItem->appendChild($quantityAvailableElement);

    // Create and appending the description element
    $descriptionElement = $xml->createElement('description', $description);
    $newItem->appendChild($descriptionElement);

    // Create and appending the quantityOnHold element (default 0)
    $quantityOnHoldElement = $xml->createElement('quantityOnHold', 0);
    $newItem->appendChild($quantityOnHoldElement);

    // Create and appending the quantitySold element (default 0)
    $quantitySoldElement = $xml->createElement('quantitySold', 0);
    $newItem->appendChild($quantitySoldElement);

    // Appending the new item to the root element
    $xml->documentElement->appendChild($newItem);

    // Saving the updated XML document
    $xml->save($xmlFile);

    // XSLT to display Item catalog details
    if (file_exists($xmlFile)) {
        $xmlDoc = new DOMDocument;
        $xmlDoc->load($xmlFile);

        $xsl = new DOMDocument;
        $xsl->load($xslFile);

        $proc = new XSLTProcessor;
        $proc->importStylesheet($xsl);

        echo "<p class = 'success-message'>The item has been listed in the system, and the item number is: <strong>" . $nextItemNumber . "</strong></p>";
      
        echo $proc->transformToXML($xmlDoc);
    } 
    else {
        echo "<p  class = 'error-message'>No items in the list as of now.</p>";
    }
} 
else {
    echo "<p  class = 'error-message'>All fields are required.</p>";
}
?>
