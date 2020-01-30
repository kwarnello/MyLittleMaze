package com.warluscampsite.mylittlemaze.status;

public class Chill extends Status {

	// Reduction of time flow add for skills
	// Check Character add time flow

	final static int DEFAULT_STATUS_TIME = 10000;
	final static double CHILL_SLOW = 0.5;

	public Chill() {
		super(DEFAULT_STATUS_TIME);

		isItGood = false;
		pathToImage = "res/statusIcons/chill.png";

		name = "Chill";
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
