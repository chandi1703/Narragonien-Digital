<xsl:stylesheet exclude-result-prefixes="xs" xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:tei="http://www.tei-c.org/ns/1.0"
    xpath-default-namespace="http://www.tei-c.org/ns/1.0" version="2.0"
    xmlns="http://graphml.graphdrawing.org/xmlns">

    <xsl:output indent="yes" method="text"/>

    <xsl:param name="path2collection">../../Daten/Output/</xsl:param>
    <xsl:variable name="path">
        <xsl:value-of
            select="concat($path2collection, '?select=*.xml;recurse=yes;on-error=warning')"/>
    </xsl:variable>
    <xsl:variable name="docs" select="collection($path)"/>

    <xsl:template match="/">
        <xsl:text>{</xsl:text>
        <xsl:text>"regL":"</xsl:text>
        <xsl:for-each select="$docs">
            <xsl:value-of select="//idno[@resp = 'GW']"/>
            <xsl:choose>
                <xsl:when test="position() != last()">
                    <xsl:text>regL,</xsl:text>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>regL</xsl:text>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:for-each>
        <xsl:text>",</xsl:text>
        <xsl:text>&#xa;</xsl:text>
        
        <xsl:for-each select="$docs">
            <xsl:text>"</xsl:text>
            <xsl:value-of select="//idno[@resp = 'GW']"/>
            <xsl:text>regL":"</xsl:text>
            <xsl:value-of select="node()//div[@type='chapter']/@xml:id"/>
            <xsl:text>regL</xsl:text>
            <xsl:text>&#xa;</xsl:text>
        </xsl:for-each>

        <!--<xsl:result-document href="jsonOutput.json">
            <xsl:text>{</xsl:text>
                <xsl:text>"regL":"</xsl:text>
                <xsl:value-of select="//idno[@resp = 'GW']"/>
                <xsl:text>regL",</xsl:text>
                <xsl:text>&#xa;</xsl:text>
        </xsl:result-document>-->
    </xsl:template>

    <xsl:template match="*"/>
</xsl:stylesheet>
