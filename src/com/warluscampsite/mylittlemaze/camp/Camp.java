package com.warluscampsite.mylittlemaze.camp;

import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;

public class Camp {

	int smithLevel, conjurorLevel, chestLevel, familiarLevel;
	final int ascensionCost = 10000000;

	final double modifierForSmithLevel = 0.05, maxChance = 0.9;
	final int moreNothingThanBroken = 3;
	double prefixRerollProbability, nothingPrefixProbability, brokenPrefixProbability;
	double suffixRerollProbability, nothingSuffixProbability, brokenSuffixProbability;
	double rarityUpgradeProbability, nothingRarityUpgradeProbability, brokenRarityUpgradeProbability;

	public void calculateAllProbabilitiesForCrafting(Item item) {
		boolean isPrefix = item.getPrefix() != null;
		boolean isSuffix = item.getSuffix() != null;
		ItemRarity rarity = item.getItemRarity();

		calculatePrefixStuff(rarity, isPrefix);
		calculateSuffixStuff(rarity, isSuffix);
		calculateRarityUpgradeStuff(rarity);
	}

	private void calculatePrefixStuff(ItemRarity rairty, boolean isPrefix) {
		double base = (smithLevel * modifierForSmithLevel) / (1 + rairty.getForSorting());
		if (isPrefix)
			base = base * 2;
		if (base > maxChance)
			base = maxChance;

		prefixRerollProbability = base;
		nothingPrefixProbability = (1.0 - base) * (moreNothingThanBroken / (1. + moreNothingThanBroken));
		brokenPrefixProbability = (1.0 - base) * (1. / (1. + moreNothingThanBroken));
	}

	private void calculateSuffixStuff(ItemRarity rarity, boolean isSuffix) {
		double base = (smithLevel * modifierForSmithLevel) / (1 + rarity.getForSorting());
		if (isSuffix)
			base = base * 2;
		if (base > maxChance)
			base = maxChance;

		suffixRerollProbability = base;
		nothingSuffixProbability = (1.0 - base) * (moreNothingThanBroken / (1. + moreNothingThanBroken));
		brokenSuffixProbability = (1.0 - base) * (1. / (1. + moreNothingThanBroken));
	}

	private void calculateRarityUpgradeStuff(ItemRarity rarity) {
		double base = (smithLevel * modifierForSmithLevel)
				/ (1 + (rarity.getForSorting() * rarity.getForSorting()) / 2.0);
		if (base > maxChance)
			base = maxChance;

		rarityUpgradeProbability = base;
		nothingRarityUpgradeProbability = (1.0 - base) * (moreNothingThanBroken / (1. + moreNothingThanBroken));
		brokenRarityUpgradeProbability = (1.0 - base) * (1. / (1. + moreNothingThanBroken));
	}

	public String getTextForPrefix() {
		return "<html>\r\nReroll - " + MyStringFormatter.formatDoubleAsPercentage(getPrefixRerollProbability(), 0)
				+ " <br>\r\nNothing - " + MyStringFormatter.formatDoubleAsPercentage(getNothingPrefixProbability(), 0)
				+ "<br>\r\nBroken - " + MyStringFormatter.formatDoubleAsPercentage(getBrokenPrefixProbability(), 0)
				+ "</html>";
	}

	public String getTextForSuffix() {
		return "<html>\r\nReroll - " + MyStringFormatter.formatDoubleAsPercentage(getSuffixRerollProbability(), 0)
				+ " <br>\r\nNothing - " + MyStringFormatter.formatDoubleAsPercentage(getNothingSuffixProbability(), 0)
				+ "<br>\r\nBroken - " + MyStringFormatter.formatDoubleAsPercentage(getBrokenSuffixProbability(), 0)
				+ "</html>";
	}

	public String getTextForRarityUpgrade() {
		return "<html>\r\nUpgrade - " + MyStringFormatter.formatDoubleAsPercentage(getRarityUpgradeProbability(), 0)
				+ " <br>\r\nNothing - "
				+ MyStringFormatter.formatDoubleAsPercentage(getNothingRarityUpgradeProbability(), 0)
				+ "<br>\r\nLower rarity - "
				+ MyStringFormatter.formatDoubleAsPercentage(getBrokenRarityUpgradeProbability(), 0) + "</html>";
	}

	public void ascension() {
		System.out.println("NOT YET!");
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public void addSmithLevel() {
		smithLevel++;
	}

	public void addConjurorLevel() {
		conjurorLevel++;
	}

	public void addChestLevel() {
		chestLevel++;
	}

	public void addFamiliarLevel() {
		familiarLevel++;
	}

	public int getSmithLevel() {
		return smithLevel;
	}

	public int getConjurorLevel() {
		return conjurorLevel;
	}

	public int getChestLevel() {
		return chestLevel;
	}

	public int getFamiliarLevel() {
		return familiarLevel;
	}

	public int getAscensionCost() {
		return ascensionCost;
	}

	public double getPrefixRerollProbability() {
		return prefixRerollProbability;
	}

	public double getNothingPrefixProbability() {
		return nothingPrefixProbability;
	}

	public double getBrokenPrefixProbability() {
		return brokenPrefixProbability;
	}

	public double getSuffixRerollProbability() {
		return suffixRerollProbability;
	}

	public double getNothingSuffixProbability() {
		return nothingSuffixProbability;
	}

	public double getBrokenSuffixProbability() {
		return brokenSuffixProbability;
	}

	public double getRarityUpgradeProbability() {
		return rarityUpgradeProbability;
	}

	public double getNothingRarityUpgradeProbability() {
		return nothingRarityUpgradeProbability;
	}

	public double getBrokenRarityUpgradeProbability() {
		return brokenRarityUpgradeProbability;
	}

}
