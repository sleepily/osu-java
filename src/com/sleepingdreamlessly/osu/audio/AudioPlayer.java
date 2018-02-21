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
				e.printStackTrace();
			}
		})
			.start();
	}
}