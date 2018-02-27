package com.sleepingdreamlessly.osu.objects.mania;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.objects.HitObject;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.rulesets.judgement.ManiaJudgement;
import com.sleepingdreamlessly.osu.utils.Utils;
import com.sleepingdreamlessly.osu.utils.Vector2;

public class ManiaHitObject extends HitObject
{
	public double OD, AR;
	protected float alpha = 1f;
	
	public ManiaHitObject(Handler handler, String id, int x, long time)
	{
		super(handler, id, new Vector2(x, 0), time);
		this.OD = handler.getGame().OverallDifficulty;
		this.scale = .25f;
		this.pos.x = ((pos.x - 2) * this.sprite.i.getWidth(null) * this.scale + UI.getJudgementLine().x); // convert index to screen coordinate
		this.TYPE = "mania";
	}
	
	public void tick()
	{
		double spriteHeight = this.sprite.i.getWidth(null) * scale;
		if (this.pos.y > UI.getScreenVector().y + spriteHeight)
			this.dispose = true;
		
		this.calculateY();
		this.calculateAlpha();
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
	
	public void hit()
	{
		this.isHit = true;
		this.time_hit = this.game.getTime_rel_current_ms();
		this.judgement = ManiaJudgement.getJudgement(this);
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
					UI.getJudgementLine().y
				);
	}
	
	protected void calculateAlpha()
	{
		this.alpha = 1f;
	}
}
