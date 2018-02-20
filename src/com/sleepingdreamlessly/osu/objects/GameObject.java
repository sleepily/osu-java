package com.sleepingdreamlessly.osu.objects;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.assets.Assets;
import com.sleepingdreamlessly.osu.graphics.Sprite;
import com.sleepingdreamlessly.osu.rulesets.UI;

public abstract class GameObject
{
	protected Sprite sprite;
	protected float x, y;
	protected Game game;
	public String TYPE, id;
	
	public GameObject(Game game, String id)
	{
		this.id = id;
		this.sprite = Assets.get(id);
		this.game = game;
		this.TYPE = "GameObject";
	}
	
	public void tick()
	{
	
	}
	
	public void render(UI ui)
	{
	
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
}
