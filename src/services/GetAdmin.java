package services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dataAccess.UserDataAccess;

/**
 * Servlet implementation class CheckLogin
 */
@WebServlet("/GetAdmin")
public class GetAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAdmin() {
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
		String a = request.getParameter("idParking");
		System.out.println(a);
		UserDataAccess dao = new UserDataAccess();
		String adminName = dao.getAdminName(Integer.parseInt(a));
		String jsonObject = createJson(adminName, dao.getAllAdminsNames());
		response.getWriter().write(jsonObject);
		response.setContentType("application/json");
		
	}
	private String createJson(String adminName, List<String> allAdminsNames) {
		JSONObject obj = new JSONObject();
		obj.put("currentAdmin", adminName);
		obj.put("admins", allAdminsNames);
		return obj.toJSONString();
	}
}
