package com.warluscampsite.mylittlemaze.battle;

import com.warluscampsite.mylittlemaze.data.Data;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class BattleInitiativeController {

	Data data;

	public BattleInitiativeController(Data data) {
		this.data = data;
	}

	void addTick(int millis) {
		data.getPlayerParty().getCharacterMap().forEach((k, v) -> {
			if (v != null)
				checkWhatNow(v, millis);
		});
		data.getMaze().getMonstersParty().getPlaceMap().forEach((k, v) -> checkWhatNow(v, millis));

		data.getBattle().getBattleAttackControllers().addTicksForNumbers(millis);

		data.getBattle().getDeathSystem().doDeath();
	}

	private void checkWhatNow(Characterr character, int milis) {
		if (character.isDead())
			return;

		character.addTimeFlow(milis);

		for (Skill skill : character.getSkills().getMapOfAttacks().values()) {
			if (skill.shouldAttack())
				data.getBattle().getBattleAttackControllers().thisAttack(skill);
		}
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * z
	 */
}
