/*
 * checkout - v1.0 - 2015-10-24
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
	
	
	if ($.cookie('cart')!=null){
		var cj=$.cookie('cart');
		var cjson= jQuery.param({"cjson": cj});
		var request1 = $.ajax({
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
		
		//show the itemList
		itemList=eval(cj);
		for(i=0;i<itemList.length;i++){
			var item="<div class='cart-header'><div id='"+itemList[i].cdid+"' class='close'> </div><div class='cart-sec simpleCart_shelfItem'><div class='cart-item cyc'><img src='"+itemList[i].imgurl+"' class='img-responsive'/></div><div class='cart-item-info'><h3><a>"+itemList[i].title+"</a><span>by "+itemList[i].artist+"</span></h3><h3>Price: CAD$ "+itemList[i].price+"</h3><ul class='qty'><li><p>Qty : </p><input class='quantityinput' name="+itemList[i].cdid+" type='text' placeholder='1' value='"+itemList[i].quantity+"'/></li></ul><div class='delivery'><span>Delivered in 2-3 bussiness days</span><div class='clearfix'></div></div></div><div class='clearfix'></div></div></div>";
			$(".cart-items").append(item);	
		}
		
		//delete removed items in cookies
		$('.close').unbind('click').bind('click',function(){
			
			$(this).parent().fadeOut('slow', function(){
				
				var cdid=$(this).children('.close').attr("id");
				
				var djson=jQuery.param({"cdid": cdid,"cjson": $.cookie('cart')});
				
				 var request2 = $.ajax({  
                     url: "https://"+address+"/KingOfLab/deleteCartItems.do",  
                     type: "POST",  
                     async: false,
                     data:djson,
                     dataType: "json",  
                     cache: false,  
                     success: function (result, textStatus) {
                    	 
                    if(result.flag!="last"){
                    		
                    	$.cookie("cart", result.cookies); 
                    	
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
                   		
                   	 var shipid=$("input[name='radio']:checked").attr("id");
 					var pjson= jQuery.param({"cjson":$.cookie('cart'),"shipping":shipid});
 						var request8 = $.ajax({
 					        url: "https://"+address+"/KingOfLab/pricedetails.do",  
 					        type: "POST",  
 					        async: false,
 					        data:pjson,
 					        dataType: "json",  
 					        cache: false,  
 					        success: function (result, textStatus) {
 					        	$("#purchase").text(result.purchase);
 					        	$("#tax").text(result.tax);
 					        	$("#subtotal").text(result.subtotal);
 					        	$("#shippingfee").text(result.shippingfee);
 					        	$("#totalfee").text("CAD$ "+result.totalfee);
 					        },  
 					        error: function (XMLHttpRequest, textStatus, errorThrown) {
 					        	alert(errorThrown); }  
 					    });
 						
                    }else{
                    	$.removeCookie('cart');
                    	alert("You Have Nothing In Cart NOW,This Page Will Lead You To Home Page!");
						window.location.href="index.html";
					}
                     },  
                     error: function (XMLHttpRequest, textStatus, errorThrown) {
                     	alert(errorThrown); }  
                 });
				 
				
						
				
					
         });
			
     	
			});
		//modify items quantity in cookies
		$(".quantityinput").bind('click',function(){
			$(this).val("");
			});
		$(".quantityinput").keyup(function(){
			var cdid=$(this).attr("name");
			var quantity=$(this).val();
			if(quantity!=0){
			var mjson=jQuery.param({"cdid": cdid,"cjson": $.cookie('cart'),"quantity":quantity});
			
			 var request4 = $.ajax({  
                 url: "https://"+address+"/KingOfLab/changeCartItems.do",  
                 type: "POST",  
                 async: false,
                 data:mjson,
                 dataType: "json",  
                 cache: false,  
                 success: function (result, textStatus) {
                	 $.cookie("cart", result.cookies, { expires: 7 });
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
                 },  
                 error: function (XMLHttpRequest, textStatus, errorThrown) {
                 	alert(errorThrown); }  
             });
			 
			 var shipid=$("input[name='radio']:checked").attr("id");
				var pjson= jQuery.param({"cjson":$.cookie('cart'),"shipping":shipid});
					var request8 = $.ajax({
				        url: "https://"+address+"/KingOfLab/pricedetails.do",  
				        type: "POST",  
				        async: false,
				        data:pjson,
				        dataType: "json",  
				        cache: false,  
				        success: function (result, textStatus) {
				        	$("#purchase").text(result.purchase);
				        	$("#tax").text(result.tax);
				        	$("#subtotal").text(result.subtotal);
				        	$("#shippingfee").text(result.shippingfee);
				        	$("#totalfee").text("CAD$ "+result.totalfee);
				        	
				        },  
				        error: function (XMLHttpRequest, textStatus, errorThrown) {
				        	alert(errorThrown); }  
				    });
				
			}else{
				
				var djson=jQuery.param({"cdid": cdid,"cjson": $.cookie('cart')});
				
				 var request2 = $.ajax({  
                    url: "https://"+address+"/KingOfLab/deleteCartItems.do",  
                    type: "POST",  
                    async: false,
                    data:djson,
                    dataType: "json",  
                    cache: false,  
                    success: function (result, textStatus) {
                   	 $.cookie("cart", result.cookies, { expires: 7 });
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
                    },  
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                    	alert(errorThrown); }  
                });
				 
				 $(this).parent().parent().parent().parent().remove();
			}
			
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
    var request6 = $.ajax({  
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
	 
    $("#search").click(function(){
        var content=$("#content").val();
      	$.post("https://"+address+"/KingOfLab/querytosession.do",{"content": content,"key": "","menu": "","title":content});
        window.location.href="cdcatalog.html";
        //?content="+content+"&key=&menu=&title="+content;
});
	//shipping method
	var request7 = $.ajax({  
        url: "https://"+address+"/KingOfLab/shipping.do",  
        type: "POST",  
        async: false,
        dataType: "json",  
        cache: false,  
        success: function (result, textStatus) {
        	result=eval(result);
    		for(i=0;i<result.result.length;i++){
    			if(i==0){
    				var method="<label class='radio left'><input type='radio' name='radio' id='"+result.result[i].shipid+"' checked=''><i></i><span>"+result.result[i].method+"</span><span>"+result.result[i].price+"</span></label>";
    			}else{
    				var method="<label class='radio'><input type='radio' id='"+result.result[i].shipid+"' name='radio'><i></i><span>"+result.result[i].method+"</span><span>"+result.result[i].price+"</span></label>";
    			}
    			$(".shipping-details").append(method);	
    		}
    		$(".shipping-details").append("<div class='clearfix'></div>");
    		
    		var shipid=$("input[name='radio']:checked").attr("id");
    		var pjson= jQuery.param({"cjson":$.cookie('cart'),"shipping":shipid});
    			var request8 = $.ajax({
    		        url: "https://"+address+"/KingOfLab/pricedetails.do",  
    		        type: "POST",  
    		        async: false,
    		        data:pjson,
    		        dataType: "json",  
    		        cache: false,  
    		        success: function (result, textStatus) {
    		        	$("#purchase").text(result.purchase);
    		        	$("#tax").text(result.tax);
    		        	$("#subtotal").text(result.subtotal);
    		        	$("#shippingfee").text(result.shippingfee);
    		        	$("#totalfee").text("CAD$ "+result.totalfee);
    		        },  
    		        error: function (XMLHttpRequest, textStatus, errorThrown) {
    		        	alert(errorThrown); }  
    		    });
        },  
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	alert(errorThrown); }  
    });
	//price details
	
	var shipid=$("input[name='radio']:checked").attr("id");
	var pjson= jQuery.param({"cjson":$.cookie('cart'),"shipping":shipid});
		var request8 = $.ajax({
	        url: "https://"+address+"/KingOfLab/pricedetails.do",  
	        type: "POST",  
	        async: false,
	        data:pjson,
	        dataType: "json",  
	        cache: false,  
	        success: function (result, textStatus) {
	        	$("#purchase").text(result.purchase);
	        	$("#tax").text(result.tax);
	        	$("#subtotal").text(result.subtotal);
	        	$("#shippingfee").text(result.shippingfee);
	        	$("#totalfee").text("CAD$ "+result.totalfee);
	        },  
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	        	alert(errorThrown); }  
	    });
		
		$("input[name='radio']").change(function() {
			var shipid=$("input[name='radio']:checked").attr("id");
			var pjson= jQuery.param({"cjson":$.cookie('cart'),"shipping":shipid});
				var request8 = $.ajax({
			        url: "https://"+address+"/KingOfLab/pricedetails.do",  
			        type: "POST",  
			        async: false,
			        data:pjson,
			        dataType: "json",  
			        cache: false,  
			        success: function (result, textStatus) {
			        	$("#purchase").text(result.purchase);
			        	$("#tax").text(result.tax);
			        	$("#subtotal").text(result.subtotal);
			        	$("#shippingfee").text(result.shippingfee);
			        	$("#totalfee").text("CAD$ "+result.totalfee);
			        },  
			        error: function (XMLHttpRequest, textStatus, errorThrown) {
			        	alert(errorThrown); }  
			    });
			
		});
		//checkout process
		$(".order").click(function(){
			var shipid=$("input[name='radio']:checked").attr("id");
			var request9 = $.ajax({
		        url: "https://"+address+"/KingOfLab/verifysession.do",  
		        type: "POST",  
		        async: false,
		        dataType: "json", 
		        cache: false,  
		        success: function (result, textStatus) {
		        	if(result.code=="yes"){
		        		var pjson= jQuery.param({"cjson":$.cookie('cart'),"shipping":shipid});
		        		var request10 = $.ajax({
					        url: "https://"+address+"/KingOfLab/order.do",  
					        type: "POST", 
					        async: false,
					        data:pjson,
					        dataType: "json",  
					        cache: false,  
					        success: function (result, textStatus) {
					        	//order summary
					        	var successfulpaymentcartlist=eval(result.successfulpaymentcartlist);
					        	var purchase=result.beforetax;
					        	var tax=result.HST;
					        	var totalfee=result.total;
					        	var shippingfee=result.shippingfee;
					        	var subtotal=result.subtotal;
					        	var unsuccessfulpaymentcartlist=eval(result.unsuccessfulpaymentcartlist);
					        	var orderid=result.orderid;
					        	var method=result.method;
					        	var userid=result.userid;
					        	
					        	$("#purchase").text(purchase);
 					        	$("#tax").text(tax);
 					        	$("#subtotal").text(subtotal);
 					        	$("#shippingfee").text(shippingfee);
 					        	$("#totalfee").text("CAD$ "+totalfee);
 					        	
 					        	$(".shipping-details").children().remove();
 					        	$(".shipping-details").append("<h3>Shipping Method</h3><label class='radio left'><input type='radio' name='radio' checked=''><i></i><span>"+method+"</span><span>"+shippingfee+"</span></label>");
 					        	
 					        	$(".order").attr("class","confirm");
 					        	$(".confirm").text("Confirm Order");
 					        	
 					        	$(".cart-items").children().remove();
 					        	$(".cart-items").append("<h1>My Order</h1>");
 					        	
 					        	for(i=0;i<successfulpaymentcartlist.length;i++){
 					        	var item="<div class='cart-header'><div class='cart-sec simpleCart_shelfItem'><div class='cart-item cyc'><img src='"+successfulpaymentcartlist[i].imgurl+"' class='img-responsive'/></div><div class='cart-item-info'><h3><a>"+successfulpaymentcartlist[i].title+"</a><span>by "+successfulpaymentcartlist[i].artist+"</span></h3><h3>Price: CAD$ "+successfulpaymentcartlist[i].price+"</h3><ul class='qty'><li><p>Qty : </p><input class='quantityinput' disabled='true' type='text' value='"+successfulpaymentcartlist[i].quantity+"'/></li></ul><div class='delivery'><span>Delivered in 2-3 bussiness days</span><div class='clearfix'></div></div></div><div class='clearfix'></div></div></div>";
 					        	$(".cart-items").append(item);
 					        	}
 					        	
 					        	if(unsuccessfulpaymentcartlist.length>0){
 					        		$(".cart-items").append("<h3>Sorry,items below are out of stock!</h3>");
 					        		for(i=0;i<unsuccessfulpaymentcartlist.length;i++){
 		 					        	var itemun="<div class='cart-header'></div><div class='cart-sec simpleCart_shelfItem'><div class='cart-item cyc'><img src='"+unsuccessfulpaymentcartlist[i].imgurl+"' class='img-responsive'/></div><div class='cart-item-info'><h3><a>"+unsuccessfulpaymentcartlist[i].title+"</a><span>by "+unsuccessfulpaymentcartlist[i].artist+"</span></h3><h3>Price: CAD$ "+unsuccessfulpaymentcartlist[i].price+"</h3><ul class='qty'><li><p>Qty : </p><input class='quantityinput' disabled='true' type='text' value='"+unsuccessfulpaymentcartlist[i].quantity+"'/></li></ul><div class='delivery'><span>Delivered in 2-3 bussiness days</span><div class='clearfix'></div></div></div><div class='clearfix'></div></div></div>";
 		 					        	$(".cart-items").append(itemun);
 		 					        	}
 					        	}

 					        	//place a order and fill in shipping address
 					        	if(successfulpaymentcartlist.length>0){
 					        	$(".confirm").unbind('click').bind('click',function(){
 					        		$(".header_right").children().remove();
 					        		$(".header_right").append("<div class='rgt-bottom'><img src='images/s3.png' class='img-responsive' alt=''/></div>");
 					        		$(".check").children().remove();
 					        		$(".check").attr("class","main");
 					        		$(".main").append("<div class='registration'></div>");
 					        		
 					        		$(".registration").append("<div class='registration_left'><h2>Add a new address</h2><div class='registration_form'></div></di>");
 					        		
 					        		var newaddress="<div id='registration_form'><div><label><input placeholder='Full Name :' type='text' tabindex='1' required autofocus name='afname'></label></div><div><label><input placeholder='Address Line 1 : Street address, P.O. box' type='text' tabindex='2' required autofocus name='addr1'></label></div><div><label><input placeholder='Address Line 2 (Optional) : Apartment, suit, building, floor,etc.' type='text' tabindex='3' required name='addr2'</label></div><div><label><input placeholder='City :' type='text' tabindex='4' required name='city'></label></div><div><select name='province' id='province' style='color:gray'><option disabled selected>State/Province/Region :</option><option>Alberta</option><option>British Columbia</option><option>Manitoba</option><option>Newfoundland and Labrador</option><option>New Brunswick</option><option>Northwest Territories7</option><option>Nova Scotia8</option><option>Nunavut</option><option>Ontario</option><option>Prince Edward Island</option><option>Quebec</option><option>Saskatchewan</option><option>Yukon</option></select></div><div><label><input placeholder='Postal Code/ZIP :' type='text' tabindex='5' required name='zipcode'></label></div><div><label><input placeholder='Telephone Number :' type='text' tabindex='6' required name='tele'></label></div><div><input type='submit' value='ship to this address' id='newaddress'></div></div></div></div>";
 					        		$(".registration_form").append(newaddress);
 					        		
 					        		
 					        		//add new address
 					        	$("input[id='newaddress']").unbind('click').bind('click',function(){
 					        			var afname=$("input[name='afname']").val();
 	 					        		var addr1=$("input[name='addr1']").val();
 	 					        		var addr2=$("input[name='addr2']").val();
 	 					        		var city=$("input[name='city']").val();
 	 					        		var province=$("select option:selected").val();
 	 					        		var zipcode=$("input[name='zipcode']").val();
 	 					        		var tele=$("input[name='tele']").val();
 	 					        		
 					        			var ujson= jQuery.param({"orderid":orderid,"userid":userid,"fullname":afname,"addr1":addr1,"addr2":addr2,"city":city,"province":province,"zipcode":zipcode,"phone":tele});
 					        			var request10 = $.ajax({
 		 					       	        url: "https://"+address+"/KingOfLab/createaddress.do",  
 		 					       	        type: "POST",  
 		 					       	        async: false,
 		 					       	        data:ujson,
 		 					       	        dataType: "json",  
 		 					       	        cache: false,  
 		 					       	        success: function (result, textStatus) {
 		 					       	        //fill in billaddress
 		 					       	        var newaddrid=result.addrid;
 		 					       	   		var gjson= jQuery.param({"addrid":newaddrid});
 		 					       	   		var request12 = $.ajax({
		 					       	        	url: "https://"+address+"/KingOfLab/getaddr.do",  
		 					       	        	type: "POST",  
		 					       	        	async: false,
		 					       	        	data:gjson,
		 					       	       	 	dataType: "json",  
		 					       	        	cache: false,  
		 					       	        	success: function (result, textStatus) {
		 					       	        	//showe bill address option or create bill adress
		 					       	        	$(".header_right").children().remove();
		 	 					        		$(".header_right").append("<div class='rgt-bottom'><img src='images/s4.png' class='img-responsive' alt=''/></div>");
		 	 					        		$(".main").children().remove();
		 	 					        		$(".main").append("<div class='registration'></div>");
		 	 					        		
		 	 					        		$(".registration").append("<div class='registration_left'><h2>Add a new bill address</h2><div class='registration_form'></div></di>");
		 	 					        		
		 	 					        		var newbilladdress="<div id='registration_form'><div><label><input placeholder='Full Name :' type='text' tabindex='1' required autofocus name='afname'></label></div><div><label><input placeholder='Address Line 1 : Street address, P.O. box' type='text' tabindex='2' required autofocus name='addr1'></label></div><div><label><input placeholder='Address Line 2 (Optional) : Apartment, suit, building, floor,etc.' type='text' tabindex='3' required name='addr2'</label></div><div><label><input placeholder='City :' type='text' tabindex='4' required name='city'></label></div><div><select name='province' id='province' style='color:gray'><option disabled selected>State/Province/Region :</option><option>Alberta</option><option>British Columbia</option><option>Manitoba</option><option>Newfoundland and Labrador</option><option>New Brunswick</option><option>Northwest Territories7</option><option>Nova Scotia8</option><option>Nunavut</option><option>Ontario</option><option>Prince Edward Island</option><option>Quebec</option><option>Saskatchewan</option><option>Yukon</option></select></div><div><label><input placeholder='Postal Code/ZIP :' type='text' tabindex='5' required name='zipcode'></label></div><div><label><div><input type='submit' value='bill to this address' id='newbilladdress'></div></div></div></div>";
		 	 					        		$(".registration_form").append(newbilladdress);
		 	 					        		
		 	 					        		
		 	 					        		//crete new bill address
		 	 					        		
		 	 					        		$("input[id='newbilladdress']").unbind('click').bind('click',function(){
		 	 					        			var afname=$("input[name='afname']").val();
			 	 					        		var addr1=$("input[name='addr1']").val();
			 	 					        		var addr2=$("input[name='addr2']").val();
			 	 					        		var city=$("input[name='city']").val();
			 	 					        		var province=$("select option:selected").val();
			 	 					        		var zipcode=$("input[name='zipcode']").val();
		 	 					        			
 					        					var bjson= jQuery.param({"orderid":orderid,"userid":userid,"fullname":afname,"addr1":addr1,"addr2":addr2,"city":city,"province":province,"zipcode":zipcode});
 					        					var request13 = $.ajax({
 		 					       	        		url: "https://"+address+"/KingOfLab/createbilladdress.do",  
 		 					       	        		type: "POST",  
 		 					       	        		async: false,
 		 					       	        		data:bjson,
 		 					       	        		dataType: "json",  
 		 					       	       		 	cache: false,  
 		 					       	        		success: function (result, textStatus) {
 		 					       	        		//confirm address
 		 					       	        		window.location.href="confirmaddress.html";	
 		 					       	        	 },  
 		 	 					       	        error: function (XMLHttpRequest, textStatus, errorThrown) {
 		 	 					       	        	alert(errorThrown); }  
 		 	 					       	    });
 					        					
		 	 					        		 });
		 	 					        		
		 	 					        		$(".registration").append("<div class='registration_left'><h2>same as shipping address?</h2><div class='registration_form'><div id='registration_form' class='oldaddress'></div></di>");
		 	 					        		var billaddress="<div class='sky-form'><div class='sky_form1'><ul><li><label class='radio left addr'><input type='radio' name='radioaddr' id='"+result.address.addrid+"' checked=''><i></i><p class='addr'>"+result.address.fullname+"</p><p class='addr'>"+result.address.addrline1+"</p><p class='addr'>"+result.address.addrline2+"</p><p class='addr'>"+result.address.city+","+result.address.province+"</p><p class='addr'>"+result.address.zipcode+"</p></label></li><div class='clearfix'></div></ul></div></div>";
	 	 		 					        	$(".oldaddress").append(billaddress);
	 	 		 					        	var button_choose="<div><input type='submit' value='bill to this address' id='choose_billaddress'></div>";
	 	 					       	       		 $(".oldaddress").append(button_choose);
	 	 					       	       		 //useshipping address as bill address
	 	 					       	       		 
	 	 					       	       		 
	 	 					       	       		 $("input[id='choose_billaddress']").unbind('click').bind('click',function(){
	 	 					       	       		var bojson= jQuery.param({"orderid":orderid,"addrid":$("input[name='radioaddr']:checked").attr("id")});
 					        					var request14 = $.ajax({
 		 					       	        		url: "https://"+address+"/KingOfLab/updatebilladdress.do",  
 		 					       	        		type: "POST",  
 		 					       	        		async: false,
 		 					       	        		data:bojson,
 		 					       	        		dataType: "json",  
 		 					       	       		 	cache: false,  
 		 					       	        		success: function (result, textStatus) {
 		 					       	        		//confirm address
 		 					       	        		window.location.href="confirmaddress.html";
 		 					       	        			
 		 					       	        	 },  
 		 	 					       	        error: function (XMLHttpRequest, textStatus, errorThrown) {
 		 	 					       	        	alert(errorThrown); }  
 		 	 					       	    });  
 					        					
	 	 					       	       	}); 
 					        					
	 	 					       	       		       	     
		 					       	   },  
	 					       	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	 					       	        	alert(errorThrown); }  
	 					       	    });
 		 					       	        
 		 					       	        
 		 					       	   },  
 	 					       	        error: function (XMLHttpRequest, textStatus, errorThrown) {
 	 					       	        	alert(errorThrown); }  
 	 					       	    });
 					        			
 					        		});
 					        		
 					        		//check if have old adress
 					        		var getjson= jQuery.param({"userid":userid});
 					        		var request11 = $.ajax({
 					       	        url: "https://"+address+"/KingOfLab/getaddressbyuserid.do",  
 					       	        type: "POST",  
 					       	        async: false,
 					       	        data:getjson,
 					       	        dataType: "json",  
 					       	        cache: false,  
 					       	        success: function (result, textStatus) {
 					       	        	var addressList=eval(result.addressList);
 					       	        	if(addressList.length>0){
 					       	        	$(".registration").append("<div class='registration_left'><h2>existing address</h2><div class='registration_form'><div id='registration_form' class='oldaddress'></div></di>");
 					       	        	for(i=0;i<addressList.length;i++){
 	 		 					        	var addressitem="<div class='sky-form'><div class='sky_form1'><ul><li><label class='radio left addr'><input type='radio' name='radioaddr' id='"+addressList[i].addrid+"' checked=''><i></i><p class='addr'>"+addressList[i].fullname+"</p><p class='addr'>"+addressList[i].addrline1+"</p><p class='addr'>"+addressList[i].addrline2+"</p><p class='addr'>"+addressList[i].city+","+addressList[i].province+"</p><p class='addr'>"+addressList[i].zipcode+"</p><p class='addr'>"+addressList[i].phone+"</p></label></li><div class='clearfix'></div></ul></div></div>";
 	 		 					        	$(".oldaddress").append(addressitem);
 	 		 					        	}
 					       	        	 var button_choose="<div><input type='submit' value='ship to this address' id='choose_address'></div>";
 					       	       		 $(".oldaddress").append(button_choose);
 					       	       		 
 					       	       		 //choosee old address to ship
 					       	       		 $("input[id='choose_address']").unbind('click').bind('click',function(){
 					       	       		var addrid=$("input[name='radioaddr']:checked").attr("id");
 					       	       		var ojson= jQuery.param({"userid":userid,"addrid":addrid,"orderid":orderid});
 					        			var request11 = $.ajax({
 		 					       	        url: "https://"+address+"/KingOfLab/updateorder.do",  
 		 					       	        type: "POST",  
 		 					       	        async: false,
 		 					       	        data:ojson,
 		 					       	        dataType: "json",  
 		 					       	        cache: false,  
 		 					       	        success: function (result, textStatus) {				       	        
 		 					       	        //fill in billaddress
 		 					       	       var oldaddrid=result.addrid;
 		 					       	  		var g2json= jQuery.param({"addrid":oldaddrid});
	 					       	   			var request12 = $.ajax({
	 					       	        	url: "https://"+address+"/KingOfLab/getaddr.do",  
	 					       	        	type: "POST",  
	 					       	        	async: false,
	 					       	        	data:g2json,
	 					       	       	 	dataType: "json",  
	 					       	        	cache: false,  
	 					       	        	success: function (result, textStatus) {
	 					       	        		//showe bill address option or create bill adress
	 					       	        		
	 					       	        	$(".header_right").children().remove();
	 	 					        		$(".header_right").append("<div class='rgt-bottom'><img src='images/s4.png' class='img-responsive' alt=''/></div>");
	 	 					        		$(".main").children().remove();
	 	 					        		$(".main").append("<div class='registration'></div>");
	 	 					        		
	 	 					        		$(".registration").append("<div class='registration_left'><h2>Add a new bill address</h2><div class='registration_form'></div></di>");
	 	 					        		
	 	 					        		var newbilladdress="<div id='registration_form'><div><label><input placeholder='Full Name :' type='text' tabindex='1' required autofocus name='afname'></label></div><div><label><input placeholder='Address Line 1 : Street address, P.O. box' type='text' tabindex='2' required autofocus name='addr1'></label></div><div><label><input placeholder='Address Line 2 (Optional) : Apartment, suit, building, floor,etc.' type='text' tabindex='3' required name='addr2'</label></div><div><label><input placeholder='City :' type='text' tabindex='4' required name='city'></label></div><div><select name='province' id='province' style='color:gray'><option disabled selected>State/Province/Region :</option><option>Alberta</option><option>British Columbia</option><option>Manitoba</option><option>Newfoundland and Labrador</option><option>New Brunswick</option><option>Northwest Territories7</option><option>Nova Scotia8</option><option>Nunavut</option><option>Ontario</option><option>Prince Edward Island</option><option>Quebec</option><option>Saskatchewan</option><option>Yukon</option></select></div><div><label><input placeholder='Postal Code/ZIP :' type='text' tabindex='5' required name='zipcode'></label></div><div><label><div><input type='submit' value='bill to this address' id='newbilladdress'></div></div></div></div>";
	 	 					        		$(".registration_form").append(newbilladdress);
	 	 					        		
	 	 					        		
	 	 					        		//crete new bill address
	 	 					        		$("input[name='newbilladdress']").unbind('click').bind('click',function(){
	 	 					        			var afname=$("input[name='afname']").val();
		 	 					        		var addr1=$("input[name='addr1']").val();
		 	 					        		var addr2=$("input[name='addr2']").val();
		 	 					        		var city=$("input[name='city']").val();
		 	 					        		var province=$("select option:selected").val();
		 	 					        		var zipcode=$("input[name='zipcode']").val();
		 	 					        		
					        					var bjson= jQuery.param({"orderid":orderid,"userid":userid,"fullname":afname,"addr1":addr1,"addr2":addr2,"city":city,"province":province,"zipcode":zipcode});
					        					var request13 = $.ajax({
		 					       	        		url: "https://"+address+"/KingOfLab/createbilladdress.do",  
		 					       	        		type: "POST",  
		 					       	        		async: false,
		 					       	        		data:bjson,
		 					       	        		dataType: "json",  
		 					       	       		 	cache: false,  
		 					       	        		success: function (result, textStatus) {
		 					       	        		//confirm address
		 					       	        		window.location.href="confirmaddress.html";	
		 					       	        	 },  
		 	 					       	        error: function (XMLHttpRequest, textStatus, errorThrown) {
		 	 					       	        	alert(errorThrown); }  
		 	 					       	    });
	 	 					        		 });
	 	 					        		
	 	 					        		$(".registration").append("<div class='registration_left'><h2>same as shipping address?</h2><div class='registration_form'><div id='registration_form' class='oldaddress'></div></di>");
	 	 					        		var billaddress="<div class='sky-form'><div class='sky_form1'><ul><li><label class='radio left addr'><input type='radio' name='radioaddr' id='"+result.address.addrid+"' checked=''><i></i><p class='addr'>"+result.address.fullname+"</p><p class='addr'>"+result.address.addrline1+"</p><p class='addr'>"+result.address.addrline2+"</p><p class='addr'>"+result.address.city+","+result.address.province+"</p><p class='addr'>"+result.address.zipcode+"</p></label></li><div class='clearfix'></div></ul></div></div>";
 	 		 					        	$(".oldaddress").append(billaddress);
 	 		 					        	var button_choose="<div><input type='submit' value='bill to this address' id='choose_billaddress'></div>";
 	 					       	       		 $(".oldaddress").append(button_choose);
 	 					       	       		 //useshipping address as bill address
 	 					       	       		 
 	 					       	       		 $("input[id='choose_billaddress']").unbind('click').bind('click',function(){
					        					var bojson= jQuery.param({"orderid":orderid,"addrid":$("input[name='radioaddr']:checked").attr("id")});
					        					var request14 = $.ajax({
		 					       	        		url: "https://"+address+"/KingOfLab/updatebilladdress.do",  
		 					       	        		type: "POST",  
		 					       	        		async: false,
		 					       	        		data:bojson,
		 					       	        		dataType: "json",  
		 					       	       		 	cache: false,  
		 					       	        		success: function (result, textStatus) {
		 					       	        		//confirm address
		 					       	        		window.location.href="confirmaddress.html";
		 					       	        			
		 					       	        	 },  
		 	 					       	        error: function (XMLHttpRequest, textStatus, errorThrown) {
		 	 					       	        	alert(errorThrown); }  
		 	 					       	    });		
 	 					       	        });
	 					       	   },  
					       	        error: function (XMLHttpRequest, textStatus, errorThrown) {
					       	        	alert(errorThrown); }  
					       	    });
 		 					       	       
 		 					       	        
 		 					       	   },  
 	 					       	        error: function (XMLHttpRequest, textStatus, errorThrown) {
 	 					       	        	alert(errorThrown); }  
 	 					       	    });
		        				});
 					       	        	}
 					       	        },  
 					       	        error: function (XMLHttpRequest, textStatus, errorThrown) {
 					       	        	alert(errorThrown); }  
 					       	    });
 					        		
 					        		$(".main").append("<div class='clearfix'></div>");
 					        		
 					        	});
 					        	
 					        	}else{
 					        		setTimeout(function (){window.location.href='index.html'},3000);
 					        	}

					        },  
					        error: function (XMLHttpRequest, textStatus, errorThrown) {
					        	alert(errorThrown); }  
					    });
		        		
						
					}else{
						window.location.href="register.html";
					}	
		        },  
		        error: function (XMLHttpRequest, textStatus, errorThrown) {
		        	alert(errorThrown); }  
		    });
	        
		});
	
});	