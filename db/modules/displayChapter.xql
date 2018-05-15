xquery version "3.1";

module namespace dch="http://oc.narragonien-digital.de/dch";
declare namespace tei = "http://www.tei-c.org/ns/1.0";

import module namespace templates="http://exist-db.org/xquery/templates" ;
import module namespace config="http://exist-db.org/apps/narrenapp/config" at "config.xqm";

declare function dch:loadCh($node as node(), $model as map(*), $version as xs:string) {
    let $data := collection('/db/apps/narrenapp/data')
    for $document in $data
           let $uri := util:unescape-uri(replace(base-uri($document), '.+/(.+).xml$', '$1'), 'UTF-8')
           return
                            
           (: in Werke aufsplitten :)
           <div id="{concat($uri,$version)}" class="GWL">
           {
               for $chapter in $document//tei:div[@type = 'chapter']
               let $id := string($chapter/@xml:id)
               return
                                        
               (: normalisierte Kapitel in GW einfügen, nicht anzeigen, erst nach Auswahl; siehe CSS :)
               <div id="{concat($uri,$id,$version)}" class="chapter" style="display:none">
                       {
                       for $line in $chapter//tei:reg//tei:l
                       return
                       <p>{string($line/@n), string($line)}</p>
                       }
                </div>
             }
             </div>
};