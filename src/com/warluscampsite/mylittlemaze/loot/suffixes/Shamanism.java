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

public class Shamanism extends Suffix {

	// Add life and mana regen
	double regen = 1;

	public Shamanism(int[] possibleItemTypes) {
		super(possibleItemTypes);
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		regen = regen * itemRarity.getMultiplier();

		describtion = "Shamanism - mana and life regeneration +" + MyStringFormatter.formatDouble(regen, 2) + "/s";
	}

	@Override
	public void doThingsDuringConstructItem(Item item) {
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
	}

	@Override
	public void doStats(Player player, Item item) {
		player.getMana().addBoostFromItemRegen(regen);
		player.getHealth().addBoostFromItemRegen(regen);
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
