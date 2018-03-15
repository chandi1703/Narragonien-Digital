xquery version "3.0";
declare namespace tei = "http://www.tei-c.org/ns/1.0";

declare option exist:serialize "method=xhtml media-type=text/html";
declare variable $page-title := "Our first Narragonia Output";
declare variable $data := collection('/db/apps/testfool/data');


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
            src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script
            src="js/test1.js"/> 
    </head>
    
    <body>
        <div
            id="page-wrap">
            <h1>{$page-title}</h1>
            
            <!-- SPIELEN 
            
             SPIELEN -->
            
            <p>
                
                <!-- Select Felder, die sich einander bedingen. Siehe data.json und test2.js -->
                <!-- Wählt Werk aus Collection -->
                
                <select
                    id="json-one">
                    <option
                        value="base">GW wählen</option>
                    
                    {
                        for $document in $data
                        let $uri := util:unescape-uri(replace(base-uri($document), '.+/(.+).xml$', '$1'), 'UTF-8')
                        return
                            <option
                                value="{$uri}">{$uri}</option>
                    }
                
                </select>
                
                <select
                    id="json-two">
                    <option value="base">Kapitel wählen</option>
                    {
                        for $document in $data
                        for $chapter in $document//tei:div[@type = 'chapter']
                        let $id := string($chapter/@xml:id)
                        return
                            <option
                                value="{$id}">{$id}</option>
                    } 
                </select>
                
                <select
                    id="json-three">
                    <option value="base">Fassung wählen</option>
                    {
                        for $document in $data
                        for $chapter in $document//tei:div[@type = 'chapter']
                        let $id := string($chapter/@xml:id)
                        return
                            (<option
                                value="{$id}reg">{$id}reg</option>,
                            <option
                                value="{$id}orig">{$id}orig</option>,
                            <option
                                value="{$id}facs">{$id}facs</option>)
                    } 
                </select> 
            
            </p>

            <div
                class="chapters">
                {
                    for $document in $data
                    for $chapter in $document//tei:div[@type = 'chapter']
                    let $id := string($chapter/@xml:id)
                    return
                        
                        <div
                            id="{$id}"
                            class="chapter">
                            
                            <div
                                id="{$id}orig"
                                class="version"
                                style="display:none;">
                                {
                                    for $line in $chapter//tei:orig//tei:l
                                    return
                                        <p>
                                            {string($line/@n), string($line)}
                                        </p>
                                }
                            </div>
                            
                            <div
                                id="{$id}reg"
                                class="version"
                                style="display:none;">
                                {
                                    for $line in $chapter//tei:reg//tei:l
                                    return
                                        <p>
                                            {string($line/@n), string($line)}
                                        </p>
                                }
                            </div>
                            <div
                                id="{$id}facs"
                                class="version"
                                style="display:none;">
                                {
                                    for $img in $chapter//tei:pb[@facs]
                                    return
                                        (<img
                                            src="img/{$img/replace(@facs, " ", "_")}"/>, <br/>)
                                }
                            
                            </div>
                        </div>
                }
            </div>
        </div>

    </body>

</html>
