package com.warluscampsite.mylittlemaze.skills.playerskills;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.controllers.MyTooltipFormatter;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.WeaponBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.skills.SkillRange;
import com.warluscampsite.mylittlemaze.skills.SkillType;
import com.warluscampsite.mylittlemaze.statistics.Characterr;
import com.warluscampsite.mylittlemaze.statistics.EquipmentSlots;
import com.warluscampsite.mylittlemaze.status.Blind;
import com.warluscampsite.mylittlemaze.status.Poison;
import com.warluscampsite.mylittlemaze.status.Slow;
import com.warluscampsite.mylittlemaze.status.Vulnerability;
import com.warluscampsite.mylittlemaze.status.Weakness;

public class ArrowInTheKnee extends Skill {

	int minDamageForLevel = 4;
	int maxDamageForLevel = 8;

	/// boosts from level to damage and attack speed
	final double attackSpeedBoostFromLevel = 1;
	double sumAttackSpeedBoostFromLevel = 1;

	public ArrowInTheKnee(Characterr character) {
		super(character);

		name = "Arrow in the knee";

		baseForNumberOfAttacks = 1;

		skillRange = SkillRange.SINGLE_ANY;
		skillType = SkillType.RANGED;
		damageTypes = DamageTypes.PHYSICAL;

		critChanceSum = 0.05;
		critMultiplierSum = 2.0;

		manaBase = 15;
		manaForLevel = 2;

		cooldownBase = 45000; // in milliseconds

		if (Player.class.isAssignableFrom(character.getClass()))
			initializeThingsForImagesAndUpgrades();

		refresh();
	}

	public ArrowInTheKnee(Characterr character, int level) {
		this(character);
		this.level = level;
	}

	public ArrowInTheKnee(Characterr character, int level, boolean is1L, boolean is1R, boolean is2L, boolean is2R,
			boolean is3L, boolean is3R) {
		this(character, level);

		skillActivated1L = is1L;
		skillActivated1R = is1R;
		skillActivated2L = is2L;
		skillActivated2R = is2R;
		skillActivated3L = is3L;
		skillActivated3R = is3R;
	}

	@Override
	public void doAttack(Characterr attacker, Characterr defender, Map<DamageTypes, Double> damageMap,
			boolean criticalStrike) {
		double damage = getRandomDamage();
		damageMap.put(getDamageTypes(), damage);

		int statusInt = random.nextInt(4) + 1;
		switch (statusInt) {
		case 1:
			defender.getStatus().addNewStatus(new Blind(defender));
			if (isSkillActivated3L()) {
				defender.getStatus().addNewStatus(new Vulnerability());
				defender.getStatus().addNewStatus(new Weakness());
			}
			break;
		case 2:
			defender.getStatus().addNewStatus(new Vulnerability());
			if (isSkillActivated3L()) {
				defender.getStatus().addNewStatus(new Weakness());
				defender.getStatus().addNewStatus(new Slow());
			}
			break;
		case 3:
			defender.getStatus().addNewStatus(new Weakness());
			if (isSkillActivated3L()) {
				defender.getStatus().addNewStatus(new Slow());
				defender.getStatus().addNewStatus(new Blind(defender));
			}
			break;
		case 4:
			defender.getStatus().addNewStatus(new Slow());
			if (isSkillActivated3L()) {
				defender.getStatus().addNewStatus(new Blind(defender));
				defender.getStatus().addNewStatus(new Vulnerability());
			}
			break;
		}

		if (isSkillActivated2L()) {
			double physDamage = damageMap.get(DamageTypes.PHYSICAL);
			Poison poison = new Poison(defender, physDamage);
			for (int n = 1; n <= NUMBER_OF_POISONS; n++)
				defender.getStatus().addNewStatus(poison);
		}

		if (isSkillActivated2R() && criticalStrike)
			timeFromLastUse += cooldownActually * REDUCTION_CD_AFTER_CRIT;

	}

	final double CD_REDUCTION = 0.4;

	final int NUMBER_OF_POISONS = 3;
	final double REDUCTION_CD_AFTER_CRIT = 0.6;

	final double ADD_MULTIPLIER_FOR_CRITICAL = 3.0;

