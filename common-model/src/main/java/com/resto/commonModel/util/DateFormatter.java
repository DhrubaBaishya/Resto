package com.resto.commonModel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	private static final String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
			"Dec" };
	@SuppressWarnings("deprecation")
	public static String getFormattedDate(Date date) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(months[date.getMonth()]);
		stringBuilder.append(" ");
		stringBuilder.append(date.getDate());
		stringBuilder.append(", ");
		stringBuilder.append(date.getYear() + 1900);
		return stringBuilder.toString();
	}
	
	public static String getDateBefore(Date date) {
		Long period = (new Date()).getTime() - date.getTime();
		Long secondsInMilli = 1000L;
		Long minutesInMilli = secondsInMilli * 60L;
		Long hoursInMilli = minutesInMilli * 60L;
		Long daysInMilli = hoursInMilli * 24L;
		Long monthsInMilli = daysInMilli * 30;
		Long yearsInMill = monthsInMilli * 12;
		if((period / yearsInMill) > 0) {
			if((period / yearsInMill) == 1)
				return (period / yearsInMill) + " year ago";
			return (period / yearsInMill) + " years ago";
		}
		else if((period / monthsInMilli) > 0) {
			if((period / monthsInMilli) == 1)
				return (period / monthsInMilli) + " month ago";
			return (period / monthsInMilli) + " months ago";
		}
		else if((period / daysInMilli) > 0) {
			if((period / daysInMilli) == 1)
				return (period / daysInMilli) + " day ago";
			return (period / daysInMilli) + " days ago";
		}
		else if((period / hoursInMilli) > 0) {
			if((period / hoursInMilli) == 1)
				return (period / hoursInMilli) + " hour ago";
			return (period / hoursInMilli) + " hours ago";
		}
		else if((period / minutesInMilli) > 0) {
			if((period / minutesInMilli) == 1)
				return (period / minutesInMilli) + " minute ago";
			return (period / minutesInMilli) + " minutes ago";
		}
		else if((period / secondsInMilli) > 0) {
			if((period / secondsInMilli) == 1)
				return (period / secondsInMilli) + " second ago";
			return (period / secondsInMilli) + " seconds ago";
		}
		return date.getTime() + "";		
	}
	
	public static void main(String[] args) throws ParseException {
		
		String stdate ="01/01/2014 09:30:30";
	    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	    Date d1 = new Date();
	    d1 = df.parse(stdate);
		System.out.println(getDateBefore(d1));
	}
}

