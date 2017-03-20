package edu.carleton.comp.cdstore.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import edu.carleton.comp.cdstore.beans.Cart;

/**

* this class is a servlet which implements the functionality that modify the quantities of items selected in cart

* @author Zhibo Zhang


*/
public class ModifyQuantity extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cdid=req.getParameter("cdid");
		String cookie=req.getParameter("cjson");
		String quantity=req.getParameter("quantity");
		PrintWriter out = resp.getWriter();
		Map<String, String> result = new HashMap<String, String>();
		Integer quan=Integer.valueOf(quantity);
		List<Cart> cartList = JSONArray.parseArray(cookie, Cart.class);
		for(int i=0;i<cartList.size();i++){
			int oldcdid=cartList.get(i).getCdid();
			if(oldcdid==Integer.parseInt(cdid)){
				cartList.get(i).setQuantity(quan);
				break;
			}
		}
		
		result.put("cookies", JSON.toJSONString(cartList,SerializerFeature.DisableCircularReferenceDetect));
		String result_str = JSON.toJSONString(result,SerializerFeature.DisableCircularReferenceDetect);
		out.print(result_str);

		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);

	}

}