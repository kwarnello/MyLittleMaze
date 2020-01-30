package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class LightningStick extends WeaponBase {

	public LightningStick(ItemTypes itemType) {
		super(itemType);

		name = "Lightning stick";
		path = "res/weapons/staves/lightningStick.png";

		damageTypes = DamageTypes.LIGHTNING;

		this.numberOfAttacks = 2;
		this.attackDelay = 5000;
		this.minDamage = 5;
		this.maxDamage = 8;
		this.critChance = 0.03;
		this.critMultiplier = 1.6;
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
