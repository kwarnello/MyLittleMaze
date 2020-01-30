package com.warluscampsite.mylittlemaze.playerteam;

import java.util.List;
import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.controllers.FightController;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.playerteam.classes.ClassBase;
import com.warluscampsite.mylittlemaze.skills.Skill;
import com.warluscampsite.mylittlemaze.skills.WeaponAttack;
import com.warluscampsite.mylittlemaze.statistics.Attributes;
import com.warluscampsite.mylittlemaze.statistics.CharacterSkillsList;
import com.warluscampsite.mylittlemaze.statistics.CharacterStatuses;
import com.warluscampsite.mylittlemaze.statistics.Characterr;
import com.warluscampsite.mylittlemaze.statistics.Equipment;
import com.warluscampsite.mylittlemaze.statistics.Experience;
import com.warluscampsite.mylittlemaze.statistics.Health;
import com.warluscampsite.mylittlemaze.statistics.Mana;
import com.warluscampsite.mylittlemaze.statistics.Statistics;

public class Player extends Characterr {

	PlayerParty playerParty;

	Experience experience;
	Equipment eq;

	ClassBase proffesion;

	public Player(PlayerParty playerParty, String name, ClassBase proffesion) {
		super();

		this.playerParty = playerParty;

		this.name = name;
		this.proffesion = proffesion;

		experience = new Experience(this);
		attributes = new Attributes(this);
		statistics = new Statistics(this);
		health = new Health(this);
		mana = new Mana(this);

		eq = new Equipment(this);

		skills = new CharacterSkillsList(this);

		status = new CharacterStatuses(this);

		resistsMap.get(DamageTypes.PHYSICAL)
				.setItemValue(FightController.countArmorReduction(statistics.getArmor().getTotal()));
		resistsMap.forEach((k, v) -> v.countSum());

		addSkillsAndAttack();
	}

	private void addSkillsAndAttack() {
		skills.addAttack(new WeaponAttack(this));

		List<Skill> skillsFromProffesion = getProffesion().getSkillsFromProffesion(this);
		Integer i = 0;
		for (Skill skill : skillsFromProffesion) {
			i++;
			skills.addAttack(skill, true, i);
		}
	}

	public void refreshCharacter() {
		resetBoostsBeforeRefresh();

		proffesion.doPreRefreshStuff(this);

		resistsMap.forEach((k, v) -> v.refresh());
		eq.refresh();
		attributes.refresh();
		statistics.refresh();
		health.refresh();
		mana.refresh();
		skills.refresh();

		proffesion.doPostRefreshStuff(this);

		resistsMap.get(DamageTypes.PHYSICAL)
				.setItemValue(FightController.countArmorReduction(statistics.getArmor().getTotal()));
		resistsMap.forEach((k, v) -> v.countSum());
	}

	private void resetBoostsBeforeRefresh() {
		skills.resetBoostsBeforeRefresh();
		statistics.resetBoostsBeforeRefresh();
		attributes.resetBoostsBeforeRefresh();
		health.resetBoostsBeforeRefresh();
		mana.resetBoostsBeforeRefresh();
		block.resetBlockBeforeRefresh();
		status.resetOptionsBeforeRefresh();
	}

	public void doAttack(Characterr attacker, Characterr defender, Skill skill, Map<DamageTypes, Double> damageMap,
			boolean criticalStrike) {
		Player player = (Player) attacker;

		skill.doAttack(player, defender, damageMap, criticalStrike);

		for (Item equipeddElement : eq.getEquipmentMap().values()) {
			if (equipeddElement != null)
				equipeddElement.doAttack(player, defender, skill, damageMap);
		}

		getProffesion().doDuringAttackStuff(attacker, defender, skill, damageMap, criticalStrike);
	}

	@Override
	public void changePlace(int newPlace) {
		this.setPlace(newPlace);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public Experience getExperience() {
		return experience;
	}

	public ClassBase getProffesion() {
		return proffesion;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public PlayerParty getPlayerParty() {
		return playerParty;
	}

	public Equipment getEq() {
		return eq;
	}

}
