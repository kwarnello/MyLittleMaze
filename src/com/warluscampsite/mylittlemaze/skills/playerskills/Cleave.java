package com.warluscampsite.mylittlemaze.skills.playerskills;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.FightController;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.controllers.MyTooltipFormatter;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.WeaponBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.skills.SkillRange;
import com.warluscampsite.mylittlemaze.skills.SkillType;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;
import com.warluscampsite.mylittlemaze.statistics.EquipmentSlots;
import com.warluscampsite.mylittlemaze.status.Vulnerability;

public class Cleave extends Skill {

	final int BOOSTS_FROM_STR_TO_MIN = 1, BOOSTS_FROM_STR_TO_MAX = 1;

	/// boosts from level to damage and attack speed
	final int damageBoostFromLevel = 1;
	int sumDamageBoostFromLevel;
	final double attackSpeedBoostFromLevel = 0.99;
	double sumAttackSpeedBoostFromLevel = 1;

	public Cleave(Characterr character) {
		super(character);

		name = "Cleave";

		baseForNumberOfAttacks = 1;

		skillRange = SkillRange.ALL_ONE_ROW;
		skillType = SkillType.MELEE;
		damageTypes = DamageTypes.PHYSICAL;

		critChanceSum = 0.03;
		critMultiplierSum = 1.5;

		cooldownBase = 10000; // in milliseconds

		if (Player.class.isAssignableFrom(character.getClass()))
			initializeThingsForImagesAndUpgrades();

		refresh();
	}

	public Cleave(Characterr character, int level) {
		this(character);
		this.level = level;
	}

