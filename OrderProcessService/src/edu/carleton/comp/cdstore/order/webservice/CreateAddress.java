package edu.carleton.comp.cdstore.order.webservice;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;

import edu.carleton.comp.cdstore.models.Address;
import edu.carleton.comp.cdstore.dao.AddressDAO;
import edu.carleton.comp.cdstore.dao.OrderDAO;
/**

* this class is a web service which implements the functionality that create new shipping address

* @author Zhibo Zhang


*/
@Path("/createAddress")
public class CreateAddress {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String createAddress(@QueryParam("orderid") int orderid,
			@QueryParam("userid") int userid,
			@QueryParam("fullname") String fullname,
			@QueryParam("addr1") String addr1,
			@QueryParam("addr2") String addr2,
			@QueryParam("city") String city,
			@QueryParam("province") String province,
			@QueryParam("zipcode") String zipcode,
			@QueryParam("phone") String phone) {
		Map<String, Object> result=new HashMap<String,Object>();
		Address address=new Address(fullname,addr1,addr2,city,province,zipcode,"Canada",phone,userid);
		//create new address
		AddressDAO adao=new AddressDAO();
		int addrid=adao.createandgetkey(address);
		adao.destory();
		
		//update order
		OrderDAO odao=new OrderDAO();
		odao.updateaddr(orderid,addrid);
		odao.destory();
		
		result.put("orderid", orderid);
		result.put("addrid", addrid);
		String result_str=JSON.toJSONString(result);
	    return result_str;
	  }

}
