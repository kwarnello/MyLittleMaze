package com.warluscampsite.mylittlemaze.controllers;

import java.awt.Point;

import com.warluscampsite.mylittlemaze.gui.BattlePanel;
import com.warluscampsite.mylittlemaze.gui.CharacterMiniature;

public class MovePanelController {

	final static int DEFAULT_X_MOVE = 242, START_FROM_WITH_X = 0;
	final static int DEFAULT_Y_MOVE = 110, START_FROM_WITH_Y = 210;

	public static void DO_STUFF_AFTER_PANEL_MOVE(Point oldPoint, Point newPoint, CharacterMiniature panel) {
		int oldPlace = panel.getCharacter().getPlace();

		int newPlace = CALC_WHERE_TO_PLACE_NEW(oldPoint, newPoint);

		if (newPlace == 0 || newPlace == oldPlace)
			return;

		((BattlePanel) panel.getParent().getParent()).changePlayerPlace(oldPlace, newPlace);
	}

	private static int CALC_WHERE_TO_PLACE_NEW(Point oldPoint, Point newPoint) {
		int row = 0;
		int column = 0;

		// calc row
		if (START_FROM_WITH_Y <= newPoint.getY() && newPoint.getY() < START_FROM_WITH_Y + DEFAULT_Y_MOVE)
			row = 1;
		else if (START_FROM_WITH_Y + DEFAULT_Y_MOVE < newPoint.getY()
				&& newPoint.getY() <= START_FROM_WITH_Y + 2 * DEFAULT_Y_MOVE)
			row = 2;
		else
			return 0;

		// calc column
		if (START_FROM_WITH_X <= newPoint.getX() && newPoint.getX() < START_FROM_WITH_X + DEFAULT_X_MOVE)
			column = 1;
		else if (START_FROM_WITH_X + DEFAULT_X_MOVE < newPoint.getX()
				&& newPoint.getX() <= START_FROM_WITH_X + 2 * DEFAULT_X_MOVE)
			column = 2;
		else if (START_FROM_WITH_X + 2 * DEFAULT_X_MOVE < newPoint.getX()
				&& newPoint.getX() <= START_FROM_WITH_X + 3 * DEFAULT_X_MOVE)
			column = 3;
		else
			return 0;

		if (row == 1) {
			if (column == 1)
				return 1;
			else if (column == 2)
				return 2;
			else if (column == 3)
				return 3;
		} else if (row == 2) {
			if (column == 1)
				return 4;
			else if (column == 2)
				return 5;
			else if (column == 3)
				return 6;
		}

		return 0;
	}

}
