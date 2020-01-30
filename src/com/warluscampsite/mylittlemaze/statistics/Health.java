package com.warluscampsite.mylittlemaze.statistics;

import com.warluscampsite.mylittlemaze.battle.Death;

public class Health {

	Characterr character;

	final int BASIC_VALUE_HEALTH = 10;
	final int BASIC_VITALITY_MODIFIER_HEALTH = 5;
	final int BASIC_STRENGTH_MODIFIER_HEALTH = 1;
	int maxValue;
	int boostFromItems, boostFromPassives, boostFromBuffs;
	double currentValue;

	final double BASIC_VALUE_HEALTH_REGEN = 0.1;
	final double BASIC_VITALITY_MODIFIER_HEALTH_REGEN = 0.01;
	double healthRegenPerSecond;
	double boostRegenFromItems, boostRegenFromPassives, boostRegenFromBuffs;

	public Health(Characterr character) {
		this.character = character;

		refresh();
		regenAllHp();
	}

	public void calculateMaxHealth() {
		maxValue = (int) (BASIC_VALUE_HEALTH
				+ character.getAttributes().getAttribute(AttributesEnum.STRENGTH).getSum()
						* BASIC_STRENGTH_MODIFIER_HEALTH
				+ character.getAttributes().getAttribute(AttributesEnum.VITALITY).getSum()
						* BASIC_VITALITY_MODIFIER_HEALTH
				+ boostFromItems + boostFromPassives + boostFromBuffs);
		;
	}

	public void calculateHealthRegen() {
		healthRegenPerSecond = BASIC_VALUE_HEALTH_REGEN
				+ character.getAttributes().getAttribute(AttributesEnum.VITALITY).getSum()
						* BASIC_VITALITY_MODIFIER_HEALTH_REGEN
				+ boostRegenFromItems + boostRegenFromPassives + boostRegenFromBuffs;
	}

	public void regenAllHp() {
		currentValue = maxValue;
	}

	public void changeCurrentValue(double change) {
		currentValue += change;
		if (currentValue < 0) {
			currentValue = 0;
			Death.addNewCharacterToDeathList(character);
		} else if (currentValue > maxValue)
			currentValue = maxValue;
	}

	public void regenHp() {
		if (!character.isDead)
			changeCurrentValue(healthRegenPerSecond);
	}

	public void resetBoostsBeforeRefresh() {
		boostFromItems = 0;
		boostFromPassives = 0;
		boostFromBuffs = 0;
		boostRegenFromItems = 0;
		boostRegenFromPassives = 0;
		boostRegenFromBuffs = 0;
	}

	public void refresh() {
		calculateMaxHealth();
		calculateHealthRegen();
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public int getMaxValue() {
		return maxValue;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	public double getHealthRegenPerSecond() {
		return healthRegenPerSecond;
	}

	public double getBoostHpFromItems() {
		return boostFromItems;
	}

	public double getBoostHpFromPassives() {
		return boostFromPassives;
	}

	public double getBoostHpFromBuffs() {
		return boostFromBuffs;
	}

	public double getBoostRegenFromItems() {
		return boostRegenFromItems;
	}

	public double getBoostRegenFromPassives() {
		return boostRegenFromPassives;
	}

	public double getBoostRegenFromBuffs() {
		return boostRegenFromBuffs;
	}

	public void addBoostFromItem(double boost) {
		boostFromItems += boost;
	}

	public void addBoostFromItemRegen(double boost) {
		boostRegenFromItems += boost;
	}
	
	public void addBoostFromPassivesRegen(double boost) {
		boostRegenFromPassives += boost;
	}
}
