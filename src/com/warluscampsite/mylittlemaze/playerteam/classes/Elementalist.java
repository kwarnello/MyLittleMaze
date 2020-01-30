package com.warluscampsite.mylittlemaze.playerteam.classes;

import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.FightController;
import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;
import com.warluscampsite.mylittlemaze.controllers.MyTooltipFormatter;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.skills.SkillType;
import com.warluscampsite.mylittlemaze.skills.playerskills.ElementalMissile;
import com.warluscampsite.mylittlemaze.skills.playerskills.Nova;
import com.warluscampsite.mylittlemaze.skills.playerskills.WeaponEnchantment;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;
import com.warluscampsite.mylittlemaze.statistics.EquipmentSlots;
import com.warluscampsite.mylittlemaze.status.Chill;
import com.warluscampsite.mylittlemaze.status.Entangle;
import com.warluscampsite.mylittlemaze.status.Ignite;
import com.warluscampsite.mylittlemaze.status.Shock;

public class Elementalist extends ClassBase {

	Random random = new Random();

	public Elementalist() {
		super();
		name = "Elementalist";
		firstSubclassName = "Sorcerer";
		secondSubclassName = "Battlemage";

		strengthBase = 0;
		dexterityBase = 0;
		intelligenceBase = 6;
		speedBase = 0;
		vitalityBase = 0;
		wisdomBase = 3;
		luckBase = 1;

		initializePassives();
	}

	final double PROBABILITY_ADD_CHANCE_FOR_AILMENTS = 0.35;
	final double INCREASE_SPELL_DAMAGE_FOR_POINT_OF_ARMOR = 0.01;
	final double PROBABILITY_OF_MANA_THINGS = 0.5;
	final DamageTypes[] damageTypes = { DamageTypes.ICE, DamageTypes.FIRE, DamageTypes.EARTH, DamageTypes.LIGHTNING };
	final double LEECH_AILMENTS = 0.03;

	@Override
	public void initializePassives() {
		StringBuilder s = new StringBuilder();

		s.append("Health regen is add to mana regen and opposite.");
		passivesMap.put(1, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("Every weapon attack has "
				+ MyStringFormatter.formatDoubleAsPercentage(PROBABILITY_ADD_CHANCE_FOR_AILMENTS, 0)
				+ " to implict ailment based on elemental damage types.");
		passivesMap.put(2, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("Every point of armor increases magic damage by "
				+ MyStringFormatter.formatDoubleAsPercentage(INCREASE_SPELL_DAMAGE_FOR_POINT_OF_ARMOR, 1) + ".");
		passivesMap.put(3, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("Magic offensive skills have "
				+ MyStringFormatter.formatDoubleAsPercentage(PROBABILITY_OF_MANA_THINGS, 0)
				+ " chance to add damage equals to mana spent on this spell.");
		passivesMap.put(4, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("For every ailment on target of skill leech " + MyStringFormatter.formatDoubleAsPercentage(3, 1)
				+ " of elemental damage as mana and life.");
		passivesMap.put(5, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("If you wield two-handed melee weapon, add it critical chance to spells.");
		passivesMap.put(6, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));

		s.setLength(0);
		s.append("Vitality increases elemental resistance.");
		passivesMap.put(7, new Passive("res/skills/warrior/firstPassive.png",
				MyTooltipFormatter.formatTooltipForPassives(s.toString())));
	}

	@Override
	public void doPreRefreshStuff(Player player) {
	}

	@Override
	public void doDuringAttackStuff(Characterr attacker, Characterr defender, Skill skill,
			Map<DamageTypes, Double> damageMap, boolean criticalStrike) {

		if (passivesMap.get(2).isActivated()) {
			for (Entry<DamageTypes, Double> damage : damageMap.entrySet()) {
				if (FightController.trueOrFalse(PROBABILITY_ADD_CHANCE_FOR_AILMENTS))
					implictAilments(damage.getKey(), defender);
			}
		}

		if (passivesMap.get(3).isActivated()) {
			if (skill.getSkillType() == SkillType.MAGIC) {
				int armorBuff = 1 + (int) (attacker.getStatistics().getArmor().getTotal()
						* INCREASE_SPELL_DAMAGE_FOR_POINT_OF_ARMOR);

				for (Entry<DamageTypes, Double> damage : damageMap.entrySet()) {
					if (damage.getKey() == DamageTypes.FIRE || damage.getKey() == DamageTypes.ICE
							|| damage.getKey() == DamageTypes.LIGHTNING || damage.getKey() == DamageTypes.EARTH) {
						Double damageOld = damage.getValue() * armorBuff;
						damageMap.put(damage.getKey(), damageOld);
					}
				}
			}
		}

		if (passivesMap.get(4).isActivated()) {
			if (FightController.trueOrFalse(PROBABILITY_OF_MANA_THINGS)) {
				DamageTypes damageType = damageTypes[random.nextInt(damageTypes.length)];
				Double newDamage = damageMap.get(damageType) + skill.getCurrentMana();
				damageMap.put(damageType, newDamage);
			}
		}
		
		if (passivesMap.get(5).isActivated()) {
			int ailments = defender.getStatus().getStatusesMap().size();
			System.out.println(ailments);
			if (ailments > 0) {
				double leech = 0;
				double damage = 0;

				for (DamageTypes damageTypes2 : damageTypes)
					damage += damageMap.get(damageTypes2);

				leech = damage * LEECH_AILMENTS * ailments;

				attacker.getHealth().changeCurrentValue(leech);
				attacker.getMana().changeCurrentValue(leech);
			}
		}
	}

	@Override
	public void doPostRefreshStuff(Player player) {
		if (passivesMap.get(1).isActivated()) {
			double manaBoost = player.getMana().getManaRegenPerSecond();
			double healthBoost = player.getHealth().getHealthRegenPerSecond();

			player.getHealth().addBoostFromPassivesRegen(manaBoost);
			player.getMana().addBoostFromPassivesRegen(healthBoost);

			player.getHealth().refresh();
			player.getMana().refresh();
		}

		if (passivesMap.get(6).isActivated()) {
			Item item = player.getEq().getEquipmentElement(EquipmentSlots.FIRST_HAND);
			if (item != null && item.getItemBase().getItemType() == ItemTypes.TWO_HANDED_MELEE) {
				for (Skill skill : player.getSkills().getMapOfAttacks().values()) {
					if (skill.getSkillType() == SkillType.MAGIC) {
						skill.addCriticalChanceBuff(item.getItemBase().getCritChance());
						skill.addCriticalMultiplierBuff(item.getItemBase().getCritMultiplier());
					}
				}
			}
		}

		if (passivesMap.get(7).isActivated()) {
			for (DamageTypes damageTypes2 : damageTypes) {
				double str = player.getAttributes().getAttribute(AttributesEnum.VITALITY).getSum() / 100;
				player.getResistsMap().get(damageTypes2).addBoostsFromPassives(str);
			}
		}
	}

	@Override
	public LinkedList<Skill> getSkillsFromProffesion(Player player) {
		LinkedList<Skill> skillsFromProffesion = new LinkedList<>();

		skillsFromProffesion.add(new ElementalMissile(player));
		skillsFromProffesion.add(new Nova(player));
		skillsFromProffesion.add(new WeaponEnchantment(player));

		return skillsFromProffesion;
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
	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
