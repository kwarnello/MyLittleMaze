package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class CultistBoots extends ArmorBase {

	public CultistBoots(ItemTypes itemType) {
		super(itemType);

		name = "Cultist boots";
		path = "res/armors/sets/cultistSet/boots.png";

		armorTypes = ArmorTypes.CLOTH;

		this.fireRes = 0.01;
		this.iceRes = 0.01;
		this.earthRes = 0.01;
		this.lightningRes = 0.01;
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
	}

	@Override
	public void doStats(Player player) {
		player.getResist(DamageTypes.FIRE).addBoostsFromItem(fireRes);
		player.getResist(DamageTypes.ICE).addBoostsFromItem(iceRes);
		player.getResist(DamageTypes.EARTH).addBoostsFromItem(earthRes);
		player.getResist(DamageTypes.LIGHTNING).addBoostsFromItem(lightningRes);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
