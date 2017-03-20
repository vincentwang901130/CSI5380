package edu.carleton.comp.cdstore.order.webservice;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;

import edu.carleton.comp.cdstore.models.Customer;
import edu.carleton.comp.cdstore.dao.CustomerDAO;
/**

* this class is a web service which implements the functionality that query customer info by username

* @author Zhibo Zhang


*/
@Path("/getCustomer")
public class GetCustomer {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String getCustomer(@QueryParam("email") String email) {
		CustomerDAO dao=new CustomerDAO();
		Customer customer=(Customer)dao.findByPrimaryKey(email);
		dao.destory();
		Map<String, Object> result=new HashMap<String,Object>();
		result.put("account",customer);
		String result_str=JSON.toJSONString(result);
	    return result_str;
	  }

}
