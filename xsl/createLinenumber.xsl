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
    
    <xsl:template match="lg/*[not(node())]"/>

    <!-- Match line-Element -->
    <xsl:template match="l">

        <xsl:copy>
            <!-- only create attribute for mainText lines which are normalized -->
            <xsl:if test="ancestor::reg[ancestor::ab[@type='mainText']]">

                <xsl:attribute name="n">

                    <!-- count all lines within mainText boxes on any level, starting number from ancestor chapter div -->
                    <xsl:number count="ab[@type = 'mainText']//reg//l" level="any" from="div"/>
                </xsl:attribute>
            </xsl:if>
            <xsl:apply-templates select="@* | node()"/>
        </xsl:copy>

    </xsl:template>

</xsl:stylesheet>
