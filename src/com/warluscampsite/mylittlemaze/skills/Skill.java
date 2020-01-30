package com.warluscampsite.mylittlemaze.skills;

import java.util.Map;
import java.util.Random;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public abstract class Skill {

	protected String name;

	protected Characterr character;

	protected int level;

	protected int numberOfAttacks;
	protected int minDamage, maxDamage;
	protected double critChanceSum, critMultiplierSum;
	protected double critChanceBuff, critMultiplierBuff;

	protected SkillRange skillRange;
	protected SkillType skillType;
	protected DamageTypes damageTypes;

	protected int manaBase, manaForLevel, currentMana;

	protected boolean addDamageFromWeapon;

	protected int cooldownBase;
	protected int cooldownActually; // in milliseconds
	protected int timeFromLastUse;

	protected int numberOfAttacksFromBuffs = 0;
	protected int baseForNumberOfAttacks;
	protected int sumNumberOfAttacks;

	public static final int LEVEL_NEEDS_FOR_FIRST_UPGRADE = 5;
	public static final int LEVEL_NEEDS_FOR_SECOND_UPGRADE = 12;
	public static final int LEVEL_NEEDS_FOR_THIRD_UPGRADE = 30;

	protected Random random = new Random();

	protected String pathSkillToImage;

	protected String skillName1L, skillName1R, skillName2L, skillName2R, skillName3L, skillName3R;
	protected boolean skillActivated1L, skillActivated1R, skillActivated2L, skillActivated2R, skillActivated3L,
			skillActivated3R;
	protected String skill1L, skill1R, skill2L, skill2R, skill3L, skill3R;

	public Skill(Characterr character) {
		this.character = character;
		name = "Default";
		timeFromLastUse = 0;

		addDamageFromWeapon = false;

		baseForNumberOfAttacks = 1;

		this.level = 0;

		manaBase = 0;
		manaForLevel = 0;

		minDamage = 1;
		maxDamage = 2;
		critChanceSum = 0;
		critMultiplierSum = 1.0;

		if (Player.class.isAssignableFrom(character.getClass()))
			initializeThingsForImagesAndUpgrades();
	}

	public void addTimeFlow(int milis) {
		if (level > 0) {
			if (character.getStatus().checkIfStatusExist("Slow"))
				milis = milis / 2;
			timeFromLastUse += milis;
		}
	}

	public boolean shouldAttack() {
		if (timeFromLastUse >= cooldownActually)
			if (character.getMana().getCurrentValue() >= currentMana) {
				character.getMana().changeCurrentValue(-currentMana);
				return true;
			} else {
				timeFromLastUse -= 0.1 * cooldownActually;
			}
		return false;
	}

	public void attack() {
		timeFromLastUse -= cooldownActually;
	}

	public abstract void doAttack(Characterr attacker, Characterr defender, Map<DamageTypes, Double> damageMap,
			boolean criticalStrike);

	public double getRandomDamage() {
		double x = random.nextDouble() * (maxDamage - minDamage) + minDamage;
		return x;
	}

	public void addLevel() {
		level++;
	}

	final double MAXIMUM_REDUCTION_CD = 0.85;
	final double REDUCTION_FOR_PRIMARY_ATTRIBUTE = 0.005;
	final double REDUCTION_FOR_SECONDARY_ATTRIBUTE = 0.0015;

	public void calculateCooldown() {
		int primary = 0;
		int secondary = 0;

		switch (skillType) {
		case BUFF:
			primary = character.getAttributes().getAttribute(AttributesEnum.WISDOM).getSum();
			secondary = character.getAttributes().getAttribute(AttributesEnum.INTELLIGENCE).getSum();
			break;

		case DEBUFF:
			primary = character.getAttributes().getAttribute(AttributesEnum.WISDOM).getSum();
			secondary = character.getAttributes().getAttribute(AttributesEnum.INTELLIGENCE).getSum();
			break;

		case HEAL:
			primary = character.getAttributes().getAttribute(AttributesEnum.WISDOM).getSum();
			secondary = character.getAttributes().getAttribute(AttributesEnum.INTELLIGENCE).getSum();
			break;

		case MAGIC:
			primary = character.getAttributes().getAttribute(AttributesEnum.INTELLIGENCE).getSum();
			break;

		case MELEE:
			primary = character.getAttributes().getAttribute(AttributesEnum.SPEED).getSum();
			break;

		case RANGED:
			primary = character.getAttributes().getAttribute(AttributesEnum.SPEED).getSum();
			secondary = character.getAttributes().getAttribute(AttributesEnum.DEXTERITY).getSum();
			break;
		}

		double reduction = 1 - MAXIMUM_REDUCTION_CD * (1 - Math
				.exp(-(primary * REDUCTION_FOR_PRIMARY_ATTRIBUTE + secondary * REDUCTION_FOR_SECONDARY_ATTRIBUTE)));

		cooldownActually = (int) ((int) cooldownBase * reduction);
	}

	public void removeBuffsDuringRefresh() {
		numberOfAttacksFromBuffs = 0;
	};

	public void refresh() {
		minDamage = 0;
		maxDamage = 0;
		critChanceBuff = 0;
		critMultiplierBuff = 0;

		numberOfAttacks = baseForNumberOfAttacks + numberOfAttacksFromBuffs;

		currentMana = manaBase + manaForLevel * level;
	};

	public void initializeThingsForImagesAndUpgrades() {
		pathSkillToImage = "res/skills/tempSkill.png";

		skillName1L = "dummyaNa";
		skillName1R = "dummyaNb";
		skillName2L = "dummybNa";
		skillName2R = "dummybNb";
		skillName3L = "dummycNa";
		skillName3R = "dummycNb";

		skillActivated1L = false;
		skillActivated1R = false;
		skillActivated2L = false;
		skillActivated2R = false;
		skillActivated3L = false;
		skillActivated3R = false;

		skill1L = "res/skills/tempSkill.png";
		skill1R = "res/skills/tempSkill.png";
		skill2L = "res/skills/tempSkill.png";
		skill2R = "res/skills/tempSkill.png";
		skill3L = "res/skills/tempSkill.png";
		skill3R = "res/skills/tempSkill.png";

	}

	public abstract String getSkillTooltip();

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public String getName() {
		return name;
	}

	public int getNumberOfAttacks() {
		return numberOfAttacks;
	}

	public void addNumberOfAttacks(int newAttacks) {
		numberOfAttacks += newAttacks;
	}

	public SkillRange getSkillRange() {
		return skillRange;
	}

	public SkillType getSkillType() {
		return skillType;
	}

	public DamageTypes getDamageTypes() {
		return damageTypes;
	}

	public int getCooldown() {
		return cooldownBase;
	}

	public int getTimeFromLastUse() {
		return timeFromLastUse;
	}

	public Characterr getCharacter() {
		return character;
	}

	public int getMinDamage() {
		return minDamage;
	}

	public int getMaxDamage() {
		return maxDamage;
	}

	public int getCooldownActually() {
		return cooldownActually;
	}

	public double getCritChance() {
		return critChanceSum + critChanceBuff;
	}

	public double getCritMultiplier() {
		return critMultiplierSum + critMultiplierBuff;
	}

	public int getCooldownBase() {
		return cooldownBase;
	}

	public String getPathSkillToImage() {
		return pathSkillToImage;
	}

	public String getSkillName1L() {
		return skillName1L;
	}

	public String getSkillName1R() {
		return skillName1R;
	}

	public String getSkillName2L() {
		return skillName2L;
	}

	public String getSkillName2R() {
		return skillName2R;
	}

	public String getSkillName3L() {
		return skillName3L;
	}

	public String getSkillName3R() {
		return skillName3R;
	}

	public String getSkill1L() {
		return skill1L;
	}

	public String getSkill1R() {
		return skill1R;
	}

	public String getSkill2L() {
		return skill2L;
	}

	public String getSkill2R() {
		return skill2R;
	}

	public String getSkill3L() {
		return skill3L;
	}

	public String getSkill3R() {
		return skill3R;
	}

	public boolean isSkillActivated1L() {
		return skillActivated1L;
	}

	public void setSkillActivated1L(boolean skillActivated1L) {
		this.skillActivated1L = skillActivated1L;
	}

	public boolean isSkillActivated1R() {
		return skillActivated1R;
	}

	public void setSkillActivated1R(boolean skillActivated1R) {
		this.skillActivated1R = skillActivated1R;
	}

	public boolean isSkillActivated2L() {
		return skillActivated2L;
	}

	public void setSkillActivated2L(boolean skillActivated2L) {
		this.skillActivated2L = skillActivated2L;
	}

	public boolean isSkillActivated2R() {
		return skillActivated2R;
	}

	public void setSkillActivated2R(boolean skillActivated2R) {
		this.skillActivated2R = skillActivated2R;
	}

	public boolean isSkillActivated3L() {
		return skillActivated3L;
	}

	public void setSkillActivated3L(boolean skillActivated3L) {
		this.skillActivated3L = skillActivated3L;
	}

	public boolean isSkillActivated3R() {
		return skillActivated3R;
	}

	public void setSkillActivated3R(boolean skillActivated3R) {
		this.skillActivated3R = skillActivated3R;
	}

	public int getLEVEL_NEEDS_FOR_FIRST_UPGRADE() {
		return LEVEL_NEEDS_FOR_FIRST_UPGRADE;
	}

	public int getLEVEL_NEEDS_FOR_SECOND_UPGRADE() {
		return LEVEL_NEEDS_FOR_SECOND_UPGRADE;
	}

	public int getLEVEL_NEEDS_FOR_THIRD_UPGRADE() {
		return LEVEL_NEEDS_FOR_THIRD_UPGRADE;
	}

	public int getLevel() {
		return level;
	}

	public void addCritMultiplier(double critMultiplier) {
		this.critMultiplierSum += critMultiplier;
	}

	public int getNumberOfAttacksFromBuffs() {
		return numberOfAttacksFromBuffs;
	}

	public void addNumberOfAttacksFromBuffs(int newAttack) {
		numberOfAttacksFromBuffs += newAttack;
	}

	public int getSumNumberOfAttacks() {
		return sumNumberOfAttacks;
	}

	public void multiplyCooldown(double multiplier) {
		cooldownActually = (int) (cooldownActually * (1 - multiplier));
	}

	public void setSkillRange(SkillRange skillRange) {
		this.skillRange = skillRange;
	}

	public void setSkillType(SkillType skillType) {
		this.skillType = skillType;
	}

	public int getCurrentMana() {
		return currentMana;
	}

	public void addCriticalChanceBuff(double buff) {
		critChanceBuff += buff;
	}

	public void addCriticalMultiplierBuff(double buff) {
		critMultiplierBuff += buff;
	}
}