	@Override
	public void initializeThingsForImagesAndUpgrades() {
		pathSkillToImage = "res/skills/archer/arrowInTheKnee.png";

		skillName1L = "Reduces cooldown of the skill by " + MyStringFormatter.formatDoubleAsPercentage(CD_REDUCTION, 0)
				+ ".";
		skillName1R = "The skill attacks twice.";

		skillName2L = "Arrow in the knee causes " + NUMBER_OF_POISONS + " poisons at the enemy.";
		skillName2R = "If deals critical strike reduced cooldown for next attack by "
				+ MyStringFormatter.formatDoubleAsPercentage(REDUCTION_CD_AFTER_CRIT, 0) + ".";

		skillName3L = "Now causes 3 (from 4 possible) statuses at hit enemy.";
		skillName3R = "Add " + MyStringFormatter.formatDoubleAsPercentage(ADD_MULTIPLIER_FOR_CRITICAL, 0)
				+ " critial multiplier.";

		skill1L = "res/skills/tempSkill.png";
		skill1R = "res/skills/tempSkill.png";
		skill2L = "res/skills/tempSkill.png";
		skill2R = "res/skills/tempSkill.png";
		skill3L = "res/skills/tempSkill.png";
		skill3R = "res/skills/tempSkill.png";
	}

	@Override
	public void refresh() {
		super.refresh();
		minDamage += minDamageForLevel * level;
		maxDamage += maxDamageForLevel * level;

		if (Player.class.isAssignableFrom(character.getClass())) {
			Item item = ((Player) character).getEq().getEquipmentElement(EquipmentSlots.FIRST_HAND);

			// Add damage from item if class is Player and weapon is ranged
			if (item != null && ((WeaponBase) item.getItemBase()).getSkillType().equals(SkillType.RANGED)) {
				minDamage += ((WeaponBase) item.getItemBase()).getMinDamage();
				maxDamage += ((WeaponBase) item.getItemBase()).getMaxDamage();
				critChanceSum = ((WeaponBase) item.getItemBase()).getCritChance();
				critMultiplierSum = ((WeaponBase) item.getItemBase()).getCritMultiplier();
			}
		}

		if (isSkillActivated1R())
			baseForNumberOfAttacks = 2;

		if (isSkillActivated3R())
			critMultiplierSum += ADD_MULTIPLIER_FOR_CRITICAL;

		this.calculateCooldown();
	}

	@Override
	public void addLevel() {
		super.addLevel();

		sumAttackSpeedBoostFromLevel = Math.pow(attackSpeedBoostFromLevel, level - 1);

		this.refresh();
	}

	@Override
	public void calculateCooldown() {
		super.calculateCooldown();

		double attackSpeedBoost = sumAttackSpeedBoostFromLevel;

		if (isSkillActivated1L())
			attackSpeedBoost = attackSpeedBoost * (1 - CD_REDUCTION);

		cooldownActually = (int) (cooldownActually * attackSpeedBoost);
	}

	@Override
	public String getSkillTooltip() {
		double damageMinBoostFromLevel = minDamageForLevel;
		double damageMaxBoostFromLevel = maxDamageForLevel;
		int newInterval = (int) (cooldownActually * attackSpeedBoostFromLevel);

		StringBuilder s = new StringBuilder();

		s.append(
				"Arrow in the knee is powerful attack that randomly can causes - slow, blind, weakness or vulnerability."
						+ "<br><br>");
		s.append("Level: " + level + "<br>");
		s.append("Cooldown: " + MyStringFormatter.formatDouble(cooldownActually / 1000.0, 2));
		if (level > 0)
			s.append("<font color=\"green\">(-"
					+ MyStringFormatter.formatDouble((cooldownActually - newInterval) / 1000.0, 2) + ")</font> ");
		s.append(" s<br>");
		if (currentMana > 0) {
			s.append("Mana cost: " + currentMana);
			if (level > 0)
				s.append(" <font color=\"green\">(+" + manaForLevel + ")</font>");
		}
		s.append("<br><br>");

		s.append("Damage: " + minDamage);
		if (level > 0)
			s.append("<font color=\"green\">(+" + MyStringFormatter.formatDouble(damageMinBoostFromLevel, 0)
					+ ")</font> ");
		s.append(" - " + maxDamage);
		if (level > 0)
			s.append(" <font color=\"green\">(+" + MyStringFormatter.formatDouble(damageMaxBoostFromLevel, 0)
					+ ")</font>");
		s.append("<br>");

		s.append("Critical chance: " + MyStringFormatter.formatDoubleAsPercentage(critChanceSum, 2) + "<br>");
		s.append("Critical multiplier: " + MyStringFormatter.formatDoubleAsPercentage(critMultiplierSum, 0) + "<br>");

		return MyTooltipFormatter.formatTooltipForBigSkill(name, s.toString());
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
