package app.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
	
	public static String DATE_FORMAT = "dd-MM-yyyy";
	public static String TIME_FORMAT = "hh:mm";
	public static String DATE_TIME_FORMAT = "dd-MM-yyyy hh:mm";	
	
	public static String convertDateToString (Date date) {
		return convertDateToString(date, DATE_FORMAT);
	}
	
	public static String convertDateToString (Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		
		return (date == null) ? "" : dateFormat.format(date);
	}
	
	public static Date convertStringToDate (String strDate) {
		return convertStringToDate(strDate, DATE_FORMAT);
	}
	
	public static Date convertStringToDate (String strDate, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		
		try {
			return dateFormat.parse(StringUtils.trim(strDate));
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static int daysDiff(Date date1, Date date2) {
		
		date1 = removeTime(date1);
		date2 = removeTime(date2);
		
		long milisDiff = date1.getTime() - date2.getTime();
		
		long millisInOneDay = 1000 * 60 * 60 * 24;
		
		return (int) (milisDiff / millisInOneDay);
	}
	
	public static Date removeTime(Date date) {
		Calendar calendar = new GregorianCalendar();
		
		calendar.setTime(date);
		
		// remove hh:mm:ss.milisecond
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}

}
