package dataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import model.JPADataAccess;

public class ReservationDataAccess {
	public List<String> getReservationTypes() throws SQLException {
		List<String> reservations = new ArrayList<String>();
		String sql = "SELECT reservation_type FROM type_reservation";
		JPADataAccess jpa = new JPADataAccess();
		Query query = jpa.getQuery(sql);
		reservations = query.getResultList();
		return reservations;
	}

	public List<String> getDestinations() throws SQLException {
		List<String> destinations = new ArrayList<String>();
		String sql = "SELECT destination_name FROM destination";
		JPADataAccess jpa = new JPADataAccess();
		Query query = jpa.getQuery(sql);
		destinations = query.getResultList();
		/*
		DBConnect db = new DBConnect();
		ResultSet result = db.executeStatement(sql);
		while (result.next()) {
			destinations.add(result.getString(1));
		}*/
		return destinations;
	}
}
