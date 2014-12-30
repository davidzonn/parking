package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Parking;
import dataAccess.ParkingDataAccess;

/**
 * Servlet implementation class newParking
 */
@WebServlet("/NewParking")
public class NewParking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewParking() {
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
		int numVehicles = Integer.parseInt(request.getParameter("numVehicles"));
		String parkingName = request.getParameter("parkingName");
		ParkingDataAccess dao = new ParkingDataAccess();
		Parking p = dao.createNewParking(numVehicles, parkingName);
		response.setContentType("text/json"); //we'll return a parking object in JSON format.
		Gson gson = new Gson();
		String json = gson.toJson(p);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}

}
