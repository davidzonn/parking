package dataAccess;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Parking;
import model.ParkingPlace;
import model.User;

public class UserDataAccess {
	DBConnect connection = new DBConnect();
	EntityManager em = connection.getEntityManager();
	ResultSet result;

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
		//sort(results);
		return results;
	}
	/*
	private void sort(List<Parking> parkings) {
		Comparator<ParkingPlace> comparator = new Comparator<ParkingPlace>() {

			@Override
			public int compare(ParkingPlace p1, ParkingPlace p2) {
				return (p1.getParkingPlaceNumber() > p2.getParkingPlaceNumber()? 1 : -1);
			}
			
		};
		for (Parking parking: parkings) {
			List<ParkingPlace> places = parking.getParkingPlaces();
			Collections.sort(places, comparator);
			parking.setParkingPlaces(places);
		}
	}
	*/
	public String getAdminName(int idParking) {
		String ans = "";
		Parking parking = em.find(Parking.class, idParking);
		User user = parking.getUser();
		if (user != null) {
			ans = user.getUsername();
		}
		return ans;
	}
	public List<String> getAllAdminsNames() {
		String sql = "SELECT a.username FROM USER a WHERE a.ACCESS_LEVEL = 2";
		Query query = connection.getQuery(sql);
		List<String> results = query.getResultList();
		return results;
	}
	public User findUserByName(String adminName) {
		String jpql = "SELECT u FROM User AS u WHERE u.username = :usr";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query = query.setParameter("usr", adminName);
		List<User> users = query.getResultList();
		return users.isEmpty()? null: users.get(0);
	}
	public void createUser(String username, String password) {
		User user = new User();
		user.setAccessLevel(1);
		user.setUsername(username);
		user.setPassword(password);
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}
}
