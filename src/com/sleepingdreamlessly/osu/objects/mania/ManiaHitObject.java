package com.sleepingdreamlessly.osu.objects.mania;

import com.sleepingdreamlessly.osu.Game;
import com.sleepingdreamlessly.osu.objects.HitObject;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.utils.Vector2;

public class ManiaHitObject extends HitObject
{
	protected long time; // perfect hit time, milliseconds
	protected float alpha = 1f; // 0f to 1f
	protected Vector2 pos = new Vector2();
	
	public ManiaHitObject(Game game, String id, int x, long time)
	{
		super(game, id, x, 0, time);
		this.TYPE = "mania";
		this.scale = .25f;
	}
	
	public void tick()
	{
		if (this.y < sprite.i.getHeight(null) / 2 * this.scale)
			this.dispose = true;
		
		this.calculateY();
		this.calculateAlpha();
	}
	
	public void render(UI ui)
	{
		this.sprite.drawCenteredWithScale(
			this.game,
			(int)((pos.x - 2) * 60 + ui.getJudgementLine().x),
			(int)(pos.y),
			this.scale,
			this.alpha
		);
	}
	
	private void calculateY()
	{
		this.pos.y = (UI.getJudgementLine().y) - (this.time - game.getTime_rel_current_ms()) / (float)(20f / UI.getScrollSpeed());
	}
	
	protected void calculateAlpha()
	{
	
	}
}
