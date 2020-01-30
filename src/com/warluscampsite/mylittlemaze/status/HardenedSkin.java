package com.warluscampsite.mylittlemaze.status;

import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class HardenedSkin extends Status {

	Characterr character;

	// Add armor points and multipliers
	// Check at BattleAttackController in doAttackStuff

	int pointArmorBuff;

	public HardenedSkin(int statusTime, Characterr character, int pointArmorBuff) {
		super(statusTime);

		this.character = character;

		isItGood = true;
		pathToImage = "res/statusIcons/hardenedSkin.png";

		this.pointArmorBuff = pointArmorBuff;

		name = "Hardened skin";

		character.getStatistics().getArmor().addBoostsFromBuffs(pointArmorBuff);
	}

	@Override
	public void remove() {
		super.remove();

		character.getStatistics().getArmor().addBoostsFromBuffs(-pointArmorBuff);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
