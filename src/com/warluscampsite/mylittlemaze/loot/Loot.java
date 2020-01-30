package com.warluscampsite.mylittlemaze.loot;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemDatabase;
import com.warluscampsite.mylittlemaze.loot.prefixes.ItemPrefixes;
import com.warluscampsite.mylittlemaze.loot.prefixes.Prefix;
import com.warluscampsite.mylittlemaze.loot.suffixes.ItemSuffixes;
import com.warluscampsite.mylittlemaze.loot.suffixes.Suffix;
import com.warluscampsite.mylittlemaze.maze.Maze;

public class Loot {

	Maze maze;
	int luck;

	Random random = new Random();

	List<ItemDatabase> listOfItemsThatCanAppear;
	int listOfItemsThatCanAppearWeight;

	List<ItemPrefixes> listOfPrefixesThatCanAppear;
	ArrayList<ItemPrefixes> listOfPrefixesThatCanAppearForArmors, listOfPrefixesThatCanAppearForJewellery,
			listOfPrefixesThatCanAppearForMeleeWeapons, listOfPrefixesThatCanAppearForRangedWeapons,
			listOfPrefixesThatCanAppearForMagicWeapons, listOfPrefixesThatCanAppearForShields;
	int listOfPrefixesThatCanAppearForArmorsWeight, listOfPrefixesThatCanAppearForJewelleryWeight,
			listOfPrefixesThatCanAppearForMeleeWeaponsWeight, listOfPrefixesThatCanAppearForRangedWeaponsWeight,
			listOfPrefixesThatCanAppearForMagicWeaponsWeight, listOfPrefixesThatCanAppearForShieldsWeight;

	List<ItemSuffixes> listOfSuffixesThatCanAppear;
	ArrayList<ItemSuffixes> listOfSuffixesThatCanAppearForArmors, listOfSuffixesThatCanAppearForJewellery,
			listOfSuffixesThatCanAppearForMeleeWeapons, listOfSuffixesThatCanAppearForRangedWeapons,
			listOfSuffixesThatCanAppearForMagicWeapons, listOfSuffixesThatCanAppearForShields;
	int listOfSuffixesThatCanAppearForArmorsWeight, listOfSuffixesThatCanAppearForJewelleryWeight,
			listOfSuffixesThatCanAppearForMeleeWeaponsWeight, listOfSuffixesThatCanAppearForRangedWeaponsWeight,
			listOfSuffixesThatCanAppearForMagicWeaponsWeight, listOfSuffixesThatCanAppearForShieldsWeight;

	public Loot(Maze maze) {
		this.maze = maze;

		initializeLists();

	}

	private void initializeLists() {
		listOfItemsThatCanAppear = new ArrayList<>();

		listOfPrefixesThatCanAppear = new ArrayList<>();
		listOfPrefixesThatCanAppearForArmors = new ArrayList<>();
		listOfPrefixesThatCanAppearForJewellery = new ArrayList<>();
		listOfPrefixesThatCanAppearForMeleeWeapons = new ArrayList<>();
		listOfPrefixesThatCanAppearForRangedWeapons = new ArrayList<>();
		listOfPrefixesThatCanAppearForMagicWeapons = new ArrayList<>();
		listOfPrefixesThatCanAppearForShields = new ArrayList<>();

		listOfSuffixesThatCanAppear = new ArrayList<>();
		listOfSuffixesThatCanAppearForArmors = new ArrayList<>();
		listOfSuffixesThatCanAppearForJewellery = new ArrayList<>();
		listOfSuffixesThatCanAppearForMeleeWeapons = new ArrayList<>();
		listOfSuffixesThatCanAppearForRangedWeapons = new ArrayList<>();
		listOfSuffixesThatCanAppearForMagicWeapons = new ArrayList<>();
		listOfSuffixesThatCanAppearForShields = new ArrayList<>();

	}

