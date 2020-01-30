package com.warluscampsite.mylittlemaze.statistics;

public class Attribute {

	int basicValue;
	int addFromLeveling;
	int addFromItem;
	int addFromProfessions;
	int addFromBuffs;
	double multiplier;

	int sum;

	public Attribute(int basicValue) {
		super();
		this.basicValue = basicValue;
		addFromLeveling = 0;
		addFromItem = 0;
		addFromProfessions = 0;
		addFromBuffs = 0;
		multiplier = 1.0;
	}

	public Attribute(int basicValue, int addFromLeveling, int addFromItem, double multiplier) {
		super();
		this.basicValue = basicValue;
		this.addFromLeveling = addFromLeveling;
		this.addFromItem = addFromItem;
		this.multiplier = multiplier;
	}

	public void resetBoostsBeforeRefresh() {
		addFromItem = 0;
		addFromProfessions = 0;
		multiplier = 1.0;
	}

	public void addFromLeveling() {
		addFromLeveling += 1;
	}

	void countSum() {
		sum = (int) Math
				.round((basicValue + addFromLeveling + addFromItem + addFromProfessions + addFromBuffs) * multiplier);
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public int getBasicValue() {
		return basicValue;
	}

	public void setBasicValue(int basicValue) {
		this.basicValue = basicValue;
	}

	public int getAddFromLeveling() {
		return addFromLeveling;
	}

	public void setAddFromLeveling(int addFromLeveling) {
		this.addFromLeveling = addFromLeveling;
	}

	public int getAddFromItem() {
		return addFromItem;
	}

	public void addFromItem(int addFromItem) {
		this.addFromItem += addFromItem;
	}

	public double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;

		countSum();
	}

	public int getSum() {
		return sum;
	}

	public void addFromProfession(int add) {
		addFromProfessions += add;
	}

	public void addFromBuff(int addFromBuff) {
		this.addFromBuffs += addFromBuff;
	}
}
