package com.sleepingdreamlessly.osu.rulesets.judgement;

import com.sleepingdreamlessly.osu.objects.mania.ManiaHitObject;

public class ManiaJudgement
{
	public static int getJudgement(ManiaHitObject h)
	{
		long time = h.handler.getGame().getTime_rel_current_ms();
		double hitWindow_perfect = 64  - (h.OD * 3) + .5;
		
		/*
		  osu!mania 300 window 	64  - (OD x 3) + 0,5
			osu!mania 200 window 	97  - (OD x 3) + 0,5
			osu!mania 100 window 	127 - (OD x 3) + 0,5
			osu!mania 50  window 	151 - (OD x 3) + 0,5
		 */
		
		if (time > h.time_hit - hitWindow_perfect ||
				time < h.time_hit + hitWindow_perfect)
			return 300;
		
		return 0;
	}
}
