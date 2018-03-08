package com.sleepingdreamlessly.osu.audio;

import com.sleepingdreamlessly.osu.Handler;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer
{
	public static synchronized void play(Handler handler, AudioClip input)
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
		input.startTime = handler.getGame().getTime_rel_current_ms();
	}
	
	public static long getPosition(Handler handler, AudioClip input)
	{
		try
		{
			AudioInputStream stream = input.getAudioStream();
			
			Clip clip = AudioSystem.getClip();
			
			long position = Math.round(clip.getMicrosecondPosition() / 1000);
			
			if (position != 0)
				return position;
			
			return handler.getGame().getTime_rel_current_ms() - input.startTime;
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		return 0;
	}
}
