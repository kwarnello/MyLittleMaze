package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Signet extends ItemBase {

	int strengthBoost = 1;

	public Signet(ItemTypes itemType) {
		super(itemType);

		name = "Signet";
		path = "res/armors/rings/signet.png";
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		super.doThingsWithRarity(itemRarity);

		strengthBoost = (int) Math.round(strengthBoost * itemRarity.getMultiplier());

		additionalString.append("Strength +" + strengthBoost);
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
	}

	@Override
	public void doStats(Player player) {
		player.getAttributes().getAttribute(AttributesEnum.STRENGTH).addFromItem(strengthBoost);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
