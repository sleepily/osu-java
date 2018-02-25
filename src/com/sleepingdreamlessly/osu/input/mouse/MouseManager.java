package com.sleepingdreamlessly.osu.input.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener
{
	public boolean click, hold, release = false;
	public boolean enter, exit = false;
	public boolean move = false;
	
	public MouseManager()
	{
	
	}
	
	public void tick()
	{
		this.click = false;
		this.hold = false;
		this.release = false;
		this.move = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		this.click = true;
		System.out.println("Mouse clicked.");
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		this.hold = true;
		System.out.println("Mouse held.");
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		this.release = true;
		System.out.println("Mouse released.");
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
