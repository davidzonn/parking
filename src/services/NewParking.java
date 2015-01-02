package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Parking;

import org.json.simple.JSONObject;

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
		Parking parking = dao.createNewParking(numVehicles, parkingName);
		response.setContentType("text/json"); //we'll return a parking object in JSON format.
        String json = toJson(parking);
        //System.out.println(json);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}

	private String toJson(Parking parking) {
		JSONObject obj = new JSONObject();
		System.out.println(parking.getIdParking());
		obj.put("id", parking.getIdParking());
		obj.put("name", parking.getParkingName());
		return obj.toJSONString();
	}
}
