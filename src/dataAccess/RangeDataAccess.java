package dataAccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Range;
//import beans.Range;
/*
public class RangeDataAccess {
	public List<Range> getRanges() throws SQLException {
		List<Range> ranges = new ArrayList<Range>();
		String sql = "SELECT * FROM ranges";
		DBConnect db = new DBConnect();
		ResultSet result = db.executeStatement(sql);
		while (result.next()) {
			Range range = new Range();
			range.setDistance(result.getInt("RANGE_KM"));
			range.setName((result.getString("RANGE_NAME")));
			range.setPrice(result.getInt("RANGE_PRICE"));
			ranges.add(range);
		}
		return ranges;
	}
}
*/

public class RangeDataAccess {
	public List<Range> getRanges() {
		EntityManager em = model.DBConnect.getEntityManager();
		String jpql = "SELECT r FROM Range AS r";
		TypedQuery query = em.createQuery(jpql, Range.class); 
		List<Range> results = query.getResultList();
		return results;
	}
}