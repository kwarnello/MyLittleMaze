package com.warluscampsite.mylittlemaze.camp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import com.warluscampsite.mylittlemaze.loot.Item;
import com.warluscampsite.mylittlemaze.loot.ItemRarity;
import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.loot.Loot;
import com.warluscampsite.mylittlemaze.loot.itemdatabase.ItemBase;
import com.warluscampsite.mylittlemaze.loot.prefixes.Prefix;
import com.warluscampsite.mylittlemaze.loot.suffixes.Suffix;
import com.warluscampsite.mylittlemaze.playerteam.Player;

public class Crafting {

	private Camp camp;
	private Loot loot;

	private Random random;

	public Crafting(Camp camp, Loot loot) {
		this.camp = camp;
		this.loot = loot;
		random = new Random();
	}

	protected void rerollPrefix(Player player, Item item) {
		double chance = random.nextDouble();

		if (chance < camp.getNothingPrefixProbability())
			return;
		else if (camp.getNothingPrefixProbability() <= chance
				&& chance < (camp.getNothingPrefixProbability() + camp.getBrokenPrefixProbability()))
			destroyItem(player, item);
		else {
			Item oldItem = item;
			ItemBase itemBase = item.getItemBase();
			Suffix oldSuffix = item.getSuffix();
			try {
				Class<?> clazz = Class.forName(itemBase.getClass().getName());
				Constructor<?> constructor = clazz.getConstructor(ItemTypes.class);
				Object iBase = constructor.newInstance(itemBase.getItemType());

				Prefix newRandomPrefix = loot.getProperPrefix(itemBase);
				Object prefix = null;
				if (newRandomPrefix != null) {
					clazz = Class.forName(newRandomPrefix.getClass().getName());
					constructor = clazz.getConstructor(int[].class);
					prefix = constructor.newInstance(newRandomPrefix.getPossibleItemTypes());
				}

				Object suffix = null;
				if (oldSuffix != null) {
					clazz = Class.forName(oldSuffix.getClass().getName());
					constructor = clazz.getConstructor(int[].class);
					suffix = constructor.newInstance(oldSuffix.getPossibleItemTypes());
				}

				item = new Item((ItemBase) iBase, oldItem.getItemRarity(), (Prefix) prefix, (Suffix) suffix);
				player.getEq().equipItemAfterCrafting(item, oldItem);

				oldItem = null;
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
	}

	protected void rerollSuffix(Player player, Item item) {
		double chance = random.nextDouble();

		if (chance < camp.getNothingSuffixProbability())
			return;
		else if (camp.getNothingSuffixProbability() <= chance
				&& chance < (camp.getBrokenSuffixProbability() + camp.getNothingSuffixProbability()))
			destroyItem(player, item);
		else {
			Item oldItem = item;
			ItemBase itemBase = item.getItemBase();
			Prefix oldPrefix = item.getPrefix();
			try {
				Class<?> clazz = Class.forName(itemBase.getClass().getName());
				Constructor<?> constructor = clazz.getConstructor(ItemTypes.class);
				Object iBase = constructor.newInstance(itemBase.getItemType());

				Object prefix = null;
				if (oldPrefix != null) {
					clazz = Class.forName(oldPrefix.getClass().getName());
					constructor = clazz.getConstructor(int[].class);
					prefix = constructor.newInstance(oldPrefix.getPossibleItemTypes());
				}

				Suffix newRandomSuffix = loot.getProperSuffix(itemBase);
				Object suffix = null;
				if (newRandomSuffix != null) {
					clazz = Class.forName(newRandomSuffix.getClass().getName());
					constructor = clazz.getConstructor(int[].class);
					suffix = constructor.newInstance(newRandomSuffix.getPossibleItemTypes());
				}

				item = new Item((ItemBase) iBase, oldItem.getItemRarity(), (Prefix) prefix, (Suffix) suffix);
				player.getEq().equipItemAfterCrafting(item, oldItem);

				oldItem = null;
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
	}

	protected void upgradeRarity(Player player, Item item) {
		double chance = random.nextDouble();

		if (chance < camp.getNothingRarityUpgradeProbability())
			return;
		else if (camp.getNothingRarityUpgradeProbability() <= chance
				&& chance < (camp.getBrokenRarityUpgradeProbability() + camp.getNothingRarityUpgradeProbability())) {
			Item oldItem = item;
			ItemBase itemBase = item.getItemBase();
			Suffix oldSuffix = item.getSuffix();
			Prefix oldPrefix = item.getPrefix();
			try {
				Class<?> clazz = Class.forName(itemBase.getClass().getName());
				Constructor<?> constructor = clazz.getConstructor(ItemTypes.class);
				Object iBase = constructor.newInstance(itemBase.getItemType());

				Object prefix = null;
				if (oldPrefix != null) {
					clazz = Class.forName(oldPrefix.getClass().getName());
					constructor = clazz.getConstructor(int[].class);
					prefix = constructor.newInstance(oldPrefix.getPossibleItemTypes());
				}

				Object suffix = null;
				if (oldSuffix != null) {
					clazz = Class.forName(oldSuffix.getClass().getName());
					constructor = clazz.getConstructor(int[].class);
					suffix = constructor.newInstance(oldSuffix.getPossibleItemTypes());
				}

				item = new Item((ItemBase) iBase, ItemRarity.getLowerTier(oldItem.getItemRarity()), (Prefix) prefix,
						(Suffix) suffix);
				player.getEq().equipItemAfterCrafting(item, oldItem);

				oldItem = null;
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
		} else {
			Item oldItem = item;
			ItemBase itemBase = item.getItemBase();
			Suffix oldSuffix = item.getSuffix();
			Prefix oldPrefix = item.getPrefix();
			try {
				Class<?> clazz = Class.forName(itemBase.getClass().getName());
				Constructor<?> constructor = clazz.getConstructor(ItemTypes.class);
				Object iBase = constructor.newInstance(itemBase.getItemType());

				Object prefix = null;
				if (oldPrefix != null) {
					clazz = Class.forName(oldPrefix.getClass().getName());
					constructor = clazz.getConstructor(int[].class);
					prefix = constructor.newInstance(oldPrefix.getPossibleItemTypes());
				}

				Object suffix = null;
				if (oldSuffix != null) {
					clazz = Class.forName(oldSuffix.getClass().getName());
					constructor = clazz.getConstructor(int[].class);
					suffix = constructor.newInstance(oldSuffix.getPossibleItemTypes());
				}

				item = new Item((ItemBase) iBase, ItemRarity.getHigherTier(oldItem.getItemRarity()), (Prefix) prefix,
						(Suffix) suffix);
				player.getEq().equipItemAfterCrafting(item, oldItem);

				oldItem = null;
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
	}

	private void destroyItem(Player player, Item item) {
		player.getEq().destroyEquipedItem(item);
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
}
