package com.sleepingdreamlessly.osu.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer
{
	public static synchronized void play(AudioClip input)
	{
		new Thread(() ->
		{
			try
			{
				AudioInputStream stream = input.getAudioStream();
				
				Clip clip = AudioSystem.getClip();
				
				clip.open(stream);
				clip.start();
			}
			catch (Exception e)
			{
				System.err.println(e.getMessage());
			}
		})
		.start();
	}
	
	public static long getPosition(AudioClip input)
	{
		try
		{
			AudioInputStream stream = input.getAudioStream();
			
			Clip clip = AudioSystem.getClip();
			
			return Math.round(clip.getMicrosecondPosition() / 1000);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		return 0;
	}
}
