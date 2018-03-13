package com.sleepingdreamlessly.osu.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioThread extends Thread
{
	AudioClip clip;
	
	public AudioThread(AudioClip clip)
	{
		this.clip = clip;
	}
	
	public void play()
	{
		try
		{
			AudioInputStream stream = clip.getAudioStream();
			
			Clip clip = AudioSystem.getClip();
			
			clip.open(stream);
			clip.start();
		}
		catch (Exception e)
		{
			// System.err.println(e.getMessage());
			clip.handler.getGame().noAudioDevice = true;
		}
	}
}
