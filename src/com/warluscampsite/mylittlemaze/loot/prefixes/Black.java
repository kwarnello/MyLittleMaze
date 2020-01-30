package com.warluscampsite.mylittlemaze.loot.prefixes;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Black extends Prefix {

	double darknessDamage = 2;
	double darknessRes = 0.04;

	public Black(int[] possibleItemTypes) {
		super(possibleItemTypes);

		describtion = "Black - add " + MyStringFormatter.formatDouble(darknessDamage, 1)
				+ " darkness damage for attack.<br>" + "Darkness resistance "
				+ MyStringFormatter.formatDoubleAsPercentage(darknessRes, 0);
	}

	@Override
	public void doThingsDuringConstructItem(Item item) {
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
		double damage = darknessDamage + damageMap.get(DamageTypes.DARKNESS);
		damageMap.put(DamageTypes.DARKNESS, damage);
	}

	@Override
	public void doStats(Player player, Item item) {
		player.getResist(DamageTypes.DARKNESS).addBoostsFromItem(darknessRes);
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		darknessDamage = darknessDamage * itemRarity.getMultiplier();
		darknessRes = darknessRes * itemRarity.getMultiplier();

		describtion = "Black - add " + MyStringFormatter.formatDouble(darknessDamage, 1)
				+ " darkness damage for attack.<br>" + "Darkness resistance "
				+ MyStringFormatter.formatDoubleAsPercentage(darknessRes, 0);
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
