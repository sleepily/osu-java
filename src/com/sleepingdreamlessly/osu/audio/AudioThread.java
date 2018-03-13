package com.sleepingdreamlessly.osu.audio;

import com.sleepingdreamlessly.osu.Handler;

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
	
	public long getPosition()
	{
		try
		{
			AudioInputStream ais = this.clip.getAudioStream();
			
			long position = Math.round(this.clip.clip.getMicrosecondPosition() / 1000);
			
			System.out.println(position);
			
			return position;
		}
		catch (Exception e)
		{
			// Not printing this error every time since it would flood the log
			System.err.println(e.getMessage());
		}
		
		return 0;
	}
}
