package edu.carleton.comp.cdstore.product.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;

import edu.carleton.comp.cdstore.dao.CDDAO;
/**

* this class is a web service which implements the functionality that get cd catalog

* @author Zhibo Zhang


*/
@Path("/getProductList")
public class GetProductList {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String getProductList(@QueryParam("menu") String menu,
			@QueryParam("content") String content,
			@QueryParam("key") String key,
			@QueryParam("title") String title) {
		Map<String, Object> result=new HashMap<String,Object>();
		CDDAO dao=new CDDAO();
		List<Object> cdList=new ArrayList<>();
		if(!content.equals("") && !key.equals("") && !menu.equals("")){
			//advanced search and by order
			cdList=dao.advancesearch(menu,content,key);
		}else if(!content.equals("") && key.equals("") && !menu.equals("")){
			//only advanced search
			cdList=dao.advancesearch(content,menu);
		}else if(content.equals("") && !key.equals("") && !menu.equals("")){
			//advanced query by order
			cdList=dao.findorderbymenu(menu,key);
		}else if(content.equals("") && key.equals("") && !menu.equals("")){
			//normal query by menu and order by title
			cdList=dao.findbymenu(menu);
		}else if (menu.equals("") && content.equals("") && !key.equals("")){
			//query all order by key on index
			cdList=dao.findorderall(key);
		}else if (menu.equals("") && !content.equals("") && key.equals("")){
			//search all by content on index
			cdList=dao.defaultsearch(content);
		}
		dao.destory();
		result.put("cdList", cdList);
		result.put("title", title);
		result.put("menu", menu);
		String result_str = JSON.toJSONString(result);
	    return result_str;
	  }

}
