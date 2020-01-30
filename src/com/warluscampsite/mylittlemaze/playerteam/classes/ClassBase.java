package com.warluscampsite.mylittlemaze.playerteam.classes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public abstract class ClassBase {

	String name;
	String firstSubclassName, secondSubclassName;
	int strengthBase, dexterityBase, intelligenceBase, speedBase, vitalityBase, wisdomBase, luckBase;

	Map<Integer, Passive> passivesMap;

	public ClassBase() {
		passivesMap = new HashMap<>();
	}

	public void doPreRefreshStuff(Player player) {

	}

	public void doPostRefreshStuff(Player player) {

	}

	public void doDuringAttackStuff(Characterr attacker, Characterr defender, Skill skill,
			Map<DamageTypes, Double> damageMap, boolean criticalStrike) {

	}

	public abstract void initializePassives();

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public int getBasicStatsOfProffesion(AttributesEnum attribute) {
		switch (attribute) {
		case STRENGTH:
			return strengthBase;
		case DEXTERITY:
			return dexterityBase;
		case INTELLIGENCE:
			return intelligenceBase;
		case SPEED:
			return speedBase;
		case VITALITY:
			return vitalityBase;
		case WISDOM:
			return wisdomBase;
		case LUCK:
			return luckBase;
		default:
			return 5;
		}
	}

	public abstract LinkedList<Skill> getSkillsFromProffesion(Player player);

	public String getName() {
		return name;
	}

	public int getStrengthBase() {
		return strengthBase;
	}

	public int getDexterityBase() {
		return dexterityBase;
	}

	public int getIntelligenceBase() {
		return intelligenceBase;
	}

	public int getSpeedBase() {
		return speedBase;
	}

	public int getVitalityBase() {
		return vitalityBase;
	}

	public int getWisdomBase() {
		return wisdomBase;
	}

	public int getLuckBase() {
		return luckBase;
	}

	public Passive getPassives(Integer key) {
		return passivesMap.get(key);
	}

	public String getFirstSubclassName() {
		return firstSubclassName;
	}

	public String getSecondSubclassName() {
		return secondSubclassName;
	}

}