	public void newMazeNewLoot() {
		luck = maze.getData().getPlayerParty().getHighestLuck();

		listOfItemsThatCanAppear = ItemDatabase.getListOfItemsThatCanAppear(maze.getLevel());
		for (ItemDatabase itemDatabase : listOfItemsThatCanAppear) {
			listOfItemsThatCanAppearWeight += itemDatabase.getProbabilityWeight();
		}

		clearAllLists();

		listOfPrefixesThatCanAppear = ItemPrefixes.getListOfPrefixesThatCanAppear(maze.getLevel());
		for (ItemPrefixes prefix : listOfPrefixesThatCanAppear) {
			for (int possiblePlace : prefix.getPrefix().getPossibleItemTypes()) {
				switch (possiblePlace) {
				case 0:
					listOfPrefixesThatCanAppearForArmors.add(prefix);
					listOfPrefixesThatCanAppearForArmorsWeight += prefix.getWeightProbability();
					break;
				case 1:
					listOfPrefixesThatCanAppearForJewellery.add(prefix);
					listOfPrefixesThatCanAppearForJewelleryWeight += prefix.getWeightProbability();
					break;
				case 2:
					listOfPrefixesThatCanAppearForMeleeWeapons.add(prefix);
					listOfPrefixesThatCanAppearForMeleeWeaponsWeight += prefix.getWeightProbability();
					break;
				case 3:
					listOfPrefixesThatCanAppearForRangedWeapons.add(prefix);
					listOfPrefixesThatCanAppearForRangedWeaponsWeight += prefix.getWeightProbability();
					break;
				case 4:
					listOfPrefixesThatCanAppearForMagicWeapons.add(prefix);
					listOfPrefixesThatCanAppearForMagicWeaponsWeight += prefix.getWeightProbability();
					break;
				case 5:
					listOfPrefixesThatCanAppearForShields.add(prefix);
					listOfPrefixesThatCanAppearForShieldsWeight += prefix.getWeightProbability();
					break;
				}
			}
		}

		listOfSuffixesThatCanAppear = ItemSuffixes.getListOfPrefixesThatCanAppear(maze.getLevel());
		for (ItemSuffixes suffix : listOfSuffixesThatCanAppear) {
			for (int possiblePlace : suffix.getSuffix().getPossibleItemTypes()) {
				switch (possiblePlace) {
				case 0:
					listOfSuffixesThatCanAppearForArmors.add(suffix);
					listOfSuffixesThatCanAppearForArmorsWeight += suffix.getWeightProbability();
					break;
				case 1:
					listOfSuffixesThatCanAppearForJewellery.add(suffix);
					listOfSuffixesThatCanAppearForJewelleryWeight += suffix.getWeightProbability();
					break;
				case 2:
					listOfSuffixesThatCanAppearForMeleeWeapons.add(suffix);
					listOfSuffixesThatCanAppearForMeleeWeaponsWeight += suffix.getWeightProbability();
					break;
				case 3:
					listOfSuffixesThatCanAppearForRangedWeapons.add(suffix);
					listOfSuffixesThatCanAppearForRangedWeaponsWeight += suffix.getWeightProbability();
					break;
				case 4:
					listOfSuffixesThatCanAppearForMagicWeapons.add(suffix);
					listOfSuffixesThatCanAppearForMagicWeaponsWeight += suffix.getWeightProbability();
					break;
				case 5:
					listOfSuffixesThatCanAppearForShields.add(suffix);
					listOfSuffixesThatCanAppearForShieldsWeight += suffix.getWeightProbability();
					break;
				}
			}
		}
	}

