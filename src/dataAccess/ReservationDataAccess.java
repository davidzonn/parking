package dataAccess;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Destination;
import model.Operation;
import model.Parking;
import model.ParkingPlace;
import model.RegularReservation;
import model.Status;
import model.TypeReservation;

public class ReservationDataAccess {
	DBConnect connection = new DBConnect();
	EntityManager em = connection.getEntityManager();
	public List<TypeReservation> getReservationTypes(){
		List<TypeReservation> reservations;
		String jpql = "SELECT t FROM TypeReservation AS t";
		TypedQuery<TypeReservation> query = em.createQuery(jpql, TypeReservation.class);
		reservations = query.getResultList();
		return reservations;
	}

	public List<Destination> getDestinations() {
		List<Destination> destinations;
		String jpql = "SELECT d FROM Destination AS d";
		TypedQuery<Destination> query = em.createQuery(jpql, Destination.class);
		destinations = query.getResultList();
		return destinations;
	}
	public TypeReservation findReservationTypeByName(String name) {
		String jpql = "SELECT rt FROM ReservationType AS rt WHERE reservationType = :name";
		TypedQuery query = em.createQuery(jpql, TypeReservation.class).setParameter("name", name); 
		try {
			return (TypeReservation)query.getSingleResult();
		} catch(NoResultException exc) {
			return null;
		}
	}

	public void makeReservation(Operation operation, Parking parking, long from, long to,
			String reservationType) {
		RegularReservation reservation = new RegularReservation();
		List<Operation> operations = new ArrayList<Operation>();
		reservation.setOperations(operations);
		operation.setRegularReservation(reservation);
		reservation.addOperation(operation);
		ParkingDataAccess pdao = new ParkingDataAccess();
		ParkingPlace parkingPlace = pdao.getPlace(parking, reservationType);
		reservation.setParkingPlace(parkingPlace);
		parkingPlace.addRegularReservation(reservation);
		Timestamp fromTS = new Timestamp(from);
		Timestamp now = getCurrentTimestamp();
		Timestamp toTS = new Timestamp(to);
		reservation.setStartts(fromTS);
		reservation.setEndts(toTS);
		if (included(now, fromTS, toTS)) {
			updateStatus(parkingPlace, "Occupied");
		}
	}

	private void updateStatus(ParkingPlace parkingPlace, String string) {
		String jpql = "UPDATE ParkingPlace AS pp SET pp.status = :status WHERE pp.idParkingPlace = :id";
		em.getTransaction().begin();
		Query query = em.createQuery(jpql);
		StatusDataAccess sdao = new StatusDataAccess();
		Status status = sdao.findStatusByName("Occupied");
		query.setParameter("status", status);
		query.setParameter("id", parkingPlace.getIdParkingPlace());
		query.executeUpdate();
		//em.persist(parkingPlace);
		em.flush();
		em.getTransaction().commit();
		//em.close();
	}

	private Timestamp getCurrentTimestamp() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		return currentTimestamp;
	}

	private boolean included(Timestamp now, Timestamp fromTS, Timestamp toTS) {
		System.out.println("FROM:" + fromTS);
		System.out.println("NOW:" + now);
		System.out.println("TO:" + toTS);
		return (now.compareTo(fromTS) >= 0 && now.compareTo(toTS) <= 0)? true: false;
	}

	public void persist(Operation operation) {
		em.getTransaction().begin();
		em.persist(operation);
		em.getTransaction().commit();
		//em.close();
	}
}
