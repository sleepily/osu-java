package com.sleepingdreamlessly.osu.audio;

import com.sleepingdreamlessly.osu.graphics.Assets;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class AudioFile
{
	private String path;
	private AudioInputStream stream;
	private File f;
	
	public AudioFile(String id)
	{
		try
		{
			this.path = System.getProperty("user.dir") + new Assets().getSkinPath() + (id + ".png");
			this.f = new File(this.path);
			stream = AudioSystem.getAudioInputStream(f);
		}
		catch (UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
