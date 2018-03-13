<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.tei-c.org/ns/1.0"
    xpath-default-namespace="http://www.tei-c.org/ns/1.0" version="2.0">

    <xsl:output omit-xml-declaration="yes" indent="yes"/>
    <xsl:output method="text"/>
    <xsl:strip-space elements="*"/>

    <xsl:template match="*">
        <xsl:apply-templates select="node()"/>
        <xsl:text> </xsl:text>
    </xsl:template>
    
    <xsl:template match="orig"/>
    
    <xsl:template match="pb">
        <xsl:text>&#xa;</xsl:text>
        <xsl:text>PAGEBREAK </xsl:text>
        <xsl:text>&#xa;</xsl:text>
    </xsl:template>
    
    <!-- Laalala -->

    <xsl:template match="lb">
        <xsl:text>&#xa;</xsl:text>
    </xsl:template>

</xsl:stylesheet>
