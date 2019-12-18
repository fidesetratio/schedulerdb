package com.app.quartz.engine.dto;

public class CronProperties {

	private String everyMinute;

	public String getEveryMinute() {
		return everyMinute;
	}

	public void setEveryMinute(String everyMinute) {
		this.everyMinute = everyMinute;
	}

	@Override
	public String toString() {
		return "CronProperties [everyMinute=" + everyMinute + "]";
	}
}
