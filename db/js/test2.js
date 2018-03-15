$(function () {
    
    $("#json-one").change(function () {
        
        var $dropdown = $(this);
        
        $.getJSON("json/dataLeft.json", function (data) {
            
            var key = $dropdown.val();
            var vals =[];
            
            switch (key) {         
                case 'base':
                vals = [ 'GW wählen'];
                break;
                case 'regL':
                vals = data.regL.split(",");
                break;
                case 'origL':
                vals = data.origL.split(",");
                break;
                case 'facsL':
                vals = data.facsL.split(",");
                break
            }
            
            var $jsontwo = $("#json-two");
            $jsontwo.empty();
            $jsontwo.append("<option>GW wählen</option>")
            $.each(vals, function (index, value) {
                $jsontwo.append("<option>" + value + "</option>");
            });
        });
    });
});

$(function () {
    
    $("#json-two").change(function () {
        
        var $dropdown = $(this);
        
        $.getJSON("json/dataLeft.json", function (data) {
            
            var key = $dropdown.val();
            var vals =[];
            
            switch (key) {
                case 'base':
                vals =[ 'Kapitel wählen'];
                break;
                case 'dummyregL':
                vals = data.dummyregL.split(",");
                break;
                case 'dummyorigL':
                vals = data.dummyorigL.split(",");
                break;
                case 'dummyfacsL':
                vals = data.dummyfacsL.split(",");
                break;
                case 'dummy2regL':
                vals = data.dummy2regL.split(",");
                break;
                case 'dummy2origL':
                vals = data.dummy2origL.split(",");
                break;
                case 'dummy2facsL':
                vals = data.dummy2facsL.split(",");
                break;
            }
            
            var $jsonthree = $("#json-three");
            $jsonthree.empty();
            $jsonthree.append("<option>Kapitel wählen</option>")
            $.each(vals, function (index, value) {
                $jsonthree.append("<option>" + value + "</option>");
            });
        });
    });
});

$(function () {
// Makes sure the code contained doesn't run until
//     all the DOM elements have loaded

 $('#json-three').change(function () {
  $('.chapter').hide();
   $('#' + $(this).val()).show();
  });
});


$(function () {
    
    $("#json-four").change(function () {
        
        var $dropdown = $(this);
        
        $.getJSON("json/dataRight.json", function (data) {
            
            var key = $dropdown.val();
            var vals =[];
            
            switch (key) {
                case 'regR':
                vals = data.regR.split(",");
                break;
                case 'origR':
                vals = data.origR.split(",");               
                break;
                case 'facsR':
                vals = data.facsR.split(",");
                break
                case 'base':
                vals =[ 'GW wählen'];
            }
            
            var $jsonfive = $("#json-five");
            $jsonfive.empty();
            $jsonfive.append("<option>GW wählen</option>")
            $.each(vals, function (index, value) {
                $jsonfive.append("<option>" + value + "</option>");
            });
        });
    });
});

$(function () {
    
    $("#json-five").change(function () {
        
        var $dropdown = $(this);
        
        $.getJSON("json/dataRight.json", function (data) {
            
            var key = $dropdown.val();
            var vals =[];
            
            switch (key) {
                case 'dummyregR':
                vals = data.dummyregR.split(",");
                break;
                case 'dummyorigR':
                vals = data.dummyorigR.split(",");
                break;
                case 'dummyfacsR':
                vals = data.dummyfacsR.split(",");
                break;
                case 'dummy2regR':
                vals = data.dummy2regR.split(",");
                break;
                case 'dummy2origR':
                vals = data.dummy2origR.split(",");
                break;
                case 'dummy2facsR':
                vals = data.dummy2facsR.split(",");
                break;
                case 'base':
                vals =[ 'Kapitel wählen'];
            }
            
            var $jsonsix = $("#json-six");
            $jsonsix.empty();
            $jsonsix.append("<option>Kapitel wählen</option>")
            $.each(vals, function (index, value) {
                $jsonsix.append("<option>" + value + "</option>");
            });
        });
    });
});
 
$(function () {

 $('#json-six').change(function () {
  $('.chapterR').hide();
   $('#' + $(this).val()).show();
  });
});

$(function () {
    var divs = $('.GWL>div');
    var now = 0; // currently shown div
    divs.hide();
    $("button[name=nextl]").click(function (e) {
        divs.eq(now).hide();
        now = (now + 1 < divs.length) ? now + 1 : 0;
        divs.eq(now).show(); // show next
    });
    $("button[name=prevl]").click(function (e) {
        divs.eq(now).hide();
        now = (now > 0) ? now - 1 : divs.length - 1;
        divs.eq(now).show(); // or .css('display','block');
        //console.log(divs.length, now);
    });
});

$(function () {
    var divs = $('.GWR>div');
    var now = 0; // currently shown div
    divs.hide();
    $("button[name=nextr]").click(function (e) {
        divs.eq(now).hide();
        now = (now + 1 < divs.length) ? now + 1 : 0;
        divs.eq(now).show(); // show next
    });
    $("button[name=prevr]").click(function (e) {
        divs.eq(now).hide();
        now = (now > 0) ? now - 1 : divs.length - 1;
        divs.eq(now).show(); // or .css('display','block');
        //console.log(divs.length, now);
    });
});