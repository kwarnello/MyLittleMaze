package com.warluscampsite.mylittlemaze.skills;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Bite extends Skill {

	final int BOOSTS_FROM_STR_TO_MIN = 2, BOOSTS_FROM_STR_TO_MAX = 3;

	public Bite(Characterr character) {
		super(character);

		name = "bite";

		numberOfAttacks = 1;

		skillRange = SkillRange.SINGLE_ONE_ROW;
		skillType = SkillType.MELEE;
		damageTypes = DamageTypes.PHYSICAL;

		cooldownBase = 7000; // in milliseconds

		refresh();
		this.calculateCooldown();
	}

	public Bite(Characterr character, int level) {
		this(character);
		this.level = level;
	}

	public Bite(Characterr character, int level, boolean is1L, boolean is1R, boolean is2L, boolean is2R, boolean is3L,
			boolean is3R) {
		this(character, level);

		skillActivated1L = is1L;
		skillActivated1R = is1R;
		skillActivated2L = is2L;
		skillActivated2R = is2R;
		skillActivated3L = is3L;
		skillActivated3R = is3R;
	}

	@Override
	public void doAttack(Characterr attacker, Characterr defender, Map<DamageTypes, Double> damageMap, boolean criticalStrike) {
		double damage = getRandomDamage();
		damageMap.put(getDamageTypes(), damage);
	}

	@Override
	public void refresh() {
		super.refresh();

		minDamage = character.getAttributes().getAttribute(AttributesEnum.STRENGTH).getSum() * BOOSTS_FROM_STR_TO_MIN;
		maxDamage = character.getAttributes().getAttribute(AttributesEnum.STRENGTH).getSum() * BOOSTS_FROM_STR_TO_MAX;
	}

	@Override
	public String getSkillTooltip() {
		return null;
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}