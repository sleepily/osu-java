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
		// System.out.println("KEY down: " + e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
		// System.out.println("KEY up:   " + e.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}
	
}