	public Cleave(Characterr character, int level, boolean is1L, boolean is1R, boolean is2L, boolean is2R, boolean is3L,
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
	public void doAttack(Characterr attacker, Characterr defender, Map<DamageTypes, Double> damageMap, boolean criticalStrike) {
		double damage = getRandomDamage();
		damageMap.put(getDamageTypes(), damage);

		if (isSkillActivated2L()) {
			double damageBuff = damageMap.get(DamageTypes.FIRE) + damageMap.get(DamageTypes.PHYSICAL) * FIRE_CONVERSION;
			damageMap.put(DamageTypes.FIRE, damageBuff);
		}
		/// here cause vulnerable
		if (isSkillActivated2R())
			if (FightController.trueOrFalse(VULNERABLE_PROB))
				defender.getStatus().addNewStatus(new Vulnerability(VULNERABLE_TIME));

		if (isSkillActivated3R()) {
			if (Player.class.isAssignableFrom(character.getClass())) {
				Item item = ((Player) character).getEq().getEquipmentElement(EquipmentSlots.FIRST_HAND);
				if (item != null
						&& ((WeaponBase) item.getItemBase()).getItemType().equals(ItemTypes.ONE_HANDED_MELEE)) {
					double damageBuff = damageMap.get(DamageTypes.LEECH)
							+ damageMap.get(DamageTypes.PHYSICAL) * LEECH_MULTIPLIER;
					damageMap.put(DamageTypes.LEECH, damageBuff);
				}
			}
		}
	}

	final double FIRE_CONVERSION = 0.5; // 2L
	final double VULNERABLE_PROB = 0.2;
	final int VULNERABLE_TIME = 5000; // 2R
	final double LEECH_MULTIPLIER = 0.2; // 3R

	@Override
	public void initializeThingsForImagesAndUpgrades() {
		pathSkillToImage = "res/skills/warrior/cleave.png";

		skillName1L = "Double damage boost for every level of Cleave.";
		skillName1R = "Double attack speed boost for every level of Cleave.";

		skillName2L = "Add half of damage as fire damage.";
		skillName2R = "Every hit has " + MyStringFormatter.formatDoubleAsPercentage(VULNERABLE_PROB, 0)
				+ " chance to cause vulunerable for 5 seconds.";

		skillName3L = "If you wield two handed sword Cleave has double critical chance and multiplier.";
		skillName3R = "If you wield one handed sword and shield you has "
				+ MyStringFormatter.formatDoubleAsPercentage(LEECH_MULTIPLIER, 0) + " life leech with Cleave.";

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

		int str = character.getAttributes().getAttribute(AttributesEnum.STRENGTH).getSum();

		// Add damage from strength
		minDamage += str * BOOSTS_FROM_STR_TO_MIN + sumDamageBoostFromLevel;
		maxDamage += str * BOOSTS_FROM_STR_TO_MAX + sumDamageBoostFromLevel;

		if (isSkillActivated1L()) {
			minDamage += sumDamageBoostFromLevel;
			maxDamage += sumDamageBoostFromLevel;
		}

		// Add damage from item if class is Player and weapon is melee
		if (Player.class.isAssignableFrom(character.getClass())) {
			Item item = ((Player) character).getEq().getEquipmentElement(EquipmentSlots.FIRST_HAND);
			if (item != null && ((WeaponBase) item.getItemBase()).getSkillType().equals(SkillType.MELEE)) {
				minDamage += ((WeaponBase) item.getItemBase()).getMinDamage();
				maxDamage += ((WeaponBase) item.getItemBase()).getMaxDamage();
				critChanceSum = ((WeaponBase) item.getItemBase()).getCritChance();
				critMultiplierSum = ((WeaponBase) item.getItemBase()).getCritMultiplier();

				if (isSkillActivated3L()
						&& ((WeaponBase) item.getItemBase()).getItemType().equals(ItemTypes.TWO_HANDED_MELEE)) {
					critChanceSum = critChanceSum * 2;
					critMultiplierSum = critMultiplierSum * 2;
				}
			}
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

		double attackSpeedBoost = 0;

		if (isSkillActivated1R())
			attackSpeedBoost = sumAttackSpeedBoostFromLevel * sumAttackSpeedBoostFromLevel;
		else
			attackSpeedBoost = sumAttackSpeedBoostFromLevel;

		cooldownActually = (int) (cooldownActually * attackSpeedBoost);
	}

	@Override
	public String getSkillTooltip() {
		double damageBoostFromLeveltp = damageBoostFromLevel;
		if (isSkillActivated1L())
			damageBoostFromLeveltp = 2 * damageBoostFromLeveltp;

		int newInterval;
		if (isSkillActivated1R())
			newInterval = (int) (cooldownActually * attackSpeedBoostFromLevel * attackSpeedBoostFromLevel);
		else
			newInterval = (int) (cooldownActually * attackSpeedBoostFromLevel);

		StringBuilder s = new StringBuilder();

		s.append("Cleave attack all enemies in one row. Needs melee weapon. Level increased damage and attack speed."
				+ "<br><br>");
		s.append("Level: " + level + "<br>");
		s.append("Cooldown: " + MyStringFormatter.formatDouble(cooldownActually / 1000.0, 2));
		s.append(" s");
		if (level > 0)
			s.append("<font color=\"green\">(-"
					+ MyStringFormatter.formatDouble((cooldownActually - newInterval) / 1000.0, 2) + ")</font> ");
		s.append("<br><br>");

		s.append("Damage: " + minDamage);
		if (level > 0)
			s.append("<font color=\"green\">(+" + MyStringFormatter.formatDouble(damageBoostFromLeveltp, 1)
					+ ")</font> ");
		s.append(" - " + maxDamage);
		if (level > 0)
			s.append(" <font color=\"green\">(+" + MyStringFormatter.formatDouble(damageBoostFromLeveltp, 1)
					+ ")</font>");
		s.append("<br>");

		if (isSkillActivated2L()) {
			s.append("Fire damage: " + minDamage / 2);
			if (level > 0)
				s.append(" <font color=\"green\">(+" + MyStringFormatter.formatDouble(damageBoostFromLeveltp / 2, 1)
						+ ")</font>");
			s.append(" - " + maxDamage / 2);
			if (level > 0)
				s.append(" <font color=\"green\">(+" + MyStringFormatter.formatDouble(damageBoostFromLeveltp / 2, 1)
						+ ")</font>");
			s.append("<br>");
		}

		s.append("Critical chance: " + MyStringFormatter.formatDoubleAsPercentage(critChanceSum, 2) + "<br>");
		s.append("Critical multiplier: " + MyStringFormatter.formatDoubleAsPercentage(critMultiplierSum, 0) + "<br>");

		if (isSkillActivated2R())
			s.append("Hit has 20% chance for cause vulnerable for 5 seconds.<br>");
		if (isSkillActivated3R())
			s.append("Hit has 20% life leech.<br>");

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
