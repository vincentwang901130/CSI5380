package edu.carleton.comp.cdstore.order.webservice;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import edu.carleton.comp.cdstore.models.Address;
import edu.carleton.comp.cdstore.dao.AddressDAO;
import edu.carleton.comp.cdstore.models.Bill;
import edu.carleton.comp.cdstore.dao.BillDAO;
import edu.carleton.comp.cdstore.dao.OrderDAO;
/**

* this class is a web service which implements the functionality that update order's billing address info

* @author Zhibo Zhang


*/
@Path("/updateBillAddress")
public class UpdateBillAddress {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String updateBillAddress(@QueryParam("orderid") int orderid,
			@QueryParam("addrid") int addrid) {
		Map<String, Object> result=new HashMap<String,Object>();

		AddressDAO adao=new AddressDAO();
		Address address=(Address) adao.findByPrimaryKey(addrid);
		adao.destory();
		
		Bill bill=new Bill(address.getFullname(),address.getAddrline1(),address.getAddrline2(),address.getCity(),address.getProvince(),address.getZipcode(),address.getCountry(),address.getUserid());
		//create new bill address
		BillDAO bdao=new BillDAO();
		int billid=bdao.createandgetkey(bill);
		bdao.destory();
		
		OrderDAO odao=new OrderDAO();
		odao.updatebilladdr(orderid,billid);
		odao.destory();
		
		result.put("orderid", orderid);
		result.put("billid", billid);
		
		String result_str = JSON.toJSONString(result,SerializerFeature.DisableCircularReferenceDetect);
	    return result_str;
	  }

}

