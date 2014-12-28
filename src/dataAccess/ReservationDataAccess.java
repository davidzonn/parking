package dataAccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.DBConnect;
import model.Destination;
import model.Range;
import model.TypeReservation;

public class ReservationDataAccess {
	public List<TypeReservation> getReservationTypes(){
		List<TypeReservation> reservations;
		String jpql = "SELECT t FROM TypeReservation AS t";
		EntityManager em = DBConnect.getEntityManager();
		TypedQuery<TypeReservation> query = em.createQuery(jpql, TypeReservation.class);
		reservations = query.getResultList();
		return reservations;
	}

	public List<Destination> getDestinations() {
		List<Destination> destinations;
		String jpql = "SELECT d FROM Destination AS d";
		EntityManager em = DBConnect.getEntityManager();
		TypedQuery<Destination> query = em.createQuery(jpql, Destination.class);
		destinations = query.getResultList();
		return destinations;
	}
}
