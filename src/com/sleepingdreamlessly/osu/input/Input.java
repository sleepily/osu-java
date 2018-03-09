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
	
	public void update(boolean bindingIsActive)
	{
		if (!bindingIsActive)
		{
			this.click = false;
			this.hold = false;
			this.release = true;
			return;
		}
		
		if (this.click)
		{
			this.click = false;
			this.hold = true;
			this.release = false;
			return;
		}
		
		this.hold = true;
		this.release = false;
	}
}
