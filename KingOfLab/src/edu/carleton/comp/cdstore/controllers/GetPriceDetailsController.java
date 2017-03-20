package edu.carleton.comp.cdstore.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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

* this class is a servlet which implements the functionality that calculate the total amount customers need to pay

* @author Zhibo Zhang


*/
public class GetPriceDetailsController extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		
		String cjson=req.getParameter("cjson");
		String shipidstr=req.getParameter("shipping");
		Integer shipid=Integer.valueOf(shipidstr);
				
		Map<String, Object> result=new HashMap<String,Object>();
		List<Cart> cartList = JSONArray.parseArray(cjson, Cart.class);
		// do the calculation
		float p=0;
		int q=0;
		int tq=0;
		float s=0;
		float t=0;
		for(int i=0;i<cartList.size();i++){
			q=cartList.get(i).getQuantity();
			p=cartList.get(i).getPrice();
			tq=q+tq;
			s=p*q;
			t=t+s;	
		}
		//call webs service
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget target = client.target(GetBaseURL.getOrderBaseURI()+"/rest/getPriceDetails");
	    WebTarget targetnew=target.queryParam("shipid", shipid);
	    String shippingstr= targetnew.request().accept(MediaType.APPLICATION_JSON).get(String.class);
	    
	    Map<String,Object> shippmap=JSON.parseObject(shippingstr);
	    Iterator<String> iterator = shippmap.keySet().iterator();
	    String value="";
	    String key;
	    while (iterator.hasNext()) {
	    	key = iterator.next();
	    	value =String.valueOf(shippmap.get(key));
	    	}

		Integer values=Integer.valueOf(value);
		BigDecimal priceb=new BigDecimal(value);  
		double pricef=priceb.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		BigDecimal tb=new BigDecimal(t);  
		double tf=tb.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		BigDecimal taxb=new BigDecimal(t*0.13);  
		double tax=taxb.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		BigDecimal subtotalb=new BigDecimal(t+tax);  
		double subtotal=subtotalb.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		BigDecimal totalfeeb=new BigDecimal(t+tax+values);  
		double totalfee=totalfeeb.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		
		result.put("purchase", String.valueOf(tf));
		result.put("tax",String.valueOf(tax));
		result.put("subtotal",String.valueOf(subtotal));
		result.put("shippingfee",String.valueOf(pricef));
		result.put("totalfee",String.valueOf(totalfee));
		String result_str = JSON.toJSONString(result,SerializerFeature.DisableCircularReferenceDetect);
		out.print(result_str);
		

		
		
		
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			this.doGet(req, resp);

}

}
