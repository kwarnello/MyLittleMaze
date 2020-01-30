package com.warluscampsite.mylittlemaze.loot.prefixes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ItemPrefixes {

		// add possible items where can exist
		// 0 - armors, 1 - jewellery, 2 - melee weapon, 3 - ranged weapon, 4 -
		// magic weapon, 5 - shield, 6 - misc;
		// For armors
		THOUGH(1, 15, new Tough(new int[] { 0, 5 })),
		ELVISH(10, 8, new Elvish(new int[] { 0 })),

		// For weapons
		SHARP(1, 10, new Sharp(new int[] { 2, 3 })),
		MALICIOUS(25, 2, new Malicious(new int[] { 2, 3, 4 })),

		// For jewellery
		SHINY(1, 10, new Shiny(new int[] { 1 })),
		BLACK(10, 7, new Black(new int[] { 1 })),
		BLOODY(25, 3, new Bloody(new int[] { 1 }));

	int minLevelToAppear;
	int weightProbability;
	Prefix prefix;

	private ItemPrefixes(int minLevelToAppear, int weightProbability, Prefix prefix) {
		this.minLevelToAppear = minLevelToAppear;
		this.weightProbability = weightProbability;
		this.prefix = prefix;
	}

	private static final List<ItemPrefixes> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final Random RANDOM = new Random();

	public static ArrayList<ItemPrefixes> getListOfPrefixesThatCanAppear(int mazeLevel) {
		ArrayList<ItemPrefixes> listOfPrefixesThatCanAppear = new ArrayList<>();

		for (ItemPrefixes prefix : VALUES) {
			if (prefix.minLevelToAppear <= mazeLevel)
				listOfPrefixesThatCanAppear.add(prefix);
		}

		return listOfPrefixesThatCanAppear;
	}

	public static Prefix getRandomFromList(ArrayList<ItemPrefixes> list, int weightSum) {
		if (weightSum == 0)
			return null;

		int randomNum = RANDOM.nextInt(weightSum);

		int currentWeightSumm = 0;

		for (ItemPrefixes value : list) {
			if (randomNum > currentWeightSumm && randomNum <= (currentWeightSumm + value.getWeightProbability())) {
				return value.getPrefix();
			}
			currentWeightSumm += value.getWeightProbability();
		}

		return null;
	}

	public Prefix getPrefix() {
		return prefix;
	}

	public int getWeightProbability() {
		return weightProbability;
	}

}
