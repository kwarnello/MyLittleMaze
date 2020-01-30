package com.warluscampsite.mylittlemaze.loot.suffixes;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Turtle extends Suffix {

	//Add defence boost to item
	double defenceBoost = 5;

	public Turtle(int[] possibleItemTypes) {
		super(possibleItemTypes);
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		defenceBoost = defenceBoost * itemRarity.getMultiplier();
	}

	@Override
	public void doThingsDuringConstructItem(Item item) {
		double newDefence = defenceBoost + item.getItemBase().getDefence();
		item.getItemBase().setDefence(newDefence);
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
