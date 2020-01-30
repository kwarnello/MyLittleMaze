package com.warluscampsite.mylittlemaze.status;

import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Blind extends Status {

	// Reduction of physical damage DEALT by attacker
	// Check at BattleAttackController in doAttackStuff

	Characterr character;

	final static int DEFAULT_STATUS_TIME = 5000;
	double dexterityReductionMultiplier;
	int removedDexterity;

	public Blind(Characterr character) {
		super(DEFAULT_STATUS_TIME);
		this.character = character;

		dexterityReductionMultiplier = 0.3;

		isItGood = false;
		pathToImage = "res/statusIcons/blind.png";

		name = "Blind";

		removedDexterity = (int) (character.getAttributes().getAttribute(AttributesEnum.DEXTERITY).getSum()
				* dexterityReductionMultiplier);

		character.getAttributes().getAttribute(AttributesEnum.DEXTERITY).addFromBuff(-removedDexterity);
	}

	public Blind(int statusTime, Characterr character) {
		this(character);

		this.statusTime = statusTime;
	}

	@Override
	public void remove() {
		super.remove();

		character.getAttributes().getAttribute(AttributesEnum.DEXTERITY).addFromBuff(removedDexterity);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
