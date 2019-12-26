package com.app.quartz.engine.obj;

public enum NotificationType {

	EMAIL(1, "Email");
	
	private final int key;
	private final String value;

	NotificationType(int key, String value) {
		this.key = key;
	    this.value = value;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	public static String getValue(int key) {
		String value = "";
		for (NotificationType n: NotificationType.values()) {
			if (n.getKey() == key)
				value = n.getValue();
		}
		return value;
	}
}
