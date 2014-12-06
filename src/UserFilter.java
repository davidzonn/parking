

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter("/user.jsp")
public class UserFilter implements Filter {

    /**
     * Default constructor.
     */
    public UserFilter() {
        // TODO Auto-generated constructor stub
    	System.out.println("HOLA!");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		ArrayList<String> carTypes = new ArrayList<String>();
		carTypes.add("tipo1");
		carTypes.add("tipo");
		request.getServletContext().setAttribute("carTypes", carTypes);
		//HttpServletRequest httpRequest = (HttpServletRequest) request;
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
