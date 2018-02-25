package com.sleepingdreamlessly.osu.rulesets.std;

import com.sleepingdreamlessly.osu.utils.Utils;

public class CircleSize
{
	public static double circleScale_hitCircle(double CS, int width)
	{
		// https://www.reddit.com/r/osugame/comments/6phntt/difficulty_settings_table_with_all_values/
		return (double)(32 * (1 - 0.7 * (CS - 5) / 5)) / width;
	}
	
	public static double circleSize_hitCircle(double CS)
	{
		// https://www.reddit.com/r/osugame/comments/6phntt/difficulty_settings_table_with_all_values/
		// System.out.println(Double.toString((32 * (1 - 0.7 * (CS - 5) / 5))));
		return (double)(109 - 9 * CS); // return (double)(32 * (1 - 0.7 * (CS - 5) / 5));
	}
	
	public static double circleSize_approachCircle(double CS, double AR, long time_current, long time_hitcircle)
	{
		// https://www.reddit.com/r/osugame/comments/6phntt/difficulty_settings_table_with_all_values/
		// System.out.println(Double.toString((32 * (1 - 0.7 * (CS - 5) / 5))));
		float scale = Utils.map(time_current, Timings.getTimeForCircle_fadeIn(AR, time_hitcircle), time_hitcircle, 0f, 1f);
		return Utils.mapAndClamp(scale, 1, 0, 1, 3) * circleSize_hitCircle(CS); // (32 * (1 - 0.7 * (CS - 5) / 5));
	}
}
