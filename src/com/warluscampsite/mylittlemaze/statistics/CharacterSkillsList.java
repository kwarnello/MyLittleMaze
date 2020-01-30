package com.warluscampsite.mylittlemaze.statistics;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.warluscampsite.mylittlemaze.skills.Skill;

public class CharacterSkillsList {

	Characterr character;

	int skillPoints;

	Map<String, Skill> mapOfAttacks;
	Map<Integer, Skill> mapOfSkillsFromProfession;

	public CharacterSkillsList(Characterr character) {
		this.character = character;

		skillPoints = 0;

		mapOfAttacks = new HashMap<>();
		mapOfSkillsFromProfession = new LinkedHashMap<>();
	}

	public void resetBoostsBeforeRefresh() {
		mapOfAttacks.forEach((k, v) -> v.removeBuffsDuringRefresh());
	}

	public void refresh() {
		mapOfAttacks.forEach((k, v) -> v.refresh());
	}

	public void addTimeFlow(int milis) {
		getMapOfAttacks().forEach((k, v) -> v.addTimeFlow(milis));
	}

	public void addAttack(Skill skill) {
		if (!mapOfAttacks.containsKey(skill.getName()))
			mapOfAttacks.put(skill.getName(), skill);
	}

	public void addAttack(Skill skill, boolean fromProffesion, Integer i) {
		addAttack(skill);

		mapOfSkillsFromProfession.put(i, skill);
	}

	public void removeAttack(Skill skill) {
		mapOfAttacks.remove(skill.getName());
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public Map<String, Skill> getMapOfAttacks() {
		return mapOfAttacks;
	}

	public Map<Integer, Skill> getMapOfSkillsFromProfession() {
		return mapOfSkillsFromProfession;
	}

	public int getSkillPoints() {
		return skillPoints;
	}

	public void setSkillPoints(int skillPoints) {
		this.skillPoints = skillPoints;
	}

	public void addSkillPoint() {
		skillPoints++;
	}

}
