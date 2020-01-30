package com.warluscampsite.mylittlemaze.controllers;

import java.text.NumberFormat;

public class MyStringFormatter {

	static public String formatDouble(double number, int placeAfterDigit) {
		if (placeAfterDigit < 0)
			placeAfterDigit = 0;

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(placeAfterDigit);
		nf.setMinimumFractionDigits(placeAfterDigit);

		return nf.format(number);
	}

	static public String formatDoubleAsPercentage(double number, int placeAfterDigit) {
		if (placeAfterDigit < 0)
			placeAfterDigit = 0;

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(placeAfterDigit);
		nf.setMinimumFractionDigits(placeAfterDigit);

		return nf.format(number * 100) + "%";
	}

	static public String formatAsAPS(int millis, int placeAfterDigit) {
		if (placeAfterDigit < 0)
			placeAfterDigit = 0;

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(placeAfterDigit);
		nf.setMinimumFractionDigits(placeAfterDigit);

		return formatDouble(1 / (millis / 1000.0), placeAfterDigit);
	}

	static public String calcDifferenceInAPS(int old, int newInterv, int placeAfterDigit) {
		if (placeAfterDigit < 0)
			placeAfterDigit = 0;

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(placeAfterDigit);
		nf.setMinimumFractionDigits(placeAfterDigit);

		return formatDouble((1 / (newInterv / 1000.0)) - (1 / (old / 1000.0)), placeAfterDigit);
	}

}
