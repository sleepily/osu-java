package com.sleepingdreamlessly.osu.audio.formats;

public class AudioFormat
{
	public static String ID, NAME;
	
	public AudioFormat(String id)
	{
		ID = id;
		
		NAME =
			(id == ".wav") ? "Waveform Audio File Format" :
			(id == ".mp3") ? "MP3" :
			(id == ".flac") ? "Free Lossless Audio Codec" :
			(id == ".ogg") ? "Ogg Vorbis" :
			"unknown";
	}
}
