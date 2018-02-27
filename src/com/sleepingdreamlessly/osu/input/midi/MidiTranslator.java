package com.sleepingdreamlessly.osu.input.midi;

import javax.sound.midi.MidiDevice;

public class MidiTranslator
{
	private MidiDevice device;
	
	//@TODO: implement scan mechanic
	public boolean std_left, std_right;
	public boolean mania_0, mania_1, mania_2, mania_3;
	public boolean mania_vol_l_counter, mania_vol_l_clock, mania_vol_r_counter, mania_vol_r_clock, mania_fx_l, mania_fx_r;
	
	public MidiTranslator()
	{
	
	}
	
	public void setDevice(MidiDevice device)
	{
		this.device = device;
	}
	
	public void tick()
	{
		//@TODO: scan midi input and send to keyboardManager
	}
}
