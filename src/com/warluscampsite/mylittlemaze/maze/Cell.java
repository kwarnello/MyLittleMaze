package com.warluscampsite.mylittlemaze.maze;

public class Cell {

	boolean hasNorthWall = true, hasSouthWall = true, hasWestWall = true, hasEastWall = true;
	boolean wasVisited = false;
	boolean visible = false;

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public boolean isWasVisited() {
		return wasVisited;
	}

	public void setWasVisited(boolean wasVisited) {
		this.wasVisited = wasVisited;
	}

	public boolean isHasNorthWall() {
		return hasNorthWall;
	}

	public void setHasNorthWall(boolean hasNorthWall) {
		this.hasNorthWall = hasNorthWall;
	}

	public boolean isHasSouthWall() {
		return hasSouthWall;
	}

	public void setHasSouthWall(boolean hasSouthWall) {
		this.hasSouthWall = hasSouthWall;
	}

	public boolean isHasWestWall() {
		return hasWestWall;
	}

	public void setHasWestWall(boolean hasWestWall) {
		this.hasWestWall = hasWestWall;
	}

	public boolean isHasEastWall() {
		return hasEastWall;
	}

	public void setHasEastWall(boolean hasEastWall) {
		this.hasEastWall = hasEastWall;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	

}
