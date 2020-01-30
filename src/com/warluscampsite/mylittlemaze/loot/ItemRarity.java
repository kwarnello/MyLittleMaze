package com.warluscampsite.mylittlemaze.loot;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ItemRarity {

		BROKEN(1000, 5, 0.8, Color.GRAY, 0),
		NORMAL(1000, 10, 1.0, Color.BLACK, 1),
		MAGIC(200, 25, 1.3, Color.BLUE, 2),
		RARE(100, 40, 1.5, Color.YELLOW, 3),
		EPIC(10, 60, 2.0, Color.MAGENTA, 4),
		UNIQUE(1, 85, 3.0, Color.ORANGE, 5);

	int probabilityWeight;
	int probabilityOfExists; // in percentage
	double multiplier;
	Color color;
	int forSorting;

	private ItemRarity(int pobrabilityWeight, int probabilityOfExists, double multiplier, Color color, int forSorting) {
		this.probabilityWeight = pobrabilityWeight;
		this.probabilityOfExists = probabilityOfExists;
		this.multiplier = multiplier;
		this.color = color;
		this.forSorting = forSorting;
	}

	private static final List<ItemRarity> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final Random RANDOM = new Random();
	private static final int PROBABILITY_WEIGHT = summWeigts();

	private static int summWeigts() {
		int summ = 0;
		for (ItemRarity value : VALUES)
			summ += value.getProbabilityWeight();
		return summ;
	}

	public static ItemRarity getRandom(int luck, int mazeLevel) {
		int randomNum = RANDOM.nextInt(PROBABILITY_WEIGHT - 100) + luck + mazeLevel;

		int currentWeightSumm = 0;

		for (ItemRarity value : VALUES) {
			if (randomNum <= 0)
				return BROKEN;
			if (randomNum > currentWeightSumm && randomNum <= (currentWeightSumm + value.getProbabilityWeight())) {
				return value;
			}
			currentWeightSumm += value.getProbabilityWeight();
		}

		return UNIQUE;
	}

	public static ItemRarity getHigherTier(ItemRarity rarity) {
		switch (rarity) {
		case BROKEN:
			return NORMAL;
		case EPIC:
			return UNIQUE;
		case MAGIC:
			return RARE;
		case NORMAL:
			return MAGIC;
		case RARE:
			return EPIC;
		case UNIQUE:
			return UNIQUE;
		default:
			return BROKEN;
		}
	}

	public static ItemRarity getLowerTier(ItemRarity rarity) {
		switch (rarity) {
		case BROKEN:
			return BROKEN;
		case EPIC:
			return RARE;
		case MAGIC:
			return NORMAL;
		case NORMAL:
			return BROKEN;
		case RARE:
			return MAGIC;
		case UNIQUE:
			return EPIC;
		default:
			return BROKEN;
		}
	}

	public static boolean checkIfAffixesShouldExists(ItemRarity rarity) {
		int percentage = RANDOM.nextInt(100);
		if (percentage <= rarity.getProbabilityOfExists())
			return true;
		else
			return false;
	}

	public Color getColor() {
		return color;
	}

	public int getProbabilityWeight() {
		return probabilityWeight;
	}

	public double getMultiplier() {
		return multiplier;
	}

	public int getProbabilityOfExists() {
		return probabilityOfExists;
	}

	public int getForSorting() {
		return forSorting;
	}

}
