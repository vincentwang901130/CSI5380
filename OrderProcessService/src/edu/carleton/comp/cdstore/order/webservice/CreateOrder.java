package edu.carleton.comp.cdstore.order.webservice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import edu.carleton.comp.cdstore.models.Order;
import edu.carleton.comp.cdstore.dao.OrderDAO;
/**

* this class is a web service which implements the functionality that create new order

* @author Zhibo Zhang


*/
@Path("/createOrder")
public class CreateOrder {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String createOrderInfo(@QueryParam("userid") int userid,
			@QueryParam("shipid") int shipid,@QueryParam("total") float total) {
		Map<String, Object> result =new HashMap<String, Object>();
			OrderDAO orderdao=new OrderDAO();
			/*setting format for the timestamp*/
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS",Locale.ENGLISH);
			/*partially ignore the input format*/
			dateFormat.setLenient(false);   
			String date=dateFormat.format(new Date());
			Order order;
			int orderid=0;
			order=new Order(date, "0", total, userid,0,0,shipid,1);
			orderid=orderdao.createandgetkeynull(order);
			orderdao.destory();
		result.put("orderid", orderid);
		String result_str = JSON.toJSONString(result,SerializerFeature.DisableCircularReferenceDetect);
		return result_str;


	  }
	
	

}
