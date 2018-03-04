package com.sleepingdreamlessly.osu.beatmaps;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.objects.GameObject;
import com.sleepingdreamlessly.osu.objects.HitObject;
import com.sleepingdreamlessly.osu.rulesets.UI;

import java.util.ArrayList;

public class Beatmap
{
	public Handler handler;
	
	public ArrayList<HitObject> _hitobjects = new ArrayList<>();
	
	public String artist, title, author;
	
	public Beatmap(Handler handler)
	{
		this.handler = handler;
		this.init();
	}
	
	private void init()
	{
		BeatmapConverter.readDummy(this);
	}
	
	public void tick()
	{
		for (HitObject h : _hitobjects)
		{
			h.tick();
			
			if (h.dispose)
				if (!handler.getGame().garbageCollector.hasHitObjectCollected(h))
					this.handler.getGame().garbageCollector.collect(h);
		}
	}
	
	public void render(UI ui)
	{
		// @TODO: render objects backwards (use object time as depth)
		for (GameObject h : _hitobjects)
			h.render(ui);
	}
	
	public void removeHitObject(HitObject h)
	{
		this._hitobjects.remove(h);
	}
}
