package com.warluscampsite.mylittlemaze.loot.suffixes;

import java.util.Map;
import java.util.Random;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Flames extends Suffix {
	
	//Add fire damage during attack

	Random random = new Random();
	double minFlamesFamageBoost = 4;
	double maxFlamesFamageBoost = 8;

	public Flames(int[] possibleItemTypes) {
		super(possibleItemTypes);
	}

	@Override
	public void doThingsDuringConstructItem(Item item) {
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
		double damage = random.nextDouble() * (maxFlamesFamageBoost - minFlamesFamageBoost) + minFlamesFamageBoost;
		damage += damageMap.get(DamageTypes.FIRE);
		damageMap.put(DamageTypes.FIRE, damage);
	}

	@Override
	public void doStats(Player player, Item item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		minFlamesFamageBoost = minFlamesFamageBoost * itemRarity.getMultiplier();
		maxFlamesFamageBoost = maxFlamesFamageBoost * itemRarity.getMultiplier();

		describtion = "Flames - add fire damage " + (int) minFlamesFamageBoost + "-" + (int) maxFlamesFamageBoost;
	}

	@Override
	public void addThingsToItemBase(ItemBase itemBase) {
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
}
