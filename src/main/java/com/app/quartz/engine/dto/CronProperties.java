package com.app.quartz.engine.dto;

public class CronProperties {

	private String minutes;
	private String hourly;
	private boolean everyDay;
	private String dailyHour;
	private String weeklyDay;
	private String weeklyHour;
	private String monthlyDay;
	private String monthlyHour;
	private String yearlyMonth;
	private String yearlyDate;
	private String yearlyHour;
	
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public String getHourly() {
		return hourly;
	}
	public void setHourly(String hourly) {
		this.hourly = hourly;
	}
	public boolean isEveryDay() {
		return everyDay;
	}
	public void setEveryDay(boolean everyDay) {
		this.everyDay = everyDay;
	}
	public String getDailyHour() {
		return dailyHour;
	}
	public void setDailyHour(String dailyHour) {
		this.dailyHour = dailyHour;
	}
	public String getWeeklyDay() {
		return weeklyDay;
	}
	public void setWeeklyDay(String weeklyDay) {
		this.weeklyDay = weeklyDay;
	}
	public String getWeeklyHour() {
		return weeklyHour;
	}
	public void setWeeklyHour(String weeklyHour) {
		this.weeklyHour = weeklyHour;
	}
	public String getMonthlyDay() {
		return monthlyDay;
	}
	public void setMonthlyDay(String monthlyDay) {
		this.monthlyDay = monthlyDay;
	}
	public String getMonthlyHour() {
		return monthlyHour;
	}
	public void setMonthlyHour(String monthlyHour) {
		this.monthlyHour = monthlyHour;
	}
	public String getYearlyMonth() {
		return yearlyMonth;
	}
	public void setYearlyMonth(String yearlyMonth) {
		this.yearlyMonth = yearlyMonth;
	}
	public String getYearlyDate() {
		return yearlyDate;
	}
	public void setYearlyDate(String yearlyDate) {
		this.yearlyDate = yearlyDate;
	}
	public String getYearlyHour() {
		return yearlyHour;
	}
	public void setYearlyHour(String yearlyHour) {
		this.yearlyHour = yearlyHour;
	}
	
	@Override
	public String toString() {
		return "CronProperties [minutes=" + minutes + ", hourly=" + hourly + ", everyDay=" + everyDay + ", dailyHour="
				+ dailyHour + ", weeklyDay=" + weeklyDay + ", weeklyHour=" + weeklyHour + ", monthlyDay=" + monthlyDay
				+ ", monthlyHour=" + monthlyHour + ", yearlyMonth=" + yearlyMonth + ", yearlyDate=" + yearlyDate
				+ ", yearlyHour=" + yearlyHour + "]";
	}

}
