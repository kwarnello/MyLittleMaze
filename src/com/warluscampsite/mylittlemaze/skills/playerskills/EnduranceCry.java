package com.warluscampsite.mylittlemaze.skills.playerskills;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.controllers.MyTooltipFormatter;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.skills.SkillRange;
import com.warluscampsite.mylittlemaze.skills.SkillType;
import com.warluscampsite.mylittlemaze.statistics.Characterr;
import com.warluscampsite.mylittlemaze.status.HardenedSkin;
import com.warluscampsite.mylittlemaze.status.Status;

public class EnduranceCry extends Skill {

	/// boosts from level add more armor
	int totalBuffTime = 0;
	int buffTimeBasic = 15000, buffTimeForLevel = 250;
	int totalArmorBuff;
	final int basicArmorBuff = 20;
	int armorBuffForLevel = 5;

	public EnduranceCry(Characterr character) {
		super(character);

		name = "Endurance Cry";

		numberOfAttacks = 1;

		skillRange = SkillRange.SELF;
		skillType = SkillType.BUFF;
		damageTypes = DamageTypes.PHYSICAL;

		critChanceSum = 0.00;
		critMultiplierSum = 1.0;

		manaBase = 15;
		manaForLevel = 1;

		cooldownBase = 60000; // in milliseconds

		if (Player.class.isAssignableFrom(character.getClass()))
			initializeThingsForImagesAndUpgrades();

		refresh();
	}

	public EnduranceCry(Characterr character, int level) {
		this(character);
		this.level = level;
	}

	public EnduranceCry(Characterr character, int level, boolean is1L, boolean is1R, boolean is2L, boolean is2R,
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
		defender.getStatus().addNewStatus(new HardenedSkin(totalBuffTime, defender, totalArmorBuff));

		if (isSkillActivated1L()) {
			for (java.util.Map.Entry<String, Status> status : defender.getStatus().getStatusesMap().entrySet()) {
				if (!status.getValue().isItGood()) {
					defender.getStatus().removeStatus(status.getKey());
					break;
				}
			}
		}

		if (isSkillActivated2L()) {
			double amountOfHeal = defender.getHealth().getMaxValue() * PERCENTAGE_OF_HEAL;
			defender.getHealth().changeCurrentValue(amountOfHeal);
		}
	}

	final double PERCENTAGE_OF_HEAL = 0.05;

	@Override
	public void initializeThingsForImagesAndUpgrades() {
		pathSkillToImage = "res/skills/warrior/enduranceCry.png";

		skillName1L = "Endurance cry removes one random debuff from target.";
		skillName1R = "Double buffs from skill leveling.";

		skillName2L = "Endurance cry also heals for "
				+ MyStringFormatter.formatDoubleAsPercentage(PERCENTAGE_OF_HEAL, 0) + " of maximum life.";
		skillName2R = "Remove mana cost.";

		skillName3L = "Hardened skin from endurance cry now add five time more armor.";
		skillName3R = "Endurance cry now buffs all teammate but double the cooldown.";

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

		totalBuffTime = buffTimeBasic + buffTimeForLevel * level;
		totalArmorBuff = basicArmorBuff + armorBuffForLevel * level;

		if (isSkillActivated1R()) {
			totalBuffTime += buffTimeForLevel * level;
			totalArmorBuff += armorBuffForLevel * level;
		}

		if (isSkillActivated2R())
			currentMana = 0;

		if (isSkillActivated3L())
			totalArmorBuff = 5 * totalArmorBuff;

		if (isSkillActivated3R())
			skillRange = SkillRange.ALL;

		this.calculateCooldown();
	}

	@Override
	public void addLevel() {
		super.addLevel();

		this.refresh();
	}

	@Override
	public void calculateCooldown() {
		super.calculateCooldown();

		if (isSkillActivated3R())
			cooldownActually += cooldownActually;
	}

	@Override
	public String getSkillTooltip() {
		int tempArmorBuff = armorBuffForLevel;
		int tempCdRed = buffTimeForLevel;

		if (isSkillActivated1R()) {
			tempArmorBuff = tempArmorBuff * 2;
			tempCdRed = tempCdRed * 2;
		}

		StringBuilder s = new StringBuilder();

		s.append("Endurance cry buff caster with hardened skin (adds " + totalArmorBuff);
		if (level > 0)
			s.append(" <font color=\"green\">(+" + tempArmorBuff + ")</font> ");
		s.append(" armor) for " + MyStringFormatter.formatDouble(totalBuffTime / 1000., 2));
		if (level > 0)
			s.append(" <font color=\"green\">(+" + MyStringFormatter.formatDouble(tempCdRed / 1000., 2) + ")</font> ");
		s.append(" seconds." + "<br><br>");

		s.append("Level: " + level + "<br>");
		s.append("Cooldown: " + MyStringFormatter.formatDouble(cooldownActually / 1000.0, 2));
		s.append(" s" + "<br>");
		if (currentMana > 0) {
			s.append("Mana cost: " + currentMana);
			if (level > 0)
				s.append(" <font color=\"green\">(+" + manaForLevel + ")</font>");
		}
		s.append("<br><br>");

		if (isSkillActivated1L())
			s.append("Endurance cry removes one random debuff from target." + "<br>");
		if (isSkillActivated2L())
			s.append("Endurance cry also heal for " + MyStringFormatter.formatDoubleAsPercentage(PERCENTAGE_OF_HEAL, 0)
					+ " of maximum life. " + "<br>");
		if (isSkillActivated3R())
			s.append("Endurance cry buffs all teammate. " + "<br>");

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
