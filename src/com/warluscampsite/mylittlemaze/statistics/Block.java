package com.warluscampsite.mylittlemaze.statistics;

public class Block {

	boolean canBlock = false, canBlockSpells = false;

	double blockChance = 0.0;
	double blockChanceMultiplier = 1.0;

	Block() {

	}

	Block(boolean canBlock, double probability) {
		this();
		this.canBlock = canBlock;
		this.blockChance = probability;
	}

	public void resetBlockBeforeRefresh() {
		canBlock = false;
		blockChance = 0;
		canBlockSpells = false;
	}

	private void countSum() {
		blockChance = blockChance * blockChanceMultiplier;
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public boolean isCanBlock() {
		return canBlock;
	}

	public void setCanBlock(boolean canBlock) {
		this.canBlock = canBlock;
	}

	public boolean isCanBlockSpells() {
		return canBlockSpells;
	}

	public void setCanBlockSpells(boolean canBlockSpells) {
		this.canBlockSpells = canBlockSpells;
	}

	public double getBlockChance() {
		return blockChance;
	}

	public void setBlockChance(double blockChance) {
		this.blockChance = blockChance;
		countSum();
	}

	public void setBlockChanceMultiplier(double blockChanceMultiplier) {
		this.blockChanceMultiplier = blockChanceMultiplier;
		countSum();
	}

}
