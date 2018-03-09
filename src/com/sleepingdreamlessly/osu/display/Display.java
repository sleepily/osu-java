package com.sleepingdreamlessly.osu.display;

import javax.swing.*;

import java.awt.*;

public class Display
{
	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height;
	
	public Display(String title, int width, int height)
	{
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	public void createDisplay()
	{
		Dimension d = new Dimension(width, height);
		
		frame = new JFrame(title);
		
		frame.setSize(d);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		// frame.setUndecorated(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(d);
		canvas.setMaximumSize(d);
		canvas.setMinimumSize(d);
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}
	
	public void show()
	{
		// AWTUtilities.setWindowOpacity(frame, .2f);
		frame.setVisible(true);
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	public Canvas getCanvas()
	{
		return canvas;
	}
}
