package com.sleepingdreamlessly.osu.objects;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.utils.Vector2;

import java.awt.*;

public class OsuHitObject extends HitObject
{
	protected double AR, CS;
	public static double OBJECT_RADIUS = 64;
	public float scale = 1f;
	public double radius = OBJECT_RADIUS * scale;
	
	public Color comboColour = Color.GRAY;
	
	public boolean isNewCombo = false;
	public int combo = 1;
	
	public OsuHitObject(Game game, String id, int pos_x, int pos_y, long time)
	{
		super(game, id, pos_x, pos_y, time);
		this.AR = game.ApproachRate;
		this.CS = game.CircleSize;
		
		this.scale = (float) ((1.0f - 0.7f * (this.CS - 5) / 5) / 2);
	}
	
	public OsuHitObject(Game game, String id, Vector2 pos, long time)
	{
		super(game, id, (int)pos.x, (int)pos.y, time);
		this.AR = game.ApproachRate;
		this.CS = game.CircleSize;
		
		this.scale = (float) ((1.0f - 0.7f * (this.CS - 5) / 5) / 2);
	}
	
	public OsuHitObject(HitObject h)
	{
		super(h.game, h.id, (int)h.pos.x, (int)h.pos.y, h.time);
		this.AR = game.ApproachRate;
		this.CS = game.CircleSize;
		
		this.scale = (float) ((1.0f - 0.7f * (this.CS - 5) / 5) / 2);
	}
	
	public void tick()
	{
	
	}
	
	public void render(UI ui)
	{
	
	}
}
