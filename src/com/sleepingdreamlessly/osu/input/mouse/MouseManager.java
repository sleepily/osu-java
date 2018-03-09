package com.sleepingdreamlessly.osu.input.mouse;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.input.InputManager;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.utils.Vector2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener
{
	public InputManager inputManager;
	
	public boolean[] mouseButtons = new boolean[4];
	public boolean enter, exit = false;
	public boolean move = false;
	
	public Vector2 pos = new Vector2();
	
	public MouseManager(Handler handler)
	{
		for (int m = 0; m < 3; m++)
		{
			mouseButtons[m] = false;
		}
	}
	
	public void tick()
	{
		/*
		for (int m = 0; m < mouseButtons.length; m++)
			mouseButtons[m] = false;
			*/
		
		this.move = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{

	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		this.mouseButtons[e.getButton()] = true;
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		this.mouseButtons[e.getButton()] = false;
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
	
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
	
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
	
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		this.move = true;
		
		this.pos = new Vector2(e.getPoint().x, e.getPoint().y).sub(UI.getPlayfieldPadding());
	}
}
