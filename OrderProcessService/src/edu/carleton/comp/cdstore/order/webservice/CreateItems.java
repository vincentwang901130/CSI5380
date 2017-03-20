package edu.carleton.comp.cdstore.order.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import edu.carleton.comp.cdstore.dao.CDDAO;
import edu.carleton.comp.cdstore.models.Item;
import edu.carleton.comp.cdstore.dao.ItemDAO;
/**

* this class is a web service which implements the functionality that create order items list

* @author Zhibo Zhang


*/
@Path("/createItems")
public class CreateItems {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public void createItems(@QueryParam("quantity") int quantity,
			@QueryParam("orderid") int orderid,
			@QueryParam("cdid") int cdid) {
		
		ItemDAO itemdao=new ItemDAO();
		Item item=new Item(quantity, orderid, cdid);
		itemdao.create(item);
		itemdao.destory();
		CDDAO cddao1=new CDDAO();
		//update stock
		//int cdid=successfulone.get(i).getCdid();
		int orign=cddao1.getstock(cdid);
		int newstock=orign-quantity;
		cddao1.updatestock(newstock, cdid);
		cddao1.destory();
	  }
	
	

}
