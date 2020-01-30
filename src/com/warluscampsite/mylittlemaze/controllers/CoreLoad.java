package com.warluscampsite.mylittlemaze.controllers;

public class CoreLoad {
	String name;

	double numberForAverage;
	int numberOverOneMillis, numberOverTwoMillis, numberOverFiveMillis, numberOverTenMillis, numberOverTwentyMillis;
	long nanoSecondsOfExecution;

	long longestCall;

	final int NUMBER_FOR_PRINT_OUT_STATS = 1500;

	public CoreLoad(String name) {
		this.name = name;
		numberForAverage = 0;
		nanoSecondsOfExecution = 0;
		numberOverOneMillis = 0;
		numberOverTwoMillis = 0;
		numberOverFiveMillis = 0;
		numberOverTenMillis = 0;
		numberOverTwentyMillis = 0;
	}

	public void addForStats(long l) {
		if (numberForAverage > 2000000000 || nanoSecondsOfExecution >= 10000000000000L)
			reset();

		numberForAverage += 1;
		nanoSecondsOfExecution += l;

		if (longestCall < l && numberForAverage > 2000)
			longestCall = l;

		if (l > 1000000) {
			numberOverOneMillis++;
			if (l > 2000000) {
				numberOverTwoMillis++;
				if (l > 5000000) {
					numberOverFiveMillis++;
					if (l > 10000000) {
						numberOverTenMillis++;
						if (l > 20000000) {
							numberOverTwentyMillis++;
						}
					}
				}
			}
		}
		if (numberForAverage % NUMBER_FOR_PRINT_OUT_STATS == 0)
			printStats();
	}

	private void printStats() {
		System.out.println("Average from " + numberForAverage + " execution. Average time for " + name + " task: "
				+ MyStringFormatter.formatDouble(nanoSecondsOfExecution / numberForAverage / 1000, 1)
				+ " us and core is working for "
				+ MyStringFormatter.formatDoubleAsPercentage(nanoSecondsOfExecution / (numberForAverage * 20000000.0),
						3)
				+ ". Above 1 ms "
				+ MyStringFormatter.formatDoubleAsPercentage(numberOverOneMillis / numberForAverage, 3) + "("
				+ numberOverOneMillis + ")" + " Above 2 ms "
				+ MyStringFormatter.formatDoubleAsPercentage(numberOverTwoMillis / numberForAverage, 3) + "("
				+ numberOverTwoMillis + ")" + " Above 5 ms "
				+ MyStringFormatter.formatDoubleAsPercentage(numberOverFiveMillis / numberForAverage, 3) + "("
				+ numberOverFiveMillis + ")" + " Above 10 ms "
				+ MyStringFormatter.formatDoubleAsPercentage(numberOverTenMillis / numberForAverage, 3) + "("
				+ numberOverTenMillis + ")" + " Above 20 ms "
				+ MyStringFormatter.formatDoubleAsPercentage(numberOverTwentyMillis / numberForAverage, 3) + "("
				+ numberOverTwentyMillis + ")");
	}

	private void reset() {
		numberForAverage = 0;
		nanoSecondsOfExecution = 0;

	}

	/*****
	 * 
	 * 
	 * getters and setters
	 * 
	 * 
	 */
}
