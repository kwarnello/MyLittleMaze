package com.warluscampsite.mylittlemaze.loot.prefixes;

import java.util.Map;
import java.util.Random;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Malicious extends Prefix {

	Random random = new Random();

	// Prefix that boosts darkness damage of weapon
	int minDamageBoost = 10, maxDamageBoost = 25;

	public Malicious(int[] possibleItemTypes) {
		super(possibleItemTypes);
	}

	@Override
	public void doThingsDuringConstructItem(Item item) {
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
		double damage = random.nextDouble() * (maxDamageBoost - minDamageBoost) + minDamageBoost;
		damage += damageMap.get(DamageTypes.DARKNESS);
		damageMap.put(DamageTypes.DARKNESS, damage);
	}

	@Override
	public void doStats(Player player, Item item) {

	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		minDamageBoost = (int) Math.round(minDamageBoost * itemRarity.getMultiplier());
		maxDamageBoost = (int) Math.round(maxDamageBoost * itemRarity.getMultiplier());

		describtion = "Malicious - add " + minDamageBoost + " - " + maxDamageBoost + " darkness damage";
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
