package dataAccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.DBConnect;
import model.Destination;
import model.Parking;
import model.Range;
import model.Status;
import model.TypeReservation;

public class ReservationDataAccess {
	EntityManager em = DBConnect.getEntityManager();
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
}
