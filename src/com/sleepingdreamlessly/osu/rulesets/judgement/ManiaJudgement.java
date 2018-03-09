package com.sleepingdreamlessly.osu.rulesets.judgement;

import com.sleepingdreamlessly.osu.objects.mania.ManiaHitObject;

public class ManiaJudgement
{
	public static int getJudgement(ManiaHitObject h)
	{
		/*
		  osu!mania 300 window 	64  - (OD * 3) + 0.5
			osu!mania 200 window 	97  - (OD * 3) + 0.5
			osu!mania 100 window 	127 - (OD * 3) + 0.5
			osu!mania 50  window 	151 - (OD * 3) + 0.5
		 */
		
		double hitWindow_notelock = (220  - (h.OD * 3) + .5 * 2);
		
		if (h.time_hit < h.time - hitWindow_notelock)
			return -1;
		
		double hitWindow_perfect = 16.5; // 64  - (h.OD * 3) + .5;
		
		if (h.time_hit > h.time - hitWindow_perfect && h.time_hit < h.time + hitWindow_perfect)
			return 300;
		
		double hitWindow_great = 97  - (h.OD * 3) + .5;
		
		if (h.time_hit > h.time - hitWindow_great && h.time_hit < h.time + hitWindow_great)
			return 200;
		
		double hitWindow_good = 97  - (h.OD * 3) + .5;
		
		if (h.time_hit > h.time - hitWindow_good && h.time_hit < h.time + hitWindow_good)
			return 200;
		
		double hitWindow_ok = 127  - (h.OD * 3) + .5;
		
		if (h.time_hit > h.time - hitWindow_ok && h.time_hit < h.time + hitWindow_ok)
			return 100;
		
		double hitWindow_meh = 151  - (h.OD * 3) + .5;
		
		if (h.time_hit > h.time - hitWindow_meh && h.time_hit < h.time + hitWindow_meh)
			return 100;
		
		return 0;
	}
}
