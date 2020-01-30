package com.warluscampsite.mylittlemaze.maze;

import java.util.Random;

public class Coordinates {

	Random random = new Random();

	int x = 1, y = 1;

	public void setRandomDuringMazeGeneration(int mazeMaxX, int mazeMaxY) {
		Direction direction = Direction.randomDirection();
		switch (direction) {
		case EAST:
			setX(mazeMaxX);
			setY(random.nextInt(mazeMaxY) + 1);
			break;
		case NORTH:
			setX(random.nextInt(mazeMaxX) + 1);
			setY(mazeMaxY);
			break;
		case SOUTH:
			setX(random.nextInt(mazeMaxX) + 1);
			setY(1);
			break;
		case WEST:
			setX(1);
			setY(random.nextInt(mazeMaxY) + 1);
			break;
		default:
			break;
		}

	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
