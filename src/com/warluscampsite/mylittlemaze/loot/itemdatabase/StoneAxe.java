package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class StoneAxe extends WeaponBase {

	public StoneAxe(ItemTypes itemType) {
		super(itemType);

		name = "Stone axe";
		path = "res/weapons/axes/stoneAxe.png";

		this.numberOfAttacks = 1;
		this.attackDelay = 5000;
		this.minDamage = 9;
		this.maxDamage = 17;
		this.critChance = 0.05;
		this.critMultiplier = 1.8;
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
