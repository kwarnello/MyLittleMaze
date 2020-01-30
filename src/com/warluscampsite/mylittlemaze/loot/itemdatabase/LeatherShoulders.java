package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class LeatherShoulders extends ArmorBase {

	public LeatherShoulders(ItemTypes itemType) {
		super(itemType);

		name = "Leather shoulders";
		path = "res/armors/sets/leatherSet/shoulders.png";

		armorTypes = ArmorTypes.LIGHT;

		this.defence = 5;

		this.armor = 3;
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
	}

	@Override
	public void doStats(Player player) {
		player.getStatistics().getDefence().addBoostsFromItems(defence);
		player.getStatistics().getArmor().addBoostsFromItems(armor);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
}
