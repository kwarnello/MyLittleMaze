package com.warluscampsite.mylittlemaze.skills.playerskills;

import java.util.Map;
import java.util.Map.Entry;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.FightController;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.controllers.MyTooltipFormatter;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.WeaponBase;
import com.warluscampsite.mylittlemaze.monsters.Monster;
import com.warluscampsite.mylittlemaze.monsters.MonsterType;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.skills.SkillRange;
import com.warluscampsite.mylittlemaze.skills.SkillType;
import com.warluscampsite.mylittlemaze.statistics.Characterr;
import com.warluscampsite.mylittlemaze.statistics.EquipmentSlots;
import com.warluscampsite.mylittlemaze.status.Poison;
import com.warluscampsite.mylittlemaze.status.Slow;

public class RainOfArrows extends Skill {

	int minDamageForLevel = 1;
	int maxDamageForLevel = 2;

	/// boosts from level to damage and attack speed
	final double attackSpeedBoostFromLevel = 0.992;
	double sumAttackSpeedBoostFromLevel = 1;

	public RainOfArrows(Characterr character) {
		super(character);

		name = "Rain of arrows";

		baseForNumberOfAttacks = 1;

		skillRange = SkillRange.ALL;
		skillType = SkillType.RANGED;
		damageTypes = DamageTypes.PHYSICAL;

		critChanceSum = 0.05;
		critMultiplierSum = 2.0;

		manaBase = 5;
		manaForLevel = 1;

		cooldownBase = 30000; // in milliseconds

		if (Player.class.isAssignableFrom(character.getClass()))
			initializeThingsForImagesAndUpgrades();

		refresh();
	}

	public RainOfArrows(Characterr character, int level) {
		this(character);
		this.level = level;
	}

	public RainOfArrows(Characterr character, int level, boolean is1L, boolean is1R, boolean is2L, boolean is2R,
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

		if (isSkillActivated1L()) {
			if (Monster.class.isAssignableFrom(defender.getClass())) {
				if (((Monster) defender).getMonsterType() == FIRST_TYPE
						|| ((Monster) defender).getMonsterType() == SECOND_TYPE) {
					for (Entry<DamageTypes, Double> damageInMap : damageMap.entrySet()) {
						double damageBuff = 2 * damageInMap.getValue();
						damageMap.put(damageInMap.getKey(), damageBuff);
					}
				}
			}
		}

		if (isSkillActivated1R()) {
			double damageBuff = damageMap.get(DamageTypes.LIGHTNING)
					+ damageMap.get(DamageTypes.PHYSICAL) * LIGHTNING_DAMAGE_BUFF;
			damageMap.put(DamageTypes.LIGHTNING, damageBuff);
		}

		if (isSkillActivated2L())
			if (FightController.trueOrFalse(SLOW_PROBABILITY))
				defender.getStatus().addNewStatus(new Slow());

		if (isSkillActivated3L()) {
			defender.getStatus().addNewStatus(new Poison(defender, damageMap.get(DamageTypes.PHYSICAL)));
		}

		if (isSkillActivated3R()) {
			double damageBuff = damageMap.get(DamageTypes.LEECH) + damageMap.get(DamageTypes.PHYSICAL) * LEECH;
			damageMap.put(DamageTypes.LEECH, damageBuff);
		}

	}

	final MonsterType FIRST_TYPE = MonsterType.ANIMAL;
	final MonsterType SECOND_TYPE = MonsterType.HUMANOID;

	final double LIGHTNING_DAMAGE_BUFF = 0.4;

	final double SLOW_PROBABILITY = 0.25;

	final double LEECH = 0.2;

	@Override
	public void initializeThingsForImagesAndUpgrades() {
		pathSkillToImage = "res/skills/archer/rainOfArrows.png";

		skillName1L = "Double damage against humanoids and animals.";
		skillName1R = "Add " + MyStringFormatter.formatDoubleAsPercentage(LIGHTNING_DAMAGE_BUFF, 0)
				+ " of physical damage as lightning damage.";

		skillName2L = "Rain of arrows has " + MyStringFormatter.formatDoubleAsPercentage(SLOW_PROBABILITY, 0)
				+ " chance for slowing targets for 5 seconds.";
		skillName2R = "Double cooldaown reduction for level.";

		skillName3L = "Arrows always poison targets.";
		skillName3R = "If rain of arrows hit with critical strike leech "
				+ MyStringFormatter.formatDoubleAsPercentage(LEECH, 0) + " physical damage as life.";

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
			minDamage += minDamageForLevel * level;
			maxDamage += maxDamageForLevel * level;

			// Add damage from item if class is Player and weapon is ranged
			if (item != null && ((WeaponBase) item.getItemBase()).getSkillType().equals(SkillType.RANGED)) {
				minDamage += ((WeaponBase) item.getItemBase()).getMinDamage();
				maxDamage += ((WeaponBase) item.getItemBase()).getMaxDamage();
				critChanceSum = ((WeaponBase) item.getItemBase()).getCritChance();
				critMultiplierSum = ((WeaponBase) item.getItemBase()).getCritMultiplier();
			}
		}

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

		double attackSpeedBoost = 0;
		if (isSkillActivated1R())
			attackSpeedBoost = sumAttackSpeedBoostFromLevel * sumAttackSpeedBoostFromLevel;
		else
			attackSpeedBoost = sumAttackSpeedBoostFromLevel;

		cooldownActually = (int) (cooldownActually * attackSpeedBoost);
	}

	@Override
	public String getSkillTooltip() {
		double damageMinBoostFromLevel = minDamageForLevel;
		double damageMaxBoostFromLevel = maxDamageForLevel;
		int newInterval;
		if (isSkillActivated1R())
			newInterval = (int) (cooldownActually * attackSpeedBoostFromLevel * attackSpeedBoostFromLevel);
		else
			newInterval = (int) (cooldownActually * attackSpeedBoostFromLevel);

		StringBuilder s = new StringBuilder();

		s.append("Rain of arrows hit all enemies." + "<br><br>");
		s.append("Level: " + level + "<br>");
		s.append("Cooldown: " + MyStringFormatter.formatDouble(cooldownActually / 1000.0, 2));
		if (level > 0)
			s.append("<font color=\"green\">(-"
					+ MyStringFormatter.formatDouble((cooldownActually - newInterval) / 1000.0, 2) + ")</font> ");
		s.append("<br>");
		if (currentMana > 0) {
			s.append("Mana cost: " + currentMana);
			if (level > 0)
				s.append(" <font color=\"green\">(+" + manaForLevel + ")</font>");
		}
		s.append("s<br><br>");

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

		if (isSkillActivated2L())
			s.append("Double damage against humanoids and animals.");

		if (isSkillActivated2L())
			s.append(MyStringFormatter.formatDoubleAsPercentage(SLOW_PROBABILITY, 0)
					+ " chance for slowing targets for 5 seconds.");

		if (isSkillActivated3L())
			s.append("Hit causes poison.");

		if (isSkillActivated3R())
			s.append("Leech " + MyStringFormatter.formatDoubleAsPercentage(LEECH, 0)
					+ " physical damage as life if critical strike.");
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
