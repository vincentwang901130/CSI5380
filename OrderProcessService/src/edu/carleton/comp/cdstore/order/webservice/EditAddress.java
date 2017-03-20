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

import edu.carleton.comp.cdstore.models.Address;
import edu.carleton.comp.cdstore.models.Customer;
import edu.carleton.comp.cdstore.dao.CustomerDAO;
import edu.carleton.comp.cdstore.models.Order;
import edu.carleton.comp.cdstore.dao.AddressDAO;
import edu.carleton.comp.cdstore.dao.OrderDAO;
/**

* this class is a web service which implements the functionality that update shipping address

* @author Zhibo Zhang


*/
@Path("/editAddress")
public class EditAddress {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String editAddress(@QueryParam("account") String account,
			@QueryParam("fullname") String fullname,
			@QueryParam("addr1") String addr1,
			@QueryParam("addr2") String addr2,
			@QueryParam("city") String city,
			@QueryParam("province") String province,
			@QueryParam("zipcode") String zipcode,
			@QueryParam("phone") String phone) {
		Map<String, Object> result=new HashMap<String,Object>();
		CustomerDAO customerdao=new CustomerDAO();
		CustomerDAO dao=new CustomerDAO();
		Customer customer=(Customer)dao.findByPrimaryKey(account);
		int userid=customer.getUserid();
		customerdao.destory();
		
		OrderDAO odao=new OrderDAO();
		List<Object> orderList=odao.getrecentorder(userid);
		int orderid=((Order)orderList.get(0)).getOrderid();
		
		Address address=new Address(fullname,addr1,addr2,city,province,zipcode,"Canada",phone,userid);
		//create new address
		AddressDAO adao=new AddressDAO();
		int addrid=adao.createandgetkey(address);
		Address addressf= (Address) adao.findByPrimaryKey(addrid);
		adao.destory();
		
		//update order
		odao.updateaddr(orderid,addrid);
		odao.destory();
		
		result.put("address", addressf);
		String result_str=JSON.toJSONString(result);
	    return result_str;
	  }

}