	private void clearAllLists() {
		listOfPrefixesThatCanAppearForArmors.clear();
		listOfPrefixesThatCanAppearForJewellery.clear();
		listOfPrefixesThatCanAppearForMeleeWeapons.clear();
		listOfPrefixesThatCanAppearForRangedWeapons.clear();
		listOfPrefixesThatCanAppearForMagicWeapons.clear();
		listOfPrefixesThatCanAppearForShields.clear();

		listOfSuffixesThatCanAppearForArmors.clear();
		listOfSuffixesThatCanAppearForJewellery.clear();
		listOfSuffixesThatCanAppearForMeleeWeapons.clear();
		listOfSuffixesThatCanAppearForRangedWeapons.clear();
		listOfSuffixesThatCanAppearForMagicWeapons.clear();
		listOfSuffixesThatCanAppearForShields.clear();

		listOfPrefixesThatCanAppearForArmorsWeight = 0;
		listOfPrefixesThatCanAppearForJewelleryWeight = 0;
		listOfPrefixesThatCanAppearForMeleeWeaponsWeight = 0;
		listOfPrefixesThatCanAppearForRangedWeaponsWeight = 0;
		listOfPrefixesThatCanAppearForMagicWeaponsWeight = 0;
		listOfPrefixesThatCanAppearForShieldsWeight = 0;

		listOfSuffixesThatCanAppearForArmorsWeight = 0;
		listOfSuffixesThatCanAppearForJewelleryWeight = 0;
		listOfSuffixesThatCanAppearForMeleeWeaponsWeight = 0;
		listOfSuffixesThatCanAppearForRangedWeaponsWeight = 0;
		listOfSuffixesThatCanAppearForMagicWeaponsWeight = 0;
		listOfSuffixesThatCanAppearForShieldsWeight = 0;
	}

	public void checkLootAfterBattle() {
		checkGoldAfterBattle();
		checkItemsAfterBattle();

	}

	private void checkGoldAfterBattle() {
	}

	private static final int NUMBER_FOR_ONE_ITEM = 75;

