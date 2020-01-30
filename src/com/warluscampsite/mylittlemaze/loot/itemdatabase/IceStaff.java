package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class IceStaff extends WeaponBase {
	


	public IceStaff(ItemTypes itemType) {
		super(itemType);

		name = "Ice staff";
		path = "res/weapons/staves/iceStaff.png";

		damageTypes = DamageTypes.ICE;
		
		this.numberOfAttacks = 1;
		this.attackDelay = 6000;
		this.minDamage = 10;
		this.maxDamage = 12;
		this.critChance = 0.03;
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
