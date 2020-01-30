package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class MagicStick extends WeaponBase {

	public MagicStick(ItemTypes itemType) {
		super(itemType);

		name = "Earth stick";
		path = "res/weapons/staves/magicStick.png";

		damageTypes = DamageTypes.EARTH;

		this.numberOfAttacks = 1;
		this.attackDelay = 4000;
		this.minDamage = 4;
		this.maxDamage = 6;
		this.critChance = 0.05;
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
