package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class WoodenBow extends WeaponBase {

	public WoodenBow(ItemTypes itemType) {
		super(itemType);

		name = "Wooden bow";
		path = "res/weapons/bows/woodenBow.png";

		this.numberOfAttacks = 1;
		this.attackDelay = 3500;
		this.minDamage = 3;
		this.maxDamage = 6;
		this.critChance = 0.03;
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
