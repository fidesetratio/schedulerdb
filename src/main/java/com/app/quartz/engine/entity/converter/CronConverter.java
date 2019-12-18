package com.app.quartz.engine.entity.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.app.quartz.engine.dto.CronProperties;

public final class CronConverter {
	
	public static String stringToCron(CronProperties cronProperties) {
		String cronExpression = "";
		
		// date format
//		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
//		Date convertTodate = new Date();
//			convertTodate = dateFormat.parse(strCron);
//			
//			// set date to calendar
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(convertTodate);
//	        String minutes = String.valueOf(calendar.get(Calendar.MINUTE));
//	        String hours = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
	        
	        if (cronProperties.getEveryMinute() != null && !cronProperties.getEveryMinute().isEmpty()) {
	        	cronExpression = everyMinute(cronProperties.getEveryMinute());
	        }
        return cronExpression;
    }

	/**
	 * this method for every minute
	 * format cron: 0 0/255 * 1/1 * ? * 
	 * @param strCron
	 * @return
	 */
	public static String everyMinute(String min) {
		return "0 " + "0/" + min + " " + "* " + "1/1 " + "* " + "? " + "* ";
	}

}
