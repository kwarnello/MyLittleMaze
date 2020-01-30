package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class ButcherAxe extends WeaponBase {

	public ButcherAxe(ItemTypes itemType) {
		super(itemType);

		name = "Butcher axe";
		path = "res/weapons/axes/butcherAxe.png";

		this.numberOfAttacks = 1;
		this.attackDelay = 2000;
		this.minDamage = 5;
		this.maxDamage = 8;
		this.critChance = 0.05;
		this.critMultiplier = 1.65;
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
