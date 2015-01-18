package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

public class TimeStamp {
	public static long getTimeStamp(String html5Date, String html5Time) {
		Date date = null;
		Date time = null;
		try {
			date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(html5Date);
			time =  new java.text.SimpleDateFormat("HH:mm").parse(html5Time);
		} catch (ParseException e) {
			System.out.println("Error while generating the timestamps!");
			e.printStackTrace();
			return -1;
		}
		long dateTimestamp = date.getTime();
		long timeTimestamp = time.getTime();
		long combinedTimestamp = dateTimestamp + timeTimestamp + 3600000;
		return combinedTimestamp;
	}
}
