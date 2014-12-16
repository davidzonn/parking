package viewsProcessing;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import businessLogic.*;
/**
 * Servlet implementation class ServletExample
 */
@WebServlet("/processLogin")
public class ProcessLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserManager userManager = new UserManager();
		if (userManager.isValidUser(username, password)) {
			User user = new User();
			user.setUsername(username);
			request.getSession().setAttribute("user", user);
			if (userManager.isAdmin(username)) {
				request.getSession().setAttribute("isAdmin", true);
				response.sendRedirect("admin.jsp");				
			} else {
				response.sendRedirect("user.jsp");
			}
		} else {
			response.sendRedirect("user.jsp");
		}
	}
}
