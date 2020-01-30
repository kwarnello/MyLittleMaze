package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.skills.SkillRange;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Halberd extends WeaponBase {

	public Halberd(ItemTypes itemType) {
		super(itemType);

		name = "Halberd";
		path = "res/weapons/polearms/halberd.png";

		weaponRange = SkillRange.SINGLE_TWO_ROW;

		additionalString.append("Polearms can attack enemies in range of two rows.");

		this.numberOfAttacks = 1;
		this.attackDelay = 4500;
		this.minDamage = 8;
		this.maxDamage = 15;
		this.critChance = 0.05;
		this.critMultiplier = 2.0;
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
	}

	@Override
	public void doStats(Player player) {
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
