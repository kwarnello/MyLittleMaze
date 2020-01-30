package com.warluscampsite.mylittlemaze.loot;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.WeaponBase;
import com.warluscampsite.mylittlemaze.loot.prefixes.Prefix;
import com.warluscampsite.mylittlemaze.loot.suffixes.Suffix;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Item {

	public static final int BASE_GOLD_VALUE = 10;
	public static final double AFFIX_MULTIPLIER = 1.8;

	String name;
	String toolTip;

	ItemBase itemBase;
	ItemRarity itemRarity;

	Prefix prefix;
	Suffix suffix;

	int goldValue;

	public Item(ItemBase itemBase, ItemRarity itemRarity, Prefix prefix, Suffix suffix) {
		super();
		this.itemBase = itemBase;
		this.itemRarity = itemRarity;
		this.prefix = prefix;
		this.suffix = suffix;

		name = "";

		if (prefix != null)
			name += prefix.getClass().getSimpleName() + " ";

		name += itemBase.getName();

		if (suffix != null)
			name += " of " + suffix.getClass().getSimpleName();

		doThingsWithRarity(itemRarity);
		doThingsDuringConstructItem();

		constructStringForTooltip();

		calculateGoldValue();
	}

	private void doThingsWithRarity(ItemRarity itemRarity) {
		itemBase.doThingsWithRarity(itemRarity);
		if (prefix != null)
			prefix.doThingsWithRarity(itemRarity);
		if (suffix != null)
			suffix.doThingsWithRarity(itemRarity);
	}

	private void doThingsDuringConstructItem() {
		if (prefix != null)
			prefix.doThingsDuringConstructItem(this);
		if (suffix != null)
			suffix.doThingsDuringConstructItem(this);
	}

	private void constructStringForTooltip() {
		StringBuilder s = new StringBuilder();

		s.append("<center><b>" + name + "</b></center>");
		s.append("<hr />");
		s.append("Rarity " + itemRarity.toString() + "<br>");
		s.append(itemBase.getItemType().toString() + "<br>" + "<br>");

		if (WeaponBase.class.isAssignableFrom(itemBase.getClass())) {
			s.append("Damage: " + MyStringFormatter.formatDouble(itemBase.getMinDamage(), 0) + "-"
					+ MyStringFormatter.formatDouble(itemBase.getMaxDamage(), 0) + "<br>");
			s.append("Number of attacks: " + itemBase.getNumberOfAttacks() + "<br>");
			s.append("Attacks per second: " + MyStringFormatter.formatAsAPS(itemBase.getAttackDelay(), 2) + "<br>");
			s.append("Critical chance: " + MyStringFormatter.formatDoubleAsPercentage(itemBase.getCritChance(), 2)
					+ "<br>");
			s.append("Critical multiplier: "
					+ MyStringFormatter.formatDoubleAsPercentage(itemBase.getCritMultiplier(), 0) + "<br>");
		}

		if (itemBase.getAttack() != 0)
			s.append("Attack: " + (int) itemBase.getAttack() + "<br>");
		if (itemBase.getDefence() != 0)
			s.append("Defence: " + (int) itemBase.getDefence() + "<br>");

		if (itemBase.getArmor() != 0)
			s.append("Armor: " + (int) itemBase.getArmor() + "<br>");

		if (itemBase.getEnergyShield() != 0)
			s.append("Energy Shield: " + (int) itemBase.getEnergyShield() + "<br>");

		if (itemBase.getDodge() != 0)
			s.append("Dodge: " + MyStringFormatter.formatDoubleAsPercentage(itemBase.getDodge(), 1) + "<br>");

		if (itemBase.getFireRes() != 0)
			s.append("Fire resistance: " + MyStringFormatter.formatDoubleAsPercentage(itemBase.getFireRes(), 0)
					+ "<br>");

		if (itemBase.getIceRes() != 0)
			s.append("Ice resistance: " + MyStringFormatter.formatDoubleAsPercentage(itemBase.getIceRes(), 0) + "<br>");

		if (itemBase.getEarthRes() != 0)
			s.append("Earth resistance: " + MyStringFormatter.formatDoubleAsPercentage(itemBase.getEarthRes(), 0)
					+ "<br>");

		if (itemBase.getLightningRes() != 0)
			s.append("Lightning resistance: "
					+ MyStringFormatter.formatDoubleAsPercentage(itemBase.getLightningRes(), 0) + "<br>");

		if (itemBase.getPoisonRes() != 0)
			s.append("Poison resistance: " + MyStringFormatter.formatDoubleAsPercentage(itemBase.getPoisonRes(), 0)
					+ "<br>");

		if (itemBase.getDarknessRes() != 0)
			s.append("Darkness resistance: " + MyStringFormatter.formatDoubleAsPercentage(itemBase.getDarknessRes(), 0)
					+ "<br>");

		if (itemBase.getRadianceRes() != 0)
			s.append("Radiance resistance: " + MyStringFormatter.formatDoubleAsPercentage(itemBase.getRadianceRes(), 0)
					+ "<br>");

		if (itemBase.getAdditionalString().length() != 0)
			s.append(itemBase.getAdditionalString() + "<br>");

		if (prefix != null)
			if (prefix.getDescribtion() != null)
				s.append("<br>" + prefix.getDescribtion());
		if (suffix != null)
			if (suffix.getDescribtion() != null)
				s.append("<br>" + suffix.getDescribtion());

		toolTip = s.toString();

	}

	public void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap) {
		itemBase.doAttack(player, defender, skill, damageMap);

		if (prefix != null)
			prefix.doAttack(player, defender, skill, damageMap);

		if (suffix != null)
			suffix.doAttack(player, defender, skill, damageMap);

	}

	public void doDuringEquip(Player player) {
		itemBase.doStats(player);

		if (prefix != null)
			prefix.doStats(player, this);
		if (suffix != null)
			suffix.doStats(player, this);
	}

	private void calculateGoldValue() {
		double base = BASE_GOLD_VALUE;

		if (prefix != null)
			base = base * AFFIX_MULTIPLIER;
		if (suffix != null)
			base = base * AFFIX_MULTIPLIER;

		base = base * getItemRarity().getMultiplier();

		goldValue = (int) base;
	}

	public String getName() {
		return name;
	}

	public ItemBase getItemBase() {
		return itemBase;
	}

	public ItemRarity getItemRarity() {
		return itemRarity;
	}

	public Prefix getPrefix() {
		return prefix;
	}

	public Suffix getSuffix() {
		return suffix;
	}

	public String getToolTip() {
		return toolTip;
	}

	public int getGoldValue() {
		return goldValue;
	}

}
