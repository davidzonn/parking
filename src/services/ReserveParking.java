package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Operation;
import model.Parking;
import model.User;
import dataAccess.ParkingDataAccess;
import dataAccess.ReservationDataAccess;


/**
 * Servlet implementation class AssignAdmin
 */
@WebServlet("/ReserveParking")
public class ReserveParking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveParking() {
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
		request.getParameter("PARAMETERNAME");
		String parkingName = request.getParameter("parking");
		ParkingDataAccess pdao = new ParkingDataAccess();
		Parking parking = pdao.findParkingByName(parkingName);
		String reservationType = request.getParameter("type");
		String fromDate = request.getParameter("fromDate");
		String fromTime = request.getParameter("fromTime");
		String toDate = request.getParameter("toDate");
		String toTime = request.getParameter("toTime");
		
		User user = (User)request.getSession().getAttribute("user");
		long from = util.TimeStamp.getTimeStamp(fromDate, fromTime);
		long to = util.TimeStamp.getTimeStamp(toDate, toTime);
		ReservationDataAccess dao = new ReservationDataAccess();
		Operation operation = new Operation();
		operation.setUser(user);
		user.addOperation(operation);
		dao.makeReservation(operation, parking, from, to, reservationType);
		String transportation = request.getParameter("transporation");
		if (Boolean.parseBoolean(transportation)) {
			String transportationType = request.getParameter("transportationType");
			if (transportationType == "regular") {
				makeRegularReservation(operation, request);
			} else {
				makeOnDemandReservation(operation, request);
			}
		}
		dao.persist(operation);
		/*
			parking: parking,
			type: reservationType,
			fromDate: fromDate,
			fromTime: fromTime,
			toDate: toDate,
			toTime: toTime,
			onDemandRange: onDemandRange,
			onDemandDate : onDemandDate,
			onDemandTime : onDemandTime,
			regularDestination : regularDestination,
			transporation : transportationRequired,
			transportationType : transporationType
		*/
	}

	private void makeOnDemandReservation(Operation operation,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	private void makeRegularReservation(Operation operation,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

}
