/*
 * index - v1.0 - 2015-10-24
 * Copyright (c) 2015 Zhibo Zhang(zhibozhang@cmail.carleton.ca)
 * Please contact the owner before you use.
 */
$(document).ready(function(){ 
	var address="localhost:8443";
	//maintain session
	var request = $.ajax({  
        url: "https://"+address+"/KingOfLab/keepsession.do",  
        type: "POST",  
        async: false,
        dataType: "json",  
        cache: false,  
        success: function (result, textStatus) {
        	while(result!=null){
        	$(".login").remove();
        	var accountinfo="<div class='accountinfo'><span>Welcome back, <a class='fisrtname' style='text-transform: capitalize'>"+result.account.fname+"</a> !</span></div>";
        	$(".log").append(accountinfo);
        	break;
        	}
        },  
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	alert(errorThrown); }  
    });
 
    if ($.cookie("rmbUser") == "true") { 
        $("#checkbox").prop("checked", true); 
        $("#email").val($.cookie("username")); 
        $("#password").val($.cookie("password")); 
    } 
    $("#login").click(function(){ 
    	//login
        if ($("#checkbox").prop("checked")) { 
        var username = $("#email").val(); 
        var password = $("#password").val(); 
        $.cookie("rmbUser", "true", { expires: 7 }); //store a 7-days cookies
        $.cookie("username", username, { expires: 7 }); 
        $.cookie("password", password, { expires: 7 }); 
        }else{ 
        $.cookie("rmbUser", "false", { expire: -1 }); 
        $.cookie("username", "", { expires: -1 }); 
        $.cookie("password", "", { expires: -1 }); 
        }
        var _json = jQuery.param({ "email": $("#email").val(),"password": $("#password").val()});  
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
                    window.location.href="index.html" 
                  }else{
                alert(result.code);  }
                
            },  
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown); }  
        }); 
    }); 
      //create cookie store cart items and show quantity and price of the chosen items
    	if ($.cookie('cart')!=null){
    		var cj=$.cookie('cart');
    		var cjson= jQuery.param({"cjson": cj});
    		var request3 = $.ajax({
    	        url: "https://"+address+"/KingOfLab/cookies.do",  
    	        type: "POST",  
    	        async: false,
    	        data:cjson,
    	        dataType: "json",  
    	        cache: false,  
    	        success: function (result, textStatus) {
    	        	$("#simpleCart_total").text("$"+result.totalprice);
    	        	$("#simpleCart_quantity").text(result.quantity);
    	        	
    	        },  
    	        error: function (XMLHttpRequest, textStatus, errorThrown) {
    	        	alert(errorThrown); }  
    	    });
    		
    	}else{
    		$.cookie("cart", "", { expires: 7 });
    		$("#simpleCart_total").text("$0.0");
        	$("#simpleCart_quantity").text(0);
    		
    	}
    	//view shopping cart
    	$("a[name='checkout']").click(function(){
        	
    		if($.cookie('cart')!="" && $.cookie('cart')!=null){
    			window.location.href="checkout.html";
    		}else{
    			alert("You Do Not Have Items In Cart Yet!");
    		}
            
    	});
    	
        
        
    //get Category list
    var request2 = $.ajax({  
        url: "https://"+address+"/KingOfLab/queryCategory.do",  
        type: "POST",  
        async: false,
        dataType: "json",  
        cache: false,  
        success: function (result, textStatus) {
          var decate="<li class='active grid'><a class='color1' href='index.html'>Home</a></li><li class='grid'><a class='color2 tm'>new arrivals</a></li>";
          $('.megamenu').append(decate);
          for(var i=0; i< result.length; i++) 
          {  
           j=i+1;
          	var category="<li class='grid'><a class='color"+(j+2)+" tm'>"+result[i].catename+"</a></li>";
           $('.megamenu').append(category);
          }
          $(".tm").unbind('click').bind('click',function(){
              var menu=$(this).text();
              if(menu=="new arrivals"){
            	  $.post("https://"+address+"/KingOfLab/querytosession.do",{"content": "","key": "redate","menu": "","title":"NewArrivals"});
            	  window.location.href="cdcatalog.html";
            	  //?content=&key=redate&menu=&title=new arrivals";
              }else if(menu=="Home"){
            	  window.location.href="index.html";
              }else{
            	  $.post("https://"+address+"/KingOfLab/querytosession.do",{"content": "","key": "redate","menu": menu,"title":menu});
            	  window.location.href="cdcatalog.html";
            	  //window.location.href="cdcatalog.html?content=&key=&menu="+escape(menu);
              }
             
          });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	alert(errorThrown); }  
          
        }); 
    //get product list
    $("a[name='takelook']").click(function(){
    	
    	//window.location.href="cdcatalog.html?content=&key=redate&menu=";
    	//$.post("cdcatalog.html",{"menu": redate});
    	$.post("https://"+address+"/KingOfLab/querytosession.do",{"content": "","key": "redate","menu":"","title":"new arrivals"});
  	  	window.location.href="cdcatalog.html";
        
});
    //get product list
	$("a[name='bestsellers']").click(function(){
    	
		//window.location.href="cdcatalog.html?content=&key=popular&menu=";
		//$.post("cdcatalog.html",{"menu": popular});
		$.post("https://"+address+"/KingOfLab/querytosession.do",{"content": "","key": "popular","menu":"","title":"best sellers"});
  	  	window.location.href="cdcatalog.html";
        
});
	$("#search").click(function(){
        var content=$("#content").val();
      	$.post("https://"+address+"/KingOfLab/querytosession.do",{"content": content,"key": "","menu": "","title":content});
        window.location.href="cdcatalog.html";
        //?content="+content+"&key=&menu=&title="+content;
});
    
   
});