package com.warluscampsite.mylittlemaze.loot.suffixes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ItemSuffixes {
		// add possible items where can exist
		// 0 - armors, 1 - jewellery, 2 - melee weapon, 3 - ranged weapon, 4 -
		// magic weapon, 5 - shield, 6 - misc;

		// For armors
		TURTLE(1, 20, new Turtle(new int[] { 0, 5 })),
		ELEMENTALIST(1, 20, new Elementalist(new int[] { 0, 5 })),

		// For weapons
		FLAMES(1, 15, new Flames(new int[] { 2, 3, 4 })),
		ORCHID(15, 5, new Orchid(new int[] { 2, 3, 4 })),

		// For jewellery
		SHAMANISM(1, 5, new Shamanism(new int[] { 1 })),
		GENIUS(5, 10, new Genius(new int[] { 1 })),
		TIGER(15, 5, new Tiger(new int[] { 1 }));

	int minLevelToAppear;
	int weightProbability;
	Suffix suffix;

	private ItemSuffixes(int minLevelToAppear, int weightProbability, Suffix sufix) {
		this.minLevelToAppear = minLevelToAppear;
		this.weightProbability = weightProbability;
		this.suffix = sufix;
	}

	private static final List<ItemSuffixes> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final Random RANDOM = new Random();

	public static ArrayList<ItemSuffixes> getListOfPrefixesThatCanAppear(int mazeLevel) {
		ArrayList<ItemSuffixes> listOfSuffixesThatCanAppear = new ArrayList<>();

		for (ItemSuffixes suffix : VALUES) {
			if (suffix.minLevelToAppear <= mazeLevel)
				listOfSuffixesThatCanAppear.add(suffix);
		}

		return listOfSuffixesThatCanAppear;
	}

	public static Suffix getRandomFromList(ArrayList<ItemSuffixes> list, int weightSum) {
		if (weightSum == 0)
			return null;

		int randomNum = RANDOM.nextInt(weightSum);

		int currentWeightSumm = 0;

		for (ItemSuffixes value : list) {
			if (randomNum > currentWeightSumm && randomNum <= (currentWeightSumm + value.getWeightProbability())) {
				return value.getSuffix();
			}
			currentWeightSumm += value.getWeightProbability();
		}

		return null;
	}

	public Suffix getSuffix() {
		return suffix;
	}

	public int getWeightProbability() {
		return weightProbability;
	}

}
