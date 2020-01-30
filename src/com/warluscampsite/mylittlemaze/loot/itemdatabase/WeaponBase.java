package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.skills.SkillRange;
import com.warluscampsite.mylittlemaze.skills.SkillType;

public abstract class WeaponBase extends ItemBase {

	SkillRange weaponRange;
	SkillType skillType;
	DamageTypes damageTypes;

	@SuppressWarnings("incomplete-switch")
	public WeaponBase(ItemTypes itemType) {
		super(itemType);

		damageTypes = DamageTypes.PHYSICAL;

		switch (itemType) {
		case ONE_HANDED_MAGIC:
			weaponRange = SkillRange.SINGLE_ANY;
			skillType = SkillType.MAGIC;
			break;
		case ONE_HANDED_MELEE:
			weaponRange = SkillRange.SINGLE_ONE_ROW;
			skillType = SkillType.MELEE;
			break;
		case ONE_HANDED_RANGED:
			weaponRange = SkillRange.SINGLE_ANY;
			skillType = SkillType.RANGED;
			break;
		case TWO_HANDED_MAGIC:
			weaponRange = SkillRange.SINGLE_ANY;
			skillType = SkillType.MAGIC;
			break;
		case TWO_HANDED_MELEE:
			weaponRange = SkillRange.SINGLE_ONE_ROW;
			skillType = SkillType.MELEE;
			break;
		case TWO_HANDED_RANGED:
			weaponRange = SkillRange.SINGLE_ANY;
			skillType = SkillType.RANGED;
			break;

		}
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public SkillRange getWeaponRange() {
		return weaponRange;
	}

	public void setWeaponRange(SkillRange weaponRange) {
		this.weaponRange = weaponRange;
	}

	public SkillType getSkillType() {
		return skillType;
	}

	public void setSkillType(SkillType skillType) {
		this.skillType = skillType;
	}

	public DamageTypes getDamageTypes() {
		return damageTypes;
	}

}
