package com.sleepingdreamlessly.osu.input;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/*
	by https://stackoverflow.com/users/878080/jonathan
	modified and cleaned up by me
 */
public class MidiManager
{
	private ArrayList<MidiDevice> devices = new ArrayList<>();

  public MidiManager()
	{
		initializeDevices();
	}
	
	public void initializeDevices()
	{
		MidiDevice device;
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		
		for (int i = 0; i < infos.length; i++)
		{
			try
			{
				device = MidiSystem.getMidiDevice(infos[i]);
				
				if (device.getTransmitters().size() == 0)
					continue;
				
				System.out.println(infos[i] + ": " + device.getTransmitters().size());
				devices.add(device);
			}
			catch (MidiUnavailableException e)
			{
			
			}
		}
		
		if (devices.size() == 0)
			return;
		
		for (MidiDevice md : devices)
		{
			try
			{
				List<Transmitter> transmitters = md.getTransmitters();
				
				for (Transmitter t : transmitters)
					t.setReceiver(new MidiInputReceiver(md.getDeviceInfo().toString()));
				
				md.open();
				
				System.out.println(md.getDeviceInfo() + " was opened successfully.");
			}
			catch (MidiUnavailableException e)
			{
			
			}
		}
	}
	
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
}
