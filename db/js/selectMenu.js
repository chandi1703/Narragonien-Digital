/*Makes use of chosen select plugin; makes the whole select thing rather pleasant*/ 

$(function () {
    $('.chosen-select').chosen();
});

/*According to what the user clicks on the second select will be changed; uses values from a json file*/
$(function () {
    $("#json1").change(function () {
        
        var $dropdown = $(this);
        
        $.getJSON("resources/json/selectMenuData.json", function (data) {
            var key = $dropdown.val();
            var vals =[];
            
            switch (key) {
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
            
            var $jsontwo = $("#json2");
            
            $jsontwo.empty();
            $jsontwo.append("<option value=''></option>")
            $.each(vals, function (index, value) {
            
                /*Creates a new value for select menu with regular expression*/
                $jsontwo.append("<option value=" + value + ">" + value.replace(/(GW[0-9]+).+/g, "$1") + "</option>");
            });
            
            /*chosen select needs an updated trigger so that new data will be visible in the browser*/
            $jsontwo.trigger("chosen:updated");
        });
    });
});

/*same function as above, only used for the third menu*/
$(function () {
    
    $("#json2").change(function () {
        
        var $dropdown = $(this);
        
        $.getJSON("resources/json/selectMenuData.json", function (data) {
            
            var key = $dropdown.val();
            var vals =[];
            
            switch (key) {
                case 'GW5041regL':
                vals = data.GW5041regL.split(",");
                break;
                case 'GW5041origL':
                vals = data.GW5041origL.split(",");
                break;
                case 'GW5041facsL':
                vals = data.GW5041facsL.split(",");
                break;
                case 'GW5046regL':
                vals = data.GW5046regL.split(",");
                break;
                case 'GW5046origL':
                vals = data.GW5046origL.split(",");
                break;
                case 'GW5046facsL':
                vals = data.GW5046facsL.split(",");
                break;
            }
            
            var $jsonthree = $("#json3");
            $jsonthree.empty();
            $jsonthree.append("<option value=''></option>")
            $.each(vals, function (index, value) {
                $jsonthree.append("<option value=" + value + ">Kapitel " + value.replace(/GW[0-9]+([A-Z][0-9]+).+/g, "$1") + "</option>");
            });
            
            $jsonthree.trigger("chosen:updated");
        });
    });
});

$(function () {
// Makes sure the code contained doesn't run until
//     all the DOM elements have loaded

 $('#json3').change(function () {
  $('.chapter').hide();
   $('#' + $(this).val()).show();
  });
});