package edu.carleton.comp.cdstore.cookies;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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

import edu.carleton.comp.cdstore.beans.CD;
import edu.carleton.comp.cdstore.beans.Cart;
import edu.carleton.comp.cdstore.util.GetBaseURL;
/**

* this class is a servlet which implements the functionality that add items to cart

* @author Zhibo Zhang


*/
public class AddToCart extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cdid=req.getParameter("cdid");
		String quantity=req.getParameter("quantity");
		String cookie=req.getParameter("aj");
		PrintWriter out = resp.getWriter();
		Map<String, Object> result = new HashMap<String, Object>();
		if(!cdid.equals("")){
			
			ClientConfig config = new ClientConfig();
		    Client client = ClientBuilder.newClient(config);
		    WebTarget target = client.target(GetBaseURL.getProductBaseURI()+"/rest/getCD");
		    WebTarget targetnew = target.queryParam("cdid", cdid);
		    String cdstr= targetnew.request().accept(MediaType.APPLICATION_JSON).get(String.class);

		    List<CD> cdList = JSONArray.parseArray(cdstr, CD.class);
		    CD cd=cdList.get(0);
		    
			Cart cart = null;
			String result_str;
			
			if(!cookie.equals("")){
				List<Cart> cartList = JSONArray.parseArray(cookie, Cart.class);
				for(int i=0;i<cartList.size();i++){
					int oldcdid=cartList.get(i).getCdid();
					if(oldcdid==Integer.parseInt(cdid)){
						int oldq=cartList.get(i).getQuantity();
						cart=new Cart(cd.getCdid(), cd.getTitle(), cd.getArtist(), cd.getPrice(), cd.getStock(), cd.getImgurl(),oldq+Integer.parseInt(quantity));
						cartList.remove(i);
						break;
					}else{
						cart=new Cart(cd.getCdid(), cd.getTitle(), cd.getArtist(), cd.getPrice(), cd.getStock(), cd.getImgurl(),Integer.parseInt(quantity));
						
					}
				}
				cartList.add(cart);
				
				result.put("cookies", JSON.toJSONString(cartList,SerializerFeature.DisableCircularReferenceDetect));

			}else{
				List<Cart> cartList =new ArrayList<Cart>();
				cart=new Cart(cd.getCdid(), cd.getTitle(), cd.getArtist(), cd.getPrice(), cd.getStock(), cd.getImgurl(),Integer.parseInt(quantity));
				cartList.add(cart);
				result.put("cookies", JSON.toJSONString(cartList,SerializerFeature.DisableCircularReferenceDetect));
				
			}
			result_str = JSON.toJSONString(result,SerializerFeature.DisableCircularReferenceDetect);
			out.print(result_str);
		
		
		}
}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);

	}
}