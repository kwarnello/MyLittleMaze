package com.warluscampsite.mylittlemaze.monsters;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.skills.Bite;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Zombie extends Monster {

	public Zombie(int mazeLevel) {
		super(mazeLevel);

		name = "Zombie";
		monsterType = MonsterType.UNDEAD;

		addSkills(new Bite(this, mazeLevel));

	}

	@Override
	public void doBoostAfterInitializeMonster() {
		super.doBoostAfterInitializeMonster();
	}

	@Override
	public void doAttack(Characterr attacker, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap,
			boolean criticalStrike) {
		super.doAttack(attacker, defender, skill, damageMap, criticalStrike);
	}

	@Override
	public void createAttributes() {
		double boost = Math.pow(mazeLevel, POWER_FOR_BOOST);

		strengthBase = (int) (boost * 12);
		dexterityBase = (int) (boost * 3);
		intelligenceBase = (int) (boost * 1);
		speedBase = (int) (boost * 3);
		vitalityBase = (int) (boost * 8);
		wisdomBase = (int) (boost * 2);
		luckBase = (int) (boost * 1);

	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
}
