package edu.carleton.comp.cdstore.product.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import edu.carleton.comp.cdstore.dao.CDDAO;
import edu.carleton.comp.cdstore.models.CD;

/**

* this class is a web service which implements the functionality that get cd info by its id

* @author Zhibo Zhang


*/
@Path("/getCD")
public class GetCD {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public String getCD(@QueryParam("cdid") String cdid) {
			CDDAO dao=new CDDAO();
			Object cdob=dao.findByPrimaryKey(Integer.parseInt(cdid));
			CD cd=(CD) cdob;
			dao.destory();
			String result_str;
			List<CD> cdList=new ArrayList<CD>();
			cdList.add(cd);
			result_str = JSON.toJSONString(cdList,SerializerFeature.DisableCircularReferenceDetect);
			return result_str;
		
		}	
}

