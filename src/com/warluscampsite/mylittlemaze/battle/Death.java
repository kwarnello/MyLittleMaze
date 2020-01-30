package com.warluscampsite.mylittlemaze.battle;

import java.util.ArrayList;
import java.util.List;

import com.warluscampsite.mylittlemaze.controllers.TextAreaController;
import com.warluscampsite.mylittlemaze.data.Data;
import com.warluscampsite.mylittlemaze.monsters.Monster;
import com.warluscampsite.mylittlemaze.playerteam.Player;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class Death {

	Data data;

	static List<Player> playerDeath = new ArrayList<>();
	static List<Monster> monsterDeath = new ArrayList<>();

	public Death(Data data) {
		this.data = data;
	}

	public static void addNewCharacterToDeathList(Characterr character) {
		character.setDead(true);
		character.getStatus().removeAllStatusesAfterDeath();

		if (character.getClass().equals(Player.class))
			playerDeath.add((Player) character);
		else
			monsterDeath.add((Monster) character);
	}

	public void doDeath() {
		playerDeath.forEach(n -> doStuffAfterPlayerDeath(n));
		monsterDeath.forEach(n -> doStuffAfterMonsterDeath(n));

		playerDeath.clear();
		monsterDeath.clear();
	}

	private void doStuffAfterPlayerDeath(Player character) {
		// TEMP
		character.getPlayerParty().getCharacterMap().forEach((k, v) -> {
			if (v != null)
				v.getHealth().regenAllHp();
		});
		character.setDead(false);

		if (data.getPlayerParty().checkIfAllDeath())
			TextAreaController.addTextToTextArea("All of you are dead! Game over!!!");
	}

	private void doStuffAfterMonsterDeath(Monster character) {
		addExperienceForPlayerAfterMonsterDeath(character);

		if (data.getMaze().getMonstersParty().checkIfAllDeath()) {
			// Check loot after monsters party death based on maze and party! :)
			data.getMaze().getLoot().checkLootAfterBattle();

			// move party
			data.getPlayerParty().moveParty();

			// do stuff with generating new monsters party
			data.getMaze().getMonstersParty().reconstructMonsterTeam();
			data.getGUI().getBattlePanel().initializeMonsterMiniatures();

			TextAreaController.addTextToTextArea("All of monsters are dead! New crew incoming!");
		}

	}

	private void addExperienceForPlayerAfterMonsterDeath(Characterr character) {
		data.getPlayerParty().getCharacterMap().forEach((k, v) -> {
			if (v != null)
				v.getExperience().addExperience(50);
		});
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
}
