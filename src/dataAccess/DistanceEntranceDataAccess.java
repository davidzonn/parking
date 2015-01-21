package dataAccess;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.DistanceEntrance;
public class DistanceEntranceDataAccess {
	DBConnect connection = new DBConnect();
	EntityManager em = connection.getEntityManager();
	public DistanceEntrance findDistanceEntranceByName(String name) {
		String jpql = "SELECT d FROM DistanceEntrance AS d WHERE d.distanceName = :name";
		TypedQuery query = em.createQuery(jpql, DistanceEntrance.class).setParameter("name", name); 
		try {
			return (DistanceEntrance)query.getSingleResult();
		} catch(NoResultException exc) {
			return null;
		}
	}
}
