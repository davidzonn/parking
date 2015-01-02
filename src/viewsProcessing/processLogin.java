package viewsProcessing;


import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.DBConnect;
import dataAccess.UserDataAccess;
import services.*;
import model.User;
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
		String root = "/parking";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDataAccess dao = new UserDataAccess();
		User user = dao.getUser(username, password);
		String path = root;
		if (user != null) {
			request.getSession().setAttribute("user", user);
			if (user.getAccessLevel() == 2) {
				path += "/admin.jsp";
			} else if (user.getAccessLevel() == 3) {
				path += "/super.jsp";
			}
		}
		response.sendRedirect(path);
	}
}
