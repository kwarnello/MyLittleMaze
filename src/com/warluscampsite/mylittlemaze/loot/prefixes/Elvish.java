package com.warluscampsite.mylittlemaze.loot.prefixes;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Elvish extends Prefix {

	int dexterityBoost = 1;
	int speedBoost = 1;

	public Elvish(int[] possibleItemTypes) {
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
		player.getAttributes().getAttribute(AttributesEnum.DEXTERITY).addFromItem(dexterityBoost);
		player.getAttributes().getAttribute(AttributesEnum.SPEED).addFromItem(speedBoost);
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		dexterityBoost = (int) (dexterityBoost * itemRarity.getMultiplier());
		speedBoost = (int) (speedBoost * itemRarity.getMultiplier());
		if (dexterityBoost == 0)
			dexterityBoost = 1;
		if (speedBoost == 0)
			speedBoost = 1;

		describtion = "Elvish - add " + dexterityBoost + " dexterity and " + speedBoost + " speed.";
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
