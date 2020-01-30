package com.warluscampsite.mylittlemaze.playerteam.classes;

public class Passive {

	String iconPath;
	String description;

	boolean activated;

	public Passive(String iconPath, String description) {
		super();
		this.iconPath = iconPath;
		this.description = description;

		activated = false;
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public String getIconPath() {
		return iconPath;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getDescription() {
		return description;
	}

}
