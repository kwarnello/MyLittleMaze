package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class ThrowingDagger extends WeaponBase {

	public ThrowingDagger(ItemTypes itemType) {
		super(itemType);

		name = "Throwing dagger";
		path = "res/weapons/throwing_weapons/throwingDagger.png";

		this.numberOfAttacks = 3;
		this.attackDelay = 3000;
		this.minDamage = 1;
		this.maxDamage = 2;
		this.critChance = 0.05;
		this.critMultiplier = 1.2;
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
