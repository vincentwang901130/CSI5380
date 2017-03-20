package edu.carleton.comp.cdstore.order.webservice;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.carleton.comp.cdstore.dao.OrderDAO;
/**

* this class is a web service which implements the functionality that update order's shipping address info

* @author Zhibo Zhang


*/
@Path("/updateAddress")
public class UpdateAddress {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String updateAddress(@QueryParam("orderid") int orderid,
			@QueryParam("addrid") int addrid) {
		Map<String, Object> result=new HashMap<String,Object>();
		OrderDAO odao=new OrderDAO();
		odao.updateaddr(orderid,addrid);
		odao.destory();
		
		result.put("orderid", orderid);
		result.put("addrid", addrid);
		
		String result_str = JSON.toJSONString(result,SerializerFeature.DisableCircularReferenceDetect);
	    return result_str;
	  }

}

