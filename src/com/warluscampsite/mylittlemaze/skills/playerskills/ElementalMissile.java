package com.warluscampsite.mylittlemaze.skills.playerskills;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.FightController;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.controllers.MyTooltipFormatter;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.skills.SkillRange;
import com.warluscampsite.mylittlemaze.skills.SkillType;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;
import com.warluscampsite.mylittlemaze.status.Chill;
import com.warluscampsite.mylittlemaze.status.Entangle;
import com.warluscampsite.mylittlemaze.status.Ignite;
import com.warluscampsite.mylittlemaze.status.Shock;

public class ElementalMissile extends Skill {

	final int BOOSTS_FROM_INT_TO_MIN = 1, BOOSTS_FROM_INT_TO_MAX = 2;
	final DamageTypes[] damageTypes = { DamageTypes.ICE, DamageTypes.FIRE, DamageTypes.EARTH, DamageTypes.LIGHTNING };

	/// boosts from level to damage and attack speed
	final int damageBoostFromLevel = 2;
	int sumDamageBoostFromLevel;
	final double attackSpeedBoostFromLevel = 0.991;
	double sumAttackSpeedBoostFromLevel = 1;

	double chanceForImplictAilment = 0.0;

	public ElementalMissile(Characterr character) {
		super(character);

		name = "Elemental missile";

		baseForNumberOfAttacks = 1;

		manaBase = 5;
		manaForLevel = 2;

		skillRange = SkillRange.SINGLE_ANY;
		skillType = SkillType.MAGIC;
		// damageTypes = DamageTypes.EARTH;

		critChanceSum = 0.05;
		critMultiplierSum = 2.0;

		cooldownBase = 12000; // in milliseconds

		if (Player.class.isAssignableFrom(character.getClass()))
			initializeThingsForImagesAndUpgrades();

		refresh();
	}

	public ElementalMissile(Characterr character, int level) {
		this(character);
		this.level = level;
	}

	public ElementalMissile(Characterr character, int level, boolean is1L, boolean is1R, boolean is2L, boolean is2R,
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
		if (isSkillActivated1L()) {
			double damage = 0;
			for (DamageTypes damageTypes2 : damageTypes) {
				damage = getRandomDamage();
				damageMap.put(damageTypes2, damage);

				if (FightController.trueOrFalse(chanceForImplictAilment))
					implictAilments(damageTypes2, defender);
			}
		} else {
			double damage = getRandomDamage();
			DamageTypes damageType = damageTypes[random.nextInt(damageTypes.length)];
			damageMap.put(damageType, damage);

			if (FightController.trueOrFalse(chanceForImplictAilment))
				implictAilments(damageType, defender);
		}
	}

	private void implictAilments(DamageTypes damageType, Characterr defender) {
		switch (damageType) {
		case FIRE:
			defender.getStatus().addNewStatus(new Ignite(defender));
			break;
		case ICE:
			defender.getStatus().addNewStatus(new Chill());
			break;
		case LIGHTNING:
			defender.getStatus().addNewStatus(new Shock(defender));
			break;
		case EARTH:
			defender.getStatus().addNewStatus(new Entangle(defender));
			break;
		default:
			break;
		}

	}

	final int LOWER_DAMAGE_BY = 3;
	final double CHANCE_TO_IMPLICT_AILMENTS = 0.3;

	final int NEW_MISSILE_EVERY_SKILL_LEVEL = 7;

	final double ADD_MULTIPLIER_FOR_EACH_POINT_OF_ATTRIBUTE = 0.02;
	final double ADD_CHANCE_FOR_AILMENTS = 0.01;

	@Override
	public void initializeThingsForImagesAndUpgrades() {
		pathSkillToImage = "res/skills/elementalist/elementalMissile.png";

		skillName1L = "Elemental missile now has three times lower damage, but deals damage with each elemental damage.";
		skillName1R = "Missile has " + MyStringFormatter.formatDoubleAsPercentage(CHANCE_TO_IMPLICT_AILMENTS, 0)
				+ " to implict ailments suitable to damage type (fire - ignite, ice - chill, lightning - shock, earth - entangle).";

		skillName2L = "Double mana cost of skill, but strength increase Elemental Missile damage.";
		skillName2R = "Add new missile every " + NEW_MISSILE_EVERY_SKILL_LEVEL + " level.";

		skillName3L = "Your strength and vitality increase critical multiplier of the skill.";
		skillName3R = "Intelligence and wisdom add "
				+ MyStringFormatter.formatDoubleAsPercentage(ADD_MULTIPLIER_FOR_EACH_POINT_OF_ATTRIBUTE, 0)
				+ " chance to implict ailemnts.";

		skill1L = "res/skills/tempSkill.png";
		skill1R = "res/skills/tempSkill2.png";
		skill2L = "res/skills/tempSkill.png";
		skill2R = "res/skills/tempSkill2.png";
		skill3L = "res/skills/tempSkill.png";
		skill3R = "res/skills/tempSkill2.png";
	}

