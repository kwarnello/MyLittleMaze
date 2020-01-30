package com.warluscampsite.mylittlemaze.monsters;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.FightController;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.skills.SkillType;
import com.warluscampsite.mylittlemaze.statistics.Attributes;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.CharacterSkillsList;
import com.warluscampsite.mylittlemaze.statistics.CharacterStatuses;
import com.warluscampsite.mylittlemaze.statistics.Characterr;
import com.warluscampsite.mylittlemaze.statistics.Health;
import com.warluscampsite.mylittlemaze.statistics.Mana;
import com.warluscampsite.mylittlemaze.statistics.Statistics;
import com.warluscampsite.mylittlemaze.status.WeaponEnchantment;

public abstract class Monster extends Characterr {

	int mazeLevel;

	MonsterType monsterType;
	int strengthBase, dexterityBase, intelligenceBase, speedBase, vitalityBase, wisdomBase, luckBase;

	public Monster(int mazeLevel) {
		super();

		this.mazeLevel = mazeLevel;

		createAttributes();

		attributes = new Attributes(this);
		statistics = new Statistics(this);
		health = new Health(this);
		mana = new Mana(this);

		skills = new CharacterSkillsList(this);

		status = new CharacterStatuses(this);

		doBoostAfterInitializeMonster();
	}

	protected void addSkills(Skill skill) {
		skills.addAttack(skill);
	};

	public void doBoostAfterInitializeMonster() {
		resistsMap.get(DamageTypes.PHYSICAL)
				.setItemValue(FightController.countArmorReduction(statistics.getArmor().getTotal()));
		resistsMap.forEach((k, v) -> v.countSum());
	}

	final double POWER_FOR_BOOST = 0.75;

	public abstract void createAttributes();

	public void doAttack(Characterr attacker, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap,
			boolean criticalStrike) {
		skill.doAttack(attacker, defender, damageMap, criticalStrike);

		if (attacker.getStatus().checkIfStatusExist("Weapon Enchantment"))
			if (skill.getSkillType() == SkillType.MELEE || skill.getSkillType() == SkillType.RANGED)
				((WeaponEnchantment) attacker.getStatus().getStatus("Weapon Enchantment")).doBuff(damageMap);
	};

	public int getBasicStatsOfProffesion(AttributesEnum attribute) {
		switch (attribute) {
		case STRENGTH:
			return strengthBase;
		case DEXTERITY:
			return dexterityBase;
		case INTELLIGENCE:
			return intelligenceBase;
		case SPEED:
			return speedBase;
		case VITALITY:
			return vitalityBase;
		case WISDOM:
			return wisdomBase;
		case LUCK:
			return luckBase;
		default:
			return 5;
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
	public void changePlace(int newPlace) {

	}

	public MonsterType getMonsterType() {
		return monsterType;
	}

	public void setMazeLevel(int mazeLevel) {
		this.mazeLevel = mazeLevel;
	}

}
