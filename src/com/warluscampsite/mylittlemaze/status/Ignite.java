package com.warluscampsite.mylittlemaze.status;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Ignite extends Status {

	Characterr character;
	final static int DEFAULT_STATUS_TIME = 8000;

	// Add armor points and multipliers
	// Check at BattleAttackController in doAttackStuff
	int doDamageEvery = 1000;
	double doDamageEqualOfHealth = 0.015;
	int timeFromLastDamaging = 0;

	final double fireResistanceDebuff = 0.2;

	public Ignite(Characterr character) {
		super(DEFAULT_STATUS_TIME);

		this.character = character;

		isItGood = false;
		pathToImage = "res/statusIcons/ignite.png";

		name = "Ignite";

		character.getResist(DamageTypes.FIRE).addBoostsFromBuffs(fireResistanceDebuff);
	}

	@Override
	public void addTimeFlow(int millis) {
		super.addTimeFlow(millis);

		timeFromLastDamaging += millis;

		if (timeFromLastDamaging > doDamageEvery) {
			timeFromLastDamaging -= doDamageEvery;

			double healthToTake = character.getHealth().getMaxValue() * doDamageEqualOfHealth
					* (1 - character.getResist(DamageTypes.FIRE).getReduction());
			character.getHealth().changeCurrentValue(-healthToTake);
		}
	}

	@Override
	public void remove() {
		super.remove();
		character.getResist(DamageTypes.FIRE).addBoostsFromBuffs(-fireResistanceDebuff);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
