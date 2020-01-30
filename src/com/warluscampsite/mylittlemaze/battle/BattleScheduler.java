package com.warluscampsite.mylittlemaze.battle;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BattleScheduler {

	MainBattle battle;

	boolean pause = true;

	final int ACTIONS_PER_SECOND = 50;
	final int MILIS_BETWEEN_ACTIONS = 1000 / ACTIONS_PER_SECOND;

	ScheduledExecutorService scheduler;

	public BattleScheduler(MainBattle mainBattle) {
		this.battle = mainBattle;

		scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(new Runnable() {
			public void run() {
				try {
					mainBattle.invokeFromScheduler(pause);
				} catch (Throwable e) {
					System.err.println("Error in runnable!");
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}, 0, MILIS_BETWEEN_ACTIONS, TimeUnit.MILLISECONDS);
	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public int getMILIS_BETWEEN_ACTIONS() {
		return MILIS_BETWEEN_ACTIONS;
	}

}
