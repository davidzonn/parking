package dataAccess;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Status;
import model.TypeReservation;

public class TypeReservationDataAccess {
	DBConnect connection = new DBConnect();
	EntityManager em = connection.getEntityManager();
	public TypeReservation findTypeReservationByName(String name) {
		String jpql = "SELECT tr FROM TypeReservation AS tr WHERE tr.reservationType = :name";
		TypedQuery query = em.createQuery(jpql, TypeReservation.class).setParameter("name", name); 
		try {
			return (TypeReservation)query.getSingleResult();
		} catch(NoResultException exc) {
			return null;
		}
	}

}
