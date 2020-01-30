package com.warluscampsite.mylittlemaze.maze;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MazeGrid {

	Random random = new Random();

	MazeSize mazeSize;

	boolean firstWalk;

	int mazeMaxX;
	int mazeMaxY;

	int currentX;
	int currentY;

	Map<Integer, Map<Integer, Cell>> mazeCells;

	public MazeGrid(MazeSize mazeSize) {
		this.mazeSize = mazeSize;

		mazeCells = new HashMap<>();

		firstWalk = true;

		createNewMaze();
	}

	private void createNewMaze() {
		mazeMaxX = random.nextInt(mazeSize.maxLength - mazeSize.minLength) + mazeSize.minLength;
		mazeMaxY = random.nextInt(mazeSize.maxLength - mazeSize.minLength) + mazeSize.minLength;

		for (int x = 1; x <= mazeMaxX; x++) {
			mazeCells.put(x, new HashMap<>());
			for (int y = 1; y <= mazeMaxY; y++) {
				mazeCells.get(x).put(y, new Cell());
			}
		}

		boolean shouldWalkAndHuntAgain = true;
		currentX = random.nextInt(mazeMaxX) + 1;
		currentY = random.nextInt(mazeMaxY) + 1;

		do {
			walk();
			shouldWalkAndHuntAgain = hunt();
		} while (shouldWalkAndHuntAgain);

		for (int x = 1; x <= mazeMaxX; x++) {
			for (int y = 1; y <= mazeMaxY; y++) {
				mazeCells.get(x).get(y).setWasVisited(false);
			}
		}
		//printMaze();
	}

	private void walk() {
		boolean canWalk = true;
		while (canWalk) {
			Direction direction = Direction.randomDirection();
			mazeCells.get(currentX).get(currentY).setWasVisited(true);

			if (canWalk(currentX, currentY, direction)) {
				destroyWalls(currentX, currentY, direction);
				currentX += direction.getDx();
				currentY += direction.getDy();

			} else if (canWalk(currentX, currentY, direction.rotate90())) {
				destroyWalls(currentX, currentY, direction.rotate90());
				currentX += direction.rotate90().getDx();
				currentY += direction.rotate90().getDy();

			} else if (canWalk(currentX, currentY, direction.rotate180())) {
				destroyWalls(currentX, currentY, direction.rotate180());
				currentX += direction.rotate180().getDx();
				currentY += direction.rotate180().getDy();

			} else if (canWalk(currentX, currentY, direction.rotate270())) {
				destroyWalls(currentX, currentY, direction.rotate270());
				currentX += direction.rotate270().getDx();
				currentY += direction.rotate270().getDy();
			} else
				canWalk = false;
		}
	}

	private boolean hunt() {
		for (int i = 1; i <= mazeMaxX; i++) {
			for (int j = 1; j <= mazeMaxY; j++)
				if (mazeCells.get(i).get(j).isWasVisited())
					if (hasUnvisitedNeighbour(i, j)) {
						currentX = i;
						currentY = j;
						return true;
					}
		}
		return false;
	}

	public boolean hasUnvisitedNeighbour(int i, int j) {
		Direction direction = Direction.randomDirection();
		if (canWalk(i, j, direction)) {
			return true;
		} else if (canWalk(i, j, direction.rotate90())) {
			return true;
		} else if (canWalk(i, j, direction.rotate180())) {
			return true;
		} else if (canWalk(i, j, direction.rotate270())) {
			return true;
		}

		return false;
	}

	private void destroyWalls(int x, int y, Direction direction) {
		switch (direction) {
		case NORTH:
			mazeCells.get(x + direction.getDx()).get(y + direction.getDy()).setHasSouthWall(false);
			mazeCells.get(x).get(y).setHasNorthWall(false);
			break;
		case SOUTH:
			mazeCells.get(x + direction.getDx()).get(y + direction.getDy()).setHasNorthWall(false);
			mazeCells.get(x).get(y).setHasSouthWall(false);
			break;
		case WEST:
			mazeCells.get(x + direction.getDx()).get(y + direction.getDy()).setHasEastWall(false);
			mazeCells.get(x).get(y).setHasWestWall(false);
			break;
		case EAST:
			mazeCells.get(x + direction.getDx()).get(y + direction.getDy()).setHasWestWall(false);
			mazeCells.get(x).get(y).setHasEastWall(false);
			break;
		default:
			break;
		}
	}

	public boolean canWalk(int x, int y, Direction direction) {
		// check if after move still inside maze
		if ((x + direction.getDx()) >= 1 && (x + direction.getDx()) <= mazeMaxX && (y + direction.getDy()) >= 1
				&& (y + direction.getDy()) <= mazeMaxY)
			// check if this cell was unvisited
			if (!mazeCells.get(x + direction.getDx()).get(y + direction.getDy()).isWasVisited())
				return true;
		return false;
	}

	public boolean partyCanMoveSomeWhere(int i, int j) {
		Direction direction = Direction.randomDirection();
		if (canWalk(i, j, direction) && thereIsNoWall(i, j, direction)) {
			return true;
		} else if (canWalk(i, j, direction.rotate90()) && thereIsNoWall(i, j, direction.rotate90())) {
			return true;
		} else if (canWalk(i, j, direction.rotate180()) && thereIsNoWall(i, j, direction.rotate180())) {
			return true;
		} else if (canWalk(i, j, direction.rotate270()) && thereIsNoWall(i, j, direction.rotate270())) {
			return true;
		}
		return false;
	}

	public boolean thereIsNoWall(int currentX2, int currentY2, Direction direction) {
		switch (direction) {
		case EAST:
			if (!mazeCells.get(currentX2).get(currentY2).isHasEastWall())
				return true;
			else
				return false;
		case NORTH:
			if (!mazeCells.get(currentX2).get(currentY2).isHasNorthWall())
				return true;
			else
				return false;
		case SOUTH:
			if (!mazeCells.get(currentX2).get(currentY2).isHasSouthWall())
				return true;
			else
				return false;
		case WEST:
			if (!mazeCells.get(currentX2).get(currentY2).isHasWestWall())
				return true;
			else
				return false;
		}
		return false;
	}

	public void setVisibleAround(int x, int y) {
		mazeCells.get(x).get(y).setVisible(true);

		for (Direction direction : Direction.values()) {
			if (canWalk(x, y, direction) && thereIsNoWall(x, y, direction))
				mazeCells.get(x + direction.getDx()).get(y + direction.getDy()).setVisible(true);
		}

	}

	@SuppressWarnings("unused")
	private void printMaze() {
		for (int x = 1; x <= mazeMaxX; x++) {
			System.out.print("__");
		}
		System.out.println();

		for (int y = 1; y <= mazeMaxY; y++) {
			for (int x = 1; x <= mazeMaxX; x++) {
				if (mazeCells.get(x).get(y).isHasWestWall())
					System.out.print("|");
				else
					System.out.print(" ");

				if (mazeCells.get(x).get(y).isHasSouthWall())
					System.out.print("_");
				else
					System.out.print(" ");

			}
			System.out.print("|");
			System.out.println();
		}
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public int getMazeMaxX() {
		return mazeMaxX;
	}

	public int getMazeMaxY() {
		return mazeMaxY;
	}

	public Map<Integer, Map<Integer, Cell>> getMazeCells() {
		return mazeCells;
	}

}
