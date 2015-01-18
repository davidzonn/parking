package dataAccess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.DistanceEntrance;
import model.Parking;
import model.ParkingPlace;
import model.Status;
import model.TypeReservation;
import model.User;

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
		em.close();
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
	public void assignAdmin(int parkingID, String adminName) {
		Parking p = em.find(Parking.class, parkingID);
		UserDataAccess daoUser = new UserDataAccess();
		User u = daoUser.findUserByName(adminName);
		p.setUser(u);
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
	}
	public Parking findParkingByName(String parkingName) {
		String jpql = "SELECT p FROM Parking AS p WHERE p.parkingName = :pName";
		TypedQuery<Parking> query = em.createQuery(jpql, Parking.class);
		query = query.setParameter("pName", parkingName);
		List<Parking> parkings = query.getResultList();
		return parkings.isEmpty()? null: parkings.get(0);
	}
	/**
	 * gets the first free place from the Database for a given parking and a given reservation type.
	 * @param parking
	 * @param reservationType
	 * @return
	 */
	public ParkingPlace getPlace(Parking parking, String reservationType) {
		String jpql = "SELECT place FROM ParkingPlace As place WHERE place.typeReservation.reservationType = :type AND place.parking = :parking AND place.status.statusName = :placeName";
		TypedQuery<ParkingPlace> query = em.createQuery(jpql, ParkingPlace.class);
		
		query = (query.setParameter("parking", parking).setParameter("type", reservationType).setParameter("placeName", "Empty"));
		List<ParkingPlace> places = query.getResultList();
		return places.get(0);
	}
}
