package com.warluscampsite.mylittlemaze.loot.prefixes;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Sharp extends Prefix {

	// Prefix that boosts attack and physical damage of weapon

	int attackBoost = 2;
	int damageBoost = 1;

	public Sharp(int[] possibleItemTypes) {
		super(possibleItemTypes);
	}

	@Override
	public void doThingsDuringConstructItem(Item item) {
		int minDamage = item.getItemBase().getMinDamage();
		minDamage += damageBoost;
		item.getItemBase().setMinDamage(minDamage);

		int maxDamage = item.getItemBase().getMaxDamage();
		maxDamage += damageBoost;
		item.getItemBase().setMaxDamage(maxDamage);

		double attack = item.getItemBase().getAttack();
		attack += attackBoost;
		item.getItemBase().setAttack(attack);
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
	}

	@Override
	public void doStats(Player player, Item item) {

	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		attackBoost = (int) Math.round(attackBoost * itemRarity.getMultiplier());
		damageBoost = (int) Math.round(damageBoost * itemRarity.getMultiplier());
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
