package com.warluscampsite.mylittlemaze.status;

import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Shock extends Status {

	Characterr character;
	final static int DEFAULT_STATUS_TIME = 7500;

	// Add armor points and multipliers
	// Check at BattleAttackController in doAttackStuff
	final double allResistanceDebuff = 0.1;

	public Shock(Characterr character) {
		super(DEFAULT_STATUS_TIME);

		this.character = character;

		isItGood = false;
		pathToImage = "res/statusIcons/shock.png";

		name = "Shock";

		character.getResistsMap().values().forEach(v -> v.addBoostsFromBuffs(allResistanceDebuff));
	}

	@Override
	public void remove() {
		super.remove();
		character.getResistsMap().values().forEach(v -> v.addBoostsFromBuffs(-allResistanceDebuff));
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
