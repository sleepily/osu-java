package com.sleepingdreamlessly.osu.utils;

public class Vector2
{
	public float x, y = 0f;
	
	public Vector2()
	{
		this.x = 0f;
		this.y = 0f;
	}
	
	public Vector2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2 mul(float m)
	{
		return new Vector2(this.x * m, this.y * m);
	}
	
	public Vector2 mul(Vector2 v)
	{
		return new Vector2(this.x * v.x, this.y * v.y);
	}
	
	public Vector2 add(Vector2 v)
	{
		return new Vector2(this.x + v.x, this.y + v.y);
	}
	
	public Vector2 sub(Vector2 v)
	{
		return new Vector2(this.x - v.x, this.y - v.y);
	}
	
	public Vector2 div(float m)
	{
		if (m == 0)
			return new Vector2();
		
		return new Vector2(this.x / m, this.y / m);
	}
	
	public Vector2 div(Vector2 v)
	{
		if (v.x == 0 || v.y == 0)
			return new Vector2();
		
		return new Vector2(this.x / v.x, this.y / v.y);
	}
	
	public double getLength()
	{
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	public double getDistance(Vector2 v)
	{
		return Math.abs(Math.sqrt(Math.pow(v.x - this.x, 2) + Math.pow(v.y - this.y, 2)));
	}
}
