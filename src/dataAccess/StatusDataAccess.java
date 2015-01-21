package dataAccess;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Parking;
import model.Status;

public class StatusDataAccess {
	DBConnect connection = new DBConnect();
	EntityManager em = connection.getEntityManager();
	public Status findStatusByName(String name) {
		String jpql = "SELECT s FROM Status AS s WHERE s.statusName = :name";
		TypedQuery query = em.createQuery(jpql, Status.class).setParameter("name", name); 
		try {
			return (Status)query.getSingleResult();
		} catch(NoResultException exc) {
			return null;
		}
	}
}
