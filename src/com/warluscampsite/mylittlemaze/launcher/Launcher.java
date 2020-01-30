package com.warluscampsite.mylittlemaze.launcher;

import java.awt.Insets;

import javax.swing.UIManager;

import com.warluscampsite.mylittlemaze.controllers.TimeController;
import com.warluscampsite.mylittlemaze.data.Data;

public class Launcher {

	public static void main(String[] args) {
		UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
		UIManager.put("TabbedPane.contentOpaque", false);

		TimeController.addNewKey("�adowanie all data");
		new Data();
		TimeController.getTimeOfRunning("�adowanie all data");
	}

}
