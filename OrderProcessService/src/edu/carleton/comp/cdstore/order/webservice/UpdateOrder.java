package edu.carleton.comp.cdstore.order.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import edu.carleton.comp.cdstore.dao.OrderDAO;
/**

* this class is a web service which implements the functionality that update order's status

* @author Zhibo Zhang


*/
@Path("/updateOrder")
public class UpdateOrder {
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public void updateOrder(@QueryParam("orderid") int orderid,
			@QueryParam("status") String status) {
		OrderDAO odao=new OrderDAO();
		odao.updatestatus(orderid, status);
		odao.destory();
		return;
	  }

}