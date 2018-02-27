package com.sleepingdreamlessly.osu.rulesets;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.utils.Vector2;

public class UI
{
	private static Vector2 screen, playfieldPadding;
	private static Vector2 BASE_SIZE = new Vector2(512, 384);
	
	private static double mania_scrollSpeed = 16;
	
	public UI(Game game)
	{
		this.screen = new Vector2(game.getWidth(), game.getHeight());
		this.playfieldPadding = getPlayfieldPadding();
	}
	
	public static Vector2 getOsuPixelScale()
	{
		Vector2 aspectSize;
		if (screen.x * 0.75f < screen.y)
			aspectSize = new Vector2(screen.x, screen.x * 0.75f);
		else
			aspectSize = new Vector2(screen.y * 4f / 3f, screen.y);
		
		// return new Vector2(aspectSize.x / parentSize.x, aspectSize.y / parentSize.y);
		return aspectSize.div(screen); //new Vector2(.75f, 1f);
	}
	
	public static Vector2 getScreenVector()
	{
		Vector2 osuPixel = getOsuPixelScale();
		
		// System.out.println(osuPixel.mul(BASE_SIZE.x).x + "x, " +  osuPixel.mul(BASE_SIZE.x).y + "y ");
		
		return osuPixel.mul(BASE_SIZE); //osuPixel.mul(BASE_SIZE.x);
	}
	
	public static Vector2 getPlayfieldPadding()
	{
		return screen.sub(getScreenVector()).mul(.5f);
	}
	
	public static Vector2 getBaseSize()
	{
		return BASE_SIZE;
	}
	
	public static Vector2 getJudgementLine()
	{
		return new Vector2(screen.x / 2f, screen.y - 100f);
	}
	
	public static double getScrollSpeed()
	{
		return mania_scrollSpeed;
	}
}