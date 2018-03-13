package com.sleepingdreamlessly.osu.objects;

import com.sleepingdreamlessly.osu.Handler;

import java.util.ArrayList;

public class HitObjectGarbageCollector
{
	private Handler handler;
	
	private ArrayList<HitObject> _hitobjectGarbageCollected = new ArrayList<>();
	
	private long time_garbageCollection_interval_ms = 40;
	private long time_garbageCollection_last = 0;
	private boolean garbageCollection_inProgress = false;
	
	public HitObjectGarbageCollector(Handler handler)
	{
		this.handler = handler;
	}
	
	public void tick()
	{
		if (!garbageCollection_inProgress)
			garbageCollection();
	}
	
	public boolean hasHitObjectCollected(HitObject h)
	{
		return this._hitobjectGarbageCollected.contains(h);
	}
	
	private void garbageCollection()
	{
		if (time_garbageCollection_interval_ms <= handler.getGame().beatmap.song.position - time_garbageCollection_last)
		{
			garbageCollection_inProgress = true;
			
			time_garbageCollection_last += time_garbageCollection_interval_ms;
			
			for (HitObject h : _hitobjectGarbageCollected)
				handler.getGame().beatmap.removeHitObject(h);
			
			_hitobjectGarbageCollected.clear();
			
			garbageCollection_inProgress = false;
		}
	}
	
	public void collect(HitObject h)
	{
		this._hitobjectGarbageCollected.add(h);
	}
}
