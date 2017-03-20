package edu.carleton.comp.cdstore.order.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;

import edu.carleton.comp.cdstore.dao.ShippingDAO;
/**

* this class is a web service which implements the functionality that get shipping method list

* @author Zhibo Zhang


*/
@Path("/getShippingMethod")
public class GetShippingMethod {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String getShippingMethod() {
		Map<String, Object> result=new HashMap<String,Object>();
		ShippingDAO dao=new ShippingDAO();
		List<Object> method=dao.findall();
		dao.destory();
		result.put("result", method);
		String result_str = JSON.toJSONString(result);
	    return result_str;
	  }

}

