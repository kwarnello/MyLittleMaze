package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class HeatherShield extends ShieldBase {

	double blockChance;

	public HeatherShield(ItemTypes itemType) {
		super(itemType);

		name = "Heather Shield";
		path = "res/weapons/shields/heaterShield.png";

		defence = 5.0;
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		super.doThingsWithRarity(itemRarity);

		additionalString.append("Block chance: +" + MyStringFormatter.formatDoubleAsPercentage(blockChance, 0));
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
	}

	@Override
	public void doStats(Player player) {
		player.getBlock().setBlockChance(blockChance);
		player.getBlock().setCanBlock(true);
		player.getStatistics().getDefence().addBoostsFromItems(defence);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
