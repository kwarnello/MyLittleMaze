package com.warluscampsite.mylittlemaze.loot.prefixes;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Bloody extends Prefix {

	int leechDamageAtEachHit = 3;

	public Bloody(int[] possibleItemTypes) {
		super(possibleItemTypes);

		describtion = "Bloody - " + leechDamageAtEachHit + " health point for every hit.";
	}

	@Override
	public void doThingsDuringConstructItem(Item item) {
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
		double damage = leechDamageAtEachHit + damageMap.get(DamageTypes.LEECH);
		damageMap.put(DamageTypes.LEECH, damage);
	}

	@Override
	public void doStats(Player player, Item item) {
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		leechDamageAtEachHit = (int) ((int) leechDamageAtEachHit * itemRarity.getMultiplier());

		describtion = "Bloody - " + leechDamageAtEachHit + " health point for every hit.";
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
