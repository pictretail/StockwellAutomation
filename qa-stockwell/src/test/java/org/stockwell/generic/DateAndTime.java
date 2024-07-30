package org.stockwell.generic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.testng.Assert;


public class DateAndTime {

	public String getDateAndTime(String requiredformat, String requiredTimeZone) {
		SimpleDateFormat formatter = new SimpleDateFormat(requiredformat);
		Date date = new Date();
		try {
			 TimeZone timeZone = TimeZone.getTimeZone(requiredTimeZone);
			 formatter.setTimeZone(timeZone);
			
		} catch (Exception exc) {
			Assert.fail(exc.toString());
		}
		return (formatter.format(date));
		
	}
}
