package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class CopperRing extends ItemBase {

	int wisdomBoost = 1;

	public CopperRing(ItemTypes itemType) {
		super(itemType);

		name = "Copper ring";
		path = "res/armors/rings/copperRing.png";

		this.fireRes = 0.05;
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		super.doThingsWithRarity(itemRarity);

		wisdomBoost = (int) Math.round(wisdomBoost * itemRarity.getMultiplier());

		additionalString.append("Wisdom +" + wisdomBoost);
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
	}

	@Override
	public void doStats(Player player) {
		player.getAttributes().getAttribute(AttributesEnum.WISDOM).addFromItem(wisdomBoost);
		player.getResist(DamageTypes.FIRE).addBoostsFromItem(fireRes);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
