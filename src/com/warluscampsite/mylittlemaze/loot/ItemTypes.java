package com.warluscampsite.mylittlemaze.loot;

public enum ItemTypes {

		// ARMORS
		HELMET(0, 1),
		SHOULDERS(0, 2),
		BODY_ARMOR(0, 3),
		GLOVES(0, 4),
		BELT(0, 5),
		LEGS(0, 6),
		BOOTS(0, 7),

		// JEWELLERY
		AMULET(1, 8),
		RING(1, 9),

		// WEAPONS
		ONE_HANDED_MELEE(2, 10),
		TWO_HANDED_MELEE(2, 10),
		ONE_HANDED_RANGED(3, 11),
		TWO_HANDED_RANGED(3, 11),
		ONE_HANDED_MAGIC(4, 12),
		TWO_HANDED_MAGIC(4, 12),
		SHIELD(5, 13),

		// MISC
		MISC(6, 17);

	// 0 - armors, 1 - jewellery, 2 - melee weapon, 3 - ranged weapon, 4 - magic
	// weapon, 5 - shield, 6 - misc;
	int typeOfItem;
	int justForSorting;

	private ItemTypes(int typeOfItem, int justForSorting) {
		this.typeOfItem = typeOfItem;
		this.justForSorting = justForSorting;
	}

	@Override
	public String toString() {
		String name = super.toString().substring(0, 1).toUpperCase() + super.toString().substring(1).toLowerCase();
		name = name.replace("_", " ");

		return name;
	}

	public int getTypeOfItem() {
		return typeOfItem;
	}

	public int getJustForSorting() {
		return justForSorting;
	}

}
