package com.warluscampsite.mylittlemaze.status;

import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Entangle extends Status {

	Characterr character;
	final static int DEFAULT_STATUS_TIME = 15000;

	// Reduced target block and dodge chance

	double reduceChanceMultiplier = 0.2;

	public Entangle(Characterr character) {
		super(DEFAULT_STATUS_TIME);

		this.character = character;

		isItGood = false;
		pathToImage = "res/statusIcons/entangle.png";

		name = "Entangle";

		character.getBlock().setBlockChanceMultiplier(reduceChanceMultiplier);
		character.getStatistics().setMultiplier("Dodge", reduceChanceMultiplier);
	}

	@Override
	public void remove() {
		super.remove();

		character.getBlock().setBlockChanceMultiplier(1.0);
		character.getStatistics().setMultiplier("Dodge", 1.0);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
