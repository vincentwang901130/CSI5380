package edu.carleton.comp.cdstore.order.webservice;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;

import edu.carleton.comp.cdstore.dao.CustomerDAO;
import edu.carleton.comp.cdstore.models.Customer;
/**

* this class is a web service which implements the functionality that login

* @author Zhibo Zhang


*/
@Path("/login")
public class Login {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String login(@QueryParam("email") String email,
			@QueryParam("password") String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		CustomerDAO dao=new CustomerDAO();
		String result_str;
		Customer customer=(Customer)dao.findByPrimaryKey(email);
		dao.destory();
		if(customer==null){
			result.put("code", "Invalid account!");
			
		}else if(!password.equals(customer.getPassword())){
			result.put("code", "Invalid Password. Please try again!");
			result_str = JSON.toJSONString(result);
		}else{
		result.put("code", "success");
		}
		result_str = JSON.toJSONString(result);
		return result_str;
	  }
}
