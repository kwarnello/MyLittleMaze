package com.warluscampsite.mylittlemaze.loot.suffixes;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Orchid extends Suffix {

	// Add more attack with weapon
	double minDamageBoost = 5;
	double maxDamageBoost = 10;
	double defenceReduction = -10.0;

	public Orchid(int[] possibleItemTypes) {
		super(possibleItemTypes);

		describtion = "Orchid - increase damage of weapon, but lower defence of owner.";
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		minDamageBoost = minDamageBoost * itemRarity.getMultiplier();
		maxDamageBoost = maxDamageBoost * itemRarity.getMultiplier();
		defenceReduction = defenceReduction * itemRarity.getMultiplier();
	}

	@Override
	public void doThingsDuringConstructItem(Item item) {
		item.getItemBase().setMinDamage((int) (item.getItemBase().getMinDamage() + minDamageBoost));
		item.getItemBase().setMaxDamage((int) (item.getItemBase().getMaxDamage() + maxDamageBoost));
		item.getItemBase().setDefence(item.getItemBase().getDefence() + defenceReduction);
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
	}

	@Override
	public void doStats(Player player, Item item) {
	}

	@Override
	public void addThingsToItemBase(ItemBase itemBase) {
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
}
