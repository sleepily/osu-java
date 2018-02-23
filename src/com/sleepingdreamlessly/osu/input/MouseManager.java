package com.sleepingdreamlessly.osu.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener
{
	public boolean[] click, hold, release = new boolean[3];
	public boolean enter, exit = false;
	public boolean move = false;
	
	public MouseManager()
	{
	
	}
	
	public void tick()
	{
		for (int m = 0; m < 3; m++)
		{
			click[m] 		= false;
			hold[m] 		= false;
			release[m] 	= false;
		}
		
		this.move = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		this.click[e.getButton()] = true;
		System.out.println("Mouse button " + e.getButton() + " clicked.");
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		this.hold[e.getButton()] = true;
		System.out.println("Mouse button " + e.getButton() + " held.");
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		this.release[e.getButton()] = true;
		System.out.println("Mouse button " + e.getButton() + " released.");
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
		System.out.println("Mouse moved.");
	}
}
