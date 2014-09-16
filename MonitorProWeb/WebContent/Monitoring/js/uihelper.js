// JScript source code


var currentLOB;
var LOBDataModel, LOBDetailDataModel;
var cols, colsDetail;
var LOBDetailData = new Array();
var sort = new Array();
var Tabs=new Object();
var gridDetail;
var UnAuthorizedCode = "Err2031";
var AdminSetupEnum=new Object();
AdminSetupEnum.UserManagement=1;
AdminSetupEnum.TabManagement=2;
AdminSetupEnum.NotificationEmails=3;
var UserSetupEnum=new Object();
UserSetupEnum.ChangePassword=1;
UserSetupEnum.UpdateProfile=2;
var chart;
var gridTab;
var gridUser;
var gridNotification;
var currentNFlob;
var Stores=new Object();;
var closedialog;
var UserPwd;

          function overlayclickclose() {	
              if (closedialog) {
                  $('#divInputOutputMessage').dialog('close');
              }

              //set to one because click on dialog box sets to zero
              closedialog = 1;
          }


colsDetail = [{ title: "Detail ID", width: 100, dataIndx: "outcomeDTLID", editable: false, resizable: false },
                        { title: "Application Code", width: 100, dataIndx: "applicationCode" , editable: false, resizable: false },
                        { title: "Flow Name", width: 280, dataIndx: "slStepName" , editable: false, resizable: false },
                        { title: "Input Message", width: 0, dataIndx: "inputPayload", editable: true, hidden:true, resizable: false},
                        { title: "Output Message", width: 0, dataIndx: "outputPayload", editable: true,hidden: true, resizable: false},
                        { title: "Log Level", width: 100, dataIndx: "errorType", editable: false, resizable: false  },
                        { title: "Error Code", width: 100, dataIndx: "sourceSystemErrorCode" , editable: false, resizable: false },
                        { title: "Error Desc", width: 450, dataIndx: "sourceSystemErrorDesc", editable: false , resizable: false }

                        ];



  $(document).ready(function () {
    var fromDate = new Date(); 
    var toDate=new Date(); 
    fromDate.setDate(fromDate.getDate() - 2);
    fromDate = fromDate.getMonth()+1 + '/' + fromDate.getDate() + '/' + fromDate.getFullYear() + ' 00:00';// + fromDate.getHours() + ':' + fromDate.getMinutes() 
    toDate = toDate.getMonth()+1 + '/' + toDate.getDate() + '/' + toDate.getFullYear() + ' 23:00';// + toDate.getHours() + ':' + toDate.getMinutes()        
    
    $("#txtOSfromdate").val(fromDate);
    $("#txtOStodate").val(toDate);
//	debugger;



      var loggedUserName = $.cookie("user");
	  $("#spnUsername").html(loggedUserName);
      
            if(!UIHelper.SetWelcome())
		return false;

            //$("#logout").click(function(){
               // UIHelper.Logout();
           // });
            
            UIHelper.GetBusinessObjectList("NFLob1");

            $("#TabName").keypress(function (e) {
             	  if (e.keyCode != 9) {
            		  var txt = String.fromCharCode(e.which);
             	  	  if(txt.match(/^[^A-Za-z0-9\-\.\b\t]+$/))
             	  	  {
             	          return false;
             	  	  }
             	  }
             	});
           
            $("#noOfDays").keypress(function (e) {
                if (e.keyCode != 9) {
                var txt = String.fromCharCode(e.which);
                   if(txt.match(/^[0-9\b\t]/g))
                   {
                        return true;
                   }
                   else
                	   return false;
                }
              });
            
            $("#btn").click(function(){
                 UIHelper.EmptyUserFormControls();
                 UIHelper.PopulateLOBCheckboxes();
                 $("#lblUserStatus").css('display','none');
                 $("#chkStatus").prop('checked',true);
                 $("#divUserForm").dialog("open").css('display','inline');                 
            });
            $("#btnDeleteUser").click(function(){
                UIHelper.deleteGridRow(AdminSetupEnum.UserManagement);
            });
            $("#btnCreateTab").click(function(){
                UIHelper.EmptyTabFormControls();
                $("#lblTabStatus").css('display','none');
                $("#TabStatus").prop('checked', false);
                $("#divTabForm").dialog("open").css('display','inline');
            });
            $("#btnDeleteTab").click(function(){
                UIHelper.deleteGridRow(AdminSetupEnum.TabManagement);
            });
             $("#btnCreateNotification").click(function(){
                UIHelper.EmptyNFFormControls();                
                $("#divNFEmailForm").dialog("open");
                // Calling Business Object menthod via ajax.. Khurram
                UIHelper.GetBusinessObjectList("NFLob");
                currentNFlob="";
            });
             $("#btnDeleteNotification").click(function(){
                 UIHelper.deleteGridRow(AdminSetupEnum.NotificationEmails);
             });
             //Binding the onchange event of Business Object Dropdown which is using on Performance graph param.-Khurram
             $( "#NFLob1" ).bind( "change", function() {
            	  var selectedValue = $(this).val();
            	  UIHelper.LoadGraphData();
            	  $('#NFLob1').val(selectedValue);
            	});
             
             $('#Username').keypress(function (e) {
           	  if (e.keyCode != 9) {
          		  var txt = String.fromCharCode(e.which);
           	  	  if(txt.match(/^[^A-Za-z0-9\-\.\b\t]+$/))
           	  	  {
           	          return false;
           	  	  }
           	  }
           	});
             
            $(".quick_search").submit(function(){
                
                if($("#SMID").val()=="" || $("#SMID").val()=="ID"){
                    alert('Please enter ID');
                    $("#SMID").focus();
                }
                else
                {
                    $("#divStateMachine").dialog({
                    height: 400,
                    width: 590,
                    resizable: false,
                    modal: true
                    }).css('display','inline');  
                    UIHelper.PopulateStates();
                }
                return false;

            });
            
            UIHelper.AdminSetup();

            // user detail form start

            var name = $( "#Username" ),
            email = $( "#Email" ),
            password = $( "#Password" ),
            allFields = $( [] ).add( name ).add( email ).add( password ),
            tips = $( ".validateTips" );
            // change password dialog
            
            var curpassword = $( "#CurrentPassword" ),
            newpassword = $( "#NewPassword" ),
            confpassword = $( "#ConfPassword" ),
            passwordFields = $( [] ).add( curpassword ).add( newpassword ).add( confpassword );
            
            var pname = $( "#Pusername" ),
            pemail = $( "#Pemail" ),
            profileFields = $( [] ).add( pname ).add( pemail );
            
            
            function updateTips( t ) {
              tips
                .text( t )
                .addClass( "ui-state-highlight" );
              setTimeout(function() {
                tips.removeClass( "ui-state-highlight", 1500 );
              }, 500 );
            }
 
            function checkLength( o, n, min, max ) {
              if ( o.val().length > max || o.val().length < min ) {
                o.addClass( "ui-state-error" );
                updateTips( "Length of " + n + " must be between " +
                  min + " and " + max + "." );
                return false;
              } else {
                return true;
              }
            }
            function checkNewPasswords(o,v,message) {
            	if (o.val()!=v.val()) {
                  v.addClass( "ui-state-error" );
                  updateTips(message);
                  return false;
            	} else {
            	  return true;
              }
            }
            function checkRegexp( o, regexp, n ) {
              if ( !( regexp.test( o.val() ) ) ) {
                o.addClass( "ui-state-error" );
                updateTips( n );
                return false;
              } else {
                return true;
              }
            }
            $( "#divUserUpdateProfile").dialog({
                autoOpen: false,
                height: 250,
                resizable: false,
                width: 450,
                modal: true,
                buttons: {
                  "Update": function() {
                   
                  var bValid = true;
                    profileFields.removeClass( "ui-state-error" );
                    
                    bValid = bValid && checkLength( pname, "username", 3, 16 );
                    bValid = bValid && checkRegexp( pemail, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "Invalid email" );
            
                    if ( bValid ) {
                    	$( ".validateTips" ).text("All form fields are required.");
                  	  
                      // ajax call for insert
//                      debugger;
                      //alert("URL "+ WCFServiceURL);
                       // ajax call for insert
                      $.ajax({
                          // url: 'json/saveuser.json',
                          url: WCFServiceURL + "UpdateProfile",
                          method: 'GET',
                          dataType: 'json',                           
                          data: 'tokenId=' + UIHelper.GetCookie("token") + "&username=" + $("#Pusername").val()+ "&email=" + $("#Pemail").val() + "&phone=" + $("#Phone").val() + "&address=" + $("#Address").val(),
                         
                          success: function (data) {
                              if (data.status == "success") {
                                  alert('User profile updated successfuly.');
                                  $("#divUserUpdateProfile" ).dialog( "close" );
                              }
                              else if (data.status == "error") {
                                  alert('Exception occurred while updating user profile ');
                                  pname.focus();
                              }
                              else {
                                  UIHelper.CheckAuthorization(data.message);
                              }
                              
                          },
                          error: function (err) {
                              alert('There is an error occured while update user profile.');
                          },
                          complete: function () {

                          }
                      });                       
                    } 
                    
                  },
                  Cancel: function() {
                    $( this ).dialog( "close" ).css('display','none');
                  }
                },
                close: function() {
                $( ".validateTips" ).text("All form fields are required.");
                  profileFields.val( "" ).removeClass( "ui-state-error" );
                },
                dialogClass: 'my-dialog9'
              });
            $( "#divUserChangePassword").dialog({
                autoOpen: false,
                height: 250,
                width: 450,
                resizable: false,
                modal: true,
                buttons: {
                  "Change Password": function() {
                  debugger;
                   
                  var bValid = true;
                    passwordFields.removeClass( "ui-state-error" );

                    bValid = bValid && checkLength( curpassword, "Current password", 5, 16 );
                    bValid = bValid && checkLength( newpassword, "New password", 5, 16 );
                    bValid = bValid && checkLength( confpassword, "Confirmation password", 5, 16 );
                    
                    bValid = bValid && checkNewPasswords( newpassword, confpassword, "Confirmation password should be matched");
                    if ( bValid ) {
                    	$( ".validateTips" ).text("All form fields are required.");
                  	  
                      // ajax call for insert
                      debugger;
                      //alert("URL "+ WCFServiceURL);
                       // ajax call for insert
                      $.ajax({
                          // url: 'json/saveuser.json',
                          url: WCFServiceURL + "ChangePassword",
                          method: 'GET',
                          dataType: 'json',                           
                          data: 'tokenId=' + UIHelper.GetCookie("token") + "&name=" + $("#UserName").val()+ "&currentpwd=" + $("#CurrentPassword").val() + "&newpwd=" + $("#NewPassword").val() + "&retype=" + $("#ConfPassword").val(),
                         
                          success: function (data) {
                              if (data.status == "success") {
                                  alert('Password changed  successfully.');
                                  $("#divUserChangePassword" ).dialog( "close" );
                              }
                              else if (data.status == "error") {
                                  alert('Exception occurred while change new password!');
                                  $("#NewPassword").focus();
                              }
                              else {
                                  UIHelper.CheckAuthorization(data.message);
                              }
                              
                          },
                          error: function (err) {
                              alert('There is an error occured while changing new password.');
                          },
                          complete: function () {

                          }
                      });                       
                    } 
                    
                  },
                  Cancel: function() {
                    $( this ).dialog( "close" ).css('display','none');
                  }
                },
                close: function() {
                $( ".validateTips" ).text("All form fields are required.");
                  passwordFields.val( "" ).removeClass( "ui-state-error" );
                },
                dialogClass: 'my-dialog8'
              });
            $( "#divUserForm" ).dialog({
                  autoOpen: false,
                  height: 300,
                  width: 450,
                  resizable: false,
                  modal: true,
                  buttons: {
                    "Create User": function() {
                    debugger;
                      var bValid = true;
                      allFields.removeClass( "ui-state-error" );
 
                      bValid = bValid && checkLength( name, "username", 3, 16 );
                      debugger;
                      if($("#UserID").val()=="")
                        bValid = bValid && checkLength( password, "password", 5, 16 );
                      bValid = bValid && checkLength( email, "email", 6, 80 );
                      
 
                      // bValid = bValid && checkRegexp( name,
						// /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z,
						// 0-9, underscores, begin with a letter." );
                      // From jquery.validate.js (by joern), contributed by
						// Scott Gonzalez:
						// http://projects.scottsplayground.com/email_address_validation/
                      bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "Invalid email" );
                      // bValid = bValid && checkRegexp( password,
						// /^([0-9a-zA-Z])+$/, "Password field only allow : a-z
						// 0-9" );
            			var UserLobs=UIHelper.GetCheckedLOBs();            
            			if(UserLobs=="" && (bValid))
						{	
							updateTips( "Please select business object.");
							bValid=false
						}

            
                      if ( bValid ) {
                    	var pwd; 
                        if($("#UserID").val()=="")
                            pwd=$("#Password").val();
                        else
                            pwd=UserPwd;
                        // aja9x call for insert
                        debugger;
                        //alert("URL "+ WCFServiceURL);
                        
                         // ajax call for insert
                        $.ajax({
                            // url: 'json/saveuser.json',
                            url: WCFServiceURL + "SaveUser",
                        	//url: WCFServiceURL + "UpdateUser", 
                            method: 'get',
                            dataType: 'json',                           
                            // data: 'tokenId=' + UIHelper.GetCookie("token") +
							// "&id=" + $("#UserID").val() + "&name=" +
							// $("#Username").val() + "&password=" +
							// $("#Password").val() + "&email=" +
							// $("#Email").val() + "&status=" +
							// $("#UserStatus").is(':checked') + "&userlobs=" +
							// UserLobs,
                            data: 'tokenId=' + UIHelper.GetCookie("token") + "&id=" + $("#UserID").val() + "&name=" + $("#Username").val() + "&password=" + pwd + "&email=" + $("#Email").val() + "&status=" + $("#chkStatus").is(':checked') + "&userlobs=" + UserLobs,
                           
                            success: function (data) {
                                if (data.status == "success") {
                                    alert('User saved successfully.');
                                    $("#divUserForm" ).dialog( "close" );
                                    UIHelper.BindUserGrid();
                                }
                                else if (data.status == "duplicate") {
                                    alert('Username already exist.');
                                    $("#Username").focus();
                                }
                                else {
                                    UIHelper.CheckAuthorization(data.message);
                                }
                                
                            },
                            error: function (err) {
                                alert('There is an error occured while saving user.');
                            },
                            complete: function () {

                            }
                        });                       
                      }
                    },
                    Cancel: function() {
                      $( this ).dialog( "close" ).css('display','none');
                    }
                  },
                  close: function() {
                	  $('.my-dialog2 .ui-button-text:contains(Update User)').text('Create User');
                    allFields.val( "" ).removeClass( "ui-state-error" );
                  },
                  dialogClass: 'my-dialog2'
                });

            // user detail form end

	//Input/Output Message popup

$("#divInputOutputMessage").dialog({
                autoOpen: false,
                height: 300,
                width: 650,
                resizable: false,
                modal: true,
		open: function() {
                      closedialog = 1;
                      $('.ui-widget-overlay').bind('click', overlayclickclose);
                  },
		close: function() {
                
			$('.ui-widget-overlay').unbind('click');
                }
      });      


            // Tab detail form
             $( "#divTabForm" ).dialog({
                  autoOpen: false,
                  height: 200,
                  width: 400,
                  resizable: false,
                  modal: true,
                  buttons: {
                    "Save": function() {
            	 debugger;
                      var bValid = true;
                      allFields.removeClass( "ui-state-error" );
                      if($("#TabName").val()=="")
                      {
                        alert("Please enter name");
                        $("#TabName").focus();
                      }
                      else  
                      {              
                        // ajax call for insert
                        $.ajax({
                            // url: 'json/savetab.json',
                        	url: WCFServiceURL + "SaveTab",
                            method: 'get',
                            dataType: 'json',     
                            // data: 'tokenId=' + UIHelper.GetCookie("token") +
							// "&id=" + $("#TabID").val() + "&name=" +
							// $("#TabName").val() + "&status=" +
							// $("#TabStatus").is(':checked'),
                            data: 'tokenId=' + UIHelper.GetCookie("token") + "&id=" + $("#TabID").val() + "&name=" + $("#TabName").val() + "&status=" + $("#TabStatus").is(':checked'),
                            success: function (data) {
                                if (data.status == "success") {
                                	$("#divTabForm" ).dialog( "close" );
            				    	if(data.message=='Update')
            				    	{ 
	            						if(confirm("To see the effect you have to reload the application\n Do you want to reload?"))
	            						{
	            							window.location.reload();
	            						}
            				    	}
            				    	else                                    
            				    		UIHelper.BindTabGrid();
                                }
                                else if (data.status == "duplicate") {
                                    alert('Tab name already exist.');
                                    $("#tabname").focus();
                                }
                                else {
                                    UIHelper.CheckAuthorization(data.message);
                                }
                                
                            },
                            error: function (err) {
                                alert('There is an error occured while saving Tab.');
                            },
                            complete: function () {

                            }
                        });                       
                      }
                    },
                    Cancel: function() {
                    
                      $( this ).dialog( "close" ).css('display','none');
                      
                    }
                  },
                  close: function() {
                		$('.my-dialog .ui-button-text:contains(Update)').text('Save');
                    allFields.val( "" ).removeClass( "ui-state-error" );
                  },
                  dialogClass: 'my-dialog'
                });

            // user tab form end


            // notification form start
            // Tab detail form
             $( "#divNFEmailForm" ).dialog({
                  autoOpen: false,
                  height: 430,
                  width: 460,
                  resizable: false,
                  modal: true,
                  buttons: {
                    "Save": function() {
                      var bValid = true;
                      allFields.removeClass( "ui-state-error" );
                      if($("#NFLob").val()=="")
                      {
                        alert("Please select business object");
                        $("#NFLob").focus();
                      }
                      else if($("#Event").val()=="")
                      {
                        alert("Please select event");
                        $("#Event").focus();
                      }
                      else if($("#NFEmails").val()=="")
                      {
                        alert("Please enter email(s)");
                        $("#NFEmails").focus();
                      }
                      else  
                      {              
                        // ajax call for insert
                        $.ajax({
                            // url: 'json/savenfemail.json',
                        	url: WCFServiceURL + "SaveNfeMail",
                            method: 'get',
                            dataType: 'json',                           
                            data: 'tokenId=' + UIHelper.GetCookie("token") + "&id=" + $("#NFID").val() + "&lob=" + $("#NFLob").val() + "&event=" + $("#Event").val() + "&emails=" + $("#NFEmails").val(),
                            success: function (data) {
                                if (data.status == "success") {
                                    $("#divNFEmailForm" ).dialog( "close" );
                                    UIHelper.BindNFEmailsGrid();
                                } 
                                else  if (data.status == "duplicate") {
                                    alert('Event already exist for the selected business object.');
                                }                              
                                else {
                                    UIHelper.CheckAuthorization(data.message);
                                }
                                
                            },
                            error: function (err) {
                                alert('There is an error occured while saving Notification.');
                            },
                            complete: function () {

                            }
                        });                       
                      }
                    },
                    Cancel: function() {
                      $( this ).dialog( "close" ).css('display','none');
                    }
                  },
                  close: function() {
                	  $('.my-dialog3 .ui-button-text:contains(Update)').text('Save');
                    allFields.val( "" ).removeClass( "ui-state-error" );
                  },
                  dialogClass: 'my-dialog3'
                });
            // notification form end

            LOBDataModel = {
                location: "remote",
                sorting: "remote",
                paging: "remote",
                dataType: "JSON",
                editable: true,
                method: "GET",
                curPage: 1,
                rPP: 20,
                sortIndx: "clientReqDateTime",
                sortDir: "down",
                topVisible: false,
                rPPOptions: [5, 10, 20, 30, 40, 50, 100],
                getUrl: function () {
            		var sortBy
            		sortBy=""
                    var sortDir = (this.sortDir == "up") ? "asc" : "desc";
			
                    var sortin=this.sortIndx;
                   	$.each(cols, function (i, item) {			
		    			if(sortin==item.dataIndx)
		    				sortBy= item.fieldName;
		    		});
                    
                    return { url: WCFServiceURL + "GetLogHeaders",data: "lob=" + currentLOB + "&currPage=" + this.curPage + "&pageSize=" + this.rPP  + "&orderid=" + $('#txtlobSearch').val() + "&store=" + $('#ddlStores').val() + "&inputkeyword=" + $('#txtInputKeyword').val() + "&status=" + $('#ddlLOBStatus').val() + "&fromDate=" + $('#txtLOBfromdate').val() + "&toDate=" + $('#txtLOBtodate').val() + "&sortBy=" + sortBy + "&sortDirection=" + sortDir + "&tokenId=" + UIHelper.GetCookie("token")
                    // return { url: "json/lob.json"//,data: "lob=" + currentLOB
					// + "&currPage=" + this.curPage + "&pageSize=" + this.rPP +
					// "&orderid=" + $('#txtlobSearch').val() + "&status=" +
					// $('#ddlLOBStatus').val() //"&sortBy=" +
					// sort[this.sortIndx] + "&sortDirection=" + sortDir
                    };
                                   


                },
                getData: function (data) {
                    if (data.status == "success") {
                        LOBDetailData.length = 0;
                        UIHelper.BindLOBDetail();
                        return { curPage: data.lob.currentPage, totalRecords: data.lob.totalRecords, data: data.lob.data };
                    }
                    else{
                        UIHelper.CheckAuthorization(data.message)                       
                    }
                }
            }

            LOBDetailDataModel = {
                location: "local",
                dataType: "JSON",
                method: "GET",
                data: LOBDetailData
            }


            // Critical Error Container start

            CriticalDataModel = {
                location: "remote",
                dataType: "JSON",
                editable: false,
                method: "GET",
                getUrl: function () {
                    var sortDir = (this.sortDir == "up") ? "asc" : "desc";
                    var sort = new Array();
                    // return { url: "json/lob.json", data: "status=Critical
					// Errors&currPage=1&pageSize=7"
                    return { url: WCFServiceURL + "GetLogHeaders", data: "status=Errors&currPage=1&pageSize=7&orderid=&tokenId=" + UIHelper.GetCookie("token") + "&sortBy=CLIENTREQUESTDATETIME&sortDirection=desc"
                    
                    };
                },
                getData: function (data) {
                    if (data.status == "success") {
                        return { data: data.lob.data };
                    }
                    else{
                        UIHelper.CheckAuthorization(data.message)                       
                    }
                }
            }
            var grid2 = $("#divCriticalErrors").pqGrid({ height: 250,
                dataModel: CriticalDataModel,
                
                colModel: [

                            { "dataIndx": "lob", "title": "Bus. Object", "width": 100,
                                render: function (ui) {
                                    var rowData = ui.rowData,
                                    dataIndx = ui.dataIndx,
                                    cellData = rowData[dataIndx];
                                    return "<a href=\"javascript:UIHelper.SaveFilter('" + cellData + "','"+rowData["correlID"]+"','','','Errors','','');UIHelper.LoadTab('" + cellData + "',true);\" title='view complete list of " + cellData + " critical issues'><font color='blue'>" + cellData + "</font></a>";
                            }
                            },
                            { "dataIndx": "outcomeHdrID", "title": "ID", "width": 100 },
                            { "dataIndx": "clientReqDateTime", "title": "Request Time", "width": 200 },
                            { "dataIndx": "applicationID", "title":"Store ID", "width" : 120},
                            { "dataIndx": "storeName", "title":"Store Name", "width" : 200 },
					        { "dataIndx": "slCode", "title": "Solution Logic Code", "width": 150 },					        
					        
                            					{ "dataIndx": "correlID", "title":"Source Transaction ID", "width" : 225,"fieldName" : "CORRELATIONID"},					
					        { "dataIndx": "clientUserID", "title": "User", "width": 100 },					        
					        { "dataIndx": "timeTaken", "title": "Duration", "width": 100 },
					        { "dataIndx": "errorCode", "title": "Error Code", "width": 250 }
                ],
                title: "",
                resizable: false,
                columnBorders: true,
                editable: false,
                topVisible: false,
                numberCell: false,
                bottomVisible: false,
                rowSelect: function (evt, obj) {
                    // $("#ddlLOBStatus").val("Critical Errors");
                    // LoadTab(obj.data[obj.rowIndx].lob, true);

                }
            });

            // Critical Error Container end


            
            // User grid start

            UserDataModel = {
                location: "remote",
                dataType: "JSON",
                editable: false,
                method: "GET",
              
               
                getUrl: function () {
                    var sortDir = (this.sortDir == "up") ? "asc" : "desc";
                    var sort = new Array();
                    // return { url: "json/users.json", data: ""
                    return { url: WCFServiceURL + "GetUsers", data: "username=&tokenId=" + UIHelper.GetCookie("token")
                    
                    };
                },
                getData: function (data) {
                    if (data.status == "success") {
                        return { data: data.users };
                    }
                    else{
                        UIHelper.CheckAuthorization(data.message)                       
                    }
                }
            }
           
            // User Grid end


            // Tab grid start

            TabDataModel = {
                location: "remote",
                dataType: "JSON",
                editable: false,
                method: "GET",
               
                getUrl: function () {
                    var sortDir = (this.sortDir == "up") ? "asc" : "desc";
                    var sort = new Array();
                    // return { url: "json/alltabs.json", data: ""
                    // return { url: WCFServiceURL + "GetAllTabs", data:
					// "username=&tokenId=" + UIHelper.GetCookie("token")
                    return { url: WCFServiceURL + "GetLOB", data: "username=&tokenId=" + UIHelper.GetCookie("token")
                    
                    };
                },
                getData: function (data) {
                    if (data.status == "success") {
                        return { data: data.lobs, currPage: 1 };
                    }
                    else{
                        UIHelper.CheckAuthorization(data.message)                       
                    }
                }
            }
           
            // Tab Grid end
            

// NF Emails grid start

            NFEmailsDataModel = {
                location: "remote",
                dataType: "JSON",
                editable: false,
                method: "GET",               
                
                getUrl: function () {
                    var sortDir = (this.sortDir == "up") ? "asc" : "desc";
                    var sort = new Array();
                    // return { url: "json/nfemails.json", data: ""
                    // return { url: WCFServiceURL + "GetAllTabs", data:
					// "username=&tokenId=" + UIHelper.GetCookie("token")
                    return { url: WCFServiceURL + "GetEmails", data: "username=&tokenId=" + UIHelper.GetCookie("token")
                    
                    };
                },
                getData: function (data) {
                    if (data.status == "success") {
                    	
                        return { data: data.nfemails };
                    }
                    else{
                        UIHelper.CheckAuthorization(data.message)                       
                    }
                }
            }
           
            // NF Emails Grid end


            
            $('.column').equalHeight();
            $(".tablesorter").tablesorter();
            $('.dateField').datepicker();
             $('.datetimepicker').datetimepicker({format:'m/d/Y H:i',
                defaultTime:'00:00'
            });
             $('.datetimepickerToDate').datetimepicker({format:'m/d/Y H:i',
                defaultTime:'23:00'
            });
             UIHelper.PopulateTabs();
            UIHelper.PopulateStores();
           
            
           // UIHelper.GetMessages();
          

            Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
                return {
                    radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
                    stops: [
		            [0, color],
		            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
		        ]
                };
            });

            // Build the overall status chart - start
            $('#container').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                },
                loading: {
                    labelStyle: {
                        backgroundImage: 'url("loading.gif")'

                    }
                },
                title: {
                    text: ' '
                },
                subtitle: {
                    text: ' '
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.y}</b>',
                    percentageDecimals: 1
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            color: '#000000',
                            connectorColor: '#000000',
                            formatter: function () {
                                return '<b>' + this.point.name + '</b>:<br> ' + Math.round(this.point.y) + '';
                            }
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: 'Browser share',
                    point: {
                        events: {
                            click: function (event) {
                                UIHelper.CreateOverallStatusBarChart(this.detail,this.name);
                                  // PrepareOverallStatusBarChart(this.name);
// if ($("#OSlob").val() != "All LOB") {
// $("#ddlLOBStatus").val(this.name);
// LoadTab($("#OSlob").val(), true);
// }
                            }
                        }
                    }
                }]
            });

            // Build the overall status chart - end


            // Build the overall status break up chart- start
            $('#containerBar').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: ' '
            },
            subtitle: {
                text: ' ',
                events: {
                    click: function (event) {
                        alert('test');
                    }
                }
            },
            xAxis: {
            	max:2
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'transactions'
                }
            },
            scrollbar: {
             	enabled: true
         	},
         	exporting: {
         		enabled: false
         	},
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                },
                series: {
                    cursor: 'pointer',
                    point: {
                            events: {
                                click: function () {
                                    UIHelper.SaveFilter(this.category,'',$('#OSstores').val(),'',this.series.name,$('#txtOSfromdate').val(),$('#txtOStodate').val());
                                    UIHelper.LoadTab(this.category, true);
                                }
                            }
                        }
                    }
            },
            series: [{
                   
                
            }]
        });

            

            // Build the overall status break up chart- end


            // Build the hoursly transaction chart- start
            $('#container2').highcharts({
                            chart: {
                                type: 'column'
                            },
                            title: {
                                text: 'Transactions in last 6 hours'
                            },
                            subtitle: {
                                text: ''
                            },
                            xAxis: {
                                categories: [     
                                '1','2','3','4','5','6','7','8','9','10','11','12'

                            ],
                            title: {
                                    text: 'hours(hr)'
                                },
                            },
                            yAxis: {
                                min: 0,
                                title: {
                                    text: 'transactions'
                                },
                                // max: 200
                            },
                            tooltip: {
                                headerFormat: '<span style="font-size:10px">{point.key} hours</span><table>',
                                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                                '<td style="padding:0"><b>{point.y} </b></td></tr>',
                                footerFormat: '</table>',
                                shared: true,
                                useHTML: true
                            },
                            plotOptions: {
                                column: {
                                    pointPadding: 0.2,
                                    borderWidth: 0
                                }
                            },
                            series: [{
                                name: 'Transactions'
                            }]
                        });
                // Build the hoursly transaction chart- end

            UIHelper.ValidateOS();
            //UIHelper.PrepareOverallStatusChart();
            UIHelper.PrepareHourlyChart();

           
                        

            $("tspan:contains('Highcharts.com')").css('display', 'none');

             $("#btnlobsearch").click(function(){
                $("div#" + currentLOB + "master").pqGrid("refreshDataAndView");
                return false;
             });
            // var
			// int=self.setInterval(function(){$("#divCriticalErrors").pqGrid("refreshDataAndView");UIHelper.GetMessages();},10000);

            });
    



