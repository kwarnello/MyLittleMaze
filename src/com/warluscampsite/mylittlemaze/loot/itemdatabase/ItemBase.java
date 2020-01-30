package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public abstract class ItemBase {

	ItemTypes itemType;

	// weapons
	int numberOfAttacks;
	int attackDelay;
	int minDamage, maxDamage;
	double critChance, critMultiplier;
	double attack, defence;

	// armors
	double armor, energyShield;
	double dodge;

	// resists
	double fireRes, iceRes, earthRes, lightningRes, poisonRes, darknessRes, radianceRes;

	String name;
	String path;
	StringBuilder additionalString;

	public ItemBase(ItemTypes itemType) {
		super();
		this.additionalString = new StringBuilder();
		this.itemType = itemType;

		this.numberOfAttacks = 0;
		this.attackDelay = 0;
		this.minDamage = 0;
		this.maxDamage = 0;
		this.critChance = 0;
		this.critMultiplier = 0;

		this.attack = 0;
		this.defence = 0;

		this.armor = 0;
		this.dodge = 0;
		this.energyShield = 0;

		this.fireRes = 0;
		this.iceRes = 0;
		this.earthRes = 0;
		this.lightningRes = 0;
		this.poisonRes = 0;
		this.darknessRes = 0;
		this.radianceRes = 0;
	}

	public void doThingsWithRarity(ItemRarity itemRarity) {
		numberOfAttacks = (int) Math.round(numberOfAttacks * itemRarity.getMultiplier());
		minDamage = (int) Math.round(minDamage * itemRarity.getMultiplier());
		maxDamage = (int) Math.round(maxDamage * itemRarity.getMultiplier());
		critChance = critChance * itemRarity.getMultiplier();
		critMultiplier = critMultiplier * itemRarity.getMultiplier();
		attack = attack * itemRarity.getMultiplier();
		defence = defence * itemRarity.getMultiplier();

		armor = armor * itemRarity.getMultiplier();
		dodge = dodge * itemRarity.getMultiplier();
		energyShield = energyShield * itemRarity.getMultiplier();

		fireRes = fireRes * itemRarity.getMultiplier();
		iceRes = iceRes * itemRarity.getMultiplier();
		earthRes = earthRes * itemRarity.getMultiplier();
		lightningRes = lightningRes * itemRarity.getMultiplier();
		poisonRes = poisonRes * itemRarity.getMultiplier();
		darknessRes = darknessRes * itemRarity.getMultiplier();
		radianceRes = radianceRes * itemRarity.getMultiplier();
	};

	public abstract void doAttack(Player player, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap);

	public abstract void doStats(Player player);

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public ItemTypes getItemType() {
		return itemType;
	}

	public String getName() {
		return name;
	}

	public String getAdditionalString() {
		return additionalString.toString();
	}

	public int getNumberOfAttacks() {
		return numberOfAttacks;
	}

	public int getMinDamage() {
		return minDamage;
	}

	public int getMaxDamage() {
		return maxDamage;
	}

	public double getCritChance() {
		return critChance;
	}

	public double getCritMultiplier() {
		return critMultiplier;
	}

	public double getAttack() {
		return attack;
	}

	public double getDefence() {
		return defence;
	}

	public double getArmor() {
		return armor;
	}

	public double getEnergyShield() {
		return energyShield;
	}

	public double getDodge() {
		return dodge;
	}

	public String getPath() {
		return path;
	}

	public void setNumberOfAttacks(int numberOfAttacks) {
		this.numberOfAttacks = numberOfAttacks;
	}

	public void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}

	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	public void setCritChance(double critChance) {
		this.critChance = critChance;
	}

	public void setCritMultiplier(double critMultiplier) {
		this.critMultiplier = critMultiplier;
	}

	public void setAttack(double attack) {
		this.attack = attack;
	}

	public void setDefence(double defence) {
		this.defence = defence;
	}

	public void setArmor(double armor2) {
		this.armor = armor2;
	}

	public void setEnergyShield(double energyShield) {
		this.energyShield = energyShield;
	}

	public void setDodge(double dodge) {
		this.dodge = dodge;
	}

	public int getAttackDelay() {
		return attackDelay;
	}

	public void setAttackDelay(int attackDelay) {
		this.attackDelay = attackDelay;
	}

	public double getFireRes() {
		return fireRes;
	}

	public double getIceRes() {
		return iceRes;
	}

	public double getEarthRes() {
		return earthRes;
	}

	public double getLightningRes() {
		return lightningRes;
	}

	public double getPoisonRes() {
		return poisonRes;
	}

	public double getDarknessRes() {
		return darknessRes;
	}

	public double getRadianceRes() {
		return radianceRes;
	}

	public void setFireRes(double fireRes) {
		this.fireRes = fireRes;
	}

	public void setIceRes(double iceRes) {
		this.iceRes = iceRes;
	}

	public void setEarthRes(double earthRes) {
		this.earthRes = earthRes;
	}

	public void setLightningRes(double lightningRes) {
		this.lightningRes = lightningRes;
	}

	public void setPoisonRes(double poisonRes) {
		this.poisonRes = poisonRes;
	}

	public void setDarknessRes(double darknessRes) {
		this.darknessRes = darknessRes;
	}

	public void setRadianceRes(double radianceRes) {
		this.radianceRes = radianceRes;
	}

}
