package com.warluscampsite.mylittlemaze.controllers;

import java.util.HashMap;
import java.util.Map;

public class TimeController {

	static Map<String, Long> mapOfTimers = new HashMap<String, Long>();

	static public void addNewKey(String key) {
		mapOfTimers.put(key, System.currentTimeMillis());
	}

	static public void getTimeOfRunning(String key) {
		if (mapOfTimers.containsKey(key)) {
			long timeDifference = System.currentTimeMillis() - mapOfTimers.get(key);

			System.out.println("Dla klucza: " + key + " - czas wynosi: " + timeDifference + " ms.");

			mapOfTimers.remove(key);
		} else
			System.err.println("W bazie TimeController nie ma klucza: " + key);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
}
