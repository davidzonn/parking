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
		UserManager userManager = new UserManager();
		RequestDispatcher dispatcher = null;
		if (userManager.isValidUser(username, password)) {
			User user = new User(username);
			request.setAttribute("user", user);
			dispatcher = request.getRequestDispatcher(
					userManager.isAdmin(username)?"admin.jsp":"user.jsp");
		}
		dispatcher.forward(request, response);
	}
}
