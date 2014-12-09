package dataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Range;

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
