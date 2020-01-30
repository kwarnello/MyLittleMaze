package com.warluscampsite.mylittlemaze.loot.prefixes;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Tough extends Prefix {

	// Prefix that boost armor
	double armorBoost = 5;
	double percentageArmorBoost = 0.2;

	public Tough(int[] possibleItemTypes) {
		super(possibleItemTypes);
	}

	@Override
	public void doThingsDuringConstructItem(Item item) {
		double armor = item.getItemBase().getArmor();
		armor = armor * (1 + percentageArmorBoost);

		armor += armorBoost;
		item.getItemBase().setArmor(armor);
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
	}

	@Override
	public void doStats(Player player, Item item) {
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		armorBoost = (int) Math.round(armorBoost * itemRarity.getMultiplier());
		percentageArmorBoost = Math.round(percentageArmorBoost * itemRarity.getMultiplier());
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
