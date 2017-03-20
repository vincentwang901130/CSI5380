package edu.carleton.comp.cdstore.controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
import edu.carleton.comp.cdstore.util.GetBaseURL;
/**

* this class is a servlet which implements the functionality that login

* @author Wenqian Wang


*/
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		PrintWriter out = resp.getWriter();		
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget target = client.target(GetBaseURL.getOrderBaseURI()+"/rest/login");
	    WebTarget targetnew=target.queryParam("email", email).queryParam("password", password);
	    String result= targetnew.request().accept(MediaType.APPLICATION_JSON).get(String.class);
	    Map<String,Object> code=JSON.parseObject(result);
	    Iterator<String> iterator = code.keySet().iterator();
	    String value="";
	    String key;
	    while (iterator.hasNext()) {
	    	key = iterator.next();
	    	value = (String) code.get(key);
	    	}
	    if(value.equals("success")){
	    session.setAttribute("account", email);
		}
	    out.print(result);

	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);

	}
}
