package com.warluscampsite.mylittlemaze.status;

public class Weakness extends Status {

	// Reduction of physical damage DEALT by attacker
	// Check at BattleAttackController in doAttackStuff

	final static int DEFAULT_STATUS_TIME = 5000;
	double physicalDamageReduction;

	public Weakness() {
		super(DEFAULT_STATUS_TIME);
		physicalDamageReduction = 0.3;

		isItGood = false;
		pathToImage = "res/statusIcons/weakness.png";

		name = "Weakness";
	}

	public Weakness(int statusTime) {
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

	public double getPhysicalDamageReduction() {
		return physicalDamageReduction;
	}

}
