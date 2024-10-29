<?php
/*
    Name: Arun Ragavendhar
    Student ID: 104837257
    Purpose: This script displays the current contents of the shopping cart in a table format.
*/

session_start();
if (!isset($_SESSION['cart']) || empty($_SESSION['cart'])) {
    echo "<p class ='error-message'>Your cart is empty.</p>";
} else {
    echo "<table border='1'>
            <thead>
                <tr>
                    <th>Item No</th>
                    <th>Item Name</th>
                    <th>Quantity</th>
                    <th>Unit Price</th>
                    <th>Total Price</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>";
    
    // Looping through the cart and display each item in the table
    foreach ($_SESSION['cart'] as $item) {
        $totalPrice = $item['quantity'] * $item['price']; // Calculate total price for this item
	
        echo "<tr>
                <td>{$item['itemNo']}</td>
                <td>{$item['name']}</td>
                <td>{$item['quantity']}</td>
                <td>\${$item['price']}</td>
                <td>\$" . number_format($totalPrice, 2) . "</td>
                <td><button type='button' onClick='removeFromCart(\"{$item['itemNo']}\")'>Remove item</button></td>
              </tr>";
    }
    
    echo "</tbody></table>";
}
?>
