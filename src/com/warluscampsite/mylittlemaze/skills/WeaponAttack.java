package com.warluscampsite.mylittlemaze.skills;

import java.util.Map;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;
import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.WeaponBase;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.statistics.AttributesEnum;
import com.warluscampsite.mylittlemaze.statistics.Characterr;
import com.warluscampsite.mylittlemaze.statistics.EquipmentSlots;
import com.warluscampsite.mylittlemaze.status.WeaponEnchantment;

public class WeaponAttack extends Skill {

	public WeaponAttack(Characterr character) {
		super(character);

		name = "weapon";

		numberOfAttacks = 1;

		level = 1;

		skillRange = SkillRange.SINGLE_ONE_ROW;
		skillType = SkillType.MELEE;
		damageTypes = DamageTypes.PHYSICAL;

		cooldownBase = 3000; // in milliseconds

		refresh();
	}

	public void doAttack(Characterr attacker, Characterr defender, Map<DamageTypes, Double> damageMap,
			boolean criticalStrike) {
		double damage = getRandomDamage();
		damageMap.put(getDamageTypes(), damage);

		if (attacker.getStatus().checkIfStatusExist("Weapon Enchantment"))
			((WeaponEnchantment) attacker.getStatus().getStatus("Weapon Enchantment")).doBuff(damageMap);
	}

	final int BOOSTS_FROM_INT_ONE_HANDED_MAGIC = 2, BOOSTS_FROM_INT_TWO_HANDED_MAGIC = 3;
	final int BOOSTS_FROM_STR_ONE_HANDED_MELEE = 2, BOOSTS_FROM_STR_TWO_HANDED_MELEE = 4;
	final int BOOSTS_FROM_STR_ONE_HANDED_RANGED = 1, BOOSTS_FROM_STR_TWO_HANDED_RANGED = 2;

	@SuppressWarnings("incomplete-switch")
	@Override
	public void refresh() {
		super.refresh();

		Item item = ((Player) character).getEq().getEquipmentElement(EquipmentSlots.FIRST_HAND);
		int str = character.getAttributes().getAttribute(AttributesEnum.STRENGTH).getSum();
		int inte = character.getAttributes().getAttribute(AttributesEnum.INTELLIGENCE).getSum();

		if (item == null) {
			skillRange = SkillRange.SINGLE_ONE_ROW;
			skillType = SkillType.MELEE;
			damageTypes = DamageTypes.PHYSICAL;

			numberOfAttacks = 1;
			minDamage = str * BOOSTS_FROM_STR_ONE_HANDED_MELEE;
			maxDamage = 2 * str * BOOSTS_FROM_STR_ONE_HANDED_MELEE;
			critChanceSum = 0.03;
			critMultiplierSum = 1.5;
			cooldownBase = 3000;
			this.calculateCooldown();
			return;
		}

		skillRange = ((WeaponBase) item.getItemBase()).getWeaponRange();
		skillType = ((WeaponBase) item.getItemBase()).getSkillType();
		damageTypes = ((WeaponBase) item.getItemBase()).getDamageTypes();
		numberOfAttacks = item.getItemBase().getNumberOfAttacks();
		cooldownBase = item.getItemBase().getAttackDelay();
		critChanceSum = item.getItemBase().getCritChance();
		critMultiplierSum = item.getItemBase().getCritMultiplier();

		switch (item.getItemBase().getItemType()) {
		case ONE_HANDED_MAGIC:
			minDamage = item.getItemBase().getMinDamage() + inte * BOOSTS_FROM_INT_ONE_HANDED_MAGIC;
			maxDamage = item.getItemBase().getMaxDamage() + inte * BOOSTS_FROM_INT_ONE_HANDED_MAGIC;
			break;

		case ONE_HANDED_MELEE:
			minDamage = item.getItemBase().getMinDamage() + str * BOOSTS_FROM_STR_ONE_HANDED_MELEE;
			maxDamage = item.getItemBase().getMaxDamage() + str * BOOSTS_FROM_STR_ONE_HANDED_MELEE;
			break;

		case ONE_HANDED_RANGED:
			minDamage = item.getItemBase().getMinDamage() + str * BOOSTS_FROM_STR_ONE_HANDED_RANGED;
			maxDamage = item.getItemBase().getMaxDamage() + str * BOOSTS_FROM_STR_ONE_HANDED_RANGED;
			break;

		case TWO_HANDED_MAGIC:
			minDamage = item.getItemBase().getMinDamage() + inte * BOOSTS_FROM_INT_TWO_HANDED_MAGIC;
			maxDamage = item.getItemBase().getMaxDamage() + inte * BOOSTS_FROM_INT_TWO_HANDED_MAGIC;
			break;

		case TWO_HANDED_MELEE:
			minDamage = item.getItemBase().getMinDamage() + str * BOOSTS_FROM_STR_TWO_HANDED_MELEE;
			maxDamage = item.getItemBase().getMaxDamage() + str * BOOSTS_FROM_STR_TWO_HANDED_MELEE;
			break;

		case TWO_HANDED_RANGED:
			minDamage = item.getItemBase().getMinDamage() + str * BOOSTS_FROM_STR_TWO_HANDED_RANGED;
			maxDamage = item.getItemBase().getMaxDamage() + str * BOOSTS_FROM_STR_TWO_HANDED_RANGED;
			break;
		}

		this.calculateCooldown();
	}

	@Override
	public String getSkillTooltip() {
		return null;
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

}
