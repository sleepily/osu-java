package com.sleepingdreamlessly.osu.audio;

import javax.sound.sampled.*;
import java.util.ArrayList;

public class AudioManager
{
	public Mixer mixer;
	public static ArrayList<Clip> clips;
	
	public AudioManager()
	{
		Mixer.Info[] mixInfo = AudioSystem.getMixerInfo();
		mixer = AudioSystem.getMixer(mixInfo[0]);
		
		DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
		try
		{
			clips.add((Clip)mixer.getLine(dataInfo));
		}
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}
}
