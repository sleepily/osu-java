package com.sleepingdreamlessly.osu.objects;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.graphics.Sprite;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.rulesets.judgement.OsuJudgement;
import com.sleepingdreamlessly.osu.utils.Vector2;

import java.awt.*;

public class OsuHitObject extends HitObject
{
	public double AR, CS, OD;
	public static double OBJECT_RADIUS = 64;
	public float scale = 1f;
	public double radius = OBJECT_RADIUS * scale;
	
	private Sprite judgement;
	
	public Color comboColour = Color.GRAY;
	
	public boolean isNewCombo = false;
	public int combo = 1;
	
	public OsuHitObject(Handler handler, String id, int pos_x, int pos_y, long time)
	{
		super(handler, id, pos_x, pos_y, time);
		this.AR = game.ApproachRate;
		this.CS = game.CircleSize;
		this.OD = game.OverallDifficulty;
		
		this.scale = (float) ((1.0f - 0.7f * (this.CS - 5) / 5) / 2);
	}
	
	public OsuHitObject(Handler handler, String id, Vector2 pos, long time)
	{
		super(handler, id, (int)pos.x, (int)pos.y, time);
		this.AR = game.ApproachRate;
		this.CS = game.CircleSize;
		this.OD = game.OverallDifficulty;
		
		this.scale = (float) ((1.0f - 0.7f * (this.CS - 5) / 5) / 2);
	}
	
	public OsuHitObject(HitObject h)
	{
		super(h.handler, h.id, (int)h.pos.x, (int)h.pos.y, h.time);
		this.AR = game.ApproachRate;
		this.CS = game.CircleSize;
		
		this.scale = (float) ((1.0f - 0.7f * (this.CS - 5) / 5) / 2);
	}
	
	public boolean keyDown()
	{
		if (isHit)
			return false;
		
		boolean key =
			handler.getInputManager().std_left || handler.getInputManager().std_right
			|| handler.getInputManager().std_mouse_left || handler.getInputManager().std_mouse_left;
		
		if (!key)
			return false;
		
		this.time_hit = this.game.getTime_rel_current_ms();
		
		double distance = this.pos.getDistance(handler.getInputManager().mouseManager.pos) - this.radius - Cursor.radius;
		
		if (distance > 0)
			return false;
		
		if (OsuJudgement.getJudgement(this) == -1)
			return false;
		
		this.hit();
		return true;
	}
	
	public void hit()
	{
		this.isHit = true;
		this.judgement = OsuJudgement.getJudgement(this);
		// System.out.println(this.judgement);
	}
	
	public void tick()
	{
		this.keyDown();
	}
	
	public void render(UI ui)
	{
	
	}
}
