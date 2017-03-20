package edu.carleton.comp.cdstore.product.webservice;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;

import edu.carleton.comp.cdstore.dao.CDDAO;
/**

* this class is a web service which implements the functionality that get cd's stock information

* @author Zhibo Zhang


*/
@Path("/getStockInfo")
public class GetStockInfo {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String getStockInfo(@QueryParam("cdid") int cdid,@QueryParam("sold") int sold) {
		Map<String, Object> result=new HashMap<String,Object>();
		CDDAO cddao=new CDDAO();
		int orginstock=cddao.getstock(cdid);
		cddao.destory();
		if(orginstock-sold<0){
			result.put("result", false);
			String result_str=JSON.toJSONString(result);
			return result_str;
		}
		else
			result.put("result", true);
			String result_str=JSON.toJSONString(result);
			return result_str;
		}	
	
}

