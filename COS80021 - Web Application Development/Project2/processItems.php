<?php
/*
    Name: Arun Ragavendhar 
    Student ID: 104837257
    Purpose: This script processes sold items by clearing the quantitySold for all sold items and removing any items that are fully sold using DOMDocument.
*/

session_start();
if(isset($_SESSION['managerName']) && isset($_SESSION['managerId'])) {
    // Path to goods.xml
    $xmlFile = '../../data/goods.xml';

    if (file_exists($xmlFile)) {
        // Creating a new DOMDocument instance
        $dom = new DOMDocument('1.0', 'UTF-8');
        $dom->preserveWhiteSpace = false;
        $dom->formatOutput = true;

        // Loading the XML file
        $dom->load($xmlFile);
        
        // Getting the root element
        $root = $dom->documentElement;
        
        // Getting all items from the XML
        $items = $root->getElementsByTagName('item');
        $itemsFound = false;
        $itemsToRemove = [];

        //looping through the items to check if any have non-zero quantitySold
        foreach ($items as $key => $item) {
            $quantitySold = $item->getElementsByTagName('quantitySold')->item(0)->nodeValue;

            if ((int)$quantitySold > 0) {
                $itemsFound = true;
            }
        }

        // Processing items if any are found
        if ($itemsFound) {
            // Loop through the items to process and mark items for removal
            foreach ($items as $key => $item) {
                $quantitySold = $item->getElementsByTagName('quantitySold')->item(0)->nodeValue;
                $quantityAvailable = $item->getElementsByTagName('quantityAvailable')->item(0)->nodeValue;
                $quantityOnHold = $item->getElementsByTagName('quantityOnHold')->item(0)->nodeValue;

                if ((int)$quantitySold > 0) {
                    // Clear the quantitySold
                    $item->getElementsByTagName('quantitySold')->item(0)->nodeValue = 0;

                    // If fully sold (quantityAvailable and quantityOnHold are 0), mark for removal
                    if ((int)$quantityAvailable == 0 && (int)$quantityOnHold == 0) {
                        $itemsToRemove[] = $item;
                    }
                }
            }

            // Removing the items marked for deletion
            foreach ($itemsToRemove as $item) {
                $root->removeChild($item);
            }

            // Save the updated XML
            $dom->save($xmlFile);

            // Success message
            echo "<p class='success-message'>Sold items processed successfully!</p>";
        } else {
            // If no items are found to be processed
            echo "<p class='error-message'>No items to be processed.</p>";
        }
    } else {
        echo "<p class='error-message'>Goods.xml file not found.</p>";
    }
} else {
    echo "<p class='error-message'>You must be logged in as a manager to process items.</p>";
}
?>
