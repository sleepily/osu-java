package com.sleepingdreamlessly.osu.objects;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.assets.Assets;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.rulesets.judgement.OsuJudgement;
import com.sleepingdreamlessly.osu.rulesets.std.CircleSize;
import com.sleepingdreamlessly.osu.utils.Vector2;

import java.awt.*;

public class OsuHitObject extends HitObject
{
	public double AR, CS, OD;
	public static double OBJECT_RADIUS = 64;
	public float scale = 1f;
	public double radius = OBJECT_RADIUS * scale;
	
	public Color comboColour = Color.GRAY;
	
	public boolean isNewCombo = false;
	public boolean isComboEnd = false;
	public int combo = 0;
	
	public OsuHitObject(Handler handler, String id, int pos_x, int pos_y, long time)
	{
		super(handler, id, pos_x, pos_y, time);
		this.init();
	}
	
	public OsuHitObject(Handler handler, String id, Vector2 pos, long time)
	{
		super(handler, id, (int)pos.x, (int)pos.y, time);
		this.init();
	}
	
	public OsuHitObject(HitObject h)
	{
		super(h.handler, h.id, (int)h.pos.x, (int)h.pos.y, h.time);
		this.init();
	}
	
	private void init()
	{
		this.AR = game.ApproachRate;
		this.CS = game.CircleSize;
		this.OD = game.OverallDifficulty;
		
		this.scale = (float)CircleSize.circleScale_hitCircle(this); //(float)((1.0f - 0.7f * (this.CS - 5) / 5) / 2);
		this.radius = OBJECT_RADIUS * scale;
	}
	
	public boolean keyDown()
	{
		if (isHit)
			return false;
		
		boolean key =
			handler.getInputManager().std_left      .click  ||
			handler.getInputManager().std_right     .click  ||
			handler.getInputManager().std_mouse_left.click  ||
			handler.getInputManager().std_mouse_left.click;
		
		if (!key)
			return false;
		
		this.time_hit = this.game.beatmap.song.position;
		
		double distance = this.pos.getDistance(handler.getInputManager().mouseManager.pos) - this.radius - Cursor.radius;
		
		if (distance > 0)
			return false;
		
		this.judgement = OsuJudgement.getJudgement(this);
		
		if (this.judgement == -1)
		{
			this.time_hit = -1;
			return false;
		}
		
		this.hit();
		return true;
	}
	
	public void hit()
	{
		this.isHit = true;
		this.time_hit = this.game.beatmap.song.position;
		
		this.judgement = OsuJudgement.getJudgement(this);
		
		this.handler.getInputManager().tick();
		
		String comboEndString = ""; // geki (perfect combo) / katu (combo end)
		
		if (isComboEnd)
			comboEndString = "k";
		
		this.judgementSprite = Assets.getSprite(String.format("hit%s%s", this.judgement, comboEndString));
	}
	
	public void tick()
	{
		this.keyDown();
		
		if (!isHit && this.game.beatmap.song.position > this.time + 300)
		{
			this.judgement = 0;
			this.judgementSprite = Assets.getSprite("hit0");
			System.out.println(this.judgement);
		}
	}
	
	public void render(UI ui)
	{
	
	}
}
