// JScript source code
        var makesArray = [];
        var watermark = 'Search Order';


        function FillOrders(searchText) {
            $("#loader").fadeIn();
            $('#chart tr:gt(0)').empty();
            $('#chartdetail tr:gt(0)').empty();
           
            var ordersArray = Orders;

            if (searchText != "" && searchText != watermark) {
                ordersArray = jQuery.grep(ordersArray, function (item, i) {
                    return item.orderid == searchText;
                });
            }
            else {
                if ($("#rectype").val() != "ALL") {
                    ordersArray = jQuery.grep(ordersArray, function (item, i) {
                        return item.outcomestatus == $("#rectype").val();
                    });
                }
            }
            if (ordersArray.length > 0) {
                for (var index = 0; index < ordersArray.length; index++) {
                    var orderid = ordersArray[index].orderid;
                    var lobcode = ordersArray[index].lobcode;
                    var solutionlogiccode = ordersArray[index].solutionlogiccode;
                    var applicationcode = ordersArray[index].applicationcode;
                    var correlationid = ordersArray[index].correlationid;
                    var clientuserid = ordersArray[index].clientuserid;
                    var clientrequesttime = ordersArray[index].clientrequesttime;
                    var outcomestatus = ordersArray[index].outcomestatus;
                    var takentime = ordersArray[index].takentime;
                    var errorcode = ordersArray[index].errorcode;
                    var detailcol = "<a href=javascript:fillorderdetails('" + orderid + "');>Details</span>"
                    var cssClass=""
                    if(ordersArray[index].outcomestatus!="SUCCESS")
                        cssClass="RedClass"
                    $('<tr Class=' + cssClass + '></tr>').html('<td>' + detailcol + '</td><td align="left">' + orderid + '</td><td>' + lobcode + '</td><td>' + solutionlogiccode + '</td><td>' + applicationcode + '</td><td>' + correlationid + '</td><td>' + clientuserid + '</td><td>' + clientrequesttime + '</td><td>' + outcomestatus + '</td><td>' + takentime + '</td><td>' + errorcode + '</td>').appendTo('#chart');
                }
            } else {
                $('<tr></tr>').html('<td colspan=12 height="40" align=center>no order(s) found</td>').appendTo('#chart');
            }
            $("#loader").fadeOut();
        }
        function fillorderdetails(orderId) {
            // $("#overlay2").fadeIn();
            $("#loader").fadeIn();
            PopulateDetails(orderId);
            $('#chartdetail').fadeIn('slow');
            $("#loader").fadeOut();

        }
        function PopulateDetails(orderId) {

            $('#chartdetail').empty();
            $('<tr></tr>').html('<th>Execution Sequence</th><th>Solution Logic Step Name</th><th>System Source Error Code</th><th>System Source Error Desc</th><th>Inputpay Load</th><th>Outputpay Load</th>').appendTo('#chartdetail');
            makesArray = jQuery.grep(OrderDetails, function (detail, i) {
                return detail.orderid == orderId;
            });
            if (makesArray.length > 0) {
                for (var i = 0; i < makesArray.length; i++) {
                    var executionsequence = makesArray[i].executionsequence;
                    var solutionlogicstepname = makesArray[i].solutionlogicstepname;
                    var systemsourceerrorcode = makesArray[i].systemsourceerrorcode;
                    var systemsourceerrordesc = makesArray[i].systemsourceerrordesc;
                    var inputpayload = makesArray[i].inputpayload;
                    var outputpayload = makesArray[i].outputpayload;

                    $('<tr></tr>').html('<td>' + executionsequence + '</td><td>' + solutionlogicstepname + '</td><td>' + systemsourceerrorcode + '</td><td>' + systemsourceerrordesc + '</td><td><textarea cols=30 rows=5>' + inputpayload + '</textarea></td><td><textarea cols=30 rows=5>' + outputpayload + '</textarea></td>').appendTo('#chartdetail');
                }
            } else {
                $('<tr></tr>').html('<td colspan=6 height="35" align="center">no details found</td>').appendTo('#chartdetail');
            }
           // $("#overlay2").fadeOut();
        }
        function Search(frm) {

            FillOrders(frm.txtSearch.value);

            return false;
        }
    