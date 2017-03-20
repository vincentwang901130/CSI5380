/*
 * cdcatalog - v1.0 - 2015-10-24
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
        	var accountinfo="<div class='accountinfo'><span>Welcome back, <a class='fisrtname' style='text-transform: capitalize'>"+result.account.fname+" !</a></span></div>";
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
		var request13 = $.ajax({
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
  
	//getProductlist
	var request3 = $.ajax({  
    	url: "https://"+address+"/KingOfLab/getProductlist.do",  
    	type: "POST",  
    	async: false,
    	dataType: "json",  
    	cache: false,  
    	success: function (result, textStatus) {
    		var mtitle=result.title;
    		var menu=result.menu;
    		$('#menutitle').text(result.title+" - "+eval(result.cdList).length+" items");
    		 $("a[name='popular']").unbind('click').bind('click',function(){
    		    	var content=$("#content").val();
    		    	$.post("https://"+address+"/KingOfLab/querytosession.do",{"content": content,"key": "popular","menu": menu,"title":mtitle});
    		        window.location.href="cdcatalog.html";
    	    });
    		 $("a[name='new']").unbind('click').bind('click',function(){
 		    	var content=$("#content").val();
 		    	$.post("https://"+address+"/KingOfLab/querytosession.do",{"content": content,"key": "redate","menu": menu,"title":mtitle});
 		        window.location.href="cdcatalog.html";
 	    });
    		 $("a[name='price']").unbind('click').bind('click',function(){
  		    	var content=$("#content").val();
  		    	$.post("https://"+address+"/KingOfLab/querytosession.do",{"content": content,"key": "price","menu": menu,"title":mtitle});
  		        window.location.href="cdcatalog.html";
  	    });
        	result=eval(result.cdList);
        	if(result.length>0){
        	$(".subset1").children().remove();
        	for(i=0;i<result.length;i++){
        		j=i+1;
        		h=Math.floor(i/4)
        		l=j%4;
        		var item="<div class='grid1_of_4' id='"+result[i].cdid+"'><div class='content_box'><a name='images' class='pointer'><img src='"+result[i].imgurl+"' class='img-responsive'/></a><h4><a class='pointer' name='title'>"+result[i].title+"</a></h4><p>by "+result[i].artist+"</p><div class='grid_1 simpleCart_shelfItem'><div class='item_add'><span class='item_price'><h6>ONLY CAD$"+result[i].price+"</h6></span></div><div class='item_add'><span class='item_price'><a class='pointer cart' id='cart'>add to cart</a></span></div></div></div></div>";
        		if(h==0){
        			if(l==0){
        				$(".subset1").append(item+"<div class='clearfix'> </div>");	
        			}else{
        				$(".subset1").append(item);		
        			}
        		}else if(h==1){
        			if(l==0){
        				$(".subset2").append(item+"<div class='clearfix'> </div>");	
        			}else{
        				$(".subset2").append(item);		
        			}
        		}else if(h==2){
        			if(l==0){
        				$(".subset3").append(item+"<div class='clearfix'> </div>");	
        			}else{
        				$(".subset3").append(item);		
        			}
        		}
        		$("a[name='images']").unbind('click').bind('click',function(){
                    var cdid=$(this).parent().parent().attr("id");
                    $.post("https://"+address+"/KingOfLab/detailtosession.do",{"cdid": cdid});
      		        window.location.href="details.html";
                   
            }); 
        		$("a[name='title']").unbind('click').bind('click',function(){
                    var cdid=$(this).parent().parent().parent().attr("id");
                    $.post("https://"+address+"/KingOfLab/detailtosession.do",{"cdid": cdid});
      		        window.location.href="details.html";
            });
        		$(".cart").unbind('click').bind('click',function(){
                    var cdid=$(this).parent().parent().parent().parent().parent().attr("id")
                    //add item to cookies/session
                    
                    
                 if(typeof(cdid) == "undefined"){
                 }else{
                	 var aj=$.cookie('cart');
                     ajson=jQuery.param({"cdid": cdid,"quantity":1,"aj":aj});
                    var request = $.ajax({  
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
                   }   
            });
        		
        	}
        	
        	}else{
        		//return notavailable
        		$(".cd_main").children().remove();
        		$(".cd_main").append("<div class='special'><div class='container'><h3>Sorry!</h3></div><div class='message'><p>Thank you for your choice!</p><p>What you request is not available in the store yet!</p><p>This page will lead you to our home page in 3 seconds!</p><p>If it not working, please <a href='index.html' style='color:#ff6978;cursor: pointer;'> click here </a>to go back to the home page!</p><p>Thank you for shopping with us!</p></div></div>");
        		setTimeout(function (){window.location.href='index.html'},3000); 
        	}
       	 
        	
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

		 //view shopping cart
	    $("a[name='checkout']").click(function(){
	    	
	    	if($.cookie('cart')!="" && $.cookie('cart')!=null){
	    		window.location.href="checkout.html";
	    	}else{
	    		alert("You Do Not Have Items In Cart Yet!");
	    	}
	        
	    });	
		 
	   
		
	
});

