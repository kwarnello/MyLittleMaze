package com.warluscampsite.mylittlemaze.battle;

import java.awt.Color;
import java.awt.Font;

import com.warluscampsite.mylittlemaze.controllers.MyStringFormatter;

public class BattleNumber {

	final static int MAX_TIME = 2300;
	final static double POSITION_CHANGE_AFTER_TICK = 0.7;
	final static int START_DISSAPEAR_AFTER = 900;
	final static int ALPHA_CHANGE_DURING_TICK = 5;
	final static Font font = new Font("TimesRoman", Font.BOLD, 14);

	String text;

	Color color;
	int alpha;
	boolean criticalStrike;

	int actuallTime;
	boolean shouldBeKilled;

	double x, y;

	public BattleNumber(int x, int y, double damage, boolean criticalStrike, Boolean isItBuff) {
		super();
		this.x = x;
		this.y = y;
		this.criticalStrike = criticalStrike;

		actuallTime = 0;
		shouldBeKilled = false;
		alpha = 255;

		if (!criticalStrike) {
			color = new Color(0, 0, 0, alpha);
			text = "-" + MyStringFormatter.formatDouble(damage, 0);
		} else {
			color = new Color(112, 4, 4, alpha);
			text = "-" + MyStringFormatter.formatDouble(damage, 0) + "!";
		}

	}

	public void addTick(int millis) {
		actuallTime += millis;

		if (actuallTime >= MAX_TIME) {
			shouldBeKilled = true;
			return;
		}

		y -= POSITION_CHANGE_AFTER_TICK;

		if (actuallTime > START_DISSAPEAR_AFTER) {
			alpha -= ALPHA_CHANGE_DURING_TICK;
			if (alpha < 0)
				alpha = 0;

			if (!criticalStrike)
				color = new Color(0, 0, 0, alpha);
			else
				color = new Color(112, 4, 4, alpha);
		}
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */

	public boolean isShouldBeKilled() {
		return shouldBeKilled;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String getText() {
		return text;
	}

	public Color getColor() {
		return color;
	}

	public static Font getFont() {
		return font;
	}

}
