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

* this class is a web service which implements the functionality that create new account

* @author Zhibo Zhang


*/
@Path("/register")
public class CreateAccount {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String register(@QueryParam("firstname") String firstname,
			@QueryParam("lastname") String lastname,
			@QueryParam("email") String email,
			@QueryParam("sex") String sex,
			@QueryParam("password") String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		Customer c =new Customer(password,firstname,lastname,email,sex);
		CustomerDAO cd=new CustomerDAO();
		boolean flag=cd.create(c);
		cd.destory();
		String result_str;
		if(flag==true){
				result.put("code", "success");
				result_str = JSON.toJSONString(result);
		}else{
			result.put("code", "User Already Existed!");
			result_str = JSON.toJSONString(result);    
		}
		return result_str;
	  }

}
