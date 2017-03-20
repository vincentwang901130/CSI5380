/*
 * confirmaddress - v1.0 - 2015-10-24
 * Copyright (c) 2015 Zhibo Zhang(zhibozhang@cmail.carleton.ca)
 * Please contact the owner before you use.
 */
$(document).ready(function(){ 
	var address="localhost:8443";
	var request1 = $.ajax({
        url: "https://"+address+"/KingOfLab/getaddresstoconfirm.do",  
        type: "POST",  
        async: false, 
        dataType: "json",
        cache: false,  
        success: function (result, textStatus) {
        	$(".adressform").append("<div class='sky-form'><div class='sky_form1'><ul><li><p class='addr'>"+result.address.fullname+"</p><p class='addr'>"+result.address.addrline1+"</p><p class='addr'>"+result.address.addrline2+"</p><p class='addr'>"+result.address.city+","+result.address.province+"</p><p class='addr'>"+result.address.zipcode+"</p><p class='addr'>"+result.address.phone+"</p></label></li><div class='clearfix'></div></ul></div></div>");
        	$(".adressform").append("<div><input type='submit' value='Edit Address' id='eidtaddress'></div>");
        	$(".billform").append("<div class='sky-form'><div class='sky_form1'><ul><li><p class='addr'>"+result.bill.fullname+"</p><p class='addr'>"+result.bill.addrline1+"</p><p class='addr'>"+result.bill.addrline2+"</p><p class='addr'>"+result.bill.city+","+result.bill.province+"</p><p class='addr'>"+result.bill.zipcode+"</p></label></li><div class='clearfix'></div></ul></div></div>");	
        	$(".billform").append("<div><input type='submit' value='Edit Address' id='eidtbill'></div>");
        	
        	$("input[id='eidtaddress']").unbind('click').bind('click',function(){
       		 $(".adressform").children().remove();
       		 var newaddress="<div><label><input placeholder='Full Name :' type='text' tabindex='1' required autofocus name='afname'></label></div><div><label><input placeholder='Address Line 1 : Street address, P.O. box' type='text' tabindex='2' required autofocus name='addr1'></label></div><div><label><input placeholder='Address Line 2 (Optional) : Apartment, suit, building, floor,etc.' type='text' tabindex='3' required name='addr2'</label></div><div><label><input placeholder='City :' type='text' tabindex='4' required name='city'></label></div><div><select name='province' id='province' style='color:gray'><option disabled selected>State/Province/Region :</option><option>Alberta</option><option>British Columbia</option><option>Manitoba</option><option>Newfoundland and Labrador</option><option>New Brunswick</option><option>Northwest Territories7</option><option>Nova Scotia8</option><option>Nunavut</option><option>Ontario</option><option>Prince Edward Island</option><option>Quebec</option><option>Saskatchewan</option><option>Yukon</option></select></div><div><label><input placeholder='Postal Code/ZIP :' type='text' tabindex='5' required name='zipcode'></label></div><div><label><input placeholder='Telephone Number :' type='text' tabindex='6' required name='tele'></label></div></div></div>";
         		$(".adressform").append(newaddress);
         		$(".adressform").append("<div><input type='submit' value='SAVE' id='save'></div>");

       		 $("input[id='save']").unbind('click').bind('click',function(){
       			var afname=$("input[name='afname']").val();
              		var addr1=$("input[name='addr1']").val();
              		var addr2=$("input[name='addr2']").val();
              		var city=$("input[name='city']").val();
              		var province=$("select option:selected").val();
              		var zipcode=$("input[name='zipcode']").val();
              		var tele=$("input[name='tele']").val();
              		var ujson= jQuery.param({"fullname":afname,"addr1":addr1,"addr2":addr2,"city":city,"province":province,"zipcode":zipcode,"phone":tele});
       		 	var request2 = $.ajax({
       		        url: "https://"+address+"/KingOfLab/editaddress.do",  
       		        type: "POST",  
       		        async: false,
       		        data:ujson,
       	       	    dataType: "json",   
       		        cache: false,  
       		        success: function (result, textStatus) {
       		        	$(".adressform").children().remove();
       		        	$(".adressform").append("<div class='sky-form'><div class='sky_form1'><ul><li><p class='addr'>"+result.address.fullname+"</p><p class='addr'>"+result.address.addrline1+"</p><p class='addr'>"+result.address.addrline2+"</p><p class='addr'>"+result.address.city+","+result.address.province+"</p><p class='addr'>"+result.address.zipcode+"</p><p class='addr'>"+result.address.phone+"</p></label></li><div class='clearfix'></div></ul></div></div>");
       		        	$(".adressform").append("<div><input type='submit' value='Edit Address' id='eidtaddress'></div>");
       		        },  
       		        error: function (XMLHttpRequest, textStatus, errorThrown) {
       		        	alert(errorThrown); }  
       		    });
       		 		
       		});
       		 
       	 });
       	 
        	$("input[id='eidtbill']").unbind('click').bind('click',function(){
        		$(".billform").children().remove();
        		 var newbilladdress="<div><label><input placeholder='Full Name :' type='text' tabindex='1' required autofocus name='afname'></label></div><div><label><input placeholder='Address Line 1 : Street address, P.O. box' type='text' tabindex='2' required autofocus name='addr1'></label></div><div><label><input placeholder='Address Line 2 (Optional) : Apartment, suit, building, floor,etc.' type='text' tabindex='3' required name='addr2'</label></div><div><label><input placeholder='City :' type='text' tabindex='4' required name='city'></label></div><div><select name='province' id='province' style='color:gray'><option disabled selected>State/Province/Region :</option><option>Alberta</option><option>British Columbia</option><option>Manitoba</option><option>Newfoundland and Labrador</option><option>New Brunswick</option><option>Northwest Territories7</option><option>Nova Scotia8</option><option>Nunavut</option><option>Ontario</option><option>Prince Edward Island</option><option>Quebec</option><option>Saskatchewan</option><option>Yukon</option></select></div><div><label><input placeholder='Postal Code/ZIP :' type='text' tabindex='5' required name='zipcode'></label></div><div></div></div>";
          		$(".billform").append(newbilladdress); 
          		$(".billform").append("<div><input type='submit' value='SAVE' id='savebill'></div>");

       		 $("input[id='savebill']").unbind('click').bind('click',function(){
       			var afname=$("input[name='afname']").val();
       	       	var addr1=$("input[name='addr1']").val();
       	       	var addr2=$("input[name='addr2']").val();
       	       	var city=$("input[name='city']").val();
       	       	var province=$("select option:selected").val();
       	       	var zipcode=$("input[name='zipcode']").val();
       	       	var ujson= jQuery.param({"fullname":afname,"addr1":addr1,"addr2":addr2,"city":city,"province":province,"zipcode":zipcode});
       		 	var request2 = $.ajax({
       		        url: "https://"+address+"/KingOfLab/editbilladdress.do",  
       		        type: "POST",  
       		        async: false,
       		        data:ujson,
       	       	    dataType: "json",
       		        cache: false,  
       		        success: function (result, textStatus) {
       		        	$(".billform").children().remove();
       				 	//$("input[id='savebill']").attr("id","editbill");
       		        	$(".billform").append("<div class='sky-form'><div class='sky_form1'><ul><li><p class='addr'>"+result.bill.fullname+"</p><p class='addr'>"+result.bill.addrline1+"</p><p class='addr'>"+result.bill.addrline2+"</p><p class='addr'>"+result.bill.city+","+result.bill.province+"</p><p class='addr'>"+result.bill.zipcode+"</p></label></li><div class='clearfix'></div></ul></div></div>");	
       		        	$(".billform").append("<div><input type='submit' value='Edit Address' id='eidtbill'></div>");
       		        },  
       		        error: function (XMLHttpRequest, textStatus, errorThrown) {
       		        	alert(errorThrown); }  
       		    });
       		 		
       		});
        		
       	 });
       	
        },  
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	alert(errorThrown); }  
    });
	
	 
});