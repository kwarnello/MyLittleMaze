package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class ThrowingAxe extends WeaponBase {

	public ThrowingAxe(ItemTypes itemType) {
		super(itemType);

		name = "Throwing axe";
		path = "res/weapons/throwing_weapons/throwingAxe.png";

		this.numberOfAttacks = 2;
		this.attackDelay = 3000;
		this.minDamage = 5;
		this.maxDamage = 9;
		this.critChance = 0.03;
		this.critMultiplier = 1.5;
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
