xquery version "3.1";
declare default element namespace "http://www.tei-c.org/ns/1.0";

declare function local:transform($n)
{
  typeswitch($n)
  case element(lg) return <div>{
    for $child in $n/*
    return local:transform($child)
    }</div>
  case element(ref) return <a>{ $n/(@*|node()) }</a>
  case element() return element { node-name($n)} {
    for $child in $n/(@*|node())
    return local:transform($child)
  }
  default return $n
};

for $n in //lg
let $play := $n
return

local:transform($play)