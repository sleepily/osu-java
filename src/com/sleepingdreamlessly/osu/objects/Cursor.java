package com.sleepingdreamlessly.osu.objects;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.assets.Assets;

public class Cursor extends GameObject
{
	private Handler handler;
	
	public static double OBJECT_RADIUS = 64;
	public static float scale = 1f;
	public static double radius = OBJECT_RADIUS * scale;
	
	public Cursor(Handler handler)
	{
		super(handler, "cursor");
		this.handler = handler;
		this.sprite = Assets.getSprite(id);
	}
	
	public void tick()
	{
		this.pos = handler.getGame().getInputManager().mouseManager.pos;
	}
	
	public void render()
	{
		this.sprite.drawCenteredWithScale(handler.getGame(), (int)pos.x, (int)pos.y, this.scale, 1f);
	}
}