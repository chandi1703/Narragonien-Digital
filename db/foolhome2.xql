xquery version "3.0";
declare namespace tei = "http://www.tei-c.org/ns/1.0";

declare option exist:serialize "method=xhtml media-type=text/html";
declare variable $page-title := "Daß Narrenschyff ad Narragoniam";
declare variable $data := collection('/db/apps/testfool/data');

(: TODO: previous-next buttons :)
(: http://jsfiddle.net/WGkPV/1/ :)

<html>
    
    <head>
        
        <meta
            HTTP-EQUIV="Content-Type"
            content="text/html; charset=UTF-8"/>
        
        <title>{$page-title}</title>
        
        <link
            rel='stylesheet'
            href='css/style.css'/>
        <script
            src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"/>
        <script
            src="js/test2.js"/>
    
    </head>
    
    <body>
        <!-- Seite mit CSS etwas schmaler machen -->
        <div
            id="page-wrap">
            
            <h1>{$page-title}</h1>
            
            <!-- Select Felder, die sich einander bedingen. Siehe data2.json und test2.js -->
            
            <!-- Fassung auswählen -->
            
            <div
                id="left">
                
                <select
                    id="json-one">
                    <option
                        value="base">Fassung wählen</option>
                    <option
                        value="facsL">Faksimile</option>
                    <option
                        value="regL">Normalisierte Lesefassung</option>
                    <option
                        value="origL">OCR des Original</option>
                </select>
                
                
                <!-- Werk wählen. Wird nach Vorauswahl automatisch befüllt, kann aber auch 
                ohne Vorauswahl auswählen -->
                <select
                    id="json-two">
                    <option
                        value="base">GW wählen</option>
                    {
                        for $document in $data
                        let $uri := util:unescape-uri(replace(base-uri($document), '.+/(.+).xml$', '$1'), 'UTF-8')
                        return
                            (
                            <option
                                value="{$uri}facsL">{$uri}facsL</option>,
                            <option
                                value="{$uri}regL">{$uri}regL</option>,
                            <option
                                value="{$uri}origL">{$uri}origL</option>)
                    }
                </select>
                <!-- Wähle Kapitel in Abhängigkeit der beiden vorausgegangen Auswahlen -->
                <select
                    id="json-three">
                    <option
                        value="base">Kapitel wählen</option>
                    {
                        for $document in $data
                        for $chapter in $document//tei:div[@type = 'chapter']
                        let $id := string($chapter/@xml:id)
                        return
                            (
                            <option
                                value="{$id}facsL">{$id}facsL</option>,
                            <option
                                value="{$id}regL">{$id}regL</option>,
                            <option
                                value="{$id}origL">{$id}origL</option>)
                    }
                </select>
                <br/>
                
                <!-- previous - next buttons einbauen -->
                <button
                    name="prevl">previous</button>
                <button
                    name="nextl">next</button>
                
                <!-- alle Kapitel aller Werke in Lesefassung div stecken -->
                <div
                    id="regL">
                    {
                        for $document in $data
                        let $uri := util:unescape-uri(replace(base-uri($document), '.+/(.+).xml$', '$1'), 'UTF-8')
                        return
                            
                            (: in Werke aufsplitten :)
                            <div
                                id="{$uri}regL"
                                class="GWL">
                                {
                                    for $chapter in $document//tei:div[@type = 'chapter']
                                    let $id := string($chapter/@xml:id)
                                    return
                                        
                                        (: normalisierte Kapitel in GW einfügen, nicht anzeigen, erst nach Auswahl; siehe CSS :)
                                        <div
                                            id="{$id}regL"
                                            class="chapter"
                                            style="display:none">
                                            {
                                                for $line in $chapter//tei:reg//tei:l
                                                return
                                                    <p>{string($line/@n), string($line)}
                                                    </p>
                                            }
                                        </div>
                                }
                            </div>
                    }
                </div>
                
                <!-- gleiches div für OCR Texte -->
                <div
                    id="origL">
                    {
                        for $document in $data
                        let $uri := util:unescape-uri(replace(base-uri($document), '.+/(.+).xml$', '$1'), 'UTF-8')
                        return
                            <div
                                id="{$uri}origL"
                                class="GWL">
                                {
                                    for $chapter in $document//tei:div[@type = 'chapter']
                                    let $id := string($chapter/@xml:id)
                                    return
                                        
                                        <div
                                            id="{$id}origL"
                                            class="chapter"
                                            style="display:none">
                                            {
                                                for $line in $chapter//tei:orig//tei:l
                                                return
                                                    <p>{string($line/@n), string($line)}
                                                    </p>
                                            }
                                        </div>
                                }
                            </div>
                    }
                </div>
                
                <!-- hier kommen die Faksimile -->
                <div
                    id="facsL">
                    {
                        for $document in $data
                        let $uri := util:unescape-uri(replace(base-uri($document), '.+/(.+).xml$', '$1'), 'UTF-8')
                        return
                            <div
                                id="{$uri}facsL"
                                class="GWL">
                                {
                                    for $document in $data
                                    for $chapter in $document//tei:div[@type = 'chapter']
                                    let $id := string($chapter/@xml:id)
                                    return
                                        <div
                                            id="{$id}facsL"
                                            class="chapter"
                                            style="display:none">
                                            {
                                                let $facs := $chapter//tei:pb//@facs
                                                for $fac in $facs
                                                return
                                                    <div
                                                        class="facsimile">
                                                        <img
                                                            src="img/{$fac}"
                                                            width="100%"
                                                            height="auto"/>
                                                    </div>
                                            }
                                        
                                        </div>
                                }
                            </div>
                    }
                
                </div>
            </div>
            
            <div
                id="right">
                <select
                    id="json-four">
                    <option
                        value="base">Fassung wählen</option>
                    <option
                        value="facsR">Faksimile</option>
                    <option
                        value="regR">Normalisierte Lesefassung</option>
                    <option
                        value="origR">OCR des Original</option>
                </select>
                
                <!-- Werk wählen. Wird nach Vorauswahl automatisch befüllt, kann aber auch 
                ohne Vorauswahl auswählen -->
                <select
                    id="json-five">
                    <option
                        value="base">GW wählen</option>
                    {
                        for $document in $data
                        let $uri := util:unescape-uri(replace(base-uri($document), '.+/(.+).xml$', '$1'), 'UTF-8')
                        return
                            (<option
                                value="{$uri}regR">{$uri}regR</option>,
                            <option
                                value="{$uri}origR">{$uri}origR</option>,
                            <option
                                value="{$uri}facsR">{$uri}facsR</option>)
                    }
                </select>
                
                <!-- Wähle Kapitel in Abhängigkeit der beiden vorausgegangen Auswahlen -->
                <select
                    id="json-six">
                    <option
                        value="base">Kapitel wählen</option>
                    {
                        for $document in $data
                        for $chapter in $document//tei:div[@type = 'chapter']
                        let $id := string($chapter/@xml:id)
                        return
                            (<option
                                value="{$id}regR">{$id}regR</option>,
                            <option
                                value="{$id}origR">{$id}origR</option>,
                            <option
                                value="{$id}facsR">{$id}facsR</option>)
                    }
                </select>
                <br/>
                
                <!-- buttons für den rechten Kasten -->
                <button
                    name="prevr">previous</button>
                <button
                    name="nextr">next</button>
                
                <!-- alle Kapitel aller Werke in Lesefassung div stecken -->
                <div
                    id="regR">
                    {
                        for $document in $data
                        let $uri := util:unescape-uri(replace(base-uri($document), '.+/(.+).xml$', '$1'), 'UTF-8')
                        return
                            
                            (: in Werke aufsplitten :)
                            <div
                                id="{$uri}regR"
                                class="GWR">
                                {
                                    for $chapter in $document//tei:div[@type = 'chapter']
                                    let $id := string($chapter/@xml:id)
                                    return
                                        
                                        (: normalisierte Kapitel in GW einfügen, nicht anzeigen, erst nach Auswahl; siehe CSS :)
                                        <div
                                            id="{$id}regR"
                                            class="chapterR"
                                            style="display:none">
                                            {
                                                for $line in $chapter//tei:reg//tei:l
                                                return
                                                    <p>{string($line/@n), string($line)}
                                                    </p>
                                            }
                                        </div>
                                }
                            </div>
                    }
                </div>
                
                <!-- gleiches div für OCR Texte -->
                <div
                    id="origR">
                    {
                        for $document in $data
                        let $uri := util:unescape-uri(replace(base-uri($document), '.+/(.+).xml$', '$1'), 'UTF-8')
                        return
                            <div
                                id="{$uri}origR"
                                class="GWR">
                                {
                                    for $chapter in $document//tei:div[@type = 'chapter']
                                    let $id := string($chapter/@xml:id)
                                    return
                                        
                                        <div
                                            id="{$id}origR"
                                            class="chapterR"
                                            style="display:none">
                                            {
                                                for $line in $chapter//tei:orig//tei:l
                                                return
                                                    <p>{string($line/@n), string($line)}
                                                    </p>
                                            }
                                        </div>
                                }
                            </div>
                    }
                </div>
                
                <div
                    id="facsR">
                    {
                        for $document in $data
                        let $uri := util:unescape-uri(replace(base-uri($document), '.+/(.+).xml$', '$1'), 'UTF-8')
                        return
                            <div
                                id="{$uri}facsL"
                                class="GWR">
                                {
                                    for $document in $data
                                    for $chapter in $document//tei:div[@type = 'chapter']
                                    let $id := string($chapter/@xml:id)
                                    return
                                        <div
                                            id="{$id}facsR"
                                            class="chapterR"
                                            style="display:none">
                                            {
                                                let $facs := $chapter//tei:pb//@facs
                                                for $fac in $facs
                                                return
                                                    <div
                                                        class="facsimile">
                                                        <img
                                                            src="img/{$fac}"
                                                            width="100%"
                                                            height="auto"/>
                                                    </div>
                                            }
                                        </div>
                                }
                            </div>
                    }
                </div>
            </div>
        </div>
    </body>
</html>
