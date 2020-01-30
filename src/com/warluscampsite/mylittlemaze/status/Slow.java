package com.warluscampsite.mylittlemaze.status;

public class Slow extends Status {

	// Increase cooldown of attack of enemy
	// Check if character has slow at addTimeFlow at skill

	final static int DEFAULT_SLOW_TIME = 5000;

	public Slow() {
		super(DEFAULT_SLOW_TIME);

		isItGood = false;
		pathToImage = "res/statusIcons/slow.png";

		name = "Slow";
	}

	public Slow(int statusTime) {
		this();

		this.statusTime = statusTime;
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
