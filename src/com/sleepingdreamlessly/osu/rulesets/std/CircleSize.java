package com.sleepingdreamlessly.osu.rulesets.std;

import com.sleepingdreamlessly.osu.objects.OsuHitObject;
import com.sleepingdreamlessly.osu.utils.Utils;

public class CircleSize
{
	public static double circleScale_hitCircle(OsuHitObject h)
	{
		// https://www.reddit.com/r/osugame/comments/6phntt/difficulty_settings_table_with_all_values/
		return (1.0f - 0.7f * (h.CS - 5) / 5) / 2;
	}
	
	public static double circleSize_hitCircle(OsuHitObject h)
	{
		// https://www.reddit.com/r/osugame/comments/6phntt/difficulty_settings_table_with_all_values/
		// System.out.println(Double.toString((32 * (1 - 0.7 * (CS - 5) / 5))));
		// other formula (109 - 9 * CS) ?
		return h.OBJECT_RADIUS * circleScale_hitCircle(h);
	}
	
	public static double circleSize_approachCircle(OsuHitObject h)
	{
		// https://www.reddit.com/r/osugame/comments/6phntt/difficulty_settings_table_with_all_values/
		// System.out.println(Double.toString((32 * (1 - 0.7 * (CS - 5) / 5))));
		float scale =
			Utils.map
			(
				h.handler.getGame().beatmap.song.position,
				Timings.getTimeForCircle_fadeIn(h.AR, h.time),
				h.time,
				0f,
				1f
			);
		return Utils.mapAndClamp(scale, 1, 0, 1, 3) * circleSize_hitCircle(h); // (32 * (1 - 0.7 * (CS - 5) / 5));
	}
}
