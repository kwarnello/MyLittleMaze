package com.warluscampsite.mylittlemaze.battle;

import com.warluscampsite.mylittlemaze.controllers.CoreLoad;
import com.warluscampsite.mylittlemaze.data.Data;

public class MainBattle {

	Data data;

	BattleScheduler battleScheduler;
	BattleInitiativeController battleInitiativeController;
	BattleAttackController battleAttackControllers;

	Death deathSystem;

	int timePassed = 0;

	CoreLoad battleCoreLoad, updateGUICoreLoad;

	public MainBattle(Data data) {
		this.data = data;

		battleScheduler = new BattleScheduler(this);
		battleInitiativeController = new BattleInitiativeController(data);
		battleAttackControllers = new BattleAttackController(data);

		deathSystem = new Death(data);

		battleCoreLoad = new CoreLoad("battle stuff");
		updateGUICoreLoad = new CoreLoad("GUI stuff");
	}

	public void invokeFromScheduler(boolean pause) {
		if (pause)
			return;

		long start = System.nanoTime();

		battleInitiativeController.addTick(battleScheduler.MILIS_BETWEEN_ACTIONS);

		timePassed += battleScheduler.MILIS_BETWEEN_ACTIONS;
		if (timePassed >= 1000)
			regen();

		battleCoreLoad.addForStats(System.nanoTime() - start);

		start = System.nanoTime();

		data.getGUI().refresh();

		updateGUICoreLoad.addForStats(System.nanoTime() - start);
	}

	private void regen() {
		timePassed -= 1000;

		data.getPlayerParty().getCharacterMap().forEach((k, v) -> {
			if (v != null) {
				v.getHealth().regenHp();
				v.getMana().regenMp();
			}
		});

		data.getMaze().getMonstersParty().getPlaceMap().forEach((k, v) -> v.getHealth().regenHp());
		data.getMaze().getMonstersParty().getPlaceMap().forEach((k, v) -> v.getMana().regenMp());
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public BattleScheduler getBattleScheduler() {
		return battleScheduler;
	}

	public BattleInitiativeController getBattleInitiativeController() {
		return battleInitiativeController;
	}

	public BattleAttackController getBattleAttackControllers() {
		return battleAttackControllers;
	}

	public Death getDeathSystem() {
		return deathSystem;
	}

}
