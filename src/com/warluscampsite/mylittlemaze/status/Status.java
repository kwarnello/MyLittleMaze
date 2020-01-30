package com.warluscampsite.mylittlemaze.status;

public abstract class Status {

	String name;

	String pathToImage;

	boolean isItGood, newForPainting;

	int statusTime;
	int actualTime;

	public Status(int statusTime) {
		this.statusTime = statusTime;
		newForPainting = true;
	}

	public void addTimeFlow(int millis) {
		actualTime += millis;
	}

	public boolean checkIfShouldEnd() {
		if (actualTime >= statusTime)
			return true;
		return false;
	}

	public void renew(Status status) {
		int newStatus = status.getStatusTime();

		if (this.statusTime < newStatus)
			this.statusTime = newStatus;
		actualTime = 0;
	}

	public void remove() {

	};

	public String getToolTip() {
		StringBuilder s = new StringBuilder();

		return s.toString();
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public String getName() {
		return name;
	}

	public int getStatusTime() {
		return statusTime;
	}

	public int getActualTime() {
		return actualTime;
	}

	public boolean isNewForPainting() {
		return newForPainting;
	}

	public void setNewForPainting(boolean newForPainting) {
		this.newForPainting = newForPainting;
	}

	public String getPathToImage() {
		return pathToImage;
	}

	public boolean isItGood() {
		return isItGood;
	}
}
