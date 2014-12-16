package viewsPreprocessing;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import dataAccess.RangeDataAccess;
import dataAccess.ReservationDataAccess;

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
		List<model.Range> ranges = getRangeBeans();
		// TODO Auto-generated method stub
		// place your code here
		ReservationDataAccess dao = new ReservationDataAccess();
		
		List<String> carTypes = null;
		List<String> destinations = null;
		try {
			carTypes = dao.getReservationTypes();
			destinations = dao.getDestinations();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getServletContext().setAttribute("destinations", destinations);
		request.getServletContext().setAttribute("carTypes", carTypes);
		request.getServletContext().setAttribute("ranges", ranges);
		//HttpServletRequest httpRequest = (HttpServletRequest) request;
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	private List<model.Range> getRangeBeans() {
		List<model.Range> ranges = new ArrayList<model.Range> ();
		RangeDataAccess dao = new RangeDataAccess();
		ranges = dao.getRanges();
		return ranges;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
