package com.sleepingdreamlessly.osu.objects;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.audio.AudioClip;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.utils.Vector2;

public class HitObject extends GameObject
{
	public Handler handler;
	
	protected long time; // perfect hit time, milliseconds
	protected float alpha = 1f; // 0f to 1f
	protected Vector2 pos = new Vector2(); // STD: 0 to 512 X, 0 to 384 Y (osu!pixels), mania/catch: only x
	public float scale = 1f;
	public boolean isHit = false;
	public long time_hit;
	
	public AudioClip sample;
	protected boolean samplePlayed = false;
	
	public HitObject(Handler handler, String id, int pos_x, int pos_y, long time)
	{
		super(handler, id);
		this.handler = handler;
		this.game = handler.getGame();
		this.time = time;
		this.pos.x = pos_x;
		this.pos.y = pos_y;
	}
	
	public HitObject(Handler handler, String id, Vector2 pos, long time)
	{
		super(handler, id);
		this.game = handler.getGame();
		this.time = time;
		this.pos.x = pos.x;
		this.pos.y = pos.y;
	}
	
	public void hit()
	{
		this.isHit = true;
		this.time_hit = this.game.getTime_rel_current_ms();
	}
	
	public void tick()
	{
	
	}
	
	public void render(UI ui)
	{
	
	}
}
