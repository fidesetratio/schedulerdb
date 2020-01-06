package com.app.quartz.engine.obj;

public enum ProcessType {

	PAUSE(1, "Pause"),
	RESUME(2, "Resume"),
	RESUME_ALL(3, "Resume All"),
	PAUSE_ALL(4, "Pause All"),
	DELETE(5, "Delete");
	
	
	private final int key;
	private final String value;

	ProcessType(int key, String value) {
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
