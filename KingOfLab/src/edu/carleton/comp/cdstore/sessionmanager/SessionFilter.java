package edu.carleton.comp.cdstore.sessionmanager;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
/**

* this class is a filter which implements the functionality that avoid user invalid access to certain pages

* @author Zhibo Zhang


*/

public class SessionFilter implements Filter {

	public FilterConfig getConfig() {
		return config;
	}

	public void setConfig(FilterConfig config) {
		this.config = config;
	}

	public FilterConfig config;
    
    public void destroy() {
        this.config = null;
    }
    
    public static boolean isContains(String container, String[] regx) {
        boolean result = false;

        for (int i = 0; i < regx.length; i++) {
            if (container.indexOf(regx[i]) != -1) {
                return true;
            }
        }
        return result;
    }

    @SuppressWarnings("static-access")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hrequest = (HttpServletRequest)request;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
 
        String includeStrings = config.getInitParameter("includeStrings");
        String redirectPath = hrequest.getContextPath() + "/index.html";

        String[] includeList = includeStrings.split(";");
        
        if (this.isContains(hrequest.getRequestURI(), includeList)) {
        	 String user = ( String ) hrequest.getSession().getAttribute("account");
             if (user == null) {
                 wrapper.sendRedirect(redirectPath);
                 return; 
             }
            chain.doFilter(request, response);
        }

        
        chain.doFilter(request, response);
        
       
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }
}