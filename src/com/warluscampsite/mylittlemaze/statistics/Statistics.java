package com.warluscampsite.mylittlemaze.statistics;

import java.util.HashMap;
import java.util.Map;

public class Statistics {

	Characterr character;

	Map<String, StatBase> stats;

	public Statistics(Characterr character) {
		this.character = character;

		stats = new HashMap<>();

		createAllStatsInHashMap();

		refresh();
	}

	private void createAllStatsInHashMap() {
		double armorMultiplierFromStrength = 0;
		double armorMultiplierFromVitality = 1;

		double attackMultiplierFromStrength = 2;
		double attackMultiplierFromDexterity = 5;
		double attackMultiplierFromSpeed = 1;
		double attackMultiplierFromLuck = 2;

		double defenceMultiplierFromStrength = 1;
		double defenceMultiplierFromDexterity = 5;
		double defenceMultiplierFromSpeed = 2;
		double defenceMultiplierFromLuck = 1;

		double dodgeMultiplierFromSpeed = 0.005;
		double dodgeMultiplierFromLuck = 0.001;

		double critChanceMultiplierFromLuck = 0.01;

		final double BASE_CRIT_MULTIPLIER = 1.2;
		double critMultiplierFromLuck = 0.005;

		stats.put("Armor",
				new StatBase(character, armorMultiplierFromStrength, 0, 0, 0, armorMultiplierFromVitality, 0, 0));
		stats.put("Attack", new StatBase(character, attackMultiplierFromStrength, attackMultiplierFromDexterity, 0,
				attackMultiplierFromSpeed, 0, 0, attackMultiplierFromLuck));
		stats.put("Defence", new StatBase(character, defenceMultiplierFromStrength, defenceMultiplierFromDexterity, 0,
				defenceMultiplierFromSpeed, 0, 0, defenceMultiplierFromLuck));
		stats.put("Dodge", new StatBase(character, 0, 0, 0, dodgeMultiplierFromSpeed, 0, 0, dodgeMultiplierFromLuck));
		stats.put("CritChance", new StatBase(character, 0, 0, 0, 0, 0, 0, critChanceMultiplierFromLuck));
		stats.put("CritMulti", new StatBase(character, BASE_CRIT_MULTIPLIER, 0, 0, 0, 0, 0, 0, critMultiplierFromLuck));

	}

	public void refresh() {
		int strength = character.getAttributes().getAttribute(AttributesEnum.STRENGTH).getSum();
		int dexterity = character.getAttributes().getAttribute(AttributesEnum.DEXTERITY).getSum();
		int intelligence = character.getAttributes().getAttribute(AttributesEnum.INTELLIGENCE).getSum();
		int speed = character.getAttributes().getAttribute(AttributesEnum.SPEED).getSum();
		int vitality = character.getAttributes().getAttribute(AttributesEnum.VITALITY).getSum();
		int wisdom = character.getAttributes().getAttribute(AttributesEnum.WISDOM).getSum();
		int luck = character.getAttributes().getAttribute(AttributesEnum.LUCK).getSum();

		stats.forEach((k, v) -> v.countTotal(strength, dexterity, intelligence, speed, vitality, wisdom, luck));
	}

	public void refresh(String status) {
		int strength = character.getAttributes().getAttribute(AttributesEnum.STRENGTH).getSum();
		int dexterity = character.getAttributes().getAttribute(AttributesEnum.DEXTERITY).getSum();
		int intelligence = character.getAttributes().getAttribute(AttributesEnum.INTELLIGENCE).getSum();
		int speed = character.getAttributes().getAttribute(AttributesEnum.SPEED).getSum();
		int vitality = character.getAttributes().getAttribute(AttributesEnum.VITALITY).getSum();
		int wisdom = character.getAttributes().getAttribute(AttributesEnum.WISDOM).getSum();
		int luck = character.getAttributes().getAttribute(AttributesEnum.LUCK).getSum();

		stats.get(status).countTotal(strength, dexterity, intelligence, speed, vitality, wisdom, luck);
	}

	public void resetBoostsBeforeRefresh() {
		stats.forEach((k, v) -> v.resetBoostsBeforeRefresh());
	}

	public void setMultiplier(String name, double multiplier) {
		stats.get(name).setMultiplierFromStatus(multiplier);
		refresh(name);
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public StatBase getArmor() {
		return stats.get("Armor");
	}

	public StatBase getAttack() {
		return stats.get("Attack");
	}

	public StatBase getDefence() {
		return stats.get("Defence");
	}

	public StatBase getDodge() {
		return stats.get("Dodge");
	}

	public StatBase getCritChance() {
		return stats.get("CritChance");
	}

	public StatBase getCritMultiplier() {
		return stats.get("CritMulti");
	}

}
