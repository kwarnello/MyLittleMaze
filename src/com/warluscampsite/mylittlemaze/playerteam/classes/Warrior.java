package com.warluscampsite.mylittlemaze.playerteam.classes;

import java.util.LinkedList;
import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.FightController;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.controllers.MyTooltipFormatter;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.skills.SkillType;
import com.warluscampsite.mylittlemaze.skills.playerskills.Cleave;
import com.warluscampsite.mylittlemaze.skills.playerskills.EnduranceCry;
import com.warluscampsite.mylittlemaze.skills.playerskills.WeakeningStrike;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;
import com.warluscampsite.mylittlemaze.statistics.EquipmentSlots;
import com.warluscampsite.mylittlemaze.status.Bleed;

public class Warrior extends ClassBase {

	public Warrior() {
		super();
		name = "Warrior";
		firstSubclassName = "Marauder";
		secondSubclassName = "Sentinel";

		strengthBase = 4;
		dexterityBase = 2;
		intelligenceBase = 0;
		speedBase = 1;
		vitalityBase = 3;
		wisdomBase = 0;
		luckBase = 100;

		initializePassives();
	}

	final double BUFF_FOR_CRITICAL_MULTIPLIER = 0.05;
	final double BUFF_FOR_VITALITY_MULTIPLIER = 0.05;
	final double CHANCE_OF_BLEED_AT_CRITICAL = 0.25;
	final int ADDITIONAL_ATTACKS = 1;
	final double BUFF_FOR_MAXIMUM_RESISTANCES = 0.10;

	@Override
	public void initializePassives() {
		StringBuilder s = new StringBuilder();

		s.append("Double armor value from body armor.");
		passivesMap.put(1, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("If you wield two-handed weapon you have extra "
				+ MyStringFormatter.formatDoubleAsPercentage(BUFF_FOR_CRITICAL_MULTIPLIER, 0)
				+ " critical multiplier for every level of character.");
		passivesMap.put(2, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("If you have shield equipped "
				+ MyStringFormatter.formatDoubleAsPercentage(BUFF_FOR_VITALITY_MULTIPLIER, 0)
				+ " of armor value is add as vitality.");
		passivesMap.put(3, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("Your critical strikes have also "
				+ MyStringFormatter.formatDoubleAsPercentage(CHANCE_OF_BLEED_AT_CRITICAL, 0) + " chance to bleed.");
		passivesMap.put(4, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("If you have only medium (or lighter) items you have " + ADDITIONAL_ATTACKS
				+ " more weapon melee attack (also for Cleave and Weakening Strike).");
		passivesMap.put(5, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("When you have shield equipped your maximum elemental and physical resistances are "
				+ MyStringFormatter.formatDoubleAsPercentage(BUFF_FOR_MAXIMUM_RESISTANCES, 0) + " higher.");
		passivesMap.put(6, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("You can also block spell damage.");
		passivesMap.put(7, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));
	}

	@Override
	public void doPreRefreshStuff(Player player) {
		if (passivesMap.get(1).isActivated()) {
			Item item = player.getEq().getEquipmentElement(EquipmentSlots.BODY_ARMOR);
			if (item != null)
				player.getStatistics().getArmor().addBoostsFromPassives(item.getItemBase().getArmor());
		}

		if (passivesMap.get(3).isActivated()) {
			Item item = player.getEq().getEquipmentElement(EquipmentSlots.SECOND_HAND);
			if (item != null && item.getItemBase().getItemType().equals(ItemTypes.SHIELD)) {
				int vitalityBoost = (int) (item.getItemBase().getArmor() * BUFF_FOR_VITALITY_MULTIPLIER);

				player.getAttributes().getAttribute(AttributesEnum.VITALITY).addFromProfession(vitalityBoost);
			}
		}

		if (passivesMap.get(5).isActivated()) {
			if (player.getEq().isMaxIsMed())
				for (Skill skill : player.getSkills().getMapOfAttacks().values()) {
					if (skill.getSkillType().equals(SkillType.MELEE))
						skill.addNumberOfAttacksFromBuffs(ADDITIONAL_ATTACKS);
				}
		}

		if (passivesMap.get(6).isActivated()) {
			Item item = player.getEq().getEquipmentElement(EquipmentSlots.SECOND_HAND);
			if (item != null && item.getItemBase().getItemType().equals(ItemTypes.SHIELD)) {
				player.getResist(DamageTypes.PHYSICAL).addMaxValueBuffs(BUFF_FOR_MAXIMUM_RESISTANCES);
				player.getResist(DamageTypes.FIRE).addMaxValueBuffs(BUFF_FOR_MAXIMUM_RESISTANCES);
				player.getResist(DamageTypes.ICE).addMaxValueBuffs(BUFF_FOR_MAXIMUM_RESISTANCES);
				player.getResist(DamageTypes.LIGHTNING).addMaxValueBuffs(BUFF_FOR_MAXIMUM_RESISTANCES);
				player.getResist(DamageTypes.EARTH).addMaxValueBuffs(BUFF_FOR_MAXIMUM_RESISTANCES);
			}
		}

		if (passivesMap.get(7).isActivated())
			player.getBlock().setCanBlockSpells(true);
	}

	@Override
	public void doPostRefreshStuff(Player player) {
		if (passivesMap.get(2).isActivated()) {
			Item item = player.getEq().getEquipmentElement(EquipmentSlots.FIRST_HAND);
			if (item != null && item.getItemBase().getItemType().equals(ItemTypes.TWO_HANDED_MELEE))
				player.getSkills().getMapOfAttacks().forEach((k, v) -> v
						.addCritMultiplier(BUFF_FOR_CRITICAL_MULTIPLIER * player.getExperience().getLevel()));
		}

	}

	public void doDuringAttackStuff(Characterr attacker, Characterr defender, Skill skill,
			Map<DamageTypes, Double> damageMap, boolean criticalStrike) {
		if (passivesMap.get(4).isActivated()) {
			if (criticalStrike)
				if (FightController.trueOrFalse(CHANCE_OF_BLEED_AT_CRITICAL))
					defender.getStatus().addNewStatus(new Bleed(defender));
		}
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	@Override
	public LinkedList<Skill> getSkillsFromProffesion(Player player) {
		LinkedList<Skill> skillsFromProffesion = new LinkedList<>();
		skillsFromProffesion.add(new Cleave(player));
		skillsFromProffesion.add(new WeakeningStrike(player));
		skillsFromProffesion.add(new EnduranceCry(player));

		return skillsFromProffesion;
	}

}
