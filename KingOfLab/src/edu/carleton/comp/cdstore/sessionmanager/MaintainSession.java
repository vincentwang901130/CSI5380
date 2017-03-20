package edu.carleton.comp.cdstore.sessionmanager;

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

* this class is a servlet which implements the functionality that store username in session

* @author Wenqian Wang,Zhibo Zhang


*/
public class MaintainSession extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		PrintWriter out=resp.getWriter();
		String email=(String)session.getAttribute("account");
		if(email!=null){
			ClientConfig config = new ClientConfig();
		    Client client = ClientBuilder.newClient(config);
		    WebTarget target = client.target(GetBaseURL.getOrderBaseURI()+"/rest/getCustomer");
		    WebTarget targetnew=target.queryParam("email", email);
		    String result= targetnew.request().accept(MediaType.APPLICATION_JSON).get(String.class);
			out.print(result);
		}else{
			out.print("null");
		}
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);

	}

}
