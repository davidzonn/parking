package viewsPreprocessing;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import dataAccess.ParkingDataAccess;
import model.Parking;
/**
 * Servlet Filter implementation class SuperFilter
 */
@WebFilter(urlPatterns = "/super.jsp")
public class SuperFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SuperFilter() {
        // TODO Auto-generated constructor stub
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
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Excecuting super filter.");
		HttpServletRequest request = (HttpServletRequest)req;
		ParkingDataAccess dao = new ParkingDataAccess();
		Collection<Parking> parkings = dao.getParkings();
		request.getSession().setAttribute("parkings", parkings);
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
