package edu.carleton.comp.cdstore.order.webservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import edu.carleton.comp.cdstore.models.Shipping;
import edu.carleton.comp.cdstore.dao.ShippingDAO;
/**

* this class is a web service which implements the functionality that get shipping price details

* @author Zhibo Zhang


*/
@Path("/getPriceDetails")
public class GetPriceDetails {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String getPriceDetails(@QueryParam("shipid") int shipid) {
		Map<String, Object> result=new HashMap<String,Object>();
		ShippingDAO dao=new ShippingDAO();
		Object shipping=dao.findByPrimaryKey(shipid);
		dao.destory();
		Shipping ship=(Shipping) shipping;
		BigDecimal priceb=new BigDecimal(ship.getPrice());  
		double pricef=priceb.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		result.put("pricef", pricef);
		String result_str = JSON.toJSONString(result,SerializerFeature.DisableCircularReferenceDetect);
		return result_str;
	  }

}
