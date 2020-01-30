package com.warluscampsite.mylittlemaze.loot.suffixes;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public abstract class Suffix {

	int[] possibleItemTypes;
	String describtion;

	// add possible items where can exist
	// 0 - armors, 1 - jewellery, 2 - melee weapon, 3 - ranged weapon, 4 -
	// magic weapon, 5 - shield, 6 - misc;

	public Suffix(int[] possibleItemTypes) {
		super();
		this.possibleItemTypes = possibleItemTypes;

		describtion = null;
	}

	public abstract void doThingsWithRarity(ItemRarity itemRarity);

	public abstract void doThingsDuringConstructItem(Item item);

	public abstract void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap);

	public abstract void doStats(Player player, Item item);

	public abstract void addThingsToItemBase(ItemBase itemBase);

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
	public int[] getPossibleItemTypes() {
		return possibleItemTypes;
	}

	public String getDescribtion() {
		return describtion;
	}

}
