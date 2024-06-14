package org.stockwell.generic;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;
//import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.testng.Assert;
public class DateAndTime {
	/**
	 * Get Date and Time
	 * 
	 * @param format
	 * @param requiredTimeZone
	 * @return
	 */
	public String getDateAndTime(String format, String requiredTimeZone) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = new Date();
		try {
			 TimeZone timeZone = TimeZone.getTimeZone(requiredTimeZone);
			 formatter.setTimeZone(timeZone);
		} catch (Exception exc) {
			Assert.fail(exc.toString());
		}
		return (formatter.format(date));
	}

//	
}
