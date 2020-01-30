package com.warluscampsite.mylittlemaze.playerteam.classes;

import java.util.LinkedList;

import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;

public class Paladin extends ClassBase {

	public Paladin() {
		super();
		name = "Paladin";

		strengthBase = 2;
		dexterityBase = 0;
		intelligenceBase = 2;
		speedBase = 0;
		vitalityBase = 4;
		wisdomBase = 2;
		luckBase = 0;
	}

	@Override
	public void initializePassives() {
	}

	@Override
	public LinkedList<Skill> getSkillsFromProffesion(Player player) {
		LinkedList<Skill> skillsFromProffesion = new LinkedList<>();

		return skillsFromProffesion;
	}
	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
