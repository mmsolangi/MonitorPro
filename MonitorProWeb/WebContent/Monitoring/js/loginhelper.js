// JScript source code


//UIHelper class
var LoginHelper = {
    SetCookie: function (name, value) {
//        debugger;
        var date = new Date();
        var minutes = SessionTimeOut;

        date.setTime(date.getTime() + (minutes * 60 * 1000));
        $.cookie(name, value, { expires: date, path: '/' });
    },
    ValidateLogin: function (username, password) {
        $.ajax({
            //url: 'Monitoring/json/login.json',
            url: WCFServiceURL + "Login",
            method: 'get',
            dataType: 'json', 
            data: 'username=' + username + '&password=' + password,
            success: function (response) {
                if (response.status == "success") {
                    LoginHelper.SetCookie("token", response.data.token);
                    LoginHelper.SetCookie("user", username);
                    LoginHelper.SetCookie("typ", response.data.isadmin);
                    document.location.href = "Monitoring/index4.html";
                }
                else if (response.status == "error") {
                    alert(response.message);
                }
            },
            error: function (err) {

                alert('There is an error occured while login.');
            },
            complete: function () {
                //                    //var display = $("#txtOStodate").val() == "" ? "none" : "inline";
                //                    $("#imgOSLoading").css('display', 'none');
                //                    //$("#imgOSundo").css('display', display);
                //                    $('#container').highcharts().hideLoading();
            }
        });

    }
}