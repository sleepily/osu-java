package com.sleepingdreamlessly.osu.beatmaps;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.assets.Assets;
import com.sleepingdreamlessly.osu.audio.AudioClip;
import com.sleepingdreamlessly.osu.audio.AudioPlayer;
import com.sleepingdreamlessly.osu.objects.GameObject;
import com.sleepingdreamlessly.osu.objects.HitObject;
import com.sleepingdreamlessly.osu.rulesets.UI;

import java.io.File;
import java.util.ArrayList;

public class Beatmap
{
	public Handler handler;
	
	public File file;
	
	public ArrayList<HitObject> _hitobjects = new ArrayList<>();
	
	public AudioClip song;
	public long starttime;
	
	public String AudioFilename;
	public long AudioLeadIn;
	public long PreviewTime;
	public boolean Countdown;
	
	public String artist, title, author;
	
	public Beatmap(Handler handler, String dir, String osuFile)
	{
		this.handler = handler;
		
		this.file = new File(System.getProperty("user.dir") + Assets.getSongsPath() + dir + "\\" + osuFile);
		
		BeatmapConverter.readGeneralInformationFromLines
		(
			BeatmapProcessor.getGeneralInformation(this.file), this
		);
		
		BeatmapConverter.readHitObjectsFromLines
		(
			BeatmapProcessor.getHitObjectLinesFromOsuFile(this.file), this
		);
		
		this.song = new AudioClip(dir + "\\" + AudioFilename, true);
	}
	
	public void start()
	{
		this.starttime = handler.getGame().getTime_rel_current_ms();
		AudioPlayer.play(handler, song);
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
