package com.warluscampsite.mylittlemaze.loot.suffixes;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Elementalist extends Suffix {

	// Add fire damage during attack
	double eleResistance = 0.05;

	public Elementalist(int[] possibleItemTypes) {
		super(possibleItemTypes);
	}

	@Override
	public void doThingsDuringConstructItem(Item item) {
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
	}

	@Override
	public void doStats(Player player, Item item) {
		player.getResist(DamageTypes.FIRE).addBoostsFromItem(eleResistance);
		player.getResist(DamageTypes.ICE).addBoostsFromItem(eleResistance);
		player.getResist(DamageTypes.EARTH).addBoostsFromItem(eleResistance);
		player.getResist(DamageTypes.LIGHTNING).addBoostsFromItem(eleResistance);
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		eleResistance = eleResistance * itemRarity.getMultiplier();

		describtion = "Elementalist - add elemental resistance "
				+ MyStringFormatter.formatDoubleAsPercentage(eleResistance, 0) + ".";
	}

	@Override
	public void addThingsToItemBase(ItemBase itemBase) {
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
}
