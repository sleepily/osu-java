package com.sleepingdreamlessly.osu.input.mouse;

import com.sleepingdreamlessly.osu.input.InputManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener
{
	public InputManager inputManager;
	
	public boolean[] mouseButtons = new boolean[4];
	public boolean[] mouseButtonsClicked = new boolean[4];
	public boolean enter, exit = false;
	public boolean move = false;
	
	public MouseManager()
	{
		for (int m = 0; m < 3; m++)
		{
			mouseButtons[m] = false;
			mouseButtonsClicked[m] = false;
		}
	}
	
	public void tick()
	{
		for (int m = 0; m < 3; m++)
			mouseButtonsClicked[m] = false;
		
		this.move = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		this.mouseButtonsClicked[e.getButton()] = true;
		
		System.out.println("Mouse button " + e.getButton() + " clicked.");
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		this.mouseButtons[e.getButton()] = true;
		
		System.out.println("Mouse button " + e.getButton() + " held.");
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		this.mouseButtons[e.getButton()] = false;
		
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
