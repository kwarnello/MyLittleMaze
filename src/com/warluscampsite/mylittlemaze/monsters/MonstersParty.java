package com.warluscampsite.mylittlemaze.monsters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.warluscampsite.mylittlemaze.maze.Maze;

public class MonstersParty {

	Maze maze;

	Random random = new Random();
	final int MAX_MONSTER_NUMBER = 6;

	int difficultyOfMonsterParty;

	Map<Integer, Monster> placeMap;

	public MonstersParty(Maze maze) {
		this.maze = maze;

		placeMap = new HashMap<Integer, Monster>();

		for (int n = 1; n < 6; n++) {
			placeMap.put(n, null);
		}
		reconstructMonsterTeam();

	}

	public boolean checkIfAllDeath() {
		for (Entry<Integer, Monster> character : placeMap.entrySet()) {
			if (!character.getValue().isDead())
				return false;
		}
		return true;
	}

	public void reconstructMonsterTeam() {
		placeMap.clear();

		int x = random.nextInt(MAX_MONSTER_NUMBER) + 1;
		ArrayList<MonstersDatabase> listOfMonstersThatCanAppear = MonstersDatabase
				.getListOfMonstersThatCanAppear(maze.getLevel(), maze.getMazeTypes());

		int n = 0;
		do {
			n++;
			int placeToPut = 0;
			boolean isFree = false;
			do {
				placeToPut = random.nextInt(MAX_MONSTER_NUMBER) + 1;
				if (!placeMap.containsKey(placeToPut))
					isFree = true;
			} while (!isFree);

			// temporary add everything everywhere!
			newMonster(placeToPut, listOfMonstersThatCanAppear);
		} while (n < x);

		calculateDifficultyOfMonstersParty();
	}

	private void newMonster(int place, ArrayList<MonstersDatabase> listOfMonstersThatCanAppear) {
		MonstersDatabase monster = listOfMonstersThatCanAppear.get(random.nextInt(listOfMonstersThatCanAppear.size()));

		Class<?> clazz;
		Constructor<?> constructor;
		Object monsterBase = null;
		try {
			clazz = Class.forName(monster.getMonster().getClass().getName());
			constructor = clazz.getConstructor(int.class);
			monsterBase = constructor.newInstance(maze.getLevel());
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		placeMap.put(place, (Monster) monsterBase);
		placeMap.get(place).setPlace(place);
	}

	private void calculateDifficultyOfMonstersParty() {
		difficultyOfMonsterParty = 0;

		for (Monster monster : placeMap.values()) {
			difficultyOfMonsterParty += 100;
		}

		difficultyOfMonsterParty = (int) (difficultyOfMonsterParty * maze.getMazeSize().getDifficultyMultiplier());
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
	public Map<Integer, Monster> getPlaceMap() {
		return placeMap;
	}

	public Monster getRandomAlive() {
		ArrayList<Integer> listOfAlive = new ArrayList<>();

		for (Entry<Integer, Monster> monster : placeMap.entrySet()) {
			if (!monster.getValue().isDead())
				listOfAlive.add(monster.getKey());
		}

		return placeMap.get(listOfAlive.get(random.nextInt(listOfAlive.size())));
	}

	public int getDifficultyOfMonsterParty() {
		return difficultyOfMonsterParty;
	}

}
