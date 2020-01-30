package com.warluscampsite.mylittlemaze.statistics;

public class Mana {
	Characterr character;

	final int BASIC_VALUE_MANA = 0;
	final int BASIC_WISDOM_MODIFIER_MANA = 5;
	final int BASIC_INTELLIGENCE_MODIFIER_MANA = 1;
	int maxValue;
	int boostFromItems, boostFromPassives, boostFromBuffs;
	double currentValue;

	final double BASIC_VALUE_MANA_REGEN = 0.01;
	final double BASIC_VITALITY_MODIFIER_MANA_REGEN = 0.005;
	double manaRegenPerSecond;
	double boostRegenFromItems, boostRegenFromPassives, boostRegenFromBuffs;

	public Mana(Characterr character) {
		this.character = character;

		calculateMaxMana();
		calculateManaRegen();
		regenAllMp();
	}

	public void calculateMaxMana() {
		maxValue = (int) (BASIC_VALUE_MANA
				+ character.getAttributes().getAttribute(AttributesEnum.INTELLIGENCE).getSum()
						* BASIC_INTELLIGENCE_MODIFIER_MANA
				+ character.getAttributes().getAttribute(AttributesEnum.WISDOM).getSum() * BASIC_WISDOM_MODIFIER_MANA)
				+ boostFromItems + boostFromPassives + boostFromBuffs;
	}

	public void calculateManaRegen() {
		manaRegenPerSecond = BASIC_VALUE_MANA_REGEN
				+ character.getAttributes().getAttribute(AttributesEnum.WISDOM).getSum()
						* BASIC_VITALITY_MODIFIER_MANA_REGEN
				+ boostRegenFromItems + boostRegenFromPassives + boostRegenFromBuffs;
	}

	public void regenAllMp() {
		currentValue = maxValue;
	}

	public void changeCurrentValue(double change) {
		currentValue += change;
		if (currentValue < 0) {
			currentValue = 0;
		} else if (currentValue > maxValue)
			currentValue = maxValue;
	}

	public void regenMp() {
		if (!character.isDead)
			changeCurrentValue(manaRegenPerSecond);
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
		calculateMaxMana();
		calculateManaRegen();
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

	public double getManaRegenPerSecond() {
		return manaRegenPerSecond;
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
