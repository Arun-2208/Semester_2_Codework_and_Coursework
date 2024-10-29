

   URL OF THE APPLICATION :   https://mercury.swin.edu.au/cos80021/s104837257/Project2/buyonline.html

Manager Login Credentials : ID  :  AR99    ,    password : ARR121280



Program Files :

buyonline.html    - This page acts as the site map for BuyOnline. It provides links for customer registration, customer login, and store manager login.
    		   It serves as the entry point for all users to navigate the system. After registration or login, customers/managers are redirected to their respective pages.

register.html     - This page allows new customers to register into the system by providing their personal details. 
             	   It sends data to register.php for processing via AJAX.

register.php      - This PHP script processes , validates customer registration and stores data in customer.xml.

login.html        - This page allows customers to log into the BuyOnline system via AJAX, and the credentials are validated against customer.xml.

login.php         - This PHP script processes customer login and validates credentials against customer.xml.

buying.html       - This page displays available items and a shopping cart. AJAX techniques are used to periodically fetch data from the server.

buying.html       - This script uses XSLT to display available items in the catalog by transforming goods.xml.

catalog.xslt      - Contains the XSLT template for the buying.php file to use it for extracting and displaying the Item Catalog from the goods.xml file

addToCart.php     - This script adds an item to the shopping cart and updates the goods.xml file.

removeFromCart.php - This script removes all quantities of an item from the cart, updates the session, and updates the goods.xml file.

cart.php          - This script displays the current contents of the shopping cart in a table format.

confirmPurchase.php - This script confirms the purchase, displays detailed pricing for each item, calculates the total amount, updates goods.xml, and clears the session cart.

cancelPurchase.php  - This script cancels the purchase and restores item quantities in goods.xml.

logout.php          - This page displays a logout message after the customer has logged out. It clears and destroys the session variable and the session.

mlogin.html         - This page allows store managers to log into the system by providing their manager ID and password. The credentials are checked against manager.txt via 
		      AJAX.

mlogin.php          - This script handles manager login and displays customer data using XSLT.

listing.html        - This page allows store managers to add new items to the system with details such as item name, price, quantity, and description. AJAX is used to submit 			      the form data.

listing.php         - This script handles the addition of new items by store managers. It validates the input and stores the new item details in the goods.xml file.

processing.html     - This page allows the manager to process sold items. Items with non-zero sold quantities are displayed, and the manager can clear sold items.

processing.php      - This script displays items with non-zero sold quantities from goods.xml for processing.

processItems.php    - This script processes sold items by clearing the quantitySold for all sold items and removing any items that are fully sold.

mlogout.php         - This page displays a logout message after the manager has logged out. It clears and destroys the session variable and the session.

style.css           - This CSS file provides styling for all pages of the Arun's Shop Online web application.



Data Files :

customer.xml   - This xml files is used to store Customer details . It is used to refer and verify a customer's details when they try to login

goods.xml      - This xml file has the item catalogue details of all the items available to be shopped. It has details about the price , quantity available and quantity sold of 		 an item.

manager.txt      - This xml files is used to store Manager details . It is used to refer and verify a Manager's details when they try to login



Instructions to Arun's BuyOnline Web Application

    Access the Site Map:
        Open the buyonline.html page in your browser. This is the main entry point for both customers and store managers. You will see options to:
            Register as a new customer
            Log in as an existing customer
            Log in as a store manager

    Customer Registration:
        If you are a new customer, click on the Customer Registration link.
        You will be redirected to the register.html page where you need to enter your details (email, first name, last name, password, and phone number).
        After submitting the form, your data will be validated and processed by register.php, and saved in the customer.xml file.
        If registration is successful, you will be redirected back to the home page.

    Customer Login:
        If you are an already registered customer, click on the Customer Login link.
        Enter your email and password on the login.html page.
        The credentials will be validated via login.php against the customer.xml file.
        Upon successful login, you will be redirected to the buying.html page, where you can start shopping.

    Browsing the Item Catalog:
        On the buying.html page, you will see the item catalog which is dynamically fetched using AJAX and displayed using catalog.xslt.
        The catalog shows available items with their item number, name, price, and quantity available. You cannot view the quantity on hold or sold.

    Adding Items to Cart:
        To add an item to your cart, click on the Add to Cart button next to the item in the catalog.
        The addToCart.php script will update the cart session and modify the goods.xml file to reflect the item being added to the cart.

    Viewing Cart:
        You can view the current items in your cart, which are displayed in table format through cart.php. The cart shows the item name, quantity, and price for each item.

    Removing Items from Cart:
        You can remove items from the cart by clicking the Remove button for the specific item in the cart.
        This will send a request to removeFromCart.php, which will update the cart session and restore the quantity in goods.xml.

    Confirming Purchase:
        Once you have finished shopping, click the Confirm Purchase button.
        The confirmPurchase.php script will calculate the total amount due, update the goods.xml file to reflect the sold items, and clear the session cart.
        A message will display the total amount due and confirm that the purchase was successful.

    Cancelling Purchase:
        If you wish to cancel your purchase, click the Cancel Purchase button.
        The cancelPurchase.php script will restore the item quantities in goods.xml and clear the session cart.

    Logging Out (Customer):
        To log out as a customer, click on the Logout link.
        The logout.php script will clear the session and redirect you to the home page with a logout confirmation message.

    Manager Login:
        If you are a store manager, click on the Store Manager Login link.
        Enter your manager ID and password on the mlogin.html page.
        The credentials are checked against manager.txt via AJAX and validated by mlogin.php.

    Adding New Items to Catalog:
        After logging in as a manager, go to the Add Items page (listing.html).
        Enter the item name, price, quantity, and description, then submit the form.
        The form is processed by listing.php, which adds the new item to the goods.xml file and updates the catalog.

    Processing Sold Items:
        To process sold items, go to the processing.html page.
        The processing.php script will display items with non-zero sold quantities from goods.xml.
        You can process sold items by clicking the Process Items button, which will clear the quantitySold and remove fully sold-out items from the catalog.

    Logging Out (Manager):
        To log out as a manager, click on the Logout link.
        The mlogout.php script will clear the session and log you out of the manager account.


