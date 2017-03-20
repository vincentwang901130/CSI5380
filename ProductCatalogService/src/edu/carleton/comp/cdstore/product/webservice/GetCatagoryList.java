package edu.carleton.comp.cdstore.product.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;

import edu.carleton.comp.cdstore.dao.CategoryDAO;
/**

* this class is a web service which implements the functionality that get cd's category list

* @author Zhibo Zhang


*/
@Path("/getCategoryList")
public class GetCatagoryList {

  @GET
  @Produces({MediaType.APPLICATION_JSON })
  public String getCategoryList() {
	  	CategoryDAO dao=new CategoryDAO();
	  	List<Object> categoryList=dao.findall();
		dao.destory();
		String categorystr=JSON.toJSONString(categoryList);
    return categorystr;
  }


} 