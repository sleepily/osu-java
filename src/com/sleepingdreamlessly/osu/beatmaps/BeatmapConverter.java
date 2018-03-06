package com.sleepingdreamlessly.osu.beatmaps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.sleepingdreamlessly.osu.objects.mania.ManiaHitObject;
import com.sleepingdreamlessly.osu.objects.std.OsuHitCircle;

public class BeatmapConverter
{
	public BeatmapConverter()
	{
	
	}
	
	public static void read(File f)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(f)))
		{
	    String line;
	    ArrayList<ArrayList<String>> lines = new ArrayList<>();
	    
	    int index = 0;
	    
	    while ((line = br.readLine()) != null)
	    {
	    	String[] split = line.split(",");
	    	
	    	for (String s : split)
	    		lines.get(index).add(s);

	    	for (String s : lines.get(index))
	    		System.out.println(s);
	    	
		    index++;
	    }
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
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
