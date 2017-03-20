/*
 * register - v1.0 - 2015-10-24
 * Copyright (c) 2015 Zhibo Zhang(zhibozhang@cmail.carleton.ca)
 * Please contact the owner before you use.
 */
$(document).ready(function(){ 
    var address="localhost:8443";
    
    $("#register-submit").click(function(){
    	
    	var password=$('#password').val();
        var repassword=$('#repassword').val();
        var fname=$('#fname').val();
        var lname=$('#lname').val();
        var email=$('#email').val();
        var sex;
        
        if($("#male").prop("checked")){
        	sex="male";}
        else{
        	sex="female";
        }
        
        var state=$("input[name='checkbox1']").prop("checked");
        
        if(password==repassword && state){
        	var _json = jQuery.param({ "password": password,"email": email,"firstname": fname,"lastname": lname,"sex":sex});  
            var request = $.ajax({  
            	url: "https://"+address+"/KingOfLab/register.do",
                data:_json,
                type: "POST",  
                async: false,
                dataType: "json",  
                cache: false,  
                success: function (result, textStatus) {
                	if (result.code=="success")
                    {
                		//window.location.href="index.html";
                		history.go(-1);
                    }else{
                  alert(result.code);  }
                	
                },  
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(errorThrown);
                    }  
            });

        }else if(password==repassword && state==false){
        	alert("Pleace Check The Aggreement!");
        }else{
            alert("Password Not Match!");
          }
    });
    
    $("#login").click(function(){ 
        var _json = jQuery.param({ "email": $("#email1").val(),"password": $("#password1").val()});  
        var request1 = $.ajax({  
        	
            url: "https://"+address+"/KingOfLab/login.do",  
            type: "POST",  
            async: false,  
            data: _json, 
            dataType: "json",  
            cache: false,  
            success: function (result, textStatus) {
                if (result.code=="success")
                  {
                    //window.location.href="index.html" 
                    history.go(-1);
                  }else{
                alert(result.code);  }
                
            },  
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown); }  
        }); 
        
});
});