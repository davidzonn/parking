package viewsProcessing;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.*;
import beans.*;
/**
 * Servlet implementation class ServletExample
 */
@WebServlet("/processLogin")
public class processLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public processLogin() {
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

		User user = new User(username, password);
		UserManager userManager = new UserManager(user);
		RequestDispatcher dispatcher;
		
		if (userManager.isValidUser()) {
			request.setAttribute("user", user);
			if (userManager.isAdmin()) {
				dispatcher = request.getRequestDispatcher("admin.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("user.jsp");
			}
			dispatcher.forward(request, response);
		} else {
			dispatcher = request.getRequestDispatcher("main.jsp");
		}
		dispatcher.forward(request, response);
		
	}
}