// UIHelper class
var UIHelper = {
    SetCookie: function (name, value) {
        var date = new Date();
        var minutes = SessionTimeOut;
        date.setTime(date.getTime() + (minutes * 60 * 1000));
        $.cookie(name, value, { expires: date, path: '/' });
    },
    DeleteCookie: function (name) {
        $.removeCookie(name, { path: '/' });
    },
    GetCookie: function (name) {
        return $.cookie(name);
    },
    CheckAuthorization: function () {
        if (message == UnAuthorizedCode && document.location.href.substr(document.location.href.lastIndexOf("/") + 1) != "index.html") {
            document.location.href = "../index.html";
        }
    },
    PrepareLOBScreen: function (name) {
        $.ajax({
            url: 'json/lobcolumns.json',
            // url: WCFServiceURL + "LobColumns",
            method: 'get',
            dataType: 'json',
            // data: 'tokenId=' + GetCookie("token"),
            success: function (data) {
                if (data.status == "success") {
                    cols = data.columns;
                    UIHelper.BindLOB(name);
                }
                else {
                    UIHelper.CheckAuthorization(data.message);
                }
            },
            error: function (err) {
                alert('There is an error occured while fetching LOB structure.');
            },
            complete: function () {

            }
        });
    },
    BindLOBDetail: function () {
        gridDetail = $("div#" + currentLOB + "detail").pqGrid({ height: 160,
            dataModel: LOBDetailDataModel,
            colModel: colsDetail,
            title: currentLOB + " detail",
            resizable: false,
            columnBorders: true,
            flexWidth: true,
            scrollModel: {horizontal:false},
	    rowDblClick: function(evt,obj){
		    debugger;
		    var data=obj.dataModel.data[obj.rowIndx];
		   // $("#InputMessage").val(data.inputPayload);
		    //$("#OutputMessage").val(data.outputPayload);
		    //$("#divInputOutputMessage").dialog("open").css('display','inline');
            UIHelper.showXMLPoppup(data.inputPayload,data.outputPayload);
	    }
        });
    },
    BindLOB: function (lob) {
        var grid1 = $("div#" + lob + "master").pqGrid({ width: 950, height: 280,
            dataModel: LOBDataModel,
            colModel: cols,
            title: lob + " master",
            resizable: false,
            columnBorders: true,
            editable: true,
            rowSelect: function (evt, obj) {
                LOBDetailData.length = 0;
                $.each(obj.data[obj.rowIndxPage].detail, function (i, item) {
                    LOBDetailData.push(item);
                });
                UIHelper.BindLOBDetail();
            }
        });
        $("div#" + lob + "master").attr('loaded', 'true');
    },
    SetWelcome: function () {
        var username = UIHelper.GetCookie("user");
        if (username) {
            $("#username").text(username);
		return true;
        }
        else {
            document.location.href = "../index.html";
		return false;
        }
    },
    Logout: function () {    
        $.ajax({
            // url: 'json/logout.json',
            url: WCFServiceURL + "Logout",
            method: 'get',
            dataType: 'json',
            data: 'token=' + UIHelper.GetCookie("token"),
            success: function (response) {
                if (response.status == "success") {
                    UIHelper.DeleteCookie('user');
                    UIHelper.DeleteCookie('token');
                    document.location.href = "../index.html";
                }
            },
            error: function (err) {
                alert('There is an error occured while logout.');
            },
            complete: function () {
              
            }
        });
    },
    GetColor: function (colorName) {
        switch (colorName) {
            case "Success":
                return "Green";
                break;
            case "Errors":
                return "Red";
                break;
            case "Warnings":
                return "#F5F3BA";
                break;
            case "Critical Errors":
                return "#7b040f";
                break;

        }
    },
    PrepareOverallStatusChart: function () {
debugger;
        // $("#imgOSLoading").css('display', 'inline');
        $('#container').highcharts().showLoading();
	var store=$('#OSstores').val();
	if(store==null)
		store='';
        $.ajax({
            // url: 'json/overallstatusbreakup1.json',
            url: WCFServiceURL + "GetOverallStatus",
            method: 'get',
            dataType: 'json',
            async:true,
            data: 'fromDate=' + $('#txtOSfromdate').val() + '&toDate=' + $('#txtOStodate').val() + '&store=' + store,
            success: function (data) {
                var dataArray = new Array();
                $.each(data.summary, function (i, item) {
                    var obj = new Object();
                    obj.name = item.status;
                    var count = 0;
                    $.each(item.detail, function () {
                        count += parseInt(this.count);
                    });
                    obj.y = count;
                    obj.color = UIHelper.GetColor(item.status);
                    obj.detail = item.detail;
                    dataArray.push(obj);
                });
                UIHelper.CreateOverallStatusChart(dataArray);
                $("tspan:contains('Highcharts.com')").css('display', 'none');
            },
            error: function (err) {
                alert('There is an error occured while creating chart.');
            },
            complete: function () {
                // var display = $("#txtOStodate").val() == "" ? "none" :
				// "inline";
                $("#imgOSLoading").css('display', 'none');
                // $("#imgOSundo").css('display', display);
                $('#container').highcharts().hideLoading();
            }
        });
    },
    CreateOverallStatusChart: function (lob) {
        var title = $('#txtOSfromdate').val() != "" ? "Status from " + $('#txtOSfromdate').val() + " to " + $('#txtOStodate').val() : "Status for last two days";
        var subtitle = "";
        if ($("#OSlob").val() != "All LOB") {
            subtitle = "LOB: " + $("#OSlob").val();
        }
        var chart = $('#container').highcharts();
        chart.series[0].setData(lob);
        chart.setTitle({ text: title }, { text: subtitle });
    },
    PrepareOverallStatusBarChart: function (name) {
        // $("#imgOSLoading").css('display', 'inline');
        $('#container').highcharts().showLoading();
        $.ajax({
            // url: 'json/overallstatusbreakup.json',
            url: WCFServiceURL + "GetOverallStatus",
            method: 'get',
            dataType: 'json',
            data: '',
            success: function (data) {
                UIHelper.CreateOverallStatusBarChart(data.lob, name);
                $("tspan:contains('Highcharts.com')").css('display', 'none');
            },
            error: function (err) {
                alert('There is an error occured while creating chart.');
            },
            complete: function () {
                // var display = $("#txtOStodate").val() == "" ? "none" :
				// "inline";
                $("#imgOSLoading").css('display', 'none');
                // $("#imgOSundo").css('display', display);
                $('#container').highcharts().hideLoading();
            }
        });
    },
    CreateOverallStatusBarChart: function (data, name) {
        var categories = new Array();
        var values = new Array();
        $.each(data, function () {
        	debugger;
            categories.push(this.name)
            values.push(this.count)
        });
        var title = $('#txtOSfromdate').val() != "" ? "Status from " + $('#txtOSfromdate').val() + " to " + $('#txtOStodate').val() : "Status for last two days";
        var subtitle = ""; // "Back";
        // subtitle = "Status: " + name;
        $("#container").css('display', 'none');
        $("#containerBar").css('display', 'block');
        var chart = $('#containerBar').highcharts();
        chart.series[0].update({ name: name }, false);
        chart.series[0].color = UIHelper.GetColor(name);
        chart.redraw();
        chart.xAxis[0].setCategories(categories);
        chart.series[0].setData(values);
        chart.setTitle({ text: title }, { text: subtitle });
    },
    PrepareHourlyChart: function () {
        // $("#imgOSLoading").css('display', 'inline');
        $('#container2').highcharts().showLoading();
        $.ajax({
            // url: 'json/hourlytransactions.json',
            url: WCFServiceURL + "GetHourlyChart",
            method: 'get',
            dataType: 'json',
            data: 'tokenId=' + UIHelper.GetCookie("token"),
            success: function (data) {
                if (data.status == "success") {
                    var dataArray = new Array();
                    $.each(data.transactions, function (i, item) {
                        dataArray.push(item.transactions);
                    });
                   
                    UIHelper.CreateHourlyChart(dataArray);
                }
                else {
                    UIHelper.CheckAuthorization(data.message)
                }
                $("tspan:contains('Highcharts.com')").css('display', 'none');
            },
            error: function (err) {
                alert('There is an error occured while creating chart.');
            },
            complete: function () {
                // var display = $("#txtOStodate").val() == "" ? "none" :
				// "inline";
                $("#imgOSLoading").css('display', 'none');
                // $("#imgOSundo").css('display', display);
                $('#container2').highcharts().hideLoading();
            }
        });
    },
    CreateHourlyChart: function (data) {
        var chart = $('#container2').highcharts();
        chart.series[0].setData(data);
    },
    PopulateTabs: function () {
        $.ajax({
            // url: 'json/tabs.json',
            url: WCFServiceURL + "GetActiveLOB",
            method: 'get',
            dataType: 'json',
            // data: 'tokenId=' + UIHelper.GetCookie("token"),
            success: function (data) {
            
                if (data.status == "success") {
                    UIHelper.CreateTabs(data.lobs);
                    Tabs=data.lobs;
                    UIHelper.PopulateSMLobDropdown();
                }
                else {
                    UIHelper.CheckAuthorization(data.message)
                }
            },
            error: function (err) {
                alert('There is an error occured while creating Tabs.');
            },
            complete: function () {

            }
        });
    },
    SaveFilter: function (lob, orderId,storeId,inputKeyword, status, fromDate, toDate) {
        var filterObj = $("#" + lob + "filter");
        var filter = new Object();
        filter.orderId = orderId;
        filter.storeId=storeId;
        filter.inputKeyword=inputKeyword;
        filter.status = status;
        filter.fromDate = fromDate;
        filter.toDate = toDate;
        filterObj.val(JSON.stringify(filter));
    },
    LoadFilter: function (lob) {
        var filterObj = $("#" + lob + "filter");
        
        var orderId = ""; var status = "ALL"; var fromDate = ""; var toDate = ""; var storeId=""; var inputKeyword="";
        if (filterObj.val() != "") {
            var filter = jQuery.parseJSON(filterObj.val());
            orderId = filter.orderId;
            inputKeyword=filter.inputKeyword;
            storeId=filter.storeId;
            status = filter.status;
            fromDate = filter.fromDate;
            toDate = filter.toDate;
        }
        $("#txtlobSearch").val(orderId);
        $("#ddlLOBStatus").val(status);
        $("#txtLOBfromdate").val(fromDate);
        $("#txtLOBtodate").val(toDate);
        $("#ddlStores").val(storeId);
        $("#txtInputKeyword").val(inputKeyword);
    },
    CreateTabs: function (tabs) {
        $.each(tabs, function (i, item) {
            $("#tabsDiv").append("<div class='breadcrumb_divider'></div> <a href=\"javascript:UIHelper.LoadTab('" + item.name + "',false);\">" + item.name + "</a>");
            $("#LOBMasterContainer").append("<div id='" + item.name + "master' style='margin-left:15px;' class='lobmastergrid' loaded=false></div><input type='hidden' id='" + item.name + "filter'>");
            $("#LOBDetailContainer").append("<div id='" + item.name + "detail' style='margin-left:15px;width:96% !important;' class='lobdetailgrid'></div>");
        });
    },
    LoadTab: function (name, chartDetail) {
        var newTab = $("#tabsDiv a:contains('" + name + "')");
        UIHelper.EnableDisableTab(name, newTab);
        $("#Dashboard.tabsDiv").css('display', (name == "Dashboard") ? 'inline' : 'none');
        if (name != "Dashboard") {
            currentLOB = name;
            if ($("div#" + currentLOB + "master").attr('loaded') == "false" || chartDetail == true) {
                UIHelper.PrepareLOBScreen(currentLOB);
            }
            $("div.lobmastergrid").css('display', 'none');
            $("div#" + currentLOB + "master").css('display', 'block');
            $("div.lobdetailgrid").css('display', 'none');
            $("div#" + currentLOB + "detail").css('display', 'block');

        }
        $("#LOB.tabsDiv").css('display', (name == "Dashboard") ? 'none' : 'inline');

    },
    EnableDisableTab: function (name, newTab) {
        var currentTab = $("#tabsDiv a.current");
        currentTab.removeClass('current');
        currentTab.attr("href", "javascript:UIHelper.LoadTab('" + currentTab.text() + "',false)");
        if (currentTab.text() != "Dashboard")
            UIHelper.SaveFilter(currentTab.text(), $("#txtlobSearch").val(), $("#ddlStores").val(),$("#txtInputKeyword").val(),$("#ddlLOBStatus").val(), $("#txtLOBfromdate").val(), $("#txtLOBtodate").val());
        if (name != 'Dashboard')
            UIHelper.LoadFilter(name);
        newTab.removeAttr('href');
        newTab.addClass('current');
    },
    ValidateOS: function () {
    	if($("#txtOSfromdate").val()!="" && $("#txtOStodate").val()!="")
    	{
            	var dt1= new Date($("#txtOSfromdate").val());
    		var dt2= new Date($("#txtOStodate").val());
    		if(dt1>dt2)
    		{
    			alert('From date must be less than to date');
    			$("#txtOSfromdate").focus();
    			return false;
    		}
    	}
    	else
    	{
    		alert('Both dates are required.');
    	}
        $("#containerBar").css('display', 'none');
        $("#container").css('display', 'block');
        UIHelper.PrepareOverallStatusChart();
        return false;
    },
    ResetOS: function () {
        $("#txtOSfromdate").val('');
        $("#txtOStodate").val('');
        $('#OSlob').prop('selectedIndex', 0);
        UIHelper.PrepareOverallStatusChart();
    },
    ResetLOB: function () {
        $("#txtLOBfromdate").val('');
        $("#txtLOBtodate").val('');
        //$('#ddlLOBStatus').prop('selectedIndex', 0);
        //UIHelper.PrepareLOBScreen(currentLOB);
    },
    ReloadOS: function () {
        $("#containerBar").css('display', 'none');
        $("#container").css('display', 'block');
        UIHelper.PrepareOverallStatusChart();
    },
    ReloadHC: function () {
        UIHelper.PrepareHourlyChart();
    },
    ExportToExcel: function () {
    	

    	var dt = new Date();
        var day = dt.getDate();
        var month = dt.getMonth() + 1;
        var year = dt.getFullYear();
        var hour = dt.getHours();
        var mins = dt.getMinutes();
        var postfix = day + "." + month + "." + year + "_" + hour + "." + mins;

    	var currentTab= currentLOB + 'master';
    	
        $("#divExport").html("<b><u>" + currentLOB + "</u></b><br><br>" + $('#' + currentTab + ' div.pq-grid-right').html());
        $("#divExport div span:eq(0)").remove();
        $("#divExport div span div.pq-grid-col-resize-handle").remove();
        $("#divExport div span div.pq-grid-header-table-div")[0].innerText = "S. No";
        $("#divExport div span div.pq-grid-header-table-div").css('font-weight', 'bold');
        $("#divExport .pq-row-hidden").remove();
        var a = document.getElementById('explink');
        var data_type = 'data:application/vnd.ms-excel';
        var table_html = encodeURIComponent($('#divExport').html()); 
        a.href = data_type + ', ' + table_html;        
        a.download = 'exported_table_' + postfix + '.xls';      
        a.click();
        //window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#divExport').html()));
    },
    ValidateMessage: function (frm) {
        if (frm.txtmessage.value != '' && frm.txtmessage.value != 'Enter Message') {
            UIHelper.InsertMessage(frm.txtmessage.value)
        }
        return false;
    },
    InsertMessage: function (message) {
        $.ajax({
            // url: 'json/tabs.json',
            url: WCFServiceURL + "PostMessage",
            method: 'get',
            dataType: 'json',
            // data: 'tokenId=' + UIHelper.get GetToken() + '&message=' +
			// message,
            data: 'tokenId=' + UIHelper.GetCookie("token") + '&message=' + message,
            success: function (data) {
                if (data.status == "success") {
                    UIHelper.GetMessages();
                }
                else {
                    UIHelper.CheckAuthorization(data.message)
                }
            },
            error: function (err) {
                alert('There is an error occured while inserting message.');
            },
            complete: function () {

            }
        });
    },
    GetMessages: function () {
        $.ajax({
            // url: 'json/messages.json',
            url: WCFServiceURL + "GetMessages",
            method: 'get',
            dataType: 'json',
            data: 'tokenId=' + UIHelper.GetCookie("token"),
            success: function (data) {
                if (data.status == "success") {
                    $("#messageDiv").empty();
                    $.each(data.messages, function () {
                        $("#messageDiv").append("<div class='message'><p>" + this.message + "</p><p><strong>" + this.username + " (" + this.messageDate + ")</strong></p></div>")
                    });
                }
                else {
                    UIHelper.CheckAuthorization(data.message)
                }
            },
            error: function (err) {
                alert('There is an error occured while retreiving messages Tabs.');
            },
            complete: function () {

            }
        });
    },
    BindUserGrid:function(){
            var grid3 = $("#divUsers").pqGrid({ height: 250,width: 550,
                dataModel: UserDataModel,                
                colModel: [

// { "dataIndx": "lob", "title": " ", "width": 100,
// render: function (ui) {
// var rowData = ui.rowData,
// dataIndx = ui.dataIndx,
// cellData = rowData[dataIndx];
// return "<a href=\"javascript:UIHelper.SaveFilter('" + cellData +
// "','','Critical Errors','','');UIHelper.LoadTab('" + cellData + "',true);\"
// title='view complete list of " + cellData + " critical issues'><font
// color='blue'>" + cellData + "</font></a>";
// }
// },
                            { "dataIndx": "id", "title": "ID", "width": 100 },	
					        { "dataIndx": "username", "title": "Username", "width": 100},					        
					        { "dataIndx": "email", "title": "Email", "width": 150 },
					        { "dataIndx": "isadmin", "title": "Admin", "width": 100 },
					        { "dataIndx": "status", "title": "Status", "width": 100 },
					        
                ],
                title: "",
                resizable: false,
                columnBorders: true,
                editable: false,
                topVisible: false,
                numberCell: false,
                bottomVisible: false,
                flexWidth: true,
                scrollModel: {autoFit: true,lastColumn: 'auto'},
                rowSelect: function (evt, obj) {
                    // $("#ddlLOBStatus").val("Critical Errors");
                    // LoadTab(obj.data[obj.rowIndx].lob, true);

                },
                rowDblClick: function(evt,obj){
                   UIHelper.EmptyUserFormControls();   
                   UIHelper.PopulateLOBCheckboxes();
                    var currRow=obj.dataModel.data[obj.rowIndx]
                    $("#Username").val(currRow.username);
                    $("#Password").val(currRow.password);
                    UserPwd=currRow.password;
                    $("#Email").val(currRow.email);
                    $("#UserID").val(currRow.id);                    
                    $("#lblUserStatus").css('display','inline');
                    if(currRow.status=='Active')
                        $("#chkStatus").prop('checked',true);
                    else
                        $("#chkStatus").prop('checked',false);
                    
                    //$("#chkStatus").prop('checked', (currRow.status=='Active')); 
                    debugger;   
                    if(currRow.roles.length>0){                    
                        var matchCheckbox=null;
                        var temp= $.each(currRow.roles, function (i, item) {
                            var temp2=$(".UserAccess").each(function(){
                                    if (item.lobid ==$(this).val())
                                    {
                                        matchCheckbox=this;
                                        return;
                                    }
                            });
                            if(matchCheckbox)
                                $(matchCheckbox).attr('checked',true);
                        });


// debugger;

// if(item.lobid==$(chkItem).val())
// {
// $(chkItem).attr('checked',true);
// }
// });
                       
                    }
                    $("#divUserForm").dialog("open").css('display','inline');
                    $('.my-dialog2 .ui-button-text:contains(Create User)').text('Update User');
                },
            });          

            gridUser = grid3;
    },
    BindTabGrid:function(){
            var grid2 = $("#divTabs").pqGrid({ height: 200,width: 560,
                dataModel: TabDataModel,                
                colModel: [

                            { "dataIndx": "id", "title": "ID", "width": 100 },
					        { "dataIndx": "name", "title": "Tab name", "width": 200 },	       
					        { "dataIndx": "status", "title": "Status", "width": 150 }
					        
                ],
                title: "",
                resizable: false,
                columnBorders: true,
                editable: false,
                topVisible: false,
                numberCell: false,
                bottomVisible: false,
                rowDblClick: function(evt,obj){
                debugger;
                    UIHelper.EmptyTabFormControls();       
                    var currRow=obj.dataModel.data[obj.rowIndx]
                    $("#TabName").val(currRow.name);
                    $("#TabID").val(currRow.id);                    
                    $("#lblTabStatus").css('display','inline');
                    if(currRow.status=='Active')
                        $("#TabStatus").attr('checked',true)
                    else
                        $("#TabStatus").removeAttr('checked');
                    $("#TabStatus").prop('checked', (currRow.status=='Active'));    
                    $("#divTabForm").dialog("open").css('display','inline');
                    $('.my-dialog .ui-button-text:contains(Save)').text('Update');
                },
                rowSelect: function (evt, obj) {
                    // $("#ddlLOBStatus").val("Critical Errors");
                    // LoadTab(obj.data[obj.rowIndx].lob, true);

                }
            });          

            gridTab = grid2;
    },
    getGridRowIndx:function(gridCurrent) {
        //var $grid = $("#grid_render_cells");

        //var obj = $grid.pqGrid("getSelection");
        //debugger;
    	var grid2 = $("#divTabs");
        var arr = gridCurrent.pqGrid("selection", { type: 'row', method: 'getSelection' });
        if (arr && arr.length > 0) {
            var rowIndx = arr[0].rowIndx;

            //if (rowIndx != null && colIndx == null) {
            return rowIndx;
        }
        else {
            alert("Kindly make a selection");
            return null;
        }
    },
    //delete Row.
    deleteGridRow:function(option) {
        debugger;
        var gridCurrent ;
        var serviceName ;
        var formId;
        switch(option)
        {
            case AdminSetupEnum.UserManagement:
                gridCurrent = gridUser;
                serviceName = "User";
                formId = "#divUserForm";
                break;
            case AdminSetupEnum.TabManagement:
                gridCurrent = gridTab;
                serviceName = "Tab";
                formId = "#divTabForm";
                break;
            case AdminSetupEnum.NotificationEmails:
            	gridCurrent = gridNotification;
            	 formId = "#divNFEmails";
            	 serviceName = "NfeMail";
                break;
        }  
        var rowIndx = UIHelper.getGridRowIndx(gridCurrent);
        if (rowIndx != null) {
            var DM = gridCurrent.pqGrid("option", "dataModel");
            var rowID = DM.data[rowIndx].id;
           
            $.ajax({
            	url: WCFServiceURL + "Delete"+serviceName,
                method: 'get',
                dataType: 'json',     
                data: 'tokenId=' + UIHelper.GetCookie("token") + "&id=" + rowID,
                success: function (data) {
                debugger;
                    if (data.status == "success") {
                        $(formId).dialog( "close" );
                        UIHelper.BindTabGrid();
                    }
                    else if (data.status == "error") {
                        alert(serviceName+' could not be deleted.');
                        $("#"+serviceName.toLowerCase()+"name").focus();
                    }
                    else {
                        UIHelper.CheckAuthorization(data.message);
                    }
                    
                },
                error: function (err) {
                    alert('There is an error occured while deleting '+serviceName+'.');
                },
                complete: function () {

                }
            });  
           
            DM.data.splice(rowIndx, 1);
            gridCurrent.pqGrid("refreshDataAndView");
            gridCurrent.pqGrid("setSelection", { rowIndx: rowIndx });
        }
    },
   
     BindNFEmailsGrid:function(){
            var grid5 = $("#divNFEmails").pqGrid({ height: 250,width: 670,
                dataModel: NFEmailsDataModel,                
                colModel: [

                           	{ "dataIndx": "id", "title": "ID", "width": 100 },	
					        { "dataIndx": "lobid", "title": "Business Object", "width": 150 },	       
					        { "dataIndx": "event", "title": "Event", "width": 150 },
                            { "dataIndx": "emails", "title": "Emails", "width": 350 }
					        
                ],
                title: "",
                resizable: false,
                columnBorders: true,
                editable: false,
                topVisible: false,
                numberCell: false,
                bottomVisible: false,
                rowDblClick: function(evt,obj){
                    UIHelper.EmptyNFFormControls();       
                    var currRow=obj.dataModel.data[obj.rowIndx]
                    debugger;
                    $("#NFLob").val(currRow.lobid);    
                    currentNFlob=currRow.lobid;
                    UIHelper.GetBusinessObjectList("NFLob");
                    $("#NFID").val(currRow.id);                    
                    $("#Event").val(currRow.event);                    
                    $("#NFEmails").val(currRow.emails);                    
                    $("#divNFEmailForm").dialog("open").css('display','inline');
                    $('.my-dialog3 .ui-button-text:contains(Save)').text('Update');
                },
                rowSelect: function (evt, obj) {
                    // $("#ddlLOBStatus").val("Critical Errors");
                    // LoadTab(obj.data[obj.rowIndx].lob, true);

                }
            });          

            gridNotification=grid5;
    },
    AdminSetup: function(){  
     var IsAdmin = UIHelper.GetCookie("typ");
        if (IsAdmin && IsAdmin=="true") {
            $("#admDiv").css('display','inline');
            $("#admUL").append("<li><a href='javascript:UIHelper.ShowAdminPopup(AdminSetupEnum.UserManagement);'>User Management</a></li><li class=''><a href='javascript:UIHelper.ShowAdminPopup(2);'>Tab Management (Business Object)</a></li><li class=''><a href='javascript:UIHelper.ShowAdminPopup(AdminSetupEnum.NotificationEmails);'>Emails Notification Setup</a></li>");
        }    
    },
    ShowAdminPopup:function(option){
        UIHelper.HideAdminPanelDivs();
        switch(option)
        {
            case AdminSetupEnum.UserManagement:
                $("#divUserPanel").dialog({
                    height: 500,
                    width: 600,
                    modal: true,
                    resizable: false,
                    
                }).css('display','inline');  
                UIHelper.BindUserGrid();
                break;
            case AdminSetupEnum.TabManagement:
                $("#divTabPanel").dialog({
                    height: 400,
                    width: 590,
                    resizable: false,
                    modal: true,
                    // Binding the close event for dialogbox of Tab Management
					// Page-Called Populate Tab method-Khurram
                    close:function(event,ui){
                	$("#tabsDiv").html("");
                	$("#tabsDiv").html("<a class='current'>Dashboard</a>");
                	UIHelper.PopulateTabs();
                }
                }).css('display','inline'); ;  
                UIHelper.BindTabGrid();
                break;
            case AdminSetupEnum.NotificationEmails:
                $("#divEmailsPanel").dialog({
                    height: 550,
                    width: 700,
                    resizable: false,
                    modal: true
                }).css('display','inline');  
                UIHelper.BindNFEmailsGrid();
                break;

        }
         
    },
    ShowUserPopup:function(option){
        UIHelper.HideUserPanelDivs();
        switch(option)
        {
            case UserSetupEnum.ChangePassword:
            	UIHelper.EmptyChangePasswordControls();
            	$("#divUserChangePassword").dialog("open").css('display','inline');
                break;
            case UserSetupEnum.UpdateProfile:
            	UIHelper.PopulateUpdateProfileControls();
            	$("#divUserUpdateProfile").dialog("open").css('display','inline');
                break;
        }
         
    },
    HideAdminPanelDivs:function(){
       $("#divEmailsPanel").css('display','none');   
       $("#divUserPanel").css('display','none');
       $("#divTabPanel").css('display','none');
    
    },
    HideUserPanelDivs:function(){
        $("#divUserChangePassword").css('display','none');   
        $("#divUserUpdateProfile").css('display','none');
    },
    LoadEmails:function(){
        $.ajax({
            // url: 'json/emails.json',
            url: WCFServiceURL + "GetEmails",
            method: 'get',
            dataType: 'json',
            // data: 'tokenId=' + UIHelper.GetCookie("token"),
            success: function (data) {
                if (data.status == "success") {                    
                    $("#txtemails").val(data.emails);
                }
                else {
                    UIHelper.CheckAuthorization(data.message)
                }
            },
            error: function (err) {
                alert('There is an error occured while retreiving notification emails.');
            },
            complete: function () {

            }
        });
    },
    SaveEmails:function(){
        $.ajax({
            // url: 'json/saveemails.json',
            url: WCFServiceURL + "SaveEmail",
            method: 'get',
            dataType: 'json',
            // data: 'tokenId=' + UIHelper.GetCookie("token"),
            success: function (data) {
                if (data.status == "success") {                    
                    alert('Notification emails have been updated successfully.');
                    UIHelper.HidePopup(AdminSetupEnum.NotificationEmails);
                }
                else {
                    UIHelper.CheckAuthorization(data.message)
                }
            },
            error: function (err) {
                alert('There is an error occured while saving notification emails.');
            },
            complete: function () {

            }
        });
    },
    HidePopup:function(option){
        switch(option)
        {
            case AdminSetupEnum.UserManagement:
                $("#divUserPanel").dialog("close");
                break;
            case AdminSetupEnum.TabManagement:
                $("#divTabPanel").dialog("close");
                break;
            case AdminSetupEnum.NotificationEmails:
                $("#divEmailsPanel").dialog("close");
                break;
        }         
    },
    EmptyTabFormControls: function(){
        $("#TabName").val('');
        $("#TabID").val('');                    
    },
    EmptyNFFormControls:function(){
    // alert('check......')
        $("#NFLID").val('');
        $("#NFLob").val('');
        
        $("#Event").val('');  
        $("#NFEmails").val('');
    },
    EmptyUserFormControls:function(){
        $("#Username").val('');
        $("#Password").val('');
        $("#Email").val('');
        $("#UserID").val('');
    },
    EmptyChangePasswordControls:function(){
        $("#CurrentPassword").val('');
        $("#NewPassword").val('');
        $("#ConfPassword").val('');
    },
    PopulateUpdateProfileControls:function(){
    	 var loggedUserName = $.cookie("user");
   	     $("#Pusername").val(loggedUserName);
        $.ajax({
            url: WCFServiceURL + "GetProfile",
            method: 'get',
            dataType: 'json',
            data: 'tokenId=' + UIHelper.GetCookie("token")+ "&username=" + loggedUserName,
            success: function (data) {
                if (data.status == "success") {                    
                     $("#Phone").val(data.user.phone);
                     $("#Pemail").val(data.user.email);
                     $("#Address").val(data.user.address);
                     
                }
                else {
                    UIHelper.CheckAuthorization(data.message)
                }
            },
            error: function (err) {
                alert('There is an error occured while getting user profile.');
            },
            complete: function () {

            }
        });
        
    },
    PopulateLOBCheckboxes:function(){
         $("#ulLOB").empty();
         var temp= $.each(Tabs, function (i, item) {
            $("#ulLOB").append("<li><input type='checkbox' id='chkUserAccess' class='UserAccess' value=" + item.id + " />" + item.name + "</li>");
         });
    },
    PopulateSMLobDropdown:function(){
        $("#SMLob").empty();
        var temp= $.each(Tabs, function (i, item) {
            $("#SMLob").append("<option value='" + item.id + "'>" + item.name + "</option>");
         });
    },
    PopulateStates:function(){
        $.ajax({
            // url: 'json/statemachine.json',
            url: WCFServiceURL + "GetStateMachine",
            method: 'get',
            dataType: 'json',
            // data: 'tokenId=' + UIHelper.GetCookie("token"),
            success: function (data) {
                if (data.status == "success") {                    
                     $("#tblStates").empty();
                     $("#tblStates").append("<tr><td colspan='2' align='left' style='padding-left:20px;' height=30><b>Business Object: " + data.lob + ", ID: " + data.id + ", Time Consumed: " + data.time + "</b></td></tr>");
                     $.each(data.states, function (i, item) {
                        if(i>0)
                            $("#tblStates").append("<tr><td align='right' style='padding-right:10px;'><img src='images/arrow_down.png' width=30 height=20></td><td>&nbsp;</td></tr>");
                        if(item.currstate=="1")
                            $("#tblStates").append("<tr><td align='right' style='padding-right:10px;height:40px;' width='35%'><div class='circleGreen'></div></td><td align='left'>" + item.state + " (Current State)</td></tr>");
                        else
                            $("#tblStates").append("<tr><td align='right' style='padding-right:10px;height:40px;'><div class='circleBlue'></div></td><td align='left'>" + item.state + "</td></tr>");
                        
                     });
                }
                else {
                    UIHelper.CheckAuthorization(data.message)
                }
            },
            error: function (err) {
                alert('There is an error occured while getting states.');
            },
            complete: function () {

            }
        });
    },
    GetCheckedLOBs:function(){
    debugger;
        var selectedLobs
        selectedLobs='';
        var temp2=$(".UserAccess:checked").each(function(){
            selectedLobs+= $(this).val() + ",";            
        });
        if(selectedLobs!='')
        {
            selectedLobs=selectedLobs.substr(0,selectedLobs.length-1);   
        }
        // alert(selectedLobs);
        return selectedLobs;
    },
    // Adding generic method for getting business Object List-Khurram
    GetBusinessObjectList:function(elementName){
    	$.ajax({
            // url: WCFServiceURL + "GetBizObject",
    		url: WCFServiceURL + "GetActiveLOB",
            method: 'get',
            dataType: 'json',                           
            success: function (data) {
                if (data.status == "success") {
                	$('#'+elementName).empty();	
                  $(data.lobs).each(function()
                			 {
                			     var option = $('<option />');
                			     option.attr('value', this.id).text(this.name);
                			     if (elementName=="NFLob" && currentNFlob.trim()==this.name.trim())
                			     {
                			    	 option.attr('selected','selected');
                			     }
                			     $('#'+ elementName).append(option);
                			 });
                }
            
            },
            error: function (err) {
                alert('There is an error occured while loading business objects.');
            },
            complete: function () {
            	if (elementName=='NFLob1'){
            	    UIHelper.LoadGraphData();
                 
            }
            }
        });  
},
//Adding the Performance Graph..Dynamically binding the X-Axis -Categories and Seris Name & Data List.-Khurram
GeneratePerformanceGraph:function(categoriesData,seriesData){
	chart = new Highcharts.Chart({
		chart: {
        renderTo: 'container3',
        type: 'column',
        height: 200,
    },
    loading: {
              labelStyle: {
              backgroundImage: 'url("loading.gif")'

          }
},
    title: {
        text: 'WCS-ESB-SAP System(s)',
        x: -20 // center
    },
    xAxis: {
        type: 'datetime',
        labels: {
    	staggerLines: 1,
    	overflow: 'justify',
            style: {
                fontSize: '12px'
            }
    	},
            title:{ text:'Date(s)'
    },
        
        categories: categoriesData,
        max: 5,
    },
    yAxis: {
	labels:{
formatter: function() {

	    	
            return this.value;
        }

},

 tickInterval: 30,
        title: {
            text: 'Millisecond(s)'
        },
stackLabels: {
                    enabled: true,
                    style: {
                        fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    }
                }
        
    },
    tooltip: {
       formatter: function() {
                    return '<b>'+ this.x +'</b><br/>'+
                        this.series.name +': '+ this.y +'<br/>'+
                        'Total: '+ this.point.stackTotal;
                }
    },
    

    plotOptions: {
        column:{   
	stacking: 'normal'
}
     },
     scrollbar: {
         enabled: true
     },
    series:[]

});
	    
},
//Adding Ajax Call for getting the Performance Graph Data from Server Side.
LoadGraphData:function(){
	
	 var businessObjectVal=$('#NFLob1').val();
	 var noOfDaysVal = $('#noOfDays').val();
	$.ajax({
		 url: WCFServiceURL + "GetPerfGraph",
	        type: "GET",
	        dataType: "json",
	        data: 'name=' + businessObjectVal + '&days=' + noOfDaysVal,
	        success: function(data) {
			if (data.status == "success") {
				var len = 0;
				 var catArray = new Array();
	            $.each(data.performance.categories, function (i, item) {
	            	catArray.push(item);
	            });
			  	//alert(catArray);
			    UIHelper.GeneratePerformanceGraph(catArray,'11');
			    var len = data.performance.series.length-1;	
			     $.each(data.performance.series, function (i, item) {
			    	 //	alert(item.name);
			    	 chart.addSeries({
			              name: item.name,
			              data: item.data,
				      index : len	
			            });
			          len--;
				});	

			    $("tspan:contains('Highcharts.com')").css('display', 'none');
			} 
		 
		},
	        
            error: function (err) {
                alert('There is an error occured while loading Graph Data.');
            },
            complete: function () {

            },
         
	        cache: false
	    });
	


    },
    PopulateStores: function () {
        $.ajax({
            // url: 'json/stores.json',
            url: WCFServiceURL + "GetActiveStores",
            method: 'get',
            dataType: 'json',
            //data: 'tokenId=' + UIHelper.GetCookie("token"),
            success: function (data) {
                if (data.status == "success") {
                debugger;
                   var ddl= $("#OSstores");
                   UIHelper.PopulateStoresDropdonws(data.stores,ddl);
                   ddl=$("#ddlStores");
                   UIHelper.PopulateStoresDropdonws(data.stores,ddl);
                }
                else {
                    UIHelper.CheckAuthorization(data.message)
                }
            },
            error: function (err) {
                alert('There is an error occured while populate stores.');
            },
            complete: function () {

            }
        });
    },	
    PopulateStoresDropdonws: function(stores,ddl){    
         ddl.empty();
         ddl.append("<option value=''>All Stores</option>");
         $.each(stores, function (i, item) {
            ddl.append("<option value='" + item.id + "'>" + item.text  + "</option>");
         });
    },
    showXMLPoppup:function(input,output){
        $("#InputMessage").val(input);
		$("#OutputMessage").val(output);
		$("#divInputOutputMessage").dialog("open").css('display','inline');

    }


}
