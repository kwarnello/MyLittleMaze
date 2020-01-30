package com.warluscampsite.mylittlemaze.playerteam.classes;

import java.util.LinkedList;
import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.FightController;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.controllers.MyTooltipFormatter;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.WeaponBase;
import com.warluscampsite.mylittlemaze.monsters.Monster;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.skills.SkillRange;
import com.warluscampsite.mylittlemaze.skills.SkillType;
import com.warluscampsite.mylittlemaze.skills.playerskills.ArrowInTheKnee;
import com.warluscampsite.mylittlemaze.skills.playerskills.Barrage;
import com.warluscampsite.mylittlemaze.skills.playerskills.RainOfArrows;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;
import com.warluscampsite.mylittlemaze.statistics.EquipmentSlots;
import com.warluscampsite.mylittlemaze.status.Bleed;
import com.warluscampsite.mylittlemaze.status.Poison;
import com.warluscampsite.mylittlemaze.status.Status;
import com.warluscampsite.mylittlemaze.status.Vulnerability;

public class Archer extends ClassBase {

	public Archer() {
		super();
		name = "Archer";
		firstSubclassName = "Marksman";
		secondSubclassName = "Renegade";

		strengthBase = 0;
		dexterityBase = 4;
		intelligenceBase = 0;
		speedBase = 4;
		vitalityBase = 1;
		wisdomBase = 0;
		luckBase = 1;

		initializePassives();
	}

	final double APS_INCREASE = 0.3;
	final double BLEED_CHANCE = 0.15;

	final double MULTIPLIER_FROM_LUCK = 0.02;

	final double VULNERABILITY_CHANCE = 0.15;

	final double POISON_CHANCE = 0.20;
	final double RESTORE_HP_IF_TARGET_IS_POISONED = 10;

	@Override
	public void initializePassives() {
		StringBuilder s = new StringBuilder();

		s.append("The archer attacks " + MyStringFormatter.formatDoubleAsPercentage(APS_INCREASE, 0)
				+ " faster with ranged weapon.");
		passivesMap.put(1, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append(
				"Your arrows can now pierce target and hit two enemies in line with attack (only for default ranged weapon attack).");
		passivesMap.put(2, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("Your arrows can bleed with " + MyStringFormatter.formatDoubleAsPercentage(BLEED_CHANCE, 0)
				+ " of chance. You are also bleed immune.");
		passivesMap.put(3, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("Your speed and luck increases multiplier of critical strikes (one point of luck/speed add "
				+ MyStringFormatter.formatDoubleAsPercentage(MULTIPLIER_FROM_LUCK, 0) + ").");
		passivesMap.put(4, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("Your critical strikes causes vulnerability at target with "
				+ MyStringFormatter.formatDoubleAsPercentage(VULNERABILITY_CHANCE, 0) + ".");
		passivesMap.put(5, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("If you kill an enemy with your arrow bleed and posions from targer spread to other enemies.");
		passivesMap.put(6, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("Poison an enemy with " + MyStringFormatter.formatDoubleAsPercentage(POISON_CHANCE, 0)
				+ " chance. If you attack a poisoned enemy restore "
				+ MyStringFormatter.formatDouble(RESTORE_HP_IF_TARGET_IS_POISONED, 0)
				+ " health points for each poison on target.");
		passivesMap.put(7, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));
	}

	@Override
	public void doPreRefreshStuff(Player player) {
		if (passivesMap.get(3).isActivated())
			player.getStatus().setBleedImmune(true);

	}

	@Override
	public void doPostRefreshStuff(Player player) {
		Item item = player.getEq().getEquipmentElement(EquipmentSlots.FIRST_HAND);
		if (item != null && ((WeaponBase) item.getItemBase()).getSkillType().equals(SkillType.RANGED)) {

			if (passivesMap.get(1).isActivated()) {
				player.getSkills().getMapOfAttacks().get("weapon").multiplyCooldown(APS_INCREASE);
			}

			if (passivesMap.get(2).isActivated()) {
				player.getSkills().getMapOfAttacks().get("weapon").setSkillRange(SkillRange.LINE);
			}
			if (passivesMap.get(4).isActivated()) {
				double luckBuff = (player.getAttributes().getAttribute(AttributesEnum.LUCK).getSum()
						+ player.getAttributes().getAttribute(AttributesEnum.SPEED).getSum()) * MULTIPLIER_FROM_LUCK;
				player.getSkills().getMapOfAttacks().forEach((k, v) -> v.addCritMultiplier(luckBuff));
			}
		}

	}

	@Override
	public void doDuringAttackStuff(Characterr attacker, Characterr defender, Skill skill,
			Map<DamageTypes, Double> damageMap, boolean criticalStrike) {
		super.doDuringAttackStuff(attacker, defender, skill, damageMap, criticalStrike);
		Item item = ((Player) attacker).getEq().getEquipmentElement(EquipmentSlots.FIRST_HAND);
		if (item != null && ((WeaponBase) item.getItemBase()).getSkillType().equals(SkillType.RANGED)) {

			if (passivesMap.get(3).isActivated()) {
				if (FightController.trueOrFalse(BLEED_CHANCE))
					defender.getStatus().addNewStatus(new Bleed(defender));
			}

			if (passivesMap.get(5).isActivated()) {
				if (criticalStrike)
					if (FightController.trueOrFalse(VULNERABILITY_CHANCE))
						defender.getStatus().addNewStatus(new Vulnerability(10000));
			}

			if (passivesMap.get(6).isActivated()) {
				if (item != null && ((WeaponBase) item.getItemBase()).getSkillType().equals(SkillType.RANGED)) {
					double damage = 0;
					for (Double value : damageMap.values())
						damage += value;

					if (damage >= defender.getHealth().getCurrentValue()) {
						if (defender.getStatus().checkIfStatusExist("Poison")) {
							Status poison = defender.getStatus().getStatus("Poison");

							for (Monster monster : ((Player) attacker).getPlayerParty().getData().getMaze()
									.getMonstersParty().getPlaceMap().values()) {
								if (!monster.isDead())
									monster.getStatus().addNewStatus(poison);
							}
						}
						if (defender.getStatus().checkIfStatusExist("Bleed")) {
							Status bleed = defender.getStatus().getStatus("Bleed");

							for (Monster monster : ((Player) attacker).getPlayerParty().getData().getMaze()
									.getMonstersParty().getPlaceMap().values()) {
								if (!monster.isDead())
									monster.getStatus().addNewStatus(bleed);
							}
						}
					}
				}
			}

			if (passivesMap.get(7).isActivated()) {
				if (FightController.trueOrFalse(POISON_CHANCE))
					defender.getStatus().addNewStatus(new Poison(defender, damageMap.get(DamageTypes.PHYSICAL)));
				if (defender.getStatus().checkIfStatusExist("Poison")) {
					double hpToRegen = ((Poison) defender.getStatus().getStatus("Poison")).getPoisonCounter()
							* RESTORE_HP_IF_TARGET_IS_POISONED;
					attacker.getHealth().changeCurrentValue(hpToRegen);
				}
			}
		}
	}

	@Override
	public LinkedList<Skill> getSkillsFromProffesion(Player player) {
		LinkedList<Skill> skillsFromProffesion = new LinkedList<>();

		skillsFromProffesion.add(new Barrage(player));
		skillsFromProffesion.add(new RainOfArrows(player));
		skillsFromProffesion.add(new ArrowInTheKnee(player));

		return skillsFromProffesion;
	}
	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
