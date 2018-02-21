package com.sleepingdreamlessly.osu.audio;

import com.sleepingdreamlessly.osu.assets.Assets;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioClip
{
	public String id;
	private File file;
	
	public AudioClip(String id)
	{
		this.id = id;
		this.load();
	}
	
	private File load()
	{
		String samplePath = System.getProperty("user.dir") + new Assets().getSkinPath() + id;
		
		String audioformat = ".wav";
		file = new File(samplePath + audioformat);
		
		System.out.println("Loading " + String.format("%1$26s", id) + " at " + samplePath + audioformat);
		return file;
		
		// System.out.println("ERROR >> Couldn't find " + this.id + " sample at " + samplePath + ".wav");
		// return null;
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
