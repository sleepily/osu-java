package com.sleepingdreamlessly.osu.objects.std;

import com.sleepingdreamlessly.osu.Handler;
import com.sleepingdreamlessly.osu.assets.Assets;
import com.sleepingdreamlessly.osu.audio.AudioPlayer;
import com.sleepingdreamlessly.osu.graphics.Sprite;
import com.sleepingdreamlessly.osu.objects.OsuHitObject;
import com.sleepingdreamlessly.osu.rulesets.judgement.OsuJudgement;
import com.sleepingdreamlessly.osu.rulesets.std.CircleSize;
import com.sleepingdreamlessly.osu.rulesets.std.Timings;
import com.sleepingdreamlessly.osu.rulesets.UI;
import com.sleepingdreamlessly.osu.utils.Utils;
import com.sleepingdreamlessly.osu.utils.Vector2;

public class OsuHitCircle extends OsuHitObject
{
	private Sprite approachCircle;
	private Sprite hitcircleoverlay;
	private Sprite sprite_combo;
	
	private long time_start_fadeIn = Timings.getTimeForCircle_fadeIn(game.ApproachRate, this.time);
	private long time_fadedCompletely;
	private long time_miss;
	
	public OsuHitCircle(Handler handler, String id, int pos_x, int pos_y, long time)
	{
		super(handler, id, new Vector2(pos_x, pos_y), time);
		this.init();
	}
	
	public OsuHitCircle(Handler handler, String id, int pos_x, int pos_y, long time, int combo)
	{
		super(handler, id, new Vector2(pos_x, pos_y), time);
		this.init();
		this.sprite_combo = Assets.font_default_numbers[combo % 10];
		this.isNewCombo = (combo == 1);
		this.time_miss = (int)OsuJudgement.getHitWindows(this)[3];
	}
	
	private void init()
	{
		this.time_fadedCompletely = this.time + this.time - Timings.getTimeForCircle_fadeIn(game.ApproachRate, this.time);
		this.approachCircle = Assets.approachcircle;
		this.hitcircleoverlay = Assets.hitcircleoverlay;
		this.sprite_combo = Assets.font_combo_numbers[combo];
		this.TYPE = "std";
		this.pos =
			new Vector2
			(
				(int)Utils.mapAndClamp(this.pos.x, 0, game.getUI().getBaseSize().x, 0, game.getUI().getScreenVector().x),
				(int)Utils.mapAndClamp(this.pos.y, 0, game.getUI().getBaseSize().y, 0, game.getUI().getScreenVector().y)
			);
		this.sample = Assets.getAudioClip("soft-hitnormal");
	}
	
	public void tick()
	{
		this.keyDown();
		
		/*
		if (AudioPlayer.getPosition(this.game.beatmap.song) <= this.time)
			this.calculateAlphaFadeIn();
		*/
		
		if (this.game.beatmap.song.position >= this.getDisposeTime())
			this.dispose = true;
		
		if (!isHit)
			return;
		
		if (!this.samplePlayed)
			this.playSample();
	}
	
	private long getDisposeTime()
	{
		if (isHit)
			return this.time_hit + this.time_miss;
		
		return this.time + this.time_miss;
	}
	
	public void render(UI ui)
	{
		// dont render before fade in time to save resources
		if (this.game.beatmap.song.position < time_start_fadeIn)
			return;
			
		if (!isHit)
		{
			this.renderHitCircle(ui);
			return;
		}
		
		this.judgementSprite.drawCenteredWithScale
		(
			this.game,
			(int)(pos.x + ui.getPlayfieldPadding().x),
			(int)(pos.y + ui.getPlayfieldPadding().y),
			1f,
			this.alpha
		);
	}
	
	private void renderHitCircle(UI ui)
	{
		this.sprite.drawCenteredWithSize
		(
			this.game,
			(int)(pos.x + ui.getPlayfieldPadding().x),
			(int)(pos.y + ui.getPlayfieldPadding().y),
			CircleSize.circleSize_hitCircle(this) * 2,
			this.alpha
		);
	
		this.hitcircleoverlay.drawCenteredWithSize
		(
			this.game,
			(int)(pos.x + ui.getPlayfieldPadding().x),
			(int)(pos.y + ui.getPlayfieldPadding().y),
			CircleSize.circleSize_hitCircle(this) * 2,
			this.alpha
		);
	
		this.sprite_combo.drawCenteredWithScale
		(
			this.game,
			(int)(pos.x + ui.getPlayfieldPadding().x),
			(int)(pos.y + ui.getPlayfieldPadding().y),
			Utils.mapAndClamp((float)CircleSize.circleSize_hitCircle(this), 20, 120,.4f, 2f),
			this.alpha
		);
	
		this.approachCircle.drawCenteredWithSize
		(
			this.game,
			(int)(pos.x + ui.getPlayfieldPadding().x),
			(int)(pos.y + ui.getPlayfieldPadding().y),
			CircleSize.circleSize_approachCircle(this) * 2,
			this.alpha
		);
	}
	
	protected void playSample()
	{
		this.samplePlayed = true;
		AudioPlayer.play(handler, this.sample);
	}
	
	protected void calculateAlphaFadeIn()
	{
		this.alpha = Utils.mapAndClamp
		(
			this.game.beatmap.song.position,
			Timings.getTimeForCircle_fadeIn(game.ApproachRate, this.time),
			Timings.getTimeForCircle_visible(game.ApproachRate, this.time),
			0,
			1
		);
	}
}
