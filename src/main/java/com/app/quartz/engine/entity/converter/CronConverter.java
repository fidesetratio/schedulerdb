package com.app.quartz.engine.entity.converter;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.quartz.engine.dto.CronProperties;
import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.obj.CronTab;
import com.app.quartz.engine.obj.Days;
import com.app.quartz.engine.obj.Months;

public final class CronConverter {
	
	private static final Logger logger = LoggerFactory.getLogger(CronConverter.class);
	
	public static SchedulerJobInfo generateCron(SchedulerJobInfo schedulerJobInfo) {
		CronProperties cronProperties = schedulerJobInfo.getCronProperties();
		
		if (cronProperties.getCronTab() == null || cronProperties.getCronTab().isEmpty()) {
			cronProperties.setCronTab(CronTab.MINUTES); 
		}
		
		// generate cron expression
		String cronExpression = "";
		
	    switch (cronProperties.getCronTab()) {
            case CronTab.MINUTES:
            	if (cronProperties.getMinutes() != null && !cronProperties.getMinutes().isEmpty()) {
            		cronExpression = minutes(cronProperties.getMinutes());
        		}
            	break;
            case CronTab.HOURLY:
            	if (cronProperties.getHourly() != null && !cronProperties.getHourly().isEmpty()) {
            		cronExpression = hourly(cronProperties.getHourly());
            	}
            	break;
            case CronTab.DAILY:
            	if (cronProperties.getDailyHour() != null && !cronProperties.getDailyHour().isEmpty()) {
            		Calendar cal = getCalendar(cronProperties.getDailyHour());
                	cronExpression = daily(cronProperties.isEveryDay(), String.valueOf(cal.get(Calendar.MINUTE)), String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
            	}
            	break;
            case CronTab.WEEKLY:
            	if (cronProperties.getWeeklyHour() != null && !cronProperties.getWeeklyHour().isEmpty()) {
            		Calendar cal1 = getCalendar(cronProperties.getWeeklyHour());
                	cronExpression = weekly(String.valueOf(cal1.get(Calendar.MINUTE)), String.valueOf(cal1.get(Calendar.HOUR_OF_DAY)), getDays(cronProperties.getWeeklyDay()).getKey());
            	}
            	break;
            case CronTab.MONTHLY:
            	if (cronProperties.getMonthlyHour() != null && !cronProperties.getMonthlyHour().isEmpty()) {
            		Calendar cal2 = getCalendar(cronProperties.getMonthlyHour());
                	cronExpression = monthly(String.valueOf(cal2.get(Calendar.MINUTE)), String.valueOf(cal2.get(Calendar.HOUR_OF_DAY)), cronProperties.getMonthlyDay());
            	}
            	break;
            case CronTab.YEARLY:
            	if (cronProperties.getYearlyHour() != null && !cronProperties.getYearlyHour().isEmpty()) {
            		Calendar cal3 = getCalendar(cronProperties.getYearlyHour());
                	cronExpression = yearly(String.valueOf(cal3.get(Calendar.MINUTE)), String.valueOf(cal3.get(Calendar.HOUR_OF_DAY)), cronProperties.getYearlyDate(), String.valueOf(getMonths(cronProperties.getYearlyMonth()).getKey()));
            	}
            	break;
            default:
            	logger.error("CronConverter:generateCron.switch (cronProperties.getCronTab())");
        }
	    
	    if (CronExpression.isValidExpression(cronExpression)) {
			schedulerJobInfo.setCronExpression(cronExpression);
		}
	    
	    // generate cron input, ini berguna untuk menampilkan kembali isi cron ke halaman edit job
	    // format CronProperties
	    String cronInput = cronProperties.toString();
	    schedulerJobInfo.setCronInput(cronInput);
        return schedulerJobInfo;
    }

	/**
	 * this method for every minute
	 * cron format: 0 0/{minute} * 1/1 * ? * 
	 * e.g: 0 0/1 * 1/1 * ? *
	 * @param strCron
	 * @return
	 */
	public static String minutes(String min) {
		return "0 0/" + min + " * 1/1 * ? * ";
	}
	
	/**
	 * this method for every hour(s)
	 * cron format: 0 0 0/{hour} 1/1 * ? *
	 * e.g: 0 0 0/3 1/1 * ? *
	 * @param hour
	 * @return
	 */
	public static String hourly(String hour) {
		return "0 0 0/" + hour + " 1/1 * ? * ";
	}
	
	/**
	 * this method is for every day
	 * cron format: 0 {seconds} {hour} 1/1 * ? *
	 * eg: 0 10 13 1/1 * ? *
	 * @param hour
	 * @return
	 */
	public static String daily(boolean isEveryDay, String seconds, String hour) {
		String expression = "";
		if (isEveryDay) {
			expression = "0 " + seconds + " " + hour + " 1/1 * ? * ";
    	} else {
    		expression = "0 " + seconds + " " + hour + " ? * MON-FRI * ";
    	}
		return expression;
	}
	
	/**
	 * this method is for weekly 
	 * cron format: 0 {seconds} {hour} ? * WED *
	 * eg: 0 8 12 ? * SAT *
	 * @param hour
	 * @param day
	 * @return
	 */
	public static String weekly(String seconds, String hour, String day) {
		return "0 " + seconds + " " + hour + " ? * " + day + " * ";
	}
	
	/**
	 * this method is for monthly
	 * cron format: 0 {seconds} {hour} {dayOfmonth} 1/1 ? *
	 * eg: 	0 13 12 1 1/1 ? *
	 * @param hour
	 * @param day
	 * @return
	 */
	public static String monthly(String seconds, String hour, String dayOfmonth) {
		return "0 " + seconds + " " + hour + " " + dayOfmonth + " 1/1 ? * ";
	}
	
	/**
	 * this method is for yearly
	 * cron format: 0 {seconds} {hour} {dayOfmonth} {month} ? *
	 * eg: 0 15 12 1 1 ? *
	 * @param hour
	 * @param day
	 * @return
	 */
	public static String yearly(String seconds, String hour, String dayOfmonth, String month) {
		return "0 " + seconds + " " + hour + " " + dayOfmonth + " " + month + " ? * ";
	}
	
	public static Days getDays(String day) {
		for (Days d: Days.values()) {
	        if (d.getValue().equals(day.trim())) {
	            return d;
	        }
	    }
		return null;
	}
	
	public static Months getMonths(String month) {
		for (Months m: Months.values()) {
	        if (m.getValue().equals(month.trim())) {
	            return m;
	        }
	    }
		return null;
	}
	
	public static Calendar getCalendar(String hour) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Date convertTodate = new Date();
		try {
			convertTodate = dateFormat.parse(hour);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// set date to calendar
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(convertTodate);
		return calendar;
	}
}
