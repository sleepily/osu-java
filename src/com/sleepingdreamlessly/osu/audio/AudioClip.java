package com.sleepingdreamlessly.osu.audio;

import com.sleepingdreamlessly.osu.graphics.Assets;

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
}
