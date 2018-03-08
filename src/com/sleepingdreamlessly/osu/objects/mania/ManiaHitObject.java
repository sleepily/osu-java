package com.sleepingdreamlessly.osu.objects.mania;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.assets.Assets;
import com.sleepingdreamlessly.osu.objects.HitObject;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.rulesets.judgement.ManiaJudgement;
import com.sleepingdreamlessly.osu.utils.Utils;
import com.sleepingdreamlessly.osu.utils.Vector2;

public class ManiaHitObject extends HitObject
{
	public double OD, AR;
	protected float alpha = 1f;
	protected int column;
	
	protected Vector2 judgementSpriteOffset;
	
	public ManiaHitObject(Handler handler, String id, int x, long time)
	{
		super(handler, id, new Vector2(x, 0), time);
		this.OD = handler.getGame().OverallDifficulty;
		this.scale = .25f;
		this.column = x;
		this.pos.x = ((pos.x - 2) * this.sprite.i.getWidth(null) * this.scale + UI.getJudgementLine().x); // convert index to screen coordinate
		this.judgementSpriteOffset = new Vector2(this.pos.x, UI.getJudgementLine().y -200);
		this.TYPE = "mania";
	}
	
	public void tick()
	{
		this.keyDown();
		
		double spriteHeight = this.sprite.i.getWidth(null) * scale;
		if (this.pos.y > UI.getScreenVector().y + spriteHeight * 3)
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
		
		if (!isHit)
			return;
		
		this.judgementSprite.drawCenteredWithScale
		(
			this.game,
			(int)(judgementSpriteOffset.x),
			(int)(judgementSpriteOffset.y),
			1f,
			1f
		);
	}
	
	public boolean keyDown()
	{
		if (isHit)
			return false;
		
		boolean key = this.handler.getInputManager().maniaKeys[this.column];
		
		if (!key)
			return false;
		
		this.time_hit = this.game.getTime_rel_current_ms();
		
		if (ManiaJudgement.getJudgement(this) == -1)
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
		this.judgement = ManiaJudgement.getJudgement(this);
		this.judgementSprite = Assets.getSprite(String.format("mania-hit%s", this.judgement));
		System.out.println(this.judgement);
	}
	
	private void calculateY()
	{
		this.pos.y = (int)
			Utils.map
			(
				(float)this.game.beatmap.song.position,
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
