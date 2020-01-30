package com.warluscampsite.mylittlemaze.maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.warluscampsite.mylittlemaze.monsters.MonsterType;

public enum MazeTypes {

		FOREST(1, new MonsterType[] { MonsterType.ANIMAL }),
		CEMETERY(1, new MonsterType[] { MonsterType.UNDEAD }),
		CASTLE(1, new MonsterType[] { MonsterType.TEMP }),

		EVIL_TOWER(15, new MonsterType[] { MonsterType.TEMP }),

		ABYSS(50, new MonsterType[] { MonsterType.TEMP });

	int minLevelToAppear;
	MonsterType[] monstersTypeThatCanAppear;

	private MazeTypes(int minLevelToAppear, MonsterType[] monstersTypeThatCanAppear) {
		this.minLevelToAppear = minLevelToAppear;
		this.monstersTypeThatCanAppear = monstersTypeThatCanAppear;
	}

	private static final List<MazeTypes> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final Random RANDOM = new Random();

	public static MazeTypes getRandom(int mazeLevel) {
		ArrayList<MazeTypes> listOfMazesThatCanAppear = new ArrayList<>();

		for (MazeTypes mazeTypes : VALUES) {
			if (mazeTypes.minLevelToAppear <= mazeLevel)
				listOfMazesThatCanAppear.add(mazeTypes);
		}

		return listOfMazesThatCanAppear.get(RANDOM.nextInt(listOfMazesThatCanAppear.size()));
	}

	@Override
	public String toString() {
		String name = super.toString().substring(0, 1).toUpperCase() + super.toString().substring(1).toLowerCase();
		name = name.replace("_", " ");

		return name;
	}

	public MonsterType[] getMonstersTypeThatCanAppear() {
		return monstersTypeThatCanAppear;
	}

}
