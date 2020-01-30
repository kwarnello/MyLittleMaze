package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class ShortBow extends WeaponBase {

	public ShortBow(ItemTypes itemType) {
		super(itemType);

		name = "Short bow";
		path = "res/weapons/bows/shortBow.png";

		this.numberOfAttacks = 1;
		this.attackDelay = 3000;
		this.minDamage = 5;
		this.maxDamage = 8;
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
