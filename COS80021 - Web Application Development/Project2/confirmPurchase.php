<?php
session_start();

/*
    Name: Arun Ragavendhar
    Student ID: 104837257
    Purpose: This script confirms the purchase, displays detailed pricing for each item, calculates the total amount, updates goods.xml, and clears the session cart.
*/

$xmlFile = '../../data/goods.xml';
$totalAmountDue = 0; // This will hold the grand total to be paid by the customer

if(isset($_SESSION['cart']) && !empty($_SESSION['cart'])){

    if (file_exists($xmlFile)) {
        $xml = simplexml_load_file($xmlFile);

        echo "<h3>Purchase Confirmation</h3>";
        echo "<table border='1'>
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Unit Price</th>
                        <th>Quantity</th>
                        <th>Total Price</th>
                    </tr>
                </thead>
                <tbody>";

        // Looping through the session cart and update XML as well
        foreach ($_SESSION['cart'] as $itemNumber => $cartItem) {
            foreach ($xml->item as $item) {
                if ((int)$item->itemNumber == $itemNumber) {
                    $item->quantityOnHold = (int)$item->quantityOnHold - $cartItem['quantity'];
                    $item->quantitySold = (int)$item->quantitySold + $cartItem['quantity'];

                    // Calculating total for this specific item
                    $itemTotal = $cartItem['quantity'] * $cartItem['price'];
                    $totalAmountDue += $itemTotal;

                    // Displaying itemized pricing
                    echo "<tr>
                            <td>{$cartItem['name']}</td>
                            <td>\${$cartItem['price']}</td>
                            <td>{$cartItem['quantity']}</td>
                            <td>\$" . number_format($itemTotal, 2) . "</td>
                        </tr>";
                    break;
                }
            }
        }

        echo "</tbody></table>";
        
        // Displaying the grand total
        echo "<p class = 'success-message'><strong>Total Amount Due: \$" . number_format($totalAmountDue, 2) . "</strong></p>";

        // Saving the updated XML file
	    $xml->formatOutput = true;
        $xml->asXML($xmlFile);

        // Clearing the session cart after purchase
        unset($_SESSION['cart']);

        echo "<p class = 'success-message'>Your purchase has been confirmed!</p>";
    }
}
else{
    echo "<p class ='error-message'>Please add an item to Cart first !</p>";
}
?>
