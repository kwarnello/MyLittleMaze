package com.warluscampsite.mylittlemaze.statistics;

import java.util.HashMap;
import java.util.Map;

import com.warluscampsite.mylittlemaze.controllers.TextAreaController;
import com.warluscampsite.mylittlemaze.monsters.Monster;
import com.warluscampsite.mylittlemaze.playerteam.Player;

public class Attributes {

	final int STARTING_VALUE_OF_STATS = 10;

	Characterr character;

	Map<AttributesEnum, Attribute> attributesMap;
	int attributePoints;

	public Attributes(Monster monster) {
		super();

		this.character = monster;

		attributesMap = new HashMap<AttributesEnum, Attribute>();
		for (AttributesEnum attribute : AttributesEnum.values()) {
			attributesMap.put(attribute, new Attribute(monster.getBasicStatsOfProffesion(attribute)));
		}

		refresh();
	}

	public Attributes(Player player) {
		super();

		this.character = player;

		attributePoints = 0;
		attributesMap = new HashMap<AttributesEnum, Attribute>();
		for (AttributesEnum attribute : AttributesEnum.values()) {
			attributesMap.put(attribute, new Attribute(STARTING_VALUE_OF_STATS
					+ ((Player) character).getProffesion().getBasicStatsOfProffesion(attribute)));
		}

		refresh();
	}

	public Attributes(int strength, int dexterity, int intelligence, int speed, int vitality, int wisdom, int luck) {
		attributesMap = new HashMap<AttributesEnum, Attribute>();

		attributesMap.put(AttributesEnum.STRENGTH, new Attribute(strength));
		attributesMap.put(AttributesEnum.DEXTERITY, new Attribute(dexterity));
		attributesMap.put(AttributesEnum.INTELLIGENCE, new Attribute(intelligence));
		attributesMap.put(AttributesEnum.SPEED, new Attribute(speed));
		attributesMap.put(AttributesEnum.VITALITY, new Attribute(vitality));
		attributesMap.put(AttributesEnum.WISDOM, new Attribute(wisdom));
		attributesMap.put(AttributesEnum.LUCK, new Attribute(luck));

		refresh();
	}

	public void refresh() {
		attributesMap.forEach((k, v) -> v.countSum());
	}

	public void resetBoostsBeforeRefresh() {
		attributesMap.forEach((k, v) -> v.resetBoostsBeforeRefresh());
	}

	void addAttributePoints(int pointsToAdd) {
		attributePoints += pointsToAdd;
	}

	final int A_MODIFIER_FOR_ATTRIBUTE_LEVELING = 1;
	final int B_MODIFIER_FOR_ATTRIBUTE_LEVELING = 1;
	final double C_MODIFIER_FOR_ATTRIBUTE_LEVELING = 0.1;

	public int checkPointNeededForLevelAttribute(AttributesEnum attribute) {
		int attLevel = attributesMap.get(attribute).getAddFromLeveling();
		return (int) Math.round(A_MODIFIER_FOR_ATTRIBUTE_LEVELING + B_MODIFIER_FOR_ATTRIBUTE_LEVELING * attLevel
				+ C_MODIFIER_FOR_ATTRIBUTE_LEVELING * attLevel * attLevel);
	}

	public boolean checkIfCanLevelAttribute(AttributesEnum attribute) {
		if (attributePoints >= checkPointNeededForLevelAttribute(attribute))
			return true;
		return false;
	}

	public void levelUpAttribute(AttributesEnum attribute) {
		if (checkIfCanLevelAttribute(attribute)) {
			addAttributePoints(-checkPointNeededForLevelAttribute(attribute));
			attributesMap.get(attribute).addFromLeveling();
		}

		else
			TextAreaController.addTextToTextArea("Not enought attribute points to level up " + attribute
					+ ". You need at least " + checkPointNeededForLevelAttribute(attribute) + " points.");
	}

	/*
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public Attribute getAttribute(AttributesEnum attribute) {
		return attributesMap.get(attribute);
	}

	public int getAttributePoints() {
		return attributePoints;
	}

}
