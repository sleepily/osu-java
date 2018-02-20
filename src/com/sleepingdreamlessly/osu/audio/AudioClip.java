package com.sleepingdreamlessly.osu.audio;

import com.sleepingdreamlessly.osu.audio.formats.AudioFormat;
import com.sleepingdreamlessly.osu.audio.formats.AudioFormats;
import com.sleepingdreamlessly.osu.assets.Assets;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;

public class AudioClip
{
	private String id;
	private File file;
	
	public AudioClip(String id)
	{
		this.id = id;
	}
	
	private File loadFile()
	{
		for (AudioFormat af : AudioFormats._FORMATS())
		{
			file = new File(Assets.getSkinPath() + this.id + af.ID);
		
			if (!file.exists())
				continue;
			
			return file;
		}
		
		return null;
	}
	
	/*
		Convert file into AudioStream
	 */
	public AudioInputStream getAudioStream()
	{
		try
		{
			return AudioSystem.getAudioInputStream(file);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
