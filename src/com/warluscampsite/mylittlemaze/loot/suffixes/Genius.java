package com.warluscampsite.mylittlemaze.loot.suffixes;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Genius extends Suffix {

	// Add intelligence
	int addIntelligence = 2;

	public Genius(int[] possibleItemTypes) {
		super(possibleItemTypes);
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		addIntelligence = (int) (addIntelligence * itemRarity.getMultiplier());

		describtion = "Genius - add" + addIntelligence + " intelligence.";
	}

	@Override
	public void doThingsDuringConstructItem(Item item) {
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
	}

	@Override
	public void doStats(Player player, Item item) {
		player.getAttributes().getAttribute(AttributesEnum.INTELLIGENCE).addFromItem(addIntelligence);
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
