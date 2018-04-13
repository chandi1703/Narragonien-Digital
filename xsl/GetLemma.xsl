<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.tei-c.org/ns/1.0"
    xpath-default-namespace="http://www.tei-c.org/ns/1.0" version="2.0">
    
    <xsl:output method="xml" indent="yes"/>
    
    <xsl:template match="@* | node()">
        <xsl:copy>
            <xsl:apply-templates select="@* | node()"/>
        </xsl:copy>
    </xsl:template>
    
    <xsl:template match="text ">
        <xsl:for-each select="//div[@type = 'chapter']">
            <xsl:text>&#xa;</xsl:text>
            <xsl:text>Kapitel </xsl:text>
            <xsl:value-of select="./@xml:id"/>
            <xsl:text>&#xa;</xsl:text>
            
            <xsl:for-each select=".//ref ">
                <xsl:copy-of copy-namespaces="no" select="."/>
                <xsl:text>&#xa;</xsl:text>
            </xsl:for-each>
        </xsl:for-each>
    </xsl:template>
    
</xsl:stylesheet>