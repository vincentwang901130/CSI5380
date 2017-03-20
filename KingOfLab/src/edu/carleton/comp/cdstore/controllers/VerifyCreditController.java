package edu.carleton.comp.cdstore.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.carleton.comp.cdstore.util.GetBaseURL;
/**

* this class is a servlet which implements the functionality that verify the credit card authorization 

* @author Zhibo Zhang


*/
public class VerifyCreditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		Map<String, Object> result =new HashMap<String, Object>();
		String requesttime=(String) session.getAttribute("requesttime");
		String account=(String) session.getAttribute("account");
		PrintWriter out=resp.getWriter();
		int request;
		//call webservice to check if it is the 5th request,if yes than deny ,if not than approve and update order status
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget target = client.target(GetBaseURL.getOrderBaseURI()+"/rest/getOrder");
	    WebTarget targetnew=target.queryParam("account", account);
	    String result1= targetnew.request().accept(MediaType.APPLICATION_JSON).get(String.class);
	    
	    Map<String,Object> code=JSON.parseObject(result1);
	    Iterator<String> iterator = code.keySet().iterator();
	    Object orderidstr = null;
	    String key;
	    while (iterator.hasNext()) {
	    	key = iterator.next();
	    	orderidstr =code.get(key);
	    	}
	    Integer orderid=Integer.valueOf(String.valueOf(orderidstr));

	    WebTarget target1=client.target(GetBaseURL.getOrderBaseURI()+"/rest/updateOrder");
	    WebTarget target1new;
		if(requesttime!=null){
			request=Integer.valueOf(requesttime);
			if(request<5){
				result.put("code", "success");
				request=request+1;
				session.setAttribute("requesttime", String.valueOf(request));
				target1new=target1.queryParam("orderid", orderid).queryParam("status", 1);
				target1new.request().accept(MediaType.APPLICATION_JSON).get(String.class);
			}else{
				result.put("code", "deny");
				request=0;
				session.setAttribute("requesttime", String.valueOf(request));
				target1new=target1.queryParam("orderid", orderid).queryParam("status", 0);
				target1new.request().accept(MediaType.APPLICATION_JSON).get(String.class);
			}
		}else{
			result.put("code", "success");
			request=1;
			session.setAttribute("requesttime", String.valueOf(request));
			target1new=target1.queryParam("orderid", orderid).queryParam("status", 1);
			target1new.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		}
		
		String result_str = JSON.toJSONString(result,SerializerFeature.DisableCircularReferenceDetect);
		out.print(result_str);
		
	}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
}

}
