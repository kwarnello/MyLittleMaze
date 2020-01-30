package com.warluscampsite.mylittlemaze.data;

import com.warluscampsite.mylittlemaze.battle.MainBattle;
import com.warluscampsite.mylittlemaze.controllers.Options;
import com.warluscampsite.mylittlemaze.controllers.TimeController;
import com.warluscampsite.mylittlemaze.gui.MainGUI;
import com.warluscampsite.mylittlemaze.gui.PlayerCharacterCreationPanel;
import com.warluscampsite.mylittlemaze.maze.Maze;
import com.warluscampsite.mylittlemaze.playerteam.PlayerParty;

public class Data {

	MainGUI GUI;

	PlayerParty playerParty;

	Maze maze;

	MainBattle battle;

	PlayerCharacterCreationPanel creationPanel;

	public Data() {
		new Options();

		initializeGameData();

		initializeBattle();

		initializeGUIElements();

		initializeCreationPanel();

	}

	private void initializeGUIElements() {
		TimeController.addNewKey("£adowanie GUI");

		GUI = new MainGUI(this);

		TimeController.getTimeOfRunning("£adowanie GUI");
	}

	public void initializeCreationPanel() {
		TimeController.addNewKey("Player creation panel");

		creationPanel = new PlayerCharacterCreationPanel(this);

		TimeController.getTimeOfRunning("Player creation panel");

	}

	private void initializeGameData() {
		TimeController.addNewKey("£adowanie game data");

		TimeController.addNewKey("£adowanie player party");
		playerParty = new PlayerParty(this);
		TimeController.getTimeOfRunning("£adowanie player party");

		maze = new Maze(this);

		TimeController.getTimeOfRunning("£adowanie game data");
	}

	private void initializeBattle() {
		TimeController.addNewKey("£adowanie battle");

		battle = new MainBattle(this);

		TimeController.getTimeOfRunning("£adowanie battle");
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public MainGUI getGUI() {
		return GUI;
	}

	public PlayerParty getPlayerParty() {
		return playerParty;
	}

	public Maze getMaze() {
		return maze;
	}

	public MainBattle getBattle() {
		return battle;
	}

	public PlayerCharacterCreationPanel getCreationPanel() {
		return creationPanel;
	}

}
