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
import edu.carleton.comp.cdstore.models.Customer;
import edu.carleton.comp.cdstore.dao.CustomerDAO;
import edu.carleton.comp.cdstore.models.Shipping;
import edu.carleton.comp.cdstore.dao.ShippingDAO;
import edu.carleton.comp.cdstore.models.Tax;
import edu.carleton.comp.cdstore.dao.TaxDAO;
/**

* this class is a web service which implements the functionality that get shipping address and billing address info from order

* @author Zhibo Zhang


*/
@Path("/getOrderInfo")
public class GetOrderInfo {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String getOrderInfo(@QueryParam("account") String account,
			@QueryParam("shipid") int shipid) {
		Map<String, Object> result =new HashMap<String, Object>();
		ShippingDAO shipdao =new ShippingDAO();
		Shipping shipping=(Shipping)(shipdao.findByPrimaryKey(shipid));
		shipdao.destory();
		float shippingfee=shipping.getPrice();
		String method=shipping.getMethod();
		
		TaxDAO taxdao=new TaxDAO();
		Tax tax=(Tax)(taxdao.findByPrimaryKey(1));
		taxdao.destory();
		float taxrate=tax.getTaxrate();
		
		CustomerDAO customerdao=new CustomerDAO();
		CustomerDAO dao=new CustomerDAO();
		Customer customer=(Customer)dao.findByPrimaryKey(account);
		int userid=customer.getUserid();
		customerdao.destory();
		
		result.put("method", method);
		result.put("taxrate", String.valueOf(taxrate));
		result.put("userid", String.valueOf(userid));
		result.put("shippingfee", String.valueOf(shippingfee));
		String result_str = JSON.toJSONString(result,SerializerFeature.DisableCircularReferenceDetect);
		return result_str;


	  }
	
	

}
