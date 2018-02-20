package com.sleepingdreamlessly.osu.objects;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.utils.Vector2;

public class HitObject extends GameObject
{
	protected long time; // perfect hit time, milliseconds
	protected float alpha = 1f; // 0f to 1f
	protected Vector2 pos = new Vector2(); // STD: 0 to 512 X, 0 to 384 Y (osu!pixels), mania/catch: only x
	public float scale = 1f;
	
	public HitObject(Game game, String id, int pos_x, int pos_y, long time)
	{
		super(game, id);
		this.game = game;
		this.time = time;
		this.pos.x = pos_x;
		this.pos.y = pos_y;
	}
	
	public HitObject(Game game, String id, Vector2 pos, long time)
	{
		super(game, id);
		this.game = game;
		this.time = time;
		this.pos.x = pos.x;
		this.pos.y = pos.y;
	}
	
	public void tick()
	{
	
	}
	
	public void render(UI ui)
	{
	
	}
}
