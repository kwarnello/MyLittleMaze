package com.warluscampsite.mylittlemaze.status;

import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Bleed extends Status {

	Characterr character;
	final static int DEFAULT_STATUS_TIME = 10000;

	// Cause damage for character every x seconds
	// Check at BattleAttackController in doAttackStuff

	int doDamageEvery = 1000;
	double doDamageEqualOfHealth = 0.02;
	int timeFromLastDamaging = 0;

	public Bleed(Characterr character) {
		super(DEFAULT_STATUS_TIME);

		this.character = character;

		isItGood = false;
		pathToImage = "res/statusIcons/bleed.png";

		name = "Bleed";
	}

	public Bleed(int statusTime, Characterr character, int doDamageEvery, double doDamageEqualOfHealth) {
		this(character);

		this.doDamageEvery = doDamageEvery;
		this.doDamageEqualOfHealth = doDamageEqualOfHealth;
	}

	@Override
	public void addTimeFlow(int millis) {
		super.addTimeFlow(millis);

		timeFromLastDamaging += millis;

		if (timeFromLastDamaging > doDamageEvery) {
			timeFromLastDamaging -= doDamageEvery;

			double healthToTake = character.getHealth().getMaxValue() * doDamageEqualOfHealth;
			character.getHealth().changeCurrentValue(-healthToTake);
		}
	}

	@Override
	public void renew(Status status) {
		super.renew(status);

		if (((Bleed) status).getDoDamageEvery() >= doDamageEqualOfHealth)
			this.doDamageEqualOfHealth = ((Bleed) status).getDoDamageEvery();
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public int getDoDamageEvery() {
		return doDamageEvery;
	}
}
