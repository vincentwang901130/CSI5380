package edu.carleton.comp.cdstore.sessionmanager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**

* this class is a servlet which implements the functionality that store search keywords in session to support query afterwards

* @author Zhibo Zhang


*/
public class QuerySessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		String content=req.getParameter("content");
		String menu=req.getParameter("menu");
		String key=req.getParameter("key");
		String title=req.getParameter("title");
		
		session.setAttribute("content",content);
		session.setAttribute("menu",menu);
		session.setAttribute("key",key);
		session.setAttribute("title",title);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
