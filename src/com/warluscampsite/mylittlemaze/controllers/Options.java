package com.warluscampsite.mylittlemaze.controllers;

public class Options {

	// TextArea options
	static boolean showInformationAboutDamage, showInformationAboutDeaths, showInformationAboutLoot,
			showInformationAboutGold, showInformationAboutLeveling, showInformationAboutExperience;
	static boolean showTime;

	// Damage numbers options
	static boolean showNumbers, showOnlyCritical, showHealing;

	public Options() {
		// TextArea options
		showInformationAboutDamage = false;
		showInformationAboutDeaths = true;
		showInformationAboutLoot = true;
		showInformationAboutGold = true;
		showInformationAboutLeveling = true;
		showInformationAboutExperience = true;
		showTime = false;

		// Damage numbers options
		showNumbers = true;
		showOnlyCritical = false;
		showHealing = true;
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public static boolean isShowInformationAboutDamage() {
		return showInformationAboutDamage;
	}

	public static void setShowInformationAboutDamage(boolean showInformationAboutDamage) {
		Options.showInformationAboutDamage = showInformationAboutDamage;
	}

	public static boolean isShowInformationAboutDeaths() {
		return showInformationAboutDeaths;
	}

	public static void setShowInformationAboutDeaths(boolean showInformationAboutDeaths) {
		Options.showInformationAboutDeaths = showInformationAboutDeaths;
	}

	public static boolean isShowInformationAboutLoot() {
		return showInformationAboutLoot;
	}

	public static void setShowInformationAboutLoot(boolean showInformationAboutLoot) {
		Options.showInformationAboutLoot = showInformationAboutLoot;
	}

	public static boolean isShowTime() {
		return showTime;
	}

	public static void setShowTime(boolean showTime) {
		Options.showTime = showTime;
	}

	public static boolean isShowInformationAboutGold() {
		return showInformationAboutGold;
	}

	public static void setShowInformationAboutGold(boolean showInformationAboutGold) {
		Options.showInformationAboutGold = showInformationAboutGold;
	}

	public static boolean isShowInformationAboutLeveling() {
		return showInformationAboutLeveling;
	}

	public static void setShowInformationAboutLeveling(boolean showInformationAboutLeveling) {
		Options.showInformationAboutLeveling = showInformationAboutLeveling;
	}

	public static boolean isShowInformationAboutExperience() {
		return showInformationAboutExperience;
	}

	public static void setShowInformationAboutExperience(boolean showInformationAboutExperience) {
		Options.showInformationAboutExperience = showInformationAboutExperience;
	}

	public static boolean isShowNumbers() {
		return showNumbers;
	}

	public static void setShowNumbers(boolean showNumbers) {
		Options.showNumbers = showNumbers;
	}

	public static boolean isShowOnlyCritical() {
		return showOnlyCritical;
	}

	public static void setShowOnlyCritical(boolean showOnlyCritical) {
		Options.showOnlyCritical = showOnlyCritical;
	}

	public static boolean isShowHealing() {
		return showHealing;
	}

	public static void setShowHealing(boolean showHealing) {
		Options.showHealing = showHealing;
	}

}
