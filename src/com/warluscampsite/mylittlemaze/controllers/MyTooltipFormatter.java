package com.warluscampsite.mylittlemaze.controllers;

import com.warluscampsite.mylittlemaze.loot.Item;

public class MyTooltipFormatter {

	public static String formatTooltipForItem(Item item) {
		String tooltip = "<html><style type=\"text/css\">\r\n"
				+ "body {border-style: double; border-width: 4px; padding:10px; font-size: 10px; background-image: url(\"file:res/tooltipBg.png\"); background-position: center; background-repeat: no-repeat\r\n}\r\n"
				+ "</style><body>" + item.getToolTip() + "</body></html>";
		return tooltip;
	}

	public static String formatTooltipForBigSkill(String name, String description) {
		StringBuilder string = new StringBuilder();

		string.append("<html><style type=\"text/css\">\r\n"
				+ "body {width: 225px; text-align:justify; border-style: double; border-width: 4px; padding:10px; font-size: 10px; background-image: url(\"file:res/tooltipBg.png\"); background-position: center; background-repeat: no-repeat\r\n}\r\n"
				+ "</style><body>");

		string.append("<h3><center>" + name + "</h3></center>");
		string.append(description);

		string.append("</body></html>");

		return string.toString();
	}

	public static String formatTooltipForUpgrade(String description) {
		StringBuilder string = new StringBuilder();

		string.append("<html><style type=\"text/css\">\r\n"
				+ "body {width: 160px; text-align:justify; border-style: double; border-width: 4px; padding:15px; font-size: 10px; font-color: black; background-image: url(\"file:res/tooltipBg.png\"); background-position: center; background-repeat: no-repeat\r\n}\r\n"
				+ "</style><body>");

		string.append(description);

		string.append("</body></html>");

		return string.toString();
	}

	public static String formatTooltipForPassives(String description) {
		StringBuilder string = new StringBuilder();

		string.append("<html><style type=\"text/css\">\r\n"
				+ "body {width: 160px; text-align:justify; border-style: double; border-width: 4px; padding:15px; font-size: 10px; font-color: black; background-image: url(\"file:res/tooltipBg.png\"); background-position: center; background-repeat: no-repeat\r\n}\r\n"
				+ "</style><body>");

		string.append(description);

		string.append("</body></html>");

		return string.toString();
	}

	/*******
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
}
