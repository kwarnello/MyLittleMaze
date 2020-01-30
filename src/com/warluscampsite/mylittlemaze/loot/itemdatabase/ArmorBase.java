package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import com.warluscampsite.mylittlemaze.loot.ItemTypes;

public abstract class ArmorBase extends ItemBase {

	public ArmorBase(ItemTypes itemType) {
		super(itemType);
		// TODO Auto-generated constructor stub
	}

	ArmorTypes armorTypes;

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public ArmorTypes getArmorTypes() {
		return armorTypes;
	}
}
