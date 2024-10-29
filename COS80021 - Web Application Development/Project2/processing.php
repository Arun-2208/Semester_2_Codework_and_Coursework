<?php
/*
    Name: Arun Ragavendhar
    Student ID: 104837257
    Purpose: This script displays items with non-zero sold quantities from goods.xml for processing.
*/

session_start();
if (isset($_SESSION['managerName']) && isset($_SESSION['managerId'])) {
    // Path to goods.xml
    $xmlFile = '../../data/goods.xml';

    // Load the XML file
    if (file_exists($xmlFile)) {
        $xml = simplexml_load_file($xmlFile);

        // Flag to track if any items with non-zero sold quantities are found
        $itemsFound = false;

        // Checking if there are items with non-zero sold quantities
        foreach ($xml->item as $item) {
            if ((int)$item->quantitySold > 0) {
                $itemsFound = true;
                break; 
            }
        }

        // If items with non-zero sold quantities are found, display them in a table
        if ($itemsFound) {
            echo "<p class = 'success-message'> Items waiting to be processed for Orders placed by customers</p>";
            echo "<table border='1'>";
            echo "<tr><th>Item Number</th><th>Name</th><th>Price</th><th>Quantity Available</th><th>Quantity On Hold</th><th>Quantity Sold</th></tr>";

            // Looping through items again to display them
            foreach ($xml->item as $item) {
               
                    echo "<tr>";
                    echo "<td>" . $item->itemNumber . "</td>";
                    echo "<td>" . $item->name . "</td>";
                    echo "<td>" . $item->price . "</td>";
                    echo "<td>" . $item->quantityAvailable . "</td>";
                    echo "<td>" . $item->quantityOnHold . "</td>";
                    echo "<td>" . $item->quantitySold . "</td>";
                    echo "</tr>";
            }
            echo "</table>";
        } 
        else{
            echo "<p class = 'error-message'> No item has been purchased yet by customer. Nothing has been sold yet , so nothing to process as of now </p>";
            echo "<table border='1'>";
            echo "<tr><th>Item Number</th><th>Name</th><th>Price</th><th>Quantity Available</th><th>Quantity On Hold</th><th>Quantity Sold</th></tr>";

            // Looping through items again to display them
            foreach ($xml->item as $item) {
               
                    echo "<tr>";
                    echo "<td>" . $item->itemNumber . "</td>";
                    echo "<td>" . $item->name . "</td>";
                    echo "<td>" . $item->price . "</td>";
                    echo "<td>" . $item->quantityAvailable . "</td>";
                    echo "<td>" . $item->quantityOnHold . "</td>";
                    echo "<td>" . $item->quantitySold . "</td>";
                    echo "</tr>";
            }
            echo "</table>";
        }
    } 
    else {
        echo "<p>Goods file not found.</p>";
    }
} 
else {
    header("Location: mlogin.html");
    exit();
}
?>
