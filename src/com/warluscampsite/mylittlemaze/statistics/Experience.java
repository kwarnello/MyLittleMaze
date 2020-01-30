package com.warluscampsite.mylittlemaze.statistics;

import com.warluscampsite.mylittlemaze.controllers.TextAreaController;

public class Experience {

	Characterr character;

	long experience;
	int level;

	/*** Multipler for leveling ***/
	int aLevelUpMultipler = 50;
	int bLevelUpMultipler = 100;
	int cLevelUpMultipler = 50;

	int attributesForLevelUp = 5;

	public Experience(Characterr character) {
		this.character = character;

		experience = 0;
		level = 1;
	}

	public void addExperience(int newExp) {
		experience += newExp;

		while (experience >= checkExpToNextLevel()) {
			levelUp();
		}
	}

	public int checkExpToNextLevel() {
		return (int) (aLevelUpMultipler * Math.pow(level - 1, 2) + bLevelUpMultipler * (level - 1) + cLevelUpMultipler);
	}

	private void levelUp() {
		experience -= checkExpToNextLevel();

		level++;

		character.getAttributes().addAttributePoints(attributesForLevelUp);
		character.getSkills().addSkillPoint();

		character.refreshCharacter();

		TextAreaController.addTextToTextArea("Level up! Your new level is " + level + "! Experience to next level: "
				+ (checkExpToNextLevel() - experience) + ".");
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public long getExperience() {
		return experience;
	}

	public int getLevel() {
		return level;
	}

}
