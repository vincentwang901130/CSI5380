package edu.carleton.comp.cdstore.util;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;
/**

* this class is a utility class which provide the method to get web services' url

* @author Zhibo Zhang


*/
public class GetBaseURL {
	public static URI getProductBaseURI() {
	    return UriBuilder.fromUri("http://localhost:8080/ProductCatalogService").build();
	  }
	
	public static URI getOrderBaseURI() {
	    return UriBuilder.fromUri("http://localhost:8080/OrderProcessService").build();
	  }

}
