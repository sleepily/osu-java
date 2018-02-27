package com.sleepingdreamlessly.osu.input;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.input.keyboard.KeyboardManager;
import com.sleepingdreamlessly.osu.input.midi.MidiManager;
import com.sleepingdreamlessly.osu.input.mouse.MouseManager;

import java.awt.event.KeyEvent;

public class InputManager
{
	private Handler handler;
	
	public KeyboardManager keyboardManager;
	public MouseManager mouseManager;
	public MidiManager midiManager;
	
	public boolean std_mouse_left, std_mouse_right;
	public boolean std_left, std_right;
	public boolean mania_0, mania_1, mania_2, mania_3;
	public boolean mania_vol_l_counter, mania_vol_l_clock, mania_vol_r_counter, mania_vol_r_clock, mania_fx_l, mania_fx_r;
	
	public InputManager(Handler handler)
	{
		this.handler = handler;
		this.keyboardManager = new KeyboardManager();
		this.mouseManager = new MouseManager(handler);
		this.midiManager = new MidiManager();
		init();
	}
	
	public void init()
	{
		handler.getGame().getDisplay().getFrame().addKeyListener(keyboardManager);
		handler.getGame().getDisplay().getCanvas().addMouseListener(mouseManager);
		handler.getGame().getDisplay().getCanvas().addMouseMotionListener(mouseManager);
		// midiManager.init(); //@TODO: continue MIDI implementation
	}
	
	public void tick()
	{
		updateKeys();
	}
	
	private void updateKeys()
	{
		this.std_mouse_left = mouseManager.mouseButtons[1];
		this.std_mouse_right = mouseManager.mouseButtons[2];
		
		//@TODO: make these interchangeable with midi input
		this.std_left   = keyboardManager.keys[KeyEvent.VK_X];
		this.std_right  = keyboardManager.keys[KeyEvent.VK_C];
		this.mania_0    = keyboardManager.keys[KeyEvent.VK_D];
		this.mania_1    = keyboardManager.keys[KeyEvent.VK_F];
		this.mania_2    = keyboardManager.keys[KeyEvent.VK_J];
		this.mania_3    = keyboardManager.keys[KeyEvent.VK_K];
		this.mania_vol_l_counter  = keyboardManager.keys[KeyEvent.VK_Q];
		this.mania_vol_l_clock    = keyboardManager.keys[KeyEvent.VK_W];
		this.mania_vol_r_counter  = keyboardManager.keys[KeyEvent.VK_O];
		this.mania_vol_r_clock    = keyboardManager.keys[KeyEvent.VK_P];
		this.mania_fx_l = keyboardManager.keys[KeyEvent.VK_V];
		this.mania_fx_r = keyboardManager.keys[KeyEvent.VK_N];
	}
}
