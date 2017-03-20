package edu.carleton.comp.cdstore.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import edu.carleton.comp.cdstore.beans.Cart;
import edu.carleton.comp.cdstore.util.GetBaseURL;
/**

* this class is a servlet which implements the functionality that create a new order

* @author Zhibo Zhang,Wenqian Wang


*/


public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cjson=req.getParameter("cjson");
		String account=(String) req.getSession().getAttribute("account");
		PrintWriter out=resp.getWriter();
		int shipid=Integer.parseInt(req.getParameter("shipping"));
		//call web service to get tax info and shipping info
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget target = client.target(GetBaseURL.getOrderBaseURI()+"/rest/getOrderInfo");
	    WebTarget targetnew=target.queryParam("account", account).queryParam("shipid", shipid);
	    String result2= targetnew.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		
	    Map<String,Object> code=JSON.parseObject(result2);
	    Iterator<String> iterator = code.keySet().iterator();
	    Object value="";
	    String key;
	    List<Object> rs=new ArrayList<Object>();
	    while (iterator.hasNext()) {
	    	key = iterator.next();
	    	value = code.get(key);
	    	rs.add(value);
	    	}
	    
	    
	    float taxrate=Float.valueOf(String.valueOf(rs.get(0)));
	    String method=String.valueOf(rs.get(1));
	    float shippingfee=Float.valueOf(String.valueOf(rs.get(2)));
	    int userid=Integer.valueOf(String.valueOf(rs.get(3)));
	    

	    


		List <Cart> successfulone=new ArrayList<Cart>();
		List<Cart> unsuccessfulone=new ArrayList<Cart>();
		float sum=0;
		if(cjson!=""){
			List<Cart> cartList = JSONArray.parseArray(cjson, Cart.class);
			for(int i=0;i<cartList.size();i++){
				if(this.checkstock(cartList.get(i))){
					successfulone.add(cartList.get(i));
					sum+=(cartList.get(i).getPrice())*(cartList.get(i).getQuantity());
				}
				else
				{
					unsuccessfulone.add(cartList.get(i));
				}
			
			}
		}
		
		float total=(sum*(1+taxrate))+shippingfee;
		float taxtopay=sum*taxrate;
		//crate order in datebase
		WebTarget target1 = client.target(GetBaseURL.getOrderBaseURI()+"/rest/createOrder");
		WebTarget targetnew1=target1.queryParam("userid", userid).queryParam("shipid", shipid).queryParam("total", total);
		String result1= targetnew1.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		
		Map<String,Object> map=JSON.parseObject(result1);
	    Iterator<String> iteratormap = map.keySet().iterator();
	    String orderidstr="";
	    String key1;
	    while (iteratormap.hasNext()) {
	    	key1 = iteratormap.next();
	    	orderidstr = String.valueOf(map.get(key1));
	    	}
	    Integer orderid=Integer.valueOf(orderidstr);
	    
	  //create item;
		if(orderid<0){
			System.out.println("error");
		}
		else
		{
			//create items and update stock
			for(int i=0;i<successfulone.size();i++){
				WebTarget targeti = client.target(GetBaseURL.getOrderBaseURI()+"/rest/createItems");
				WebTarget targetnewi=targeti.queryParam("quantity", successfulone.get(i).getQuantity()).queryParam("orderid", orderid).queryParam("cdid", successfulone.get(i).getCdid());
				targetnewi.request().accept(MediaType.APPLICATION_JSON).get(String.class);
			}
		}
	
	    
		//format output
		BigDecimal sumb=new BigDecimal(sum);  
		double sumf=sumb.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		BigDecimal taxtopayb=new BigDecimal(taxtopay);  
		double taxtopayf=taxtopayb.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		BigDecimal totalb=new BigDecimal(total);  
		double totalf=totalb.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		BigDecimal shippingfeeb=new BigDecimal(shippingfee);  
		double shippingfeef=shippingfeeb.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		BigDecimal subtotalb=new BigDecimal(total-shippingfee);  
		double subtotalf=subtotalb.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		//generate output
		Map<String, Object> result =new HashMap<String, Object>();
		result.put("successfulpaymentcartlist", successfulone);
		result.put("beforetax", String.valueOf(sumf));
		result.put("HST", String.valueOf(taxtopayf));
		result.put("total", String.valueOf(totalf));
		result.put("shippingfee", String.valueOf(shippingfeef));
		result.put("method", method);
		result.put("subtotal", String.valueOf(subtotalf));
		result.put("orderid", orderidstr);
		result.put("userid", String.valueOf(userid));
		result.put("unsuccessfulpaymentcartlist", unsuccessfulone);
		String result_str = JSON.toJSONString(result,SerializerFeature.DisableCircularReferenceDetect);
	    out.println(result_str);
		
	}

	private Boolean checkstock(Cart cart){
		int cdid=cart.getCdid();
		int sold=cart.getQuantity();
		ClientConfig config = new ClientConfig();
	    Client client1 = ClientBuilder.newClient(config);
	    WebTarget target3 = client1.target(GetBaseURL.getProductBaseURI()+"/rest/getStockInfo");
	    WebTarget targetnew3=target3.queryParam("cdid", cdid).queryParam("sold", sold);
	    String result2= targetnew3.request().accept(MediaType.APPLICATION_JSON).get(String.class);
	    
	    Map<String,Object> mapbool=JSON.parseObject(result2);
	    Iterator<String> iteratorbool = mapbool.keySet().iterator();
	    Boolean boolvalue = null;
	    String key2;
	    while (iteratorbool.hasNext()) {
	    	key2 = iteratorbool.next();
	    	boolvalue = (Boolean) mapbool.get(key2);
	    	}
		
	    return boolvalue;
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
	}

}
