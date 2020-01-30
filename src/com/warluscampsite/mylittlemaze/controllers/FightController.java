package com.warluscampsite.mylittlemaze.controllers;

import java.util.Random;

import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class FightController {
	final static Random random = new Random();

	final static double MIN_HIT_CHANCE = 0.10;
	final static double MAX_HIT_CHANCE = 0.95;
	final static double UP_PART_OF_CALCULATIONS = MAX_HIT_CHANCE - MIN_HIT_CHANCE;

	static public double calculateHitChance(Characterr attacker, Characterr defender) {
		double attack = attacker.getStatistics().getAttack().getTotal();
		double defence = defender.getStatistics().getDefence().getTotal();
		double sqrtFromAttack = Math.pow(attack, 0.65);

		double hitChance = MAX_HIT_CHANCE
				- (UP_PART_OF_CALCULATIONS / (Math.exp((attack - defence) / sqrtFromAttack) + 1));

		return hitChance;
	}

	static public boolean trueOrFalse(double probability) {
		if (random.nextDouble() <= probability)
			return true;
		else
			return false;
	}

	final static double MAX_REDUCTION = 0.80;
	final static double REDUCTION_MULTIPLIER = 0.004;

	static public double countArmorReduction(double d) {
		double reduction = MAX_REDUCTION * (1 - Math.exp(-d * REDUCTION_MULTIPLIER));
		return reduction;
	}

	static final double MINIMUM_ARMOR_REDUCTION = 0.05;
	static final double MAXIMUM_ARMOR_REDUCTION = 0.15;
	static final double DIFFERENCE_ARMOR_REDUCTION = MAXIMUM_ARMOR_REDUCTION - MINIMUM_ARMOR_REDUCTION;

	static public double getArmorReduction(Characterr character) {
		double armor = character.getStatistics().getArmor().getTotal();
		return (DIFFERENCE_ARMOR_REDUCTION * armor) * random.nextDouble() + armor * MINIMUM_ARMOR_REDUCTION;
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
}
