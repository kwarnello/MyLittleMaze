package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class GuardianShield extends ShieldBase {

	double blockChance;

	public GuardianShield(ItemTypes itemType) {
		super(itemType);

		name = "Guardian Shield";
		path = "res/weapons/shields/guardianShield.png";

		defence = 10.0;
		attack = -10.0;

		blockChance = 0.35;

		fireRes = 0.05;
		iceRes = 0.05;
		earthRes = 0.05;
		lightningRes = 0.05;
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
		player.getStatistics().getAttack().addBoostsFromItems(attack);
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
