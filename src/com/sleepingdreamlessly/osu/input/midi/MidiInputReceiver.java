package com.sleepingdreamlessly.osu.input.midi;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class MidiInputReceiver implements Receiver
{
	public String name;
	
	public MidiInputReceiver(String name)
	{
		this.name = name;
	}
	
	public void send(MidiMessage msg, long time)
	{
		System.out.println("MIDI received: " + msg.getLength() + " at " + time + "[ms?]");
	}
	
	public void close()
	{
	
	}
}