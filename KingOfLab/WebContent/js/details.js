/*
 * details - v1.0 - 2015-10-24
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
            	  $.post("https://"+address+"/KingOfLab/querytosession.do",{"content": "","key": "redate","menu": "","title":"new arrivals"});
            	  window.location.href="cdcatalog.html";
              }else if(menu=="Home"){
            	  window.location.href="index.html";
              }else{
            	  $.post("https://"+address+"/KingOfLab/querytosession.do",{"content": "","key": "redate","menu": menu,"title":menu});
            	  window.location.href="cdcatalog.html";
              }
          });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	alert(errorThrown); }  
          
        });
	 
	//get product details
	var request1 = $.ajax({  
        url: "https://"+address+"/KingOfLab/getDetails.do",  
        type: "POST",  
        async: false,
        //data:_json,
        dataType: "json",  
        cache: false,  
        success: function (result, textStatus) {
        	var image="<img src='"+result.result[7]+"' class='img-responsive' /><div class='clearfix'></div>";
        	$('.images_3_of_2').append(image);
        	var info="<h3>"+result.result[1]+"</h3><span class='artist'>Artist: <a>"+result.result[2]+"</a></span><br><span class='category'>Category: <a>"+result.result[8]+"</a></span><br><span class='rdate'>Released Date: <a>"+result.result[3]+"</a> </span><br><div class='price'><span class='text'>Price:</span><span class='price-new'>  CAD$ "+result.result[5]+"</span></div><div class='det_nav1'><h4>Quantity:<select name='quantity' id='quantity' class='cdquantity'><option selected=''>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option></select>&nbsp;<span>(<a class='stock' id='stock'>"+result.result[6]+"</a> in stock)</span></h4><div class='btn_form'><a name='buy' id='"+result.result[0]+"' href='checkout.html'>buy</a></div>";
        	$('.span_3_of_2').append(info);
        	var details="<h6>Details</h6><p class='prod-desc'>"+result.result[4]+"</p>";
        	$('.single-bottom1').append(details);
        	
        	$("a[name='buy']").unbind('click').bind('click',function(){
                var cdid=$(this).attr("id");
                var quantity=$("#quantity").find("option:selected").text();
                if(result.result[6]>0){
                	//add item to cookies/session
                     var aj=$.cookie('cart');
                     ajson=jQuery.param({"cdid": cdid,"quantity":quantity,"aj":aj});
                     var request4 = $.ajax({  
                         url: "https://"+address+"/KingOfLab/addtocart.do",  
                         type: "POST",  
                         async: false,
                         data:ajson,
                         dataType: "json",  
                         cache: false,  
                         success: function (result, textStatus) {
                         	 $.cookie("cart", result.cookies);
                         	var cj=$.cookie('cart');
                      		var cjson= jQuery.param({"cjson": cj});
                      		var request5 = $.ajax({
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
                      		alert("Nice Choice!");
                         },  
                         error: function (XMLHttpRequest, textStatus, errorThrown) {
                         	alert(errorThrown); }  
                     }); 
                	
                }else{
                	
                	alert("You Can Not Buy Stock Out Item!");
                	
                }
                
        });
         
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	alert(errorThrown); }  
          
        });
	
	//search product by key words
	$("#search").click(function(){
        var content=$("#content").val();
      	$.post("https://"+address+"/KingOfLab/querytosession.do",{"content": content,"key": "","menu": "","title":content});
        window.location.href="cdcatalog.html";
});
	

});