package com.sleepingdreamlessly.osu.input.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener
{
	public boolean[] keys;
	
	public KeyboardManager()
	{
		keys = new boolean[256];
	}
	
	public void tick()
	{
	
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
