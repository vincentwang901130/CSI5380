package edu.carleton.comp.cdstore.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;


import edu.carleton.comp.cdstore.util.GetBaseURL;

/**

* this class is a servlet which implements the functionality that get shipping and billing address info for customer to confirm

* @author Zhibo Zhang


*/
public class ConfirmAddressController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String account=(String) req.getSession().getAttribute("account");
		PrintWriter out=resp.getWriter();
		// call web service
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget target = client.target(GetBaseURL.getOrderBaseURI()+"/rest/getAddressInfo");
	    WebTarget targetnew=target.queryParam("account", account);
	    String result= targetnew.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		out.print(result);
	
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
}

}
