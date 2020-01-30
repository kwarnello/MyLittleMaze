package com.warluscampsite.mylittlemaze.skills.playerskills;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.FightController;
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
import com.warluscampsite.mylittlemaze.status.Poison;

public class Barrage extends Skill {

	/// boosts from level to damage and attack speed
	final double attackSpeedBoostFromLevel = 0.99;
	double sumAttackSpeedBoostFromLevel = 1;

	final int BASE_NUMBER_OF_ARROWS = 5;
	final int NEW_ARROW_AFTER_LEVEL = 4;
	int newArrowAfterLevel = NEW_ARROW_AFTER_LEVEL;

	public Barrage(Characterr character) {
		super(character);

		name = "Barrage";

		baseForNumberOfAttacks = BASE_NUMBER_OF_ARROWS;

		skillRange = SkillRange.SINGLE_ANY;
		skillType = SkillType.RANGED;
		damageTypes = DamageTypes.PHYSICAL;

		critChanceSum = 0.03;
		critMultiplierSum = 1.8;

		manaBase = 1;
		manaForLevel = 1;

		cooldownBase = 20000; // in milliseconds

		if (Player.class.isAssignableFrom(character.getClass()))
			initializeThingsForImagesAndUpgrades();

		refresh();
	}

	public Barrage(Characterr character, int level) {
		this(character);
		this.level = level;
	}

	public Barrage(Characterr character, int level, boolean is1L, boolean is1R, boolean is2L, boolean is2R,
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
	public void doAttack(Characterr attacker, Characterr defender, Map<DamageTypes, Double> damageMap, boolean criticalStrike) {
		double damage = getRandomDamage();
		damageMap.put(getDamageTypes(), damage);

		if (isSkillActivated2R()) {
			if (FightController.trueOrFalse(PROBABILITY_FOR_POISON))
				defender.getStatus().addNewStatus(new Poison(defender, damageMap.get(DamageTypes.PHYSICAL)));
		}

		if (isSkillActivated3R()) {
			DamageTypes type = possibleDamage[random.nextInt(possibleDamage.length)];
			double damageBuff = damageMap.get(type) + damageMap.get(DamageTypes.PHYSICAL) * PERCENTAGE_OF_ELEMENTAL;
			damageMap.put(type, damageBuff);
		}
	}

	final double PROBABILITY_FOR_POISON = 0.2;

	final double PERCENTAGE_OF_ELEMENTAL = 2.0;
	final DamageTypes[] possibleDamage = { DamageTypes.FIRE, DamageTypes.ICE, DamageTypes.EARTH,
			DamageTypes.LIGHTNING };

	@Override
	public void initializeThingsForImagesAndUpgrades() {
		pathSkillToImage = "res/skills/archer/barrage.png";

		skillName1L = "Level needed for new arrow is reduced by one.";
		skillName1R = "Add one point of physical damage every level of skill.";

		skillName2L = "Level needed for new arrow is reduced by one.";
		skillName2R = "Every arrow has " + MyStringFormatter.formatDoubleAsPercentage(PROBABILITY_FOR_POISON, 0)
				+ " chance for poison an enemy.";

		skillName3L = "Level needed for new arrow is reduced by one.";
		skillName3R = "Deal random extra elemental damage equals "
				+ MyStringFormatter.formatDoubleAsPercentage(PERCENTAGE_OF_ELEMENTAL, 0) + " of physical damage.";

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

		if (Player.class.isAssignableFrom(character.getClass())) {
			Item item = ((Player) character).getEq().getEquipmentElement(EquipmentSlots.FIRST_HAND);
			// Add damage from item if class is Player and weapon is ranged
			if (item != null && ((WeaponBase) item.getItemBase()).getSkillType().equals(SkillType.RANGED)) {
				minDamage += ((WeaponBase) item.getItemBase()).getMinDamage();
				maxDamage += ((WeaponBase) item.getItemBase()).getMaxDamage();
				critChanceSum = ((WeaponBase) item.getItemBase()).getCritChance();
				critMultiplierSum = ((WeaponBase) item.getItemBase()).getCritMultiplier();

				if (isSkillActivated1R()) {
					minDamage += level;
					maxDamage += level;
				}
			}
		}
		baseForNumberOfAttacks = BASE_NUMBER_OF_ARROWS + (level / newArrowAfterLevel);
		this.calculateCooldown();
	}

	@Override
	public void addLevel() {
		super.addLevel();

		newArrowAfterLevel = NEW_ARROW_AFTER_LEVEL;

		if (isSkillActivated1L())
			newArrowAfterLevel--;
		if (isSkillActivated2L())
			newArrowAfterLevel--;
		if (isSkillActivated3L())
			newArrowAfterLevel--;

		sumAttackSpeedBoostFromLevel = Math.pow(attackSpeedBoostFromLevel, level - 1);

		this.refresh();
	}

	@Override
	public void calculateCooldown() {
		super.calculateCooldown();

		double attackSpeedBoost = sumAttackSpeedBoostFromLevel;

		cooldownActually = (int) (cooldownActually * attackSpeedBoost);
	}

	@Override
	public String getSkillTooltip() {
		int newInterval = (int) (cooldownActually * attackSpeedBoostFromLevel);

		StringBuilder s = new StringBuilder();

		s.append("Barrage hit one enemy multiple times. Add new arrow every " + newArrowAfterLevel + " level."
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

		s.append("Arrows: " + numberOfAttacks);
		if ((level + 1) % newArrowAfterLevel == 0)
			s.append("<font color=\"green\">(+1)</font>");
		s.append("<br>");
		s.append("Damage: " + minDamage);
		if (isSkillActivated1R())
			s.append("<font color=\"green\">(+1)</font> ");
		s.append(" - " + maxDamage);
		if (isSkillActivated1R())
			s.append("<font color=\"green\">(+1)</font> ");
		s.append("<br>");
		if (isSkillActivated3R()) {
			s.append("Extra damage of random element: " + minDamage * PERCENTAGE_OF_ELEMENTAL);
			s.append(" - " + maxDamage * PERCENTAGE_OF_ELEMENTAL);
			s.append("<br>");
		}

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
