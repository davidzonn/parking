package dataAccess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.json.simple.JSONObject;

import model.DistanceEntrance;
import model.Parking;
import model.ParkingPlace;
import model.Status;
import model.TypeReservation;

public class ParkingDataAccess {
	EntityManager em = dataAccess.DBConnect.getEntityManager();
	int parkingPlaceNumber;
	public Collection<Parking> getParkings() {
		String jpql = "SELECT p FROM Parking AS p";
		TypedQuery<Parking> query = em.createQuery(jpql, Parking.class); 
		List<Parking> results = query.getResultList();
		return results;
	}
	/**
	 * Creates a parking in the DB
	 * @param numVehicles
	 * @param parkingName
	 * @return The Newly created parking.
	 */
	public Parking createNewParking(int numVehicles, String parkingName) {
		parkingPlaceNumber = 1;
		em.getTransaction().begin();
		Parking parking = new Parking();
		parking.setParkingName(parkingName);
		int numTrucks = (int) (numVehicles * 0.1);
		int numVIP = (int) (numVehicles * 0.1);
		int numRegular = numVehicles - numTrucks - numVIP;
		List<ParkingPlace> parkingPlaces = new ArrayList<ParkingPlace>();
		parking.setParkingPlaces(parkingPlaces);
		add(parking, parkingPlaces, "Car", numRegular);
		add(parking, parkingPlaces, "Truck", numTrucks);
		add(parking, parkingPlaces, "VIP", numVIP);
		em.persist(parking);
		em.flush();
		em.getTransaction().commit();
//		saveToDB(parking);
		System.out.println("commited");
		return parking;
	}
	private void add(Parking parking, List<ParkingPlace> parkingPlaces, String statusName, int n) {
		StatusDataAccess dao = new StatusDataAccess();
		Status status = dao.findStatusByName("Empty");
		DistanceEntranceDataAccess daoDE = new DistanceEntranceDataAccess();
		DistanceEntrance de = daoDE.findDistanceEntranceByName("Average");
		TypeReservationDataAccess typeDAO = new TypeReservationDataAccess();
		TypeReservation type = typeDAO.findTypeReservationByName(statusName);
		for (int i = 0; i < n; i++) {
			ParkingPlace place = new ParkingPlace();
			place.setStatus(status);
			place.setDistanceEntrance(de);
			place.setTypeReservation(type);
			place.setParking(parking);
			place.setParkingPlaceNumber(parkingPlaceNumber++);
			parking.addParkingPlace(place);
		}
	}
}
