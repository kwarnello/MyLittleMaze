package com.warluscampsite.mylittlemaze.status;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Poison extends Status {

	Characterr character;
	final static int DEFAULT_STATUS_TIME = 10001;

	// Cause damage for character every x seconds
	// Check at BattleAttackController in doAttackStuff

	double sumDamage;

	int doDamageEvery = 20;
	double doDamageEqualOfHealthPhysicalDamage = 0.01;
	int timeFromLastDamaging = 0;

	int addTimeAfterRenew = 2000;

	int poisonCounter;

	public Poison(Characterr character, double physicalDamge) {
		super(DEFAULT_STATUS_TIME);

		this.character = character;

		isItGood = false;
		pathToImage = "res/statusIcons/poison.png";

		name = "Poison";

		poisonCounter = 1;

		sumDamage = physicalDamge * doDamageEqualOfHealthPhysicalDamage;
	}

	@Override
	public void addTimeFlow(int millis) {
		super.addTimeFlow(millis);

		timeFromLastDamaging += millis;

		if (timeFromLastDamaging > doDamageEvery) {
			timeFromLastDamaging -= doDamageEvery;
			double damageDoDeal = (1 - character.getResist(DamageTypes.POISON).getReduction()) * sumDamage;
			character.getHealth().changeCurrentValue(-damageDoDeal);
		}
	}

	@Override
	public void renew(Status status) {
		poisonCounter++;

		sumDamage += ((Poison) status).getSumDamage();

		actualTime -= addTimeAfterRenew;
		if (actualTime < 0)
			actualTime = 0;
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public int getPoisonCounter() {
		return poisonCounter;
	}

	public double getSumDamage() {
		return sumDamage;
	}

}
