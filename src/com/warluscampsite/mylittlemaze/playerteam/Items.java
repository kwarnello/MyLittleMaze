package com.warluscampsite.mylittlemaze.playerteam;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.warluscampsite.mylittlemaze.gui.SingleItemPanel;
import com.warluscampsite.mylittlemaze.loot.Item;

public class Items {

	PlayerParty playerParty;

	Map<Item, SingleItemPanel> itemHandler;
	int autoSellBelowRarity = -1;
	long goldCoins;

	public Items(PlayerParty playerParty) {
		this.playerParty = playerParty;

		itemHandler = new LinkedHashMap<>();

		goldCoins = 0;
	}

	public void addItemAfterBattle(Item item) {
		if (checkIfShouldSell(item)) {
			sellItem(item);
			return;
		}

		itemHandler.put(item, new SingleItemPanel(item, playerParty.data));
	}

	public void sellItemFromList(Item item) {
		sellItem(item);
		itemHandler.remove(item);
	}

	public void sellItem(Item item) {
		changeGoldCoinsStock(item.getGoldValue());
	}

	public void changeComboBoxStatus(int newRarity) {
		setAutoSellBelowRarity(newRarity);

		List<Item> itemsToRemove = new ArrayList<Item>();
		itemHandler.keySet().forEach(k -> {
			if (checkIfShouldSell(k)) {
				itemsToRemove.add(k);
			}
		});

		itemsToRemove.forEach(n -> sellItemFromList(n));
		playerParty.data.getGUI().getItemsPanel().addItemsList();
	}

	private boolean checkIfShouldSell(Item item) {
		if (item.getItemRarity().getForSorting() < autoSellBelowRarity)
			return true;
		return false;
	}

	public void changeGoldCoinsStock(int change) {
		goldCoins += change;
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public long getGoldCoins() {
		return goldCoins;
	}

	public void setGoldCoins(long goldCoins) {
		this.goldCoins = goldCoins;
	}

	public Map<Item, SingleItemPanel> getItemHandler() {
		return itemHandler;
	}

	public void setAutoSellBelowRarity(int autoSellBelowRarity) {
		if (autoSellBelowRarity < 5)
			this.autoSellBelowRarity = autoSellBelowRarity;
	}

}
