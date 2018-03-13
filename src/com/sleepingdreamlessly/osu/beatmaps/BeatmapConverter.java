package com.sleepingdreamlessly.osu.beatmaps;

import java.util.ArrayList;

import com.sleepingdreamlessly.osu.objects.mania.ManiaHitObject;
import com.sleepingdreamlessly.osu.objects.std.OsuHitCircle;
import com.sleepingdreamlessly.osu.utils.BitMap;

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
			int type = Integer.valueOf(line.get(3));
			
			/*
					type is a bitmap specifying the object type and attributes.
				Bit 0 (1): circle.
				Bit 1 (2): slider.
				Bit 2 (4): new combo.
				Bit 3 (8): spinner.
				Bits 4-6 (16, 32, 64) form a 3-bit number (0-7) that chooses how many combo colours to skip.
				Bit 7 (128) is for an osu!mania hold note.
			 */
			
			boolean[] typeBitmap = BitMap.asArray(8, type);
			
			if (typeBitmap[3])
				return; // spinner
			
			if (typeBitmap[2])
				combo = 0; // new combo
			combo++;
			
			if (typeBitmap[1])
				return; // slider
			
			if (typeBitmap[0])
				b._hitobjects.add(new OsuHitCircle(b.handler, "hitcircle", x, y, time + b.AudioLeadIn + offset, combo));
		}
		
		System.out.println(String.format("Finished converting %s - %s [%s]", b.artist, b.title, b.author));
	}
	
	public static void readGeneralInformationFromLines(ArrayList<ArrayList<String>> lines, Beatmap b)
	{
		b.AudioFilename = lines.get(0).get(1);
		b.AudioLeadIn = Long.valueOf(lines.get(1).get(1));
		b.PreviewTime = Long.valueOf(lines.get(2).get(1));
		b.Countdown = (Integer.valueOf(lines.get(3).get(1)) == 1);
	}
}
