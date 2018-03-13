package com.sleepingdreamlessly.osu.audio;

import com.sleepingdreamlessly.osu.Handler;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer
{
	public static synchronized void play(Handler handler, AudioClip input)
	{
		input.start();
		
		input.startTime = handler.getGame().getTime_rel_current_ms();
	}
	
	public static long getPosition(Handler handler, AudioClip input)
	{
		try
		{
			AudioInputStream stream = input.getAudioStream();
			
			Clip clip = AudioSystem.getClip();
			
			clip.open(stream);
			
			long position = Math.round(clip.getMicrosecondPosition() / 1000);
			
			System.out.println(position);
			
			if (position != 0)
				return position;
			
			return handler.getGame().getTime_rel_current_ms() - input.startTime;
		}
		catch (Exception e)
		{
			// Not printing this error every time since it would flood the log
			// System.err.println(e.getMessage());
		}
		
		return 0;
	}
	
	private void noAudioDeviceMessage(Handler handler)
	{
		if (handler.getGame().noAudioDeviceMessageShown)
			return;
		
		handler.getGame().noAudioDeviceMessageShown = true;
		System.err.println("No audio device found. Will use system clock.");
	}
}
