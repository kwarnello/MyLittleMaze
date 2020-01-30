package com.warluscampsite.mylittlemaze.controllers;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import com.warluscampsite.mylittlemaze.gui.CharacterMiniature;

public class DragListener extends MouseInputAdapter {
	Point location, oldPoint;
	MouseEvent pressed;
	Component panel;
	Component component;
	
	public void mousePressed(MouseEvent me) {
		pressed = me;
		component = me.getComponent();
		panel = component.getParent().getParent().getParent();
		oldPoint = panel.getLocation();
	}

	public void mouseDragged(MouseEvent me) {
		Component component = me.getComponent();
		location = panel.getLocation(location);
		int x = location.x - pressed.getX() + me.getX();
		int y = location.y - pressed.getY() + me.getY();
		panel.setLocation(x, y);
	}

	public void mouseReleased(MouseEvent me) {
		MovePanelController.DO_STUFF_AFTER_PANEL_MOVE(oldPoint, location, (CharacterMiniature) component.getParent().getParent());
		panel.setLocation(oldPoint);
	}
}
