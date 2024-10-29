<?php
/*
Name: Arun Ragavendhar 
Student ID: 104837257
Purpose: This script uses XSLT to display available items in the catalog by transforming goods.xml.
*/
session_start();

if(!isset($_SESSION['firstName']) && !isset($_SESSION['CustomerId'])){
    header("Location:login.html");
}
// Path to XML and XSLT files
$xmlFile = '../../data/goods.xml';
$xslFile = 'catalog.xslt';

if(file_exists($xmlFile)){
    // Loading the XML document
    $xml = new DOMDocument;
    $xml->load($xmlFile);
    $xml->formatOutput = true;

    // Loading the XSLT file
    $xsl = new DOMDocument;
    $xsl->load($xslFile);

    // Initializing the XSLT processor and apply the transformation
    $proc = new XSLTProcessor;
    $proc->importStyleSheet($xsl);

    // Output the transformed XML as HTML
    echo $proc->transformToXML($xml);
}

else {
echo "<p class ='error-message'>No items in the Catalogue yet<p>";
}
?>

