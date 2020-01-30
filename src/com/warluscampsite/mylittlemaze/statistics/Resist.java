package com.warluscampsite.mylittlemaze.statistics;

import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;

public class Resist {

	final double maxValue = 0.75;
	double maxValueBuffs = 0;
	double maxValuePassives = 0;
	double maxValueSum = 0;

	double sumValue = 0;
	double baseValue = 0;
	double boostsFromItem = 0;
	double boostsFromPassives = 0;
	double boostsFromBuff = 0;

	public void countSum() {
		sumValue = baseValue + boostsFromItem + boostsFromPassives + boostsFromBuff;
		maxValueSum = maxValue + maxValueBuffs + maxValuePassives;
	}

	public double getReduction() {
		double reduction = sumValue;

		if (reduction >= maxValueSum + maxValueBuffs)
			reduction = maxValueSum + maxValueBuffs;

		return reduction;
	}

	public void refresh() {
		boostsFromPassives = 0;
		boostsFromItem = 0;

		maxValueSum = 0;
		maxValuePassives = 0;
	}

	@Override
	public String toString() {
		if (sumValue >= maxValueSum + maxValueBuffs)
			return MyStringFormatter.formatDoubleAsPercentage(maxValueSum + maxValueBuffs, 0) + " ("
					+ MyStringFormatter.formatDoubleAsPercentage(sumValue, 0) + ")";

		return MyStringFormatter.formatDoubleAsPercentage(sumValue, 0);
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public double getMaxValue() {
		return maxValue;
	}

	public double getSumValue() {
		return sumValue;
	}

	public void setSumValue(double sumValue) {
		this.sumValue = sumValue;
	}

	public double getUnchangeableValue() {
		return baseValue;
	}

	public void setUnchangeableValue(double unchangeableValue) {
		this.baseValue = unchangeableValue;
	}

	public double getItemValue() {
		return boostsFromPassives;
	}

	public void setItemValue(double itemValue) {
		this.boostsFromPassives = itemValue;
	}

	public double getMaxValueBuffs() {
		return maxValueBuffs;
	}

	public void setMaxValueBuffs(double maxValueBuffs) {
		this.maxValueBuffs = maxValueBuffs;
		countSum();
	}

	public void addMaxValueBuffs(double add) {
		maxValueBuffs += add;
	}

	public void addBoostsFromItem(double boost) {
		boostsFromItem += boost;
	}

	public void addBoostsFromPassives(double boost) {
		boostsFromPassives += boost;
	}

	public void addBoostsFromBuffs(double boost) {
		boostsFromBuff += boost;
		countSum();
	}
}
