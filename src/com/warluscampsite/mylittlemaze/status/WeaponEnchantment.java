package com.warluscampsite.mylittlemaze.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.warluscampsite.mylittlemaze.battle.DamageTypes;

public class WeaponEnchantment extends Status {

	Random random = new Random();

	final static int DEFAULT_STATUS_TIME = 10000;

	List<DamageTypes> damageTypes;
	int minDamage, maxDamage;

	// Add armor points and multipliers
	// Check at BattleAttackController in doAttackStuff

	public WeaponEnchantment(List<DamageTypes> damageTypes, int timeOfStatus, int minDamage, int maxDamage) {
		super(timeOfStatus);

		isItGood = true;
		pathToImage = "res/statusIcons/weaponEnchantment.png";

		name = "Weapon Enchantment";

		this.damageTypes = new ArrayList<DamageTypes>();
		this.damageTypes.addAll(damageTypes);
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
	}

	public WeaponEnchantment(List<DamageTypes> damageTypes, int minDamage, int maxDamage) {
		this(damageTypes, DEFAULT_STATUS_TIME, minDamage, maxDamage);
	}

	public void doBuff(Map<DamageTypes, Double> damageMap) {
		for (DamageTypes damageType : damageTypes) {
			Double oldDamge = damageMap.get(damageType);

			Double damage = random.nextDouble() * (maxDamage - minDamage) + minDamage + oldDamge;
			damageMap.put(damageType, damage);
		}
	}

	@Override
	public void renew(Status status) {
		super.renew(status);

		WeaponEnchantment statusWep = (WeaponEnchantment) status;

		for (DamageTypes temp : statusWep.getDamageTypes()) {
			if (!damageTypes.contains(temp)) {
				damageTypes.add(temp);
			}
		}

		if (statusWep.getMinDamage() > minDamage)
			minDamage = statusWep.getMinDamage();

		if (statusWep.getMaxDamage() > maxDamage)
			maxDamage = statusWep.getMaxDamage();
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public List<DamageTypes> getDamageTypes() {
		return damageTypes;
	}

	public int getMinDamage() {
		return minDamage;
	}

	public int getMaxDamage() {
		return maxDamage;
	}

}
