package com.warluscampsite.mylittlemaze.loot.itemdatabase;

import com.warluscampsite.mylittlemaze.loot.ItemTypes;
import com.warluscampsite.mylittlemaze.playerteam.Player;

public abstract class ShieldBase extends ItemBase {

	double blockChance;;

	public ShieldBase(ItemTypes itemType) {
		super(itemType);
		blockChance = 0.3;
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	@Override
	public void doStats(Player player) {
		
	}

	public double getBlockChance() {
		return blockChance;
	}
}
