<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.tei-c.org/ns/1.0"
    xpath-default-namespace="http://www.tei-c.org/ns/1.0" version="2.0">

    <xsl:output method="xml" version="1.0" indent="yes"/>

    <!-- Copy whole TEI-Content, as we need it -->
    <xsl:template match="@* | node()">
        <xsl:copy>
            <xsl:apply-templates select="@* | node()"/>
        </xsl:copy>
    </xsl:template>

    <!-- remove whitespace from original document because it creates unwanted empty lines -->
    <xsl:strip-space elements="*"/>

    <!-- replace p with linegroup -->
    <xsl:template match="text//p">

        <xsl:choose>

            <!--don't create lingroup when there is nothing inside of p-->
            <xsl:when test="not(node())">
                <xsl:apply-templates select="node()"/>
            </xsl:when>

            <!--don't create linegroup when there are only figures in p-->
            <xsl:when test="./not(text())">
                <xsl:apply-templates select="node()"/>
            </xsl:when>

            <xsl:otherwise>
                <lg>

                    <!-- make a group out of everything inside of p, ending with a linebreak -->
                    <xsl:for-each-group select="node()" group-ending-with="lb">

                                <!-- wrap line around current-group -->
                                <l>
                                    <xsl:apply-templates select="current-group()"/>
                                </l>

                    </xsl:for-each-group>
                </lg>

            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <!-- get rid if linebreak, as we don't need it anymore -->
    <xsl:template match="p//lb"/>

    <!-- make text inside of p-elements look more beautiful in the output -->
    <xsl:template match="p//text()">
        <xsl:value-of select="normalize-space()"/>
    </xsl:template>

    <!-- replace normalized and OCR with reg and orig -->
    <xsl:template match="div[@type = 'normalized'] | div[@type = 'OCR']">
        <xsl:choose>
            <xsl:when test="@type = 'normalized'">
                <reg>
                    <xsl:apply-templates select="node()"/>
                </reg>
            </xsl:when>

            <xsl:when test="@type = 'OCR'">
                <orig>
                    <xsl:apply-templates select="node()"/>
                </orig>
            </xsl:when>
        </xsl:choose>
    </xsl:template>

    <!-- replace div's with ab and choice within -->
    <xsl:template
        match="
            div[@type = 'mainText'] | div[@type = 'woodcut'] |
            div[@type = 'chapterTitle'] | div[@type = 'motto'] |
            div[@type = 'signatureMark'] | div[@type = 'none']">

        <ab>
            <xsl:apply-templates select="@* | node()"/>
        </ab>
    </xsl:template>

</xsl:stylesheet>
