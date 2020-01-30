package com.warluscampsite.mylittlemaze.statistics;

public class StatBase {
	Characterr character;

	double total;

	double base;

	double boostsFromItems, boostsFromPassives, boostsFromBuffs;
	double multiplierFromStatus = 1.0;

	double buffFromStrength;
	double buffFromDexterity;
	double buffFromIntelligence;
	double buffFromSpeed;
	double buffFromVitality;
	double buffFromWisdom;
	double buffFromLuck;

	public StatBase(Characterr character) {
		this.character = character;
		base = 0;
		buffFromStrength = 0;
		buffFromDexterity = 0;
		buffFromIntelligence = 0;
		buffFromSpeed = 0;
		buffFromVitality = 0;
		buffFromWisdom = 0;
		buffFromLuck = 0;
	}

	public StatBase(Characterr character, double strengthBuff, double dexterityBuff, double intelligenceBuff,
			double speedBuff, double vitalityBuff, double wisdomBuff, double luckBuff) {
		this.character = character;

		base = 0;
		buffFromStrength = strengthBuff;
		buffFromDexterity = dexterityBuff;
		buffFromIntelligence = intelligenceBuff;
		buffFromSpeed = speedBuff;
		buffFromVitality = vitalityBuff;
		buffFromWisdom = wisdomBuff;
		buffFromLuck = luckBuff;
	}

	public StatBase(Characterr character, double base, double strengthBuff, double dexterityBuff,
			double intelligenceBuff, double speedBuff, double vitalityBuff, double wisdomBuff, double luckBuff) {
		this(character, strengthBuff, dexterityBuff, intelligenceBuff, speedBuff, vitalityBuff, wisdomBuff, luckBuff);
		this.base = base;
	}

	public void countTotal(double strength, double dexterity, double intelligence, double speed, double vitality,
			double wisdom, double luck) {
		total = base;

		total += buffFromStrength * strength + buffFromDexterity * dexterity + buffFromIntelligence * intelligence
				+ buffFromSpeed * speed + buffFromVitality * vitality + buffFromWisdom * wisdom + buffFromLuck * luck;

		total += boostsFromItems + boostsFromPassives + boostsFromBuffs;

		total = total * multiplierFromStatus;
	}

	public void resetBoostsBeforeRefresh() {
		boostsFromItems = 0;
		boostsFromPassives = 0;
		boostsFromBuffs = 0;
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public double getTotal() {
		return total;
	}

	public void addBoostsFromItems(double boost) {
		boostsFromItems += boost;
	}

	public void addBoostsFromPassives(double boost) {
		boostsFromPassives += boost;
	}

	public void addBoostsFromBuffs(double boost) {
		boostsFromBuffs += boost;
	}

	public void setMultiplierFromStatus(double multiplierFromStatus) {
		this.multiplierFromStatus = multiplierFromStatus;
	}
}
