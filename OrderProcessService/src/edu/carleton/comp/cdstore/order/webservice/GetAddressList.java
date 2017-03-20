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

import edu.carleton.comp.cdstore.dao.AddressDAO;
/**

* this class is a web service which implements the functionality that query shipping address list by userid

* @author Zhibo Zhang


*/
@Path("/getAddressList")
public class GetAddressList {

	@GET
	  @Produces({MediaType.APPLICATION_JSON })
	  public String getAddressList(@QueryParam("userid") int userid) {
		AddressDAO adao=new AddressDAO();
		List<Object> addressList=adao.findByUser(userid);
		adao.destory();
		Map<String, Object> result=new HashMap<String,Object>();
		result.put("addressList", addressList);
		String result_str=JSON.toJSONString(result);
	    return result_str;
	  }
}
