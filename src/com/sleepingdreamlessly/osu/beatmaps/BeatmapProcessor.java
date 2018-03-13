package com.sleepingdreamlessly.osu.beatmaps;

import java.io.*;
import java.util.ArrayList;

public class BeatmapProcessor
{
	public static File processOszFile(File f)
	{
		//TODO: continue this
		return f;
	}
	
	public static ArrayList<ArrayList<String>> getHitObjectLinesFromOsuFile(File f)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			String line;
			ArrayList<ArrayList<String>> lines = new ArrayList<>();
			
			int index = 0;
			boolean hitobjectsReached = false;
			
			while ((line = br.readLine()) != null)
			{
				if (!line.equals("[HitObjects]") && !hitobjectsReached)
					continue;
				
				if (!hitobjectsReached)
				{
					hitobjectsReached = true;
					continue;
				}
				
				lines.add(new ArrayList<>());
				
				String[] split = line.split(",");
				
				for (String s : split)
					lines.get(index).add(s);
				
				index++;
			}
			
			br.close();
			
			return lines;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<ArrayList<String>> getGeneralInformation(File f)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			String line;
			ArrayList<ArrayList<String>> lines = new ArrayList<>();
			
			int index = 0;
			boolean generalInformationReached = false;
			
			while ((line = br.readLine()) != null)
			{
				if (!line.equals("[General]") && !generalInformationReached)
					continue;
				
				if (!generalInformationReached)
				{
					generalInformationReached = true;
					continue;
				}
				
				lines.add(new ArrayList<>());
				
				String[] split = line.split(": ");
				
				for (String s : split)
					lines.get(index).add(s);
				
				index++;
			}
			
			br.close();
			
			return lines;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<ArrayList<String>> getDifficultyInformation(File f)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			String line;
			ArrayList<ArrayList<String>> lines = new ArrayList<>();
			
			int index = 0;
			boolean generalInformationReached = false;
			
			while ((line = br.readLine()) != null)
			{
				if (!line.equals("[Difficulty]") && !generalInformationReached)
					continue;
				
				if (!generalInformationReached)
				{
					generalInformationReached = true;
					continue;
				}
				
				lines.add(new ArrayList<>());
				
				String[] split = line.split(":");
				
				for (String s : split)
					lines.get(index).add(s);
				
				index++;
			}
			
			br.close();
			
			return lines;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
