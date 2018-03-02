package com.sleepingdreamlessly.osu.rulesets.judgement;

import com.sleepingdreamlessly.osu.objects.OsuHitObject;

public class OsuJudgement
{
	public static int getJudgement(OsuHitObject h)
	{
		double[] hitwindows = getHitWindows(h);
		
		double hitWindow_perfect = hitwindows[0];
		double hitWindow_good = hitwindows[1];
		double hitWindow_meh = hitwindows[2];
		double hitWindow_notelock = hitwindows[3];
		
		if (h.time_hit < h.time - hitWindow_notelock)
			return -1;
		
		if (h.time_hit > h.time - hitWindow_perfect && h.time_hit < h.time + hitWindow_perfect)
			return 300;
		
		if (h.time_hit > h.time - hitWindow_good && h.time_hit < h.time + hitWindow_good)
			return 100;
		
		if (h.time_hit > h.time - hitWindow_meh && h.time_hit < h.time + hitWindow_meh)
			return 50;
		
		return 0;
	}
	
	public static double[] getHitWindows(OsuHitObject h)
	{
		double[] hitwindows = new double[4];
		
		hitwindows[0] = 79 - (h.OD * 6) + .5;
		hitwindows[1] = 139 - (h.OD * 8) + .5;
		hitwindows[2] = 199 - (h.OD * 10) + .5;
		hitwindows[3] = hitwindows[2] * 2;
		
		return hitwindows;
	}
}
