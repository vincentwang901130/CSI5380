package edu.carleton.comp.cdstore.controllers;

import java.io.IOException;
import java.io.PrintWriter;

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
import edu.carleton.comp.cdstore.util.GetBaseURL;
/**

* this class is a servlet which implements the functionality that get cd catalog

* @author Zhibo Zhang


*/
public class GetProductListController extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		PrintWriter out = resp.getWriter();
		
		String menu=(String) session.getAttribute("menu");
		String content=(String) session.getAttribute("content");
		String key=(String) session.getAttribute("key");
		String title=(String) session.getAttribute("title");
		//call web service
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget target= client.target(GetBaseURL.getProductBaseURI()+"/rest/getProductList");
	    WebTarget targetnew=target.queryParam("menu", menu).queryParam("content", content).queryParam("key", key).queryParam("title", title);
	    String result= targetnew.request().accept(MediaType.APPLICATION_JSON).get(String.class);
	   
		out.print(result);
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			this.doGet(req, resp);

}

}
