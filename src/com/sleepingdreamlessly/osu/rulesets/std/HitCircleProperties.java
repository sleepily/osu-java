package com.sleepingdreamlessly.osu.rulesets.std;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.objects.GameObject;
import com.sleepingdreamlessly.osu.objects.HitObject;
import com.sleepingdreamlessly.osu.objects.OsuHitObject;

public class HitCircleProperties
{
	private int combo = 1;
	
	private Game game;
	
	public HitCircleProperties(Game game)
	{
		this.game = game;
	}
	
	public void getOsuComboNumbers()
	{
		int index = -1;
		for (HitObject h : this.game._hitobjects)
		{
			index++; // starts at 0
			
			if (h.getClass().getCanonicalName() != OsuHitObject.class.getCanonicalName())
				continue;
			
			OsuHitObject o = new OsuHitObject(h);
			o.combo = combo;
			combo++;
			
			System.out.println(combo);
			
			System.out.println(combo);
			this.game._hitobjects.set(index, o);
		}
		
		for (GameObject o : game.getHitObjects())
		{
		
		}
	}
}
