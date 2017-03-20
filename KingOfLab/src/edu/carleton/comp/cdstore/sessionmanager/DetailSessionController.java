package edu.carleton.comp.cdstore.sessionmanager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**

* this class is a servlet which implements the functionality that store cdid in session to support query afterwards

* @author Zhibo Zhang


*/
public class DetailSessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		String cdid=req.getParameter("cdid");
		session.setAttribute("cdid",cdid);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
