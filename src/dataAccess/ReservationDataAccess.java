package dataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDataAccess {
	public List<String> getReservationTypes() throws SQLException {
		List<String> reservations = new ArrayList<String>();
		String sql = "SELECT reservation_type FROM type_reservation";
		DBConnect db = new DBConnect();
		ResultSet result = db.executeStatement(sql);
		while (result.next()) {
			reservations.add(result.getString(1));
		}
		return reservations;
	}

	public List<String> getDestinations() throws SQLException {
		List<String> destinations = new ArrayList<String>();
		String sql = "SELECT destination_name FROM destination";
		DBConnect db = new DBConnect();
		ResultSet result = db.executeStatement(sql);
		while (result.next()) {
			destinations.add(result.getString(1));
		}
		return destinations;
	}
}
