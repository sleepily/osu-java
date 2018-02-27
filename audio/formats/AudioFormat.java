package com.sleepingdreamlessly.osu.audio.formats;

public class AudioFormat
{
	public static String ID, NAME;
	
	//@TODO: make this iterable for when loading samples in Assets
	
	public AudioFormat(String id)
	{
		ID = id;
		
		NAME =
			(id.equals(".wav" )) ? "Waveform Audio File Format" :
			(id.equals(".mp3" )) ? "MP3" :
			(id.equals(".flac")) ? "Free Lossless Audio Codec" :
			(id.equals(".ogg" )) ? "Ogg Vorbis" :
			"unknown";
	}
}
