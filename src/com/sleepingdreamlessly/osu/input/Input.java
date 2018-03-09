package com.sleepingdreamlessly.osu.input;

public class Input
{
	public String id;
	
	public boolean click, hold, release;
	
	public Input(String id)
	{
		this.id = id;
		
		this.click = false;
		this.hold = false;
		this.release = false;
	}
	
	public void update(boolean active)
	{
		if (!active)
		{
			this.click = false;
			this.hold = false;
			
			if (this.release)
				this.release = false;
			else
				this.release = true;
			
			return;
		}
		
		// set to hold if already clicked
		if (this.click)
		{
			this.click = false;
			this.hold = true;
			this.release = false;
			return;
		}
		
		// set to click if not yet clicked
		this.click = true;
		this.hold = false;
		this.release = false;
	}
}
