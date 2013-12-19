<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:template match="/">
  <html>
   <body>
    <h2>My Library</h2>
    <table>
     <tr>
      <th>Title</th>
      <th>Author</th>
     </tr>
     <xsl:for-each select="testCaseList/ListMessage/entry">
      <tr>
       <td>
        <xsl:value-of select="key" />
       </td>
      </tr>
        <xsl:for-each select="value/testMessage">
            <tr>
             <td>
              <xsl:value-of select="Description" />
             </td>
             <td>
              <xsl:value-of select="Status" />
             </td>
            </tr>
        </xsl:for-each>
     </xsl:for-each>
    </table>
   </body>
  </html>
 </xsl:template>
</xsl:stylesheet>