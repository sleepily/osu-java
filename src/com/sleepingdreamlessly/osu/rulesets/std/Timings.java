package com.sleepingdreamlessly.osu.rulesets.std;

public class Timings
{
	public static long getTimeForCircle_fadeIn(double AR, long time)
	{
		if (AR <= 5)
			return time - (int)(1800 - (AR * 120));
		
		// else (if AR > 5)
		return time - (int)(1200 - ((AR - 5) * 150));
	}
	
	public static long getTimeForCircle_visible(double AR, long time)
	{
		if (AR <= 5)
			return time - (int)((1800 - (AR * 120)) / 2);
		
		// else (if AR > 5)
		return time - (int)((1200 - ((AR - 5) * 150)) / 2);
	}
}
