package com.warluscampsite.mylittlemaze.maze;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum MazeSize {

		TINY(3, 4, 50, 0.7),
		SMALL(5, 6, 50, 0.9),
		MEDIUM(7, 8, 70, 1.0),
		BIG(9, 11, 40, 1.2),
		HUGE(12, 14, 20, 1.3),
		EPIC(15, 17, 10, 1.5);

	int minLength, maxLength;
	int probabilityWeight;
	double difficultyMultiplier;

	private MazeSize(int minLength, int maxLength, int probabilityWeight, double difficultyMultiplier) {
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.probabilityWeight = probabilityWeight;
		this.difficultyMultiplier = difficultyMultiplier;
	}

	private static final List<MazeSize> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final Random RANDOM = new Random();
	private static final int PROBABILITY_WEIGHT = summWeigts();

	private static int summWeigts() {
		int summ = 0;
		for (MazeSize value : VALUES)
			summ += value.getProbabilityWeight();
		return summ;
	}

	public static MazeSize getRandom(int mazeLevel) {
		int randomNum = RANDOM.nextInt(PROBABILITY_WEIGHT - 50);

		int currentWeightSumm = mazeLevel;

		for (MazeSize value : MazeSize.values()) {
			if (randomNum > currentWeightSumm && randomNum <= (currentWeightSumm + value.getProbabilityWeight())) {
				return value;
			}
			currentWeightSumm += value.getProbabilityWeight();
		}

		return EPIC;
	}

	public int getProbabilityWeight() {
		return probabilityWeight;
	}

	public double getDifficultyMultiplier() {
		return difficultyMultiplier;
	}

	
}
