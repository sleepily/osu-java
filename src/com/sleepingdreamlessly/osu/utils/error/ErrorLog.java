package com.sleepingdreamlessly.osu.utils.error;

public class ErrorLog
{
	private String level = ErrorLevel.VERBOSE;
	private String log;
	
	public ErrorLog(String level, String log)
	{
		this.level = level;
		this.log = log;
	}
	
	public void log()
	{
		// @TODO: implement level functionality
		System.out.println(log);
	}
}
