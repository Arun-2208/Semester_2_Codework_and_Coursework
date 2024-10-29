<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <xsl:template match="/">
        <html>
        <head>
            <title>Available Items</title>  
              <style>
                            table {
                    width: 90%;
                    margin: 20px auto;
                    border-collapse: collapse;
                    background-color: #ffffff;
                    border-radius: 8px;
                    overflow: hidden;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                }

                th, td {
                    padding: 15px;
                    border-bottom: 1px solid #ddd;
                    text-align: center;
                    font-size: 14px;
                }

                th {
                    background-color: #ffd500;
                    color: white;
                    text-transform: uppercase;
                    letter-spacing: 1px;
                }

                tr:nth-child(even) {
                    background-color: #f8f9fa;
                }

                tr:hover {
                    background-color: #f1f3f5;
                }
            </style>
        </head>
        <body>
    
            <table border="1">
                <tr>
                    <th>Item Number</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Quantity Available</th>
                    <th>Action</th>
                </tr>
                <xsl:for-each select="goods/item">
                    <xsl:if test="quantityAvailable > 0">
                        <tr>
                            <td><xsl:value-of select="itemNumber"/></td>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="price"/></td>
                            <td><xsl:value-of select="substring(description, 1, 20)"/>...</td>
                            <td><xsl:value-of select="quantityAvailable"/></td>
                            <td>
                              <button onclick="addToCart('{itemNumber}')">Add to Cart</button>
                            </td>
                        </tr>
                    </xsl:if>
                </xsl:for-each>
            </table>
        </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
