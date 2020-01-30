package com.warluscampsite.mylittlemaze.loot.suffixes;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Tiger extends Suffix {

	// Add more attack with weapon
	int newAttack = 1;

	public Tiger(int[] possibleItemTypes) {
		super(possibleItemTypes);
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		newAttack = (int) (newAttack * itemRarity.getMultiplier());
		if (newAttack == 0)
			newAttack = 1;

		describtion = "Tiger - add " + newAttack + " attacks with main weapon attack";
	}

	@Override
	public void doThingsDuringConstructItem(Item item) {
		item.getItemBase().setNumberOfAttacks(item.getItemBase().getNumberOfAttacks() + newAttack);
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
