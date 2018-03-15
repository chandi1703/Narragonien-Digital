$(function () {
// Makes sure the code contained doesn't run until
//     all the DOM elements have loaded

 $('#json-three').change(function () {
  $('.version').hide();
   $('#' + $(this).val()).show();
  });
});


$(function () {
    
    $("#json-one").change(function () {
        
        var $dropdown = $(this);
        
        $.getJSON("json/data1.json", function (data) {
            
            var key = $dropdown.val();
            var vals =[];
            
            switch (key) {
                case 'dummy':
                vals = data.dummy.split(",");
                break;
                case 'dummy2':
                vals = data.dummy2.split(",");
                break;
                case 'base':
                vals =[ 'GW wählen'];
            }
            
            var $jsontwo = $("#json-two");
            $jsontwo.empty();
            $jsontwo.append("<option>Kapitel wählen</option>")
            $.each(vals, function (index, value) {
                $jsontwo.append("<option>" + value + "</option>");
            });
        });
    });
});

$(function () {
    
    $("#json-two").change(function () {
        
        var $dropdown = $(this);
        
        $.getJSON("json/data1.json", function (data) {
            
            var key = $dropdown.val();
            var vals =[];
            
            switch (key) {
                case 'P5':
                vals = data.P5.split(",");
                break;
                case 'P20':
                vals = data.P20.split(",");
                break;
                case 'B1':
                vals = data.B1.split(",");
                break;
                case 'B2':
                vals = data.B2.split(",");
                break;
                case 'base':
                vals =[ 'Kapitel wählen'];
            }
            
            var $jsonthree = $("#json-three");
            $jsonthree.empty();
            $jsonthree.append("<option>Fassung wählen</option>")
            $.each(vals, function (index, value) {
                $jsonthree.append("<option>" + value + "</option>");
            });
        });
    });
});