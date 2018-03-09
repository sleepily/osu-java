package com.sleepingdreamlessly.osu.objects;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.assets.Assets;
import com.sleepingdreamlessly.osu.graphics.Sprite;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.utils.Vector2;

public abstract class GameObject
{
	public Sprite sprite;
	protected Vector2 pos = new Vector2();
	protected Game game;
	public String TYPE, id;
	public boolean dispose = false;
	
	public GameObject(Handler handler, String id)
	{
		this.id = id;
		this.sprite = Assets.getSprite(id);
		this.game = handler.getGame();
		this.TYPE = "GameObject";
	}
	
	public void tick()
	{
	
	}
	
	public void render(UI ui)
	{
	
	}
	
	public Vector2 getPos()
	{
		return pos;
	}
}
