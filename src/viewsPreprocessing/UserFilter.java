package viewsPreprocessing;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import model.Destination;
import model.TypeReservation;
import dataAccess.RangeDataAccess;
import dataAccess.ReservationDataAccess;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter(urlPatterns = {"/*"})
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
		ReservationDataAccess dao = new ReservationDataAccess();
		List<TypeReservation> types = dao.getReservationTypes();
		List<Destination>destinations = dao.getDestinations();
		request.getServletContext().setAttribute("destinations", destinations);
		request.getServletContext().setAttribute("carTypes", types);
		request.getServletContext().setAttribute("ranges", ranges);
		//HttpServletRequest httpRequest = (HttpServletRequest) request;
		// pass the request along the filter chain
		System.out.println(ranges + "HOLA");
		chain.doFilter(request, response);
	}

	private List<model.Range> getRangeBeans() {
		List<model.Range> ranges;
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
