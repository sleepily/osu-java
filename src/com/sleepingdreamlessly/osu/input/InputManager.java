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
	
	public Input std_mouse_left, std_mouse_right;
	public Input std_left, std_right;
	public Input mania_0, mania_1, mania_2, mania_3, mania_fx_l, mania_fx_r;
	public Input[] maniaKeys;
	public Input mania_vol_l_counter, mania_vol_l_clock, mania_vol_r_counter, mania_vol_r_clock;
	
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
		
		std_mouse_left = new Input("std_mouse_left");
		std_mouse_right = new Input("std_mouse_right");
		std_left = new Input("std_left");
		std_right = new Input("std_right");
		
		maniaKeys = new Input[6];
		mania_0 = new Input("mania_0");
		mania_1 = new Input("mania_1");
		mania_2 = new Input("mania_2");
		mania_3 = new Input("mania_3");
		mania_fx_l = new Input("mania_fx_l");
		mania_fx_r = new Input("mania_fx_r");
		
		maniaKeys[0] = mania_0;
		maniaKeys[1] = mania_1;
		maniaKeys[2] = mania_2;
		maniaKeys[3] = mania_3;
		maniaKeys[4] = mania_fx_l;
		maniaKeys[5] = mania_fx_r;
		
		mania_vol_l_counter = new Input("mania_vol_l_counter");
		mania_vol_l_clock = new Input("mania_vol_l_clock");
		mania_vol_r_counter = new Input("mania_vol_r_counter");
		mania_vol_r_clock = new Input("mania_vol_r_clock");
	}
	
	public void tick()
	{
		keyboardManager.tick();
		mouseManager.tick();
		// midiManager.tick();
		updateKeys();
	}
	
	public void updateKeys()
	{
		this.std_mouse_left		.update(mouseManager.mouseButtons[1]);
		this.std_mouse_right	.update(mouseManager.mouseButtons[2]);
		
		//@TODO: make these interchangeable with midi input
		this.std_left		.update(keyboardManager.keys[KeyEvent.VK_X]);
		this.std_right	.update(keyboardManager.keys[KeyEvent.VK_C]);
		this.mania_0    .update(keyboardManager.keys[KeyEvent.VK_D]);
		this.mania_1    .update(keyboardManager.keys[KeyEvent.VK_F]);
		this.mania_2    .update(keyboardManager.keys[KeyEvent.VK_J]);
		this.mania_3    .update(keyboardManager.keys[KeyEvent.VK_K]);
		this.mania_fx_l .update(keyboardManager.keys[KeyEvent.VK_V]);
		this.mania_fx_r .update(keyboardManager.keys[KeyEvent.VK_N]);
		this.mania_vol_l_counter  .update(keyboardManager.keys[KeyEvent.VK_Q]);
		this.mania_vol_l_clock    .update(keyboardManager.keys[KeyEvent.VK_W]);
		this.mania_vol_r_counter  .update(keyboardManager.keys[KeyEvent.VK_O]);
		this.mania_vol_r_clock    .update(keyboardManager.keys[KeyEvent.VK_P]);
		
		this.maniaKeys[0] = this.mania_0;
		this.maniaKeys[1] = this.mania_1;
		this.maniaKeys[2] = this.mania_2;
		this.maniaKeys[3] = this.mania_3;
		this.maniaKeys[4] = this.mania_fx_l;
		this.maniaKeys[5] = this.mania_fx_r;
	}
}
