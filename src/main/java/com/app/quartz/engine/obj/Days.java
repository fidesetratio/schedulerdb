package com.app.quartz.engine.obj;

public enum Days {
	
	SUNDAY("SUN", "Sunday"), 
	MONDAY("MON", "Monday"), 
	TUESDAY("TUE", "Tuesday"),
	WEDNESDAY("WED", "Wednesday"),
	THURSDAY("THU", "Thursday"),
	FRIDAY("FRI", "Friday"),
	SATURDAY("SAT", "Saturday");
	
	private final String key;
	private final String value;

	Days(String key, String value) {
		this.key = key;
	    this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

}
