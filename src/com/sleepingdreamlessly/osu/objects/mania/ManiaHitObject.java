package com.sleepingdreamlessly.osu.objects.mania;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.objects.HitObject;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.utils.Utils;
import com.sleepingdreamlessly.osu.utils.Vector2;

public class ManiaHitObject extends HitObject
{
	protected float alpha = 1f;
	
	public ManiaHitObject(Game game, String id, int x, long time)
	{
		super(game, id, x, 0, time);
		this.pos.x = ((pos.x - 2) * 60 + UI.getJudgementLine().x); // convert index to screen coordinate
		this.TYPE = "mania";
		this.scale = .25f;
	}
	
	public void tick()
	{
		/*
		if (game.getTime_rel_current_ms() > this.time)
			this.dispose = true;
		*/
		
		this.calculateY();
		// this.calculateAlpha();
	}
	
	public void render(UI ui)
	{
		this.sprite.drawCenteredWithScale(
			this.game,
			(int)(pos.x),
			(int)(pos.y),
			this.scale,
			this.alpha
		);
	}
	
	private void calculateY()
	{		
		// this.pos.y = (UI.getJudgementLine().y) - (this.time - game.getTime_rel_current_ms()) * (float)(2f / UI.getScrollSpeed());
		
		this.pos.y = (int)
			Utils.map(
					(float)this.game.getTime_rel_current_ms(),
					(float)(this.time - (10000f / UI.getScrollSpeed())),
					(float)this.time,
					(float)-50,
					(float)UI.getJudgementLine().y
				);
	}
	
	protected void calculateAlpha()
	{
		this.alpha = 1f;
	}
}
