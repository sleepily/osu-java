package com.sleepingdreamlessly.osu.input;

public class Input
{
	public String id;
	
	public boolean click, hold, release, isNewInput;
	
	public Input(String id)
	{
		this.id = id;
		
		this.click = false;
		this.hold = false;
		this.release = false;
		this.isNewInput = true;
	}
	
	public void update(boolean active)
	{
		if (active)
		{
			this.release = false;
			
			if (!this.isNewInput)
			{
				this.click = false;
				this.hold = true;
				return;
			}
			
			this.click = true;
			this.hold = true;
			this.isNewInput = false;
			
			return;
		}
		
		if (this.isNewInput)
			return;
		
		if (this.release)
		{
			this.release = false;
			this.isNewInput = true;
			return;
		}
		
		this.release = true;
	}
}
