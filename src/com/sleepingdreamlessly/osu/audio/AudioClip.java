package com.sleepingdreamlessly.osu.audio;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.assets.Assets;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioClip
{
	public Clip clip;
	
	public String id;
	protected File file;
	public boolean isSong = false;
	
	public long startTime;
	public long position = 0;
	
	public AudioClip(String id)
	{
		this.id = id;
		this.load();
	}
	
	public AudioClip(String id, boolean isSong)
	{
		this.id = id;
		this.isSong = isSong;
		this.load();
	}
	
	public File load()
	{
		String songPath =
			this.isSong ?
				System.getProperty("user.dir") + Assets.getSongsPath() + id :
				System.getProperty("user.dir") + Assets.getSkinPath() + id;
		
		//@TODO: implement multiple audio formats
		String audioformat = ".wav";
		this.file = new File(songPath + audioformat);
		
		if (!this.file.exists())
		{
			System.err.println("ERROR >> Couldn't find " + this.id + " song at " + songPath + ".mp3");
			return null;
		}
		
		return this.file;
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
	
	public void tick(Handler handler)
	{
		if (!handler.getGame().noAudioDevice)
		{
			this.position = AudioPlayer.getPosition(handler, this);
			return;
		}
		
		this.position = handler.getGame().getTime_rel_current_ms() - this.startTime;
	}
}
