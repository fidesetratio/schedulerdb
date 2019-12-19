package com.app.quartz.engine.obj;

public enum Months {

	JANUARY(1, "January"), 
	FEBRUARY(2, "February"), 
	MARCH(3, "March"),
	APRIL(4, "April"),
	MAY(5, "May"),
	JUNE(6, "June"),
	JULY(7, "July"),
	AUGUST(8, "August"),
	SEPTEMBER(9, "September"),
	OCTOBER(10, "October"),
	NOVEMBER(11, "November"),
	DECEMBER(12, "December");
	
	private final int key;
	private final String value;

	Months(int key, String value) {
		this.key = key;
	    this.value = value;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
