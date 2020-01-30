package com.warluscampsite.mylittlemaze.monsters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.warluscampsite.mylittlemaze.maze.MazeTypes;

public enum MonstersDatabase {

	// ANIMALS
	DUMMY(),

	// ANIMALS
	RAT(), WOLF(), BEAR(),

	// UNDEAD
	SKELETON(), ZOMBIE(new Zombie(1), 1);

	Monster monster;
	int minimumLevelToRespawn;

	private MonstersDatabase() {
		this.monster = new Zombie(1);
		this.minimumLevelToRespawn = 5;
	}

	private MonstersDatabase(Monster monster, int minimumLevelToRespawn) {
		this.monster = monster;
		this.minimumLevelToRespawn = minimumLevelToRespawn;

	}

	private static final List<MonstersDatabase> VALUES = Collections.unmodifiableList(Arrays.asList(values()));

	public static ArrayList<MonstersDatabase> getListOfMonstersThatCanAppear(int mazeLevel, MazeTypes mazeTypes) {
		ArrayList<MonstersDatabase> listOfMonstersThatCanAppear = new ArrayList<>();

		for (MonstersDatabase monster : VALUES) {
			if (monster.minimumLevelToRespawn <= mazeLevel)
				for (MonsterType monsterTypeThatCanAppear : mazeTypes.getMonstersTypeThatCanAppear()) {
					if (monsterTypeThatCanAppear.equals(monster.getMonster().getMonsterType()))
						listOfMonstersThatCanAppear.add(monster);
				}
		}

		if (listOfMonstersThatCanAppear.isEmpty())
			listOfMonstersThatCanAppear.add(MonstersDatabase.DUMMY);

		return listOfMonstersThatCanAppear;
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public Monster getMonster() {
		return monster;
	}
}
