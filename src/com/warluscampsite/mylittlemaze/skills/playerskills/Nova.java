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

public class Nova extends Skill {

	final int BOOSTS_FROM_INT_TO_MIN = 3, BOOSTS_FROM_INT_TO_MAX = 5;
	final DamageTypes[] damageTypes = { DamageTypes.ICE, DamageTypes.FIRE, DamageTypes.EARTH, DamageTypes.LIGHTNING };

	/// boosts from level to damage and attack speed
	final int damageBoostFromLevel = 2;
	int sumDamageBoostFromLevel;
	final double attackSpeedBoostFromLevel = 0.993;
	double sumAttackSpeedBoostFromLevel = 1;

	public Nova(Characterr character) {
		super(character);

		name = "Nova";

		baseForNumberOfAttacks = 1;

		skillRange = SkillRange.ALL_ONE_ROW;
		skillType = SkillType.MAGIC;

		critChanceSum = 0.05;
		critMultiplierSum = 1.8;

		manaBase = 25;
		manaForLevel = 4;

		cooldownBase = 29000; // in milliseconds

		if (Player.class.isAssignableFrom(character.getClass()))
			initializeThingsForImagesAndUpgrades();

		refresh();
	}

	public Nova(Characterr character, int level) {
		this(character);
		this.level = level;
	}

	public Nova(Characterr character, int level, boolean is1L, boolean is1R, boolean is2L, boolean is2R, boolean is3L,
			boolean is3R) {
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
		double damage = 0;
		damage = getRandomDamage();
		DamageTypes damageType = damageTypes[random.nextInt(damageTypes.length)];

		damageMap.put(damageType, damage);

		if (isSkillActivated2L())
			defender.getMana().changeCurrentValue(-damage * MANA_BURN_PERCENTAGE);

		if (isSkillActivated3R()) {
			if (FightController.trueOrFalse(CHANCE_FOR_AILMENTS))
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

	final double MANA_BURN_PERCENTAGE = 0.15;
	final double BUFF_FOR_CRITICAL_STRIKE_FOR_PIECE_OF_CLOTH = 0.03;
	final double BUFF_FOR_CRITICAL_MULTIPLIER_FOR_PIECE_OF_HEAVY = 0.1;
	final double CHANCE_FOR_AILMENTS = 0.3;

	@Override
	public void initializeThingsForImagesAndUpgrades() {
		pathSkillToImage = "res/skills/elementalist/nova.png";

		skillName1L = "Double cooldown reduction for level of skill.";
		skillName1R = "Wisdom reduced mana cost of skill and increase damage.";

		skillName2L = "The skill now burns the opponent's mana in amount "
				+ MyStringFormatter.formatDoubleAsPercentage(MANA_BURN_PERCENTAGE, 0) + " of the damage dealt.";
		skillName2R = "For each piece of heavy armor add "
				+ MyStringFormatter.formatDoubleAsPercentage(BUFF_FOR_CRITICAL_MULTIPLIER_FOR_PIECE_OF_HEAVY, 0)
				+ " citical multiplier and for each cloth "
				+ MyStringFormatter.formatDoubleAsPercentage(BUFF_FOR_CRITICAL_STRIKE_FOR_PIECE_OF_CLOTH, 0)
				+ " critical chance.";

		skillName3L = "Nova now hits all enemies.";
		skillName3R = "Add " + MyStringFormatter.formatDoubleAsPercentage(CHANCE_FOR_AILMENTS, 0)
				+ " to implict ailments to enemies.";

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

		if (isSkillActivated1R()) {
			int wis = character.getAttributes().getAttribute(AttributesEnum.WISDOM).getSum();
			minDamage += wis;
			maxDamage += wis;

			currentMana -= wis;
			if (currentMana < 1)
				currentMana = 1;
		}

		if (isSkillActivated2R()) {
			if (Player.class.isAssignableFrom(character.getClass())) {
				critChanceSum = 0.05;
				critMultiplierSum = 1.8;

				critChanceSum += ((Player) character).getEq().getNumberOfCloth()
						* BUFF_FOR_CRITICAL_STRIKE_FOR_PIECE_OF_CLOTH;
				critMultiplierSum += ((Player) character).getEq().getNumberOfHeavy()
						* BUFF_FOR_CRITICAL_MULTIPLIER_FOR_PIECE_OF_HEAVY;
			}
		}

		if (isSkillActivated3L())
			skillRange = SkillRange.ALL;

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

		if (isSkillActivated1L())
			cooldownActually = (int) (cooldownActually * sumAttackSpeedBoostFromLevel);

		cooldownActually = (int) (cooldownActually * sumAttackSpeedBoostFromLevel);
	}

	@Override
	public String getSkillTooltip() {
		double damageBoostFromLeveltp = damageBoostFromLevel;

		int newInterval;
		if (isSkillActivated1R())
			newInterval = (int) (cooldownActually * attackSpeedBoostFromLevel * attackSpeedBoostFromLevel);
		else
			newInterval = (int) (cooldownActually * attackSpeedBoostFromLevel);

		StringBuilder s = new StringBuilder();

		s.append(
				"Nova shot powerful wave with damage type randomly choosen from all elements (fire, ice, earth, lightning) and hit enemies in single row."
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
			s.append("<font color=\"green\">(+" + MyStringFormatter.formatDouble(damageBoostFromLeveltp, 1)
					+ ")</font>");
		s.append(" - " + maxDamage);
		if (level > 0)
			s.append("<font color=\"green\">(+" + MyStringFormatter.formatDouble(damageBoostFromLeveltp, 1)
					+ ")</font>");
		s.append("<br>");

		s.append("Critical chance: " + MyStringFormatter.formatDoubleAsPercentage(critChanceSum, 0) + "<br>");
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
