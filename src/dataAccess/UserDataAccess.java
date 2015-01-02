package dataAccess;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Parking;
import model.User;

public class UserDataAccess {
	EntityManager em = DBConnect.getEntityManager();
	ResultSet result;
	DBConnect dao;
	public UserDataAccess() {
		dao = new DBConnect();
	}
	/*
	public Collection<String> resultSetToUser (ResultSet rs) {
		Collection<String> user = new java.util.ArrayList<String>();
		while (rs.next()) {
			
		}
		return user;
	}
	*/
	public boolean userExists(String username, String password) {
		String sql = "SELECT count(*)"
				+ "FROM USER"
				+ " WHERE username = '" + username
				+ "' AND password = '" + password + "'";
		DBConnect jpa = new DBConnect();
		Query query = jpa.getQuery(sql);
		long result = (long)query.getSingleResult();
		return result == 1;
	}
	public boolean isAdmin(String username) {
		String sql = "SELECT count(*) "
				+ "FROM USER u "
				+ " WHERE username = '" + username + "'"
				+ " AND ACCESS_LEVEL > 1";
		DBConnect jpa = new DBConnect();
		Query query = jpa.getQuery(sql);
		long result = (long)query.getSingleResult();
		return result == 1;	}
	public User getUser(String username, String p) {
		String jpql = "SELECT u FROM User AS u WHERE u.username = :usr AND u.password = :pwd";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query = query.setParameter("usr", username).setParameter("pwd", p);
		List<User> users = query.getResultList();
		return users.isEmpty()? null: users.get(0);
	}
	public Collection<Parking> getAdminParkings(User user) {
		String jpql = "SELECT p FROM Parking AS p WHERE p.user = :user";
		TypedQuery<Parking> query = em.createQuery(jpql, Parking.class).setParameter("user", user); 
		List<Parking> results = query.getResultList();
		return results;
	}
}
