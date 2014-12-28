package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DBConnect {
	public static final String DBNAME = "parking";
	//See also "META-INF/persistence.xml" if not using default configuration parameters (username "root", password "")
	private static EntityManagerFactory getEMFactory() {
		return Persistence.createEntityManagerFactory( DBNAME );
	}
	public static EntityManager getEntityManager() {
		return getEMFactory().createEntityManager();
	}
	public static Query getQuery(String sql) {
		EntityManager em = getEntityManager();
		Query query = em.createNativeQuery(sql);
		return query;
	}
	public static Query getQuery(String sql, Class<java.io.Serializable> type) {
		EntityManager em = getEntityManager();
		Query query = em.createNativeQuery(sql);
		return query;
	}
}
