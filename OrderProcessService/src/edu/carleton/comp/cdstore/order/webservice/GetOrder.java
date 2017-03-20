package edu.carleton.comp.cdstore.order.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;

import edu.carleton.comp.cdstore.models.Customer;
import edu.carleton.comp.cdstore.dao.CustomerDAO;
import edu.carleton.comp.cdstore.models.Order;
import edu.carleton.comp.cdstore.dao.OrderDAO;
/**

* this class is a web service which implements the functionality that get order info

* @author Zhibo Zhang


*/
@Path("/getOrder")
public class GetOrder {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String getOrder(@QueryParam("account") String account) {
		Map<String, Object> result=new HashMap<String,Object>();
		CustomerDAO customerdao=new CustomerDAO();
		CustomerDAO dao=new CustomerDAO();
		Customer customer=(Customer)dao.findByPrimaryKey(account);
		int userid=customer.getUserid();
		customerdao.destory();
		OrderDAO odao=new OrderDAO();
		List<Object> orderList=odao.getrecentorder(userid);
		int orderid=((Order)orderList.get(0)).getOrderid();
		odao.destory();
		result.put("orderid", orderid);
		String result_str = JSON.toJSONString(result);
	    return result_str;
	  }
}
