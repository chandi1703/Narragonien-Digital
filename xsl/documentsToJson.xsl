<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet exclude-result-prefixes="xs" version="2.0" xmlns:tei="http://www.tei-c.org/ns/1.0"
    xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns="http://graphml.graphdrawing.org/xmlns">

    <xsl:output indent="yes" method="text"/>

    <xsl:template match="processing-instruction('xml-model')"/>

    <xsl:template match="root">
        <xsl:result-document href="documents_json_sentiment.json" indent="no">
            <xsl:text>[</xsl:text>
            <xsl:for-each select="collection('../documents_xml/?select=*.xml')">
                <xsl:variable name="FILENAME" select="tokenize(document-uri(.), '/')[last()]"/>
                <xsl:if
                    test="not(starts-with($FILENAME, '_')) and document/header/type = 'Kurzbericht'">
                    <xsl:if test="position() != 1">
                        <xsl:text>,&#xa;</xsl:text>
                    </xsl:if>
                    <xsl:text>{</xsl:text>
                    <xsl:text>"reviewerID": "author_</xsl:text>
                    <xsl:value-of select="$FILENAME"/>
                    <xsl:text>",</xsl:text>
                    <xsl:text>&#xa;</xsl:text>
                    <xsl:text>"asin": "</xsl:text>
                    <xsl:value-of select="position()"/>
                    <xsl:text>",</xsl:text>
                    <xsl:text>&#xa;</xsl:text>
                    <xsl:text>"reviewerName": "author_</xsl:text>
                    <xsl:value-of select="$FILENAME"/>
                    <xsl:text>",</xsl:text>
                    <xsl:text>&#xa;</xsl:text>
                    <xsl:text>"helpful": [0, 0],</xsl:text>
                    <xsl:text>&#xa;</xsl:text>
                    <xsl:text>"reviewText": "</xsl:text>
                    <xsl:value-of select="document/body/normalize-space(.)"/>
                    <xsl:text>",</xsl:text>
                    <xsl:text>&#xa;</xsl:text>
                    <xsl:text>"overall": 5.0,</xsl:text>
                    <xsl:text>&#xa;</xsl:text>
                    <xsl:text>"summary": "no_summary",</xsl:text>
                    <xsl:text>&#xa;</xsl:text>
                    <xsl:text>"unixReviewTime": 1355616000,</xsl:text>
                    <xsl:text>&#xa;</xsl:text>
                    <xsl:text>"reviewTime": "</xsl:text>
                    <xsl:value-of select="document/header/history/publication/date/@when"/>
                    <xsl:text>"</xsl:text>
                    <xsl:text>}</xsl:text>
                </xsl:if>
            </xsl:for-each>
            <xsl:text>]</xsl:text>
        </xsl:result-document>
    </xsl:template>

    <xsl:template match="*"/>

</xsl:stylesheet>
