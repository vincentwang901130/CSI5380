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

import edu.carleton.comp.cdstore.models.Bill;
import edu.carleton.comp.cdstore.dao.BillDAO;
import edu.carleton.comp.cdstore.models.Customer;
import edu.carleton.comp.cdstore.dao.CustomerDAO;
import edu.carleton.comp.cdstore.models.Order;
import edu.carleton.comp.cdstore.dao.AddressDAO;
import edu.carleton.comp.cdstore.dao.OrderDAO;
import edu.carleton.comp.cdstore.models.Address;
/**

* this class is a web service which implements the functionality that query shipping address info and billing address info to support confirm address

* @author Zhibo Zhang


*/
@Path("/getAddressInfo")
public class GetAddressInfo {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String getAddressInfo(@QueryParam("account") String account) {
		Map<String, Object> result=new HashMap<String,Object>();
		CustomerDAO customerdao=new CustomerDAO();
		CustomerDAO dao=new CustomerDAO();
		Customer customer=(Customer)dao.findByPrimaryKey(account);
		int userid=customer.getUserid();
		customerdao.destory();
		
		OrderDAO odao=new OrderDAO();
		List<Object> orderList=odao.getrecentorder(userid);
		int addrid=((Order)orderList.get(0)).getAddrid();
		int billid=((Order)orderList.get(0)).getBillid();
		odao.destory();
		
		AddressDAO adao=new AddressDAO();
		BillDAO bdao=new BillDAO();
		Address address= (Address) adao.findByPrimaryKey(addrid);
		Bill bill=(Bill) bdao.findByPrimaryKey(billid);
		adao.destory();
		bdao.destory();
		result.put("address", address);
		result.put("bill", bill);
		String result_str=JSON.toJSONString(result);
	    return result_str;
	  }

}
