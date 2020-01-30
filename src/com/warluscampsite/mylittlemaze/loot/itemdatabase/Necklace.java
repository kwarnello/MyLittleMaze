package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Necklace extends ItemBase {

	int manaBoost = 10;
	double manaRegenBoost = 0.25;

	public Necklace(ItemTypes itemType) {
		super(itemType);

		name = "Necklace";
		path = "res/armors/necklace/necklace.png";

		this.iceRes = 0.05;
	}

	@Override
	public void doThingsWithRarity(ItemRarity itemRarity) {
		super.doThingsWithRarity(itemRarity);

		manaBoost = (int) Math.round(manaBoost * itemRarity.getMultiplier());
		manaRegenBoost = manaRegenBoost * itemRarity.getMultiplier();

		additionalString.append("Mana +" + manaBoost + "<br>");
		additionalString.append("Mana regeneration +" + MyStringFormatter.formatDouble(manaRegenBoost, 2) + "/s");
	}

	@Override
	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
	}

	@Override
	public void doStats(Player player) {
		player.getMana().addBoostFromItem(manaBoost);
		player.getMana().addBoostFromItemRegen(manaRegenBoost);
		player.getResist(DamageTypes.ICE).addBoostsFromItem(iceRes);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