	@Override
	public void refresh() {
		super.refresh();

		int intt = character.getAttributes().getAttribute(AttributesEnum.INTELLIGENCE).getSum();
		minDamage += (intt * BOOSTS_FROM_INT_TO_MIN + sumDamageBoostFromLevel);
		maxDamage += (intt * BOOSTS_FROM_INT_TO_MAX + sumDamageBoostFromLevel);

		chanceForImplictAilment = 0.0;
		if (isSkillActivated1R())
			chanceForImplictAilment += CHANCE_TO_IMPLICT_AILMENTS;
		if (isSkillActivated3R())
			chanceForImplictAilment += (intt + character.getAttributes().getAttribute(AttributesEnum.WISDOM).getSum())
					* ADD_MULTIPLIER_FOR_EACH_POINT_OF_ATTRIBUTE;

		if (isSkillActivated2L()) {
			int str = character.getAttributes().getAttribute(AttributesEnum.STRENGTH).getSum();
			minDamage += (str * BOOSTS_FROM_INT_TO_MIN);
			maxDamage += (str * BOOSTS_FROM_INT_TO_MAX);
			currentMana += currentMana;
		}

		if (isSkillActivated1L()) {
			minDamage = minDamage / LOWER_DAMAGE_BY;
			maxDamage = maxDamage / LOWER_DAMAGE_BY;
		}

		if (isSkillActivated3L()) {
			critMultiplierSum = 2.0;
			double addMultiplier = (character.getAttributes().getAttribute(AttributesEnum.STRENGTH).getSum()
					+ character.getAttributes().getAttribute(AttributesEnum.VITALITY).getSum())
					* ADD_MULTIPLIER_FOR_EACH_POINT_OF_ATTRIBUTE;
			critMultiplierSum += addMultiplier;
		}
		
		numberOfAttacks = 1;
		if (isSkillActivated2R())
			numberOfAttacks += level / NEW_MISSILE_EVERY_SKILL_LEVEL;
		
		this.calculateCooldown();
	}

	@Override
	public void addLevel() {
		super.addLevel();

		sumDamageBoostFromLevel = damageBoostFromLevel * level;
		sumAttackSpeedBoostFromLevel = Math.pow(attackSpeedBoostFromLevel, level - 1);

		this.refresh();
	}

	@Override
	public void calculateCooldown() {
		super.calculateCooldown();
		cooldownActually = (int) (cooldownActually * sumAttackSpeedBoostFromLevel);
	}

	@Override
	public String getSkillTooltip() {
		double damageBoostFromLeveltp = damageBoostFromLevel;
		// if (isSkillActivated3L())
		// damageBoostFromLeveltp = damageBoostFromLeveltp *
		// MULTIPLIER_FOR_DAMAGE;

		StringBuilder s = new StringBuilder();

		if (isSkillActivated1L())
			s.append(
					"Elemental missile skill shot powerful projectile with damage type randomly choosen from all elements (fire, ice, earth, lightning)."
							+ "<br><br>");
		else
			s.append(
					"Elemental missile skill shot powerful projectile and deals damage with each of elements (fire, ice, earth, lightning)."
							+ "<br><br>");
		s.append("Level: " + level + "<br>");
		s.append("Cooldown: " + MyStringFormatter.formatDouble(cooldownActually / 1000.0, 2));
		if (level > 0)
			s.append("<font color=\"green\">(-"
					+ MyStringFormatter
							.formatDouble((cooldownActually - cooldownActually * attackSpeedBoostFromLevel) / 1000.0, 2)
					+ ")</font>");
		s.append(" s<br>");
		if (currentMana > 0) {
			s.append("Mana cost: " + currentMana);
			if (level > 0)
				s.append(" <font color=\"green\">(+" + manaForLevel + ")</font>");
		}
		s.append("<br><br>");

		s.append("Missile: " + numberOfAttacks);
		if ((level + 1) % NEW_MISSILE_EVERY_SKILL_LEVEL == 0)
			s.append("<font color=\"green\">(+1)</font>");
		s.append("<br>");
		if (isSkillActivated1L()) {
			s.append("Damage: 4x " + MyStringFormatter.formatDouble(minDamage, 1));
			if (level > 0)
				s.append("<font color=\"green\">(+"
						+ MyStringFormatter.formatDouble(damageBoostFromLeveltp / LOWER_DAMAGE_BY, 1) + ")</font>");
			s.append(" - " + MyStringFormatter.formatDouble(maxDamage, 1));
			if (level > 0)
				s.append("<font color=\"green\">(+"
						+ MyStringFormatter.formatDouble(damageBoostFromLeveltp / LOWER_DAMAGE_BY, 1) + ")</font>");
			s.append("<br>");
		} else {
			s.append("Damage: " + minDamage);
			if (level > 0)
				s.append("<font color=\"green\">(+" + MyStringFormatter.formatDouble(damageBoostFromLeveltp, 1)
						+ ")</font>");
			s.append(" - " + maxDamage);
			if (level > 0)
				s.append("<font color=\"green\">(+" + MyStringFormatter.formatDouble(damageBoostFromLeveltp, 1)
						+ ")</font>");
			s.append("<br>");
		}

		s.append("Critical chance: " + MyStringFormatter.formatDoubleAsPercentage(critChanceSum, 0) + "<br>");
		s.append("Critical multiplier: " + MyStringFormatter.formatDoubleAsPercentage(critMultiplierSum, 0) + "<br>");

		if (isSkillActivated1L() || isSkillActivated3R())
			s.append("Skill has " + MyStringFormatter.formatDoubleAsPercentage(chanceForImplictAilment, 0)
					+ " of implict ailiment of adequate to skill damage type (fire - ignite, ice - chill, lightning - shock, earth - entangle).");

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
