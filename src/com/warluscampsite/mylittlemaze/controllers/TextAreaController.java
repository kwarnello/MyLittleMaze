package com.warluscampsite.mylittlemaze.controllers;

import javax.swing.JTextArea;

import com.warluscampsite.mylittlemaze.skills.Skill;

public class TextAreaController {

	static JTextArea jTextArea;

	static public void setTextAreaController(JTextArea jTextPane) {
		TextAreaController.jTextArea = jTextPane;
	}

	static public void addTextToTextArea(String string) {
		String time = "";

		if (Options.isShowTime())
			time = Long.toString(System.currentTimeMillis());

		jTextArea.append(time + " " + string + "\n");
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 * 
	 */

	static public void addInformationAboutAttack(String attackerName, String defender, Skill skill, double damageOutput,
			boolean critical) {
		if (Options.isShowInformationAboutDamage()) {
			StringBuilder message = new StringBuilder();

			message.append(attackerName + " hit " + defender + " using a " + skill.getName() + " and dealt "
					+ MyStringFormatter.formatDouble(damageOutput, 0)+". ");
			
			if(critical)
				message.append("CRITICAL STRIKE!");

			addTextToTextArea(message.toString());
		}
	}

	static public void addInformationIfMissed(String attackerName, String defender, Skill skill) {
		if (Options.isShowInformationAboutDamage()) {

			StringBuilder message = new StringBuilder();

			message.append(attackerName + " missed " + defender + " using a " + skill.getName() + "!");

			addTextToTextArea(message.toString());
		}
	}

	public static void addInformationIfDodge(String attackerName, String defender, Skill skill) {
		if (Options.isShowInformationAboutDamage()) {
			StringBuilder message = new StringBuilder();

			message.append(defender + " dodge from " + skill.getName() + " " + attackerName + "!");

			addTextToTextArea(message.toString());
		}
	}

}
