package com.warluscampsite.mylittlemaze.statistics;

import java.util.HashMap;
import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.skills.Skill;

public abstract class Characterr {

	protected String name;
	protected int place;

	protected boolean isDead = false;

	protected Attributes attributes;
	protected Statistics statistics;
	protected Health health;
	protected Mana mana;
	protected Block block;

	protected CharacterSkillsList skills;

	protected CharacterStatuses status;

	protected Map<DamageTypes, Resist> resistsMap;

	public Characterr() {
		resistsMap = new HashMap<>();
		block = new Block();

		for (DamageTypes damage : DamageTypes.values()) {
			resistsMap.put(damage, new Resist());
		}
	}

	public void refreshCharacter() {
		attributes.refresh();
		statistics.refresh();
		health.refresh();
		mana.refresh();
	}

	public void addTimeFlow(int milis) {
		status.addTimeFlow(milis);

		if (getStatus().checkIfStatusExist("Chill"))
			milis = milis / 2;

		skills.addTimeFlow(milis);
	}

	public abstract void changePlace(int newPlace);

	public abstract void doAttack(Characterr attacker, Characterr defender, Skill skill,
			Map<DamageTypes, Double> damageMap, boolean criticalStrike);

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public String getName() {
		return name;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public Health getHealth() {
		return health;
	}

	public Mana getMana() {
		return mana;
	}

	public CharacterSkillsList getSkills() {
		return skills;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public int getPlace() {
		return place;
	}

	public Resist getResist(DamageTypes type) {
		return resistsMap.get(type);
	}

	public CharacterStatuses getStatus() {
		return status;
	}

	public Block getBlock() {
		return block;
	}

	public Map<DamageTypes, Resist> getResistsMap() {
		return resistsMap;
	}

}