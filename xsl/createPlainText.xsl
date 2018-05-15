<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.tei-c.org/ns/1.0"
    xpath-default-namespace="http://www.tei-c.org/ns/1.0" version="2.0">

    <!-- Get Plain Text from TEI-Text -->

    <xsl:output omit-xml-declaration="yes" indent="no"/>
   <!-- <xsl:output method="text"/>-->
    <xsl:strip-space elements="*"/>

    <xsl:template match="/">
        <xsl:for-each select="//reg//l">
            <xsl:apply-templates/>
            <xsl:text>&#xa;</xsl:text>
        </xsl:for-each>
    </xsl:template>
       
    <xsl:template match="pb">
        <xsl:text>&#xa;</xsl:text>
        <xsl:text>PAGEBREAK</xsl:text>
        <xsl:text>&#xa;</xsl:text>
    </xsl:template>

    <!--<xsl:template match="l">
        <xsl:apply-templates select="node()"/>
        <xsl:text>&#xa;</xsl:text>
    </xsl:template>-->

</xsl:stylesheet>
