package com.sleepingdreamlessly.osu.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private boolean[] keys;
	public boolean std_left, std_right;
	public boolean mania_0, mania_1, mania_2, mania_3;
	public boolean mania_vol_l_counter, mania_vol_l_clock, mania_vol_r_counter, mania_vol_r_clock, mania_fx_l, mania_fx_r;
	
	public KeyManager()
	{
		keys = new boolean[256];
	}
	
	public void tick(){
		std_left = keys[KeyEvent.VK_X];
		std_right = keys[KeyEvent.VK_C];
		mania_0 = keys[KeyEvent.VK_D];
		mania_1 = keys[KeyEvent.VK_F];
		mania_2 = keys[KeyEvent.VK_J];
		mania_3 = keys[KeyEvent.VK_K];
		mania_vol_l_counter = keys[KeyEvent.VK_Q];
		mania_vol_l_clock = keys[KeyEvent.VK_W];
		mania_vol_r_counter = keys[KeyEvent.VK_O];
		mania_vol_r_clock = keys[KeyEvent.VK_P];
		mania_fx_l = keys[KeyEvent.VK_V];
		mania_fx_r = keys[KeyEvent.VK_N];
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()] = true;
		System.out.println("KEY pressed: " + e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}
	
}
