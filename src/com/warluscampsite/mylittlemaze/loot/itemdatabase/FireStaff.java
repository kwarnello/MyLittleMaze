package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class FireStaff extends WeaponBase {
	


	public FireStaff(ItemTypes itemType) {
		super(itemType);

		name = "Fire staff";
		path = "res/weapons/staves/fireStaff.png";

		damageTypes = DamageTypes.FIRE;
		
		this.numberOfAttacks = 1;
		this.attackDelay = 5000;
		this.minDamage = 8;
		this.maxDamage = 11;
		this.critChance = 0.025;
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
