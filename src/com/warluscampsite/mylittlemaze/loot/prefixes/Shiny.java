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

public class Shiny extends Prefix {

	int luckBoost = 1;

	public Shiny(int[] possibleItemTypes) {
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
		player.getAttributes().getAttribute(AttributesEnum.LUCK).addFromItem(luckBoost);
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		luckBoost = (int) (luckBoost * itemRarity.getMultiplier());
		if (luckBoost == 0)
			luckBoost = 1;

		describtion = "Shiny - add " + luckBoost + " luck.";
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
