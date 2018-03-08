package com.sleepingdreamlessly.osu.beatmaps;

import java.util.ArrayList;

import com.sleepingdreamlessly.osu.objects.mania.ManiaHitObject;
import com.sleepingdreamlessly.osu.objects.std.OsuHitCircle;

public class BeatmapConverter
{
	public BeatmapConverter()
	{
	
	}
	
	public static void readHitObjectsFromLines(ArrayList<ArrayList<String>> lines, Beatmap b)
	{
		int combo = 0;
		long offset = b.handler.getGame().offset;
		
		for (ArrayList<String> line: lines)
		{
			int x = Integer.valueOf(line.get(0));
			int y = Integer.valueOf(line.get(1));
			long time = Long.valueOf(line.get(2));
			byte type = Byte.valueOf(line.get(3));
			
			if (type - 4 <= 0)
				combo = 0;
			
			combo++;
			
			b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", x, y, time + b.AudioLeadIn + offset, combo));
		}
	}
	
	public static void readGeneralInformationFromLines(ArrayList<ArrayList<String>> lines, Beatmap b)
	{
		b.AudioFilename = lines.get(0).get(1);
		b.AudioLeadIn = Long.valueOf(lines.get(1).get(1));
		b.PreviewTime = Long.valueOf(lines.get(2).get(1));
		b.Countdown = (Integer.valueOf(lines.get(3).get(1)) == 1);
	}
	
	public static void readDummy(Beatmap b)
	{
		b.artist = "cysmiX";
		b.title = "Tear Rain (feat. Emmy)";
		b.author = "Monstrata";
		
		b._hitobjects.add(new ManiaHitObject(b.handler, "note", 1, 2100));
		b._hitobjects.add(new ManiaHitObject(b.handler, "note", 3, 2300));
		b._hitobjects.add(new ManiaHitObject(b.handler, "note", 1, 2500));
		b._hitobjects.add(new ManiaHitObject(b.handler, "note", 3, 2600));
		b._hitobjects.add(new ManiaHitObject(b.handler, "note", 0, 2700));
		b._hitobjects.add(new ManiaHitObject(b.handler, "note", 2, 2800));
		b._hitobjects.add(new ManiaHitObject(b.handler, "note", 0, 2000));
		b._hitobjects.add(new ManiaHitObject(b.handler, "note", 2, 2200));

		/*
		b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", 140, 200, 4000, 1));
		b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", 230, 214, 4200, 2));
		b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", 310, 134, 4400, 3));
	
		b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", 200, 100, 4600, 1));
		b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", 200, 348, 4900, 2));
		b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", 300, 300, 5200, 3));
		b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", 220, 140, 5500, 4));
		*/
		
		b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", 100, 300, 4000, 1));
		b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", 200, 300, 4500, 2));
		b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", 300, 300, 5000, 3));
		b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", 100, 100, 6000, 1));
		b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", 200, 100, 6500, 2));
		b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", 300, 100, 7000, 3));
		
		System.out.println(String.format("Finished reading %s - %s by %s", b.artist, b.title, b.author));
	}
}
