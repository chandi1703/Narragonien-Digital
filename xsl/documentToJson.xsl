<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.tei-c.org/ns/1.0"
    xpath-default-namespace="http://www.tei-c.org/ns/1.0" version="2.0"
    xmlns:fn="http://oc.narragonien-digital.de">

    <!-- reads collection of xml-files and generates json ouput for selection menu -->

    <xsl:output indent="yes" method="text"/>

    <!-- DECLARE NECESSARY VARIABLES -->
    <!-- put all xml files into variable "documents" -->
    <xsl:variable name="documents" select="collection('./xml?select=*.xml')"/>

    <!-- distinguish between normalized and ocr-version, facsimile -->
    <xsl:variable name="regL">
        <xsl:text>regL</xsl:text>
    </xsl:variable>

    <xsl:variable name="origL">
        <xsl:text>origL</xsl:text>
    </xsl:variable>

    <xsl:variable name="facsL">
        <xsl:text>facsL</xsl:text>
    </xsl:variable>

    <!-- declare function for listing gw-numbers -->
    <xsl:function name="fn:getGW">
        <xsl:param name="variable"/>

        <xsl:for-each select="$documents">
            <xsl:if test="position() = 1">
                <xsl:text>"</xsl:text>
            </xsl:if>
            <xsl:value-of select="concat('GW',//idno[@resp = 'GW'], $variable)"/>
            <xsl:choose>
                <xsl:when test="position() != last()">
                    <xsl:text>,</xsl:text>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>",</xsl:text>
                    <xsl:text>&#xa;</xsl:text>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:for-each>
    </xsl:function>

    <!-- declare function for listing all chapters from documents -->
    <xsl:function name="fn:getChapter">
        <xsl:param name="variable"/>

        <xsl:for-each select="$documents">
            <xsl:text>"</xsl:text>
            <xsl:variable name="gw">
                <xsl:value-of select="concat('GW',//idno[@resp = 'GW'])"/>
            </xsl:variable>
            <xsl:value-of select="$gw"/>
            <xsl:value-of select="$variable"/>
            <xsl:text>":"</xsl:text>
            <xsl:for-each select="node()//div[@type = 'chapter']/@xml:id">
                <xsl:value-of select="concat($gw, ., $variable)"/>
                <xsl:choose>
                    <xsl:when test="position() != last()">
                        <xsl:text>,</xsl:text>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:text>",</xsl:text>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:for-each>
            <xsl:text>&#xa;</xsl:text>
        </xsl:for-each>
    </xsl:function>

    <!-- match documents' roots and call functions with declared variables -->
    <!-- result document output.json -->
    <xsl:template match="/">

        <xsl:result-document href="json/output.json">
            <xsl:text>{</xsl:text>

            <xsl:text>"regL":</xsl:text>
            <xsl:value-of select="fn:getGW($regL)"/>
            <xsl:text>"origL":</xsl:text>
            <xsl:value-of select="fn:getGW($origL)"/>
            <xsl:text>"facsL":</xsl:text>
            <xsl:value-of select="fn:getGW($facsL)"/>

            <xsl:value-of select="fn:getChapter($regL)"/>
            <xsl:value-of select="fn:getChapter($origL)"/>
            <xsl:value-of select="fn:getChapter($facsL)"/>

            <xsl:text>}</xsl:text>
        </xsl:result-document>

    </xsl:template>

</xsl:stylesheet>
