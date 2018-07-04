xquery version "3.1";

module namespace dch="http://oc.narragonien-digital.de/dch";
declare default element namespace "http://www.tei-c.org/ns/1.0";

import module namespace templates="http://exist-db.org/xquery/templates" ;
import module namespace config="http://exist-db.org/apps/narrenapp/config" at "config.xqm";

declare variable $dch:data := collection('/db/apps/narrenapp/data');

declare function dch:loadCh($node as node(), $model as map(*), $version as xs:string, $path as xs:string) {
    let $data := collection('/db/apps/narrenapp/data')
    for $document in $data
           let $uri := util:unescape-uri(replace(base-uri($document), '.+/(.+).xml$', '$1'), 'UTF-8')
           return
                            
           (: in Werke aufsplitten :)       
           <div id="{concat($uri,$version)}" class="GWL">
           {
               for $chapter in $document//div[@type = 'chapter']
               let $id := string($chapter/@xml:id)
               return
                                        
               (: normalisierte Kapitel in GW einfügen, nicht anzeigen, erst nach Auswahl; siehe CSS :)
               <div id="{concat($uri,$id,$version)}" class="chapterL" style="display:none">
                        
                       {
                       (: ermöglicht Einbindung der verschiedenen Varianten in einer Funktion :)
                       
                       switch($path)
                            case "reg" return (                    
                                for $line in $chapter//reg//l
                                return
                                 <p>
                                 <div class="row">
                                        <div id="line" class="col-sm-2">{string($line/@n)}</div>
                                        <div id="text" class="col-sm-10">{string($line)}</div>
                                 </div>
                                 </p>)
                       
                            case "orig" return (
                                for $line in $chapter//orig//l
                                return
                                 <p>
                                 <div class="row">
                                        <div id="line" class="col-sm-2">{string($line/@n)}</div>
                                        <div id="text" class="col-sm-10">{string($line)}</div>
                                 </div>
                                 </p>)
                       
                            default return "Something went horribly wrong here"
                       }
                </div>
             }
             </div>
};


declare function dch:tei2html($nodes as node()*, $version as xs:string) {
    
    for $node in $nodes
    return
        typeswitch($node)
        
            case text() return
                $node
                
            case element (choice) return
                switch($version)
                    case "regL" return
                        <div class='nrm'>{ for $child in $node//reg return dch:tei2html($child,$version) }</div>
                    case "origL" return
                        <div class='ocr'>{ for $child in $node//orig return dch:tei2html($child,$version) }</div>
                    default return ()
                
            case element (l) return    
                    <div class="row">
                        <div id="line" class="col-sm-2">{ string($node/@n) }</div>
                        <div id="text" class="col-sm-10">{ dch:tei2html($node/node(),$version) }</div>
                    </div>
                
            case element(ref) return <a>{ $node/(@*|node()) }</a>
            case element() return
                dch:tei2html($node/node(),$version)
            default return 
                $node/string()
        
};

declare function dch:view($node as node(), $model as map(*), $version as xs:string) {
    
    for $document in $dch:data
        let $uri := util:unescape-uri(replace(base-uri($document), '.+/(.+).xml$', '$1'), 'UTF-8')
    
    for $body in $document//body
        return
            <div id="{concat($uri,$version)}" class="GWL">{
                for $chapter in $body//div[@type='chapter']
                let $id := string($chapter/@xml:id)
                return
                    <div id="{concat($uri,$id,$version)}" class="NORMALIZEDchapterL" style="display:none;">{dch:tei2html($chapter,$version)}</div>}  
                    
            </div>
};

