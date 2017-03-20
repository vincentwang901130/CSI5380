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

* this class is a servlet which implements the functionality that update billing address

* @author Zhibo Zhang


*/
public class EditBillAddressController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String account=(String) req.getSession().getAttribute("account");
		String fullname=req.getParameter("fullname");
		String addr1=req.getParameter("addr1");
		String addr2=req.getParameter("addr2");
		String city=req.getParameter("city");
		String province=req.getParameter("province");
		String zipcode=req.getParameter("zipcode");
		PrintWriter out=resp.getWriter();
		// call web service
		ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget target = client.target(GetBaseURL.getOrderBaseURI()+"/rest/editBillAddress");
	    WebTarget targetnew=target.queryParam("account", account).queryParam("fullname", fullname).queryParam("addr1", addr1).queryParam("addr2", addr2).queryParam("city", city).queryParam("province", province).queryParam("zipcode", zipcode);
	    String result= targetnew.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		out.print(result);
	
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
}

}
