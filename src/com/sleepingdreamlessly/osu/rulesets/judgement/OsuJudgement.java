package com.sleepingdreamlessly.osu.rulesets.judgement;

import com.sleepingdreamlessly.osu.objects.OsuHitObject;

public class OsuJudgement
{
	public static int getJudgement(OsuHitObject h)
	{
		double hitWindow_perfect = 79 - (h.OD * 6) + .5;
		double hitWindow_good = 139 - (h.OD * 8) + .5;
		double hitWindow_meh = 199 - (h.OD * 10) + .5;
		double hitWindow_notelock = hitWindow_meh * 2;
		
		if (h.time_hit < h.time - hitWindow_notelock)
			return -1;
		
		if (h.time_hit > h.time - hitWindow_perfect && h.time_hit < h.time + hitWindow_perfect)
			return 300;
		
		if (h.time_hit > h.time - hitWindow_good && h.time_hit < h.time + hitWindow_good)
			return 200;
		
		if (h.time_hit > h.time - hitWindow_meh && h.time_hit < h.time + hitWindow_meh)
			return 100;
		
		return 0;
	}
}
