package edu.carleton.comp.cdstore.product.webservice;


import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;

import edu.carleton.comp.cdstore.models.CD;
import edu.carleton.comp.cdstore.dao.CDDAO;
import edu.carleton.comp.cdstore.models.Category;
import edu.carleton.comp.cdstore.dao.CategoryDAO;
/**

* this class is a web service which implements the functionality that get cd info by its id

* @author Zhibo Zhang


*/
@Path("/getProductInfo")
public class GetProductInfo {

  @GET
  @Produces({MediaType.APPLICATION_JSON })
  public String getProductInfo(@QueryParam("cdid") int cdid) {
	  	CDDAO dao=new CDDAO();
		CD cd=(CD)(dao.findByPrimaryKey(cdid));
		dao.destory();
		CategoryDAO dao1=new CategoryDAO();
		Category cate=(Category)(dao1.findByPrimaryKey(cd.getCateid()));
		dao1.destory();
		Object[] cdfinal={cd.getCdid(),cd.getTitle(),cd.getArtist(),cd.getDate(),cd.getIntro(),cd.getPrice(),cd.getStock(),cd.getImgurl(),cate.getCatename()};
		Map<String, Object> result=new HashMap<String,Object>();
		result.put("result", cdfinal);
		String result_str=JSON.toJSONString(result);
    return result_str;
  }


} 