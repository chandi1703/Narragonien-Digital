<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.tei-c.org/ns/1.0"
    xpath-default-namespace="http://www.tei-c.org/ns/1.0" version="2.0">
    
    <!-- creates lines in ocr elements out of linebreaks -->
    
    <xsl:output method="xml" version="1.0" indent="yes"/>
    <xsl:template match="@* | node()">
        <xsl:copy>
            <xsl:apply-templates select="@* | node()"/>
        </xsl:copy>
    </xsl:template>
    
    <xsl:template match="orig">
        <xsl:copy>
            
            <xsl:choose>
                <xsl:when test="not(node())">
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
           
        </xsl:copy>
    </xsl:template>
    
</xsl:stylesheet>