	private void checkItemsAfterBattle() {
		int numberOfItems = random.nextInt(maze.getMonstersParty().getDifficultyOfMonsterParty()) + maze.getLevel()
				+ luck;

		while (numberOfItems >= NUMBER_FOR_ONE_ITEM) {
			numberOfItems -= NUMBER_FOR_ONE_ITEM;

			ItemBase itemBase = ItemDatabase.getRandomFromList(listOfItemsThatCanAppear,
					listOfItemsThatCanAppearWeight);
			ItemRarity rarity = ItemRarity.getRandom(luck, maze.getLevel());

			Prefix prefixFromEnum = checkPrefix(itemBase, rarity);
			Suffix suffixFromEnum = checkSuffix(itemBase, rarity);

			try {
				Class<?> clazz = Class.forName(itemBase.getClass().getName());
				Constructor<?> constructor = clazz.getConstructor(ItemTypes.class);
				Object iBase = constructor.newInstance(itemBase.getItemType());

				Object prefix = null;
				if (prefixFromEnum != null) {
					clazz = Class.forName(prefixFromEnum.getClass().getName());
					constructor = clazz.getConstructor(int[].class);
					prefix = constructor.newInstance(prefixFromEnum.getPossibleItemTypes());
				}

				Object suffix = null;
				if (suffixFromEnum != null) {
					clazz = Class.forName(suffixFromEnum.getClass().getName());
					constructor = clazz.getConstructor(int[].class);
					suffix = constructor.newInstance(suffixFromEnum.getPossibleItemTypes());
				}

				maze.getData().getPlayerParty().getItems()
						.addItemAfterBattle(new Item((ItemBase) iBase, rarity, (Prefix) prefix, (Suffix) suffix));

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		maze.getData().getGUI().getItemsPanel().addItemsList();
	}

	private Suffix checkSuffix(ItemBase itemBase, ItemRarity rarity) {
		if (ItemRarity.checkIfAffixesShouldExists(rarity)) {
			return getProperSuffix(itemBase);
		}
		return null;
	}

	public Suffix getProperSuffix(ItemBase itemBase) {
		switch (itemBase.getItemType()) {
		case AMULET:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForJewellery,
					listOfSuffixesThatCanAppearForJewelleryWeight);
		case BELT:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForArmors,
					listOfSuffixesThatCanAppearForArmorsWeight);
		case BODY_ARMOR:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForArmors,
					listOfSuffixesThatCanAppearForArmorsWeight);
		case BOOTS:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForArmors,
					listOfSuffixesThatCanAppearForArmorsWeight);
		case GLOVES:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForArmors,
					listOfSuffixesThatCanAppearForArmorsWeight);
		case HELMET:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForArmors,
					listOfSuffixesThatCanAppearForArmorsWeight);
		case LEGS:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForArmors,
					listOfSuffixesThatCanAppearForArmorsWeight);
		case ONE_HANDED_MAGIC:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForMagicWeapons,
					listOfSuffixesThatCanAppearForMagicWeaponsWeight);
		case ONE_HANDED_MELEE:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForMeleeWeapons,
					listOfSuffixesThatCanAppearForMeleeWeaponsWeight);
		case ONE_HANDED_RANGED:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForRangedWeapons,
					listOfSuffixesThatCanAppearForRangedWeaponsWeight);
		case RING:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForJewellery,
					listOfSuffixesThatCanAppearForJewelleryWeight);
		case SHIELD:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForShields,
					listOfSuffixesThatCanAppearForShieldsWeight);
		case SHOULDERS:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForArmors,
					listOfSuffixesThatCanAppearForArmorsWeight);
		case TWO_HANDED_MAGIC:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForMagicWeapons,
					listOfSuffixesThatCanAppearForMagicWeaponsWeight);
		case TWO_HANDED_MELEE:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForMeleeWeapons,
					listOfSuffixesThatCanAppearForMeleeWeaponsWeight);
		case TWO_HANDED_RANGED:
			return ItemSuffixes.getRandomFromList(listOfSuffixesThatCanAppearForRangedWeapons,
					listOfSuffixesThatCanAppearForRangedWeaponsWeight);
		default:
			return null;
		}
	}

	private Prefix checkPrefix(ItemBase itemBase, ItemRarity rarity) {
		if (ItemRarity.checkIfAffixesShouldExists(rarity)) {
			return getProperPrefix(itemBase);
		}
		return null;
	}

	public Prefix getProperPrefix(ItemBase itemBase) {
		switch (itemBase.getItemType()) {
		case AMULET:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForJewellery,
					listOfPrefixesThatCanAppearForJewelleryWeight);
		case BELT:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForArmors,
					listOfPrefixesThatCanAppearForArmorsWeight);
		case BODY_ARMOR:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForArmors,
					listOfPrefixesThatCanAppearForArmorsWeight);
		case BOOTS:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForArmors,
					listOfPrefixesThatCanAppearForArmorsWeight);
		case GLOVES:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForArmors,
					listOfPrefixesThatCanAppearForArmorsWeight);
		case HELMET:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForArmors,
					listOfPrefixesThatCanAppearForArmorsWeight);
		case LEGS:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForArmors,
					listOfPrefixesThatCanAppearForArmorsWeight);
		case ONE_HANDED_MAGIC:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForMagicWeapons,
					listOfPrefixesThatCanAppearForMagicWeaponsWeight);
		case ONE_HANDED_MELEE:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForMeleeWeapons,
					listOfPrefixesThatCanAppearForMeleeWeaponsWeight);
		case ONE_HANDED_RANGED:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForRangedWeapons,
					listOfPrefixesThatCanAppearForRangedWeaponsWeight);
		case RING:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForJewellery,
					listOfPrefixesThatCanAppearForJewelleryWeight);
		case SHIELD:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForShields,
					listOfPrefixesThatCanAppearForShieldsWeight);
		case SHOULDERS:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForArmors,
					listOfPrefixesThatCanAppearForArmorsWeight);
		case TWO_HANDED_MAGIC:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForMagicWeapons,
					listOfPrefixesThatCanAppearForMagicWeaponsWeight);
		case TWO_HANDED_MELEE:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForMeleeWeapons,
					listOfPrefixesThatCanAppearForMeleeWeaponsWeight);
		case TWO_HANDED_RANGED:
			return ItemPrefixes.getRandomFromList(listOfPrefixesThatCanAppearForRangedWeapons,
					listOfPrefixesThatCanAppearForRangedWeaponsWeight);
		default:
			return null;
		}
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
}
