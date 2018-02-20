package com.sleepingdreamlessly.osu.audio;

import com.sleepingdreamlessly.osu.graphics.Assets;

import java.io.File;

public class AudioClip
{
	private File file;
	
	public AudioClip(String id)
	{
		file = new File(Assets.getSkinPath() + id);
	}
}
