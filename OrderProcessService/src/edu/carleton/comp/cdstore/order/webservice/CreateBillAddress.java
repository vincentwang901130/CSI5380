package edu.carleton.comp.cdstore.order.webservice;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;

import edu.carleton.comp.cdstore.models.Bill;
import edu.carleton.comp.cdstore.dao.BillDAO;
import edu.carleton.comp.cdstore.dao.OrderDAO;
/**

* this class is a web service which implements the functionality that create new billing address

* @author Zhibo Zhang


*/
@Path("/createBillAddress")
public class CreateBillAddress {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String creatteBillAddress(@QueryParam("orderid") int orderid,
			@QueryParam("userid") int userid,
			@QueryParam("fullname") String fullname,
			@QueryParam("addr1") String addr1,
			@QueryParam("addr2") String addr2,
			@QueryParam("city") String city,
			@QueryParam("province") String province,
			@QueryParam("zipcode") String zipcode) {
		Map<String, Object> result=new HashMap<String,Object>();
		Bill bill=new Bill(fullname,addr1,addr2,city,province,zipcode,"Canada",userid);
		//create new bill address
		BillDAO bdao=new BillDAO();
		int billid=bdao.createandgetkey(bill);
		bdao.destory();
		
		//update order
		OrderDAO odao=new OrderDAO();
		odao.updatebilladdr(orderid,billid);
		odao.destory();
		
		result.put("orderid", orderid);
		result.put("billid", billid);
		
		String result_str=JSON.toJSONString(result);
	    return result_str;
	  }

}
