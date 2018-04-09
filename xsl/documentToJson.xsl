<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.tei-c.org/ns/1.0" xpath-default-namespace="http://www.tei-c.org/ns/1.0" version="2.0">

    <xsl:output indent="yes" method="text"/>

    <!--<xsl:template match="processing-instruction('xml-model')"/>-->

    <xsl:template match="/">
        <xsl:text>{</xsl:text>
        <xsl:text>"regL":"</xsl:text>
        <xsl:value-of select="//idno[@resp = 'GW']"/>
        <xsl:text>regL",</xsl:text>
    </xsl:template>

</xsl:stylesheet>