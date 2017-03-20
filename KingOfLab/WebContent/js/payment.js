/*
 * payment - v1.0 - 2015-10-24
 * Copyright (c) 2015 Zhibo Zhang(zhibozhang@cmail.carleton.ca)
 * Please contact the owner before you use.
 */
$(document).ready(function(){ 
	var address="localhost:8443";
	$("#verifycredit").click(function(){
		var request = $.ajax({  
        	url: "https://"+address+"/KingOfLab/verifycredit.do",
            type: "POST",  
            async: false,
            dataType: "json",  
            cache: false,  
            success: function (result, textStatus) {
            	if (result.code=="success")
                {
            		$.removeCookie('cart');
            		window.location.href="successpay.html";
            		
                }else{
                	window.location.href="unsuccesspay.html"; 
                	}
            	
            },  
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
                }  
        });
		
	});
	
});