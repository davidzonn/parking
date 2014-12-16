package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPADataAccess {
	EntityManagerFactory entityManagerFactory;
	public JPADataAccess() {
	    entityManagerFactory = Persistence.createEntityManagerFactory( "parking" );
	}
	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
	public Query getQuery(String sql) {
		EntityManager em = getEntityManager();
		Query query = em.createNativeQuery(sql);
		return query;
	}
	public Query getQuery(String sql, Class type) {
		EntityManager em = getEntityManager();
		Query query = em.createNativeQuery(sql);
		return query;
	}
}
