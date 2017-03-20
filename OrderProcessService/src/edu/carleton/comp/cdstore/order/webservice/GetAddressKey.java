package edu.carleton.comp.cdstore.order.webservice;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;

import edu.carleton.comp.cdstore.dao.AddressDAO;
/**

* this class is a web service which implements the functionality that query shipping address info by its id

* @author Zhibo Zhang


*/
@Path("/getAddressKey")
public class GetAddressKey {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String getAddressKey(@QueryParam("addrid") String addrid) {
		Map<String, Object> result=new HashMap<String,Object>();
		AddressDAO adao=new AddressDAO();
		Object address=adao.findByPrimaryKey(Integer.valueOf(addrid));
		adao.destory();
		result.put("address", address);
		String result_str = JSON.toJSONString(result);
	    return result_str;
	  }

}
