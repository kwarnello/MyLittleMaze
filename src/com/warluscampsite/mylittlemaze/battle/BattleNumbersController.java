package com.warluscampsite.mylittlemaze.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.warluscampsite.mylittlemaze.controllers.Options;
import com.warluscampsite.mylittlemaze.gui.BattlePanel;
import com.warluscampsite.mylittlemaze.statistics.Characterr;

public class BattleNumbersController {

	BattlePanel battlePanel;

	Random random;

	List<BattleNumber> numberList;

	public BattleNumbersController() {
		random = new Random();

		numberList = new ArrayList<BattleNumber>();
	}

	public void addNumber(Characterr character, double damage, boolean criticalStrike, boolean isItBuff,
			boolean isAttackerPlayer) {

		if (checkIfShouldShow(damage, criticalStrike, isItBuff, isAttackerPlayer)) {
			int place = character.getPlace();
			int x, y = 0;
			int xShift = random.nextInt(120);

			if (!isAttackerPlayer) {
				if (place < 4) {
					x = place * 245 - 210 + xShift;
					y = 320;
				} else {
					x = (place - 3) * 245 - 210 + xShift;
					y = 440;
				}
			} else {
				if (place < 4) {
					x = place * 245 - 210 + xShift;
					y = 200;
				} else {
					x = (place - 3) * 245 - 210 + xShift;
					y = 90;
				}
			}

			numberList.add(new BattleNumber(x, y, damage, criticalStrike, isItBuff));

		}

	}

	private boolean checkIfShouldShow(double damage, boolean criticalStrike, boolean isItBuff,
			boolean isAttackerPlayer) {
		if (!Options.isShowNumbers())
			return false;

		if (isItBuff && !Options.isShowHealing())
			return false;

		if (!criticalStrike && Options.isShowOnlyCritical())
			return false;

		return true;
	}

	public void addTick(int millis) {
		numberList.forEach(n -> n.addTick(millis));
		numberList.removeIf(n -> n.isShouldBeKilled());
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public List<BattleNumber> getNumberList() {
		return numberList;
	}
}
