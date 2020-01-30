package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.warluscampsite.mylittlemaze.loot.ItemTypes;

public enum ItemDatabase {

		// HELMET
		LEATHER_HELMET(ItemTypes.HELMET, new LeatherHelmet(ItemTypes.HELMET), 1, 100),
		CULTISTS_HOOD(ItemTypes.HELMET, new CultistHood(ItemTypes.HELMET), 3, 90),

		// SHOULDERS
		LEATHER_SHOULDERS(ItemTypes.SHOULDERS, new LeatherShoulders(ItemTypes.SHOULDERS), 1, 80),
		CULTIST_SHOULDERS(ItemTypes.SHOULDERS, new CultistShoulders(ItemTypes.SHOULDERS), 3, 75),

		// BODY_ARMOR
		LEATHER_ARMOR(ItemTypes.BODY_ARMOR, new LeatherArmor(ItemTypes.BODY_ARMOR), 1, 100),
		CULTIST_ARMOR(ItemTypes.BODY_ARMOR, new CultistArmor(ItemTypes.BODY_ARMOR), 3, 90),

		// GLOVES
		LEATHER_GLOVES(ItemTypes.GLOVES, new LeatherGloves(ItemTypes.GLOVES), 1, 80),
		CULTIST_GLOVES(ItemTypes.GLOVES, new CultistGloves(ItemTypes.GLOVES), 3, 75),

		// BELT
		LEATHER_BELT(ItemTypes.BELT, new LeatherBelt(ItemTypes.BELT), 1, 70),
		CULTIST_BELT(ItemTypes.BELT, new CultistBelt(ItemTypes.BELT), 3, 70),
		HEAVY_BELT(ItemTypes.BELT, new HeavyBelt(ItemTypes.BELT), 5, 45),

		// LEGS
		LEATHER_LEGS(ItemTypes.LEGS, new LeatherLegs(ItemTypes.LEGS), 1, 80),
		CULTIST_PANTS(ItemTypes.LEGS, new CultistPants(ItemTypes.LEGS), 3, 75),

		// BOOTS
		LEATHER_BOOTS(ItemTypes.BOOTS, new LeatherBoots(ItemTypes.BOOTS), 1, 70),
		CULTIST_BOOTS(ItemTypes.BOOTS, new CultistBoots(ItemTypes.BOOTS), 3, 65),

		// AMULET
		NECKLACE(ItemTypes.AMULET, new Necklace(ItemTypes.AMULET), 1, 50),
		WITCH_AMULET(ItemTypes.AMULET, new WitchAmulet(ItemTypes.AMULET), 20, 35),

		// RING
		COPPER_RING(ItemTypes.RING, new CopperRing(ItemTypes.RING), 1, 50),
		SIGNET(ItemTypes.RING, new Signet(ItemTypes.RING), 5, 40),

		// ONE_HANDED_MELEE
		SHORT_SWORD(ItemTypes.ONE_HANDED_MELEE, new ShortSword(ItemTypes.ONE_HANDED_MELEE), 1, 80),
		BUTCHER_AXE(ItemTypes.ONE_HANDED_MELEE, new ButcherAxe(ItemTypes.ONE_HANDED_MELEE), 2, 75),

		// TWO_HANDED_MELEE
		STONE_AXE(ItemTypes.TWO_HANDED_MELEE, new StoneAxe(ItemTypes.TWO_HANDED_MELEE), 2, 70),
		BASTARD_SWORD(ItemTypes.TWO_HANDED_MELEE, new BastardSword(ItemTypes.TWO_HANDED_MELEE), 4, 70),
		HALBERD(ItemTypes.TWO_HANDED_MELEE, new Halberd(ItemTypes.TWO_HANDED_MELEE), 10, 65),

		// ONE_HANDED_RANGED
		THROWING_DAGGER(ItemTypes.ONE_HANDED_RANGED, new ThrowingDagger(ItemTypes.ONE_HANDED_RANGED), 1, 60),
		THROWING_AXE(ItemTypes.ONE_HANDED_RANGED, new ThrowingAxe(ItemTypes.ONE_HANDED_RANGED), 3, 60),

		// TWO_HANDED_RANGED,
		WOODEN_BOW(ItemTypes.TWO_HANDED_RANGED, new WoodenBow(ItemTypes.TWO_HANDED_RANGED), 1, 75),
		SHORT_BOW(ItemTypes.TWO_HANDED_RANGED, new ShortBow(ItemTypes.TWO_HANDED_RANGED), 3, 70),

		// ONE_HANDED_MAGIC,
		MAGIC_STICK(ItemTypes.ONE_HANDED_MAGIC, new MagicStick(ItemTypes.ONE_HANDED_MAGIC), 1, 55),
		LIGHTNING_STICK(ItemTypes.ONE_HANDED_MAGIC, new LightningStick(ItemTypes.ONE_HANDED_MAGIC), 5, 55),

		// TWO_HANDED_MAGIC,
		ICE_STAFF(ItemTypes.TWO_HANDED_MAGIC, new IceStaff(ItemTypes.TWO_HANDED_MAGIC), 1, 55),
		FIRE_STAFF(ItemTypes.TWO_HANDED_MAGIC, new FireStaff(ItemTypes.TWO_HANDED_MAGIC), 3, 55),

		// SHIELD
		WOODEN_SHIELD(ItemTypes.SHIELD, new WoodenShield(ItemTypes.SHIELD), 1, 70),
		HEATHER_SHIELD(ItemTypes.SHIELD, new HeatherShield(ItemTypes.SHIELD), 5, 55),
		GUARDIAN_SHIELD(ItemTypes.SHIELD, new GuardianShield(ItemTypes.SHIELD), 25, 40),

		// MISC

		DUMMY(ItemTypes.MISC, new LeatherHelmet(ItemTypes.HELMET), 1000, 0);

	ItemTypes itemTypes;
	ItemBase itemBase;
	int minLevelToAppear;
	int probabilityWeight;

	private ItemDatabase(ItemTypes itemTypes, ItemBase itemBase, int minLevelToAppear, int probabilityWeight) {
		this.itemTypes = itemTypes;
		this.itemBase = itemBase;
		this.minLevelToAppear = minLevelToAppear;
		this.probabilityWeight = probabilityWeight;
	}

	private static final List<ItemDatabase> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final Random RANDOM = new Random();

	public static ArrayList<ItemDatabase> getListOfItemsThatCanAppear(int mazeLevel) {
		ArrayList<ItemDatabase> listOfItemsThatCanAppear = new ArrayList<>();

		for (ItemDatabase item : VALUES) {
			if (item.minLevelToAppear <= mazeLevel)
				listOfItemsThatCanAppear.add(item);
		}

		return listOfItemsThatCanAppear;
	}

	public static ItemBase getRandomFromList(List<ItemDatabase> listOfItemsThatCanAppear, int weightSum) {
		int randomNum = RANDOM.nextInt(weightSum);

		int currentWeightSumm = 0;

		for (ItemDatabase value : listOfItemsThatCanAppear) {
			if (randomNum > currentWeightSumm && randomNum <= (currentWeightSumm + value.getProbabilityWeight())) {
				return value.getItemBase();
			}
			currentWeightSumm += value.getProbabilityWeight();
		}

		return DUMMY.getItemBase();
	}

	public ItemTypes getItemTypes() {
		return itemTypes;
	}

	public int getMinLevelToAppear() {
		return minLevelToAppear;
	}

	public int getProbabilityWeight() {
		return probabilityWeight;
	}

	public ItemBase getItemBase() {
		return itemBase;
	}

}
