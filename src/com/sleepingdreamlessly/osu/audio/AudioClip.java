package com.sleepingdreamlessly.osu.audio;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.assets.Assets;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioClip
{
	public Handler handler;
	
	public AudioThread thread;
	
	public Clip clip;
	
	public String id;
	protected File file;
	public boolean isSong = false;
	
	public long startTime;
	public long position = 0;
	
	public AudioClip(Handler handler, String id)
	{
		this.init(handler, id);
	}
	
	public AudioClip(Handler handler, String id, boolean isSong)
	{
		this.isSong = isSong;
		this.init(handler, id);
	}
	
	private void init(Handler handler, String id)
	{
		this.handler = handler;
		this.id = id;
		this.load();
	}
	
	public File load()
	{
		String songPath =
			this.isSong ?
				System.getProperty("user.dir") + Assets.getSongsPath() + id :
				System.getProperty("user.dir") + Assets.getSkinPath() + id;
		
		//@TODO: implement multiple audio formats
		this.file = this.isSong ? new File(songPath) : new File(songPath + ".wav");
		
		if (!this.file.exists())
		{
			System.err.println("ERROR >> Couldn't find " + this.id + " song at " + songPath);
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
		if (handler.getGame().noAudioDevice)
		{
			this.position = handler.getGame().getTime_rel_current_ms() - this.startTime;
			return;
		}
		
		this.position = this.getPosition();
	}
	
	public void play()
	{
		if (this.thread != null)
			return;
		
		this.startTime = handler.getGame().getTime_rel_current_ms();
		this.thread = new AudioThread(this);
		this.thread.play();
	}
	
	private long getPosition()
	{
		if (this.thread == null)
			return 0;
		
		return this.thread.getPosition();
	}
}
