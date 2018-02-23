package com.sleepingdreamlessly.osu.input;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;

/*
	by https://stackoverflow.com/users/878080/jonathan
	modified and cleaned up by me
 */
public class MidiManager
{
	private ArrayList<MidiDevice> devices = new ArrayList<>();
	public ArrayList<MidiDevice> devicesOpened = new ArrayList<>();
	public MidiDevice deviceOpened;
	
	private MidiTranslator translator = new MidiTranslator();
	
	public MidiManager()
	{
	
	}
	
	public void init()
	{
		rescan();
	}
	
	public void tick()
	{
		translator.tick();
	}
	
	public void rescan()
	{
		scan();
		open(midiDeviceSelectPrompt());
		translator.setDevice(deviceOpened);
	}
	
	private int midiDeviceSelectPrompt()
	{
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Choose a MIDI device: ");
		return reader.nextInt();
	}
	
	public void scan()
	{
		devices.clear();
		
		System.out.println("Beginning MIDI device scan...");
		MidiDevice device;
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		
		for (int i = 0; i < infos.length; i++)
		{
			try
			{
				device = MidiSystem.getMidiDevice(infos[i]);
				devices.add(device);
				
				String paddedMidiDeviceIndex = String.format("%1$2s", i).replace(' ', '0');
				System.out.println("\tMIDI device " + paddedMidiDeviceIndex + ": " +  infos[i]);
				
				List<Transmitter> transmitters = device.getTransmitters();
				
				for(Transmitter t : transmitters)
					t.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));
			}
			catch (MidiUnavailableException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void open(int midiDeviceIndex)
	{
		open(devices.get(midiDeviceIndex));
	}
	
	public void open(MidiDevice device)
	{
		devicesOpened.clear();
		
		try
		{
			Transmitter trans = device.getTransmitter();
			trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));
			
			device.open();
			// devicesOpened.add(device);
			deviceOpened = device;
			
			System.out.println(device.getDeviceInfo() + " was opened successfully.");
			
			return;
		}
		catch (MidiUnavailableException e)
		{
			System.err.println("ERROR: " + e.getLocalizedMessage());
			System.err.println("Please select a different MIDI device.");
		}
		
		open(midiDeviceSelectPrompt());
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
