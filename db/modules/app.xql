xquery version "3.1";

module namespace app="http://exist-db.org/apps/narrenapp/templates";

import module namespace templates="http://exist-db.org/xquery/templates" ;
import module namespace config="http://exist-db.org/apps/narrenapp/config" at "config.xqm";


(:~
 : This is a sample templating function. It will be called by the templating module if
 : it encounters an HTML element with an attribute data-template="app:test" 
 : or class="app:test" (deprecated). The function has to take at least 2 default
 : parameters. Additional parameters will be mapped to matching request or session parameters.
 : 
 : @param $node the HTML node with the attribute which triggered this call
 : @param $model a map containing arbitrary data - used to pass information between template calls
 :)
declare function app:selectGw($node as node(), $model as map(*)) {
    let $data := collection('/db/apps/narrenapp/data')
    for $document in $data
                        let $uri := util:unescape-uri(replace(base-uri($document), '.+/(.+).xml$', '$1'), 'UTF-8')
                        return
                            (
                            <option value=""/>,
                            <option
                                value="{$uri}facsL">{$uri} Faksimile</option>,
                            <option
                                value="{$uri}regL">{$uri} Normalisiert</option>,
                            <option
                                value="{$uri}origL">{$uri} Original</option>)
};

declare function app:selectCh($node as node(), $model as map(*)) {
    let $data := collection('/db/apps/narrenapp/data')
    for $document in $data
                        for $chapter in $document//tei:div[@type = 'chapter']
                        let $id := string($chapter/@xml:id)
                        return
                            (
                            <option value=""/>,
                            <option
                                value="{$id}facsL">Kapitel {$id} Faksimile</option>,
                            <option
                                value="{$id}regL">Kapitel {$id} normalisiert</option>,
                            <option
                                value="{$id}origL">Kapitel {$id} Original</option>)
};




