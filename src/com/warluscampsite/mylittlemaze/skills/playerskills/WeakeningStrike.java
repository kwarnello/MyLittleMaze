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
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;
import com.warluscampsite.mylittlemaze.statistics.EquipmentSlots;
import com.warluscampsite.mylittlemaze.status.Bleed;
import com.warluscampsite.mylittlemaze.status.Vulnerability;
import com.warluscampsite.mylittlemaze.status.Weakness;

public class WeakeningStrike extends Skill {

	final int BOOSTS_FROM_STR_TO_MIN = 2, BOOSTS_FROM_STR_TO_MAX = 3;

	/// boosts from level to damage and attack speed
	final int damageBoostFromLevel = 2;
	int sumDamageBoostFromLevel;
	final double attackSpeedBoostFromLevel = 0.992;
	double sumAttackSpeedBoostFromLevel = 1;

	double chanceToCauseWeakness = 0.4;
	int timeOfWeakness = 10000;

	public WeakeningStrike(Characterr character) {
		super(character);

		name = "Weakening Strike";

		baseForNumberOfAttacks = 1;

		skillRange = SkillRange.SINGLE_ONE_ROW;
		skillType = SkillType.MELEE;
		damageTypes = DamageTypes.PHYSICAL;

		critChanceSum = 0.05;
		critMultiplierSum = 1.8;

		cooldownBase = 25000; // in milliseconds

		if (Player.class.isAssignableFrom(character.getClass()))
			initializeThingsForImagesAndUpgrades();

		refresh();
	}

	public WeakeningStrike(Characterr character, int level) {
		this(character);
		this.level = level;
	}

	public WeakeningStrike(Characterr character, int level, boolean is1L, boolean is1R, boolean is2L, boolean is2R,
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

		if (FightController.trueOrFalse(chanceToCauseWeakness))
			defender.getStatus().addNewStatus(new Weakness(timeOfWeakness));

		if (isSkillActivated2L())
			if (FightController.trueOrFalse(chanceToCauseWeakness))
				defender.getStatus().addNewStatus(new Vulnerability(timeOfWeakness));

		if (isSkillActivated3R())
			defender.getStatus().addNewStatus(new Bleed(defender));
	}

	final int NEW_TIME_FOR_STATUSES = 30000;// 1R
	final double MULTIPLIER_FOR_DAMAGE = 0.2; // 3L

	@Override
	public void initializeThingsForImagesAndUpgrades() {
		pathSkillToImage = "res/skills/warrior/weakeningStrike.png";

		skillName1L = "Hit with Weakening Strike always cause Weakness.";
		skillName1R = "Weakness applied by Weakening Strike lasts for " + NEW_TIME_FOR_STATUSES / 1000
				+ " seconds now.";

		skillName2L = "Skill has also chance to cause vulnerability with same probability and duration as Weakness.";
		skillName2R = "Skill always hit as critical strike.";

		skillName3L = "Attack deal only " + MyStringFormatter.formatDoubleAsPercentage(MULTIPLIER_FOR_DAMAGE, 0)
				+ " but hit ALL enemies.";
		skillName3R = "Weakening Strike also causes the enemy to bleed.";

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

		if (isSkillActivated1L())
			chanceToCauseWeakness = 1.0;
		if (isSkillActivated1R())
			timeOfWeakness = NEW_TIME_FOR_STATUSES;

		int str = character.getAttributes().getAttribute(AttributesEnum.STRENGTH).getSum();

		// Add damage from strength and level
		minDamage += str * BOOSTS_FROM_STR_TO_MIN + sumDamageBoostFromLevel;
		maxDamage += str * BOOSTS_FROM_STR_TO_MAX + sumDamageBoostFromLevel;

		// Add damage from item if class is Player and weapon is melee
		if (Player.class.isAssignableFrom(character.getClass())) {
			Item item = ((Player) character).getEq().getEquipmentElement(EquipmentSlots.FIRST_HAND);
			if (item != null && ((WeaponBase) item.getItemBase()).getSkillType().equals(SkillType.MELEE)) {
				minDamage += ((WeaponBase) item.getItemBase()).getMinDamage();
				maxDamage += ((WeaponBase) item.getItemBase()).getMaxDamage();
				critChanceSum = ((WeaponBase) item.getItemBase()).getCritChance();
				critMultiplierSum = ((WeaponBase) item.getItemBase()).getCritMultiplier();
			}
		}

		if (isSkillActivated2R())
			critChanceSum = 1.0;

		if (isSkillActivated3L()) {
			minDamage = (int) (minDamage * MULTIPLIER_FOR_DAMAGE);
			maxDamage = (int) (maxDamage * MULTIPLIER_FOR_DAMAGE);
			skillRange = SkillRange.ALL;
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
		if (isSkillActivated3L())
			damageBoostFromLeveltp = damageBoostFromLeveltp * MULTIPLIER_FOR_DAMAGE;

		StringBuilder s = new StringBuilder();

		s.append("Hit with Weakening Strike can cause weakness with "
				+ MyStringFormatter.formatDoubleAsPercentage(chanceToCauseWeakness, 0) + " for " + timeOfWeakness / 1000
				+ " seconds." + "<br><br>");
		s.append("Level: " + level + "<br>");
		s.append("Cooldown: " + MyStringFormatter.formatDouble(cooldownActually / 1000.0, 2));
		if (level > 0)
			s.append("<font color=\"green\">(-"
					+ MyStringFormatter
							.formatDouble((cooldownActually - cooldownActually * attackSpeedBoostFromLevel) / 1000.0, 2)
					+ ")</font> ");
		s.append(" s<br><br>");

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

		if (isSkillActivated2L())
			s.append("Hit cause vulnerability with "
					+ MyStringFormatter.formatDoubleAsPercentage(chanceToCauseWeakness, 0) + " for "
					+ timeOfWeakness / 1000 + " seconds.<br>");

		if (isSkillActivated3R())
			s.append("Hit cause Bleeding.<br>");

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
