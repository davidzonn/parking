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

import model.Parking;
import model.User;
import dataAccess.UserDataAccess;
/**
 * Servlet Filter implementation class SuperFilter
 */
@WebFilter(urlPatterns = "/admin.jsp")
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
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
		HttpServletRequest request = (HttpServletRequest)req;
		User user = (User) request.getSession().getAttribute("user");
		System.out.println("Excecuting admin filter.");
		UserDataAccess dao = new UserDataAccess();
		Collection<Parking> parkings = dao.getAdminParkings(user);
		System.out.println(parkings);
		request.getSession().setAttribute("adminParkings", parkings);
		request.getSession().setAttribute("user", user);
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
