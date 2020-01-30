package com.warluscampsite.mylittlemaze.skills.playerskills;

import java.util.ArrayList;
import java.util.List;
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

public class WeaponEnchantment extends Skill {

	final double BOOSTS_FROM_WISDOM_TO_MIN = 0.05, BOOSTS_FROM_WISDOM_TO_MAX = 0.12;
	final DamageTypes[] damageTypes = { DamageTypes.ICE, DamageTypes.FIRE, DamageTypes.EARTH, DamageTypes.LIGHTNING };

	int timeOfBuff = 15000;

	/// boosts from level to damage and attack speed
	final int damageBoostFromLevel = 1;
	int sumDamageBoostFromLevel;
	final double attackSpeedBoostFromLevel = 0.994;
	double sumAttackSpeedBoostFromLevel = 1;

	public WeaponEnchantment(Characterr character) {
		super(character);

		name = "Weapon enchantment";

		baseForNumberOfAttacks = 1;

		skillRange = SkillRange.SINGLE_ANY;
		skillType = SkillType.BUFF;

		critChanceSum = 0.00;
		critMultiplierSum = 1.0;

		manaBase = 25;
		manaForLevel = 5;

		cooldownBase = 35000; // in milliseconds

		if (Player.class.isAssignableFrom(character.getClass()))
			initializeThingsForImagesAndUpgrades();

		refresh();
	}

	public WeaponEnchantment(Characterr character, int level) {
		this(character);
		this.level = level;
	}

	public WeaponEnchantment(Characterr character, int level, boolean is1L, boolean is1R, boolean is2L, boolean is2R,
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
		List<DamageTypes> types = new ArrayList<DamageTypes>();
		types.add(damageTypes[random.nextInt(damageTypes.length)]);

		if (isSkillActivated1L()) {
			for (DamageTypes damageTypes : types) {
				if (FightController.trueOrFalse(CHANCE_FOR_NEXT_ELEMENT)) {
					if (!types.contains(damageTypes))
						types.add(damageTypes);
				}
			}
		}

		int minDamageTemp = this.minDamage;
		int maxDamageTemp = this.maxDamage;
		int timeOfBuffTemp = this.timeOfBuff;

		if (isSkillActivated1R()) {
			if (FightController.trueOrFalse(critChanceSum)) {
				minDamageTemp = (int) (minDamage * critMultiplierSum);
				maxDamageTemp = (int) (maxDamage * critMultiplierSum);
				timeOfBuffTemp = (int) (timeOfBuff * critMultiplierSum);
			}
		}

		attacker.getStatus().addNewStatus(new com.warluscampsite.mylittlemaze.status.WeaponEnchantment(types,
				timeOfBuffTemp, minDamageTemp, maxDamageTemp));
	}

	final int TIME_BUFF_FROM_WISDOM = 200;
	final double CRITICAL_CHANCE_FOR_ATR = 0.005;
	final double CRITICAL_MULTIPLIER_FOR_ATR = 0.07;
	final double CHANCE_FOR_NEXT_ELEMENT = 0.3;

	@Override
	public void initializeThingsForImagesAndUpgrades() {
		pathSkillToImage = "res/skills/elementalist/weaponEnchantment.png";

		skillName1L = "The skill now has  " + MyStringFormatter.formatDoubleAsPercentage(CHANCE_FOR_NEXT_ELEMENT, 0)
				+ " to add another type of element (chance is rolled for every type, so can buff with each element).";
		skillName1R = "Weapon enchantment can crit now. Wisdom and vitality add critical chance. Strength and intelligence buffs critical multiplier.";

		skillName2L = "Wisdom increases time of buff.";
		skillName2R = "Strength adds damage to buff.";

		skillName3L = "Weapon enchantment now has four times longer duration.";
		skillName3R = "Reduce duration by half but now affect all allies.";

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

		int atr = character.getAttributes().getAttribute(AttributesEnum.WISDOM).getSum()
				+ character.getAttributes().getAttribute(AttributesEnum.INTELLIGENCE).getSum();

		if (isSkillActivated2R())
			atr += character.getAttributes().getAttribute(AttributesEnum.STRENGTH).getSum();

		minDamage += ((int) (atr * BOOSTS_FROM_WISDOM_TO_MIN) + sumDamageBoostFromLevel);
		maxDamage += ((int) (atr * BOOSTS_FROM_WISDOM_TO_MAX) + sumDamageBoostFromLevel);

		timeOfBuff = 15000;

		if (isSkillActivated2L()) {
			atr = character.getAttributes().getAttribute(AttributesEnum.WISDOM).getSum();
			timeOfBuff += atr * TIME_BUFF_FROM_WISDOM;
		}

		if (isSkillActivated3L())
			timeOfBuff = timeOfBuff * 4;

		if (isSkillActivated3R()) {
			skillRange = SkillRange.ALL;
			timeOfBuff = timeOfBuff / 2;
		}

		if (isSkillActivated1R()) {
			critChanceSum = (character.getAttributes().getAttribute(AttributesEnum.WISDOM).getSum()
					+ character.getAttributes().getAttribute(AttributesEnum.VITALITY).getSum())
					* CRITICAL_CHANCE_FOR_ATR;
			critMultiplierSum = 1 + (character.getAttributes().getAttribute(AttributesEnum.INTELLIGENCE).getSum()
					+ character.getAttributes().getAttribute(AttributesEnum.STRENGTH).getSum())
					* CRITICAL_MULTIPLIER_FOR_ATR;
		}

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

		StringBuilder s = new StringBuilder();

		s.append("Enchants weapon of the ally with random elements (buff from each element stacks)." + "<br><br>");
		s.append("Level: " + level + "<br>");
		s.append("Cooldown: " + MyStringFormatter.formatDouble(cooldownActually / 1000.0, 2));
		if (level > 0)
			s.append("<font color=\"green\">(-"
					+ MyStringFormatter
							.formatDouble((cooldownActually - cooldownActually * attackSpeedBoostFromLevel) / 1000.0, 2)
					+ ")</font> ");
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
		s.append("Duration: " + MyStringFormatter.formatAsAPS(timeOfBuff / 1000, 1) + " s");

		if (isSkillActivated1R()) {
			s.append("Critical chance: " + MyStringFormatter.formatDoubleAsPercentage(critChanceSum, 0) + "<br>");
			s.append("Critical multiplier: " + MyStringFormatter.formatDoubleAsPercentage(critMultiplierSum, 0) + "<br>");
		}

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
