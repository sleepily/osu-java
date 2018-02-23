package com.sleepingdreamlessly.osu.objects.mania;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.objects.HitObject;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.utils.Utils;
import com.sleepingdreamlessly.osu.utils.Vector2;

public class ManiaHitObject extends HitObject
{
	protected long time;
	protected float alpha = 1f;
	protected Vector2 pos = new Vector2();
	
	public ManiaHitObject(Game game, String id, int x, long time)
	{
		super(game, id, x, 0, time);
		this.pos.x = ((pos.x - 2) * 60 + UI.getJudgementLine().x);
		this.TYPE = "mania";
		this.scale = .25f;
	}
	
	public void tick()
	{
		if (this.time < this.game.getTime_rel_current_ms() - 200)
			this.dispose = true;
		
		// this.calculateY();
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
		if (game.getTime_rel_current_ms() < 10)
		{
			this.pos.y = 0;
			return;
		}
		
		this.pos.y = (UI.getJudgementLine().y) - (this.time - game.getTime_rel_current_ms()) / (float)(20f / UI.getScrollSpeed());
		// this.pos.y = (int) Utils.map((float)game.getTime_rel_current_ms(), (float)(this.time - (20f / UI.getScrollSpeed())), this.time, -50, UI.getJudgementLine().y);
	}
	
	protected void calculateAlpha()
	{
		this.alpha = 1f;
	}
}
