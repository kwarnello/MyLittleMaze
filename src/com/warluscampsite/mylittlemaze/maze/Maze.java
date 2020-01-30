package com.warluscampsite.mylittlemaze.maze;

import com.warluscampsite.mylittlemaze.controllers.TimeController;
import com.warluscampsite.mylittlemaze.data.Data;
import com.warluscampsite.mylittlemaze.loot.Loot;
import com.warluscampsite.mylittlemaze.monsters.MonstersParty;

public class Maze {

	Data data;

	MazeSize mazeSize;
	MazeTypes mazeTypes;

	MazeGrid mazeGrid;

	MonstersParty monstersParty;
	Loot loot;

	int level;

	public Maze(Data data) {
		this.data = data;

		TimeController.addNewKey("£adowanie loot");
		loot = new Loot(this);
		TimeController.getTimeOfRunning("£adowanie loot");

		TimeController.addNewKey("£adowanie maze");
		initializeMaze();
		TimeController.getTimeOfRunning("£adowanie maze");

		TimeController.addNewKey("£adowanie monster party");
		monstersParty = new MonstersParty(this);
		TimeController.getTimeOfRunning("£adowanie monster party");

	}

	private void initializeMaze() {
		level = 0;
		newMaze();
	}

	public void newMaze() {
		TimeController.addNewKey("New maze");
		level++;

		mazeTypes = MazeTypes.getRandom(level);
		mazeSize = MazeSize.getRandom(level);
		mazeGrid = new MazeGrid(mazeSize);

		loot.newMazeNewLoot();

		// updatePartyCoordiantes
		data.getPlayerParty().getCoord().setRandomDuringMazeGeneration(mazeGrid.mazeMaxX, mazeGrid.mazeMaxY);
		mazeGrid.getMazeCells().get(data.getPlayerParty().getCoord().getX())
				.get(data.getPlayerParty().getCoord().getY()).setWasVisited(true);
		mazeGrid.setVisibleAround(data.getPlayerParty().getCoord().getX(), data.getPlayerParty().getCoord().getY());
		TimeController.getTimeOfRunning("New maze");
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public MonstersParty getMonstersParty() {
		return monstersParty;
	}

	public Data getData() {
		return data;
	}

	public Loot getLoot() {
		return loot;
	}

	public MazeGrid getMazeGrid() {
		return mazeGrid;
	}

	public int getLevel() {
		return level;
	}

	public MazeSize getMazeSize() {
		return mazeSize;
	}

	public MazeTypes getMazeTypes() {
		return mazeTypes;
	}

}
